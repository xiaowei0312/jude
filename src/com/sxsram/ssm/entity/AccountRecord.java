package com.sxsram.ssm.entity;

import java.util.Date;

public class AccountRecord {
	private int id;
	private int operation;
	private Date operateTime;
	private double operateNum;
	private String operateDesc;
	private double balance; // 当前余额字段

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public double getOperateNum() {
		return operateNum;
	}

	public void setOperateNum(double operateNum) {
		this.operateNum = operateNum;
	}

	public String getOperateDesc() {
		return operateDesc;
	}

	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}

	@Override
	public String toString() {
		return "AccountRecord [id=" + id + ", operation=" + operation + ", operateTime=" + operateTime + ", operateNum="
				+ operateNum + ", operateDesc=" + operateDesc + "]";
	}

}
