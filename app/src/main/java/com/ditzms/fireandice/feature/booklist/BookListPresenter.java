package com.ditzms.fireandice.feature.booklist;

import com.ditzms.fireandice.application.MyApplication;
import com.ditzms.fireandice.data.controller.IBookListController;
import com.ditzms.fireandice.data.model.Book;
import com.ditzms.fireandice.utils.ThreadUtils;

import java.util.List;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */
public class BookListPresenter implements IBookListPresenter {

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Fields
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private IBookListView mView;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Constructor
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Public methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void attachView(IBookListView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void loadBooks() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                MyApplication.getBookListController().getBooks(new IBookListController.Listener() {
                    @Override
                    public void onResponse(List<Book> bookList) {
                        notifyBookListLoadComplete(bookList);
                    }
                });
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Private methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void notifyBookListLoadComplete(List<Book> bookList) {
        if (mView != null) {
            mView.onBookListAvailable(bookList);
        }
    }

}
