package com.example.dormitory_magician;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SignUpActivity extends AppCompatActivity {

    private Spinner departmentSpinner;
    private Spinner dormitorySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        departmentSpinner = (Spinner) findViewById(R.id.spinner_department);
        dormitorySpinner = (Spinner) findViewById(R.id.spinner_dormitory);
        //显示的数组
        final String arr[] = new String[]{
                "工学部",
                "信息学部",
                "医学部",
                "文理学部",
        };
        final String arr2[] = new String[]{
                "9", "10", "11", "12", "13"
        };
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr2);
        //设置显示的数据
        departmentSpinner.setAdapter(arrayAdapter);
        dormitorySpinner.setAdapter(arrayAdapter2);
    }
}
