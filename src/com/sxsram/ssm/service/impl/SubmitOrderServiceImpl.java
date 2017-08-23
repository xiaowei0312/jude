package com.sxsram.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sxsram.ssm.entity.JournalBookExpand;
import com.sxsram.ssm.entity.JournalBookExpandQueryVo;
import com.sxsram.ssm.mapper.SubmitOrderMapper;
import com.sxsram.ssm.service.SubmitOrderService;

@Service("submitOrderService")
public class SubmitOrderServiceImpl implements SubmitOrderService {
	@Autowired
	private SubmitOrderMapper submitOrderMapper;

	// @Override
	// public List<JournalBookExpand> findAllJournalBooks() {
	// try {
	// return submitOrderMapper.queryAllJournalBooks();
	// } catch (Exception e) {
	// // e.printStackTrace();
	// return null;
	// }
	// }
	//
	// @Override
	// public List<JournalBookExpand> findAllJournalBooksByProxyId(Integer id) {
	// try {
	// return submitOrderMapper.queryAllJournalBooksByProxyId(id);
	// } catch (Exception e) {
	// return null;
	// }
	// }

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public boolean updateJournalBookFlag(JournalBookExpand journalBookExpand) {
		try {
			submitOrderMapper.updateJournalBookFlag(journalBookExpand);
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public List<JournalBookExpand> findAllUnVerifyJournalBooks() {
		try {
			return submitOrderMapper.queryAllUnVerifyJournalBookRecords();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<JournalBookExpand> findAllUnVerifyJournalBooksByProxyId(Integer id) {
		try {
			return submitOrderMapper.queryAllUnVerifyJournalBookRecordsByProxyId(id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Integer getOfflineOrdersTotalNum(JournalBookExpandQueryVo journalBookExpandQueryVo) throws Exception {
		return submitOrderMapper.queryOfflineOrdersTotalNum(journalBookExpandQueryVo);
	}

	@Override
	public List<JournalBookExpand> getOfflineOrders(JournalBookExpandQueryVo journalBookExpandQueryVo)
			throws Exception {
		return submitOrderMapper.queryMultiOfflineOrders(journalBookExpandQueryVo);
	}

}
