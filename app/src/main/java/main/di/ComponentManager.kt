package main.di

import main.App
import main.di.components.*


object ComponentManager {

    @JvmStatic
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(App.appContext))
                .dataModule(DataModule())
                .build()
    }

    val contactComponent: ContactComponent by lazy {
        appComponent.contactsComponentBuilder()
                .build()
    }
}