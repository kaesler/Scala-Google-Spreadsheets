package gsheets.cells

import scala.scalajs.js

type CellValueGrid = Grid[CellValue]

object CellValueGrid:
  def one(cv: CellValue): CellValueGrid = js.Array(js.Array(cv))

end CellValueGrid
