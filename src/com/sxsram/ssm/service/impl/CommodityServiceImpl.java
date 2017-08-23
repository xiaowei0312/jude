package com.sxsram.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxsram.ssm.entity.OnLineCommodityQueryVo;
import com.sxsram.ssm.entity.OnLineCommodityTypeQueryVo;
import com.sxsram.ssm.entity.OnlineCommodity;
import com.sxsram.ssm.entity.OnlineCommodityModel;
import com.sxsram.ssm.entity.OnlineCommodityType;
import com.sxsram.ssm.mapper.CommodityMapper;
import com.sxsram.ssm.service.CommodityService;

@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {
	@Autowired
	private CommodityMapper commodityMapper;

	@Override
	public List<OnlineCommodity> getOnlineCommoditiesByCatId(OnLineCommodityQueryVo onLineCommodityQueryVo)
			throws Exception {
		return commodityMapper.queryOnlineCommoditiesByCatId(onLineCommodityQueryVo);
	}

	@Override
	public Integer getOnlineCommoditiesTotalNumByCatId(OnLineCommodityQueryVo onLineCommodityQueryVo) throws Exception {
		return commodityMapper.queryOnlineCommoditiesTotalNumByCatId(onLineCommodityQueryVo);
	}

	@Override
	public OnlineCommodity getOnlineCommodityById(OnLineCommodityQueryVo onLineCommodityQueryVo) throws Exception {
		return commodityMapper.queryOnlineCommodityById(onLineCommodityQueryVo);
	}

	@Override
	public Integer getOnlineCommoditiesTotalNum(OnLineCommodityQueryVo onLineCommodityQueryVo) throws Exception {
		return commodityMapper.queryOnlineCommoditiesTotalNum(onLineCommodityQueryVo);
	}

	@Override
	public List<OnlineCommodity> getOnlineCommodities(OnLineCommodityQueryVo onLineCommodityQueryVo) throws Exception {
		return commodityMapper.queryMultiOnlineCommodities(onLineCommodityQueryVo);
	}

	@Override
	public OnlineCommodityModel getOnlineCommodityModelById(Integer modelId) throws Exception {
		return commodityMapper.queryOnlineCommodityModelById(modelId);
	}

	@Override
	public List<OnlineCommodityType> getOnlineTypes(OnLineCommodityTypeQueryVo onLineCommodityTypeQueryVo)
			throws Exception {
		return commodityMapper.queryMultiOnlineTypes(onLineCommodityTypeQueryVo);
	}

	@Override
	public Integer getOnlineCommodityTypesTotalNum(OnLineCommodityTypeQueryVo onLineCommodityTypeQueryVo)
			throws Exception {
		return commodityMapper.queryOnlineTypesTotalNum(onLineCommodityTypeQueryVo);
	}

	@Override
	public void updateType(OnlineCommodityType type) throws Exception {
		commodityMapper.updateType(type);
	}

	@Override
	public void deleteTypeCascadeCommodity(OnlineCommodityType type) throws Exception {

	}

	@Override
	public void deleteType(OnlineCommodityType type) throws Exception {
		commodityMapper.deleteType(type);
	}

	@Override
	public void addType(OnlineCommodityType type) throws Exception {
		commodityMapper.insertNewType(type);
	}

	@Override
	public void updateCommodity(OnlineCommodity onlineCommodity) throws Exception {
		commodityMapper.updateCommodity(onlineCommodity);
	}

	@Override
	public void addNewCommodity(OnlineCommodity onlineCommodity) throws Exception {
		commodityMapper.insertNewCommodity(onlineCommodity);
	}

	@Override
	public void updateCommodityModel(OnlineCommodityModel onlineCommodityModel) throws Exception {
		commodityMapper.updateCommodityModel(onlineCommodityModel);
	}

	@Override
	public void deleteCommodityModel(Integer id) throws Exception {
		commodityMapper.deleteCommodityModel(id);
	}

	@Override
	public void addNewCommodityModel(OnlineCommodityModel onlineCommodityModel) throws Exception {
		commodityMapper.insertNewCommodityModel(onlineCommodityModel);
	}
}
