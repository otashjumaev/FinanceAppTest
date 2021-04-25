package org.hiro.financeapp.app

import android.app.Application
import com.orhanobut.hawk.Hawk
import org.hiro.financeapp.util.HawkUtils

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
    }
}