package com.example.dormitory_magician;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LifeCycleFragment extends Fragment {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lifecycle_fragment, container, false);
        TextView tv = (TextView)view.findViewById(R.id.life_cycle_text);
        return view;
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
