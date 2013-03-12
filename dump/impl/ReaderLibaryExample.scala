package impl

import org.jboss.netty.handler.codec.http.{HttpRequest, HttpMethod}, HttpMethod._
import scala.collection.JavaConverters._
import Reader._

object ReaderLibaryExample {
  type HttpReader[A] = Reader[HttpRequest, A]

  def header(name: String): HttpReader[List[String]] =
    ask[HttpRequest] map (_.getHeaders(name).asScala.toList)

  def method: HttpReader[HttpMethod] =
    ask[HttpRequest] map (_.getMethod)

  def auth: HttpReader[Boolean] = for {
    m <- method
    r <- m match {
      case PUT | POST =>
        header("Authorization") map (_.headOption.exists(_ == "let me in"))
      case GET =>
        Reader.reader[HttpRequest, Boolean](true)
    }
  } yield r
}
