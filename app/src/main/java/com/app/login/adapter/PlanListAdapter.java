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
import com.app.login.entity.ExerciseInfo;
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
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {

        //绑定数据
        PlanInfo planInfo = mPlanInfoList.get(position);
        holder.exercise_img.setImageResource(planInfo.getExercise_img());
        holder.exercise_title.setText(planInfo.getExercise_title());
        holder.exercise_calories.setText(planInfo.getExercise_calories()+"");
        holder.exercise_count.setText(planInfo.getExercise_count()+"");


        //设置点击事件
        holder.btn_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onSubTractOnClick(planInfo,position);

                }

            }
        });

        holder.btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onPlusOnClick(planInfo,position);
                }


            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null ){
                    mOnItemClickListener.delOnClick(planInfo,position);
                }
                return true;
            }
        });





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
        TextView btn_subtract;
        TextView btn_plus;


        public MyHolder(@NonNull View itemView) {
            super(itemView);

            exercise_img = itemView.findViewById(R.id.exercise_img);
            exercise_title = itemView.findViewById(R.id.exercise_title);
            exercise_calories = itemView.findViewById(R.id.exercise_calories);
            exercise_count = itemView.findViewById(R.id.exercise_count);
            btn_subtract = itemView.findViewById(R.id.btn_subtract);
            btn_plus = itemView.findViewById(R.id.btn_plus);

        }
    }


    private onItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(onItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface onItemClickListener{
        void onPlusOnClick(PlanInfo planInfo,int position);
        void onSubTractOnClick(PlanInfo planInfo,int position);
        void delOnClick(PlanInfo planInfo,int position);
    }
}
