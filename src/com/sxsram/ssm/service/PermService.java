package com.sxsram.ssm.service;

import java.util.List;

import com.sxsram.ssm.entity.OperationExpand;
import com.sxsram.ssm.entity.OperationExpandQueryVo;
import com.sxsram.ssm.entity.PermissionExpand;
import com.sxsram.ssm.entity.PermissionExpandQueryVo;
import com.sxsram.ssm.entity.RoleExpand;
import com.sxsram.ssm.entity.RoleExpandQueryVo;

public interface PermService {

	public Integer getRolesTotalNum(RoleExpandQueryVo roleExpandQueryVo) throws Exception;

	public List<RoleExpand> getRoles(RoleExpandQueryVo roleExpandQueryVo) throws Exception;

	void addNewRole(RoleExpand roleExpand) throws Exception;

	void updateRole(RoleExpand roleExpand) throws Exception;

	void deleteRole(RoleExpand roleExpand) throws Exception;

	public void deleteOper(OperationExpand operExpand) throws Exception;;

	public void updateOper(OperationExpand operExpand) throws Exception;;

	public void addNewOper(OperationExpand operExpand) throws Exception;;

	public List<OperationExpand> getOpers(OperationExpandQueryVo operExpandQueryVo) throws Exception;;

	public Integer getOpersTotalNum(OperationExpandQueryVo operExpandQueryVo) throws Exception;;

	public void deletePerm(PermissionExpand roleExpand) throws Exception;;

	public Integer getPermsTotalNum(PermissionExpandQueryVo roleExpandQueryVo) throws Exception;;

	public void updatePerm(PermissionExpand permExpand) throws Exception;;

	public void addNewPerm(PermissionExpand permExpand) throws Exception;

	public List<PermissionExpand> getPerms(PermissionExpandQueryVo permExpandQueryVo)throws Exception;
}
