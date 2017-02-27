package com.apress.gerber.myapplicationtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */
public class TestActivity extends Activity {
    private String[] list = { "个人信息","设置","关于我们","test1","test2","test3","test4","test5","test6"};
    private List<Test> testList = new ArrayList<Test>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        initTest();
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                TestActivity.this,android.R.layout.simple_list_item_1,list);
        ListView listView = (ListView) findViewById(R.id.list_view);*/
        TestAdapter adapter = new TestAdapter(TestActivity.this, R.layout.test_item, testList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        //list事件的响应
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Test test = testList.get(position);
                Toast.makeText(TestActivity.this, test.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initTest(){
        Test infor = new Test("个人信息", R.drawable.s3);
        testList.add(infor);
        Test set = new Test("设置", R.drawable.s3);
        testList.add(set);
        Test about = new Test("关于我们", R.drawable.s3);
        testList.add(about);
        Test test1 = new Test("test1", R.drawable.s3);
        testList.add(test1);
        Test test2 = new Test("test2", R.drawable.s3);
        testList.add(test2);
        Test test3 = new Test("test3", R.drawable.s3);
        testList.add(test3);
        Test test4 = new Test("test4", R.drawable.s3);
        testList.add(test4);
        Test test5 = new Test("test5", R.drawable.s3);
        testList.add(test5);
        Test test6 = new Test("test6", R.drawable.s3);
        testList.add(test6);

    }

}
