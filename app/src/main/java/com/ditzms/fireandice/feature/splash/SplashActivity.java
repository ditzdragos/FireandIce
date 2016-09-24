package com.ditzms.fireandice.feature.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ditzms.fireandice.R;
import com.ditzms.fireandice.feature.booklist.BookListActivity;
import com.ditzms.fireandice.utils.ThreadUtils;

public class SplashActivity extends AppCompatActivity implements ISplashView {

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Fields
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private ISplashPresenter mPresenter;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Public methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mPresenter = new SplashPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attachView(this);
        mPresenter.loadInitialData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.detachView();
    }

    @Override
    public void onLoadFinished() {
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Intent intent = BookListActivity.createIntent(SplashActivity.this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }
}
