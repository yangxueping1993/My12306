package com.neuedu.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.domain.User;
import com.neuedu.util.Constants;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter("/SessionFilter")
public class SessionFilter implements Filter {
	
	private String loginPath="";

    /**
     * Default constructor. 
     */
    public SessionFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		String url=req.getRequestURI();
//		session中存在用户信息，说明登录成功，则放行
		if(req.getSession().getAttribute(Constants.userKey) != null){
			// pass the request along the filter chain
			if(((User)req.getSession().getAttribute(Constants.userKey)).getRule().equals("2")){
				if(url.indexOf("Admin")!=-1){
					resp.setContentType("text/html"); 
					PrintWriter pw=resp.getWriter(); 
					pw.println("<html><body>");
					pw.println("<script>");
					pw.println("alert('您的权限不足，不支持非法访问！')");
					pw.println("window.top.location.href='"+req.getContextPath()+"/Login.jsp'");
					pw.println("</script>");
					pw.println("</body></html>");
					pw.println("请求超时，请重新");
					pw.flush();
					pw.close();	
					return;
				}else{
					chain.doFilter(request, response);
				}
			}else {
				chain.doFilter(request, response);
				
			}
			
		}else{
			String[] pathArray=loginPath.split(";");
			for(String path:pathArray){
				if(null!=url&&url.endsWith("Login.jsp")){
					chain.doFilter(request, response);
					return;
				}else if(null!=url&&url.indexOf(path)!=-1&&(!url.endsWith(".jsp"))){
					chain.doFilter(request, response);
					return;
				}
			}
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter(); 
			pw.println("<html><body>");
			pw.println("<script>");
			pw.println("alert('您的会话超时或者您从未登录，请重新登录！')");
			pw.println("window.top.location.href='"+req.getContextPath()+"/Login.jsp'");
			pw.println("</script>");
			pw.println("</body></html>");
			pw.flush();
			pw.close();
			
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig cfg) throws ServletException {
		loginPath=cfg.getInitParameter("loginPath");
	}
}
