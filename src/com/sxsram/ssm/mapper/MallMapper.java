package com.sxsram.ssm.mapper;

import java.util.List;

import com.sxsram.ssm.entity.ShoppingMallTypeQueryVo;
import com.sxsram.ssm.entity.OnlineCommodity;
import com.sxsram.ssm.entity.OnlineCommodityModel;
import com.sxsram.ssm.entity.ShoppingMallType;
import com.sxsram.ssm.entity.ShoppingMallExpand;
import com.sxsram.ssm.entity.ShoppingMallExpandQueryVo;

public interface MallMapper {

	List<ShoppingMallExpand> queryAllMallsByProxyId(int id) throws Exception;

	List<ShoppingMallExpand> queryAllMalls()throws Exception;

	boolean updateMall(ShoppingMallExpand mallExpand)throws Exception;
	
	boolean addNewMall(ShoppingMallExpand mallExpand) throws Exception;

	Integer queryMallListCount(ShoppingMallExpandQueryVo shoppingMallExpandQueryVo)throws Exception;

	List<ShoppingMallExpand> queryMultiMalls(ShoppingMallExpandQueryVo shoppingMallExpandQueryVo)throws Exception;

	ShoppingMallExpand querySingleMall(ShoppingMallExpandQueryVo shoppingMallExpandQueryVo)throws Exception;
	
	/**
	 * Type Operation
	 */
	
	/**
	 * Commodity Types Operation
	 * 
	 * @param ShoppingMallTypeQueryVo
	 * @return
	 * @throws Exception
	 */
	List<ShoppingMallType> queryMultiMallTypes(ShoppingMallTypeQueryVo ShoppingMallTypeQueryVo)
			throws Exception;

	ShoppingMallType querySingleMallTypes(ShoppingMallTypeQueryVo ShoppingMallTypeQueryVo) throws Exception;

	Integer queryMallTypesTotalNum(ShoppingMallTypeQueryVo ShoppingMallTypeQueryVo) throws Exception;

	void updateType(ShoppingMallType type) throws Exception;

	void deleteType(ShoppingMallType type) throws Exception;

	void insertNewType(ShoppingMallType type) throws Exception;

	void updateCommodity(OnlineCommodity onlineCommodity) throws Exception;

	void insertNewCommodity(OnlineCommodity onlineCommodity) throws Exception;

	void updateCommodityModel(OnlineCommodityModel onlineCommodityModel) throws Exception;

	void deleteCommodityModel(Integer id) throws Exception;

	void insertNewCommodityModel(OnlineCommodityModel onlineCommodityModel) throws Exception;
}
