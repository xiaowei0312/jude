package com.sxsram.ssm.entity;

public class PermissionExpand extends Permission {
	private OperationExpand operation;

	public OperationExpand getOperation() {
		return operation;
	}

	public void setOperation(OperationExpand operation) {
		this.operation = operation;
	}

}
