package com.neuedu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SetCharacterEncodingFilter implements Filter {
	private String charSet="";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		//System.out.println("正在被执行过滤的："+((HttpServletRequest)req).getRequestURI());
		//System.out.println("sessionID="+((HttpServletRequest)req).getSession().getId());
		/***
		 * 设置session的存活时间为30s
		 * 一般不需要单独设置，只需要在web.xml中统一配置即可
		 * setMaxInactiveInterval(30)单位为秒
		 * 
		 */
//		((HttpServletRequest)req).getSession().setMaxInactiveInterval(30);//存活30s
		
		/**
		 * ajax中文乱码问题处理begin
		 */
		
		HttpServletRequest req2=((HttpServletRequest)req);
		if(req2.getHeader("X-Requested-With")!=null&&
				req2.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")){
			//通过判断request中的Header的值，决定是否对ajax请求进行特殊处理
			req.setCharacterEncoding("utf-8");
		}else{
			req.setCharacterEncoding(charSet);
		}
		/**
		 * ajax中文乱码问题处理end
		 */
		
//		req.setCharacterEncoding("GB2312");
//		System.out.println("resp.getCharacterEncoding()为："+resp.getCharacterEncoding());
		resp.setCharacterEncoding(charSet);
		
		//放行到下一个过滤器，如果这是最后一个，那么请求到达目标资源
		chain.doFilter(req,resp);
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
//		System.out.println(cfg.getInitParameter("charSet"));
		charSet=cfg.getInitParameter("charSet");
//		System.out.println("charSet="+charSet);
	}
}
