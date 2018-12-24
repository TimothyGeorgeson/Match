package com.example.consultants.match.model.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.example.consultants.match.model.Contact;
import com.example.consultants.match.model.data.remote.RemoteDataSource;

import java.util.List;

public class ContactRepository {
    public static final String TAG = ContactRepository.class.getSimpleName() + "_TAG";

    private MutableLiveData<List<Contact>> listLiveData;
    private RemoteDataSource remoteDataSource;

    public ContactRepository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
        listLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Contact>> getContacts() {

        return listLiveData;
    }
}
