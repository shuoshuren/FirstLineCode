package com.example.xiao.coolweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.example.xiao.coolweather.gson.Weather;
import com.example.xiao.coolweather.util.HttpUtils;
import com.example.xiao.coolweather.util.PreferencesUtil;
import com.example.xiao.coolweather.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 自动更新服务
 */
public class AutoUpdateService extends Service {


    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        updateWeather();
        updateBingPic();

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 8 * 60 * 60 * 1000;
        long targetAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, targetAtTime, pi);


        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 更新天气
     */
    private void updateWeather() {

        String weatherString = PreferencesUtil.getWeatherInfo(this,null);
        if (weatherString != null) {
            Weather weather = Utility.handleWeatherResponse(weatherString);
            String weatherId = weather.basic.weatherId;
            requestWeather(weatherId);

        }

    }

    /**
     * 更新背景图
     */
    private void updateBingPic() {

        String address = "http://guolin.tech/api/bing_pic";
        HttpUtils.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic = response.body().string();
                PreferencesUtil.setBingPicInfo(AutoUpdateService.this,bingPic);
            }
        });

    }


    /**
     * 根据天气id请求城市天气信息
     *
     * @param weatherId
     */
    private void requestWeather(String weatherId) {
        String address = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=d8d79a1a98064292bf626ba84caed267";
        HttpUtils.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                Weather weather = Utility.handleWeatherResponse(responseText);

                if (weather != null && "ok".equals(weather.status)) {
                    PreferencesUtil.setWeatherInfo(AutoUpdateService.this,responseText);
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

        });

    }
}
