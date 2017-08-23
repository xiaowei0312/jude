package com.sxsram.ssm.mapper;

import java.util.List;

import com.sxsram.ssm.entity.OperationExpand;
import com.sxsram.ssm.entity.OperationExpandQueryVo;
import com.sxsram.ssm.entity.PermissionExpand;
import com.sxsram.ssm.entity.PermissionExpandQueryVo;
import com.sxsram.ssm.entity.RoleExpand;
import com.sxsram.ssm.entity.RoleExpandQueryVo;

public interface PermMapper {
	
	/**
	 * Perm Operation
	 * @param roleExpandQueryVo
	 * @return
	 */
	Integer queryPermsTotalNum(PermissionExpandQueryVo roleExpandQueryVo);
	
	PermissionExpand querySinglePerm(PermissionExpandQueryVo permExpandQueryVo) throws Exception;

	List<PermissionExpand> queryMultiPerms(PermissionExpandQueryVo permExpandQueryVo) throws Exception;

	void insertNewPerm(PermissionExpand permExpand);

	void updatePerm(PermissionExpand permExpand);

	void deletePerm(PermissionExpand roleExpand);

	/**
	 * Operations operation
	 * 
	 * @param operExpandQueryVo
	 * @return
	 */
	Integer queryOpersTotalNum(OperationExpandQueryVo operExpandQueryVo);
	
	OperationExpand querySingleOper(OperationExpandQueryVo operExpandQueryVo);

	List<OperationExpand> queryMultiOpers(OperationExpandQueryVo operExpandQueryVo);

	void insertNewOper(OperationExpand operExpand);

	void updateOper(OperationExpand operExpand);

	void deleteOper(OperationExpand operExpand);

	/**
	 * Role Operation
	 * 
	 * @param roleExpand
	 */

	List<RoleExpand> queryMultiRoles(RoleExpandQueryVo roleExpandQueryVo);
	
	RoleExpand querySingleRole(RoleExpandQueryVo roleExpandQueryVo);

	Integer queryRolesTotalNum(RoleExpandQueryVo roleExpandQueryVo);

	void deleteRole(RoleExpand roleExpand);

	void updateRole(RoleExpand roleExpand);

	void insertNewRole(RoleExpand roleExpand);

}
