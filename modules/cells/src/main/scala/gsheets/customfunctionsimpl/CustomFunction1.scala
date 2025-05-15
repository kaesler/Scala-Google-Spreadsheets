package gsheets.customfunctionsimpl

import gsheets.cells.GSheetGrid
import gsheets.customfunctions.{Decoder, Encoder, Input}

final class CustomFunction1[-T, +U](f: T => U)(
  using encoder: Encoder[T],
  decoder: Decoder[U]
) extends (Input => GSheetGrid):

  def apply(input: Input): GSheetGrid =
    encoder(input)
      .map(f andThen decoder.apply)
      .recoverFailure
