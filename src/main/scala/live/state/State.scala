package live.state

case class State[S, A](run: S => (S, A)) {
  def map[B](f: A => B): State[S, B] =
    ???

  def flatMap[B](f: A => State[S, B]): State[S, B] =
    ???
}

object State {
  def value[S, A](a: => A): State[S, A] =
    ???

  def get[S]: State[S, S] =
    ???

  def gets[S, A](f: S => A): State[S, A] =
    ???

  def modify[S](f: S => S): State[S, Unit] =
    ???

  def put[S](s: S): State[S, Unit] =
    ???
}
