package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class RegisterServlet extends HttpServlet {
	
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
		//不能用println打印，会加空格
		pw.print(flag?"exists":"");
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
		String pwd=StringUtil.parseNull(req.getParameter("pwd"));
		pwd=Md5Utils.md5(pwd);
		String rule=StringUtil.parseNull(req.getParameter("rule"));
		if("管理员".equals(rule)){
			rule="1";
		}else{
			rule="2";
		}
		
		String realName=StringUtil.parseNull(req.getParameter("realName"));
		String sexCode=StringUtil.parseNull(req.getParameter("sexCode"));
		String province=StringUtil.parseNull(req.getParameter("province"));
		String city=StringUtil.parseNull(req.getParameter("city"));
		String idTypeCode=StringUtil.parseNull(req.getParameter("idTypeCode"));
		String idCode=StringUtil.parseNull(req.getParameter("idCode"));
		String birthday=StringUtil.parseNull(req.getParameter("birthday"));
		String passenger_type=StringUtil.parseNull(req.getParameter("passenger_type"));
		String remark=StringUtil.parseNull(req.getParameter("reamrk"));		
		
		UserService us=UserService.getInstance();
		Province provinceP=new Province(-1,"-1",province);
		provinceP.setProvince(province);	
		CityService citySer=CityService.getInstance();
		String cityId=citySer.queryCityID(city);
		City cityC=new City(-1,cityId,city,provinceP);
		UserType userType=new UserType(Integer.parseInt(passenger_type),"");
		CertType certType=new CertType(Integer.parseInt(idTypeCode),"");
		SimpleDateFormat sdf =new SimpleDateFormat( "yyyy-MM-dd" );
		Date birth=null;
		try {
			birth = sdf.parse(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String ip=req.getRemoteAddr();
		User user=new User(-1,cityC,userType,certType,loginName,pwd,rule,realName,sexCode,
				idCode,birth,remark,"1",ip,null);
		boolean saveFlag=us.addUser(user);
		if(saveFlag){
			//使用重定向方法跳转到登录页面
//			System.out.println("req.getContextPath()=="+req.getContextPath());
//			resp.sendRedirect(req.getContextPath()+"/Login.jsp?loginNameLN="+loginName);
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter();
			pw.print("success");
			pw.flush();
			pw.close();
		}else if(saveFlag=false){
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter();
			pw.print("error");
			pw.flush();
			pw.close();
		}
	}
}
