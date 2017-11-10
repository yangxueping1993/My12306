package com.neuedu.business.dao;

import java.util.List;

import com.neuedu.domain.UserType;


public interface UserTypeDao {
	
	/**
	 * 查询所有用户类型
	 * @return
	 */
	public List<UserType> queryUsertType() throws Exception;

}
