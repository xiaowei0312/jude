package com.sxsram.ssm.entity;

import com.sxsram.ssm.util.ConfigUtil.TradeState;
import com.sxsram.ssm.util.ConfigUtil.TradeType;

public class RechargeAndWithDrawRecordExpand extends RechargeAndWithDrawRecord {
	private UserExpand user;
	
	public RechargeAndWithDrawRecordExpand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RechargeAndWithDrawRecordExpand(TradeType recharge, String orderNo, Double tradeNum,Double serviceNum, TradeState paypending,
			String clientIp) {
		super(recharge,orderNo,tradeNum,serviceNum,paypending,clientIp);
	}

	public UserExpand getUser() {
		return user;
	}

	public void setUser(UserExpand user) {
		this.user = user;
	}

}
