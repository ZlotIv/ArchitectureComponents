package main.ui.details


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import zlotnikov.architecturecomponents.R

class DetailsFragment : Fragment() {

    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView =  inflater.inflate(R.layout.fragment_details, container, false)
        return view
    }
}
