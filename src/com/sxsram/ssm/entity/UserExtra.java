package com.sxsram.ssm.entity;

import java.util.Date;

public class UserExtra {
	private Integer id;
	private Integer userId;
	private Integer inviterUserId;
	private Integer inviterRewardFlag;
	private Integer beInviterRewardFlag;
	private Integer sendPushMsgFlag;
	private Integer giveMoneyFlag;
	private Integer delFlag;
	private String extra;
	private Date addTime;
	private Date lmtTime;
	
	private Integer withdrawLimit;//每次提现上限	2017-08-20
	
	private User user;
	private User inviterUser;

	private Integer inviterNum;
	
	public Integer getWithdrawLimit() {
		return withdrawLimit;
	}

	public void setWithdrawLimit(Integer withdrawLimit) {
		this.withdrawLimit = withdrawLimit;
	}

	public Integer getBeInviterRewardFlag() {
		return beInviterRewardFlag;
	}

	public void setBeInviterRewardFlag(Integer beInviterRewardFlag) {
		this.beInviterRewardFlag = beInviterRewardFlag;
	}

	public Integer getInviterRewardFlag() {
		return inviterRewardFlag;
	}

	public void setInviterRewardFlag(Integer inviterRewardFlag) {
		this.inviterRewardFlag = inviterRewardFlag;
	}

	public Integer getSendPushMsgFlag() {
		return sendPushMsgFlag;
	}

	public void setSendPushMsgFlag(Integer sendPushMsgFlag) {
		this.sendPushMsgFlag = sendPushMsgFlag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		if (user != null)
			return user.getId();
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getInviterUserId() {
		if (inviterUser != null)
			return inviterUser.getId();
		return inviterUserId;
	}

	public void setInviterUserId(Integer inviterUserId) {
		this.inviterUserId = inviterUserId;
	}

	public Integer getGiveMoneyFlag() {
		return giveMoneyFlag;
	}

	public void setGiveMoneyFlag(Integer giveMoneyFlag) {
		this.giveMoneyFlag = giveMoneyFlag;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getLmtTime() {
		return lmtTime;
	}

	public void setLmtTime(Date lmtTime) {
		this.lmtTime = lmtTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getInviterUser() {
		return inviterUser;
	}

	public void setInviterUser(User inviterUser) {
		this.inviterUser = inviterUser;
	}

	public Integer getInviterNum() {
		return inviterNum;
	}

	public void setInviterNum(Integer inviterNum) {
		this.inviterNum = inviterNum;
	}

	@Override
	public String toString() {
		return "UserExtra [id=" + id + ", userId=" + userId + ", inviterUserId=" + inviterUserId + ", giveMoneyFlag="
				+ giveMoneyFlag + ", delFlag=" + delFlag + ", extra=" + extra + ", addTime=" + addTime + ", lmtTime="
				+ lmtTime + ", user=" + user + ", inviterUser=" + inviterUser + "]";
	}
}
