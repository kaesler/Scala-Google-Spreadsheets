package gsheets.cells

import scala.collection.immutable.Seq
import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import scala.scalajs.js.|
import scala.util.Try

/** A Cell is simply a wrapper for values that we get from Google SpreadSheets. As the
  * Google doc says, a value can be a String, a Number (Double), a js.Date or a Boolean.
  */
final case class Cell(value: CellValue) {

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
  def map(f: CellValue => CellValue): Cell = Cell(f(value))

  /** Returns whether the cell actually contains something. */
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

  def fromGrid(grid: CellValueGrid): Vector[Vector[Cell]] =
    grid.map(fromJSFlatArray).toVector

  private def fromJSFlatArray(cells: js.Array[CellValue]): Vector[Cell] =
    cells.toVector.map(Cell.apply)

  // TODO: kae: extension method
  implicit class VectorsToGrid(cells: Vector[Vector[Cell]]) {
    def toGrid: js.Array[js.Array[CellValue]] =
      cells.map(_.map(_.value).toJSArray).toJSArray

    def deepMap[U](f: Cell => U): Vector[Vector[U]] = cells.map(_.map(f))
  }

  // TODO: kae: extension method
  implicit class GridToVectors(grid: CellValueGrid) {
    def asScala: Vector[Vector[Cell]] = fromGrid(grid)
  }
}
