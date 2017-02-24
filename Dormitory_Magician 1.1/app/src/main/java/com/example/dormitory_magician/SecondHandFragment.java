package com.example.dormitory_magician;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondHandFragment extends Fragment{
    public static SecondHandFragment newInstance(){
        return new SecondHandFragment();
    }
    public SecondHandFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.secondhand_fragment, container, false);
        TextView tv = (TextView)view.findViewById(R.id.second_hand_text);
        return view;
    }
}
