package concept.law

import org.scalacheck._, Prop._

object MonoidProp extends Properties("MonoidLaws") {

  trait Monoid[F] {
    def zero: F
    def append(a: F, b: F): F
  }

  implicit def IntMonoid: Monoid[Int] = new Monoid[Int] {
    def zero = 0
    def append(a: Int, b: Int) = a + b
  }

//  implicit def DoubleNotMonoid: Monoid[Double] = new Monoid[Double] {
//    def zero = 0.0
//    def append(a: Double, b: Double) = a + b
//  }

  case class MonoidSyntax[A](a: A) {
    def |+|(b: A)(implicit M: Monoid[A]) = M.append(a, b)
  }
  implicit def ToMonoidSyntax[A](a: A) = MonoidSyntax(a)

  // &forall; a, b in F, a + b is also in F
  def closure[F: Monoid](a: F, b: F): F = // enforced by type system
    a |+| b

  // &forall; a, b, c. (a + b) + c == a + (b + c)
  def associative[F : Monoid](a: F, b: F, c: F): Boolean =
    ((a |+| b) |+| c) == (a |+| (b |+| c))

  // &forall; a. zero + a = a
  def leftIdentity[F : Monoid](a: F): Boolean =
    (implicitly[Monoid[F]].zero |+| a) == a

  // &forall; a. a + zero = a
  def rightIdentity[F : Monoid](a: F): Boolean =
    (a |+| implicitly[Monoid[F]].zero) == a

  property("Int Monoid obeys monoid laws") = forAll(
    (x: Int, y: Int, z: Int) =>
      associative(x, y, z) &&
      leftIdentity(x) &&
      rightIdentity(x))

}
