package com.neuedu.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.google.gson.Gson;


public class DBUtil {
	
	//获取c3p0连接池
	//private static final ComboPooledDataSource ds=new ComboPooledDataSource();
	private static final Logger log=Logger.getLogger(DBUtil.class);
	/**
	 * 获取数据库连接
	 * @return
	 */
	/*public static Connection getConn(){
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			log.error("获取数据库连接失败",e);
		}
		return null;
	}*/
	
	public static Connection getConn(){
		try {
			//初始化上下文
			Context ctx=new InitialContext();
			//根据名称搜索对应的服务	
			//连接服务器的配置123062，连接项目的配置My12306
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/My123062");
			return ds.getConnection();
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("获取数据库连接失败",e);
		}
		return null;
	} 
	
	/**
	 *将传入的数据库连接设置为非自动提交 
	 * @param conn
	 */
	public static void beginTransaction(Connection conn){
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *执行事务提交，同时把数据库连接设置为默认的走动提交
	 * @param conn
	 */
	public static void commit(Connection conn){
		
		try {
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行事务回滚
	 * @param conn
	 */
	public static void rollback(Connection conn){
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt,ResultSet rs){
		close(rs,stmt,null);
	}
	public static void close(Statement stmt){
		close(null,stmt,null);
	}
	public static void close(Connection conn){
		close(null,null,conn);
	}
	
	public static void close(Statement stmt,Connection conn){
		close(null,stmt,conn);
	}
	
	/**
	 *释放数据库资源 
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void close(ResultSet rs,Statement stmt,Connection conn){
		if(null!=rs){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(null!=stmt){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 通过该方法获取SQL语句中的第一行第一列的整数值
	 * 一般用于类似select count（1）from tab这样的情况
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static int queryForInt(Connection conn,String sql,Object[] params) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int cnt=-1;
		try {
			log.info("queryForInt-sql="+sql+"[参数为："+params+"}");
			stmt=conn.prepareStatement(sql);
			if(null!=params&&params.length>0){
				for(int i=0;i<params.length;i++){
					stmt.setObject((i+1), params[i]);
				}
			}
			rs=stmt.executeQuery();
			if(rs.next()){
				//取SQL结果集中的第一行第一列的值
				cnt=rs.getInt(1);
			}
		} finally {
			DBUtil.close(stmt, rs);
		}
		return cnt;
	}
	
	public static int queryForInt(Connection conn,String sql) throws SQLException{
		return queryForInt(conn,sql,null);
	}
	
	/**
	 * 通过该方法获取SQL语句中的第一行第一列的整数值
	 * 一般用于类似select name from tab where id=100 这样的情况，其中id是唯一的
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static String queryForString(Connection conn,String sql,Object[] params) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String res="-1";
		try {
			stmt=conn.prepareStatement(sql);
			if(null!=params&&params.length>0){
				for(int i=0;i<params.length;i++){
					stmt.setObject((i+1), params[i]);
				}
			}
			rs=stmt.executeQuery();
			if(rs.next()){
				//取SQL结果集中的第一行第一列的值
				res=rs.getString(1);
			}
		} finally {
			DBUtil.close(stmt, rs);
		}
		return res;
	}
	
	public static String queryForString(Connection conn,String sql) throws SQLException{
		return queryForString(conn,sql,null);
	}
	
	/**
	 * 通过制定回调函数，让调用者定制化数据的输出
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static <T>T queryByCallBack(Connection conn,String sql,Object[] params,
			ResultSetCallBack<T> call) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		T res=null;
		try {
			//log.info("queryByCallBack-sql="+sql+" 参数="+new Gson().toJson(params));
			stmt=conn.prepareStatement(sql);
			if(null!=params && params.length>0){
				for (int i = 0; i < params.length; i++) {
					stmt.setObject((i+1), params[i]);
				}
			}
			rs=stmt.executeQuery();
			res=call.parseResultSet(rs);
		} finally {
			DBUtil.close(stmt, rs);
		}
		return res;
	}
	public static <T>T queryByCallBack(Connection conn,String sql,
			ResultSetCallBack<T> call) throws SQLException{
		return queryByCallBack(conn,sql,null,call);
	}
	
	/**
	 * 用于执行不需要返回结果集的SQL
	 * 类似delete from tab\ update table\truncate table
	 * create table\drop table
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static int execSQL(Connection conn,String sql,Object[] params)throws SQLException{
		PreparedStatement stmt=null;
		int cnt=-1;
		try {
			stmt=conn.prepareStatement(sql);
			if (null!=params && params.length>0) { 
				for (int i = 0; i < params.length; i++) {
					stmt.setObject((i+1), params[i]);
				}
			}
			cnt=stmt.executeUpdate();
		} finally {
			DBUtil.close(stmt);
		}
		return cnt;
	}
	public static int execSQL(Connection conn,String sql)throws SQLException{
		return execSQL(conn,sql,null);
	}
	
	public static <T>T queryByCallBackPage(Connection conn,String sql,Object[] params,
			PageBean page,ResultSetCallBack<T> call) throws SQLException{
		int start=page.getStartRowNum();
		int end=page.getEndRowNum();
		String pageSQL="select * from (select rownum rn,b.* from ("+sql+") b where rownum<="+end+") where rn>="+start;
		log.info("pageSQL"+pageSQL);
		return queryByCallBack(conn,pageSQL,params,call);
	}
	
	public static <T>T queryByCallBackPage(Connection conn,String sql,PageBean page,
			ResultSetCallBack<T> call) throws SQLException{
		return queryByCallBackPage(conn,sql,null,page,call);
	}
}
