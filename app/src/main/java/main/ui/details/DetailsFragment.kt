package main.ui.details


import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import main.ui.main.MainActivityViewModel
import zlotnikov.architecturecomponents.R
import zlotnikov.architecturecomponents.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(), LifecycleOwner {

    companion object {
        fun newInstance(): DetailsFragment = DetailsFragment()
    }

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsFragmentViewModel
    private lateinit var viewModelActivity: MainActivityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_details, container, false)

        viewModel = ViewModelProviders.of(this).get(DetailsFragmentViewModel::class.java)
        activity?.let { viewModelActivity = ViewModelProviders.of(it).get(MainActivityViewModel::class.java) }

        viewModelActivity.getContact().observe(this, Observer {
            viewModel.contactField.set(it)
        })
        binding.viewModel = viewModel

        return binding.root
    }
}
