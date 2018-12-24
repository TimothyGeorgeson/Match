package com.example.consultants.match.model;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.List;

public class ContactObserver implements Observer<List<Contact>> {

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
    public void onNext(List<Contact> contacts) {
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

        void onNext(List<Contact> contacts);

        void onError(String error);
    }
}
