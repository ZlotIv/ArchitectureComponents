package main.ui.list

import android.arch.lifecycle.*
import android.support.v7.widget.RecyclerView
import main.adapters.ContactAdapter
import main.ui.main.MainActivityViewModel


class ListFragmentObserver(private val contactListRv: RecyclerView, private val lifecycle: LifecycleOwner,
                           private val viewModel: ListFragmentViewModel, private val viewModelActivity: MainActivityViewModel) : LifecycleObserver {

    private val adapter: ContactAdapter by lazy {
        ContactAdapter(viewModelActivity)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        contactListRv.adapter = adapter
        viewModel.allData.observe(lifecycle, Observer {
            adapter.submitList(it)
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {

    }
}