package live.writer

import scalaz._, Scalaz._

case class Writer[W, A](log: W, value: A) {
  def map[B](f: A => B): Writer[W, B] =
    ???

  def flatMap[B](f: A => Writer[W, B]): Writer[W, B] =
    ???
}

object Writer {
  def value[W, A](a: A)(implicit M: Monoid[W]) =
    ???

  def tell[W](w: W): Writer[W, Unit] =
    ???

  def writer[W, A](a: A)(w: W): Writer[W, A] =
    ???
}
