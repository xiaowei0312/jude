package com.sxsram.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxsram.ssm.entity.RechargeAndWithDrawRecordExpand;
import com.sxsram.ssm.entity.RechargeAndWithDrawRecordQueryVo;
import com.sxsram.ssm.mapper.RechargeAndWithdrawMapper;
import com.sxsram.ssm.service.RechargeAndWithdrawService;

@Service("rechargeAndWithdrawService")
public class RechargeAndWithdrawServiceImpl implements RechargeAndWithdrawService {
	@Autowired
	private RechargeAndWithdrawMapper mapper;

	@Override
	public RechargeAndWithDrawRecordExpand getRechargeAndWithdrawRecord(RechargeAndWithDrawRecordQueryVo recordQueryVo)
			throws Exception {
		return mapper.querySingleRechargeAndWithdrawRecord(recordQueryVo);
	}

	@Override
	public List<RechargeAndWithDrawRecordExpand> getRechargeAndWithdrawRecords(
			RechargeAndWithDrawRecordQueryVo recordQueryVo) throws Exception {
		return mapper.queryMultiRechargeAndWithdrawRecords(recordQueryVo);
	}
}
