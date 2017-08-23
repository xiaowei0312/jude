package com.sxsram.ssm.entity;

import java.util.Date;

import com.sxsram.ssm.util.ConfigUtil;
import com.sxsram.ssm.util.ConfigUtil.TradeState;
import com.sxsram.ssm.util.ConfigUtil.TradeType;

public class RechargeAndWithDrawRecord {
	private Integer id;
	private ConfigUtil.TradeType operateType;
	private String orderNo;
	private Double operateNum;
	private Date operateTime;
	private ConfigUtil.TradeState operateState;
	private String operateUserIp;
	private double serviceNum;

	public RechargeAndWithDrawRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RechargeAndWithDrawRecord(TradeType operateType, String orderNo, Double operateNum,Double serviceNum, 
			TradeState operateState, String operateUserIp) {
		super();
		this.operateType = operateType;
		this.orderNo = orderNo;
		this.operateNum = operateNum;
		this.serviceNum = serviceNum;
		this.operateState = operateState;
		this.operateUserIp = operateUserIp;
	}

	public RechargeAndWithDrawRecord(Integer id, TradeType operateType, String orderNo, Double operateNum,Double serviceNum,
			Date operateTime, TradeState operateState, String operateUserIp) {
		super();
		this.id = id;
		this.operateType = operateType;
		this.orderNo = orderNo;
		this.operateNum = operateNum;
		this.operateTime = operateTime;
		this.operateState = operateState;
		this.operateUserIp = operateUserIp;
	}
	
	public double getServiceNum() {
		return serviceNum;
	}

	public void setServiceNum(double serviceNum) {
		this.serviceNum = serviceNum;
	}

	public String getOperateUserIp() {
		return operateUserIp;
	}

	public void setOperateUserIp(String operateUserIp) {
		this.operateUserIp = operateUserIp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Double getOperateNum() {
		return operateNum;
	}

	public void setOperateNum(Double operateNum) {
		this.operateNum = operateNum;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public int getOperateType() {
		return operateType.ordinal();
	}

	public void setOperateType(int operateType) {
		this.operateType = ConfigUtil.TradeType.values()[operateType];
	}

	public int getOperateState() {
		return operateState.ordinal();
	}

	public void setOperateState(int operateState) {
		this.operateState = ConfigUtil.TradeState.values()[operateState];
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "RechargeAndWithDrawRecord [id=" + id + ", operateType=" + getOperateType() + ", orderNo=" + orderNo
				+ ", operateNum=" + operateNum + ", operateTime=" + operateTime + ", operateState=" + operateState
				+ ", operateUserIp=" + operateUserIp + ", serviceNum=" + serviceNum + "]";
	}
}
