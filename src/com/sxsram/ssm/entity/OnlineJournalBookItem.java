package com.sxsram.ssm.entity;

public class OnlineJournalBookItem {
	private Integer id;
	private OnlineJournalBook onlineJournalBook;
	private OnlineCommodityModel onlineCommodityModel;
	private Integer num; // 数量
	private Double amount; // 单价
	private Integer journalId;
	private Integer commodityModelId;

	public OnlineJournalBookItem(Integer num, Double amount, Integer commodityModelId) {
		super();
		this.num = num;
		this.amount = amount;
		// this.journalId = journalId;
		this.commodityModelId = commodityModelId;
	}

	public OnlineJournalBookItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getJournalId() {
		return journalId;
	}

	public void setJournalId(Integer journalId) {
		this.journalId = journalId;
	}

	public Integer getCommodityModelId() {
		return commodityModelId;
	}

	public void setCommodityModelId(Integer commodityModelId) {
		this.commodityModelId = commodityModelId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OnlineJournalBook getOnlineJournalBook() {
		return onlineJournalBook;
	}

	public void setOnlineJournalBook(OnlineJournalBook onlineJournalBook) {
		this.onlineJournalBook = onlineJournalBook;
	}

	public OnlineCommodityModel getOnlineCommodityModel() {
		return onlineCommodityModel;
	}

	public void setOnlineCommodityModel(OnlineCommodityModel onlineCommodityModel) {
		this.onlineCommodityModel = onlineCommodityModel;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
