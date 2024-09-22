package gsheets.customfunctions

import gsheets.cells.Cell.VectorsToGrid
import gsheets.cells.{Cell, GSheetCellValue, GSheetGrid, ScalaCellGrid}
import scala.scalajs.js

/** A [[Decoder]] takes the result of a custom function, of type U, and decodes it as an
  * [[GSheetGrid]] in order to be sent back to the Google sheets.
  *
  * @tparam U
  *   type to decode as an [[GSheetGrid]]
  */
trait Decoder[-U] {

  def decodeU(u: U): GSheetGrid

  final def apply(u: U): GSheetGrid = decodeU(u)
}

object Decoder {

  implicit final val identityDecoder: Decoder[GSheetGrid] =
    (u: GSheetGrid) => u

  implicit final val cellDecoder: Decoder[ScalaCellGrid] =
    (u: ScalaCellGrid) => u.toGrid

  implicit final val stringDecoder: Decoder[String]   = (u: String) => GSheetGrid.one(u)
  implicit final val doubleDecoder: Decoder[Double]   = (u: Double) => GSheetGrid.one(u)
  implicit final val intDecoder: Decoder[Int]         = (u: Int) => GSheetGrid.one(u)
  implicit final val dateDecoder: Decoder[js.Date]    = (u: js.Date) => GSheetGrid.one(u)
  implicit final val booleanDecoder: Decoder[Boolean] = (u: Boolean) => GSheetGrid.one(u)

}
