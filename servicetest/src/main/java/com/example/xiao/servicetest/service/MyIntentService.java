package com.example.xiao.servicetest.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 *
 */
public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: thread="+Thread.currentThread().getId());
    }

    /**
     * 在子线程中执行，执行完毕后自动调用onDestroy
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent: thread="+Thread.currentThread().getId());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}
