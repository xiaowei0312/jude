package com.sxsram.ssm.entity;

import java.util.List;

public class AccountExpand extends Account {
	private User user;
	// 账户记录
	private List<AccountRecordExpand> accountRecords;
	
	public AccountExpand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountExpand(String accountNo) {
		super(accountNo);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<AccountRecordExpand> getAccountRecords() {
		return accountRecords;
	}

	public void setAccountRecords(List<AccountRecordExpand> accountRecords) {
		this.accountRecords = accountRecords;
	}

	@Override
	public String toString() {
		return "AccountExpand [user=" + user + ", accountRecords=" + accountRecords + ", getId()=" + getId()
				+ ", getAccountNo()=" + getAccountNo() + ", getAccountBlance()=" + getAccountBalance()
				+ ", getTotalPlateformIncomings()=" + getTotalPlatformIncomings() + ", getTotalPlateformOutgoings()="
				+ getTotalPlatformOutgoings() + ", getTotalUserIncomings()=" + getTotalUserIncomings()
				+ ", getTotalUserOutgoings()=" + getTotalUserOutgoings() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
