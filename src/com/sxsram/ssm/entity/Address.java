package com.sxsram.ssm.entity;

public class Address {
	private int id;
	private String name;
	private String code;
	private String parentId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", code=" + code + ", parentId=" + parentId + "]";
	}
	
	
}
