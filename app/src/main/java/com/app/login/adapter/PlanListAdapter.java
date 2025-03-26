package com.app.login.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.login.R;
import com.app.login.entity.PlanInfo;

import java.util.ArrayList;
import java.util.List;

public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.MyHolder> {

    private List<PlanInfo> mPlanInfoList = new ArrayList<>();


    
    public void setPlanInfoList(List<PlanInfo> list){
        this.mPlanInfoList = list;
        //这句话不能少！
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_list_item, null);
        return new MyHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        //绑定数据
        PlanInfo planInfo = mPlanInfoList.get(position);
        holder.exercise_img.setImageResource(planInfo.getExercise_img());
        holder.exercise_title.setText(planInfo.getExercise_title());
        holder.exercise_calories.setText(planInfo.getExercise_calories()+"");
        holder.exercise_count.setText(planInfo.getExercise_count()+"");
    }

    @Override
    public int getItemCount() {
        return mPlanInfoList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        ImageView exercise_img;
        TextView exercise_title;
        TextView exercise_calories;
        TextView exercise_count;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            exercise_img = itemView.findViewById(R.id.exercise_img);
            exercise_title = itemView.findViewById(R.id.exercise_title);
            exercise_calories = itemView.findViewById(R.id.exercise_calories);
            exercise_count = itemView.findViewById(R.id.exercise_count);
        }
    }
}
