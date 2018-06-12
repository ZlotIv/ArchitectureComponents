package main.ui.main

import android.arch.lifecycle.ViewModel
import main.di.components.ContactScope
import javax.inject.Inject

/*AndroidViewModel(Application())*/

@ContactScope
class MainActivityViewModel @Inject constructor(): ViewModel() {

}