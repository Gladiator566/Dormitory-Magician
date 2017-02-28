package com.example.dormitory_magician;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NeedHelpFragment extends Fragment {
    public NeedHelpFragment(){}
    public static NeedHelpFragment newInstance() {
        return new NeedHelpFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_need_help, container, false);
    }

    
}
