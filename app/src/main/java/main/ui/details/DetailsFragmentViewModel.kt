package main.ui.details

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import main.db.entity.Contact
import main.model.ContactRepository


class DetailsFragmentViewModel: ViewModel() {

    val contactField = ObservableField<Contact>()
    private val repository: ContactRepository by lazy { ContactRepository() }

    fun deleteContact(contact: Contact) {
        repository.deleteContact(contact)
    }

}