package com.sxsram.ssm.entity;

import java.util.Date;

public class Notice {
	private Integer id;
	private String noticeTitle;
	private String noticeFileName;
	private Date noticeAddTime;
	private Integer noticeFlag;
	private NoticeType noticeType;

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNoticeFileName() {
		return noticeFileName;
	}

	public void setNoticeFileName(String noticeFileName) {
		this.noticeFileName = noticeFileName;
	}

	public Date getNoticeAddTime() {
		return noticeAddTime;
	}

	public void setNoticeAddTime(Date noticeAddTime) {
		this.noticeAddTime = noticeAddTime;
	}

	public Integer getNoticeFlag() {
		return noticeFlag;
	}

	public void setNoticeFlag(Integer noticeFlag) {
		this.noticeFlag = noticeFlag;
	}

	public NoticeType getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(NoticeType noticeType) {
		this.noticeType = noticeType;
	}
//
//	@Override
//	public String toString() {
//		return "Notice [id=" + id + ", noticeFileName=" + noticeFileName + ", noticeAddTime=" + noticeAddTime
//				+ ", noticeFlag=" + noticeFlag + ", noticeType=" + noticeType + "]";
//	}
//	
	
}
