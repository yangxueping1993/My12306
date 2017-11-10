package com.neuedu.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.UserTypeDao;
import com.neuedu.domain.UserType;
import com.neuedu.util.DBUtil;

public class UserTypeDaoImpl implements UserTypeDao {
	private Connection conn=null;
	public UserTypeDaoImpl(Connection conn){
		this.conn=conn;
	}

	@Override
	public List<UserType> queryUsertType() throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<UserType> list=new ArrayList<>();
		try {
			stmt=conn.prepareStatement("select id,content from tab_usertype");
			rs = stmt.executeQuery();
			while(rs.next()){
				UserType ut=new UserType(rs.getInt("ID"),rs.getString("CONTENT"));
				list.add(ut);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(stmt, rs);;
		}
		return list;
	}

}
