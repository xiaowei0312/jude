package com.sxsram.ssm.util;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class WechatOAuth2User {
	@SerializedName("openid")
	private String openId;
	
	@SerializedName("nickname")
	private String nickname;
	
	@SerializedName("sex")
	private int sex;
	
	@SerializedName("province")
	private String province;
	
	@SerializedName("city")
	private String city;
	
	@SerializedName("country")
	private String country;
	
	@SerializedName("headimgurl")
	private String headImgUrl;
	
	@SerializedName("privilege")
	private List<String> privilegeList;
	
	@SerializedName("unionid")
	private String unionId;
	
	@SerializedName("language")
	private String language;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public List<String> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<String> privilegeList) {
		this.privilegeList = privilegeList;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "WechatOAuth2User [openId=" + openId + ", nickname=" + nickname + ", sex=" + sex + ", province="
				+ province + ", city=" + city + ", country=" + country + ", headImgUrl=" + headImgUrl
				+ ", privilegeList=" + privilegeList + ", unionId=" + unionId + "]";
	}
}
