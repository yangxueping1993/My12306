package com.neuedu.business.dao;

import java.util.List;

import com.neuedu.domain.Province;

public interface ProvinceDao {
	
	/**
	 * 查询所有省份信息
	 * @return
	 */
	public List<Province> queryProvince() throws Exception;

}
