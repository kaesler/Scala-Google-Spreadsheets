package gsheets.cells

import gsheets.cells.Cell.Data
import scala.collection.immutable.Seq
import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import scala.scalajs.js.|
import scala.util.Try

/** A Cell is simply a wrapper for values that we get from Google SpreadSheets. As the
  * Google doc says, a value can be a String, a Number (Double), a js.Date or a Boolean.
  */
final case class Cell(value: Data) {

  /** Returns whether the content is a Double. */
  def isNumeric: Boolean = (value: Any).isInstanceOf[Double]

  /** Returns whether the content is a Date. */
  def isDate: Boolean = value.isInstanceOf[js.Date]

  def isBoolean: Boolean = (value: Any).isInstanceOf[Boolean]

  /** Returns whether this cell contains the empty string, which comes out of empty
    * spreadsheet cells.
    */
  def isEmpty: Boolean = (value: Matchable) match {
    case s: String if s == "" => true
    case _                    => false
  }

  /** Maps the content of this cell via the function f. */
  def map(f: Data => Data): Cell = Cell(f(value))

  /** Returns wheter the cell actually contains something. */
  def nonEmpty: Boolean = !isEmpty

  def ==(that: String): Boolean = (value: Matchable) match {
    case s: String => s == that
    case _         => false
  }

  def ==(that: Double): Boolean = (value: Matchable) match {
    case x: Double => x == that
    case _         => false
  }

  def toDouble: Try[Double] = Try {
    (value: Matchable) match {
      case value: Double => value
      case value: Int    => value
      case _ =>
        throw new WrongDataTypeException(
          s"value ($value) data type is ${value.getClass}, but should be Double."
        )
    }
  }

  def toInt: Try[Int] = Try {
    (value: Matchable) match {
      case value: Double => value.toInt
      case value: String => value.toInt
      case _ =>
        throw new WrongDataTypeException(
          s"value data type is ${value.getClass}, but should be Int."
        )
    }
  }

  def toDate: Try[js.Date] = Try {
    value match {
      case value: js.Date => value
      case _ =>
        throw new WrongDataTypeException(
          s"value data type is ${value.getClass}, but should be js.Date."
        )
    }
  }

  override def toString: String = value.toString

}

object Cell {

  /** The type of Data that we can receive from Google SpreadSheets. */
  // TODO: rename CellValue
  type Data = String | Double | Boolean | js.Date

  /** Transposes the rows into columns. */
  def columns(row: Vector[Vector[Cell]]): Vector[Vector[Cell]] = row.transpose

  def toJSArray(cells: Seq[Seq[Cell]]): js.Array[js.Array[Data]] =
    cells.map(_.map(_.value).toJSArray).toJSArray

  def fromJSArray(cells: js.Array[js.Array[Data]]): Vector[Vector[Cell]] =
    cells.map(fromJSFlatArray).toVector

  def fromJSFlatArray(cells: js.Array[Data]): Vector[Cell] =
    cells.toVector.map(new Cell(_))

  implicit def fromDouble(x: Double): Cell = Cell(x)

  implicit def fromString(s: String): Cell = Cell(s)

  implicit def fromDate(d: js.Date): Cell = Cell(d)

  implicit def fromBoolean(b: Boolean): Cell = Cell(b)

  // implicit def fromJS(value: Data): Cell = Cell(value)

  // TODO: kae: extension method
  implicit class VectorToJS(cells: Vector[Vector[Cell]]) {
    def toGoogleCells: js.Array[js.Array[Data]] = toJSArray(cells)

    def deepMap[U](f: Cell => U): Vector[Vector[U]] = cells.map(_.map(f))
  }

  // TODO: kae: extension method
  implicit class JSToVector(cells: js.Array[js.Array[Data]]) {
    def asScala: Vector[Vector[Cell]] = fromJSArray(cells)
  }

}
