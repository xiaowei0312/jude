package com.sxsram.ssm.mapper;

import java.util.List;

import com.sxsram.ssm.entity.JournalBookExpand;
import com.sxsram.ssm.entity.JournalBookExpandQueryVo;

public interface SubmitOrderMapper {

	// List<JournalBookExpand> queryAllJournalBooks() throws Exception;
	//
	// List<JournalBookExpand> queryAllJournalBooksByProxyId(Integer id) throws
	// Exception;

	void updateJournalBookFlag(JournalBookExpand journalBookExpand) throws Exception;

	List<JournalBookExpand> queryAllUnVerifyJournalBookRecords() throws Exception;

	List<JournalBookExpand> queryAllUnVerifyJournalBookRecordsByProxyId(Integer id) throws Exception;

	Integer queryOfflineOrdersTotalNum(JournalBookExpandQueryVo journalBookExpandQueryVo);

	List<JournalBookExpand> queryMultiOfflineOrders(JournalBookExpandQueryVo journalBookExpandQueryVo);
	
	JournalBookExpand querySingleOfflineOrder(JournalBookExpandQueryVo journalBookExpandQueryVo);
}
