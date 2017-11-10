package com.neuedu.domain;

import java.util.Date;

public class User {
	private int id;
	private City city;
	private UserType userType;
	private CertType certType;
	private String userName;
	private String password;
	//权限（1、管理员 2、普通用户）
	private String rule;
	private String realName;
	//性别（1、男 2、女）
	private String sex;
	private String cert;
	private Date birthday;
	private String content;
	//状态（0、无效 1、有效）
	private String status;
	private String loginIp;
	//用户头像地址
	private String imagePath;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public CertType getCertType() {
		return certType;
	}
	public void setCertType(CertType certType) {
		this.certType = certType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCert() {
		return cert;
	}
	public void setCert(String cert) {
		this.cert = cert;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", city=" + city + ", userType=" + userType + ", certType=" + certType + ", userName="
				+ userName + ", password=" + password + ", rule=" + rule + ", realName=" + realName + ", sex=" + sex
				+ ", cert=" + cert + ", date=" +birthday + ", content=" + content + ", status=" + status + ", loginIp="
				+ loginIp + ", imagePath=" + imagePath + "]";
	}
	
	public User(int id, City city, UserType userType, CertType certType, String userName, String password, String rule,
			String realName, String sex, String cert, Date birthday, String content, String status, String loginIp,
			String imagePath) {
		super();
		this.id = id;
		this.city = city;
		this.userType = userType;
		this.certType = certType;
		this.userName = userName;
		this.password = password;
		this.rule = rule;
		this.realName = realName;
		this.sex = sex;
		this.cert = cert;
		this.birthday = birthday;
		this.content = content;
		this.status = status;
		this.loginIp = loginIp;
		this.imagePath = imagePath;
	}
	public User() {
		super();
	}

}
