package com.example.xiao.litepaltest.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by xiao on 2017/3/9.
 */

public class Category extends DataSupport{

    private int id;

    private String categoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
