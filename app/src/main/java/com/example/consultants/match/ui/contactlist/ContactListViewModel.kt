package com.example.consultants.match.ui.contactlist

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.consultants.match.model.data.ContactRepository
import com.example.consultants.match.model.data.remote.RemoteDataSource
import com.example.consultants.match.model.jsondata.Contact

class ContactListViewModel(application: Application) : AndroidViewModel(application) {

    private val contactRepository: ContactRepository = ContactRepository(RemoteDataSource())

    val contacts: LiveData<List<Contact>>
        get() = contactRepository.getContacts()

}
