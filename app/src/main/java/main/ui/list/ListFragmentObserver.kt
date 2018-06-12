package main.ui.list

import android.arch.lifecycle.*
import android.support.v7.widget.RecyclerView
import main.adapters.ContactAdapter


class ListFragmentObserver(private val contactListRv: RecyclerView, private val lifecycle: LifecycleOwner,
                           private val viewModel: ListFragmentViewModel) : LifecycleObserver {

    private val adapter: ContactAdapter by lazy {
        ContactAdapter()
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        viewModel.allData.observe(lifecycle, Observer {
            adapter.submitList(it)
            contactListRv.adapter = adapter
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {

    }
}