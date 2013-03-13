package cheat.readert

case class ReaderT[M[_], R, A](run: R => M[A]) {
  def map[B](f: A => B): ReaderT[M, R, A] =
    ???

  def flatMap[B](f: A => ReaderT[M, R, B]): ReaderT[M, R, B] =
    ???
}
