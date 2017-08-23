package com.sxsram.ssm.service;

import java.util.List;

import com.sxsram.ssm.entity.JournalBookExpand;
import com.sxsram.ssm.entity.JournalBookExpandQueryVo;

public interface SubmitOrderService {

	public boolean updateJournalBookFlag(JournalBookExpand journalBookExpand);

	public List<JournalBookExpand> findAllUnVerifyJournalBooks();

	public List<JournalBookExpand> findAllUnVerifyJournalBooksByProxyId(Integer id);

	// public List<JournalBookExpand> findAllJournalBooks();
	//
	// public List<JournalBookExpand> findAllJournalBooksByProxyId(Integer id);

	public Integer getOfflineOrdersTotalNum(JournalBookExpandQueryVo journalBookExpandQueryVo)throws Exception;

	public List<JournalBookExpand> getOfflineOrders(JournalBookExpandQueryVo journalBookExpandQueryVo)throws Exception;
}
