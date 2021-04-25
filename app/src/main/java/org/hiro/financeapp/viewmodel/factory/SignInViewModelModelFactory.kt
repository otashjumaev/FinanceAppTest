package org.hiro.financeapp.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.hiro.financeapp.database.dao.UserDao
import org.hiro.financeapp.viewmodel.SignInViewModelModel

class SignInViewModelModelFactory(private val db: UserDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModelModel::class.java))
            return SignInViewModelModel(db) as T
        throw IllegalArgumentException("ERROR ARGUMENT TYPE")
    }
}