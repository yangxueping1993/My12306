package com.neuedu.test;

import com.neuedu.business.service.CertTypeService;
import com.neuedu.business.service.CityService;
import com.neuedu.business.service.ProvinceService;
import com.neuedu.business.service.UserTypeService;
import com.neuedu.domain.Province;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*CertTypeService service=new CertTypeService();
        System.out.println(service.queryCertType());
        
        ProvinceService service1=new ProvinceService();
        System.out.println(service1.queryProvince());
        
        UserTypeService service3=new UserTypeService();
        System.out.println(service3.queryUsertType());
        
        CityService service4=new CityService();
        System.out.println(service4.queryCity());
        Province p=new Province("河北省");
        System.out.println("河北"+service4.queryCityByProvince(p));
        System.out.println("北京"+service4.queryCityByID("110100"));*/
		
		String a="1";
		String b=Integer.parseInt(a)+"";
		System.out.println(b);
	}
}
