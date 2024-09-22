package gsheets.customfunctions

import gsheets.cells.Cell.VectorsToGrid
import gsheets.cells.{Cell, CellValue, CellValueGrid}
import scala.scalajs.js

/** A [[Decoder]] takes the result of a custom function, of type U, and decodes it as an
  * [[CellValueGrid]] in order to be sent back to the Google sheets.
  *
  * @tparam U
  *   type to decode as an [[CellValueGrid]]
  */
trait Decoder[-U] {

  def decodeU(u: U): CellValueGrid

  final def apply(u: U): CellValueGrid = decodeU(u)
}

object Decoder {

  implicit final val identityDecoder: Decoder[CellValueGrid] =
    (u: CellValueGrid) => u

  implicit final val cellDecoder: Decoder[Vector[Vector[Cell]]] =
    (u: Vector[Vector[Cell]]) => u.toGrid

  implicit final val stringDecoder: Decoder[String] = (u: String) => CellValueGrid.one(u)
  implicit final val doubleDecoder: Decoder[Double] = (u: Double) => CellValueGrid.one(u)
  implicit final val intDecoder: Decoder[Int]       = (u: Int) => CellValueGrid.one(u)
  implicit final val dateDecoder: Decoder[js.Date]  = (u: js.Date) => CellValueGrid.one(u)
  implicit final val booleanDecoder: Decoder[Boolean] = (u: Boolean) =>
    js.Array(js.Array(u))

}
