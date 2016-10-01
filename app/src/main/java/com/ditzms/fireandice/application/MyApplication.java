package com.ditzms.fireandice.application;

import android.app.Application;

import com.ditzms.fireandice.data.controller.BookListController;
import com.ditzms.fireandice.data.controller.IBookListController;
import com.ditzms.fireandice.data.network.FireAndIceClient;
import com.ditzms.fireandice.data.repository.BookListRepository;
import com.orm.SugarContext;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */
public class MyApplication extends Application {

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Fields
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private static IBookListController sBookListController;
    private static FireAndIceClient sFireAndIceClient;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Public methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public static IBookListController getBookListController() {
        return sBookListController;
    }

    public static FireAndIceClient getFireAndIceClient() {
        return sFireAndIceClient;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
        initializeBookListController();
        initializeFireAndIceClient();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Private methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void initializeBookListController() {
        sBookListController = new BookListController(new BookListRepository());
    }

    private void initializeFireAndIceClient() {
        sFireAndIceClient = new FireAndIceClient();
    }
}
