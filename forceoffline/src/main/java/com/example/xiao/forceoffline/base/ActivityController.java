package com.example.xiao.forceoffline.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiao on 2017/3/9.
 */

public class ActivityController {

    private static List<Activity> activityList = new ArrayList<>();


    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    public static void finishAll(){
        for(Activity activity:activityList){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }

}
