package live.writert

case class WriterT[M[_], W, A](run: M[(W, A)]) {
  def map[B](f: A => B): WriterT[M, W, A] =
    ???

  def flatMap[B](f: A => WriterT[M, W, B]): WriterT[M, W, B] =
    ???
}
