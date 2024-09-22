package gsheets.customfunctions

import gsheets.cells.Cell.*
import gsheets.cells.{Cell, CellValue, CellValueGrid, Grid}
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

  def encode(grid: CellValueGrid): Try[T]

  final def apply(input: Input): Try[T] = input match {
    case input: js.Array[_] => encode(input.asInstanceOf[CellValueGrid])
    case input              => encode(Grid.one(input.asInstanceOf[CellValue]))
  }

}

object Encoder {

  implicit final val stringEncoder: Encoder[String] =
    (data: CellValueGrid) => Try(data(0)(0).toString)

  implicit final val intEncoder: Encoder[Int] =
    (data: CellValueGrid) => Try(data(0)(0).asInstanceOf[Double].toInt)

  implicit final val doubleEncoder: Encoder[Double] =
    (data: CellValueGrid) => Try(data(0)(0).asInstanceOf[Double])

  implicit final val dateEncoder: Encoder[js.Date] =
    (data: CellValueGrid) => Try(data(0)(0).asInstanceOf[js.Date])

  implicit final val booleanEncoder: Encoder[Boolean] =
    (data: CellValueGrid) => Try(data(0)(0).asInstanceOf[Boolean])

  implicit final val cellsEncoder: Encoder[Vector[Vector[Cell]]] =
    (data: CellValueGrid) => Try(data.asScala)

  implicit final val vectorStringEncoder: Encoder[Vector[Vector[String]]] =
    (data: CellValueGrid) => Try(data.asScala.deepMap(_.toString))

  implicit final val vectorIntEncoder: Encoder[Vector[Vector[Int]]] =
    (data: CellValueGrid) => Try(data.asScala.deepMap(_.toInt.get))

  implicit final val vectorTryIntEncoder: Encoder[Vector[Vector[Try[Int]]]] =
    (data: CellValueGrid) => Try(data.asScala.deepMap(_.toInt))

}
