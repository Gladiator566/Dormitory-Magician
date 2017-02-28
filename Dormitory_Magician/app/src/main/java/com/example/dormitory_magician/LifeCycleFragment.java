package com.example.dormitory_magician;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;


public class LifeCycleFragment extends Fragment {
    private Context context;
    private PtrFrameLayout ptrFrameLayout;
    private FloatingActionButton fab;
    private ListView lv;
    private MyListAdapter adapter;
    private List<Map<String,Object>> dataSource;

    public static LifeCycleFragment newInstance(){
        return new LifeCycleFragment();
    }

    public LifeCycleFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lifecycle_fragment, container, false);
        ptrFrameLayout=(PtrFrameLayout) view.findViewById(R.id.ptr_frame);
        lv=(ListView)view.findViewById(R.id.activity_list);
        fab=(FloatingActionButton)view.findViewById(R.id.release_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,LifeCycleRelease.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
        initView();
        initEvent();
    }

    private List<Map<String, Object>>  initData() {
        dataSource = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Map<String, Object> map= new HashMap<>();
            map.put("type","吃喝玩乐"+i);
            map.put("people","当前人数"+i);
            map.put("time","14:00"+i);
            map.put("place","信操"+i);
            dataSource.add(map);
        }
        return dataSource;
    }

    private void initView(){
        List<Map<String, Object>> list=initData();
        adapter=new MyListAdapter(context,list);
        lv.setAdapter(adapter);
        ptrFrameLayout.setHeaderView(new MyPtrRefresher(context));
        ptrFrameLayout.setFooterView(new MyPtrRefresher(context));
        ptrFrameLayout.addPtrUIHandler(new MyPtrHandler(context,ptrFrameLayout));
    }

    private void initEvent(){
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Map<String, Object> map= new HashMap<>();
                        map.put("type","体育活动");
                        dataSource.add(map);
                        adapter.notifyDataSetChanged();
                        ptrFrameLayout.refreshComplete();
                        lv.smoothScrollToPosition(dataSource.size() - 1);
                    }
                }, 1000);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Map<String, Object> map= new HashMap<>();
                        map.put("type","约饭约炮");
                        dataSource.add(map);
                        adapter.notifyDataSetChanged();
                        ptrFrameLayout.refreshComplete();
                        lv.smoothScrollToPosition(0);
                    }
                },1000);
            }
        });
    }

    public void onBackPressed() {
        DrawerLayout drawer =(DrawerLayout)getActivity().findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent=new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }
}
