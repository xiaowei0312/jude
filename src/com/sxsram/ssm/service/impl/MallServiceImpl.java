package com.sxsram.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sxsram.ssm.entity.Role;
import com.sxsram.ssm.entity.RoleExpand;
import com.sxsram.ssm.entity.ShoppingMallExpand;
import com.sxsram.ssm.entity.ShoppingMallExpandQueryVo;
import com.sxsram.ssm.entity.ShoppingMallType;
import com.sxsram.ssm.entity.ShoppingMallTypeQueryVo;
import com.sxsram.ssm.mapper.MallMapper;
import com.sxsram.ssm.mapper.UserMapper;
import com.sxsram.ssm.service.MallService;

@Service("mallService")
public class MallServiceImpl implements MallService {
	@Autowired
	private MallMapper mallMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<ShoppingMallExpand> findAllMallsByProxyId(int id) {
		// TODO Auto-generated method stub
		try {
			return mallMapper.queryAllMallsByProxyId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ShoppingMallExpand> findAllMalls() {
		try {
			return mallMapper.queryAllMalls();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateMall(ShoppingMallExpand mallExpand) {
		try {
			mallMapper.updateMall(mallExpand);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addNewMall(ShoppingMallExpand mallExpand) {
		try {
			mallMapper.addNewMall(mallExpand);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteMall(ShoppingMallExpand mallExpand) {
		try {
			mallMapper.updateMall(mallExpand);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Integer getMallListCount(ShoppingMallExpandQueryVo shoppingMallExpandQueryVo) throws Exception {
		return mallMapper.queryMallListCount(shoppingMallExpandQueryVo);
	}

	@Override
	public List<ShoppingMallExpand> getMallList(ShoppingMallExpandQueryVo shoppingMallExpandQueryVo) throws Exception {
		return mallMapper.queryMultiMalls(shoppingMallExpandQueryVo);
	}

	@Override
	public ShoppingMallExpand getMall(ShoppingMallExpandQueryVo shoppingMallExpandQueryVo) throws Exception {
		return mallMapper.querySingleMall(shoppingMallExpandQueryVo);
	}
	
	/**
	 * Type Operation
	 */

	@Override
	public List<ShoppingMallType> getMallTypes(ShoppingMallTypeQueryVo shoppingMallTypeQueryVo) throws Exception {
		return mallMapper.queryMultiMallTypes(shoppingMallTypeQueryVo);
	}

	@Override
	public Integer getMallTypesTotalNum(ShoppingMallTypeQueryVo shoppingMallTypeQueryVo) throws Exception {
		// TODO Auto-generated method stub
		return mallMapper.queryMallTypesTotalNum(shoppingMallTypeQueryVo);
	}

	@Override
	public void updateType(ShoppingMallType type) throws Exception {
		mallMapper.updateType(type);
		
	}

	@Override
	public void deleteTypeCascadeCommodity(ShoppingMallType type) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteType(ShoppingMallType type) throws Exception {
		mallMapper.deleteType(type);
	}

	@Override
	public void addType(ShoppingMallType type) throws Exception {
		mallMapper.insertNewType(type);
		
	}
}
