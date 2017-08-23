package com.sxsram.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxsram.ssm.entity.OperationExpand;
import com.sxsram.ssm.entity.OperationExpandQueryVo;
import com.sxsram.ssm.entity.PermissionExpand;
import com.sxsram.ssm.entity.PermissionExpandQueryVo;
import com.sxsram.ssm.entity.RoleExpand;
import com.sxsram.ssm.entity.RoleExpandQueryVo;
import com.sxsram.ssm.mapper.PermMapper;
import com.sxsram.ssm.service.PermService;

@Service("permService")
public class PermServiceImpl implements PermService {
	@Autowired
	private PermMapper permMapper;

	@Override
	public Integer getRolesTotalNum(RoleExpandQueryVo roleExpandQueryVo) throws Exception {
		return permMapper.queryRolesTotalNum(roleExpandQueryVo);
	}

	@Override
	public List<RoleExpand> getRoles(RoleExpandQueryVo roleExpandQueryVo) throws Exception {
		return permMapper.queryMultiRoles(roleExpandQueryVo);
	}

	@Override
	public void addNewRole(RoleExpand roleExpand) throws Exception {
		permMapper.insertNewRole(roleExpand);
	}

	@Override
	public void updateRole(RoleExpand roleExpand) throws Exception {
		permMapper.updateRole(roleExpand);
	}

	@Override
	public void deleteRole(RoleExpand roleExpand) throws Exception {
		permMapper.deleteRole(roleExpand);
	}

	@Override
	public void deleteOper(OperationExpand operExpand) throws Exception {
		permMapper.deleteOper(operExpand);
	}

	@Override
	public void updateOper(OperationExpand operExpand) throws Exception {
		permMapper.updateOper(operExpand);
	}

	@Override
	public void addNewOper(OperationExpand operExpand) throws Exception {
		permMapper.insertNewOper(operExpand);
	}

	@Override
	public List<OperationExpand> getOpers(OperationExpandQueryVo operExpandQueryVo) throws Exception {
		return permMapper.queryMultiOpers(operExpandQueryVo);
	}

	@Override
	public Integer getOpersTotalNum(OperationExpandQueryVo operExpandQueryVo) throws Exception {
		return permMapper.queryOpersTotalNum(operExpandQueryVo);
	}

	@Override
	public void deletePerm(PermissionExpand roleExpand) throws Exception {
		permMapper.deletePerm(roleExpand);
	}

	@Override
	public Integer getPermsTotalNum(PermissionExpandQueryVo roleExpandQueryVo) throws Exception {
		return permMapper.queryPermsTotalNum(roleExpandQueryVo);
	}

	@Override
	public void updatePerm(PermissionExpand permExpand) throws Exception {
		permMapper.updatePerm(permExpand);
	}

	@Override
	public void addNewPerm(PermissionExpand permExpand) throws Exception {
		permMapper.insertNewPerm(permExpand);
	}

	@Override
	public List<PermissionExpand> getPerms(PermissionExpandQueryVo permExpandQueryVo) throws Exception {
		return permMapper.queryMultiPerms(permExpandQueryVo);
	}

}
