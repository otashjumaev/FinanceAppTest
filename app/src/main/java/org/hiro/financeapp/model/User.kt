package org.hiro.financeapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var name: String? = null,
    var email: String? = null,
    @ColumnInfo(name = "phone_number")
    var phoneNumber: String? = null,
    var password: String? = null
)

data class UserMini(
    var name: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
)