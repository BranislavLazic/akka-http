package app.http

import akka.actor.Status.Failure
import akka.actor.{Actor, ActorLogging, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.pattern.pipe
import akka.stream.ActorMaterializer

object HttpServer {
  final val Name = "http-server"

  def apply() = Props(new HttpServer)
}

final class HttpServer extends Actor with ActorLogging {
  import app.util.ConfigReader._
  import app.action.Site._
  import context.dispatcher
  private implicit val materializer = ActorMaterializer()

  Http(context.system).bindAndHandle(route, host, port).pipeTo(self)

  override def receive: Receive = {
    case ServerBinding(socketAddress) => log.info(s"Server started at: $socketAddress")
    case Failure(cause)               =>
      log.error(s"Failed to start server: $cause")
      context.system.terminate()
  }
}
