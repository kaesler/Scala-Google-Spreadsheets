package gsheets.tutorial

import gsheets.cells.GridExtensions.*
import gsheets.cells.{Cell, GSheetGrid, ScalaGrid, WrongDataTypeException}
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

/** This object contains examples of functions that are exported to Google custom
  * functions.
  */
object ExportedFunctions:

  /** Returns the average of the cells. Empty cells are treated as 0. Non double cells are
    * removed from collection.
    *
    * @param xs
    *   {number} the cells to take the mean of
    * @return
    *   {number} the mean
    */
  @JSExportTopLevel("mean")
  def mean(xs: GSheetGrid): Double =
    val flat = xs.flatten
      .map: (m: Matchable) =>
        m match
          case s: String if s == "" => 0.0
          case elem                 => elem
      .filter((_: Any).isInstanceOf[Double])
      .map(_.asInstanceOf[Double])
    flat.sum / flat.length

  @JSExportTopLevel("mean")
  def mean(x: Double): Double = x

  /** Returns the number of seconds in the form mm:ss.
    *
    * @param seconds
    *   {number} the number of seconds
    */
  @JSExportTopLevel("seconds2MinuteSeconds")
  def seconds2MinuteSeconds(seconds: Double): String =
    val roundedSeconds = math.round(seconds)
    val s              = roundedSeconds % 60
    s"${roundedSeconds / 60}:${if (s < 10) s"0$s" else s}"

  /** An example of how to implement the sum function in Scala.
    *
    * We consider only elements that are Double by filtering on the isNumeric method.
    */
  @JSExportTopLevel("customSum")
  def sum(elems: GSheetGrid): Double =
    elems.asScala.flatten
      .filter(_.isNumeric)
      .map(_.toDouble.get)
      .sum

  @JSExportTopLevel("getTypes")
  def types(elems: GSheetGrid): GSheetGrid =
    elems.asScala.map(_.map(v => Cell(v.isEmpty))).asGSheet

  /** Below is an example of actually using Scala code for treating data in the
    * spreadsheet.
    *
    * Remember that computations are made on the server side, and calling lots of custom
    * functions can be slow. The best way around this is to create functions that take a
    * full table of data, treat them, and return a table with the results.
    *
    * In this example, we'll assume that we have a table with people information, where
    * each row contains the information relative to one person, and each row contain the
    * following information, in that order:
    *   - first name (String)
    *   - last name (String)
    *   - date of birth (js.Date)
    *   - sex (String, M for male and F for female)
    *   - monthly income (say, in euros) (Double)
    *
    * In Scala, we'll represent the data using case classes.
    */

  private sealed trait Sex
  private case object Male   extends Sex
  private case object Female extends Sex

  private case class Person(
    firstName: String,
    lastName: String,
    dateOfBirth: js.Date,
    sex: Sex,
    income: Double
  ):

    def age: Int =
      val now = new js.Date(js.Date.now())

      val hadBirthday: Boolean = now.getMonth > dateOfBirth.getMonth ||
        (now.getMonth == dateOfBirth.getMonth && now.getDay >= dateOfBirth.getDay)

      now.getFullYear().toInt - dateOfBirth.getFullYear().toInt + (if (hadBirthday) 0
                                                                   else -1)

    def toRow: Vector[Cell] = Vector(
      Cell(firstName),
      Cell(lastName),
      Cell(dateOfBirth),
      Cell(if (sex == Male) "M" else "F"),
      Cell(income)
    )

  private def rowToPerson(row: Vector[Cell]): Person = Person(
    row(0).value.asInstanceOf[String],
    row(1).value.asInstanceOf[String],
    row(2).toDate.get,
    if (row(3).value.asInstanceOf[String] == "M") Male else Female,
    row(4).toDouble.get
  )

  /** Returns a table containing only the adults in the data. An adult is a person whose
    * age is at least 18.
    */
  @JSExportTopLevel("getAdults")
  def adults(data: GSheetGrid): GSheetGrid =
    data.asScala
      .map(rowToPerson)
      .filter(_.age >= 18)
      .map(_.toRow)
      .asGSheet

  /** Returns a table containing the adults whose income is above the average of income.
    * The last row also contains the average income, for reference.
    */
  @JSExportTopLevel("aboveIncomeAverage")
  def aboveIncomeAverage(data: GSheetGrid): GSheetGrid =
    val persons = data.asScala.map(rowToPerson)
    val adults  = persons.filter(_.age >= 18)

    val incomeAverage = adults.map(_.income).sum / adults.length

    val adultsAboveAverage = adults.filter(_.income >= incomeAverage)

    (adultsAboveAverage.map(_.toRow) :+ Vector(Cell(incomeAverage))).asGSheet

  private def scalarProd(x: Vector[Double], y: Vector[Double]): Double =
    x.zip(y).map(elem => elem._1 * elem._2).sum

  private def cost(
    theta: Vector[Double],
    zippedTraining: Vector[(Vector[Double], Double)]
  ): Double =
    zippedTraining
      .map({ case (x, y) => math.pow(scalarProd(theta, x) - y, 2) })
      .sum / zippedTraining.length

  private def linearRegression(
    trainingDataX: ScalaGrid[Double],
    trainingDataY: Vector[Double]
  ): Vector[Double] =
    def trainingSetSize: Int   = trainingDataY.length
    def trainingDimension: Int = trainingDataX.head.length

    def zippedTraining = trainingDataX.map(1.0 +: _).zip(trainingDataY)

    def grad(theta: Vector[Double], alpha: Double): Vector[Double] =
      (0 to trainingDimension)
        .map(j =>
          zippedTraining
            .map({ case (x, y) =>
              (scalarProd(theta, x) - y) * x(j)
            })
            .sum / trainingSetSize * alpha
        )
        .toVector

    val tol: Double       = 0.000001
    val maxIteration: Int = 200

    @scala.annotation.tailrec
    def makeIterations(
      prevTheta: Vector[Double],
      prevCost: Double,
      alpha: Double,
      iteration: Int
    ): Vector[Double] =
      if (iteration > maxIteration) prevTheta
      else {
        val theta   = prevTheta.zip(grad(prevTheta, alpha)).map(elem => elem._1 - elem._2)
        val newCost = cost(theta, zippedTraining)

        if (newCost > prevCost)
          makeIterations(prevTheta, prevCost, alpha / 2, iteration + 1)
        else if (prevCost - newCost < tol) theta
        else makeIterations(theta, newCost, alpha, iteration + 1)
      }

    val startingTheta = Vector[Double]().padTo(trainingDimension + 1, 0.0)

    makeIterations(startingTheta, cost(startingTheta, zippedTraining), 0.1, 0)

  /** Computes the hypothesis function corresponding to the data. Similar to LINEST built
    * in function, but works on multi-dimensional data. Quite slow though...
    *
    * Each row of the X matrix must be one data. Each row of the Y matrix must be an array
    * of one element corresponding to the X data.
    */
  @JSExportTopLevel("linearRegression")
  def linearRegression(
    trainingDataX: GSheetGrid,
    trainingDataY: GSheetGrid
  ): GSheetGrid =
    try
      val startTime = js.Date.now
      val result = Vector(
        linearRegression(
          trainingDataX.asScala.map(_.map(_.toDouble.get)),
          trainingDataY.asScala.map(_.head.toDouble.get)
        ).map(new Cell(_))
      )

      (result :+ Vector(Cell("Time taken"), Cell(js.Date.now - startTime))).asGSheet
    catch
      case e: WrongDataTypeException => GSheetGrid.one(e.msg)
      case e: Throwable              => throw e

  /** Returns the predicted income at a given age, given the income of the people in the
    * data set.
    *
    * This function performs a linear regression on the data set, and return the predicted
    * value from that linear regression. This is probably a bad model, since there is no
    * reason why income should be proportional to the age, and not quadratic of
    * sub-linear.
    */
  @JSExportTopLevel("predictIncomeAtAge")
  def predictIncomeAtAge(data: GSheetGrid, age: Int): Double =
    val persons = data.asScala.map(rowToPerson)
    val adults  = persons.filter(_.age >= 18)

    val theta =
      linearRegression(adults.map(p => Vector(p.age.toDouble)), adults.map(_.income))

    theta(0) + theta(1) * age
