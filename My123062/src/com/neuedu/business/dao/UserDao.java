package com.neuedu.business.dao;

import java.util.List;

import com.neuedu.domain.User;
import com.neuedu.util.PageBean;

public interface UserDao {
	
	/**
	 * 新增用户信息
	 * @param user
	 * @return true:成功 false：失败：
	 * @throws Exception：dao层抛出异常，由service层处理
	 */
	public boolean addUser(User user) throws Exception;
	
	/**
	 * 根据集合中的id删除用户信息（单个删除）
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delUser(int id) throws Exception;
	
	/**
	 * 根据集合中的id删除用户信息（批量删除）
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int delUser(String[] ids) throws Exception;
	
	/**
	 * 根据user中的id修改对应的信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int updateUser(User user) throws Exception;
	
	/**
	 * 根据用户userId修改密码
	 * @param userId
	 * @param newPwd
	 * @return
	 * @throws Exception
	 */
	public int modifyPwd(int userId,String newPwd,String oldPwd) throws Exception;
	
	/**
	 * 根据用户userId查询单个用户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public User queryUserByID(int userId) throws Exception;
	
	/**
	 * 根据查询实体，查询符合条件的多个用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<User> queryUser(User user) throws Exception;
	
	/**
	 * 验证登录
	 * 
	 * @param name
	 * @param pwd
	 * @return True：可以登录   false：不可以
	 */
	public User login(String loginName, String pwd) throws Exception;
	
	/**
	 * 判断用户名是否存在
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	public int isloginNameExists(String loginName) throws Exception ;

	public List<User> queryUser(User user, PageBean bean) throws Exception;

	public int[] querySex() throws Exception;

}
