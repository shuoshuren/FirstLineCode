package com.example.xiao.coolweather.gson;

/**
 * Created by xiao on 2017/3/15.
 */

public class AQI {

    public AQICity city;

    public class AQICity{
        public String aqi;

        public String pm25;
    }
}
