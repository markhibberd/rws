package concept.law

object BrokenLaws {
  trait Monoid[F] {
    def zero: F
    def append(a: F, b: F): F
  }

  implicit def IntMonoid: Monoid[Int] = new Monoid[Int] {
    def zero = 0
    def append(a: Int, b: Int) = a + b
  }

  implicit def DoubleMonoid: Monoid[Double] = new Monoid[Double] {
    def zero = 0.0
    def append(a: Double, b: Double) = a + b
  }

  // FIX Doesn't foldRight stack overflow? Perhaps foldLeft? Mmmm, consequences...
  def reduce[A](xs: List[A])(implicit M: Monoid[A]) =
    xs.foldRight(M.zero)((a, acc) => M.append(a, acc))
}


object ContractKiller {
  import BrokenLaws._

  def print(s: String) = {
    println("==================================")
    println(s)
    println("==================================")
  }

  def transactions = List(-8.988465674311579E307, 8.988465674311579E307, 1.0)

  def main(args: Array[String]) {
    val owing = reduce(transactions)
    if (owing > 0.0)
      print("bang, your dead!!!")
    else
      print("have a nice day")
  }
}
