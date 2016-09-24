package com.ditzms.fireandice.data.controller;

import com.ditzms.fireandice.data.model.Book;

import java.util.List;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */

public interface IBookListController {

    void getBooks(Listener listener);

    interface Listener {
        void onResponse(List<Book> bookList);
    }

}
