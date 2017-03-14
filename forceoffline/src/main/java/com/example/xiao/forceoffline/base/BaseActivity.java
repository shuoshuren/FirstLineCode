package com.example.xiao.forceoffline.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.xiao.forceoffline.Constants;

/**
 * Created by xiao on 2017/3/9.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    private BroadcastReceiver receiver;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate: "+getClass().getSimpleName());
        ActivityController.addActivity(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.FORCE_OFF_LINE);
        receiver = new ForceOffLineReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(receiver !=null){
            LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: "+getClass().getSimpleName());
        ActivityController.removeActivity(this);
    }

    public  void startActivityAction(Class<? extends Activity> targetActivityClass){
        Intent intent = new Intent(this,targetActivityClass);
        startActivity(intent);
    }

    public static void startActivityAction(Activity activity,Class<? extends Activity> targetActivityClass){
        Intent intent = new Intent(activity,targetActivityClass);
        activity.startActivity(intent);
    }

    public class ForceOffLineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(Constants.FORCE_OFF_LINE.equals(action)){
                AlertDialog.Builder alert = new AlertDialog.Builder(BaseActivity.this);
                alert.setTitle("账号异常");
                alert.setMessage("你在异地登录，请重新登录");
                alert.setCancelable(false);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityController.finishAll();


                    }
                });
                alert.show();
            }
        }
    }
}
