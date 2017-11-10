package com.neuedu.business.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.business.dao.CertTypeDao;
import com.neuedu.domain.CertType;
import com.neuedu.util.DBUtil;
import com.neuedu.util.ResultSetCallBack;

public class CertTypeDaoImpl implements CertTypeDao {
	
	private Connection conn=null;
	public CertTypeDaoImpl(Connection conn){
		this.conn=conn;
	}

	@Override
	public List<CertType> queryCertType() throws Exception {
		return DBUtil.queryByCallBack(conn,
				"select id,content from tab_certtype",
				new ResultSetCallBack<List<CertType>>(){

					@Override
					public List<CertType> parseResultSet(ResultSet rs) throws SQLException {
						List<CertType> list=new ArrayList<CertType>();
						while(rs.next()){
							CertType ct=new CertType(rs.getInt("ID"),rs.getString("CONTENT"));
							list.add(ct);
						}
						return list;
					}
			
		});
		// TODO Auto-generated method stub
		/*PreparedStatement stmt=null;
		ResultSet rs=null;
		List<CertType> list = new ArrayList<>();
		try {
			stmt = conn.prepareStatement("select id,content from tab_certtype");
			rs = stmt.executeQuery();
			while(rs.next()){
				CertType ct=new CertType(rs.getInt("ID"),rs.getString("CONTENT"));
				list.add(ct);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(stmt, rs);;
		}
		return list;*/
	}



}
