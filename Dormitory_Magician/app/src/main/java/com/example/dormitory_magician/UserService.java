package com.example.dormitory_magician;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserService {
	private DatabaseHelper dbHelper;
	public UserService(Context context){
		dbHelper=new DatabaseHelper(context);
	}

	//登录用
	public boolean login(String username,String password){
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		String sql="select * from user where username=? and password=?";
		Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
		if(cursor.moveToFirst()==true){
			cursor.close();
			return true;
		}

		/*final String url = "http://130.234.1.190/Test/login.php";
		final String url1 = "http://130.234.1.190/Test/json_array.php";
		final String name = username;
		final String pass = password;

		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				try {
					JSONObject json = new JSONObject();
					json.put("UserName", name);
					json.put("PassWord", pass);
					//						httpPostMethod(json);
					HttpUtils.httpPostMethod(url, json,handler);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Log.d("json", "解析JSON出错");
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();*/
		return false;
	}
	//注册用
	public boolean register(User user){
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		String sql="insert into user(username,password,age,sex) values(?,?,?,?)";
		Object obj[]={user.getUsername(),user.getPassword(),user.getPhone(),user.getSex()};
		sdb.execSQL(sql, obj);	
		return true;
	}
}
