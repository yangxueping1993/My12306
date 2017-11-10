package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.business.service.UserService;
import com.neuedu.util.StringUtil;

public class LoginServlet2 extends HttpServlet {
	
	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		doPost(req,resp);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String userName=StringUtil.parseNull(req.getParameter("userNameL"));
		String pwd=StringUtil.parseNull(req.getParameter("pwdL"));
		UserService us=UserService.getInstance();
		String meg=null;
		/*if(){
			meg="登录成功！";
		}else{
			meg="登录名或密码输入有误！";
		}*/
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		pw.println("<html><title>登录提示信息</title><body>");
		pw.println("<p color='red'>"+meg+"</p>");
		pw.println("</body></html>");
		pw.flush();
		pw.close();
	}
}
