package gsheets.customfunctionsimpl

import gsheets.cells.GSheetGrid
import gsheets.customfunctions.Decoder
import scala.scalajs.js
import scala.util.{Failure, Success, Try}

final class CustomFunction0[+U](f: () => U)(
  using decoder: Decoder[U]
) extends (() => GSheetGrid) {

  def apply(): GSheetGrid = {
    Try(decoder(f()))
      .fold(
        ex => GSheetGrid.one(ex.getMessage),
        identity
      )
  }
}
