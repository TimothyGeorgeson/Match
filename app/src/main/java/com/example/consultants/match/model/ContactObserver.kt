package com.example.consultants.match.model

import com.example.consultants.match.model.jsondata.ContactResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class ContactObserver : Observer<ContactResponse> {

    internal var callback: Callback? = null

    private fun setCallback(callback: Callback) {
        this.callback = callback
    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(contacts: ContactResponse) {
        callback?.onNext(contacts)
    }

    override fun onError(e: Throwable) {
        callback?.onError(e.message.toString())
    }

    override fun onComplete() {

    }

    interface Callback {
        fun onNext(contactResponse: ContactResponse)
        fun onError(error: String)
    }

    companion object {

        fun addCallback(callback: Callback): ContactObserver {

            val contactObserver = ContactObserver()
            contactObserver.setCallback(callback)
            return contactObserver
        }
    }
}
