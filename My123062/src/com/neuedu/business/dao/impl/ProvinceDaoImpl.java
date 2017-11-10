package com.neuedu.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.ProvinceDao;
import com.neuedu.domain.Province;
import com.neuedu.util.DBUtil;

public class ProvinceDaoImpl implements ProvinceDao {
	private Connection conn=null;
	public ProvinceDaoImpl(Connection conn){
		this.conn=conn;
	}

	@Override
	public List<Province> queryProvince() throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Province> list = new ArrayList<>();
		try {
			stmt = conn.prepareStatement("select id,PROVINCE,PROVINCEID from TAB_PROVINCE");
			rs = stmt.executeQuery();
			while(rs.next()){
				Province pr=new Province(rs.getInt("ID"),rs.getString("PROVINCE"),rs.getString("PROVINCEID"));
				list.add(pr);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(stmt, rs);
		}
		return list;
	}

}
