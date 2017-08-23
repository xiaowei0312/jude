package com.sxsram.ssm.entity;

import java.util.Date;

public class ShoppingMall {
	private Integer id;
	private Double mallPos_lat;
	private Double mallPos_lnt;
	private String mallName;
	private String mallDesc;
	private String mallMainPicUrl;
	private String mallPicUrl1;
	private String mallPicUrl2;
	private String mallPicUrl3;
	private String mallPicUrl4;
	private String mallPicUrl5;
	private String mallLinkMan;
	private String mallPhone;
	private String mallAddress;
	private Integer locked;
	private Date createTime;
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getMallPos_lat() {
		return mallPos_lat;
	}
	public void setMallPos_lat(Double mallPos_lat) {
		this.mallPos_lat = mallPos_lat;
	}
	public Double getMallPos_lnt() {
		return mallPos_lnt;
	}
	public void setMallPos_lnt(Double mallPos_lnt) {
		this.mallPos_lnt = mallPos_lnt;
	}
	public String getMallName() {
		return mallName;
	}
	public void setMallName(String mallName) {
		this.mallName = mallName;
	}
	public String getMallDesc() {
		return mallDesc;
	}
	public void setMallDesc(String mallDesc) {
		this.mallDesc = mallDesc;
	}
	public String getMallMainPicUrl() {
		return mallMainPicUrl;
	}
	public void setMallMainPicUrl(String mallMainPicUrl) {
		this.mallMainPicUrl = mallMainPicUrl;
	}
	public String getMallPicUrl1() {
		return mallPicUrl1;
	}
	public void setMallPicUrl1(String mallPicUrl1) {
		this.mallPicUrl1 = mallPicUrl1;
	}
	public String getMallPicUrl2() {
		return mallPicUrl2;
	}
	public void setMallPicUrl2(String mallPicUrl2) {
		this.mallPicUrl2 = mallPicUrl2;
	}
	public String getMallPicUrl3() {
		return mallPicUrl3;
	}
	public void setMallPicUrl3(String mallPicUrl3) {
		this.mallPicUrl3 = mallPicUrl3;
	}
	public String getMallPicUrl4() {
		return mallPicUrl4;
	}
	public void setMallPicUrl4(String mallPicUrl4) {
		this.mallPicUrl4 = mallPicUrl4;
	}
	public String getMallPicUrl5() {
		return mallPicUrl5;
	}
	public void setMallPicUrl5(String mallPicUrl5) {
		this.mallPicUrl5 = mallPicUrl5;
	}
	public String getMallLinkMan() {
		return mallLinkMan;
	}
	public void setMallLinkMan(String mallLinkMan) {
		this.mallLinkMan = mallLinkMan;
	}
	public String getMallPhone() {
		return mallPhone;
	}
	public void setMallPhone(String mallPhone) {
		this.mallPhone = mallPhone;
	}
	public String getMallAddress() {
		return mallAddress;
	}
	public void setMallAddress(String mallAddress) {
		this.mallAddress = mallAddress;
	}
	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
}
