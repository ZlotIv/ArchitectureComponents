package main.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.ContentValues
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import main.App
import main.db.dao.ContactDao
import main.db.entity.Contact

@Database(entities = [(Contact::class)], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun contactsDao(): ContactDao

    companion object {
        val database = Room.databaseBuilder(App.appContext, AppDatabase::class.java, "database")
                .allowMainThreadQueries()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Completable.create {
                            listArray().mapIndexed { index, contact ->
                                val contentValues = ContentValues()
                                contentValues.put("first_name", "${contact.firstName}$index")
                                contentValues.put("last_name", "${contact.lastName}$index")
                                contentValues.put("age", contact.age + index)
                                contentValues.put("post", contact.post)
                                contentValues.put("phone_number", contact.phoneNumber)
                                db.insert("Contact", OnConflictStrategy.IGNORE, contentValues)
                            }
                        }
                                .subscribeOn(Schedulers.computation())
                                .subscribe()
                    }
                })
                .build()
        private fun listArray(): Array<Contact> {
            return Array(24, { i ->
                Contact(firstName = "User FirstName$i", lastName = "User LastName$i", age = i, phoneNumber = 1234567890, post = "programmer")
            })
        }
    }
}