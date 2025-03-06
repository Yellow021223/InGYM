package com.app.login;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.login.entity.ExerciseInfo;

public class ExerciseDetailsActivity extends AppCompatActivity {

    private ImageView exercise_img;
    private TextView exercise_title;
    private TextView exercise_calories;
    private TextView exercise_details;

    private ExerciseInfo exerciseInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //获取传输的数据
        exerciseInfo = (ExerciseInfo) getIntent().getSerializableExtra("exerciseInfo");

        //返回
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //初始化控件
        exercise_img = findViewById(R.id.exercise_img);
        exercise_title = findViewById(R.id.exercise_title);
        exercise_calories = findViewById(R.id.exercise_calories);
        exercise_details = findViewById(R.id.exercise_details);


        //设置数据
        if (null!= exerciseInfo){
            exercise_img.setImageResource(exerciseInfo.getProduct_img());
            exercise_title.setText(exerciseInfo.getExercise_title());
            exercise_details.setText(exerciseInfo.getExercise_details());
            exercise_calories.setText(exerciseInfo.getExercise_calories()+"");
        }
    }
}