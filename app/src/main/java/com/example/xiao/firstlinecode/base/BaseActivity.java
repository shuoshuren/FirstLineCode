package com.example.xiao.firstlinecode.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by xiao on 2017/3/9.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate: "+getClass().getSimpleName());
        ActivityController.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: "+getClass().getSimpleName());
        ActivityController.removeActivity(this);
    }

    public void startActivityAction(Class<? extends Activity> targetActivityClass){
        Intent intent = new Intent(this,targetActivityClass);
        startActivity(intent);
    }
}
