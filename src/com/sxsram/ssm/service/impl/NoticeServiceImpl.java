package com.sxsram.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxsram.ssm.entity.Notice;
import com.sxsram.ssm.entity.NoticeQueryVo;
import com.sxsram.ssm.entity.NoticeType;
import com.sxsram.ssm.entity.NoticeTypeQueryVo;
import com.sxsram.ssm.entity.ShoppingMallExpand;
import com.sxsram.ssm.mapper.NoticeMapper;
import com.sxsram.ssm.service.NoticeService;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public void updateNotice(Notice notice) throws Exception {
		noticeMapper.updateNotice(notice);
	}

	@Override
	public void addNewNotice(Notice notice) throws Exception {
		// TODO Auto-generated method stub
		noticeMapper.addNewNotice(notice);
	}

	@Override
	public void deleteNotice(Integer id) throws Exception {
		noticeMapper.deleteNotice(id);
	}

	@Override
	public Integer getNoticeListCount(NoticeQueryVo noticeQueryVo) throws Exception {
		return noticeMapper.queryNoticeListCount(noticeQueryVo);
	}

	@Override
	public List<Notice> getMultiNotices(NoticeQueryVo noticeQueryVo) throws Exception {
		return noticeMapper.queryMultiNotices(noticeQueryVo);
	}

	@Override
	public Notice getSingleNotice(NoticeQueryVo noticeQueryVo) throws Exception {
		return noticeMapper.querySingleNotice(noticeQueryVo);
	}

	@Override
	public void updateNoticeType(NoticeType noticeType) throws Exception {
		noticeMapper.updateNoticeType(noticeType);;
	}

	@Override
	public void addNewNoticeType(NoticeType noticeType) throws Exception {
		noticeMapper.addNewNoticeType(noticeType);
	}

	@Override
	public void deleteNoticeType(Integer id) throws Exception {
		noticeMapper.deleteNoticeType(id);
	}

	@Override
	public Integer getNoticeTypeListCount(NoticeTypeQueryVo noticeTypeQueryVo) throws Exception {
		return noticeMapper.queryNoticeTypeListCount(noticeTypeQueryVo);
	}

	@Override
	public List<NoticeType> getMultiNoticeTypes(NoticeTypeQueryVo noticeTypeQueryVo) throws Exception {
		return noticeMapper.queryMultiNoticeTypes(noticeTypeQueryVo);
	}

	@Override
	public NoticeType getSingleNoticeType(NoticeTypeQueryVo noticeTypeQueryVo) throws Exception {
		return noticeMapper.querySingleNoticeType(noticeTypeQueryVo);
	}

	@Override
	public void deleteTypeCascadeNotice(Integer id) throws Exception {
		
	}

}
