package main.di.components

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.ContentValues
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import main.db.AppDatabase
import main.db.dao.ContactDao
import main.db.entity.Contact
import javax.inject.Scope

@AppScope
@Component(modules = [(ApplicationModule::class), (DataModule::class)])
interface AppComponent {

    fun inject(app: Application)

    fun contactsComponentBuilder(): ContactComponent.Builder
}

@Module
class ApplicationModule(val context: Context) {
    @AppScope
    @Provides
    fun provideContext() = context
}

@Module
class DataModule {
    @AppScope
    @Provides
    fun providesDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "database")
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
    @AppScope
    @Provides
    fun providesContactDao(database: AppDatabase): ContactDao = database.contactsDao()
}
fun listArray(): Array<Contact> {
    return Array(24, { i ->
        Contact(firstName = "User FirstName$i", lastName = "User LastName$i", age = i, phoneNumber = 1234567890, post = "programmer")
    })
}

@Scope @Retention(AnnotationRetention.RUNTIME) annotation class AppScope