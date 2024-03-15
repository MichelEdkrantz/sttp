package sttp.client3.asynchttpclient.cats

import cats.effect.IO
import sttp.client3._
import sttp.client3.asynchttpclient.AsyncHttpClientHttpTest
import sttp.client3.impl.cats.{CatsRetryTest, CatsTestBase}

class AsyncHttpClientCatsHttpTest extends AsyncHttpClientHttpTest[IO] with CatsRetryTest with CatsTestBase {
  override val backend: SttpBackend[IO, Any] = AsyncHttpClientCatsBackend[IO]().unsafeRunSync()

  "illegal url exceptions" - {
    "should be wrapped in the effect wrapper" in {
      basicRequest.get(uri"ps://sth.com").send(backend).toFuture().failed.map { e =>
        e shouldBe a[IllegalArgumentException]
      }
    }
  }

  override def throwsExceptionOnUnsupportedEncoding = false
  override def supportsAutoDecompressionDisabling = false
}
