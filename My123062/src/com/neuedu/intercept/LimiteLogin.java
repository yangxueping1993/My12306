package com.neuedu.intercept;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.neuedu.business.service.UserService;
import com.neuedu.domain.User;
import com.neuedu.util.Constants;

/**
 * 判断登录是否已经在线
 * @author 杨雪平
 * 2017年11月10日
 */
public class LimiteLogin {
	
	private static Logger log=Logger.getLogger(LimiteLogin.class);
	//存储在线用户
	private static Map<String,String> loginUserMap=new HashMap<>();
	//存储剔除用户时间
	private static Map<String,String> loginOutTime=new HashMap<>();
	
	public String loginLimite(HttpServletRequest request,User user,
			ServletContext application){
		String sessionid=((User)request.getSession().getAttribute(Constants.userKey)).getId()+"";
		for(String key:loginUserMap.keySet()){
			//用户已在另一处登录
			if(key.equals(user.getUserName()) && !loginUserMap.containsValue(sessionid)){
				log.info("用户："+user.getUserName()+",于"+new Date()+"被剔除！");
				loginOutTime.put(user.getUserName(), new Date().toString());
				loginUserMap.remove(user.getUserName());
				break;
			}
		}
		loginUserMap.put(user.getUserName(), sessionid);
		application.setAttribute("loginUserMap", loginUserMap);
		application.setAttribute("loginOutTime", loginOutTime);
		return "success";
	}
}
