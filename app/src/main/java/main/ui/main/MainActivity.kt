package main.ui.main

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import main.di.ComponentManager
import zlotnikov.architecturecomponents.R
import javax.inject.Inject

class MainActivity : AppCompatActivity(), LifecycleOwner {

    lateinit var observer: MainActivityObserver
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ComponentManager.contactComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)

        observer = MainActivityObserver(this, lifecycle)
        lifecycle.addObserver(observer)
    }
}
