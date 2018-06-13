package main

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.ContentValues
import android.content.Context
import com.facebook.stetho.Stetho
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import main.db.AppDatabase
import main.db.entity.Contact


class App: Application() {

    companion object {
        lateinit var appContext: Context
        lateinit var dataBase: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        Stetho.initializeWithDefaults(this)
        dataBase = Room.databaseBuilder(this, AppDatabase::class.java, "database")
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
    }

    fun listArray(): Array<Contact> {
        return Array(24, { i ->
            Contact(firstName = "User FirstName$i", lastName = "User LastName$i", age = i, phoneNumber = 1234567890, post = "programmer")
        })
    }
}