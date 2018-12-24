package com.example.consultants.match.ui.contactlist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import com.example.consultants.match.model.data.ContactRepository;
import com.example.consultants.match.model.data.remote.RemoteDataSource;
import com.example.consultants.match.model.jsondata.Contact;

import java.util.List;

public class ContactListViewModel extends AndroidViewModel {
    public static final String TAG = ContactListViewModel.class.getSimpleName() + "_TAG";

    private ContactRepository contactRepository;

    public ContactListViewModel(@NonNull Application application) {
        super(application);
        contactRepository = new ContactRepository(new RemoteDataSource());
    }

    public LiveData<List<Contact>> getContacts() {
        return contactRepository.getContacts();
    }

}
