package com.app.login.adapter;

import android.annotation.SuppressLint;
import android.hardware.display.DeviceProductInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.login.R;
import com.app.login.entity.ExerciseInfo;

import java.util.ArrayList;
import java.util.List;

public class RightListAdapter extends RecyclerView.Adapter<RightListAdapter.Myholder> {

    private List<ExerciseInfo> mExerciseInfos = new ArrayList<>();


    public void setListData(List<ExerciseInfo> list){

        this.mExerciseInfos = list;
        //一定要刷新
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_list_item, null);
        return new Myholder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Myholder holder, @SuppressLint("RecyclerView") int position) {

        //绑定数据
        ExerciseInfo exerciseInfo = mExerciseInfos.get(position);

        holder.exercise_img.setImageResource(exerciseInfo.getProduct_img());
        holder.exercise_title.setText(exerciseInfo.getExercise_title());
        holder.exercise_details.setText(exerciseInfo.getExercise_details());

        //这里一定注意，只能设置String类型
        holder.exercise_calories.setText(exerciseInfo.getExercise_calories()+"");

        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mOnItemClickListener){
                    mOnItemClickListener.onItemClick(exerciseInfo,position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mExerciseInfos.size();
    }

    static class Myholder extends RecyclerView.ViewHolder{

        ImageView exercise_img;
        TextView exercise_title;
        TextView exercise_details;
        TextView exercise_calories;



        public Myholder(@NonNull View itemView) {

            super(itemView);
            exercise_img = itemView.findViewById(R.id.exercise_img);
            exercise_title = itemView.findViewById(R.id.exercise_title);
            exercise_details = itemView.findViewById(R.id.exercise_details);
            exercise_calories = itemView.findViewById(R.id.exercise_calories);
        }
    }

    private onItemClickListener mOnItemClickListener;


    public void setOnItemClickListener(onItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface onItemClickListener{
        void onItemClick(ExerciseInfo exerciseInfo,int positionn);
    }
}
