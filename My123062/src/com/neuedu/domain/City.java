package com.neuedu.domain;

/**
 * 城市实体
 * @author 杨雪平
 * 2017年9月29日
 */
public class City {
	private int id;
	private String cityId;
	private String city;
	//通过类之间的组合，保存信息
	private Province province;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", cityId=" + cityId + ", city=" + city + ", province=" + province + "]";
	}
	
	public City(int id, String cityId, String city, Province province) {
		super();
		this.id = id;
		this.cityId = cityId;
		this.city = city;
		this.province = province;
	}
	public City() {
		super();
	}
	
	
}
