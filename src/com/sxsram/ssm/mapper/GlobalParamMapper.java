package com.sxsram.ssm.mapper;

import java.util.List;

import com.sxsram.ssm.entity.GlobalPrams;

public interface GlobalParamMapper {
	public GlobalPrams queryCurrentGlobalParams() throws Exception;

	public void insertNewGlobalParam(GlobalPrams globalPrams) throws Exception;

	public void updateGlobalParamFlag(GlobalPrams globalPrams) throws Exception;

	public void updateCurrentGlobalParamFlagToDisable() throws Exception;

	public List<GlobalPrams> queryAllGlobalParams();
}
