package concept.duplication

object Wat {
  // Duplication for the Win!!!!!
  def wat(a: String, b: String) = {
    val aa = a.substring(0, if (a.length > 0) a.length - 1 else 0)
    val bb = b.substring(0, if (b.length > 0) b.length - 1 else 0)
    val aaa = aa.length * 111
    val bbb = bb.length * 222
    aaa + bbb
  }
}
