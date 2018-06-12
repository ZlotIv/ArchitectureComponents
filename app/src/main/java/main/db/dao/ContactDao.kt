package main.db.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import main.db.entity.Contact
import main.di.components.AppScope

@AppScope
@Dao
interface ContactDao {
    @Query("SELECT * FROM Contact")
    fun getAll(): DataSource.Factory<Int, Contact>

    @Insert
    fun insertAll(vararg contact: Contact)

    @Delete
    fun delete(contact: Contact)
}