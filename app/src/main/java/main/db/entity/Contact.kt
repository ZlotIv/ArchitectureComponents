package main.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Contact(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                   @ColumnInfo(name = "first_name") val firstName: String,
                   @ColumnInfo(name = "last_name") val lastName: String,
                   @ColumnInfo(name = "age") val age: Int,
                   @ColumnInfo(name = "post") val post: String,
                   @ColumnInfo(name = "phone_number") val phoneNumber: Int)