package main.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.ContentValues
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import main.App
import main.db.dao.ContactDao
import main.db.entity.Contact

@Database(entities = [(Contact::class)], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun contactsDao(): ContactDao

    companion object {
        val database by lazy {
            val room = Room.databaseBuilder(App.appContext, AppDatabase::class.java, "database").build()
            Completable.create {
                listArray().mapIndexed { index, contact ->
                    room.contactsDao().insertAll(contact)
                }
            }
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            return@lazy room
        }
        private fun listArray(): Array<Contact> {
            return Array(24, { i ->
                Contact(firstName = "User FirstName$i", lastName = "User LastName$i", age = i, phoneNumber = 1234567890, post = "programmer")
            })
        }
    }
}