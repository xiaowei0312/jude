package com.sxsram.ssm.mapper;

import java.util.List;

import com.sxsram.ssm.entity.OnlineJournalBook;
import com.sxsram.ssm.entity.OnlineJournalBookItem;
import com.sxsram.ssm.entity.OnlineJournalBookQueryVo;
import com.sxsram.ssm.entity.RechargeAndWithDrawRecord;
import com.sxsram.ssm.entity.RechargeAndWithDrawRecordExpand;
import com.sxsram.ssm.entity.RechargeAndWithDrawRecordQueryVo;

import sun.org.mozilla.javascript.internal.EcmaError;

public interface RechargeAndWithdrawMapper {

	RechargeAndWithDrawRecordExpand querySingleRechargeAndWithdrawRecord(RechargeAndWithDrawRecordQueryVo recordQueryVo)
			throws Exception;

	List<RechargeAndWithDrawRecordExpand> queryMultiRechargeAndWithdrawRecords(RechargeAndWithDrawRecordQueryVo recordQueryVo)
			throws Exception;
}
