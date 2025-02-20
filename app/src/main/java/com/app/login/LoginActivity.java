package com.app.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private SharedPreferences mSharedpreferences;

    // 适配窗口边距
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //获取mSharedpreferences
        mSharedpreferences = getSharedPreferences("user",MODE_PRIVATE);


        //初始化控件
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);


        //点击注册
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //跳转到注册页面
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


        //登录功能
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                }else {
                    String name = mSharedpreferences.getString("username",null);
                    String pwd = mSharedpreferences.getString("password",null);
                    if(username.equals(name) && password.equals(pwd)){
                        //跳转到问卷
                        Intent intent = new Intent(LoginActivity.this,SurveyActivity.class);
                        startActivity(intent);
                        //提示登陆成功
                        Toast.makeText(LoginActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();

                    }else {
                        //提示用户名或密码错误
                        Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }
}