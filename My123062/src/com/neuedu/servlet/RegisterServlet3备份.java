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
import com.neuedu.util.StringUtil;

public class RegisterServlet3备份 extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		doPost(req,resp);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		req.setCharacterEncoding("GB2312");
		/*User user=new User();
		String pwd2=StringUtil.parseNull(req.getParameter("pwd2"));
		String errorMag=validate(req,user,pwd2);
		String e=null;
		if(null==errorMag){
			UserService us=UserService.getInstance();
			if(us.addUser(user)){
				e="用户信息导入数据库成功！";
			}
			errorMag="注册成功！";
		}*/
		String errorMag="注册成功！";
		
		//获取表单参数
		String loginName=StringUtil.parseNull(req.getParameter("loginName"));
		String pwd=StringUtil.parseNull(req.getParameter("pwd"));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String ip=req.getRemoteAddr();
/*public User(int id, City city, UserType userType, CertType certType, String userName,
 *  String password,String rule,String realName, String sex, String cert, Date date, 
 *  String content, String status,  String loginIp,String imagePath)*/	
		User user=new User(-1,cityC,userType,certType,loginName,pwd,"2",realName,sexCode,
				idCode,birth,remark,"1",ip,null);
		
		boolean saveFlag=us.addUser(user);
		String tip="注册数据库成功！";
		if(saveFlag=false){
			tip="注册数据库失败！";
		}
		//成功给予提醒
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		pw.println("<html><title>注册提示信息</title><body>");
		pw.println("<p color='red'>"+errorMag+"</p><script>alert("+errorMag+");</script>");
		pw.println("<p color='red'>"+tip+"</p>");
		pw.println("</body><html>");
		pw.flush();
		pw.close();
	}
	
	
	
	private String validate(HttpServletRequest req,User user,String pwd2){
		String errorMag=null;
		String userName=user.getUserName();
		String regex="[a-zA-Z0-9_](6,30)";
		if(null==userName||userName.equalsIgnoreCase("")){
			errorMag="请输入用户名！";
		}else if(!(userName.length()>6&&userName.length()<30)){
			errorMag="用户名长度在6到30位之间！";
		}else if(userName.matches(regex)){
			errorMag="用户名只能由字母、数字、_组成！";
		}else if(null==user.getPassword()||user.getPassword().equalsIgnoreCase("")){
			errorMag="请输入密码！";
		}else if(!user.getPassword().equals(pwd2)){
			errorMag="两次密码不相同！";
		}else if(null==user.getRealName()||user.getRealName().equalsIgnoreCase("")){
			errorMag="请输入真实姓名！";
		}else if(null==user.getCity().getCity()||user.getCity().getCity().equalsIgnoreCase("")){
			errorMag="请选择所在城市！";
		}else if(null==user.getCertType().getContent()||user.getCertType().getContent().equalsIgnoreCase("null")){
			errorMag="请选择证件类型！";
		}else if(null==user.getCert()||user.getCert().equalsIgnoreCase("")){
			errorMag="请输入证件号码！";
		}else if(null==user.getBirthday()||user.getBirthday().toString().equalsIgnoreCase("")){
			errorMag="请输入出生日期！";
		}
		return errorMag;
	}
	
	
}
