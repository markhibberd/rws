package cheat.writer

import scalaz._, Scalaz._

case class Writer[W, A](log: W, value: A) {
  def map[B](f: A => B): Writer[W, B] =
    Writer(log, f(value))

  def flatMap[B](f: A => Writer[W, B])(implicit M: Monoid[W]): Writer[W, B] =
    f(value) match {
      case Writer(more, b) => Writer(log |+| more, b)
    }
}

object Writer {
  def value[W, A](a: A)(implicit M: Monoid[W]) =
    Writer(M.zero, a)

  def tell[W](w: W): Writer[W, Unit] =
    Writer(w, ())

  def writer[W, A](a: A)(w: W): Writer[W, A] =
    Writer(w, a)
}
