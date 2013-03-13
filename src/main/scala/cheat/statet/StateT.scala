package cheat.statet

case class StateT[M[_], S, A](run: S => M[(S, A)]) {
  def map[B](f: A => B): StateT[M, S, B] =
    ???

  def flatMap[B](f: A => StateT[M, S, B]): StateT[M, S, B] =
    ???
}
