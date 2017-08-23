package com.sxsram.ssm.entity;

import java.util.Date;

public class GlobalPrams {

	// `cashRatio` float(5,2) NULL , -- 现金比例
	// `yljRatio` float(5,2) NULL , -- 养老金比例
	// `totalReCash` float(9,2) NULL , -- 每日返还总额
	// `cashByOneDlb` float(5,2) NULL , -- 每日每个聚财宝返还金额
	// `vipJfRatio` float(5,2) NULL , -- 会员积分获得比例
	// `busJfRatio` float(5,2) NULL , -- 商家积分获得比例
	// `oneDlbTotalIncom` float(9,2) NULL , -- 得利宝收入上限
	// `beginTime` datetime NULL , -- 启用时间
	// `endTime` datetime NULL , -- 停用时间
	// `checkLimitAmount` float(9,2) NULL, -- 报单审核阀值
	// `flag` int NULL DEFAULT 0 , -- 启用标志 0为启用，1为停用 只能有一条记录为0
	private Integer id;
	private Double cashRatio;
	private Double yljRatio;
	private Double totalReCash;
	private Double cashByOneDlb;
	private Double vipJfRatio;
	private Double busJfRatio;
	private Double oneDlbTotalIncom;
	private Double checkLimitAmount;
	private Date beginTime;
	private Date endTime;
	private Integer flag;
	private Integer withdrawFlag;
	private Integer withdrawUpLimit;
	private Integer inviterSuccessGiftJf; // 邀请人邀请成功，并产生第一次消费赠送积分
	private Integer beInviterSuccessGiftJf; // 被邀请人注册成功赠送积分
	
	public Integer getWithdrawUpLimit() {
		return withdrawUpLimit;
	}
	public void setWithdrawUpLimit(Integer withdrawUpLimit) {
		this.withdrawUpLimit = withdrawUpLimit;
	}
	public Integer getInviterSuccessGiftJf() {
		return inviterSuccessGiftJf;
	}
	public void setInviterSuccessGiftJf(Integer inviterSuccessGiftJf) {
		this.inviterSuccessGiftJf = inviterSuccessGiftJf;
	}
	public Integer getBeInviterSuccessGiftJf() {
		return beInviterSuccessGiftJf;
	}
	public void setBeInviterSuccessGiftJf(Integer beInviterSuccessGiftJf) {
		this.beInviterSuccessGiftJf = beInviterSuccessGiftJf;
	}
	public Integer getWithdrawFlag() {
		return withdrawFlag;
	}
	public void setWithdrawFlag(Integer withdrawFlag) {
		this.withdrawFlag = withdrawFlag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getCashRatio() {
		return cashRatio;
	}
	public void setCashRatio(Double cashRatio) {
		this.cashRatio = cashRatio;
	}
	public Double getYljRatio() {
		return yljRatio;
	}
	public void setYljRatio(Double yljRatio) {
		this.yljRatio = yljRatio;
	}
	public Double getTotalReCash() {
		return totalReCash;
	}
	public void setTotalReCash(Double totalReCash) {
		this.totalReCash = totalReCash;
	}
	public Double getCashByOneDlb() {
		return cashByOneDlb;
	}
	public void setCashByOneDlb(Double cashByOneDlb) {
		this.cashByOneDlb = cashByOneDlb;
	}
	public Double getVipJfRatio() {
		return vipJfRatio;
	}
	public void setVipJfRatio(Double vipJfRatio) {
		this.vipJfRatio = vipJfRatio;
	}
	public Double getBusJfRatio() {
		return busJfRatio;
	}
	public void setBusJfRatio(Double busJfRatio) {
		this.busJfRatio = busJfRatio;
	}
	public Double getOneDlbTotalIncom() {
		return oneDlbTotalIncom;
	}
	public void setOneDlbTotalIncom(Double oneDlbTotalIncom) {
		this.oneDlbTotalIncom = oneDlbTotalIncom;
	}
	public Double getCheckLimitAmount() {
		return checkLimitAmount;
	}
	public void setCheckLimitAmount(Double checkLimitAmount) {
		this.checkLimitAmount = checkLimitAmount;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "GlobalPrams [id=" + id + ", cashRatio=" + cashRatio + ", yljRatio=" + yljRatio + ", totalReCash="
				+ totalReCash + ", cashByOneDlb=" + cashByOneDlb + ", vipJfRatio=" + vipJfRatio + ", busJfRatio="
				+ busJfRatio + ", oneDlbTotalIncom=" + oneDlbTotalIncom + ", checkLimitAmount=" + checkLimitAmount
				+ ", beginTime=" + beginTime + ", endTime=" + endTime + ", flag=" + flag + "]";
	}

}
