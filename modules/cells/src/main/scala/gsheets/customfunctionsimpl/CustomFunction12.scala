package gsheets.customfunctionsimpl

import gsheets.cells.GSheetGrid
import gsheets.customfunctions.{Decoder, Encoder, Input}

/** A [[CustomFunction12]] represents a Google custom function taking 12 inputs and
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
  * @param encoder6
  *   encoder to go from Input type to type T6
  * @param encoder7
  *   encoder to go from Input type to type T7
  * @param encoder8
  *   encoder to go from Input type to type T8
  * @param encoder9
  *   encoder to go from Input type to type T9
  * @param encoder10
  *   encoder to go from Input type to type T10
  * @param encoder11
  *   encoder to go from Input type to type T11
  * @param encoder12
  *   encoder to go from Input type to type T12
  * @param decoder
  *   decoder to go from type U to CellValueGrid type
  * @tparam U
  *   return type
  */
final class CustomFunction12[
  -T1,
  -T2,
  -T3,
  -T4,
  -T5,
  -T6,
  -T7,
  -T8,
  -T9,
  -T10,
  -T11,
  -T12,
  +U
](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => U)(
  using encoder1: Encoder[T1],
  encoder2: Encoder[T2],
  encoder3: Encoder[T3],
  encoder4: Encoder[T4],
  encoder5: Encoder[T5],
  encoder6: Encoder[T6],
  encoder7: Encoder[T7],
  encoder8: Encoder[T8],
  encoder9: Encoder[T9],
  encoder10: Encoder[T10],
  encoder11: Encoder[T11],
  encoder12: Encoder[T12],
  decoder: Decoder[U]
) extends (
      (
        Input,
        Input,
        Input,
        Input,
        Input,
        Input,
        Input,
        Input,
        Input,
        Input,
        Input,
        Input
      ) => GSheetGrid
    ):

  def apply(
    input1: Input,
    input2: Input,
    input3: Input,
    input4: Input,
    input5: Input,
    input6: Input,
    input7: Input,
    input8: Input,
    input9: Input,
    input10: Input,
    input11: Input,
    input12: Input
  ): GSheetGrid =
    (
      for
        arg1  <- encoder1(input1)
        arg2  <- encoder2(input2)
        arg3  <- encoder3(input3)
        arg4  <- encoder4(input4)
        arg5  <- encoder5(input5)
        arg6  <- encoder6(input6)
        arg7  <- encoder7(input7)
        arg8  <- encoder8(input8)
        arg9  <- encoder9(input9)
        arg10 <- encoder10(input10)
        arg11 <- encoder11(input11)
        arg12 <- encoder12(input12)
        output = f(
          arg1,
          arg2,
          arg3,
          arg4,
          arg5,
          arg6,
          arg7,
          arg8,
          arg9,
          arg10,
          arg11,
          arg12
        )
      yield decoder(output)
    ).recoverFailure
