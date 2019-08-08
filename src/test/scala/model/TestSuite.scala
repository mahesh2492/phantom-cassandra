package model

import org.cassandraunit.utils.EmbeddedCassandraServerHelper
import org.scalatest.{AsyncFlatSpec, BeforeAndAfterAll}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

trait TestSuite extends AsyncFlatSpec with AppDbProvider with BeforeAndAfterAll {

  override def beforeAll(): Unit = {
    super.beforeAll()
    EmbeddedCassandraServerHelper.startEmbeddedCassandra("test-cassandra.yaml", 1000000L)
    database.create(10 seconds)
  }
}
