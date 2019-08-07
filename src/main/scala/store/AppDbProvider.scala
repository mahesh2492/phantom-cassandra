package store

import com.outworkers.phantom.database.DatabaseProvider

trait AppDbProvider extends DatabaseProvider[AppDatabase] {
  override def database: AppDatabase = AppDatabase
}
