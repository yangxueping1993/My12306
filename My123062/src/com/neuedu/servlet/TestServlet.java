package com.neuedu.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.business.service.CityService;
import com.neuedu.business.service.ProvinceService;
import com.neuedu.domain.City;
import com.neuedu.domain.Province;
import com.neuedu.util.StringUtil;


@WebServlet("/test")
public class TestServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletResponse resp,HttpServletRequest req){  
		doPost(resp,req); 
	}
	
	public void doPost(HttpServletResponse resp,HttpServletRequest req){
		String type=StringUtil.parseNull(req, "type");
		if(type.equals("city")){
			CityService city=CityService.getInstance();
			List<City> list=city.queryCity();
			System.out.println(list);
		}else if(type.equals("province")){
			ProvinceService province=ProvinceService.getInstance();
			List<Province> list=province.queryProvince();
			System.out.println(list);
		}else{
			System.out.println("请输入正确的参数！");
		}
	}

}
