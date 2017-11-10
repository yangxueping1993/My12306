package com.neuedu.business.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.impl.UserTypeDaoImpl;
import com.neuedu.domain.UserType;
import com.neuedu.util.DBUtil;

public class UserTypeService extends BaseService {
	
	private static final UserTypeService instance=new UserTypeService();
	private UserTypeService(){}
	public static UserTypeService getInstance(){
		return instance;
	}
	
	/**
	 * 查询所有用户类型
	 * @return
	 */
	public List<UserType> queryUsertType(){
		List<UserType> res=new ArrayList<>();
		Connection conn=null;
		try {
			conn=DBUtil.getConn();
			UserTypeDaoImpl dao=new UserTypeDaoImpl(conn);
			res=dao.queryUsertType();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return res;
	}
}
