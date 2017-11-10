package com.neuedu.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.UserDao;
import com.neuedu.domain.CertType;
import com.neuedu.domain.City;
import com.neuedu.domain.Province;
import com.neuedu.domain.User;
import com.neuedu.domain.UserType;
import com.neuedu.util.DBUtil;
import com.neuedu.util.PageBean;
import com.neuedu.util.ResultSetCallBack;

public class UserDaoImpl implements UserDao {
	
	//数据库连接
	private Connection conn=null;
	//构造dao时传递数据库连接进来
	public UserDaoImpl(Connection conn){
		this.conn=conn;
	}

	@Override
	public boolean addUser(User user) throws Exception {
		// TODO Auto-generated method stub]
		String sql="insert into tab_user(ID,CERT_TYPE,"
				+ "CITY,USER_TYPE,USERNAME,PASSWORD,RULE,REALNAME,SEX,CERT,BIRTHDAY,"
				+ "CONTENT,STATUS,LOGIN_IP,IMAGE_PATH)"
				+ "values (tab_user_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int res=DBUtil.execSQL(conn,sql, 
					new Object[]{user.getCertType().getId(),
							Integer.parseInt(user.getCity().getCityId()),
							user.getUserType().getId(),
							user.getUserName(),
							user.getPassword(),
							user.getRule(),
							user.getRealName(),
							user.getSex(),
							user.getCert(),
							new java.sql.Date(user.getBirthday().getTime()),
							user.getContent(),
							user.getStatus(),
							user.getLoginIp(),
							user.getImagePath()});
		//执行
		if(res==1){
			return true;
		}else{
			return false;
		}
		
