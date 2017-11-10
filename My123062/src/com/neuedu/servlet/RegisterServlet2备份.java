package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.business.service.UserService;
import com.neuedu.domain.CertType;
import com.neuedu.domain.City;
import com.neuedu.domain.Province;
import com.neuedu.domain.User;
import com.neuedu.domain.UserType;
import com.neuedu.util.StringUtil;

public class RegisterServlet2备份 extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		doPost(req,resp);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		req.setCharacterEncoding("GB2312");
		User user=new User();
		String pwd2=StringUtil.parseNull(req.getParameter("pwd2"));
		populate(req,user,pwd2);
		String errorMag=validate(req,user,pwd2);
		String e=null;
		if(null==errorMag){
			UserService us=UserService.getInstance();
			if(us.addUser(user)){
				e="用户信息导入数据库成功！";
			}
			errorMag="注册成功！";
		}
		
		
		/*String loginName=StringUtil.parseNull(req.getParameter("loginName"));
		System.out.println("loginName=="+loginName);
		String pwd=StringUtil.parseNull(req.getParameter("pwd"));
		System.out.println("pwd=="+pwd);
		String pwd2=StringUtil.parseNull(req.getParameter("pwd2"));
		System.out.println("pwd2=="+pwd2);
		String realName=StringUtil.parseNull(req.getParameter("realName"));
		System.out.println("realName=="+realName);
		String sexCode=StringUtil.parseNull(req.getParameter("sexCode"));
		System.out.println("sexCode=="+sexCode);
		String province=StringUtil.parseNull(req.getParameter("province"));
		System.out.println("province=="+province);
		String city=StringUtil.parseNull(req.getParameter("city"));
		System.out.println("city=="+city);
		String idTypeCode=StringUtil.parseNull(req.getParameter("idTypeCode"));
		System.out.println("idTypeCode=="+idTypeCode);
		String idCode=StringUtil.parseNull(req.getParameter("idCode"));
		System.out.println("idCode=="+idCode);
		String birthday=StringUtil.parseNull(req.getParameter("birthday"));
		System.out.println("birthday=="+birthday);
		String passenger_type=StringUtil.parseNull(req.getParameter("passenger_type"));
		System.out.println("passenger_type=="+passenger_type);
		String reamrk=StringUtil.parseNull(req.getParameter("reamrk"));
		System.out.println("reamrk=="+reamrk);*/
		
		/**
		 * 对于checkbox的值传递需要考虑以下情况
		 * 1、根据名称获取的是页面中被选择的checkbox的值
		 * 2、如果使用getParameter方法，获取的是选中值中的第一个
		 * 3、如果获取全部选中的，那么使用getParameterValues；
		 * 4、如果checkbox没有一个被选中，那么reg.getParameterValues方法返回null，需要进行空指针处理
		 */
//		String people1=req.getParameter("people");
//		System.out.println("people1=="+people1);
		
		String[] people=StringUtil.parseNullArray(req,"people");
		String people22=StringUtil.parseNull(req.getParameter("people"));
		people22=StringUtil.parseNull(req,"people");
		
		for(String i:people){
			System.out.println("people2=="+i);
		}
//		
//		for(int i=0;i<people.length;i++){
//			System.out.println("people3=="+people[i]);
//		}
		
		//把获取的参数信息，调用service和dao完成数据保存
		
		//成功给予提醒
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		pw.println("<html><title>注册提示信息</title><body>");
		pw.println("<p color='red'>"+errorMag+"</p><script>alert("+errorMag+");</script>");
		pw.println("<p color='red'>"+e+"</p>");
		pw.println("</body><html>");
		pw.flush();
		pw.close();
	}
	
	/**
	 * 服务端验证输入信息
	 */
	public void populate(HttpServletRequest req,User user,String pwd2){
		String ip=req.getRemoteAddr();
		
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
		String reamrk=StringUtil.parseNull(req.getParameter("reamrk"));
		
		//创建User对象
/*public User(int id, City city, UserType userType, CertType certType, String userName, String password,
 *  String rule,String realName, String sex, String cert, Date date, String content, String status, 
 *  String loginIp,String imagePath)*/
		user.setUserName(loginName);
		user.setPassword(pwd);
		user.setRealName(realName);
		user.setSex(sexCode);
		user.setCert(idCode);
		
		SimpleDateFormat sdf =new SimpleDateFormat( "yyyy-MM-dd" );
		Date date=null;
		try {
			date=sdf.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setBirthday(date);
		
		user.setContent(reamrk);
		
		//public City(int id, String cityId, String city, Province province)
		City cityC=new City();
		cityC.setCity(city);
		//public Province(int id, String provinceId, String province)
		Province provinceP=new Province();
		provinceP.setProvince(province);
		cityC.setProvince(provinceP);
		user.setCity(cityC);
		
		//public CertType(int id, String content)
		CertType certType=new CertType();
		certType.setContent(idTypeCode);
		user.setCertType(certType);
		
		//public UserType(int id, String content) 
		UserType userType=new UserType();
		userType.setContent(passenger_type);
		user.setUserType(userType);
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
