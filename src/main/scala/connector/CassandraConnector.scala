package connector

import com.outworkers.phantom.connectors.CassandraConnection
import com.outworkers.phantom.dsl._
import com.typesafe.config.ConfigFactory

import scala.collection.JavaConverters._

object CassandraConnector {
  private val config = ConfigFactory.load()
  private val hosts = config.getStringList("cassandra.host").asScala
  private val keyspace = config.getString("cassandra.keyspace")
  val connector: CassandraConnection = ContactPoints(hosts).keySpace(keyspace)
}
