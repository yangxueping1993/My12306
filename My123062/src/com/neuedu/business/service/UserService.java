package com.neuedu.business.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.impl.UserDaoImpl;
import com.neuedu.domain.User;
import com.neuedu.util.DBUtil;
import com.neuedu.util.PageBean;

public class UserService extends BaseService {
	
	//1.初始化实例
	private static final UserService instance=new UserService();
	
	//2.把该类的构造函数设置为私有
	private UserService(){}
	
	//3.需要提供一个静态方法获取这一单个实例
	public static UserService getInstance(){
		return instance;
	}
	
	/**
	 * 新增用户信息
	 * @param user
	 * @return true:成功 false：失败：
	 * @throws Exception：dao层抛出异常，由service层处理
	 */
	public boolean addUser(User user){
		Connection conn=DBUtil.getConn();
		//DBUtil.beginTransaction(conn);
		UserDaoImpl userDao=new UserDaoImpl(conn);
		boolean saveFlag=true;
		try {
			saveFlag=userDao.addUser(user);
			//DBUtil.commit(conn);
		} catch (Exception e) {
			saveFlag=false;
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return saveFlag;
	}
	
	/**
	 * 根据集合中的id删除用户信息（单个删除）
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delUser(int id){
		int cnt=0;
		try {
//			cnt=dao.delUser(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}
	
	/**
	 * 根据集合中的id删除用户信息（批量删除）
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int delUser(String[] ids){
		Connection conn=DBUtil.getConn();
		UserDaoImpl userDao=new UserDaoImpl(conn);
		int cnt=-1;
		try {
			cnt=userDao.delUser(ids);
		} catch (Exception e) {
			cnt=-1;
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return cnt;
	}
	
	/**
	 * 根据user中的id修改对应的信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int updateUser(User user){
		Connection conn=DBUtil.getConn();
		UserDaoImpl userDao=new UserDaoImpl(conn);
		int cnt=-1;
		try {
			cnt=userDao.updateUser(user);
			//DBUtil.commit(conn);
		} catch (Exception e) {
			cnt=-1;
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return cnt;
	}
	
	/**
	 * 根据用户userId修改密码
	 * @param userId
	 * @param newPwd
	 * @return
	 * @throws Exception
	 */
	public boolean changePwd(int userId, String newPwd,String oldPwd){
		Connection conn=null;
		UserDaoImpl userDao=null;
		boolean execFlag=false;
		try {
			conn=DBUtil.getConn();
			userDao=new UserDaoImpl(conn);
			int cnt=userDao.modifyPwd(userId, newPwd, oldPwd);
			if(cnt>0){
				execFlag=true;
			}
		} catch (Exception e) {
			execFlag=false;
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return execFlag;
	}
	
	/**
	 * 根据用户userId查询单个用户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public User queryUserByID(int userId) {
		Connection conn=DBUtil.getConn();
		UserDaoImpl userDao=new UserDaoImpl(conn);
		User user=null;
		try {
			user=userDao.queryUserByID(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return user;
		
	}
	
	/**
	 * 根据查询实体，查询符合条件的多个用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<User> queryUser(User user){
		Connection conn=DBUtil.getConn();
		List<User> list=new ArrayList<>();
		UserDaoImpl userDao=new UserDaoImpl(conn);
		try{
			list=userDao.queryUser(user);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return list;
	}
	
	/**
	 * 根据查询实体，查询符合条件的多个用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<User> queryUser(User user,PageBean bean){
		Connection conn=DBUtil.getConn();
		List<User> list=new ArrayList<>();
		UserDaoImpl userDao=new UserDaoImpl(conn);
		try{
			list=userDao.queryUser(user,bean);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return list;
	}
	
	/**
	 * 根据用户输入的用户名和密码判断用户是否可以登录
	 * @param name
	 * @param pwd
	 * @return True：可以登录   false：不可以
	 */
	public User login(String loginName, String pwd) {
		Connection conn=DBUtil.getConn();
		UserDaoImpl userDao=new UserDaoImpl(conn);
		User user=null;
		try {
			user=userDao.login(loginName, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return user;
	}
	public boolean isLoginNameExists(String loginName) {
		Connection conn=null;
		UserDaoImpl userDao=null;
		boolean loginFlag=false;
		try {
			conn=DBUtil.getConn();
			userDao=new UserDaoImpl(conn);
			int cnt=userDao.isloginNameExists(loginName);
			if(cnt>0){
				loginFlag=true;
			}
		} catch (Exception e) {
			loginFlag=false;
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return loginFlag;
	}
	
	/**
	 * 验证用户名是否存在，排除自身
	 * @param loginNamem
	 * @param userID
	 * @return
	 */
	public boolean isExistsForUpdate(String loginName,int userID){
		boolean res=false;
		Connection conn=null;
		try {
			conn=DBUtil.getConn();
			int cnt=DBUtil.queryForInt(conn, 
					"select count(1) from tab_user where username=? and id<>?",
					new Object[]{loginName,userID});
			if(cnt>0){
				res=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return res;
	}
	
	public int[] querySex() {
		int[] sex=new int[2];
		Connection conn=DBUtil.getConn();
		UserDaoImpl userDao=new UserDaoImpl(conn);
		try {
			sex=userDao.querySex();
			//DBUtil.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return sex;
	}
}
