package gsheets.customfunctionsimpl

import gsheets.cells.GSheetGrid
import gsheets.customfunctions.{Decoder, Encoder, Input}
import scala.scalajs.js
import scala.util.{Failure, Success}

final class CustomFunction1[-T, +U](f: T => U)(
  using encoder: Encoder[T],
  decoder: Decoder[U]
) extends (Input => GSheetGrid):

  def apply(input: Input): GSheetGrid =
    (for (arg <- encoder(input)) yield decoder(f(arg))) match
      case Success(value) => value
      case Failure(e)     => GSheetGrid.one(e.getMessage)
