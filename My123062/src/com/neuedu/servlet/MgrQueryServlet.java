package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.neuedu.business.service.CityService;
import com.neuedu.business.service.UserService;
import com.neuedu.domain.CertType;
import com.neuedu.domain.City;
import com.neuedu.domain.User;
import com.neuedu.domain.UserType;
import com.neuedu.util.Md5Utils;
import com.neuedu.util.PageBean;
import com.neuedu.util.StringUtil;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Servlet implementation class QueryServlet
 */
@WebServlet("/Admin/QueryServlet")
public class MgrQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MgrQueryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		String action=StringUtil.parseNull(req, "action");
		if("delete".equals(action)){
			doDelete(req,response);
		}else if("query".equals(action)){
			doPageQuery(req,response);
		}else if("queryAjax".equals(action)){
			doPageQueryAjax(req,response);
		}else if("excel".equals(action)){
			doPageExcel(req,response);
		}else if("modifyOther".equals(action)){
			doModify(req,response);
		}else if("modifyPwd".equals(action)){
			doModifyPwd(req,response);
		} else {
			String id=StringUtil.parseNull(req, "action");
			UserService us=UserService.getInstance();
			User user=us.queryUserByID(Integer.parseInt(id));
			//System.out.println("user="+user);
			req.getSession().setAttribute("pageUser", user);
			req.getRequestDispatcher("../Admin/UserManageInfo_Amind_Edit.jsp").forward(req, response);
		}
	}
	
	public void doPageQueryAjax(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException{
		List<User> list=new ArrayList<User>();
		String realName=StringUtil.parseNull(req,"realName");
		String sexCode=StringUtil.parseNull(req,"sexCode");
		String idTypeCode=StringUtil.parseNull(req,"idTypeCode");
		String idCode=StringUtil.parseNull(req,"idCode");
		String passenger_type=StringUtil.parseNull(req,"passenger_type");
		//System.out.println("%%%"+realName+sexCode+"%%%"+idTypeCode+"%%%"+idCode+"%%%"+passenger_type);
		//获取分页数据
		String pageSize=StringUtil.parseNull(req,"pageSize");
		String curPage=StringUtil.parseNull(req,"curPage");
		//拼装分页对象
		PageBean bean=new PageBean(Integer.parseInt(pageSize),Integer.parseInt(curPage));
		
		UserService us=UserService.getInstance();
		UserType userType=new UserType();
		userType.setId(Integer.parseInt(passenger_type));
		CertType certType=new CertType();
		certType.setId(Integer.parseInt(idTypeCode));
		User user=new User();
		user.setRealName(realName);
		user.setSex(sexCode);
		user.setUserType(userType);
		user.setCertType(certType);
		user.setCert(idCode);
		list=us.queryUser(user,bean);
		
		Gson gson=new Gson();
		Object[] obj=new Object[]{bean.getPageCnt(),list};
		String str=gson.toJson(obj);
		
		/*设置response.setContentType("application/json");后，
		 * jsp页面不需要再解析json字符串		 *  var jsonArray=JSON.parse(resp.trim()); */
		response.setContentType("application/json");
		/*response.setCharacterEncoding("UTF-8");*/
		PrintWriter pw=response.getWriter();
      	pw.print(str);
      	pw.flush();
      	pw.close();
		//req.setAttribute("userKK", list);
		//把list存放到session中，以便于Excel导出
/*		req.getSession().setAttribute("exportExcelUserList", list);
		req.setAttribute("recordCnt", bean.getRecordCnt());
		req.getSession().setAttribute("pageCnt", bean.getPageCnt());
		req.setAttribute("curPage", curPage);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("pageUserParam", user);*/
		
	}

	
	private void doModifyPwd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		UserService us=UserService.getInstance();
		String pwd=Md5Utils.md5("1");
		boolean flag=us.changePwd(((User)req.getSession().getAttribute("pageUser")).getId(),
				pwd, ((User)req.getSession().getAttribute("pageUser")).getPassword());
		if(flag){
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter();
			pw.println("<html><title>密码修改提示信息</title><body>");
			pw.println("<script>alert('密码修改成功');</script>");
			pw.println("<script>window.opener.location.reload();</script>");
			pw.println("<script>window.close();</script>");
			pw.println("</body><html>");
			pw.flush();
			pw.close();
		}else{
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter();
			pw.println("<html><title>密码修改提示信息</title><body>");
			pw.println("<script>alert('初始化密码失败，请稍后重试！');</script>");
			pw.println("</body><html>");
			pw.flush();
			pw.close();
		}

	}
	
	private void doModify(HttpServletRequest req, HttpServletResponse response) throws IOException{
		System.out.println("修改信息！");
		String loginName=StringUtil.parseNull(req.getParameter("loginName"));
      	String userId1=StringUtil.parseNull(req,"id");
      	String realName=StringUtil.parseNull(req.getParameter("realName"));
      	String sex=StringUtil.parseNull(req.getParameter("sexCode"));
      	String rule=StringUtil.parseNull(req.getParameter("rule"));
      	String city=StringUtil.parseNull(req.getParameter("city"));
      	String idTypeCode=StringUtil.parseNull(req.getParameter("idTypeCode"));
      	String idCode=StringUtil.parseNull(req.getParameter("idCode"));
      	String birthday=StringUtil.parseNull(req.getParameter("birthday"));
      	String passenger_type=StringUtil.parseNull(req.getParameter("passenger_type"));
      	String remark=StringUtil.parseNull(req.getParameter("remark"));   
      //1、组装User对象
        SimpleDateFormat dtf=new SimpleDateFormat("yyyy-MM-dd");
       	Date birth=null;
 		try {
 			birth = dtf.parse(birthday.toString());
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
       	String ip=req.getRemoteAddr();
       	String peopleArray[]=StringUtil.parseNullArray(req, "people");
       	for(String str:peopleArray) {
       		System.out.println("peopleArray="+str);
       	}
       	
       	CityService citySer=CityService.getInstance();
       	String cityId=citySer.queryCityID(city);
       	City cityObj=new City(-1,cityId,city,null);
       	UserType uType=new UserType(Integer.parseInt(passenger_type),"");
       	CertType cType=new CertType(Integer.parseInt(idTypeCode),"");
	     //2、调用service方法进行修改
       	UserService userSer=UserService.getInstance();
      	User user=new User(Integer.parseInt(userId1),cityObj,uType,cType,loginName,"",rule,realName,sex,idCode,birth,remark,"1",ip,null);
      	int saveFlag=userSer.updateUser(user);
      	 if(saveFlag>0) {
      		response.setContentType("text/html");
          	PrintWriter pw=response.getWriter();
          	pw.println("<html><title>修改成功</title><body>");
	      	pw.println("<script>alert('信息修改成功')</script>");
	      	//pw.println("window.parent.location.href='UserManageQuery.jsp'</script>");
	      	pw.println("<script>window.opener.location.reload();</script>");
			pw.println("<script>window.close();</script>");
	      	pw.println("</body></html>");
          	pw.flush();
          	pw.close();	
      	}else{
      		//成功给予提醒
          	//resp.setCharacterEncoding("gb2312");
          	response.setContentType("text/html");
          	PrintWriter pw=response.getWriter();
          	pw.println("<html><script>");
          	pw.println("alert('数据保存失败，请稍候重试！')");
          	pw.println("</script></html>");
          	pw.flush();
          	pw.close();	
      	}
	}
	
	private void doPageExcel(HttpServletRequest req, HttpServletResponse response){
		List<User> list=new ArrayList<>();
		list=(List<User>)req.getSession().getAttribute("exportExcelUserList");
		if(null==list || list.size()==0){
			return;
		}
		ServletOutputStream os=null;
		WritableWorkbook workBook=null;
		try{
			String fileName=new String("用户".getBytes("GB2312"),"ISO8859-1");
			//设置响应信息
			response.setHeader("Content-disposition", "attachment;filename="+fileName+".xls");
			response.setHeader("parama", "no-cache");
			response.setContentType("application/msexcel");
			//获取输出流，完成向浏览器的输出
			os=response.getOutputStream();
			//创建Workbook以及sheet对象
			workBook=Workbook.createWorkbook(os)	;
			WritableSheet ws=workBook.createSheet("用户信息列表", 0);
			//创建一些格式化对象
			WritableFont title=new WritableFont(
					WritableFont.TIMES,16,WritableFont.BOLD);
			WritableFont head=new WritableFont(
					WritableFont.TIMES,12,WritableFont.BOLD);
			WritableCellFormat titleFormat=new WritableCellFormat(title);
			//设置居中
			titleFormat.setAlignment(jxl.format.Alignment.CENTRE);
			//添加边框
			titleFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			Label titleLabel=new Label(0,0,"导出用户列表",titleFormat);
			ws.addCell(titleLabel);
			//合并单元格
			ws.mergeCells(0, 0, 7, 0);
			//声明一组对header的格式化
			WritableCellFormat headerFormat=new WritableCellFormat(head);
			headerFormat.setAlignment(Alignment.CENTRE);
			headerFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			headerFormat.setBackground(jxl.format.Colour.PINK2);
			
			WritableCellFormat rowFormat=new WritableCellFormat();
			rowFormat.setAlignment(Alignment.CENTRE);
			rowFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			//指定列宽（列、列宽）
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 25);
			ws.setColumnView(7, 60);
			//指定行高（行、行高）
			//ws.setRowView(0, 50);
			
			//添加数据表格的题头
			ws.addCell(new Label(0,1,"编号",headerFormat));
			ws.addCell(new Label(1,1,"用户名",headerFormat));
			ws.addCell(new Label(2,1,"角色",headerFormat));
			ws.addCell(new Label(3,1,"真实姓名",headerFormat));
			ws.addCell(new Label(4,1,"性别",headerFormat));
			ws.addCell(new Label(5,1,"城市",headerFormat));
			ws.addCell(new Label(6,1,"用户类型",headerFormat));
			ws.addCell(new Label(7,1,"证件号码",headerFormat));
			
			//添加数据到表格
			int rowNum=2;
			for(int i=0;i<list.size();i++,rowNum++){
				User user=list.get(i);
				ws.addCell(new Label(0,rowNum,user.getId()+"",headerFormat));
				ws.addCell(new Label(1,rowNum,user.getUserName(),headerFormat));
				ws.addCell(new Label(2,rowNum,user.getRule().equals("1")?"管理员":"普通用户",headerFormat));
				ws.addCell(new Label(3,rowNum,user.getRealName(),headerFormat));
				ws.addCell(new Label(4,rowNum,user.getSex().equals("M")?"男":"女",headerFormat));
				ws.addCell(new Label(5,rowNum,user.getCity().getCity(),headerFormat));
				ws.addCell(new Label(6,rowNum,user.getUserType().getContent(),headerFormat));
				ws.addCell(new Label(7,rowNum,user.getCert(),headerFormat));
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try {
				workBook.write();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				workBook.close();
			} catch (WriteException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void doDelete(HttpServletRequest req, HttpServletResponse response)throws ServletException, IOException{
		String[] dode=StringUtil.parseNullArray(req, "checkbox");
		if(dode.length>0){
			UserService us=UserService.getInstance();
			/*req.getRequestDispatcher("/Admin/UserManageQuery.jsp").forward(req, response);*/
			if(us.delUser(dode)>0){
		      	PrintWriter pw=response.getWriter();
		      	pw.print("success");
		      	pw.flush();
		      	pw.close();	
			}
		}else{
			response.setContentType("text/html");
	      	PrintWriter pw=response.getWriter();
	      	pw.println("<html><title>删除失败</title><body>");
	      	pw.println("<script>alert('请选择删除信息')</script>");
	      	pw.println("<script>window.location='UserManageQuery.jsp';</script>");
	      	pw.println("</body></html>");
	      	pw.flush();
	      	pw.close();	
		}
	}
	
	public void doPageQuery(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException{
		List<User> list=new ArrayList<User>();
		String realName=StringUtil.parseNull(req,"realName");
		String sexCode=StringUtil.parseNull(req,"sexCode");
		String idTypeCode=StringUtil.parseNull(req,"idTypeCode");
		String idCode=StringUtil.parseNull(req,"idCode");
		String passenger_type=StringUtil.parseNull(req,"passenger_type");
		//System.out.println("%%%"+realName+sexCode+"%%%"+idTypeCode+"%%%"+idCode+"%%%"+passenger_type);
		//获取分页数据
		String pageSize=StringUtil.parseNull(req,"pageSize");
		String curPage=StringUtil.parseNull(req,"curPage");
		//拼装分页对象
		PageBean bean=new PageBean(Integer.parseInt(pageSize),Integer.parseInt(curPage));
		
		UserService us=UserService.getInstance();
		UserType userType=new UserType();
		userType.setId(Integer.parseInt(passenger_type));
		CertType certType=new CertType();
		certType.setId(Integer.parseInt(idTypeCode));
		User user=new User();
		user.setRealName(realName);
		user.setSex(sexCode);
		user.setUserType(userType);
		user.setCertType(certType);
		user.setCert(idCode);
		list=us.queryUser(user);
		//list=us.queryUser(user,bean);
//		System.out.println("11="+ bean.getRecordCnt());
//		System.out.println("2="+pageSize);
//		System.out.println("3="+pageCnt);
//		System.out.println("4="+curPage);
		req.setAttribute("userKK", list);
		
		response.setContentType("text/html");
      	PrintWriter pw=response.getWriter();
      	pw.println("<html><title>修改成功</title><body>");
      	pw.println("<script>alert('信息修改成功')</script>");
      	pw.println("<script>window.opener.location.reload();</script>");
		pw.println("<script>window.close();</script>");
      	pw.println("</body></html>");
      	pw.flush();
      	pw.close();	
      	
		//把list存放到session中，以便于Excel导出
		req.getSession().setAttribute("exportExcelUserList", list);
		req.setAttribute("recordCnt", bean.getRecordCnt());
		req.setAttribute("pageCnt", bean.getPageCnt());
		req.setAttribute("curPage", curPage);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("pageUserParam", user);
		req.getRequestDispatcher("/Admin/UserManageQuery.jsp?action=queryBack").forward(req, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
