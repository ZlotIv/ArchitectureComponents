package main.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import main.App
import main.db.dao.ContactDao
import main.db.entity.Contact

@Database(entities = [(Contact::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactsDao(): ContactDao

    companion object {

        private fun listArray(): Array<Contact> {
            return Array(24) { i ->
                Contact(firstName = "User FirstName$i", lastName = "User LastName$i", age = i, phoneNumber = 1234567890, post = "programmer")
            }
        }

        val dataBase : AppDatabase by lazy {
            Room.databaseBuilder(App.appContext, AppDatabase::class.java, "database")
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Completable.fromAction {
                                listArray().map { contact ->
                                    dataBase.contactsDao().insertAll(contact)
                                }
                            }
                                    .subscribeOn(Schedulers.computation())
                                    .subscribe()
                        }
                    }).build()
        }

//        private var INSTANCE: AppDatabase? = null
//
//        fun getInstance() = INSTANCE ?: buildDatabase().also { INSTANCE = it }
//
//        private fun buildDatabase(): AppDatabase {
//            return Room.databaseBuilder(App.appContext, AppDatabase::class.java, "database")
//                    .addCallback(object : RoomDatabase.Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//                            Completable.create {
//                                listArray().map { contact ->
//                                    INSTANCE?.contactsDao()?.insertAll(contact)
//                                }
//                            }
//                                    .subscribeOn(Schedulers.computation())
//                                    .observeOn(AndroidSchedulers.mainThread())
//                                    .subscribe()
//                        }
//                    }).build()
//        }
    }
}