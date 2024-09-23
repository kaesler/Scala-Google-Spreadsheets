package gsheets.customfunctionsimpl

import gsheets.cells.GSheetGrid
import gsheets.customfunctions.Decoder
import scala.scalajs.js
import scala.util.{Failure, Success, Try}

final class CustomFunction0[+U](f: () => U)(
  using decoder: Decoder[U]
) extends (() => GSheetGrid) {

  def apply(): GSheetGrid = {
    Try(decoder(f())) match {
      case Success(value) => value
      case Failure(e)     => js.Array(js.Array(e.getMessage))
    }
  }

}

object CustomFunction0 {

  extension[U: Decoder] (f: () => U)
    def asCustomFunction: CustomFunction0[U] = CustomFunction0(f)
}
