package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Activity;
import com.ideabobo.db.DBTool;
import com.ideabobo.db.Page;
import com.ideabobo.util.IdeaServlet;

/**
 * 活动增删改查
 */
public class ActivityServlet extends IdeaServlet {
	private DBTool dbTool = new DBTool(null, Activity.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		String action = request.getParameter("action");
		if(action == null){
			String pageNo = (String) this.request.getParameter("page");
			String pageSizes = (String) this.request.getParameter("rows");
			Page page = new Page();
			if (pageNo == null) {
				page.pageSize = 50;
				page.pageNo = 1;
			} else {
				page.pageSize = (Integer.parseInt(pageSizes));
				int pn = (Integer.parseInt(pageNo));
				page.pageNo = pn;
				if(pn<1){
					page.pageNo = 1;
				}
				
			}
			
			
			Activity model = new Activity();
			boolean flag = false;
			String username =  request.getParameter("searchStr");
			if(username != null && !username.equals("")){
				model.Activity_Desc = username;
				flag = true;
			}
			
			if(flag){
				page.model = model;
			}
			try {
				page = dbTool.getByPage(page);
				renderJson(page);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		/**
		 * 活动添加
		 * http://win10-609070858/ActivityServlet?action=add&Member_Name=66&Activity_Date=2017-02-25&Site=l%E7%A4%BC%E5%A0%82&type=%E4%BD%93%E8%82%B2%E8%BF%90%E5%8A%A8&Activity_Desc=%E6%8F%8F%E8%BF%B0
		 * 
		 */
		}else if(action.equals("add")){
			Activity model = (Activity) getByRequest(Activity.class,false);
			try {
				dbTool.insert(model);
				render("success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		/**
		 * 活动修改
		 * http://win10-609070858/ActivityServlet?action=edit&Activity_ID=1&Member_Name=66&Activity_Date=2017-02-25&Site=l%E7%A4%BC%E5%A0%82&type=%E4%BD%93%E8%82%B2%E8%BF%90%E5%8A%A8&Activity_Desc=%E6%8F%8F%E8%BF%B0
		 */
		}else if(action.equals("edit")){
			Activity model = (Activity) getByRequest(Activity.class,false);
			try {
				dbTool.update(model);
				render("success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		/**
		 * 活动删除
		 * http://win10-609070858/ActivityServlet?action=delete&Activity_ID=1
		 */
		}else if(action.equals("delete")){
			String id = request.getParameter("Activity_ID");
			try {
				int c = dbTool.delete(Integer.parseInt(id));
				renderInfoString("success");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
