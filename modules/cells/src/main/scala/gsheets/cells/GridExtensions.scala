package gsheets.cells

import scala.scalajs.js.JSConverters.*

object GridExtensions:

  extension (grid: GSheetGrid)
    def asScala: ScalaCellGrid =
      grid
        .map(_.toVector.map(Cell.apply))
        .toVector

  extension (grid: ScalaCellGrid)
    def asGSheet: GSheetGrid =
      grid.map(_.map(_.value).toJSArray).toJSArray

    def deepMap[U](f: Cell => U): ScalaGrid[U] =
      grid.map(_.map(f))

end GridExtensions
