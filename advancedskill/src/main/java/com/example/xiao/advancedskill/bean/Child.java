package com.example.xiao.advancedskill.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiao on 2017/3/15.
 */

public class Child implements Parcelable {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);

    }

    public static final Parcelable.Creator<Child> CREATOR = new Parcelable.Creator<Child>(){
        @Override
        public Child createFromParcel(Parcel source) {
            Child child = new Child();
            child.setAge(source.readInt());
            child.setName(source.readString());
            return child;
        }

        @Override
        public Child[] newArray(int size) {
            return new Child[size];
        }
    };
}
