package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.ideabobo.db.DBTool;
import com.ideabobo.util.IdeaServlet;
import com.ideabobo.util.Utils;

public class ClientServlet extends IdeaServlet {
	private DBTool userdbTool = new DBTool(null, User.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		String action = request.getParameter("action");
		// 登录
		if(action.equals("login")){
			login();
		// 注册
		}else if(action.equals("register")){
			register();
		}else if(action.equals("checkUser")){
			checkUser();
		}else if(action.equals("updateUser")){
			updateUser();
		}else if(action.equals("findPasswd")){
			findPasswd();
		}else if(action.equals("updatePassword")){
			updatePassword();
		}else if(action.equals("updateUser")){
			updateUser();
		}
		
		
	}
	
	
	
	/**
	 * 登录
	 * http://win10-609070858/ClientServlet?action=login&Member_Name=66&Password=66
	 */
	public void login(){
		String username = request.getParameter("Member_Name");
		String passwd = request.getParameter("Password");
		User user = new User();
		user.Member_Name = username;
		user.Password = passwd;
		try {
			// 登录成功 返回user对象
			ArrayList<User> rl = userdbTool.select(user,false);
			if(rl!=null&&rl.size()>0){
				User user2 = rl.get(0);
				//if(user2.roletype.equals("2")){
					renderJsonToClient(rl.get(0));
				//}else{
				//	renderJsonToClient("fail");
				//}
				
			}else{
				// 返回 fail 登录不成功
				renderJsonToClient("fail");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 注册：字段可为空、客户端使用post方式
	 * 测试 http://win10-609070858/ClientServlet?action=register&Member_Name=66&Password=66&Dormitory=address&Phone_Num=13464646464&Mail=153614@qq.com
	 */
	public void register(){
		User user = (User) getByRequest(User.class,false);
		try {
			userdbTool.insert(user);
		} catch (Exception e) {
			renderJsonpString("fail");
			e.printStackTrace();
		}
		renderJsonpString("success");
	}
	
	public void checkUser(){
		User u = new User();
		u.Member_Name = request.getParameter("Member_Name");
		try {
			ArrayList rl = userdbTool.select(u,false);
			if(rl!=null&&rl.size()>0){
				renderJsonpString("fail");
			}else{
				renderJsonpString("success");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateUser(){
		User user = (User) getByRequest(User.class,false);
		try {
			userdbTool.update(user);
			renderJsonpString("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 找回密码
	public void findPasswd(){
		User u = new User();
		u.Member_Name = request.getParameter("Member_Name");
		u.Mail = request.getParameter("Mail");
		try {
			ArrayList<User> rl = userdbTool.select(u,false);
			if(rl!=null&&rl.size()>0){
				System.out.println("email:"+rl.get(0).Mail);
				Utils.senEmail("找回密码", "用户名："+rl.get(0).Member_Name+" 密码："+rl.get(0).Password, rl.get(0).Mail);
				renderJsonToClient("success");
			}else{
				renderJsonToClient("fail");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void updatePassword(){
		
		String username = request.getParameter("Member_Name");
		String passwd = request.getParameter("Password");
		
		User user = new User();
		user.Member_Name = username;
		System.out.println("passwd:"+passwd);
		try {
			ArrayList<User> rl = userdbTool.select(user,false);
			if(rl!=null&&rl.size()>0){
				User user2 = rl.get(0);
				user2.Password = passwd;
				userdbTool.update(user2);
				renderJsonToClient(user2);
			}else{
				renderJsonToClient("fail");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
