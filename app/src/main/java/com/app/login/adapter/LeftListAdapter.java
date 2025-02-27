package com.app.login.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.login.R;

import java.util.ArrayList;
import java.util.List;

public class LeftListAdapter extends RecyclerView.Adapter<LeftListAdapter.MyHolder> {
    //页面布局
    private List<String> dataList = new ArrayList<>();

    private int currentIndex = 0;


    //构造方法--传参
    public LeftListAdapter(List<String> dataList){
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_list_item,null);
        return new MyHolder(view);
    }

    @Override
    @SuppressLint("RecyclerView")
    public void onBindViewHolder(@NonNull MyHolder holder,  int position) {
        //绑定数据
        String name = dataList.get(position);
        holder.tv_name.setText(name);

        //分类的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mLeftListonClickItemListener){
                    mLeftListonClickItemListener.onItemClick(position);
                }
            }
        });


        //改变左边运动中选中的背景颜色----选中时是白色没选中的还是灰色
        if (currentIndex == position){
            holder.itemView.setBackgroundResource(R.drawable.type_selector_bg);
        }else {
            holder.itemView.setBackgroundResource(R.drawable.type_selector_normal_bg);
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        TextView tv_name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.name);
        }
    }


    private LeftListonClickItemListener mLeftListonClickItemListener;

    public void setmLeftListonClickItemListener(LeftListonClickItemListener mLeftListonClickItemListener) {
        this.mLeftListonClickItemListener = mLeftListonClickItemListener;
    }

    public  interface LeftListonClickItemListener{
        void onItemClick(int position);
    }



    public void setCurrentIndex(int position){
        this.currentIndex = position;
        notifyDataSetChanged();
    }
}
