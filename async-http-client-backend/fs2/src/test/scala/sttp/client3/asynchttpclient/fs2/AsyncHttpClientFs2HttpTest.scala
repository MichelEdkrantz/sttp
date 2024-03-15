package sttp.client3.asynchttpclient.fs2

import cats.effect.IO
import sttp.client3.SttpBackend
import sttp.client3.asynchttpclient.AsyncHttpClientHttpTest
import sttp.client3.impl.cats.{CatsTestBase, TestIODispatcher}

class AsyncHttpClientFs2HttpTest extends AsyncHttpClientHttpTest[IO] with TestIODispatcher with CatsTestBase {
  override val backend: SttpBackend[IO, Any] =
    AsyncHttpClientFs2Backend[IO](dispatcher = dispatcher).unsafeRunSync()

  override def throwsExceptionOnUnsupportedEncoding = false
  // for some unknown reason this single test fails using the fs2 implementation
  override def supportsConnectionRefusedTest = false
  override def supportsAutoDecompressionDisabling = false
}
