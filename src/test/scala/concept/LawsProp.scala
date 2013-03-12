package concept

import org.scalacheck._, Prop._

object LawsProp extends Properties("Laws") {
  import Laws._

  property("Addition commutes") = forAll(
    (x: Int, y: Int) => addition(x, y) == addition(y, x))
}
