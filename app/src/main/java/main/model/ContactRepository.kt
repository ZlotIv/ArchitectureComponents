package main.model

import android.arch.paging.DataSource
import main.App
import main.db.dao.ContactDao
import main.db.entity.Contact

class ContactRepository {

    private val dao: ContactDao by lazy { App.dataBase.contactsDao() }

    fun getData(): DataSource.Factory<Int, Contact> = dao.getAll()
}