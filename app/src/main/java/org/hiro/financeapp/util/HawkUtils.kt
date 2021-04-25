package org.hiro.financeapp.util

import com.google.gson.Gson
import com.orhanobut.hawk.Hawk
import org.hiro.financeapp.model.User
import org.hiro.financeapp.model.UserMini

object HawkUtils {

    var firstOpened: Boolean
        get() = Hawk.get("FIRST_OPENED", true)
        set(value) {
            Hawk.put("FIRST_OPENED", value)
        }

    var userLoggedIn: Boolean
        get() = Hawk.get("LOGGED_IN", false)
        set(value) {
            Hawk.put("LOGGED_IN", value)
        }


    var user: UserMini?
        get() {
            return Gson().fromJson(
                Hawk.get(
                    "user_data",
                    Gson().toJson(UserMini())
                ),
                UserMini::class.java
            )
        }
        set(value) {
            Hawk.put("user_data", Gson().toJson(value))
        }
}