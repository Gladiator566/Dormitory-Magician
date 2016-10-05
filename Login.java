package com.example.callCar;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.*;
import android.widget.*;

public class Login extends Activity {
    private Button mLoginBtn=null;
    private Button mRegisterBtn=null;
    private Button mPassenger=null;
    private Button mDriver=null;
    private EditText mUser=null;
    private EditText mPwd=null;
    private TextView mHintText=null;
    private CheckBox mRMkey=null;
    private CheckBox mAutoLogin=null;
    private PopupWindow mPopupWindow=null;
    private static String username=null;
    private static String password=null;
    private static int PASSENGER=0;
    private static int DRIVER=0;

    private Vibrator mVibrator;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mLoginBtn=(Button)findViewById(R.id.Login);
        mRegisterBtn=(Button)findViewById(R.id.register);
        mUser=(EditText)findViewById(R.id.username);
        mPwd=(EditText)findViewById(R.id.pwd);
        mHintText=(TextView)findViewById(R.id.hintInfo);
        mRMkey=(CheckBox)findViewById(R.id.remember_key);
        mAutoLogin=(CheckBox)findViewById(R.id.auto_login);
        mVibrator=(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);

        Bundle bundle=this.getIntent().getExtras();
        PASSENGER=bundle.getInt("passenger",0);
        DRIVER=bundle.getInt("driver",0);

        SharedPreferences sp=getSharedPreferences("LoginData",MODE_PRIVATE);
        if (sp.getBoolean("ISCHECK",false)) {
            mRMkey.setChecked(true);
            mUser.setText(sp.getString("USER_NAME",""));
            mPwd.setText(sp.getString("PASSWORD",""));
            if (sp.getBoolean("AUTO_ISCHECK",false)){
                mAutoLogin.setChecked(true);
                setupNewActivity();
            }
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {    //注册按钮
            @Override
            public void onClick(View v) {
                showPopupWindow(Login.this,v);
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {       //登录按钮
            @Override
            public void onClick(View v) {
                String userNameValue=mUser.getText().toString();
                String passwordValue=mPwd.getText().toString();
                username=sp.getString("USER_NAME","");
                password=sp.getString("PASSWORD","");
                if (userNameValue.equals(username)&&passwordValue.equals(password))
                {
                    Toast.makeText(getApplicationContext(),"login successful",Toast.LENGTH_SHORT).show();
                    if (mRMkey.isChecked())
                    {
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("USER_NAME",userNameValue);
                        editor.putString("PASSWORD",passwordValue);
                        editor.apply();
                    }
                    long[] pattern={0,500};
                    mVibrator.vibrate(pattern,-1);
                    setupNewActivity();
                }
                else {
                    mHintText.setText("error，please login again");
                }
            }
        });
        mRMkey.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {    //记住密码
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mRMkey.isChecked())
                    sp.edit().putBoolean("ISCHECK",true).apply();
                else
                    sp.edit().putBoolean("ISCHECK",false).apply();
            }
        });
        mAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {    //自动登录
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mAutoLogin.isChecked())
                    sp.edit().putBoolean("AUTO_ISCHECK",true).apply();
                else
                    sp.edit().putBoolean("AUTO_ISCHECK",false).apply();
            }
        });

    }
    public void backgroundAlpha(float bgAlpha)//设置窗口透明度
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    public void  showPopupWindow(Context context,View parent)//显示弹窗，选择司机或乘客
    {
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.choose_role,null);
        mPopupWindow=new PopupWindow(view,800,1000,true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0xff000000));
        backgroundAlpha(100);
        mPopupWindow.showAtLocation(parent, Gravity.CENTER,0,0);
        mPassenger=(Button)view.findViewById(R.id.passenger);
        mDriver=(Button)view.findViewById(R.id.driver);
        mPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                Intent passengerIntent=new Intent(Login.this,PassengerRegister.class);
                startActivity(passengerIntent);
            }
        });
        mDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                Intent driverIntent =new Intent(Login.this,DriverRegister.class);
                startActivity(driverIntent);
            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1);
            }
        });
    }

    private void setupNewActivity(){                            //登录按钮，启动主程序
        Intent newActivityIntent=new Intent(this,CallCar.class);
        startActivity(newActivityIntent);
    }
}
