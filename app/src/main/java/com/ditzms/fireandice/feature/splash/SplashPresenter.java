package com.ditzms.fireandice.feature.splash;

import android.util.Log;

import com.ditzms.fireandice.utils.ThreadUtils;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */

class SplashPresenter implements ISplashPresenter {

    private static final String TAG = "SplashPresenter";
    private static final int SLEEP_TIME_MILLIS = 5000;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Fields
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private ISplashView mView;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Constructor
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public SplashPresenter() {

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Public methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void attachView(ISplashView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void loadInitialData() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                loadData();
                if (mView != null) {
                    mView.onLoadFinished();
                }
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Private methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This is a stub method that calls {@link Thread#sleep(long)} for
     * {@link #SLEEP_TIME_MILLIS} milliseconds
     */
    private void loadData() {
        try {
            Thread.sleep(SLEEP_TIME_MILLIS);
        } catch (InterruptedException ex) {
            Log.e(TAG, "loadData: ", ex);
        }
    }

}
