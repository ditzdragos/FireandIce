package com.ditzms.fireandice.feature.booklist;

import com.ditzms.fireandice.data.model.Book;

import java.util.List;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */

interface IBookListView {

    void onBookListAvailable(List<Book> list);
}
