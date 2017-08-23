package com.sxsram.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sxsram.ssm.entity.UserExtra;
import com.sxsram.ssm.entity.UserExtraQueryVo;
import com.sxsram.ssm.mapper.UserExtraMapper;
import com.sxsram.ssm.service.UserExtraService;

@Service("userExtraService")
public class UserExtraServiceImpl implements UserExtraService {
	@Resource
	private UserExtraMapper userExtraMapper;

	@Override
	public void addNewUserExtra(UserExtra userExtra) throws Exception {
		userExtraMapper.insertNewUserExtra(userExtra);
	}

	@Override
	public void updateUserExtra(UserExtra userExtra) throws Exception {
		userExtraMapper.updateUserExtra(userExtra);
	}

	@Override
	public void delUserExtra(UserExtra userExtra) throws Exception {
		userExtraMapper.deleteUserExtra(userExtra.getId());
	}

	@Override
	public UserExtra findUserExtra(UserExtraQueryVo userExtraQueryVo) throws Exception {
		return userExtraMapper.querySingleUserExtra(userExtraQueryVo);
	}

	@Override
	public List<UserExtra> findUserExtras(UserExtraQueryVo userExtraQueryVo) throws Exception {
		return userExtraMapper.queryMultiUserExtras(userExtraQueryVo);
	}

	@Override
	public List<UserExtra> findUserExtrasHasInviter(UserExtraQueryVo userExtraQueryVo) throws Exception {
		return userExtraMapper.queryMultiUserExtrasHasInviter(userExtraQueryVo);
	}

	@Override
	public List<UserExtra> findUserExtrasTotalNumHasInviter(UserExtraQueryVo userExtraQueryVo)
			throws Exception {
		return userExtraMapper.queryMultiUserInviteNum(userExtraQueryVo);
	}

}
