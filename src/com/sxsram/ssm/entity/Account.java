package com.sxsram.ssm.entity;

public class Account {
	private int id;
	private String accountNo;
	private double accountBalance;
	private double totalPlatformIncomings;
	private double totalPlatformOutgoings;
	private double totalUserIncomings;
	private double totalUserOutgoings;

	public Account(String accountNo) {
		super();
		this.accountNo = accountNo;
	}

	public Account(int id, String accountNo, double accountBalance, double totalPlatformIncomings,
			double totalPlatformOutgoings, double totalUserIncomings, double totalUserOutgoings) {
		super();
		this.id = id;
		this.accountNo = accountNo;
		this.accountBalance = accountBalance;
		this.totalPlatformIncomings = totalPlatformIncomings;
		this.totalPlatformOutgoings = totalPlatformOutgoings;
		this.totalUserIncomings = totalUserIncomings;
		this.totalUserOutgoings = totalUserOutgoings;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public double getTotalPlatformIncomings() {
		return totalPlatformIncomings;
	}

	public void setTotalPlatformIncomings(double totalPlatformIncomings) {
		this.totalPlatformIncomings = totalPlatformIncomings;
	}

	public double getTotalPlatformOutgoings() {
		return totalPlatformOutgoings;
	}

	public void setTotalPlatformOutgoings(double totalPlatformOutgoings) {
		this.totalPlatformOutgoings = totalPlatformOutgoings;
	}

	public double getTotalUserIncomings() {
		return totalUserIncomings;
	}

	public void setTotalUserIncomings(double totalUserIncomings) {
		this.totalUserIncomings = totalUserIncomings;
	}

	public double getTotalUserOutgoings() {
		return totalUserOutgoings;
	}

	public void setTotalUserOutgoings(double totalUserOutgoings) {
		this.totalUserOutgoings = totalUserOutgoings;
	}

	
}
