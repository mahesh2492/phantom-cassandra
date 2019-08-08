package model

import com.datastax.driver.core.ResultSet
import com.outworkers.phantom.dsl._
import com.outworkers.phantom.keys.PartitionKey
import entity.Song

import scala.concurrent.Future

/*
Cassandra representation of the table song
 */
abstract class Songs extends Table[Songs, Song] with RootConnector {

  object id extends StringColumn(this) with PartitionKey

  object artist extends StringColumn(this)

  object title extends StringColumn(this)

  object album extends StringColumn(this)

  object year extends IntColumn(this)

  def getBySongId(id: String): Future[Option[Song]] =
    select
      .where(_.id eqs id)
      .one()

  def getNameById(id: String): Future[Option[String]] =
    select(_.title).where(_.id eqs id).one()


  def getTitleAndAlbum(id: String): Future[Option[(String, String)]] =
    select(_.title, _.album).where(_.id eqs id).one()


  def deleteBySongId(id: String): Future[Option[Song]] = {
   val deletedRecord = delete.where(_.id eqs id).consistencyLevel_=(ConsistencyLevel.ONE).future()

    deletedRecord.flatMap(_ => getBySongId(id)).recoverWith {
      case ex: Exception =>
        Future.failed(new Exception("Could not delete a song", ex))
    }

  }

  def create(song: Song): Future[Option[Song]] = {
    val inserted = insert
      .value(_.id, song.id)
      .value(_.title, song.title)
      .value(_.album, song.album)
      .value(_.artist, song.artist)
      .value(_.year, song.year)
      .future()

    inserted.flatMap(_ => getBySongId(song.id)).recoverWith {
      case ex: Exception =>
        Future.failed(new Exception("Cannot create Song", ex))
    }
  }

}
