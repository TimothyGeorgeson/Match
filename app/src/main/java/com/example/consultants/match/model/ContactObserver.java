package com.example.consultants.match.model;

import com.example.consultants.match.model.jsondata.ContactResponse;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ContactObserver implements Observer<ContactResponse> {

    Callback callback;

    public static ContactObserver addCallback(Callback callback) {

        ContactObserver contactObserver = new ContactObserver();
        contactObserver.setCallback(callback);
        return contactObserver;
    }

    private void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ContactResponse contacts) {
        callback.onNext(contacts);
    }

    @Override
    public void onError(Throwable e) {
        callback.onError(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public interface Callback {
        void onNext(ContactResponse contacts);
        void onError(String error);
    }
}
