package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.business.service.UserService;
import com.neuedu.domain.User;
import com.neuedu.util.Constants;
import com.neuedu.util.Md5Utils;
import com.neuedu.util.StringUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/doLogin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int  i=1; 
	private static Map<Integer,Integer> map=new HashMap<>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=StringUtil.parseNull(request, "action");
		if("modifyPwd".equals(action)){
			doModifyPwd(request,response);
		}else{
			doLogin(request,response);
		}
		
	}
	public void doLogin(HttpServletRequest request,HttpServletResponse response ) throws ServletException, IOException {
		String loginName=StringUtil.parseNull(request, "loginName");
		String pwd=StringUtil.parseNull(request, "pwd");
//		对密码进行MD5加密
		pwd=Md5Utils.md5(pwd);
		String validateCode=StringUtil.parseNull(request, "validateCode");
		String autoLogin=StringUtil.parseNull(request, "autoLogin");
		if("".equals(autoLogin)){
			autoLogin="false";
		}
		
		//验证验证码是否输入正确
		if(!validateCode.equalsIgnoreCase((String)request.getSession().getAttribute("code"))){
			request.setAttribute("msg", "登录失败，请输入正确的验证码！");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
			return;
		}
		UserService us=UserService.getInstance();
		User user=us.login(loginName, pwd);
		
		if(user!=null){
			request.getSession().setAttribute(Constants.userKey, user);
			
			Integer ii=new Integer(++i);
			map.put(-1,0);
			ServletContext application=this.getServletContext();  
			
			
			
			application.setAttribute("app", map);
			if(null!=((HashMap)application.getAttribute("app"))&&
					((HashMap)application.getAttribute("app")).containsKey(user.getId())){
				response.setContentType("text/html"); 
				PrintWriter pw=response.getWriter(); 
				pw.println("<html><body>");
				pw.println("<script>");
				pw.println("alert('用户已登录，请重新登录！')");
				pw.println("window.top.location.href='"+request.getContextPath()+"/Login.jsp'");
				pw.println("</script>");
				pw.println("</body></html>");
				pw.println("请求超时，请重新");
				pw.flush();
				pw.close();	
				return;
			}else{
				map.put(user.getId(),ii);
			}
			
			//登录成功
			/**
			 * 登录成功后，把用户对象存放到session中
			 * session可以在所有页面之间共享数据
			 * 前提是session是有效的
			 */
			
			//System.out.println("sessionID="+request.getSession().getId());
			//把当前登录成功的用户名通过Cookie写给浏览器
			Cookie cook=new Cookie("loginName",loginName);
			//设置Cookie的存活时间，以秒为单位（默认是临时的，浏览器关闭即清除）
			cook.setMaxAge(5*60);
			cook.setPath(request.getContextPath());
			response.addCookie(cook);
			if("1".equals(user.getRule())){
				response.sendRedirect(request.getContextPath()+"/Admin/Index.html");
			}else if("2".equals(user.getRule())){
				response.sendRedirect(request.getContextPath()+"/User/Index.html");
			}
		}else{
			//登录失败.
			request.setAttribute("msg", "登录失败，请输入正确的用户信息！");
//			response.sendRedirect(request.getContextPath()+"/Login.jsp");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	}
	public void doModifyPwd(HttpServletRequest req,HttpServletResponse resp ) throws IOException{
		String userId=StringUtil.parseNull(req,"userID");
		String oldPwd=StringUtil.parseNull(req,"oldPwd");
		oldPwd=Md5Utils.md5(oldPwd);
		String pwd=StringUtil.parseNull(req,"pwd");
		pwd=Md5Utils.md5(pwd);
		UserService us=UserService.getInstance();
		boolean flag=us.changePwd(Integer.parseInt(userId), pwd, oldPwd);
		if(flag){
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter();
			pw.println("<html><title>密码修改提示信息</title><body>");
			pw.println("<script>alert('密码修改成功');</script>");
			pw.println("<p color='red'>密码修改成功</p>");
			pw.println("</body><html>");
			pw.flush();
			pw.close();
//			resp.sendRedirect(req.getContextPath()+"/Login.jsp");
//			req.getRequestDispatcher("/Login.html").forward(req, resp);
		}else{
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter();
			pw.println("<html><title>密码修改提示信息</title><body>");
			pw.println("<script>alert('密码修改失败,请确认原密码是否正确！');</script>");
			pw.println("<p color='red'>密码修改成功,请确认原密码是否正确</p>");
			pw.println("</body><html>");
			pw.flush();
			pw.close();
		}
	}
}
