package app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import app.action.Site._
import app.http.HttpServer

object Server {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("system")
    system.actorOf(HttpServer.apply(), HttpServer.Name)
  }
}
