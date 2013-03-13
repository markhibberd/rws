package concept.trans

import scalaz.Monad

trait MonadTrans[F[_[_], _]] {
  def lift[G[_]: Monad, A](g: G[A]): F[G, A]
}

// usage: g.lift[ReaderT]
case class MonadTransSyntax[G[_], A](g: G[A]) {
  def lift[F[_[_], _]](implicit T: MonadTrans[F], M: Monad[G]): F[G, A] =
    T.lift(g)
}

object MonadTransSyntax {
  implicit def ToMonadTransSyntax[G[_], A](g: G[A]) =
    MonadTransSyntax(g)
}
