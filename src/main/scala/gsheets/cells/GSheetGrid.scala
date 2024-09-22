package gsheets.cells

import scala.scalajs.js

type GSheetGrid = JsGrid[GSheetCellValue]

object GSheetGrid:
  def one(cv: GSheetCellValue): GSheetGrid = js.Array(js.Array(cv))

end GSheetGrid
