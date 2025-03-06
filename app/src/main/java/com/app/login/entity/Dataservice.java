package com.app.login.entity;

import com.app.login.R;

import java.util.ArrayList;
import java.util.List;

public class Dataservice {

    /**
     * 模拟提供数据
     */


    public static List<ExerciseInfo> getListData(int position){
        List<ExerciseInfo> list = new ArrayList<>();
        if (position == 0){
            list.add(new ExerciseInfo(0, R.mipmap.login_poster,"Bench press","Bench Press is a common strength training exercise primarily used to develop the pectoralis major (chest muscles), anterior deltoids (front shoulders), and triceps brachii (triceps). It is one of the key movements for measuring upper body pushing strength.",200));


        }else if (position == 1){
            list.add(new ExerciseInfo(0, R.mipmap.login_poster,"Pull-up","Pull-up is a common upper body strength training exercise that primarily targets the back, arms, and shoulders.",180));


        }else if (position == 2){


        }else if (position == 3){


        }else if (position == 4){


        }else {


        }
        return list;
    }
}
