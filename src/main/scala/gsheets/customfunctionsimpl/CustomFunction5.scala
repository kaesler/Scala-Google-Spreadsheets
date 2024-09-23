package gsheets.customfunctionsimpl

import gsheets.cells.GSheetGrid
import gsheets.customfunctions.{Decoder, Encoder, Input}
import scala.scalajs.js
import scala.util.{Failure, Success}

/** A [[CustomFunction5]] represents a Google custom function taking 5 inputs and
  * returning an [[GSheetGrid]].
  *
  * @param f
  *   function to apply to the transformed arguments
  * @param encoder1
  *   encoder to go from Input type to type T1
  * @param encoder2
  *   encoder to go from Input type to type T2
  * @param encoder3
  *   encoder to go from Input type to type T3
  * @param encoder4
  *   encoder to go from Input type to type T4
  * @param encoder5
  *   encoder to go from Input type to type T5
  * @param decoder
  *   decoder to go from type U to CellValueGrid type
  * @tparam U
  *   return type
  */
final class CustomFunction5[-T1, -T2, -T3, -T4, -T5, +U](f: (T1, T2, T3, T4, T5) => U)(
  using encoder1: Encoder[T1],
  encoder2: Encoder[T2],
  encoder3: Encoder[T3],
  encoder4: Encoder[T4],
  encoder5: Encoder[T5],
  decoder: Decoder[U]
) extends ((Input, Input, Input, Input, Input) => GSheetGrid) {

  def apply(
    input1: Input,
    input2: Input,
    input3: Input,
    input4: Input,
    input5: Input
  ): GSheetGrid = {
    (
      for {
        arg1 <- encoder1(input1)
        arg2 <- encoder2(input2)
        arg3 <- encoder3(input3)
        arg4 <- encoder4(input4)
        arg5 <- encoder5(input5)
        output = f(arg1, arg2, arg3, arg4, arg5)
      } yield decoder(output)
    ) match {
      case Success(value)     => value
      case Failure(exception) => js.Array(js.Array(exception.getMessage))
    }
  }

}

object CustomFunction5 {

  implicit final class FromFunction5[-T1, -T2, -T3, -T4, -T5, +U](
    f: (T1, T2, T3, T4, T5) => U
  )(
    using Encoder[T1],
    Encoder[T2],
    Encoder[T3],
    Encoder[T4],
    Encoder[T5],
    Decoder[U]
  ) {
    def asCustomFunction: CustomFunction5[T1, T2, T3, T4, T5, U] = CustomFunction5(f)
  }

}
