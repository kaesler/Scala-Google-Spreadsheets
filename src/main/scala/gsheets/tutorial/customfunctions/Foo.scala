package gsheets.tutorial.customfunctions

import gsheets.cells.Cell.GridToVectors
import gsheets.cells.GSheetGrid
import gsheets.customfunctions.Encoder
import scala.util.Try

final case class Foo(bar: String, babar: Int)

object Foo {
  implicit val fooEncoder: Encoder[Vector[Foo]] =
    (data: GSheetGrid) =>
      Try(
        data.asScala.filterNot(_(0).isEmpty).map(v => Foo(v(0).toString, v(1).toInt.get))
      )

}
