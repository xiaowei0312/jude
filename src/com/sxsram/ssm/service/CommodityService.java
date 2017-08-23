package com.sxsram.ssm.service;

import java.util.List;

import com.sxsram.ssm.entity.OnLineCommodityQueryVo;
import com.sxsram.ssm.entity.OnLineCommodityTypeQueryVo;
import com.sxsram.ssm.entity.OnlineCommodity;
import com.sxsram.ssm.entity.OnlineCommodityModel;
import com.sxsram.ssm.entity.OnlineCommodityType;

public interface CommodityService {
	List<OnlineCommodity> getOnlineCommoditiesByCatId(OnLineCommodityQueryVo onLineCommodityQueryVo) throws Exception;

	Integer getOnlineCommoditiesTotalNumByCatId(OnLineCommodityQueryVo onLineCommodityQueryVo) throws Exception;

	OnlineCommodity getOnlineCommodityById(OnLineCommodityQueryVo onLineCommodityQueryVo) throws Exception;

	Integer getOnlineCommoditiesTotalNum(OnLineCommodityQueryVo onLineCommodityQueryVo) throws Exception;

	List<OnlineCommodity> getOnlineCommodities(OnLineCommodityQueryVo onLineCommodityQueryVo) throws Exception;

	OnlineCommodityModel getOnlineCommodityModelById(Integer modelId) throws Exception;

	List<OnlineCommodityType> getOnlineTypes(OnLineCommodityTypeQueryVo onLineCommodityTypeQueryVo) throws Exception;

	Integer getOnlineCommodityTypesTotalNum(OnLineCommodityTypeQueryVo onLineCommodityTypeQueryVo) throws Exception;

	void updateType(OnlineCommodityType type) throws Exception;

	void deleteTypeCascadeCommodity(OnlineCommodityType type) throws Exception;

	void deleteType(OnlineCommodityType type) throws Exception;

	void addType(OnlineCommodityType type) throws Exception;

	void updateCommodity(OnlineCommodity onlineCommodity) throws Exception;

	void addNewCommodity(OnlineCommodity onlineCommodity) throws Exception;

	void updateCommodityModel(OnlineCommodityModel onlineCommodityModel) throws Exception;

	void deleteCommodityModel(Integer id) throws Exception;

	void addNewCommodityModel(OnlineCommodityModel onlineCommodityModel) throws Exception;
}