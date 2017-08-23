package com.sxsram.ssm.service;

import java.util.List;

import com.sxsram.ssm.entity.Notice;
import com.sxsram.ssm.entity.NoticeQueryVo;
import com.sxsram.ssm.entity.NoticeType;
import com.sxsram.ssm.entity.NoticeTypeQueryVo;
import com.sxsram.ssm.entity.ShoppingMallExpand;

public interface NoticeService {

	/**
	 * Notice Operation
	 */

	void updateNotice(Notice notice) throws Exception;

	void addNewNotice(Notice notice) throws Exception;

	void deleteNotice(Integer id) throws Exception;

	Integer getNoticeListCount(NoticeQueryVo noticeQueryVo) throws Exception;

	List<Notice> getMultiNotices(NoticeQueryVo noticeQueryVo) throws Exception;

	Notice getSingleNotice(NoticeQueryVo noticeQueryVo) throws Exception;

	/**
	 * Notice Types Operation
	 */

	void updateNoticeType(NoticeType noticeType) throws Exception;

	void addNewNoticeType(NoticeType noticeType) throws Exception;

	void deleteNoticeType(Integer id) throws Exception;

	void deleteTypeCascadeNotice(Integer id) throws Exception;

	Integer getNoticeTypeListCount(NoticeTypeQueryVo noticeTypeQueryVo) throws Exception;

	List<NoticeType> getMultiNoticeTypes(NoticeTypeQueryVo noticeTypeQueryVo) throws Exception;

	NoticeType getSingleNoticeType(NoticeTypeQueryVo noticeTypeQueryVo) throws Exception;

}