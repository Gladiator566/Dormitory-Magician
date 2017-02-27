package com.apress.gerber.myapplicationtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */
public class TestAdapter extends ArrayAdapter<Test> {
    private int resourceId;
    public TestAdapter(Context context, int textViewResourceId, List<Test> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Test test = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.testImage = (ImageView) view.findViewById(R.id.test_image);
            viewHolder.testName = (TextView) view.findViewById(R.id.test_name);
            view.setTag(viewHolder);//将ViewHolder存储在View中
        }else{
            view = convertView;//之前的缓存进行重用
            viewHolder = (ViewHolder) view.getTag();//重新获取ViewHolder
        }
        ImageView testImage = (ImageView) view.findViewById(R.id.test_image);
        TextView testName = (TextView) view.findViewById(R.id.test_name);
        testImage.setImageResource(test.getImageID());
        testName.setText(test.getName());
        return view;
    }

    //使之不会每次都findViewById
    class ViewHolder {
        ImageView testImage;
        TextView testName;
    }
}
