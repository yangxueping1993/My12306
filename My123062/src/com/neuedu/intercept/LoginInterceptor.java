package com.neuedu.intercept;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.domain.User;

/**
 * 登录拦截器（未登录跳转登录页）
 * @author 杨雪平
 * 2017年11月10日
 */

public class LoginInterceptor  {

	public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("now_user");
		if (session.getAttribute("now_user") == null) {
		response.sendRedirect(request.getContextPath() + "/other/toLogin");
		return false;
	}
	//多用户登录限制判断,并给出提示信息
	boolean isLogin = false;
	if (user != null) {
	Map<String, String> loginUserMap = (Map<String, String>) session.getServletContext().getAttribute("loginUserMap");
	String sessionId = session.getId();
		for (String key : loginUserMap.keySet()) {
		//用户已在另一处登录
			if (key.equals(user.getUserName()) && !loginUserMap.containsValue(sessionId)) {
			isLogin = true;
			break;
			}
		}
	}
	if (isLogin) {
	Map<String, String> loginOutTime = (Map<String, String>) session.getServletContext().getAttribute("loginOutTime");
	session.setAttribute("mess", "用户：" + user.getUserName() + "，于 " + loginOutTime.get(user.getUserName()) + " 已在别处登录!");
	loginOutTime.remove(user.getUserName());
	session.getServletContext().setAttribute("loginUserMap", loginOutTime);
	response.sendRedirect(request.getContextPath() + "/other/toLogin");
	return false;
	}
	return super.preHandle(request, response, handler);
	}
	
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex)	throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}

