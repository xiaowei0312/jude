package com.sxsram.ssm.entity;

import java.util.Date;

/**
 * 养老金每月转存记录
 * 
 * @author Administrator
 *
 */
public class PlatformYljSavedRecord {
	// id int auto_increment,
	// operationDate varchar(20), -- 操作年月
	// prevMonthBalance double, -- 上月转入
	// currMonthDistributed double, -- 当月累计
	// currMonthSaved double, -- 当月转存
	// currMonthBalance double, -- 当月结余
	// userId int, -- 对应Account
	// primary key(id),
	// foreign key(userId) references t_user(id)

	private int id;
	private Date savedDate; // 操作年月
	private double prevMonthBalance; // 上月转入
	private double currMonthDistributed; // 当月累计
	private double currMonthSaved; // 当月转存
	private double currMonthBalance; // 当月结余

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getSavedDate() {
		return savedDate;
	}

	public void setSavedDate(Date savedDate) {
		this.savedDate = savedDate;
	}

	public double getPrevMonthBalance() {
		return prevMonthBalance;
	}

	public void setPrevMonthBalance(double prevMonthBalance) {
		this.prevMonthBalance = prevMonthBalance;
	}

	public double getCurrMonthDistributed() {
		return currMonthDistributed;
	}

	public void setCurrMonthDistributed(double currMonthDistributed) {
		this.currMonthDistributed = currMonthDistributed;
	}

	public double getCurrMonthSaved() {
		return currMonthSaved;
	}

	public void setCurrMonthSaved(double currMonthSaved) {
		this.currMonthSaved = currMonthSaved;
	}

	public double getCurrMonthBalance() {
		return currMonthBalance;
	}

	public void setCurrMonthBalance(double currMonthBalance) {
		this.currMonthBalance = currMonthBalance;
	}

}
