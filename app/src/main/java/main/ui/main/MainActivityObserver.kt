package main.ui.main

import android.arch.lifecycle.*


class MainActivityObserver(private val viewModel: MainActivityViewModel, private val lifeCycleOwner: LifecycleOwner, private val lifeCycle: Lifecycle): LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        viewModel.getContact().observe(lifeCycleOwner, Observer {

        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStop() {

//        if (lifeCycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
//
//        }
    }
}