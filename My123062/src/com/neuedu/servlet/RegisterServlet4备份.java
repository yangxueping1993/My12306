package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.business.service.CityService;
import com.neuedu.business.service.UserService;
import com.neuedu.domain.CertType;
import com.neuedu.domain.City;
import com.neuedu.domain.Province;
import com.neuedu.domain.User;
import com.neuedu.domain.UserType;
import com.neuedu.util.Md5Utils;
import com.neuedu.util.StringUtil;

public class RegisterServlet4备份 extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		doPost(req,resp);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String action=StringUtil.parseNull(req,"action");
		if ("check".equals(action)) {
			this.doCheck(req, resp);
		} else {
			this.doReg(req, resp);
		}
	}
	/**
	 * 验证要注册的登录名是否存在
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void  doCheck(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String loginName=StringUtil.parseNull(req.getParameter("loginName"));
		UserService userSer=UserService.getInstance();
		boolean flag=userSer.isLoginNameExists(loginName);
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		pw.print(flag);
		pw.flush();
		pw.close();
	}
	
	/**
	 * 执行用户注册
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	
	private void doReg(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String loginName=StringUtil.parseNull(req.getParameter("loginName"));
		UserService userSer=UserService.getInstance();
		boolean flag=userSer.isLoginNameExists(loginName);
		if (flag) {
			
		}

		String errorMag="注册成功！";
		
		//获取表单参数
	   /* System.out.println("开始进行今天的判断卡！");
		String action=StringUtil.parseNull(req.getParameter("action"));
//		String action=(String) req.getAttribute("action");
		System.out.println("action=="+action);
		if("checked".equals(action)){
			System.out.println("loginName=="+loginName);
		}else if(action==null||"register".equals(action)){*/
		
		String pwd=StringUtil.parseNull(req.getParameter("pwd"));
		pwd=Md5Utils.md5(pwd);
		String realName=StringUtil.parseNull(req.getParameter("realName"));
		String sexCode=StringUtil.parseNull(req.getParameter("sexCode"));
		String province=StringUtil.parseNull(req.getParameter("province"));
		String city=StringUtil.parseNull(req.getParameter("city"));
		String idTypeCode=StringUtil.parseNull(req.getParameter("idTypeCode"));
		String idCode=StringUtil.parseNull(req.getParameter("idCode"));
		String birthday=StringUtil.parseNull(req.getParameter("birthday"));
		String passenger_type=StringUtil.parseNull(req.getParameter("passenger_type"));
		String remark=StringUtil.parseNull(req.getParameter("reamrk"));		
		
		//把获取的参数信息，调用service和dao完成数据保存
		UserService us=UserService.getInstance();
		//public Province(int id, String provinceId, String province)
		Province provinceP=new Province(-1,"-1",province);
		provinceP.setProvince(province);	
		
		CityService citySer=CityService.getInstance();
		String cityId=citySer.queryCityID(city);
		//public City(int id, String cityId, String city, Province province)
		City cityC=new City(-1,cityId,city,provinceP);
		
		//public UserType(int id, String content) 
		UserType userType=new UserType(Integer.parseInt(passenger_type),"");
		
		//public CertType(int id, String content)
		CertType certType=new CertType(Integer.parseInt(idTypeCode),"");
		
		SimpleDateFormat sdf =new SimpleDateFormat( "yyyy-MM-dd" );
		Date birth=null;
		try {
			birth = sdf.parse(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String ip=req.getRemoteAddr();
/*public User(int id, City city, UserType userType, CertType certType, String userName,
 *  String password,String rule,String realName, String sex, String cert, Date date, 
 *  String content, String status,  String loginIp,String imagePath)*/	
		User user=new User(-1,cityC,userType,certType,loginName,pwd,"2",realName,sexCode,
				idCode,birth,remark,"1",ip,null);
		/*boolean saveFlag=us.(user.getUserName());
		if(saveFlag==true){
			tip="用户名已经被注册，请重新输入！";
		}else{*/
		String tip="注册数据库成功！";
		boolean saveFlag=us.addUser(user);
		if(saveFlag){
			//使用重定向方法跳转到登录页面
			System.out.println("req.getContextPath()=="+req.getContextPath());
			                                                //               注册页面的name
			resp.sendRedirect(req.getContextPath()+"/Login.jsp?loginNameLN="+loginName);
//				resp.sendRedirect("http://www.baidu.com");
			//使用请求转发方式
			/*try {
				req.getRequestDispatcher("/Login.html").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			}*/
		}else if(saveFlag=false){
			tip="注册数据库失败！";
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter();
			pw.println("error");
			pw.flush();
			pw.close();
		}
/*//		}
		//成功给予提醒
//		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		pw.println("<html><title>注册提示信息</title><body>");
		//pw.println("<p color='red'>"+errorMag+"</p><script>alert("+errorMag+");</script>");
		pw.println("<p color='red'>"+tip+"</p>");
		pw.println("</body><html>");
		pw.flush();
		pw.close();*/
//		}
	}
	
	
	/*private boolean isLoginNameExists(String loginName,HttpServletResponse resp) throws IOException{
		String result="0";
		if(flag){
			//成功给予提醒
//			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter();
			pw.println("<html><title>注册提示信息</title><body>");
			pw.println("<p color='red'>注册失败，用户名已经存在！</p>");
			pw.println("</body><html>");
			pw.flush();
			pw.close();
			result="1";
		}
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		pw.print(result);
		pw.close();
		return flag;
	}*/
}
