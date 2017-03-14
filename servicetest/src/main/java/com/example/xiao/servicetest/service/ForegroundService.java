package com.example.xiao.servicetest.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.xiao.servicetest.MainActivity;
import com.example.xiao.servicetest.R;

/**
 * 前台service
 */
public class ForegroundService extends Service {
    private static final String TAG = "ForegroundService";

    public ForegroundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate:Thread:"+Thread.currentThread().getId());
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new NotificationCompat.Builder(this)//
                .setContentTitle("标题")//
                .setContentText("内容")//
                .setSmallIcon(R.mipmap.ic_launcher_round)//
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round))//
                .setWhen(System.currentTimeMillis())//
                .setContentIntent(pi)//
                .build();
        startForeground(1,notification);

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");

        throw new UnsupportedOperationException("Not yet implemented");
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
}
