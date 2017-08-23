package com.sxsram.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sxsram.ssm.entity.GlobalPrams;
import com.sxsram.ssm.mapper.GlobalParamMapper;
import com.sxsram.ssm.service.GlobalParamService;

@Service("globalParamService")
public class GlobalParamServiceImpl implements GlobalParamService {
	@Autowired
	private GlobalParamMapper globalParamMapper;

	@Override
	public GlobalPrams findCurrentGlobalParams() throws Exception {
		return globalParamMapper.queryCurrentGlobalParams();
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void updateGlobalParams(GlobalPrams globalPrams) throws Exception {
		globalParamMapper.updateCurrentGlobalParamFlagToDisable();
		globalParamMapper.insertNewGlobalParam(globalPrams);
	}

	@Override
	public List<GlobalPrams> findAllGlobalParamsChangeRecord()throws Exception {
		return globalParamMapper.queryAllGlobalParams();
	}
}
