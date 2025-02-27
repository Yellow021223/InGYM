package com.app.login.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.login.R;
import com.app.login.adapter.LeftListAdapter;

import java.util.ArrayList;
import java.util.List;


public class ExerciseFragment extends Fragment {

    private View rootView;
    private RecyclerView leftRecyclerView;
    private LeftListAdapter mLeftListAdapter;

    private List<String> leftDataList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_exercise,container,false);

        //初始化控件
        leftRecyclerView = rootView.findViewById(R.id.leftRecyclerView);

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


        //recyclerView点击事件
        mLeftListAdapter.setmLeftListonClickItemListener(new LeftListAdapter.LeftListonClickItemListener() {
            @Override
            public void onItemClick(int position) {
                mLeftListAdapter.setCurrentIndex(position);
            }
        });
    }
}