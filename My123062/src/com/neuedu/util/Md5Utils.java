package com.neuedu.util;

import java.security.MessageDigest;

public class Md5Utils {

	public final static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			byte[] btInput = s.getBytes();
			//生成MessageDigest对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			//传入需要计算的字符串
			mdInst.update(btInput);
			//计算消息摘要
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				/*System.out.println(byte0);*/
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("md5潘高峰数据库my12306=="+md5("y"));
		System.out.println("md5="+md5("1"));
	}
}
