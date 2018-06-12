package main.ui.list

import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import main.di.components.ContactScope
import main.model.ContactRepository
import javax.inject.Inject


@ContactScope
class ListFragmentViewModel @Inject constructor(repository: ContactRepository) : ViewModel() {

    private val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .setPrefetchDistance(5)
            .build()

    val allData = LivePagedListBuilder(repository.getData(), config)
            .setInitialLoadKey(0)
            .build()
}