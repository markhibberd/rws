package live.reader

import scalaz._

case class Reader[R, A](run: R => A) {
  def map[B](f: A => B): Reader[R, B] =
    ???

  def flatMap[B](f: A => Reader[R, B]): Reader[R, B] =
    ???
}

object Reader {
  def value[R, A](a: => A): Reader[R, A] =
    ???

  def ask[R]: Reader[R, R] =
    ???

  def local[R, A](f: R => R)(reader: Reader[R, A]): Reader[R, A] =
    ???

  implicit def ReaderMonad[R]: Monad[PartialReader[R]#t] = new Monad[PartialReader[R]#t] {
    def point[A](a: => A): Reader[R, A] = ???
    def bind[A, B](r: Reader[R, A])(f: A => Reader[R, B]) = ???
  }

  implicit def ReaderMonoid[R, A](implicit A: Monoid[A]): Monoid[Reader[R, A]] = new Monoid[Reader[R, A]] {
    def zero: Reader[R, A] = ???
    def append(a: Reader[R, A], b: => Reader[R, A]) = ???
  }

  private class PartialReader[R] {
    type t[A] = Reader[R, A]
  }

  // Aside: You may be unfamliar with the type trick above, but may have seen
  //        its more common (inline) form, refered to as a 'type lambda', i.e.
  //           ({type λ[α] = Reader[R, α]})#λ
  //
  //        What this does (in both examples) is use path dependent types to
  //        express partial application of a type constructor. In the example
  //        Reader is a type constructor that takes two arguments, R and A, but we want
  //        to refer to the monad, Reader[R, _] that only has a single type
  //        argument. The 'type lambda' version creeates an anonymous structural
  //        type, the long form uses a standard named class.
}
