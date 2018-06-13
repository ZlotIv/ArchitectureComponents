package main.ui.list


import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.*
import main.ui.main.MainActivityViewModel
import zlotnikov.architecturecomponents.R

class ListFragment : Fragment(), LifecycleObserver {

    companion object {
        fun newInstance(): ListFragment = ListFragment()
    }

    private lateinit var rootView: View
    private lateinit var viewModel: ListFragmentViewModel
    private lateinit var viewModelActivity: MainActivityViewModel
    private lateinit var observer: ListFragmentObserver

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView =  inflater.inflate(R.layout.fragment_list, container, false)

        viewModel = ViewModelProviders.of(this).get(ListFragmentViewModel::class.java)
        activity?.let { viewModelActivity = ViewModelProviders.of(it).get(MainActivityViewModel::class.java) }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer = ListFragmentObserver(contactRv, this, viewModel)
        lifecycle.addObserver(observer)
    }
}
