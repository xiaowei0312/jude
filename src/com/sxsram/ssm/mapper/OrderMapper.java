package com.sxsram.ssm.mapper;

import java.util.List;

import com.sxsram.ssm.entity.OnlineJournalBook;
import com.sxsram.ssm.entity.OnlineJournalBookItem;
import com.sxsram.ssm.entity.OnlineJournalBookQueryVo;

import sun.org.mozilla.javascript.internal.EcmaError;

public interface OrderMapper {

	void insertNewOrder(OnlineJournalBook newOrder) throws Exception;

	void insertNewOrderItem(OnlineJournalBookItem item) throws Exception;

	void updateOrder(OnlineJournalBook onlineJournalBook) throws Exception;

	OnlineJournalBook querySingleOrder(OnlineJournalBookQueryVo onlineJournalBookQueryVo) throws Exception;

	List<OnlineJournalBook> queryMultiOrder(OnlineJournalBookQueryVo onlineJournalBookQueryVo) throws Exception;

	OnlineJournalBook queryOrderById(Integer orderId) throws Exception;

	Integer queryOnlineOrdersTotalNum(OnlineJournalBookQueryVo onlineJournalBookQueryVo) throws Exception;

}