		/*PreparedStatement stmt=null;
		try {
			stmt=conn.prepareStatement("insert into tab_user(id,cert_type,"
					+ "city,user_type,username,password,rule,realname,sex,cert,birthday,"
					+ "content,status,login_ip,image_path)"
					+ "values (tab_user_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, user.getCertType().getId());
			stmt.setInt(2, Integer.parseInt(user.getCity().getCityId()));
			stmt.setInt(3, user.getUserType().getId());
			stmt.setString(4, user.getUserName());
			stmt.setString(5, user.getPassword());
			stmt.setString(6, user.getRule());
			stmt.setString(7, user.getRealName());
			stmt.setString(8, user.getSex());
			stmt.setString(9, user.getCert());
			stmt.setDate(10,new java.sql.Date(user.getBirthday().getTime()));
			stmt.setString(11, user.getContent());
			stmt.setString(12, user.getStatus());
			stmt.setString(13, user.getLoginIp());
			stmt.setString(14, user.getImagePath());
			int res=stmt.executeUpdate();
			if(res==1){
				return true;
			}else{
				return false;
			}
		}finally{
			DBUtil.close(stmt);
		}*/
	}

	@Override
	public int delUser(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delUser(String[] ids) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("delete from tab_user where id in(");
		for(String i:ids){
			sb.append(i+",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		int res=DBUtil.execSQL(conn,sb.toString());
		//执行
		return res;
	}

	@Override
	public int updateUser(User user) throws Exception {
		String sql="update tab_user a set"
				+ " a.cert_type=?,"
				+ "a.city=(select c.cityid from tab_city c where c.city=?),"
				+ "a.user_type=?,"
				+ "a.username=?,a.realname=?, a.sex=?,a.cert=?,"
				+ "a.birthday=?,a.content=?,a.login_ip=?,"
				+ "a.image_path=? where a.id=?";
		int res=DBUtil.execSQL(conn,sql, 
					new Object[]{user.getCertType().getId(),
							user.getCity().getCity(),
							user.getUserType().getId(),
							user.getUserName(),
							user.getRealName(),
							user.getSex(),
							user.getCert(),
							new java.sql.Date(user.getBirthday().getTime()),
							user.getContent(),
							user.getLoginIp(),
							user.getImagePath(),
							user.getId()});
		//执行
		return res;
	}

	@Override
	public int modifyPwd(int userId, String newPwd,String oldPwd) throws Exception {
		PreparedStatement stmt=null;
		int recordCnt=0;
		
		try {
			stmt = conn.prepareStatement("update tab_user a set a.password=? where a.id=? and a.password=?");
			stmt.setString(1, newPwd);
			stmt.setInt(2, userId);
			stmt.setString(3, oldPwd);
			recordCnt=stmt.executeUpdate();
		}finally{
			DBUtil.close(stmt);
		}
		return recordCnt;
	}

	@Override
	public User queryUserByID(int userId) throws Exception {
		String str="select a.*,"
				+ "b.content cert_type_name,"
				+ "c.content user_type_name,"
				+ "d.city city_name,d.id city_id,"
				+ "e.province province_name,e.id p_id,"
				+ "e.provinceid provinceid from tab_user a,tab_certtype b,"
				+ "tab_usertype c,tab_city d,tab_province e "
				+ "where a.cert_type=b.id and a.user_type=c.id "
				+ "and a.city=d.cityid(+) and d.father=e.provinceid(+) "
				+ "and a.id=?";
		
		return DBUtil.queryByCallBack(conn, 
				str,
				new Object[]{userId},
				new ResultSetCallBack<User>(){
				
					@Override
					public User parseResultSet(ResultSet rs) throws SQLException {
						User user=new User();
						if(rs.next()){
							user.setId(rs.getInt("ID"));
		 					user.setPassword(rs.getString("PASSWORD"));
							user.setUserName(rs.getString("USERNAME"));
							user.setRule(rs.getString("RULE"));
							user.setRealName(rs.getString("REALNAME"));
							user.setSex(rs.getString("SEX"));
							user.setBirthday(rs.getDate("BIRTHDAY"));
							user.setCert(rs.getString("CERT"));
							//组装证件类型对象
							CertType cType=new CertType();
							cType.setId(rs.getInt("CERT_TYPE"));
							cType.setContent(rs.getString("CERT_TYPE_NAME"));
							user.setCertType(cType);
							//组装城市类型对象
							Province province=new Province();
							province.setProvince(rs.getString("PROVINCE_NAME"));
							province.setId(rs.getInt("P_ID"));
							province.setProvinceId(rs.getString("PROVINCEID"));
							City city=new City();
							city.setCity(rs.getString("CITY_NAME"));
							city.setId(rs.getInt("CITY_ID"));
							//tab_user的city
							city.setCityId(rs.getString("CITY"));
							city.setProvince(province);
							user.setContent(rs.getString("CONTENT"));
							user.setImagePath(rs.getString("IMAGE_PATH"));
							user.setLoginIp(rs.getString("LOGIN_IP"));
							user.setStatus(rs.getString("status"));
							user.setCity(city);
							//组装用户类型
							UserType userType=new UserType();
							userType.setId(rs.getInt("USER_TYPE"));
							userType.setContent(rs.getString("USER_TYPE_NAME"));
							user.setUserType(userType);
						}
						return user;
					}
		});
	}

	@Override
	public List<User> queryUser(User user) throws Exception {
		StringBuffer sb=new StringBuffer();
		String str="select a.*,"
				+ "b.content cert_type_name,"
				+ "c.content user_type_name,"
				+ "d.city city_name,d.id city_id,"
				+ "e.province province_name,e.id p_id,"
				+ "e.provinceid provinceid from tab_user a,tab_certtype b,"
				+ "tab_usertype c,tab_city d,tab_province e "
				+ "where a.cert_type=b.id and a.user_type=c.id "
				+ "and a.city=d.cityid(+) and d.father=e.provinceid(+) "
				+ "and 1=1";
		sb.append(str);
		if(user!=null){
			if(null!=user.getRealName()&&!user.getRealName().equals("")){
				sb.append(" and a.realname like '%").append(user.getRealName()).append("%'");
			}
			if(null!=user.getSex()&&!user.getSex().equals("")){
				sb.append(" and a.sex='").append(user.getSex()).append("'");
			}
			if(user.getCertType().getId()!=0){
				sb.append(" and a.cert_type=").append(user.getCertType().getId());
			}
			if(null!=user.getCert()&&!user.getCert().equals("")){
				sb.append(" and a.cert='").append(user.getCert()).append("'");
			}
			if(user.getUserType().getId()!=0){
				sb.append(" and a.user_type=").append(user.getUserType().getId());
			}
		}
		return DBUtil.queryByCallBack(conn, 
				sb.toString(),
				new ResultSetCallBack<List<User>>(){

					@Override
					public List<User> parseResultSet(ResultSet rs) throws SQLException {
						List<User> list=new ArrayList<User>();
						while(rs.next()){
							User user=new User();
							user.setId(rs.getInt("ID"));
		 					user.setPassword(rs.getString("PASSWORD"));
							user.setUserName(rs.getString("USERNAME"));
							user.setRule(rs.getString("RULE"));
							user.setRealName(rs.getString("REALNAME"));
							user.setSex(rs.getString("SEX"));
							user.setBirthday(rs.getDate("BIRTHDAY"));
							user.setCert(rs.getString("CERT"));
							//组装证件类型对象
							CertType cType=new CertType();
							cType.setId(rs.getInt("CERT_TYPE"));
							cType.setContent(rs.getString("CERT_TYPE_NAME"));
							user.setCertType(cType);
							//组装城市类型对象
							Province province=new Province();
							province.setProvince(rs.getString("PROVINCE_NAME"));
							province.setId(rs.getInt("P_ID"));
							province.setProvinceId(rs.getString("PROVINCEID"));
							City city=new City();
							city.setCity(rs.getString("CITY_NAME"));
							city.setId(rs.getInt("CITY_ID"));
							//tab_user的city
							city.setCityId(rs.getString("CITY"));
							city.setProvince(province);
							user.setContent(rs.getString("CONTENT"));
							user.setImagePath(rs.getString("IMAGE_PATH"));
							user.setLoginIp(rs.getString("LOGIN_IP"));
							user.setStatus(rs.getString("status"));
							user.setCity(city);
							//组装用户类型
							UserType userType=new UserType();
							userType.setId(rs.getInt("USER_TYPE"));
							userType.setContent(rs.getString("USER_TYPE_NAME"));
							user.setUserType(userType);
							list.add(user);
						}
						return list;
					}
		});
	}
	@Override
	public List<User> queryUser(User user,PageBean bean) throws Exception {
		StringBuffer sb=new StringBuffer();
		String str="select a.*,"
				+ "b.content cert_type_name,"
				+ "c.content user_type_name,"
				+ "d.city city_name,d.id city_id,"
				+ "e.province province_name,e.id p_id,"
				+ "e.provinceid provinceid from tab_user a,tab_certtype b,"
				+ "tab_usertype c,tab_city d,tab_province e "
				+ "where a.cert_type=b.id and a.user_type=c.id "
				+ "and a.city=d.cityid(+) and d.father=e.provinceid(+) "
				+ "and 1=1";
		sb.append(str);
		if(user!=null){
			if(null!=user.getRealName()&&!user.getRealName().equals("")){
				sb.append(" and a.realname like'%").append(user.getRealName()).append("%'");
			}
			if(null!=user.getSex()&&!user.getSex().equals("")){
				sb.append(" and a.sex='").append(user.getSex()).append("'");
			}
			if(user.getCertType().getId()!=0){
				sb.append(" and a.cert_type=").append(user.getCertType().getId());
			}
			if(null!=user.getCert()&&!user.getCert().equals("")){
				sb.append(" and a.cert='").append(user.getCert()).append("'");
			}
			if(user.getUserType().getId()!=0){
				sb.append(" and a.user_type=").append(user.getUserType().getId());
			}
		}
		//把SQL排序写在计算总记录数之后，节省数据库资源
		sb.append(" order by a.realname asc");
		//要查询全部记录数
		int recordCnt=DBUtil.queryForInt(conn,"select count(1) from ("+sb.toString()+")");
		bean.setRecordCnt(recordCnt);
		return DBUtil.queryByCallBackPage(conn, 
				sb.toString(),
				bean,
				new ResultSetCallBack<List<User>>(){

					@Override
					public List<User> parseResultSet(ResultSet rs) throws SQLException {
						List<User> list=new ArrayList<User>();
						while(rs.next()){
							User user=new User();
							user.setId(rs.getInt("ID"));
		 					user.setPassword(rs.getString("PASSWORD"));
							user.setUserName(rs.getString("USERNAME"));
							user.setRule(rs.getString("RULE"));
							user.setRealName(rs.getString("REALNAME"));
							user.setSex(rs.getString("SEX"));
							user.setBirthday(rs.getDate("BIRTHDAY"));
							user.setCert(rs.getString("CERT"));
							//组装证件类型对象
							CertType cType=new CertType();
							cType.setId(rs.getInt("CERT_TYPE"));
							cType.setContent(rs.getString("CERT_TYPE_NAME"));
							user.setCertType(cType);
							//组装城市类型对象
							Province province=new Province();
							province.setProvince(rs.getString("PROVINCE_NAME"));
							province.setId(rs.getInt("P_ID"));
							province.setProvinceId(rs.getString("PROVINCEID"));
							City city=new City();
							city.setCity(rs.getString("CITY_NAME"));
							city.setId(rs.getInt("CITY_ID"));
							//tab_user的city
							city.setCityId(rs.getString("CITY"));
							city.setProvince(province);
							user.setContent(rs.getString("CONTENT"));
							user.setImagePath(rs.getString("IMAGE_PATH"));
							user.setLoginIp(rs.getString("LOGIN_IP"));
							user.setStatus(rs.getString("status"));
							user.setCity(city);
							//组装用户类型
							UserType userType=new UserType();
							userType.setId(rs.getInt("USER_TYPE"));
							userType.setContent(rs.getString("USER_TYPE_NAME"));
							user.setUserType(userType);
							list.add(user);
						}
						return list;
					}
		});
	}

	@Override
	public User login(String name, String pwd) throws Exception {
		return DBUtil.queryByCallBack(conn, 
				"select a.*,"
				+ "b.content cert_type_name,"
				+ "c.content user_type_name,"
				+ "d.city city_name,d.id city_id,"
				+ "e.province province_name,e.id p_id,"
				+ "e.provinceid provinceid from tab_user a,tab_certtype b,"
				+ "tab_usertype c,tab_city d,tab_province e "
				+ "where a.cert_type=b.id and a.user_type=c.id "
				+ "and a.city=d.cityid(+) and d.father=e.provinceid(+) "
				+ "and a.username=? and a.password=?",
				new Object[]{name,pwd}, 
				new ResultSetCallBack<User>(){
					@Override
					public User parseResultSet(ResultSet rs) throws SQLException {
						User user=null;
						if(rs.next()){
							user=new User();
		 					user.setId(rs.getInt("ID"));
		 					user.setPassword(rs.getString("PASSWORD"));
							user.setUserName(rs.getString("USERNAME"));
							user.setRule(rs.getString("RULE"));
							user.setRealName(rs.getString("REALNAME"));
							user.setSex(rs.getString("SEX"));
							user.setBirthday(rs.getDate("BIRTHDAY"));
							user.setCert(rs.getString("CERT"));
							//组装证件类型对象
							CertType cType=new CertType();
							cType.setId(rs.getInt("CERT_TYPE"));
							cType.setContent(rs.getString("CERT_TYPE_NAME"));
							user.setCertType(cType);
							//组装城市类型对象
							Province province=new Province();
							province.setProvince(rs.getString("PROVINCE_NAME"));
							province.setId(rs.getInt("P_ID"));
							province.setProvinceId(rs.getString("PROVINCEID"));
							City city=new City();
							city.setCity(rs.getString("CITY_NAME"));
							city.setId(rs.getInt("CITY_ID"));
							//tab_user的city
							city.setCityId(rs.getString("CITY"));
							city.setProvince(province);
							user.setContent(rs.getString("CONTENT"));
							user.setImagePath(rs.getString("IMAGE_PATH"));
							user.setLoginIp(rs.getString("LOGIN_IP"));
							user.setStatus(rs.getString("status"));
							user.setCity(city);
							//组装用户类型
							UserType userType=new UserType();
							userType.setId(rs.getInt("USER_TYPE"));
							userType.setContent(rs.getString("USER_TYPE_NAME"));
							user.setUserType(userType);
						}
						return user;
					}
		});
		// TODO Auto-generated method stub
		/*PreparedStatement stmt=null;
		ResultSet rs=null;
		User user=null;
		try {
			stmt = conn.prepareStatement("select * from tab_user where username=?"
					+ " and password=?");
			stmt.setString(1,name);
			stmt.setString(2,pwd);
			rs=stmt.executeQuery();
			if(rs.next()){
				user=new User();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("USERNAME"));
				user.setRule(rs.getString("RULE"));
				user.setRealName(rs.getString("REALNAME"));
			}
		}finally{
			DBUtil.close(stmt,rs);
		}
		return user;*/
	}

	
	@Override
	/**
	 * 判断指定的登录名是否存在
	 * 
	 */
	public int isloginNameExists(String loginName) throws Exception {
		return DBUtil.queryForInt(conn, 
				"select count(1) cnt from tab_user where username=?", 
				new Object[]{loginName});
		/*PreparedStatement stmt=null;
		ResultSet rs=null;
		int loginFlag=-1;
		try {
			stmt = conn.prepareStatement("select count(1) cnt from tab_user where username=?");
			stmt.setString(1,loginName);
			rs=stmt.executeQuery();
			if(rs.next()){
				loginFlag=rs.getInt("CNT");
			}
		}finally{
			DBUtil.close(stmt,rs);
		}
		return loginFlag;*/
	}
	
	@Override
	public int[] querySex() throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int[] sex=new int[2];
		String sql="select a.cn female,b.cn male from (select count(1) cn from tab_user where sex='F') a,"
				+ " (select count(1) cn from tab_user where sex='M') b";
		try {
			DBUtil.queryForInt(conn, sql);
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			if(rs.next()){
				sex[0]=rs.getInt("FEMALE");
				sex[1]=rs.getInt("MALE");
			}
		} finally {
			DBUtil.close(stmt, rs);
		}
		return sex;
	}

}
