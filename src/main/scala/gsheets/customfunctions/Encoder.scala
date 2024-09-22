package gsheets.customfunctions

import gsheets.cells.*
import gsheets.cells.GridExtensions.*
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

  given Encoder[String] =
    (data: GSheetGrid) => Try(data(0)(0).toString)

  given Encoder[Int] =
    (data: GSheetGrid) => Try(data(0)(0).asInstanceOf[Double].toInt)

  given Encoder[Double] =
    (data: GSheetGrid) => Try(data(0)(0).asInstanceOf[Double])

  given Encoder[js.Date] =
    (data: GSheetGrid) => Try(data(0)(0).asInstanceOf[js.Date])

  given Encoder[Boolean] =
    (data: GSheetGrid) => Try(data(0)(0).asInstanceOf[Boolean])

  given Encoder[ScalaCellGrid] =
    (data: GSheetGrid) => Try(data.asScala)

  given encodeGridString: Encoder[ScalaGrid[String]] =
    (data: GSheetGrid) => Try(data.asScala.deepMap(_.toString))

  given encodeGridInt: Encoder[ScalaGrid[Int]] =
    (data: GSheetGrid) => Try(data.asScala.deepMap(_.toInt.get))

  given vectorTryIntEncoder: Encoder[ScalaGrid[Try[Int]]] =
    (data: GSheetGrid) => Try(data.asScala.deepMap(_.toInt))
}
