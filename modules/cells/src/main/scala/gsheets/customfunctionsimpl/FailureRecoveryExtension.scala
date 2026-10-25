package gsheets.customfunctionsimpl

import gsheets.cells.GSheetGrid
import scala.util.Try

extension (attempt: Try[GSheetGrid])
  def recoverFailure: GSheetGrid =
    attempt.fold(
      ex => throw ex,
      identity
    )
