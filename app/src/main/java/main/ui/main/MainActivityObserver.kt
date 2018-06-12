package main.ui.main

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import main.ui.list.ListFragment
import zlotnikov.architecturecomponents.R


class MainActivityObserver(private val activity: MainActivity, private val lifeCycle: Lifecycle): LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.i("lifeCycle", "OnCreate()")
        activity.supportFragmentManager
                .beginTransaction()
                .add(R.id.mainContainer, ListFragment.newInstance())
                .commit()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStop() {

//        if (lifeCycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
//
//        }
    }

}