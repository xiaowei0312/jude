package com.sxsram.ssm.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OnlineJournalBook {
	private Integer id;
	private String orderNo;	// 订单编号,uuid
	private Double totalAmount;
	private Integer receiveType;
	private Double postage;
	private Double postAddress;
	private String postReceiveName;
	private String postReceivePhone;
	private String takeTheirStore;
	private Integer status;
	private Date journalTime;

	private Integer userId;// 消费则ID
	private UserExpand user;// 消费者
	private Integer recvCommodityAddressId;// 收获地址ID
	private RecvCommodityAddress recvCommodityAddress;// 收货地址
	//订单项
	private List<OnlineJournalBookItem> items = new ArrayList<>();

	
	public enum OrderStatus{
		PENDING,UNDO,ALL
	}
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public List<OnlineJournalBookItem> getItems() {
		return items;
	}

	public void setItems(List<OnlineJournalBookItem> items) {
		this.items = items;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRecvCommodityAddressId() {
		return recvCommodityAddressId;
	}

	public void setRecvCommodityAddressId(Integer recvCommodityAddressId) {
		this.recvCommodityAddressId = recvCommodityAddressId;
	}

	public RecvCommodityAddress getRecvCommodityAddress() {
		return recvCommodityAddress;
	}

	public void setRecvCommodityAddress(RecvCommodityAddress recvCommodityAddress) {
		this.recvCommodityAddress = recvCommodityAddress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(Integer receiveType) {
		this.receiveType = receiveType;
	}

	public Double getPostage() {
		return postage;
	}

	public void setPostage(Double postage) {
		this.postage = postage;
	}

	public Double getPostAddress() {
		return postAddress;
	}

	public void setPostAddress(Double postAddress) {
		this.postAddress = postAddress;
	}

	public String getPostReceiveName() {
		return postReceiveName;
	}

	public void setPostReceiveName(String postReceiveName) {
		this.postReceiveName = postReceiveName;
	}

	public String getPostReceivePhone() {
		return postReceivePhone;
	}

	public void setPostReceivePhone(String postReceivePhone) {
		this.postReceivePhone = postReceivePhone;
	}

	public String getTakeTheirStore() {
		return takeTheirStore;
	}

	public void setTakeTheirStore(String takeTheirStore) {
		this.takeTheirStore = takeTheirStore;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getJournalTime() {
		return journalTime;
	}

	public void setJournalTime(Date journalTime) {
		this.journalTime = journalTime;
	}

	public UserExpand getUser() {
		return user;
	}

	public void setUser(UserExpand user) {
		this.user = user;
	}
}
