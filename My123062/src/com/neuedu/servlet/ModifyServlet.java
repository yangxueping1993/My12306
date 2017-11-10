package com.neuedu.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.neuedu.business.service.CityService;
import com.neuedu.business.service.UserService;
import com.neuedu.domain.CertType;
import com.neuedu.domain.City;
import com.neuedu.domain.User;
import com.neuedu.domain.UserType;
import com.neuedu.util.Constants;
import com.neuedu.util.StringUtil;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet("/User/ModifyServlet")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=StringUtil.parseNull(request,"action");
		try {
			if("check".equals(action)){
				checkLoginName(request,response);
			}else{
				doModify(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 验证修改时的用户名是否重复
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void checkLoginName(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("check……");
		String loginName=StringUtil.parseNull(request,"loginName");
		int userID=Integer.parseInt(StringUtil.parseNull(request,"userID"));
		UserService us=UserService.getInstance();
		boolean flag=us.isExistsForUpdate(loginName, userID);
		String result="NOT EXISTS";
		if(flag){
			result="exists";
		}
		PrintWriter pw=response.getWriter();
		pw.write(result);
		pw.flush();
		pw.close();
		
	}
	
	/**
	 * 执行用户信息修改
	 * @throws Exception
	 */
	private void doModify(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("doModify……");
		/**
		 * 实现文件上传
		 * 1、把表单设置为：enctype="multipart/form-data"
		 * 2、表单的method=post
		 * 3、文件域：<input type="file">
		 */
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			DiskFileItemFactory factory=new DiskFileItemFactory();
			ServletFileUpload upload=new ServletFileUpload(factory);
			upload.setHeaderEncoding("GB2312");
			upload.setFileSizeMax(10*1024*1024);
			String userID=""; 
			String loginName="";
			String realName="";
			String sex="";
			String city="";
			String idTypeCode="";
			String cert="";
			String birthday="";
			String passengerType="";
			String content="";
			String imagePath="";
			List<FileItem> fileItems=upload.parseRequest(request);
			for(FileItem item:fileItems){
				if(item.isFormField()){
					//如果是普通的表单域，那么只需要获取数据
					//表单域
					//使用ajax请求提交，编码方式应改为UTF-8
					String name=new String(item.getFieldName().getBytes("ISO8859-1"),"GB2312");
					//对应的值
					String value=new String(item.getString().getBytes("ISO8859-1"),"GB2312");
					if("loginName".equals(name)){
						loginName=value;
					}
					if("userID".equals(name)){
						userID=value;
					}
					if("realName".equals(name)){
						realName=value;
					}
					if("sex".equals(name)){
						sex=value;
					}
					if("city".equals(name)){
						city=value;
					}
					if("idTypeCode".equals(name)){
						idTypeCode=value;
					}
					if("cert".equals(name)){
						cert=value;
					}
					if("birthday".equals(name)){
						birthday=value;
					}
					if("passenger_type".equals(name)){
						passengerType=value;
					}
					if("content".equals(name)){
						content=value;
					}
					
				}else{
					//代表文件条目
					//获取文件名（客户端上传时的文件名
					String fileName=item.getName();
					if(null!=fileName && fileName.indexOf(".")!=-1){
						//生成唯一的文件名
						String newName=new Date().getTime()+"."+
								fileName.substring(fileName.lastIndexOf(".")+1);
						//定义一个文件对象，用来保存上传的文件内容
						File fullFile=new File(getServletContext().getRealPath("/images/photo/"),
								newName);
						//把item中的文件数据，写入到服务器指定路径
						item.write(fullFile);
						imagePath=newName;
					}
					//删除临时文件
					item.delete();
				}
			}
			//1、组装User对象
			CityService citySer=CityService.getInstance();
			String cityId=citySer.queryCityID(city);
			City cityC=new City(-1,cityId,city,null);
			UserType userType=new UserType(Integer.parseInt(passengerType),"");
			CertType certType=new CertType(Integer.parseInt(idTypeCode),"");
			SimpleDateFormat sdf =new SimpleDateFormat( "yyyy-MM-dd" );
			Date birth=null;
			try {
				birth = sdf.parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String ip=request.getRemoteAddr();
			User user=new User(Integer.parseInt(userID),cityC,userType,certType,loginName,null,"2",
					realName,sex,cert,birth,content,"1",ip,imagePath);
			//2、调用service方法进行修改
			UserService us=UserService.getInstance();
			int res=us.updateUser(user);
			response.setContentType("text/html");
			//3、处理修改结果
			PrintWriter pw=response.getWriter();
			System.out.println("res"+res);
			if(res!=-1){
				//修改成功时业务逻辑
				HttpSession se=request.getSession();
				User ur=us.login(((User)se.getAttribute(Constants.userKey)).getUserName(),((User)se.getAttribute(Constants.userKey)).getPassword());
				se.setAttribute(Constants.userKey, ur);
				pw.write("<html><head><script >window.parent.location.href='UserManageInfo.jsp';</script></head></html>");
				pw.flush();
				pw.close();
			}else{
				//修改失败是业务逻辑
				pw.write("<html><title>用户信息修改提示信息</title>");
				pw.write("<body>用户信息修改失败，请稍后重试！</body></html>");
				pw.flush();
				pw.close();
			}
			
//			System.out.println("loginName="+((User)se.getAttribute(Constants.userKey)).getUserName());
//			System.out.println("Password="+((User)se.getAttribute(Constants.userKey)).getPassword());
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
