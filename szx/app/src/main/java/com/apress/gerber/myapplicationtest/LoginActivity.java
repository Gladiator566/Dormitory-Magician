package com.apress.gerber.myapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LoginActivity extends Activity implements OnClickListener {

    private Handler handler;
    private EditText username;
    private EditText password;
    private Button login;
    private Button register;
    private String url = "http://localhost:8080";
    private String url1 = "http://130.234.1.190/Test/json_array.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        try {
                            String res = msg.getData().getString("res");
                            JSONObject result = new JSONObject(res);
                            String success = result.getString("Member_Name");
                            Toast.makeText(LoginActivity.this, res + ":\n" +result.toString(), Toast.LENGTH_LONG).show();
                            // TODO Auto-generated catch block
                            if(success != null){
                                Intent intent = new Intent(LoginActivity.this, FirstActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this, "输入的用户名或密码有错", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;

                    default:
                        break;
                }
            }
        };

        Bundle bundle = this.getIntent().getExtras();

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        if(bundle != null){
            username.setText(bundle.getString("empNo"));
            password.setText(bundle.getString("pass"));
        }
    }
    //////////////
    public void onClick(View v){
        int id = v.getId();
        switch(id){
            //登陆按钮点击事件
            case R.id.login:
                new Thread(){
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        super.run();
                        try {


                            JSONObject json = new JSONObject();
                            json.put("Member_Name", username.getText().toString().trim());
                            json.put("Password", password.getText().toString().trim());
                            json.put("action","login");
                            //						httpPostMethod(json);

                            HttpUtils.httpPostMethod(url, json, handler);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            Log.d("json", "解析JSON出错");
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                       // } catch (ClientProtocolException e) {
                            // TODO Auto-generated catch block
                       //     e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            //注册按钮点击事件
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}

