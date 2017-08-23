package com.sxsram.ssm.entity;

public class OnlineCommodityType {
	private Integer id;
	private Integer parentId;
	private String typeName;
	private Integer sequence;
	public OnlineCommodityType(Integer catId) {
		this.id = catId;
	}
	
	public OnlineCommodityType() {
		super();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	@Override
	public String toString() {
		return "OnlineCommodityType [id=" + id + ", parentId=" + parentId + ", typeName=" + typeName + ", sequence="
				+ sequence + "]";
	}

	
}
