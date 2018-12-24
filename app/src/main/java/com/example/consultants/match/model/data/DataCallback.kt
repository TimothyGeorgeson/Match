package com.example.consultants.match.model.data

import com.example.consultants.match.model.jsondata.Contact

interface DataCallback {
    fun onSuccess(contactList: List<Contact>)
    fun onFailure(error: String)
}
