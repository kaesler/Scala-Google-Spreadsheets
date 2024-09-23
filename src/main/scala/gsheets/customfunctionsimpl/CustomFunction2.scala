package gsheets.customfunctionsimpl

import gsheets.cells.GSheetGrid
import gsheets.customfunctions.{Decoder, Encoder, Input}
import scala.scalajs.js
import scala.util.{Failure, Success}

/** A [[CustomFunction2]] represents a Google custom function taking two inputs and
  * returning an [[GSheetGrid]].
  *
  * @example
  *   def foo(cells: ScalaGrid[String], bar: String): Int = ???
  *
  * \@JSExportTopLevel("FOO") def jsFoo(cells: Input, bar: Int): CellValueGrid = (foo
  * _).toGoogleFunction(cells, bar)
  * @param f
  *   function to apply to the transformed arguments
  * @param encoder1
  *   encoder to go from Input type to type T1
  * @param encoder2
  *   encoder to go from Input type to type T2
  * @param decoder
  *   decoder to go from type U to CellValueGrid type
  * @tparam T1
  *   first argument type
  * @tparam T2
  *   second argument type
  * @tparam U
  *   return type
  */
final class CustomFunction2[-T1, -T2, +U](f: (T1, T2) => U)(
  using encoder1: Encoder[T1],
  encoder2: Encoder[T2],
  decoder: Decoder[U]
) extends ((Input, Input) => GSheetGrid):

  def apply(input1: Input, input2: Input): GSheetGrid =
    (
      for
        arg1 <- encoder1(input1)
        arg2 <- encoder2(input2)
        output = f(arg1, arg2)
      yield decoder(output)
    ) match
      case Success(value)     => value
      case Failure(exception) => js.Array(js.Array(exception.getMessage))
