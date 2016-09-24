package com.ditzms.fireandice.feature.characterlist;

import android.util.Log;

import com.ditzms.fireandice.application.MyApplication;
import com.ditzms.fireandice.data.controller.IBookListController;
import com.ditzms.fireandice.data.model.Book;
import com.ditzms.fireandice.utils.ThreadUtils;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */
public class CharacterListPresenter implements ICharacterListPresenter {

    private static final String TAG = "CharacterListPresenter";

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Fields
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private String[] mCharacterUrlList = new String[]{};
    private ICharacterListView mView;
    private int mPosition;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Constructor
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public CharacterListPresenter(int position) {
        mPosition = position;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Public methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void attachView(ICharacterListView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void loadCharacters() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                MyApplication.getBookListController().getBooks(new IBookListController.Listener() {
                    @Override
                    public void onResponse(List<Book> bookList) {

                        try {
                            Book book = bookList.get(mPosition);

                            if (book == null) {
                                notifyCharacterListLoadComplete();
                                return;
                            }

                            fetchCharactersUrls(book);
                        } catch (IndexOutOfBoundsException ex) {
                            Log.e(TAG, "onResponse: ", ex);
                        }


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

    private void fetchCharactersUrls(Book book) {
        mCharacterUrlList = book.getCharacters();

        if (mCharacterUrlList == null || mCharacterUrlList.length == 0) {
            mCharacterUrlList = getCharacterUrls(book);
        }

        notifyCharacterListLoadComplete();
    }

    private String[] getCharacterUrls(Book book) {
        Call<Book> bookCall = MyApplication.getFireAndIceClient().getFireAndIceApi().getBook(book.getUrl());
        try {
            Response<Book> response = bookCall.execute();
            if (response != null && response.isSuccessful()) {
                return response.body().getCharacters();
            }
        } catch (IOException e) {
            Log.e(TAG, "getCharacterUrls: ", e);
        }

        return new String[]{};

    }

    private void notifyCharacterListLoadComplete() {
        if (mView != null) {
            mView.onCharacterUrlListAvailable(mCharacterUrlList);
        }
    }

}
