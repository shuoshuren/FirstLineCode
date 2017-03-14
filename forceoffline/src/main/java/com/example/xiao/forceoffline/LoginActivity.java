package com.example.xiao.forceoffline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiao.forceoffline.base.BaseActivity;

import static com.example.xiao.forceoffline.R.id.password;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText accountEt;
    private EditText passwordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEt = (EditText) findViewById(R.id.account);
        passwordEt = (EditText) findViewById(password);

    }

    public void login(View view){
        String account = accountEt.getText().toString();
        String password = passwordEt.getText().toString();

        if("admin".equals(account) && "123456".equals(password)){
            BaseActivity.startActivityAction(LoginActivity.this,MainActivity.class);
        }else{
            Toast.makeText(LoginActivity.this,"账号或密码不正确",Toast.LENGTH_SHORT).show();

        }

    }


}

