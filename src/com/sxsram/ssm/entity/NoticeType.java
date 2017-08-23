package com.sxsram.ssm.entity;

import java.util.ArrayList;
import java.util.List;

public class NoticeType {
	private Integer id;
	private Integer parentId;
	private String typeName;
	private Integer sequence;
	List<Notice> notices = new ArrayList<>();

	public List<Notice> getNotices() {
		return notices;
	}

	public void setNotices(List<Notice> notices) {
		this.notices = notices;
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
		return "NoticeType [id=" + id + ", parentId=" + parentId + ", typeName=" + typeName + ", sequence=" + sequence
				+ ", notices=" + notices + "]";
	}

	
}
