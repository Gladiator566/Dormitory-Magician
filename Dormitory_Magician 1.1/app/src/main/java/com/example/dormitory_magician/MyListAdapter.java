package com.example.dormitory_magician;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;


class MyListAdapter extends BaseAdapter{
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    private static class ViewHolder{
        TextView tv_type;
        TextView tv_people;
        TextView tv_time;
        TextView tv_place;
        Button btn_enroll;
    }

    MyListAdapter(Context context, List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount(){
        return data.size();
    }
    @Override
    public Object getItem(int position){
        return data.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView == null)
        {
            viewHolder=new ViewHolder();
            layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.lifecycle_item,null);
            viewHolder.tv_type=(TextView)convertView.findViewById(R.id.activity_show_type);
            viewHolder.tv_people=(TextView)convertView.findViewById(R.id.activity_show_people);
            viewHolder.tv_time=(TextView)convertView.findViewById(R.id.activity_show_time);
            viewHolder.tv_place=(TextView)convertView.findViewById(R.id.activity_show_place);
            viewHolder.btn_enroll=(Button)convertView.findViewById(R.id.enroll_btn);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.tv_type.setText((String)data.get(position).get("type"));
        viewHolder.tv_people.setText((String)data.get(position).get("people"));
        viewHolder.tv_time.setText((String)data.get(position).get("time"));
        viewHolder.tv_place.setText((String)data.get(position).get("place"));
        viewHolder.btn_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"报名成功",Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
