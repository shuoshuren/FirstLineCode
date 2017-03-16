package com.example.xiao.coolweather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by xiao on 2017/3/16.
 */

public class PreferencesUtil {

    private static final String WEATHER = "weather";

    private static final String BING_PIC = "bing_pic";


    /**
     * 保存天气信息到SharedPreferences中
     * @param context
     * @param weatherInfo
     */
    public static void setWeatherInfo(Context context,String weatherInfo){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(
                context).edit();
        editor.putString(WEATHER, weatherInfo);
        editor.apply();

    }

    /**
     * 从SharedPreferences中获取天气信息
     * @param context
     * @param defaultValue
     * @return
     */
    public static String getWeatherInfo(Context context,String defaultValue){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String weatherInfo = prefs.getString(WEATHER, defaultValue);
        return weatherInfo;
    }

    /**
     * 保存每日一图到SharedPreferences中
     * @param context
     * @param info
     */
    public static void setBingPicInfo(Context context,String info){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(
                context).edit();
        editor.putString(BING_PIC, info);
        editor.apply();
    }

    /**
     * 从SharedPreferences中获取每日一图
     * @param context
     * @param defaultValue
     * @return
     */
    public static String getBingPicInfo(Context context,String defaultValue){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String bingPicInfo = prefs.getString(BING_PIC, defaultValue);
        return bingPicInfo;
    }
}
