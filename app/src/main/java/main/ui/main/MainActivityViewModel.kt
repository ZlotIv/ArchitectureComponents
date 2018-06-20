package main.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import main.db.entity.Contact

/*AndroidViewModel(Application())*/

class MainActivityViewModel: ViewModel() {

    private val contactLiveData: MutableLiveData<Contact> = object : MutableLiveData<Contact>(){}

    fun getContact(): LiveData<Contact> = contactLiveData

    fun setContact(contact: Contact) = contactLiveData.postValue(contact)
}