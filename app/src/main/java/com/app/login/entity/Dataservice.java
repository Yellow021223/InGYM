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
            list.add(new ExerciseInfo(1001, R.mipmap.login_poster,"Bench press","Bench Press is a common strength training exercise primarily used to develop the pectoralis major (chest muscles), anterior deltoids (front shoulders), and triceps brachii (triceps). It is one of the key movements for measuring upper body pushing strength.",40));


        }else if (position == 1){
            list.add(new ExerciseInfo(1002, R.mipmap.login_poster,"Dumbbell Shoulder Press","Dumbbell Shoulder Press is a common shoulder exercise that primarily targets the deltoid muscles (anterior and middle heads) while also engaging the trapezius, triceps, and core muscles.",30));


        }else if (position == 2){
            list.add(new ExerciseInfo(1003, R.mipmap.login_poster,"Pull-up","Pull-up is a common upper body strength training exercise that primarily targets the back, arms, and shoulders.",35));


        }else if (position == 3){
            list.add(new ExerciseInfo(1004, R.mipmap.login_poster,"Squat","The squat is a classic compound strength training exercise that primarily targets the quadriceps, gluteus maximus, and hamstrings, while also enhancing core stability and improving lower body strength.",40));


        }else if (position == 4){
            list.add(new ExerciseInfo(1005, R.mipmap.login_poster,"Crunch","The crunch is a classic core exercise that primarily targets the rectus abdominis (upper abs) while also engaging the external and internal obliques, helping to define abdominal muscles.",30));


        }else {
            list.add(new ExerciseInfo(1006, R.mipmap.login_poster,"HIIT","HIIT (High-Intensity Interval Training) is a short-duration, high-intensity workout method that alternates between intense exercise and short rest periods. It boosts heart rate, burns fat efficiently, and improves cardiovascular endurance.",300));


        }
        return list;
    }
}
