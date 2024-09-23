package gsheets.tutorial.customfunctions

import gsheets.cells.{Cell, GSheetGrid, ScalaCellGrid, ScalaGrid}
import gsheets.customfunctions.{Decoder, Input}
import gsheets.customfunctionsimpl.Syntax.*
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.util.Try

object CustomFunctionsAbstractionExamples:

  /** Counts the number of [[Foo]] for which `babar` is bigger than 10. */
  private def countBigFoo(foos: Vector[Foo]): Int = foos.count(_.babar > 10)

  /** Export the custom function for google sheets. The encoder for vectors of [[Foo]] is
    * defined above, and the decoder for Int is defined in the companion object of the
    * [[Decoder]] trait.
    */
  @JSExportTopLevel("COUNTBIGFOO")
  def jsCountBigFoo(input: Input): GSheetGrid = countBigFoo.asCustomFunction(input)

  private def sumByCategory(
    categories: ScalaGrid[String],
    values: ScalaGrid[Try[Int]]
  ): ScalaCellGrid =
    categories.flatten
      .zip(values.flatten)
      .filterNot(_._1.isEmpty)
      .map { case (cat, maybeValue) => cat -> maybeValue.get }
      .groupMapReduce(_._1)(_._2)(_ + _)
      .toVector
      .map { case (cat, value) => Vector(Cell(cat), Cell(value)) }

  @JSExportTopLevel("SUMBYCATEGORY")
  def jsSumByCategories(categories: Input, values: Input): GSheetGrid =
    sumByCategory.asCustomFunction(categories, values)

  /** Exception example. */
  def throwException(input: ScalaGrid[String]): Int =
    throw new Exception("I failed!")

  /** Throw an exception to see how it is handled by the spreadsheet.
    *
    * The output of the function is simply the message of the exception.
    */
  @JSExportTopLevel("THROWEXCEPTION")
  def jsThrowException(input: Input): GSheetGrid =
    throwException.asCustomFunction(input)
