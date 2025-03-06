package com.app.login.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.login.ExerciseDetailsActivity;
import com.app.login.R;
import com.app.login.adapter.LeftListAdapter;
import com.app.login.adapter.RightListAdapter;
import com.app.login.entity.Dataservice;
import com.app.login.entity.ExerciseInfo;

import java.util.ArrayList;
import java.util.List;


public class ExerciseFragment extends Fragment {

    private View rootView;
    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;
    private LeftListAdapter mLeftListAdapter;
    private RightListAdapter mRightListAdapter;

    private List<String> leftDataList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_exercise,container,false);

        //初始化控件
        leftRecyclerView = rootView.findViewById(R.id.leftRecyclerView);
        rightRecyclerView = rootView.findViewById(R.id.rightRecyclerView);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        leftDataList.add("Chest");
        leftDataList.add("Shoulders");
        leftDataList.add("Back");
        leftDataList.add("Legs");
        leftDataList.add("Core Strength");
        leftDataList.add("Aerobics");

        mLeftListAdapter = new LeftListAdapter(leftDataList);
        leftRecyclerView.setAdapter(mLeftListAdapter);

        mRightListAdapter = new RightListAdapter();
        rightRecyclerView.setAdapter(mRightListAdapter);
        //默认加载运动的数据为第0页
        mRightListAdapter.setListData(Dataservice.getListData(0));

        //recyclerView点击事件
        mRightListAdapter.setOnItemClickListener(new RightListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(ExerciseInfo exerciseInfo, int positionn) {

                //跳转传值
                Intent intent = new Intent(getActivity(), ExerciseDetailsActivity.class);

                //intent 传递对象的时候，实体类一定要实现implements Serializable
                intent.putExtra("exerciseInfo",exerciseInfo);
                startActivity(intent);

            }
        });


        //recyclerView点击事件
        mLeftListAdapter.setmLeftListonClickItemListener(new LeftListAdapter.LeftListonClickItemListener() {
            @Override
            public void onItemClick(int position) {
                mLeftListAdapter.setCurrentIndex(position);

                //点击左侧分类切换对应的列表数据
                mRightListAdapter.setListData(Dataservice.getListData(position));
            }
        });
    }
}