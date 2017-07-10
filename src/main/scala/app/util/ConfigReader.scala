package app.util

import com.typesafe.config.ConfigFactory

object ConfigReader {
  private val config = ConfigFactory.load()

  val host = config.getString("http.host")
  val port = config.getInt("http.port")
}
