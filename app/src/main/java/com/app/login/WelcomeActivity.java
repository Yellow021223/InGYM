package com.app.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tv_countdown;
    private CountDownTimer countDownTimer;
    // 设置倒计时时长，单位为毫秒
    private long timeLeftInMillis = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //初始化控件
        tv_countdown = findViewById(R.id.tv_countdown);

        //启动倒计时
        startCountdown();


    }

    private void startCountdown() {
        countDownTimer =new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                int secondsRemaining = (int) (millisUntilFinished / 1000);
                tv_countdown.setText(secondsRemaining +" s");
            }

            @Override
            public void onFinish() {
                //跳转到登录页面（看自己逻辑想跳转哪个页面）
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                // 倒计时结束后的操作，例如跳转到主页面
                finish();

            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}