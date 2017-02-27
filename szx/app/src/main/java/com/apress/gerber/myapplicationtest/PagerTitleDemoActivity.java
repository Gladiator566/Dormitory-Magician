package com.apress.gerber.myapplicationtest;

/**
 * Created by Administrator on 2016/11/1.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

public class PagerTitleDemoActivity extends Activity {
    /** Called when the activity is first created. */
    private ViewPager mViewPager;
    private PagerTitleStrip mPagerTitleStrip;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main0);
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        mPagerTitleStrip = (PagerTitleStrip)findViewById(R.id.pagertitle);

        //将要分页显示的View装入数组中
        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.help, null);
        View view2 = mLi.inflate(R.layout.life, null);
        View view3 = mLi.inflate(R.layout.old, null);
        View view4 = mLi.inflate(R.layout.infor, null);

        //每个页面的Title数据
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        final ArrayList<String> titles = new ArrayList<String>();
        titles.add("帮我");
        titles.add("约吗");
        titles.add("旧物");
        titles.add("资讯");

        //填充ViewPager的数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager)container).removeView(views.get(position));
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }

            @Override
            public Object instantiateItem(View container, int position) {
                ((ViewPager)container).addView(views.get(position));
                return views.get(position);
            }
        };

        mViewPager.setAdapter(mPagerAdapter);



       /* Button setmenu = (Button) findViewById(R.id.setmenu);
        setmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PagerTitleDemoActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });

        Button web_button = (Button) findViewById(R.id.web_button);
        web_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PagerTitleDemoActivity.this, WebActivity.class);
                startActivity(intent);
            }
        });*/
    }
}
