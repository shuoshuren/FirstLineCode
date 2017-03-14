package com.example.xiao.servicetest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * 通过bindService方式启动的service
 */
public class BindService extends Service {
    private static final String TAG = "BindService";

    private DownloadBinder mDownloadBinder = new DownloadBinder();

    public BindService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate:thread:"+Thread.currentThread().getId());

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");

        return mDownloadBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "onDestroy: ");
    }

    public class DownloadBinder extends Binder {

        public void startDownload(){
            Log.i(TAG, "startDownload: ");
        }
        
        public int getProgress(){
            Log.i(TAG, "getProgress: ");
            
            return 0;
        }

    }
}
