package com.neuedu.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.neuedu.business.service.ProvinceService;
import com.neuedu.domain.Province;

public class ProvinceServiceTest {
	
	ProvinceService test=ProvinceService.getInstance();

	@Test
	public void testQueryProvince() {
		List<Province> list=test.queryProvince();
		System.out.println(list);
		assertEquals(35, list.size());//期望值值和实际通过
	}

}
