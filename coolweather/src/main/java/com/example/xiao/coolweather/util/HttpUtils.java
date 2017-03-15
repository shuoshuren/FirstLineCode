package com.example.xiao.coolweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by xiao on 2017/3/15.
 */

public class HttpUtils {

    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        httpClient.newCall(request).enqueue(callback);
    }
}
