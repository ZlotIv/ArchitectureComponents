package main

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho


class App: Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        Stetho.initializeWithDefaults(this)
    }
}