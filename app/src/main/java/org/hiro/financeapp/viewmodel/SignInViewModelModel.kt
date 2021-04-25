package org.hiro.financeapp.viewmodel

import androidx.lifecycle.ViewModel
import org.hiro.financeapp.database.dao.UserDao
import org.hiro.financeapp.model.User
import org.hiro.financeapp.util.PasswordUtils

class SignInViewModelModel(private val db: UserDao) : ViewModel() {
    var user: User? = null

    fun checkUser(email: String, password: String): Boolean {
        user = db.checkAndGet(email, PasswordUtils.sha1(password))
        return user != null
    }
}
