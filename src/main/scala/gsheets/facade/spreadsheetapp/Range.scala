package gsheets.facade.spreadsheetapp

import gsheets.cells.{CellValue, CellValueGrid}
import scala.scalajs.js

/** https://developers.google.com/apps-script/reference/spreadsheet/range
  */
@js.native
trait Range extends js.Object {

  def getCell(row: Int, column: Int): Range = js.native

  def getValue(): CellValue = js.native

  def getValues(): CellValueGrid = js.native

  def setValues(values: CellValueGrid): Unit = js.native

}
