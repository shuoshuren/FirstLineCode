package com.example.xiao.forceoffline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.example.xiao.forceoffline.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void forceOffLine(View view){
        Intent intent = new Intent();
        intent.setAction(Constants.FORCE_OFF_LINE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }
}
