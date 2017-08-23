package com.sxsram.ssm.entity;

import java.util.Date;

public class PlatformSyDistributedRecord {
	private int id;
	private Date operationTime;
	private int dlbNum;
	private double distributedMoney;
	private double distributedYlj;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public int getDlbNum() {
		return dlbNum;
	}

	public void setDlbNum(int dlbNum) {
		this.dlbNum = dlbNum;
	}

	public double getDistributedMoney() {
		return distributedMoney;
	}

	public void setDistributedMoney(double distributedMoney) {
		this.distributedMoney = distributedMoney;
	}

	public double getDistributedYlj() {
		return distributedYlj;
	}

	public void setDistributedYlj(double distributedYlj) {
		this.distributedYlj = distributedYlj;
	}
}
