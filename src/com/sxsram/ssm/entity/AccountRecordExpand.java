package com.sxsram.ssm.entity;

public class AccountRecordExpand extends AccountRecord {
	private AccountExpand account;
	private UserAccountExpand operateObj;

	public AccountExpand getAccount() {
		return account;
	}

	public void setAccount(AccountExpand account) {
		this.account = account;
	}

	public UserAccountExpand getOperateObj() {
		return operateObj;
	}

	public void setOperateObj(UserAccountExpand operateObj) {
		this.operateObj = operateObj;
	}

	@Override
	public String toString() {
		return "AccountRecordExpand [account=" + account + ", operateObj=" + operateObj + ", getId()=" + getId()
				+ ", getOperation()=" + getOperation() + ", getOperateTime()=" + getOperateTime() + ", getOperateNum()="
				+ getOperateNum() + ", getOperateDesc()=" + getOperateDesc() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
