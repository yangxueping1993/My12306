package com.neuedu.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.neuedu.business.service.CityService;
import com.neuedu.domain.City;

public class CityServiceTest {

	//无法测私有方法
	
	CityService test=CityService.getInstance();
	@Test
	public void testQueryCity() {
		List<City> list=test.queryCity();
		System.out.println(list);
		assertEquals(11, list.size());
	}

	@Test
	public void testQueryCityByProvince() {
		
	}

	@Test
	public void testQueryCityByID() {
		List<City> list=test.queryCityByID("130000");
//		String srt=
		System.out.println(list);
		assertEquals(11, list.size());
	}

	@Test
	public void testQueryCityID() {
		
	}

}
