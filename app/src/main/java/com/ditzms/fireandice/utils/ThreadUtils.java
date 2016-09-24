package com.ditzms.fireandice.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */
public class ThreadUtils {

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Fields
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private static final int CORE_POOL_SIZE = 1;
    private static final int MAXIMUM_POOL_SIZE = Integer.MAX_VALUE;
    private static final int KEEP_ALIVE_TIME_SECONDS = 30;

    private final static ThreadPoolExecutor mExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE,
            KEEP_ALIVE_TIME_SECONDS,
            TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());

    private final static Handler mHandler = new Handler(Looper.getMainLooper());


    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Constructor
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private ThreadUtils() {
        /*ignore*/
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Public methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public static void runOnUiThread(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static void runOnBackgroundThread(Runnable runnable) {
        mExecutor.execute(runnable);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Private methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

}
