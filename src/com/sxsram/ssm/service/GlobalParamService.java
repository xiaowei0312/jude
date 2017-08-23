package com.sxsram.ssm.service;

import java.util.List;

import com.sxsram.ssm.entity.GlobalPrams;

public interface GlobalParamService {
	GlobalPrams findCurrentGlobalParams() throws Exception;

	void updateGlobalParams(GlobalPrams globalPrams) throws Exception;

	List<GlobalPrams> findAllGlobalParamsChangeRecord()throws Exception;
}
