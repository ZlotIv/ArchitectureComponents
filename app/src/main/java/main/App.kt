package main

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import main.di.ComponentManager


class App: Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        Stetho.initializeWithDefaults(this)
        ComponentManager.appComponent.inject(this)
    }

}