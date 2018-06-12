package main.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import main.db.dao.ContactDao
import main.db.entity.Contact

@Database(entities = [(Contact::class)], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun contactsDao(): ContactDao
}