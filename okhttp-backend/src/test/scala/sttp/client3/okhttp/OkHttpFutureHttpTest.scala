package sttp.client3.okhttp

import sttp.capabilities.WebSockets
import sttp.client3.SttpBackend
import sttp.client3.testing.ConvertToFuture

import scala.concurrent.Future

class OkHttpFutureHttpTest extends OkHttpHttpTest[Future] {

  override val backend: SttpBackend[Future, WebSockets] = OkHttpFutureBackend()
  override implicit val convertToFuture: ConvertToFuture[Future] = ConvertToFuture.future

  override def supportsCancellation: Boolean = false
  override def timeoutToNone[T](t: Future[T], timeoutMillis: Int): Future[Option[T]] = t.map(Some(_))
  override def supportsDeflateWrapperChecking = false
}
