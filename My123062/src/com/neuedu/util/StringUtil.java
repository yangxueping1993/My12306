package com.neuedu.util;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {
	
	public static String covertEmpty(Object obj){
		if(null==obj||String.valueOf(obj).equalsIgnoreCase("null")){
			return "";
		}else{
			return String.valueOf(obj);
		}
	}
	
	/**
	 * 处理空字符串
	 * @param param
	 * @return
	 */
	public static String parseNull(String param){
		if(null==param||param.equalsIgnoreCase("null")){
			return "";
		}else{
			return param;
		}
	}
	
	/**
	 * 处理request参数里的空
	 * @param param
	 * @return
	 */
	public static String parseNull(HttpServletRequest req,String name){
		return parseNull(req.getParameter(name));
	}
	
	/**
	 * 处理数组的null
	 * @param param
	 * @return
	 */
	public static String[] parseNullArray(String[] param){
		if(null==param){
			return new String[]{};
		}else{
			return param;
		}
	}
	
	/**
	 * 处理getParameterValues里面的空数组
	 * @param req
	 * @param name
	 * @return
	 */
	public static String[] parseNullArray(HttpServletRequest req,String name){
		return parseNullArray(req.getParameterValues(name));
	}
	
	
}
