package com.neuedu.business.dao;

import java.util.List;

import com.neuedu.domain.CertType;

public interface CertTypeDao {
	 
	/**
	 * 获取所有证件类型
	 * @return
	 */
	public List<CertType> queryCertType() throws Exception;

}
