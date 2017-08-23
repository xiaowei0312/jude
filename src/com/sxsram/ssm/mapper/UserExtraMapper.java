package com.sxsram.ssm.mapper;

import java.util.List;

import com.sxsram.ssm.entity.UserExtra;
import com.sxsram.ssm.entity.UserExtraQueryVo;

public interface UserExtraMapper {
	// 添加
	public void insertNewUserExtra(UserExtra userExtra) throws Exception;

	// 根据条件查找
	public UserExtra querySingleUserExtra(UserExtraQueryVo userExtraQueryVo) throws Exception;

	// 查找所有
	public List<UserExtra> queryMultiUserExtras(UserExtraQueryVo userExtraQueryVo) throws Exception;

	//查找所有有邀请人的记录
	public List<UserExtra> queryMultiUserExtrasHasInviter(UserExtraQueryVo userExtraQueryVo) throws Exception;

	// 修改
	public void updateUserExtra(UserExtra userExtra) throws Exception;

	// 根据条件删除
	public void deleteUserExtra(Integer userExtraId) throws Exception;

	/**
	 * 查找邀请数量
	 * @param userExtraQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<UserExtra> queryMultiUserInviteNum(UserExtraQueryVo userExtraQueryVo) throws Exception;
}
