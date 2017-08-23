package com.sxsram.ssm.service;

import java.util.List;

import com.sxsram.ssm.entity.OnlineJournalBook;
import com.sxsram.ssm.entity.OnlineJournalBookQueryVo;
import com.sxsram.ssm.entity.RechargeAndWithDrawRecordExpand;
import com.sxsram.ssm.entity.RechargeAndWithDrawRecordQueryVo;

public interface RechargeAndWithdrawService {

	RechargeAndWithDrawRecordExpand getRechargeAndWithdrawRecord(RechargeAndWithDrawRecordQueryVo recordQueryVo)
			throws Exception;

	List<RechargeAndWithDrawRecordExpand> getRechargeAndWithdrawRecords(RechargeAndWithDrawRecordQueryVo recordQueryVo)
			throws Exception;
}