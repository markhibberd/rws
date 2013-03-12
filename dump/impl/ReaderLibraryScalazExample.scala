package impl

import org.jboss.netty.handler.codec.http.{HttpRequest, HttpMethod}, HttpMethod._
import scala.collection.JavaConverters._
import scalaz._, Scalaz._, std.anyVal.booleanInstance.conjunction
import Reader._

object ReaderLibaryScalazExample {
  implicit val BooleanMonoid = conjunction

  type HttpReader[A] = Reader[HttpRequest, A]

  def header(name: String): HttpReader[List[String]] =
    ask[HttpRequest] map (_.getHeaders(name).asScala.toList)

  def method: HttpReader[HttpMethod] =
    ask[HttpRequest] map (_.getMethod)

  def auth: HttpReader[Boolean] =
    method >>= (_.matchOrZero({
      case PUT | POST =>
        header("Authorization") map (_.headOption.
          exists(_ == "let me in"))
    }))
}
