package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	
	/**
	 * servlet初始化时要执行的方法
	 */
	public void init(ServletConfig conf){
		System.out.println("init"); 
	}
	
	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		doPost(req,resp);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		System.out.println("hello.."+this.toString());
		System.out.println("客户机IP："+req.getRemoteAddr());
		Enumeration<String> header=req.getParameterNames();
		while(header.hasMoreElements()){
			String name=header.nextElement();
			String val=req.getHeader(name);
			System.out.println("key="+name+";val="+val);
		}
		System.out.println("req.getContextPath()=="+req.getContextPath());
		System.out.println("req.getRequestURI()=="+req.getRequestURI());
		System.out.println("req.getMethod()=="+req.getMethod());
		System.out.println("req.getRemoteHost()=="+req.getRemoteHost());
		System.out.println("req.getLocalAddr()=="+req.getLocalAddr());
//		参数信息
//		枚举
		Enumeration<String> names=req.getParameterNames();
		while(names.hasMoreElements()){
			String name=names.nextElement();
			String val=req.getParameter(name);
			System.out.println("name="+name+",值="+val);
		}
		String name=req.getParameter("haha");
		System.out.println("=="+name);
		//设置响应编码
		resp.setCharacterEncoding("UTF-8");
		//告诉浏览器返回的内容类型
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		pw.println("<html><head>haha</head><body>");
		pw.println("<p>欢迎您，用户:"+req.getRemoteAddr()+"</p>");
		pw.println("</body></html>");
		pw.flush();
		pw.close();
	}
	
	/**
	 * servlet销毁化时要执行的方法
	 */
	public void destory(){
		
	}

}
