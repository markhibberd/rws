package concept.trans

import scalaz._, Scalaz._

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


object Fakery {
  type User = String
  type Profile = String
  type Widget = String
  type Json = String
  type HttpRequest = String
}

object WithoutTrans {
  import Fakery._
  import cheat.reader._

  type HttpReader[A] = Reader[HttpRequest, A]

  def user: HttpReader[Option[User]] = ???
  def profile(u: User): HttpReader[Option[Profile]] = ???
  def widget(u: User): HttpReader[Option[Widget]] = ???
  def render(u: User, p: Profile, w: Widget): Json =  ???

  def service: HttpReader[Option[Json]] = for {
    ou <- user
    op <- Reader((r: HttpRequest) => for {
      u <- ou
      result <- profile(u).run(r)
    } yield result)
    ow <- Reader((r: HttpRequest) => for {
      u <- ou
      result <- widget(u).run(r)
    } yield result)
  } yield for {
    u <- ou
    p <- op
    w <- ou
  } yield render(u, p, w)

}

object WithTrans {
  import Fakery._
  import MonadTransSyntax._
  import cheat.readert._

  type HttpReader[A] = ReaderT[Option, HttpRequest, A]

  def user: HttpReader[User] = ???
  def profile(u: User): HttpReader[Profile] = ???
  def widget(u: User): HttpReader[Widget] = ???
  def render(u: User, p: Profile, w: Widget): Json =  ???

  def service: ReaderT[Option, HttpRequest, Json] = for {
    u <- user
    p <- profile(u)
    w <- widget(u)
  } yield render(u, p, w)
}
