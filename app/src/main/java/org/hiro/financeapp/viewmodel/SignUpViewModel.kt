package org.hiro.financeapp.viewmodel

import androidx.lifecycle.ViewModel
import org.hiro.financeapp.database.dao.UserDao
import org.hiro.financeapp.model.User
import org.hiro.financeapp.util.PasswordUtils

class SignUpViewModel(private val db: UserDao) : ViewModel() {
    var user: User? = null
    fun signUp(user: User): Boolean {
        user.apply { password?.let { password = PasswordUtils.sha1(password!!) } }
        if (db.checkAndGet(user.email ?: "", user.password ?: "") == null) {
            val id = db.insert(user)
            if (id != -1L)
                this.user = user.apply { this.id = id }
            else return false
        } else return false
        return true
    }

}
