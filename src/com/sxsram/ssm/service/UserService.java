package com.sxsram.ssm.service;

import java.util.List;

import com.sxsram.ssm.entity.AccountRecordExpand;
import com.sxsram.ssm.entity.Address;
import com.sxsram.ssm.entity.Certification;
import com.sxsram.ssm.entity.CommodityType;
import com.sxsram.ssm.entity.Indent;
import com.sxsram.ssm.entity.JournalBookExpand;
import com.sxsram.ssm.entity.OperationExpandQueryVo;
import com.sxsram.ssm.entity.PermissionExpand;
import com.sxsram.ssm.entity.PermissionExpandQueryVo;
import com.sxsram.ssm.entity.PlatformSyDistributedRecordExpand;
import com.sxsram.ssm.entity.ShoppingMallExpand;
import com.sxsram.ssm.entity.User;
import com.sxsram.ssm.entity.UserExpand;
import com.sxsram.ssm.entity.UserExpandQueryVo;
import com.sxsram.ssm.entity.PlatformYljSavedRecordExpand;
import com.sxsram.ssm.entity.RechargeAndWithDrawRecordExpand;
import com.sxsram.ssm.entity.Role;
import com.sxsram.ssm.entity.RoleExpand;
import com.sxsram.ssm.entity.RoleExpandQueryVo;

public interface UserService {
	public boolean registUser(UserExpand userExpand) throws Exception;

	public UserExpand findUserByOpenId(String openId) throws Exception;

	public UserExpand findUserByKeyWords(String keyWords);

	public UserExpand findUserById(int id) throws Exception;

	public List<ShoppingMallExpand> findAllShppingMalls() throws Exception;

	public List<AccountRecordExpand> getMoneyAccountRecord(Integer userId) throws Exception;

	public List<AccountRecordExpand> getJfAccountRecord(Integer userId) throws Exception;

	public List<AccountRecordExpand> getDlbAccountRecord(Integer userId) throws Exception;

	public List<PlatformYljSavedRecordExpand> getYljAccountRecord(Integer userId) throws Exception;

	public List<PlatformSyDistributedRecordExpand> getSyAccountRecord(Integer userId) throws Exception;

	public UserExpand findUserWhenLogin(UserExpand user) throws Exception;

	public void updateUserOpenId(UserExpand user) throws Exception;

	public boolean usernameExist(String username) throws Exception;

	public boolean openIdExist(String openId) throws Exception;

	public boolean phoneExist(String phone) throws Exception;

	public List<Address> findEparchies(Integer integer) throws Exception;

	public List<Address> findCities(Integer integer) throws Exception;

	public Double getMoneyAccountBalance(int userId) throws Exception;

	public Indent findIndentByIndentNo(String indentNo) throws Exception;

	public UserExpand findUserByPhone(String phone) throws Exception;

	public void saveNewJournalBookItem(JournalBookExpand journalBookExpand) throws Exception;

	public List<CommodityType> findCommodityByParentId(int parentId) throws Exception;

	public UserExpand getUserAccountInfo(String keyword) throws Exception;

	public List<JournalBookExpand> findAllJournalBookRecordsByUserId(int id) throws Exception;

	public void userRecharge(RechargeAndWithDrawRecordExpand record) throws Exception;

	public void userWithDraw(RechargeAndWithDrawRecordExpand record) throws Exception;

	public RechargeAndWithDrawRecordExpand findRechargeRecordByOrderNo(String orderNo) throws Exception;

	public RechargeAndWithDrawRecordExpand findWithDrawRecordByOrderNo(String orderNo) throws Exception;

	public void updateRechargeRecordState(RechargeAndWithDrawRecordExpand record) throws Exception;

	public void updateWithDrawRecordState(RechargeAndWithDrawRecordExpand record) throws Exception;

	public List<RechargeAndWithDrawRecordExpand> findAllWithDrawsRecords(int id) throws Exception;

	public List<JournalBookExpand> findAllJournalBookRecordsByClientId(int clientId) throws Exception;

	public void updateCertification(Certification certification) throws Exception;

	public List<UserExpand> findAllUsers();

	public List<UserExpand> findAllProxyUsers();

	public List<UserExpand> findAllSeller();

	public List<UserExpand> findAllSellersByProxyId(Integer proxyId);

	public List<UserExpand> findAllManagers();

	public boolean addNewUser(UserExpand userExpand);

	public boolean updateUser(UserExpand userExpand);

	public boolean deleteUser(UserExpand userExpand);

	public List<Address> findAllProvinces();

	public List<Role> findAllRoles();

	public boolean resetUserPwd(UserExpand userExpand);

	/**
	 * Added by wzz 2017/04/14
	 * 
	 * @param userExpandQueryVo
	 * @return
	 * @throws Exception
	 */

	public Integer getUserListCount(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public List<UserExpand> getUserList(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public Integer getRolesTotalNum(RoleExpandQueryVo roleExpandQueryVo) throws Exception;

	public List<RoleExpand> getRoles(RoleExpandQueryVo roleExpandQueryVo) throws Exception;
}
