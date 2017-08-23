package com.sxsram.ssm.entity;

import java.util.Date;

public class JournalBook {
	// id int primary key auto_increment,
	// indentId varchar(20), -- 订单号 由平台下发单据生成
	// businessId int, -- 商户id
	// clientId int, -- 客户id
	// commodityName varchar(50), -- 商品名称
	// amount double, -- 交易金额
	// premiumRates float(4,2), -- 优惠率
	// journalTime datetime, -- 报单时间
	// flag int default 0, -- 是否处理 0未处理 1已处理

	private int id;
	private String commodityName;
	private double amount;
	private float premiumRates;
	private Date journalTime;
	private double giftJf;
	private double rewardJf;
	private int flag; // 是否审核通过0未通过1通过

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public float getPremiumRates() {
		return premiumRates;
	}

	public void setPremiumRates(float premiumRates) {
		this.premiumRates = premiumRates;
	}

	public Date getJournalTime() {
		return journalTime;
	}

	public void setJournalTime(Date journalTime) {
		this.journalTime = journalTime;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public double getRewardJf() {
		return rewardJf;
	}

	public void setRewardJf(double rewardJf) {
		this.rewardJf = rewardJf;
	}

	public double getGiftJf() {
		return giftJf;
	}

	public void setGiftJf(double giftJf) {
		this.giftJf = giftJf;
	}

	@Override
	public String toString() {
		return "JournalBook [id=" + id + ", commodityName=" + commodityName + ", amount=" + amount + ", premiumRates="
				+ premiumRates + ", journalTime=" + journalTime + ", giftJf=" + giftJf + ", rewardJf=" + rewardJf
				+ ", flag=" + flag + "]";
	}
	
	
}
