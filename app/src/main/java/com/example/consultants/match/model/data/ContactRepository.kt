package com.example.consultants.match.model.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.consultants.match.model.ContactObserver
import com.example.consultants.match.model.data.remote.RemoteDataSource
import com.example.consultants.match.model.jsondata.Contact
import com.example.consultants.match.model.jsondata.ContactResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContactRepository(private val remoteDataSource: RemoteDataSource) {

    private var listLiveData: MutableLiveData<List<Contact>> = MutableLiveData()

    fun getContacts(): LiveData<List<Contact>> {
        remoteDataSource.randApi.getContactsObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(ContactObserver.addCallback(object : ContactObserver.Callback {
                override fun onNext(contactResponse: ContactResponse) {
                    listLiveData.value = contactResponse.contacts
                }

                override fun onError(error: String) {
                    Log.i("_tag", error)
                }
            }))
        return listLiveData
    }
}
