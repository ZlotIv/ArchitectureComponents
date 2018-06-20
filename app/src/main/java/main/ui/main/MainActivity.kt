package main.ui.main

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import main.ui.details.DetailsFragment
import main.ui.list.ListFragment
import zlotnikov.architecturecomponents.R

class MainActivity : AppCompatActivity(), LifecycleOwner {

    lateinit var observer: MainActivityObserver
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        observer = MainActivityObserver(viewModel,this, lifecycle)
        lifecycle.addObserver(observer)

        setFragment()

    }

    private fun setFragment() {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.firstContainer, DetailsFragment.newInstance())
                .commit()

        supportFragmentManager
                .beginTransaction()
                .add(R.id.secondContainer, ListFragment.newInstance())
                .commit()
    }
}
