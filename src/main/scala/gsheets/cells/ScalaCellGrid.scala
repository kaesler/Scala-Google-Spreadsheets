package gsheets.cells

type ScalaCellGrid = ScalaGrid[Cell]

object ScalaCellGrid:

  def apply(grid: GSheetGrid): ScalaCellGrid =
    grid
      .map(_.toVector.map(Cell.apply))
      .toVector

end ScalaCellGrid
