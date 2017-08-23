package com.sxsram.ssm.entity;

import java.util.List;

public class OperationExpand extends Operation {
	public List<OperationExpand> childOperations;
	private boolean active;
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<OperationExpand> getChildOperations() {
		return childOperations;
	}

	public void setChildOperations(List<OperationExpand> childOperations) {
		this.childOperations = childOperations;
	}

}
