package gsheets.cells

import scala.scalajs.js

type Grid[T] = js.Array[js.Array[T]]

object Grid:
  def one[T](t: T): Grid[T] = js.Array(js.Array(t))
