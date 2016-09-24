package com.ditzms.fireandice.data.repository;

import android.util.Log;

import com.ditzms.fireandice.data.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */
public class BookListRepository implements IBookListRepository {

    private static final String TAG = "BookListRepository";

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Fields
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private List<Book> mBookList;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Constructor
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public BookListRepository() {
        mBookList = new ArrayList<>();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Public methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<Book> getList() {
        return mBookList;
    }

    @Override
    public void addAll(List<Book> list) {
        mBookList.addAll(list);
    }

    @Override
    public void clear() {
        mBookList.clear();
    }

    @Override
    public Book get(int position) {
        try {
            return mBookList.get(position);
        } catch (IndexOutOfBoundsException ex) {
            Log.e(TAG, "get: ", ex);
            return null;
        }
    }


}
