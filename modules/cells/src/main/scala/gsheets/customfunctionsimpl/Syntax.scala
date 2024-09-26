package gsheets.customfunctionsimpl

import gsheets.customfunctions.{Decoder, Encoder}

object Syntax:
  extension [U: Decoder](f: () => U)
    def asCustomFunction: CustomFunction0[U] = CustomFunction0(f)

  extension [T: Encoder, U: Decoder](f: T => U)
    def asCustomFunction: CustomFunction1[T, U] = CustomFunction1(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    U: Decoder
  ](f: (T1, T2) => U)
    def asCustomFunction: CustomFunction2[T1, T2, U] = CustomFunction2(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    U: Decoder
  ](f: (T1, T2, T3) => U)
    def asCustomFunction: CustomFunction3[T1, T2, T3, U] = CustomFunction3(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    U: Decoder
  ](f: (T1, T2, T3, T4) => U)
    def asCustomFunction: CustomFunction4[T1, T2, T3, T4, U] = CustomFunction4(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    U: Decoder
  ](f: (T1, T2, T3, T4, T5) => U)
    def asCustomFunction: CustomFunction5[T1, T2, T3, T4, T5, U] = CustomFunction5(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    U: Decoder
  ](f: (T1, T2, T3, T4, T5, T6) => U)
    def asCustomFunction: CustomFunction6[T1, T2, T3, T4, T5, T6, U] = CustomFunction6(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    U: Decoder
  ](f: (T1, T2, T3, T4, T5, T6, T7) => U)
    def asCustomFunction: CustomFunction7[T1, T2, T3, T4, T5, T6, T7, U] =
      CustomFunction7(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    T8: Encoder,
    U: Decoder
  ](f: (T1, T2, T3, T4, T5, T6, T7, T8) => U)
    def asCustomFunction: CustomFunction8[T1, T2, T3, T4, T5, T6, T7, T8, U] =
      CustomFunction8(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    T8: Encoder,
    T9: Encoder,
    U: Decoder
  ](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => U)
    def asCustomFunction: CustomFunction9[T1, T2, T3, T4, T5, T6, T7, T8, T9, U] =
      CustomFunction9(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    T8: Encoder,
    T9: Encoder,
    T10: Encoder,
    U: Decoder
  ](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => U)
    def asCustomFunction: CustomFunction10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, U] =
      CustomFunction10(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    T8: Encoder,
    T9: Encoder,
    T10: Encoder,
    T11: Encoder,
    U: Decoder
  ](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => U)
    def asCustomFunction
      : CustomFunction11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, U] =
      CustomFunction11(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    T8: Encoder,
    T9: Encoder,
    T10: Encoder,
    T11: Encoder,
    T12: Encoder,
    U: Decoder
  ](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => U)
    def asCustomFunction
      : CustomFunction12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, U] =
      CustomFunction12(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    T8: Encoder,
    T9: Encoder,
    T10: Encoder,
    T11: Encoder,
    T12: Encoder,
    T13: Encoder,
    U: Decoder
  ](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => U)
    def asCustomFunction
      : CustomFunction13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, U] =
      CustomFunction13(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    T8: Encoder,
    T9: Encoder,
    T10: Encoder,
    T11: Encoder,
    T12: Encoder,
    T13: Encoder,
    T14: Encoder,
    U: Decoder
  ](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => U)
    def asCustomFunction
      : CustomFunction14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, U] =
      CustomFunction14(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    T8: Encoder,
    T9: Encoder,
    T10: Encoder,
    T11: Encoder,
    T12: Encoder,
    T13: Encoder,
    T14: Encoder,
    T15: Encoder,
    U: Decoder
  ](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => U)
    def asCustomFunction: CustomFunction15[
      T1,
      T2,
      T3,
      T4,
      T5,
      T6,
      T7,
      T8,
      T9,
      T10,
      T11,
      T12,
      T13,
      T14,
      T15,
      U
    ] = CustomFunction15(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    T8: Encoder,
    T9: Encoder,
    T10: Encoder,
    T11: Encoder,
    T12: Encoder,
    T13: Encoder,
    T14: Encoder,
    T15: Encoder,
    T16: Encoder,
    U: Decoder
  ](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => U)
    def asCustomFunction: CustomFunction16[
      T1,
      T2,
      T3,
      T4,
      T5,
      T6,
      T7,
      T8,
      T9,
      T10,
      T11,
      T12,
      T13,
      T14,
      T15,
      T16,
      U
    ] = CustomFunction16(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    T8: Encoder,
    T9: Encoder,
    T10: Encoder,
    T11: Encoder,
    T12: Encoder,
    T13: Encoder,
    T14: Encoder,
    T15: Encoder,
    T16: Encoder,
    T17: Encoder,
    U: Decoder
  ](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => U)
    def asCustomFunction: CustomFunction17[
      T1,
      T2,
      T3,
      T4,
      T5,
      T6,
      T7,
      T8,
      T9,
      T10,
      T11,
      T12,
      T13,
      T14,
      T15,
      T16,
      T17,
      U
    ] = CustomFunction17(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    T8: Encoder,
    T9: Encoder,
    T10: Encoder,
    T11: Encoder,
    T12: Encoder,
    T13: Encoder,
    T14: Encoder,
    T15: Encoder,
    T16: Encoder,
    T17: Encoder,
    T18: Encoder,
    U: Decoder
  ](
    f: (
      T1,
      T2,
      T3,
      T4,
      T5,
      T6,
      T7,
      T8,
      T9,
      T10,
      T11,
      T12,
      T13,
      T14,
      T15,
      T16,
      T17,
      T18
    ) => U
  )
    def asCustomFunction: CustomFunction18[
      T1,
      T2,
      T3,
      T4,
      T5,
      T6,
      T7,
      T8,
      T9,
      T10,
      T11,
      T12,
      T13,
      T14,
      T15,
      T16,
      T17,
      T18,
      U
    ] = CustomFunction18(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    T8: Encoder,
    T9: Encoder,
    T10: Encoder,
    T11: Encoder,
    T12: Encoder,
    T13: Encoder,
    T14: Encoder,
    T15: Encoder,
    T16: Encoder,
    T17: Encoder,
    T18: Encoder,
    T19: Encoder,
    U: Decoder
  ](
    f: (
      T1,
      T2,
      T3,
      T4,
      T5,
      T6,
      T7,
      T8,
      T9,
      T10,
      T11,
      T12,
      T13,
      T14,
      T15,
      T16,
      T17,
      T18,
      T19
    ) => U
  )
    def asCustomFunction: CustomFunction19[
      T1,
      T2,
      T3,
      T4,
      T5,
      T6,
      T7,
      T8,
      T9,
      T10,
      T11,
      T12,
      T13,
      T14,
      T15,
      T16,
      T17,
      T18,
      T19,
      U
    ] = CustomFunction19(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    T8: Encoder,
    T9: Encoder,
    T10: Encoder,
    T11: Encoder,
    T12: Encoder,
    T13: Encoder,
    T14: Encoder,
    T15: Encoder,
    T16: Encoder,
    T17: Encoder,
    T18: Encoder,
    T19: Encoder,
    T20: Encoder,
    U: Decoder
  ](
    f: (
      T1,
      T2,
      T3,
      T4,
      T5,
      T6,
      T7,
      T8,
      T9,
      T10,
      T11,
      T12,
      T13,
      T14,
      T15,
      T16,
      T17,
      T18,
      T19,
      T20
    ) => U
  )
    def asCustomFunction: CustomFunction20[
      T1,
      T2,
      T3,
      T4,
      T5,
      T6,
      T7,
      T8,
      T9,
      T10,
      T11,
      T12,
      T13,
      T14,
      T15,
      T16,
      T17,
      T18,
      T19,
      T20,
      U
    ] = CustomFunction20(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    T8: Encoder,
    T9: Encoder,
    T10: Encoder,
    T11: Encoder,
    T12: Encoder,
    T13: Encoder,
    T14: Encoder,
    T15: Encoder,
    T16: Encoder,
    T17: Encoder,
    T18: Encoder,
    T19: Encoder,
    T20: Encoder,
    T21: Encoder,
    U: Decoder
  ](
    f: (
      T1,
      T2,
      T3,
      T4,
      T5,
      T6,
      T7,
      T8,
      T9,
      T10,
      T11,
      T12,
      T13,
      T14,
      T15,
      T16,
      T17,
      T18,
      T19,
      T20,
      T21
    ) => U
  )
    def asCustomFunction: CustomFunction21[
      T1,
      T2,
      T3,
      T4,
      T5,
      T6,
      T7,
      T8,
      T9,
      T10,
      T11,
      T12,
      T13,
      T14,
      T15,
      T16,
      T17,
      T18,
      T19,
      T20,
      T21,
      U
    ] = CustomFunction21(f)

  extension [
    T1: Encoder,
    T2: Encoder,
    T3: Encoder,
    T4: Encoder,
    T5: Encoder,
    T6: Encoder,
    T7: Encoder,
    T8: Encoder,
    T9: Encoder,
    T10: Encoder,
    T11: Encoder,
    T12: Encoder,
    T13: Encoder,
    T14: Encoder,
    T15: Encoder,
    T16: Encoder,
    T17: Encoder,
    T18: Encoder,
    T19: Encoder,
    T20: Encoder,
    T21: Encoder,
    T22: Encoder,
    U: Decoder
  ](
    f: (
      T1,
      T2,
      T3,
      T4,
      T5,
      T6,
      T7,
      T8,
      T9,
      T10,
      T11,
      T12,
      T13,
      T14,
      T15,
      T16,
      T17,
      T18,
      T19,
      T20,
      T21,
      T22
    ) => U
  )
    def asCustomFunction: CustomFunction22[
      T1,
      T2,
      T3,
      T4,
      T5,
      T6,
      T7,
      T8,
      T9,
      T10,
      T11,
      T12,
      T13,
      T14,
      T15,
      T16,
      T17,
      T18,
      T19,
      T20,
      T21,
      T22,
      U
    ] = CustomFunction22(f)

//  (1 to 22).foreach: argCount =>
//    println(genExtension(argCount))
//  def genExtension(argCount: Int): String = {
//    val sb = StringBuilder()
//    sb.append("  extension [\n")
//    (1 to argCount).foreach: i =>
//      sb.append(s"    T$i: Encoder,\n")
//    sb.append(s"    U: Decoder\n")
//    // ](f: (T1, T2, T3, T4) => U)
//    sb.append("  ](f: (")
//    (1 to argCount).foreach: i =>
//      if i < argCount then sb.append(s"T$i, ")
//      else sb.append(s"T$i")
//    sb.append(") => U)\n")
//    //    def asCustomFunction: CustomFunction4[T1, T2, T3, T4, U] = CustomFunction4(f)
//    sb.append(s"    def asCustomFunction: CustomFunction$argCount[")
//    (1 to argCount).foreach: i =>
//      if i < argCount then sb.append(s"T$i, ")
//      else sb.append(s"T$i")
//    sb.append(s", U] = CustomFunction$argCount(f)\n\n")
//    sb.mkString
//  }

end Syntax
