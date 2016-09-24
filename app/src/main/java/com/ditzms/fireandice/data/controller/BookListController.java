package com.ditzms.fireandice.data.controller;

import android.util.Log;

import com.ditzms.fireandice.application.MyApplication;
import com.ditzms.fireandice.data.model.Book;
import com.ditzms.fireandice.data.repository.IBookListRepository;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */
public class BookListController implements IBookListController {

    private static final String TAG = "BookListController";

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Fields
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private IBookListRepository mRepository;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Constructor
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public BookListController(IBookListRepository repository) {
        mRepository = repository;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Public methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void getBooks(Listener listener) {
        List<Book> bookList = fetchFromRepository();

        if (!bookList.isEmpty()) {
            listener.onResponse(bookList);
            return;
        }


        bookList = fetchFromDb();

        if (!bookList.isEmpty()) {
            Log.d(TAG, "getBooks: FROM DATABASE");
            listener.onResponse(bookList);
            return;
        }

        fetchFromServer(listener);

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Private methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private List<Book> getBooksFromRepository() {
        return mRepository.getList();
    }

    private List<Book> fetchFromRepository() {
        return mRepository.getList();
    }

    private List<Book> fetchFromDb() {
        return Book.listAll(Book.class);
    }

    private void fetchFromServer(Listener listener) {

        Call<List<Book>> call = MyApplication.getFireAndIceClient().getFireAndIceApi().getBooks();
        try {
            Response<List<Book>> response = call.execute();

            if (response != null) {
                List<Book> bookList = response.body();
                mRepository.addAll(bookList);
                Book.saveInTx(bookList);
                listener.onResponse(bookList);
            }

        } catch (IOException e) {
            Log.e(TAG, "getBooks: ", e);
        }
    }

}
