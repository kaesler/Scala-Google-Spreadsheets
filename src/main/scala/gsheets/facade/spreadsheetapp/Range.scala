package gsheets.facade.spreadsheetapp

import gsheets.cells.{GSheetCellValue, GSheetGrid}
import scala.scalajs.js

/** https://developers.google.com/apps-script/reference/spreadsheet/range
  */
@js.native
trait Range extends js.Object {

  def getCell(row: Int, column: Int): Range = js.native

  def getValue(): GSheetCellValue = js.native

  def getValues(): GSheetGrid = js.native

  def setValues(values: GSheetGrid): Unit = js.native

}
