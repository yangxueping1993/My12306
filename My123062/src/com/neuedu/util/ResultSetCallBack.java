package com.neuedu.util;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface ResultSetCallBack<T>{
	
	/**
	 * 以回调函数的形式，处理SQL查询的结果集ResultSet
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public T parseResultSet(ResultSet rs) throws SQLException;

}
