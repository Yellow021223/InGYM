package com.app.login.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.login.R;
import com.app.login.adapter.PlanListAdapter;
import com.app.login.db.PlanDbHelper;
import com.app.login.entity.PlanInfo;

import java.util.List;


public class PlanFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private PlanListAdapter mPlanListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_plan,container,false);

        //初始化控件
        recyclerView = rootView.findViewById(R.id.recyclerView);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化mPLanListAdapter
        mPlanListAdapter = new PlanListAdapter();
        //设置适配器
        recyclerView.setAdapter(mPlanListAdapter);
        //获取数据
        List<PlanInfo> planList = PlanDbHelper.getInstance(getActivity()).queryPlanList("DuanLi");
        //设置数据
        mPlanListAdapter.setPlanInfoList(planList);
    }
}