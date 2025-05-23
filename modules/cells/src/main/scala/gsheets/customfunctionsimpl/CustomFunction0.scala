package gsheets.customfunctionsimpl

import gsheets.cells.GSheetGrid
import gsheets.customfunctions.Decoder
import scala.util.Try

final class CustomFunction0[+U](f: () => U)(
  using decoder: Decoder[U]
) extends (() => GSheetGrid):

  def apply(): GSheetGrid = Try(decoder(f())).recoverFailure
