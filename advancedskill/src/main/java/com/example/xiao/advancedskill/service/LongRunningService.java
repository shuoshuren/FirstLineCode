package com.example.xiao.advancedskill.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import static android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP;

/**
 *使用Alarm进行定时任务
 */
public class LongRunningService extends Service {
    public LongRunningService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //执行具体的逻辑
            }
        }).start();

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int aHour = 60*60*1000;//1小时的毫秒数
        long targetAtTime = SystemClock.elapsedRealtime()+aHour;
        Intent i = new Intent(this,LongRunningService.class);
        PendingIntent pi = PendingIntent.getService(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,targetAtTime,pi);






        return super.onStartCommand(intent, flags, startId);
    }
}
