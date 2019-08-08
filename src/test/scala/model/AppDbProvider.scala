package model

import com.outworkers.phantom.database.DatabaseProvider
import store.AppDatabase

trait AppDbProvider extends DatabaseProvider[AppDatabase] {
  override def database: AppDatabase = AppDatabase
}
