package com.neuedu.business.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.impl.ProvinceDaoImpl;
import com.neuedu.domain.Province;
import com.neuedu.util.DBUtil;

public class ProvinceService extends BaseService {
	private static final ProvinceService instance=new ProvinceService();
	private ProvinceService(){}
	public static ProvinceService getInstance(){
		return instance;
	}
	/**
	 * 查新所有省份信息
	 * @return
	 */
	public List<Province> queryProvince(){
		List<Province> res=new ArrayList<>();
		Connection conn=null;
		try {
			conn=DBUtil.getConn();
			ProvinceDaoImpl dao=new ProvinceDaoImpl(conn);
			res=dao.queryProvince();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return res;
	}


}
