package gsheets.cells

import scala.scalajs.js

type JsGrid[T] = js.Array[js.Array[T]]

object JsGrid:
  def one[T](t: T): JsGrid[T] = js.Array(js.Array(t))
