package com.app.login.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.login.R;
import com.app.login.entity.HistoryInfo;

import java.util.ArrayList;
import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.MyHolder> {
    private List<HistoryInfo> mHistoryInfos = new ArrayList<>();

    public void setListData(List<HistoryInfo> list){
        this.mHistoryInfos = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        //绑定数据
        HistoryInfo historyInfo = mHistoryInfos.get(position);
        //设置数据
        holder.exercise_count.setText("x"+historyInfo.getExercise_count());
        holder.exercise_img.setImageResource(historyInfo.getExercise_img());
        holder.exercise_title.setText(historyInfo.getExercise_title());
        holder.exercise_calories.setText(historyInfo.getExercise_calories()+"");


        //长安删除
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(historyInfo,position);
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mHistoryInfos.size();
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

    private onItemClickListener mOnItemClickListener;


    public void setOnItemClickListener(onItemClickListener OnItemClickListener) {
        mOnItemClickListener = OnItemClickListener;
    }

    public interface onItemClickListener{
        void onItemClick(HistoryInfo historyInfo,int position);
    }
}
