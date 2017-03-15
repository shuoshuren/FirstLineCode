package com.example.xiao.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * 市
 * Created by xiao on 2017/3/15.
 */

public class City extends DataSupport {

    private int id;

    private String cityName;//市的名字

    private int cityCode;//市的编号

    private int provinceId; //市所属省的id号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
