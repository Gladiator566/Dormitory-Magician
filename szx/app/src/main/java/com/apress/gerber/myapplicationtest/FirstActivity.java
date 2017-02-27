package com.apress.gerber.myapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2016/11/1.
 */
public class FirstActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        //Log.d("MainActivity","onCreate execute");
        Button infor = (Button) findViewById(R.id.enter);
        infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,PagerTitleDemoActivity.class);
                startActivity(intent);
            }
        });

        Button setmenu = (Button) findViewById(R.id.setmenu);
        setmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });

        Button web_button = (Button) findViewById(R.id.web_button);
        web_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, WebActivity.class);
                startActivity(intent);
            }
        });
    }
}
