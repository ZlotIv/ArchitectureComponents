package main.model

import android.arch.paging.DataSource
import main.db.dao.ContactDao
import main.db.entity.Contact
import main.di.components.ContactScope
import javax.inject.Inject

@ContactScope
class ContactRepository @Inject constructor(private val dao: ContactDao) {
    fun getData(): DataSource.Factory<Int, Contact> = dao.getAll()
}