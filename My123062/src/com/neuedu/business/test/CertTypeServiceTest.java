package com.neuedu.business.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.neuedu.business.service.CertTypeService;
import com.neuedu.business.service.CityService;
import com.neuedu.domain.CertType;
import com.neuedu.domain.City;

public class CertTypeServiceTest {
	CertTypeService test=CertTypeService.getInstance();

	@Test
	public void testQueryCertType() {
		List<CertType> list=test.queryCertType();
		System.out.println(list);
		assertEquals(4, list.size()); 
	}

}
