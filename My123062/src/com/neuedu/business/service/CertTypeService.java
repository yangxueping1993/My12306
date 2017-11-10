package com.neuedu.business.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.impl.CertTypeDaoImpl;
import com.neuedu.domain.CertType;
import com.neuedu.util.DBUtil;

public class CertTypeService extends BaseService {
	private static final CertTypeService instance=new CertTypeService();
	private CertTypeService(){};
	public static CertTypeService getInstance(){
		return instance;
	}
	
	/**
	 * 获取所有证件类型
	 * @return
	 */
	public List<CertType> queryCertType(){
		List<CertType> res=new ArrayList<>();
		Connection conn=null;
		try {
			conn=DBUtil.getConn();
			CertTypeDaoImpl dao=new CertTypeDaoImpl(conn);
			res=dao.queryCertType();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return res;
	}

}
