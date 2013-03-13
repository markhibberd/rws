package cheat.statet

import scalaz._, Scalaz._

case class StateT[M[_], S, A](run: S => M[(S, A)]) {
  def map[B](f: A => B)(implicit M: Monad[M]): StateT[M, S, B] =
    flatMap(a => StateT.value(f(a)))

  def flatMap[B](f: A => StateT[M, S, B])(implicit M: Monad[M]): StateT[M, S, B] =
    StateT(s => run(s) flatMap ({
      case (ss, a) => f(a).run(s)
    }))
}

object StateT {
  def value[M[_]: Monad, S, A](a: => A): StateT[M, S, A] =
    StateT(s => (s, a).point[M])
}
