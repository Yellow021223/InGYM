package com.app.login.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.login.R;
import com.app.login.adapter.PlanListAdapter;
import com.app.login.db.HistoryDbHelper;
import com.app.login.db.PlanDbHelper;
import com.app.login.entity.ExerciseInfo;
import com.app.login.entity.PlanInfo;
import com.app.login.entity.UserInfo;

import java.util.List;


public class PlanFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private TextView total;
    private Button btn_finish;
    private PlanListAdapter mPlanListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_plan,container,false);

        //初始化控件
        recyclerView = rootView.findViewById(R.id.recyclerView);
        total = rootView.findViewById(R.id.total);
        btn_finish = rootView.findViewById(R.id.btn_finish);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化mPLanListAdapter
        mPlanListAdapter = new PlanListAdapter();
        //设置适配器
        recyclerView.setAdapter(mPlanListAdapter);

        //recyclerView点击事件
        mPlanListAdapter.setmOnItemClickListener(new PlanListAdapter.onItemClickListener() {
            @Override
            public void onPlusOnClick(PlanInfo planInfo, int position) {
                //组数加1
                PlanDbHelper.getInstance(getActivity()).updateExercise(planInfo.getPlan_id(),planInfo);
                loadData();
            }

            @Override
            public void onSubTractOnClick(PlanInfo planInfo, int position) {
                //组数减1
                PlanDbHelper.getInstance(getActivity()).subStractUpdateExercise(planInfo.getPlan_id(),planInfo);
                loadData();
            }

            @Override
            public void delOnClick(PlanInfo planInfo, int position) {


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
                                PlanDbHelper.getInstance(getActivity()).delete(planInfo.getPlan_id()+"");
                                loadData();
                            }
                        })


                        .show();



            }
        });


        //finish的点击事件
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //批量将plan里的数据添加到history里
                UserInfo userInfo = UserInfo.getsUserInfo();
                if(userInfo != null){
                    List<PlanInfo> planList = PlanDbHelper.getInstance(getActivity()).queryPlanList(userInfo.getUsername());
                    if (planList.size() == 0){
                        Toast.makeText(getActivity(), "You don't have any plan", Toast.LENGTH_SHORT).show();
                    }else {

                        View view = LayoutInflater.from(getActivity()).inflate(R.layout.finish_dialog_layout, null);

                        EditText et_date = view.findViewById(R.id.et_date);
                        EditText et_comment = view.findViewById(R.id.et_comment);
                        TextView tv_total = view.findViewById(R.id.tv_total);

                        //设置总的消耗量
                        tv_total.setText(total.getText().toString());


                        new AlertDialog.Builder(getActivity())

                                .setTitle("What would you like to say to yourself today?")
                                .setView(view)
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String date = et_date.getText().toString();
                                        String comment = et_comment.getText().toString();
                                        if(TextUtils.isEmpty(comment)|| TextUtils.isEmpty(date)){
                                            Toast.makeText(getActivity(), "Please enter the information", Toast.LENGTH_SHORT).show();
                                        }else{
                                            //生成记录
                                            HistoryDbHelper.getInstance(getActivity()).insertByAll(planList,date,comment);

                                            //清空plan
                                            for (int i=0; i < planList.size();i++){
                                                PlanDbHelper.getInstance(getActivity()).delete(planList.get(i).getPlan_id()+"");
                                            }
                                            //再次加载数据
                                            loadData();
                                            Toast.makeText(getActivity(), "Record successfully!Do a set of stretches and have a good rest!", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                })
                                .show();
                    }
                }
            }
        });

        loadData();



    }

    //合计训练的卡路里
    private void setTotalData(List<PlanInfo> list){
        int toTalCount = 0;
        for(int i = 0;i < list.size();i++){
            int price = list.get(i).getExercise_calories()*list.get(i).getExercise_count();
            toTalCount = toTalCount + price;
        }
        //设置数据
        total.setText(toTalCount+".00");
    }


    public void loadData(){

        UserInfo userInfo = UserInfo.getsUserInfo();
        if(userInfo != null){
            //获取数据
            List<PlanInfo> planList = PlanDbHelper.getInstance(getActivity()).queryPlanList(userInfo.getUsername());
            //设置数据
            mPlanListAdapter.setPlanInfoList(planList);

            //计算总的卡路里
            setTotalData(planList);
        }

    }
}