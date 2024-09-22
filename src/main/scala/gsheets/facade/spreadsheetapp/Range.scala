package gsheets.facade.spreadsheetapp

import gsheets.cells.Cell.CellValue
import scala.scalajs.js

/** https://developers.google.com/apps-script/reference/spreadsheet/range
  */
@js.native
trait Range extends js.Object {

  def getCell(row: Int, column: Int): Range = js.native

  def getValue(): CellValue = js.native

  def getValues(): js.Array[js.Array[CellValue]] = js.native

  def setValues(values: js.Array[js.Array[CellValue]]): Unit = js.native

}
