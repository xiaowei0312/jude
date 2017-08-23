package com.sxsram.ssm.service;

import java.util.List;

import com.sxsram.ssm.entity.ShoppingMallTypeQueryVo;
import com.sxsram.ssm.entity.ShoppingMallType;
import com.sxsram.ssm.entity.ShoppingMallExpand;
import com.sxsram.ssm.entity.ShoppingMallExpandQueryVo;

public interface MallService {
	List<ShoppingMallExpand> findAllMallsByProxyId(int id);

	List<ShoppingMallExpand> findAllMalls();

	boolean updateMall(ShoppingMallExpand mallExpand);

	boolean addNewMall(ShoppingMallExpand mallExpand);

	boolean deleteMall(ShoppingMallExpand mallExpand);

	Integer getMallListCount(ShoppingMallExpandQueryVo shoppingMallExpandQueryVo) throws Exception;

	List<ShoppingMallExpand> getMallList(ShoppingMallExpandQueryVo shoppingMallExpandQueryVo) throws Exception;

	ShoppingMallExpand getMall(ShoppingMallExpandQueryVo shoppingMallExpandQueryVo) throws Exception;

	/**
	 * Type Operation
	 */

	List<ShoppingMallType> getMallTypes(ShoppingMallTypeQueryVo shoppingMallTypeQueryVo) throws Exception;

	Integer getMallTypesTotalNum(ShoppingMallTypeQueryVo shoppingMallTypeQueryVo) throws Exception;

	void updateType(ShoppingMallType type) throws Exception;

	void deleteTypeCascadeCommodity(ShoppingMallType type) throws Exception;

	void deleteType(ShoppingMallType type) throws Exception;

	void addType(ShoppingMallType type) throws Exception;
}
