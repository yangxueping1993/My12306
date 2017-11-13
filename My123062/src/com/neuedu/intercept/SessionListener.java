package com.neuedu.intercept;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.neuedu.business.service.UserService;
import com.neuedu.domain.User;

/**
 * 在session销毁的时候,把loginUserMap中保存的键值对清除
 * @author 杨雪平
 * 2017年11月11日
 */
public class SessionListener implements HttpSessionListener  {
	private static Logger log = Logger.getLogger(SessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		String sessionId = session.getId();
		//在session销毁的时候,把loginUserMap中保存的键值对清除
		User user = (User) session.getAttribute("now_user");
		if (user != null) {
			Map<String, String> loginUserMap = (Map<String, String>) event.getSession().getServletContext().getAttribute("loginUserMap");
			if(loginUserMap.get(user.getUserName()).equals(sessionId)){
				log.info("clean user from application : " + user.getUserName());
				loginUserMap.remove(user.getUserName());
				event.getSession().getServletContext().setAttribute("loginUserMap", loginUserMap);
			}
		}
	}
	
	/**
	 * 清除挤掉用户session代码
	 * @param request
	 * @return
	 */
	public String clearUserSession(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		//httpSession.invalidate();
		httpSession.removeAttribute("now_user");
		httpSession.removeAttribute("mess");
		return "success";
		}
	
	public String login(String userName, String password, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		//判断用户是否已经在线及处理（已在线则剔除）
		String loginLimite = LimiteLogin.loginLimite(request, userName);
		//判断用户名、密码是否正确
		String result = UserService.login(userName, password);
		if (result.equals("success")) {
		request.getSession().setAttribute("now_user", userService.findByUserName(userName));
		//创建token及验证
		String jwtToken = tokenService.createUserAuthToken(userService.findByUserName(userName));//生成token
		System.out.println(jwtToken);
		UserAuthenticationToken authToken = tokenService.retrieveUserAuthToken(jwtToken);//token解析
		System.out.println(authToken.isAuthenticated());
		System.out.println("id = " + UserAuthenticationToken.getCurrentToken().getUserUuid());
		//用户掉线，登录后重定向到保存的链接
		Object url = request.getSession().getAttribute("redirect_link");
		if (url != null) {
		request.getSession().removeAttribute("redirect_link");
		return "redirect:" + url.toString();
		}
		return "index";
		}
		redirectAttributes.addFlashAttribute("message", result);
		return "redirect:/other/toLogin";
		}

}


