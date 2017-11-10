package com.neuedu.business.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.impl.CityDaoImpl;
import com.neuedu.domain.City;
import com.neuedu.domain.Province;
import com.neuedu.util.DBUtil;

public class CityService extends BaseService {
	
	private static final CityService instance=new CityService();
	private CityService(){}
	public static CityService getInstance(){
		return instance;
	}
	
	/**
	 * 查询所有城市信息
	 * @return
	 */
	public List<City> queryCity(){
		Connection conn=null;
		List<City> list=new ArrayList<>();
		try{
			conn=DBUtil.getConn();
			CityDaoImpl dao=new CityDaoImpl(conn);
			list=dao.queryCity();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return list;
	}
	 
	/**
	 *根据省份对象，查询城市信息 
	 * @param p
	 * @return
	 */
	public List<City> queryCityByProvince(Province p){
		Connection conn=null;
		List<City> list=new ArrayList<>();
		try{
			conn=DBUtil.getConn();
			CityDaoImpl dao=new CityDaoImpl(conn);
			list=dao.queryCityByProvince(p);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return list;
	}
	
	/**
	 *根据省份编码，查询对应城市信息
	 * @param p
	 * @return
	 */
	public List<City> queryCityByID(String id){
		Connection conn=null;
		List<City> list=new ArrayList<>();
		try{
			conn=DBUtil.getConn();
			CityDaoImpl dao=new CityDaoImpl(conn);
			list=dao.queryCityByID(id);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return list;
	}
	
	/**
	 * 根据城市名称获取城市ID
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public String queryCityID(String name){
		Connection conn=null;
		String cityId="-99";
		try {
			conn=DBUtil.getConn();
			CityDaoImpl dao=new CityDaoImpl(conn);
			cityId=dao.queryCityID(name);
			if("".equals(cityId)){
				cityId="-99";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return cityId;
	}
	/*private int test(){
		return 10;
		
	}*/
}
