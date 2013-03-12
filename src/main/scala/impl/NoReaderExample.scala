package impl

import org.jboss.netty.handler.codec.http.{HttpRequest, HttpMethod}, HttpMethod._
import scala.collection.JavaConverters._

object NoReaderExample {
  def header(request: HttpRequest, name: String): List[String] =
    request.getHeaders(name).asScala.toList

  def method(request: HttpRequest): HttpMethod =
    request.getMethod

  def auth(request: HttpRequest): Boolean =
    method(request) match {
      case PUT | POST =>
        header(request, "Authorization").headOption.exists(_ == "let me in")
      case GET =>
        true
    }
}
