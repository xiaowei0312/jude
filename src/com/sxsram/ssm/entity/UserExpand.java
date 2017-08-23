package com.sxsram.ssm.entity;

import java.util.List;

public class UserExpand extends User {
	private RoleExpand role;
	private Certification certification;
	private List<UserAccount> userAccounts;
	private AccountExpand jfAccount;
	private AccountExpand dlbAccount;
	private AccountExpand moneyAccount;
	private AccountExpand yljAccount;
	private AccountExpand jdbAccount;
	private double changeValue;	//账户余额改变
	private Address provinceObj;
	private Address cityObj;
	private Address areaObj;
	private String keywords;
	private UserExpand proxyUser;
	
	//额外信息
	private UserExtra userExtra;
	
	public UserExtra getUserExtra() {
		return userExtra;
	}

	public void setUserExtra(UserExtra userExtra) {
		this.userExtra = userExtra;
	}

	public AccountExpand getJdbAccount() {
		return jdbAccount;
	}

	public void setJdbAccount(AccountExpand jdbAccount) {
		this.jdbAccount = jdbAccount;
	}

	public UserExpand getProxyUser() {
		return proxyUser;
	}

	public void setProxyUser(UserExpand proxyUser) {
		this.proxyUser = proxyUser;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Address getProvinceObj() {
		return provinceObj;
	}

	public void setProvinceObj(Address provinceObj) {
		this.provinceObj = provinceObj;
	}

	public Address getCityObj() {
		return cityObj;
	}

	public void setCityObj(Address cityObj) {
		this.cityObj = cityObj;
	}

	public Address getAreaObj() {
		return areaObj;
	}

	public void setAreaObj(Address areaObj) {
		this.areaObj = areaObj;
	}

	public double getChangeValue() {
		return changeValue;
	}

	public void setChangeValue(double changeValue) {
		this.changeValue = changeValue;
	}

	public Certification getCertification() {
		return certification;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	public RoleExpand getRole() {
		return role;
	}

	public void setRole(RoleExpand role) {
		this.role = role;
	}

	public List<UserAccount> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(List<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}

	public AccountExpand getJfAccount() {
		return jfAccount;
	}

	public void setJfAccount(AccountExpand jfAccount) {
		this.jfAccount = jfAccount;
	}

	public AccountExpand getDlbAccount() {
		return dlbAccount;
	}

	public void setDlbAccount(AccountExpand dlbAccount) {
		this.dlbAccount = dlbAccount;
	}

	public AccountExpand getMoneyAccount() {
		return moneyAccount;
	}

	public void setMoneyAccount(AccountExpand moneyAccount) {
		this.moneyAccount = moneyAccount;
	}

	public AccountExpand getYljAccount() {
		return yljAccount;
	}

	public void setYljAccount(AccountExpand yljAccount) {
		this.yljAccount = yljAccount;
	}

	@Override
	public String toString() {
		return "UserExpand [role=" + role + ", certification=" + certification + ", userAccounts=" + userAccounts
				+ ", jfAccount=" + jfAccount + ", dlbAccount=" + dlbAccount + ", moneyAccount=" + moneyAccount
				+ ", yljAccount=" + yljAccount + ", getId()=" + getId() + ", getUsername()=" + getUsername()
				+ ", getPassword()=" + getPassword() + ", getProvince()=" + getProvince() + ", getCity()=" + getCity()
				+ ", getArea()=" + getArea() + ", getPhone()=" + getPhone() + ", getOpenId()=" + getOpenId()
				+ ", getHeadImgUrl()=" + getHeadImgUrl() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
