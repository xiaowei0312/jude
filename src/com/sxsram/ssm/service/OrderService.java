package com.sxsram.ssm.service;

import java.util.List;

import com.sxsram.ssm.entity.OnlineJournalBook;
import com.sxsram.ssm.entity.OnlineJournalBookQueryVo;

public interface OrderService {

	void purchase(OnlineJournalBook newOrder, double d, String string, Integer id) throws Exception;

	void addNewOrder(OnlineJournalBook newOrder) throws Exception;

	OnlineJournalBook getOnlineOrderById(Integer orderId) throws Exception;

	Integer getOnlineOrdersTotalNum(OnlineJournalBookQueryVo onlineJournalBookQueryVo) throws Exception;

	List<OnlineJournalBook> getOnlineOrders(OnlineJournalBookQueryVo onlineJournalBookQueryVo) throws Exception;

	void updateOrder(OnlineJournalBook onlineJournalBook)throws Exception;

}