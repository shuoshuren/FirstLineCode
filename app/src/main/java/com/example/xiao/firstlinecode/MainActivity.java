package com.example.xiao.firstlinecode;

import android.os.Bundle;
import android.view.View;

import com.example.xiao.firstlinecode.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnRecycleView(View view){

        startActivityAction(RecyclerActivity.class);


    }
}
