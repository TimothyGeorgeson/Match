package com.example.consultants.match.ui.contactlist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ContactListViewModel extends AndroidViewModel {
    public static final String TAG = BookListViewModel.class.getSimpleName() + "_TAG";

    private BookRepository bookRepository;

    public BookListViewModel(@NonNull Application application) {
        super(application);
        bookRepository = new BookRepository(new RemoteDataSource(), new LocalDataSource(application.getApplicationContext()));
    }

    public LiveData<List<Book>> getBooks() {
        return bookRepository.getBooks();
    }

}
