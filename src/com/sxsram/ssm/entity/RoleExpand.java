package com.sxsram.ssm.entity;

import java.util.List;

public class RoleExpand extends Role {
	private List<PermissionExpand> permissionList;

	public List<PermissionExpand> getPermissionExpandList() {
		return permissionList;
	}

	public void setPermissionExpandList(List<PermissionExpand> permissionList) {
		this.permissionList = permissionList;
	}
	
}
