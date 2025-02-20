package com.app.login;

import      android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.app.login.fragment.ExerciseFragment;
import com.app.login.fragment.HistoryFragment;
import com.app.login.fragment.MineFragment;
import com.app.login.fragment.PlanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {

    private ExerciseFragment mExerciseFragment;
    private PlanFragment mPlanFragment;
    private HistoryFragment mHistoryFragment;
    private MineFragment mMineFragment;

    private BottomNavigationView mBottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //初始化控件
        mBottomNavigationView = findViewById(R.id.bottomNavigationView);
        //设置mBottomNavigationView点击事件
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.exercise){
                    selectedFragment(0);

                } else if (item.getItemId()==R.id.plan) {
                    selectedFragment(1);

                } else if (item.getItemId()==R.id.history) {
                    selectedFragment(2);

                } else if (item.getItemId()==R.id.mine){
                    selectedFragment(3);
                }
                return true;
            }
        });


        //默认首页选中(是一个名为selectedFragment的方法，方法体在下面）
        selectedFragment(0);

    }
    private void selectedFragment(int position){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);

        if (position == 0){
            if (mExerciseFragment ==null){
                mExerciseFragment = new ExerciseFragment();
                fragmentTransaction.add(R.id.content,mExerciseFragment);
            }else {
                fragmentTransaction.show(mExerciseFragment);
            }
        } else if (position == 1) {
            if (mPlanFragment ==null){
                mPlanFragment = new PlanFragment();
                fragmentTransaction.add(R.id.content,mPlanFragment);
            }else {
                fragmentTransaction.show(mPlanFragment);
            }
        } else if (position == 2) {
            if (mHistoryFragment ==null){
                mHistoryFragment = new HistoryFragment();
                fragmentTransaction.add(R.id.content,mHistoryFragment);
            }else {
                fragmentTransaction.show(mHistoryFragment);
            }
        } else if(position == 3){
            if (mMineFragment ==null){
                mMineFragment = new MineFragment();
                fragmentTransaction.add(R.id.content,mMineFragment);
            }else {
                fragmentTransaction.show(mMineFragment);
            }
        }

        //一定要提交
        fragmentTransaction.commit();


    }
    //隐藏已经选中的menu
    private void hideFragment(FragmentTransaction fragmentTransaction){

        if(mExerciseFragment != null){
            fragmentTransaction.hide(mExerciseFragment);
        }
        if(mPlanFragment != null){
            fragmentTransaction.hide(mPlanFragment);
        }
        if(mHistoryFragment != null){
            fragmentTransaction.hide(mHistoryFragment);
        }
        if(mMineFragment != null){
            fragmentTransaction.hide(mMineFragment);
        }

    }

}