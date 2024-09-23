package gsheets.customfunctions

import gsheets.cells.GridExtensions.*
import gsheets.cells.{Cell, GSheetCellValue, GSheetGrid, ScalaCellGrid}
import scala.scalajs.js

/** A [[Decoder]] takes the result of a custom function, of type U, and decodes it as an
  * [[GSheetGrid]] in order to be sent back to the Google sheets.
  *
  * @tparam U
  *   type to decode as an [[GSheetGrid]]
  */
trait Decoder[-U]:

  def decodeU(u: U): GSheetGrid

  final def apply(u: U): GSheetGrid = decodeU(u)

object Decoder:

  given Decoder[GSheetGrid]    = (u: GSheetGrid) => u
  given Decoder[ScalaCellGrid] = (u: ScalaCellGrid) => u.asGSheet
  given Decoder[String]        = (u: String) => GSheetGrid.one(u)
  given Decoder[Double]        = (u: Double) => GSheetGrid.one(u)
  given Decoder[Int]           = (u: Int) => GSheetGrid.one(u)
  given Decoder[js.Date]       = (u: js.Date) => GSheetGrid.one(u)
  given Decoder[Boolean]       = (u: Boolean) => GSheetGrid.one(u)
