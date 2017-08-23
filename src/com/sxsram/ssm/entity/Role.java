package com.sxsram.ssm.entity;

public class Role {
	private Integer id;
	private String roleName;
	private String roleGrade;
	private Integer seqNum;
	
	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleGrade() {
		return roleGrade;
	}

	public void setRoleGrade(String roleGrade) {
		this.roleGrade = roleGrade;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", roleGrade=" + roleGrade + "]";
	}

}
