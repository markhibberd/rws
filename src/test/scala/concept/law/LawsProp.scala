package concept.law

import org.scalacheck._, Prop._

object LawsProp extends Properties("Laws") {
  property("Integer addition is associative") = forAll(
    (x: Int, y: Int, z: Int) => ((x + y) + z) == (x + (y + z)))

  property("Integer addition is commutative") = forAll(
    (x: Int, y: Int) => (x + y) == (y + x))
}
