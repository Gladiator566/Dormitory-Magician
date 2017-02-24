package com.example.dormitory_magician;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewsFragment extends Fragment {
    public static NewsFragment newInstance(){
        return new NewsFragment();
    }
    public NewsFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        TextView tv = (TextView)view.findViewById(R.id.news_text);
        return view;
    }
}
