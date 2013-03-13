package cheat.writert

import scalaz._, Scalaz._

case class WriterT[M[_], W, A](run: M[(W, A)]) {
  def map[B](f: A => B)(implicit W: Monoid[W], M: Monad[M]): WriterT[M, W, B] =
    flatMap(a => WriterT.value(f(a)))

  def flatMap[B](f: A => WriterT[M, W, B])(implicit W: Monoid[W], M: Monad[M]): WriterT[M, W, B] =
    WriterT(run flatMap ({
      case (w, a) => f(a).run
    }))
}

object WriterT {
  def value[M[_], W, A](a: => A)(implicit M: Monad[M], W: Monoid[W]): WriterT[M, W, A] =
    WriterT((W.zero, a).point[M])
}
