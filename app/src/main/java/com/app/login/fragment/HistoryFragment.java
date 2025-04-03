package com.app.login.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.login.R;
import com.app.login.adapter.HistoryListAdapter;
import com.app.login.db.HistoryDbHelper;
import com.app.login.entity.HistoryInfo;
import com.app.login.entity.UserInfo;

import java.util.List;


public class HistoryFragment extends Fragment {
    private View rootview;
    private RecyclerView recyclerView;
    private HistoryListAdapter mHistoryListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_history, container, false);
        //初始化控件
        recyclerView = rootview.findViewById(R.id.recyclerView);
        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化mHistoryListAdapter
        mHistoryListAdapter = new HistoryListAdapter();

        //设置HistoryListAdapter
        recyclerView.setAdapter(mHistoryListAdapter);

        //recyclerView点击事件
        mHistoryListAdapter.setOnItemClickListener(new HistoryListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(HistoryInfo historyInfo, int position) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Tips:")
                        .setMessage("Are you sure you want to delete it?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int row = HistoryDbHelper.getInstance(getActivity()).delete(historyInfo.getHistory_id() + "");
                                if (row > 0){
                                    loadData();
                                    Toast.makeText(getActivity(), "Successfully delete!", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getActivity(), "Failed to delete!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();
            }
        });

        //获取数据
        loadData();
    }

    public void  loadData(){

        UserInfo userInfo = UserInfo.getsUserInfo();
        if (userInfo != null){
            List<HistoryInfo> historyInfos = HistoryDbHelper.getInstance(getActivity()).queryHistoryListData(userInfo.getUsername());
            mHistoryListAdapter.setListData(historyInfos);
        }
    }
}