package gsheets.cells

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*

type GSheetGrid = JsGrid[GSheetCellValue]

object GSheetGrid:
  def one(cv: GSheetCellValue): GSheetGrid = js.Array(js.Array(cv))

  def apply(cells: ScalaCellGrid): GSheetGrid =
    cells.map(_.map(_.value).toJSArray).toJSArray

end GSheetGrid
