package com.neuedu.domain;

/**
 * 省份实体
 * @author 杨雪平
 * 2017年9月29日
 */
public class Province {
	private int id;
	private String provinceId;
	private String province;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	public Province(int id, String provinceId, String province) {
		super();
		this.id = id;
		this.provinceId = provinceId;
		this.province = province;
	}
	@Override
	public String toString() {
		return "Province [id=" + id + ", provinceId=" + provinceId + ", province=" + province + "]";
	}
	public Province() {
		super();
	}
	public Province(String province) {
		super();
		this.province = province;
	}

}
