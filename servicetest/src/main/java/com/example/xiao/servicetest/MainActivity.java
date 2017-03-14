package com.example.xiao.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.xiao.servicetest.service.BindService;
import com.example.xiao.servicetest.service.ForegroundService;
import com.example.xiao.servicetest.service.MyIntentService;
import com.example.xiao.servicetest.service.MyService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStartServiceBtn;
    private Button mStopServiceBtn;

    private Button mBindServiceBtn;
    private Button mUnbindServiceBtn;

    private Button mForegroundBtn;

    private Button mIntentBtn;

    private BindService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (BindService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartServiceBtn = (Button) findViewById(R.id.start_service);
        mStopServiceBtn = (Button) findViewById(R.id.stop_service);
        mBindServiceBtn = (Button) findViewById(R.id.bind_service);
        mUnbindServiceBtn = (Button) findViewById(R.id.unbind_service);
        mForegroundBtn = (Button) findViewById(R.id.foreground_service);
        mIntentBtn = (Button) findViewById(R.id.intent_service);


        mStartServiceBtn.setOnClickListener(this);

        mStopServiceBtn.setOnClickListener(this);

        mBindServiceBtn.setOnClickListener(this);

        mUnbindServiceBtn.setOnClickListener(this);

        mForegroundBtn.setOnClickListener(this);

        mIntentBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.start_service:
                intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
                break;
            case R.id.stop_service:
                intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
                break;
            case R.id.bind_service:
                intent = new Intent(MainActivity.this, BindService.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            case R.id.foreground_service:
                intent = new Intent(MainActivity.this, ForegroundService.class);
                startService(intent);
                break;

            case R.id.intent_service:
                Log.i("xc","MainActivity--》Thread:"+Thread.currentThread().getId());
                intent = new Intent(MainActivity.this, MyIntentService.class);
                startService(intent);
                break;
        }
    }
}
