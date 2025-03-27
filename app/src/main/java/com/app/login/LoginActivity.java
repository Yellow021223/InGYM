package com.app.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.login.db.UserDbHelper;
import com.app.login.entity.UserInfo;

public class LoginActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private SharedPreferences mSharedpreferences;
    private CheckBox remember;

    private boolean is_login;

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
        remember = findViewById(R.id.remember);


        //获取mSharedpreferences实例
        mSharedpreferences = getSharedPreferences("user",MODE_PRIVATE);


        //判断是否记住密码
        is_login = mSharedpreferences.getBoolean("is_login", false);
        if(is_login){
            String username = mSharedpreferences.getString("username",null);
            String password = mSharedpreferences.getString("password",null);
            et_username.setText(username);
            et_password.setText(password);
            remember.setChecked(true);
        }


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
                    UserInfo login = UserDbHelper.getInstance(LoginActivity.this).login(username);
                    if(login != null){
                        if(username.equals(login.getUsername()) && password.equals(login.getPassword())){

                            //实现记住密码
                            SharedPreferences.Editor edit = mSharedpreferences.edit();
                            edit.putBoolean("is_login",is_login);
                            edit.putString("username",username);
                            edit.putString("password",password);

                            //一定要提交
                            edit.commit();

                            //这需要修改为跳转到问卷
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            //提示登陆成功
                            Toast.makeText(LoginActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();

                        }else {
                            //提示用户名或密码错误
                            Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        //提示用户,该账号并未注册
                        Toast.makeText(LoginActivity.this, "This account is not registered", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        //remember的点击事件
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                is_login = isChecked;
            }
        });


    }
}