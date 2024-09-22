package gsheets.customfunctions

import gsheets.cells.Cell.*
import gsheets.cells.{Cell, GSheetCellValue, GSheetGrid, JsGrid, ScalaCellGrid, ScalaGrid}
import scala.scalajs.js
import scala.util.Try

/** An [[Encoder]] receives inputs from the spreadsheet cells and translates it to an
  * instance of T.
  *
  * The user must implement the method encode that returns a [[Try]] of T, containing
  * either the transformed input, or an exception describing what went wrong when parsing
  * the input.
  *
  * @tparam T
  *   output type of this encoder.
  */
trait Encoder[+T] {

  def encode(grid: GSheetGrid): Try[T]

  final def apply(input: Input): Try[T] = input match {
    case input: js.Array[_] => encode(input.asInstanceOf[GSheetGrid])
    case input              => encode(JsGrid.one(input.asInstanceOf[GSheetCellValue]))
  }

}

object Encoder {

  implicit final val stringEncoder: Encoder[String] =
    (data: GSheetGrid) => Try(data(0)(0).toString)

  implicit final val intEncoder: Encoder[Int] =
    (data: GSheetGrid) => Try(data(0)(0).asInstanceOf[Double].toInt)

  implicit final val doubleEncoder: Encoder[Double] =
    (data: GSheetGrid) => Try(data(0)(0).asInstanceOf[Double])

  implicit final val dateEncoder: Encoder[js.Date] =
    (data: GSheetGrid) => Try(data(0)(0).asInstanceOf[js.Date])

  implicit final val booleanEncoder: Encoder[Boolean] =
    (data: GSheetGrid) => Try(data(0)(0).asInstanceOf[Boolean])

  implicit final val cellsEncoder: Encoder[ScalaCellGrid] =
    (data: GSheetGrid) => Try(data.asScala)

  implicit final val vectorStringEncoder: Encoder[ScalaGrid[String]] =
    (data: GSheetGrid) => Try(data.asScala.deepMap(_.toString))

  implicit final val vectorIntEncoder: Encoder[ScalaGrid[Int]] =
    (data: GSheetGrid) => Try(data.asScala.deepMap(_.toInt.get))

  implicit final val vectorTryIntEncoder: Encoder[ScalaGrid[Try[Int]]] =
    (data: GSheetGrid) => Try(data.asScala.deepMap(_.toInt))

}
