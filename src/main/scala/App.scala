import cats.effect._
import kamon.http4s.middleware.server.KamonSupport
import org.http4s._
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.Logger
import org.http4s.syntax.kleisli._
import org.slf4j.LoggerFactory
import scala.concurrent.ExecutionContext.global

object App extends IOApp {
  val iface = "0.0.0.0"
  val port = 8080

  def run(args: List[String]) =
    for {
      log <- IO(LoggerFactory.getLogger(getClass))
      service = HttpRoutes.of[IO] {
        case req => IO(log.info("In service")).as(Response(Status.Ok))
      }
      _ <- BlazeServerBuilder[IO](global)
        .withHttpApp(KamonSupport(Logger.httpRoutes(true, true)(service), iface, port).orNotFound)
        .bindHttp(port, iface)
        .resource
        .use(_ => IO.never)
    } yield ExitCode.Success
}
