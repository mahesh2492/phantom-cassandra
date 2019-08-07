package store

import com.outworkers.phantom.connectors.CassandraConnection
import com.outworkers.phantom.dsl._
import connector.CassandraConnector
import model.Songs

class AppDatabase(override val connector: CassandraConnection) extends Database[AppDatabase](connector) {

  object songs extends Songs with Connector

}

object AppDatabase extends AppDatabase(CassandraConnector.connector)
