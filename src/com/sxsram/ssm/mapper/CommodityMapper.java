package com.sxsram.ssm.mapper;

import java.util.List;

import com.sxsram.ssm.entity.OnLineCommodityQueryVo;
import com.sxsram.ssm.entity.OnLineCommodityTypeQueryVo;
import com.sxsram.ssm.entity.OnlineCommodity;
import com.sxsram.ssm.entity.OnlineCommodityModel;
import com.sxsram.ssm.entity.OnlineCommodityType;

public interface CommodityMapper {

	List<OnlineCommodity> queryOnlineCommoditiesByCatId(OnLineCommodityQueryVo onLineCommodityQueryVo) throws Exception;

	Integer queryOnlineCommoditiesTotalNumByCatId(OnLineCommodityQueryVo onLineCommodityQueryVo) throws Exception;

	OnlineCommodity queryOnlineCommodityById(OnLineCommodityQueryVo onLineCommodityQueryVo) throws Exception;

	Integer queryOnlineCommoditiesTotalNum(OnLineCommodityQueryVo onLineCommodityQueryVo) throws Exception;

	List<OnlineCommodity> queryOnlineCommodities(OnLineCommodityQueryVo onLineCommodityQueryVo) throws Exception;

	List<OnlineCommodity> queryMultiOnlineCommodities(OnLineCommodityQueryVo onLineCommodityQueryVo) throws Exception;

	OnlineCommodityModel queryOnlineCommodityModelById(Integer modelId) throws Exception;

	/**
	 * Commodity Types Operation
	 * 
	 * @param onLineCommodityTypeQueryVo
	 * @return
	 * @throws Exception
	 */
	List<OnlineCommodityType> queryMultiOnlineTypes(OnLineCommodityTypeQueryVo onLineCommodityTypeQueryVo)
			throws Exception;

	OnlineCommodityType querySingleOnlineTypes(OnLineCommodityTypeQueryVo onLineCommodityTypeQueryVo) throws Exception;

	Integer queryOnlineTypesTotalNum(OnLineCommodityTypeQueryVo onLineCommodityTypeQueryVo) throws Exception;

	void updateType(OnlineCommodityType type) throws Exception;

	void deleteType(OnlineCommodityType type) throws Exception;

	void insertNewType(OnlineCommodityType type) throws Exception;

	void updateCommodity(OnlineCommodity onlineCommodity) throws Exception;

	void insertNewCommodity(OnlineCommodity onlineCommodity) throws Exception;

	void updateCommodityModel(OnlineCommodityModel onlineCommodityModel) throws Exception;

	void deleteCommodityModel(Integer id) throws Exception;

	void insertNewCommodityModel(OnlineCommodityModel onlineCommodityModel) throws Exception;
}
