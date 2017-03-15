package com.example.xiao.coolweather.util;

import android.text.TextUtils;

import com.example.xiao.coolweather.db.City;
import com.example.xiao.coolweather.db.County;
import com.example.xiao.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by xiao on 2017/3/15.
 */

public class Utility {


    /**
     * 解析和处理服务器返回的省的数据
     * @param response
     * @return
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObj = allProvinces.getJSONObject(i);
                    Province province  = new Province();
                    province.setProvinceCode(provinceObj.getInt("id"));
                    province.setProvinceName(provinceObj.getString("name"));
                    province.save();
                }
                return true;
            }catch (Exception e){

                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    /**
     * 解析和处理服务器返回的市的数据
     * @param response
     * @param provinceId
     * @return
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObj = allCities.getJSONObject(i);
                    City city  = new City();
                    city.setCityCode(cityObj.getInt("id"));
                    city.setCityName(cityObj.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (Exception e){

                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    /**
     * 解析和处理服务器返回的县的数据
     * @param response
     * @param cityId
     * @return
     */
    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObj = allCounties.getJSONObject(i);
                    County county  = new County();
                    county.setWeatherId(countyObj.getString("weather_id"));
                    county.setCountyName(countyObj.getString("name"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (Exception e){

                e.printStackTrace();
                return false;
            }
        }

        return false;
    }
}
