package main.model

import android.arch.paging.DataSource
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import main.db.AppDatabase
import main.db.dao.ContactDao
import main.db.entity.Contact

class ContactRepository {

    private val dao: ContactDao by lazy { AppDatabase.dataBase.contactsDao() }

    fun getData(): DataSource.Factory<Int, Contact> = dao.getAll()

    fun deleteContact(contact: Contact) {
        Completable.fromAction { dao.delete(contact) }
                .subscribeOn(Schedulers.computation())
                .subscribe()
    }
}