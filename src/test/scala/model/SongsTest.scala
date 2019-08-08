package model

import entity.Song

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class SongsTest extends TestSuite {

  val id: String = java.util.UUID.randomUUID().toString
  val song1 = Song(id, "Eye of the Tiger", "Eye of the Tiger", "Survivor", 2000)
  val song2 = Song(java.util.UUID.randomUUID().toString, "Wake me up when september ends", "American Idiot", "Green Day", 2000)

  override def afterAll(): Unit = {
    database.truncate(5 seconds)
    super.afterAll()
  }

  it should "insert a new song in song table" in {
    database.truncate(2 seconds)

    (for {
      song <- database.songs.create(song2)
    } yield song).map { response =>
      assert(response.contains(song2))
    }
  }

  it should "not get a song by id" in {
    val songResponse = for {
      song <- database.songs.getBySongId(id)
    } yield song

    songResponse.map { song =>
      assert(song.isEmpty)
    }
  }

  it should "get song name by given id" in {
    val songResponse = for {
      _ <- database.songs.create(song1)
      optionalTitle <- database.songs.getNameById(id)
    } yield optionalTitle

    songResponse.map { title =>
      assert(title.contains(song1.title))
    }
  }

  it should "get a song by id" in {
    val songResponse = for {
      song <- database.songs.getBySongId(id)
    } yield song

    songResponse.map { song =>
      assert(song.contains(song1))
    }
  }

  it should "get album and title by given song id" in {
    val songResponse = for {
      mayBeTitleAlbum <- database.songs.getTitleAndAlbum(id)
    } yield mayBeTitleAlbum

    songResponse.map { titleAlbum =>
      assert(titleAlbum.contains((song1.title, song1.album)))
    }
  }

  it should "delete a song by given song id" in {
    val songResponse = for {
      maybeDeletedRecord <- database.songs.deleteBySongId(id)
    } yield maybeDeletedRecord

    songResponse.map { deletedRecord =>
      assert(deletedRecord.isEmpty)
    }
  }

}
