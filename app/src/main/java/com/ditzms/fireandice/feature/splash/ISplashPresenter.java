package com.ditzms.fireandice.feature.splash;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */

interface ISplashPresenter {

    void attachView(ISplashView view);

    void detachView();

    void loadInitialData();

}
