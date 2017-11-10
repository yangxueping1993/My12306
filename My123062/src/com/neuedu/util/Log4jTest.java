package com.neuedu.util;

import org.apache.log4j.Logger;

public class Log4jTest {
	
	//声明日志记录对象
	private static final Logger log=Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args){
		log.trace("trace...");
		log.debug("degug信息……");
		try {
			int i=10/0;
		} catch (Exception e) {
			log.error("tomcat",e);
			log.fatal("fatal...");
			
		}
		
	}

}
