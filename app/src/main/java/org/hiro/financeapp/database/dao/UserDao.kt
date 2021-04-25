package org.hiro.financeapp.database.dao

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*
import org.hiro.financeapp.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User): Long

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM User WHERE id = :id")
    fun get(id: Long): User?

    @Query("SELECT * FROM User")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE (email = :email OR phone_number= :email) AND password = :password")
    fun checkAndGet(email: String, password: String): User?

    @Query("DELETE FROM User")
    fun clear()

}
