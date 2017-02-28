package com.example.dormitory_magician;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class RegisterActivity extends Activity {
	EditText username;
	EditText password;
	EditText phone;
	RadioGroup sex;	
	Button register;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		findViews();
		register.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name=username.getText().toString().trim();
				String pass=password.getText().toString().trim();
				String phonestr=phone.getText().toString().trim();
				String sexstr=((RadioButton)RegisterActivity.this.findViewById(sex.getCheckedRadioButtonId())).getText().toString();
				Log.i("TAG",name+"_"+pass+"_"+phonestr+"_"+sexstr);
				UserService uService=new UserService(RegisterActivity.this);
				User user=new User();
				user.setUsername(name);
				user.setPassword(pass);
				user.setPhone(phonestr);
				user.setSex(sexstr);
				uService.register(user);
				Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
				startActivity(intent);
			}
		});
	}
	private void findViews() {
		username=(EditText) findViewById(R.id.usernameRegister);
		password=(EditText) findViewById(R.id.passwordRegister);
		phone=(EditText) findViewById(R.id.phoneRegister);
		sex=(RadioGroup) findViewById(R.id.sexRegister);
		register=(Button) findViewById(R.id.Register);
	}

}
