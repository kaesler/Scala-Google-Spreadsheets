package gsheets.cells

import scala.annotation.targetName
import scala.collection.immutable.Seq
import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import scala.util.{Failure, Success, Try}

/** A Cell is simply a wrapper for values that we get from Google SpreadSheets. As the
  * Google doc says, a value can be a String, a Number (Double), a js.Date or a Boolean.
  */
final case class Cell(value: GSheetCellValue):

  /** Returns whether the content is a Double. */
  def isNumeric: Boolean = (value: Any).isInstanceOf[Double]

  /** Returns whether the content is a Date. */
  def isDate: Boolean = value.isInstanceOf[js.Date]

  def isBoolean: Boolean = (value: Any).isInstanceOf[Boolean]

  /** Returns whether this cell contains the empty string, which comes out of empty
    * spreadsheet cells.
    */
  def isEmpty: Boolean = (value: Matchable) match
    case s: String if s == "" => true
    case _                    => false

  /** Maps the content of this cell via the function f. */
  def map(f: GSheetCellValue => GSheetCellValue): Cell = Cell(f(value))

  /** Returns whether the cell actually contains something. */
  def nonEmpty: Boolean = !isEmpty

  @targetName("equals")
  def ==(that: String): Boolean = (value: Matchable) match
    case s: String => s == that
    case _         => false

  @targetName("equals")
  def ==(that: Double): Boolean = (value: Matchable) match
    case x: Double => x == that
    case _         => false

  def toDouble: Try[Double] = (value: Matchable) match
    case value: Double => Success(value)
    case value: Int    => Success(value)
    case _ =>
      Failure(
        WrongDataTypeException(
          s"value ($value) data type is ${value.getClass}, but should be Double."
        )
      )

  def toInt: Try[Int] = (value: Matchable) match
    case value: Double => Success(value.toInt)
    case value: String => Success(value.toInt)
    case _ =>
      Failure(
        WrongDataTypeException(
          s"value data type is ${value.getClass}, but should be Int."
        )
      )

  def toDate: Try[js.Date] = value match
    case value: js.Date => Success(value)
    case _ =>
      Failure(
        WrongDataTypeException(
          s"value data type is ${value.getClass}, but should be js.Date."
        )
      )

  override def toString: String = value.toString
