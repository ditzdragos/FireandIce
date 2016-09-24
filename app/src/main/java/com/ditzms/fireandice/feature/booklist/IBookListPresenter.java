package com.ditzms.fireandice.feature.booklist;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */

interface IBookListPresenter {

    void attachView(IBookListView view);

    void detachView();

    void loadBooks();

}
