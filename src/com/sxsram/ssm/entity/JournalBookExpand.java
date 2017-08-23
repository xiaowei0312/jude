package com.sxsram.ssm.entity;

public class JournalBookExpand extends JournalBook {
	private Indent indent;
	private UserExpand business;
	private UserExpand client;
	private UserExpand reward;
	private CommodityType commodityType;
	
	public UserExpand getReward() {
		return reward;
	}

	public void setReward(UserExpand reward) {
		this.reward = reward;
	}

	public CommodityType getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(CommodityType commodityType) {
		this.commodityType = commodityType;
	}

	public Indent getIndent() {
		return indent;
	}

	public void setIndent(Indent indent) {
		this.indent = indent;
	}

	public UserExpand getBusiness() {
		return business;
	}

	public void setBusiness(UserExpand business) {
		this.business = business;
	}

	public UserExpand getClient() {
		return client;
	}

	public void setClient(UserExpand client) {
		this.client = client;
	}

}
