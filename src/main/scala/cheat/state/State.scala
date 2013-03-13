package cheat.state

case class State[S, A](run: S => (S, A)) {
  def map[B](f: A => B): State[S, B] =
    flatMap(a => State.value(f(a)))

  def flatMap[B](f: A => State[S, B]): State[S, B] =
    State(s => run(s) match {
      case (ss, a) => f(a).run(s)
    })
}

object State {
  def value[S, A](a: => A): State[S, A] =
    State(s => (s, a))

  def get[S]: State[S, S] =
    State(s => (s, s))

  def gets[S, A](f: S => A): State[S, A] =
    get map f

  def modify[S](f: S => S): State[S, Unit] =
    State(s => (f(s), ()))

  def put[S](s: S): State[S, Unit] =
    modify(_ => s)
}
