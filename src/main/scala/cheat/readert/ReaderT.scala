package cheat.readert

import scalaz._, Scalaz._

case class ReaderT[M[_], R, A](run: R => M[A]) {
  def map[B](f: A => B)(implicit M: Monad[M]): ReaderT[M, R, B] =
    flatMap(a => ReaderT.value(f(a)))

  def flatMap[B](f: A => ReaderT[M, R, B])(implicit M: Monad[M]): ReaderT[M, R, B] =
    ReaderT(r => run(r) flatMap (a => f(a).run(r)))
}

object ReaderT {
  def value[M[_]: Monad, R, A](a: => A): ReaderT[M, R, A] =
    ReaderT(_ => a.point[M])
}
