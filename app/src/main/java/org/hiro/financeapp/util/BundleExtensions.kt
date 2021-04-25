package org.hiro.financeapp.util

import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


fun <T> Bundle.putData(key: String, data: T) {
    this.putString(key, Gson().toJson(data))
}

fun Bundle.getData(key: String): String? {
    return this.getString(key)
}

inline fun <reified T> Bundle.getData(key: String): T? {
    return Gson().fromJson(getData(key), T::class.java)
}