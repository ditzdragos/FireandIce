package com.ditzms.fireandice.data.repository;

import com.ditzms.fireandice.data.model.Book;

import java.util.List;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */

public interface IBookListRepository {

    void addAll(List<Book> list);

    void clear();

    Book get(int position);

    List<Book> getList();

}
