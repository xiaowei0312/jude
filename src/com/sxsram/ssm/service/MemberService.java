package com.sxsram.ssm.service;

import java.util.List;

import com.sxsram.ssm.aop.SystemServiceLog;
import com.sxsram.ssm.entity.AccountExpand;
import com.sxsram.ssm.entity.AccountRecordExpand;
import com.sxsram.ssm.entity.Address;
import com.sxsram.ssm.entity.Certification;
import com.sxsram.ssm.entity.CommodityType;
import com.sxsram.ssm.entity.GlobalPrams;
import com.sxsram.ssm.entity.Indent;
import com.sxsram.ssm.entity.JournalBookExpand;
import com.sxsram.ssm.entity.OnlineJournalBookItem;
import com.sxsram.ssm.entity.PlatformSyDistributedRecordExpand;
import com.sxsram.ssm.entity.ShoppingMallExpand;
import com.sxsram.ssm.entity.User;
import com.sxsram.ssm.entity.UserExpand;
import com.sxsram.ssm.entity.UserExpandQueryVo;
import com.sxsram.ssm.entity.PlatformYljSavedRecordExpand;
import com.sxsram.ssm.entity.RechargeAndWithDrawRecordExpand;
import com.sxsram.ssm.entity.RecvCommodityAddress;
import com.sxsram.ssm.entity.RecvCommodityAddressQueryVo;
import com.sxsram.ssm.entity.Role;
import com.sxsram.ssm.entity.RoleExpand;

public interface MemberService {
	public boolean registUser(UserExpand userExpand) throws Exception;

	public UserExpand findUserByOpenId(String openId) throws Exception;

	public UserExpand findUserByKeyWords(String keyWords);

	public UserExpand findUserById(int id) throws Exception;

	public List<ShoppingMallExpand> findAllShppingMalls() throws Exception;

	public List<AccountRecordExpand> getJfAccountRecord(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public List<AccountRecordExpand> getMoneyAccountRecord(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public List<AccountRecordExpand> getDlbAccountRecord(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public List<AccountRecordExpand> getYljTransferRecord(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public List<PlatformYljSavedRecordExpand> getYljAccountRecord(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public List<PlatformSyDistributedRecordExpand> getSyAccountRecord(UserExpandQueryVo userExpandQueryVo)
			throws Exception;

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

	public List<JournalBookExpand> getJournalBookRecordsByUserId(int id) throws Exception;

	public void userRecharge(RechargeAndWithDrawRecordExpand record) throws Exception;

	public void userWithDraw(RechargeAndWithDrawRecordExpand record) throws Exception;

	public RechargeAndWithDrawRecordExpand findRechargeRecordByOrderNo(String orderNo) throws Exception;

	public RechargeAndWithDrawRecordExpand findWithDrawRecordByOrderNo(String orderNo) throws Exception;

	public void updateRechargeRecordState(RechargeAndWithDrawRecordExpand record) throws Exception;

	public void updateWithDrawRecordState(RechargeAndWithDrawRecordExpand record) throws Exception;

	public List<RechargeAndWithDrawRecordExpand> findAllWithDrawsRecords(int id) throws Exception;

	public List<JournalBookExpand> getJournalBookRecordsByClientId(UserExpandQueryVo userExpandQueryVo)
			throws Exception;

	public void updateCertification(Certification certification) throws Exception;

	// @SystemServiceLog(description="Service: Query all users")
	public List<UserExpand> findAllUsers() throws Exception;

	public List<UserExpand> findAllProxyUsers();

	public List<UserExpand> findAllSeller();

	public List<UserExpand> findAllSellersByProxyId(Integer proxyId);

	public List<UserExpand> findAllManagers();

	public boolean addNewUser(UserExpand userExpand);

	public boolean updateUser(UserExpand userExpand) throws Exception;

	public boolean deleteUser(UserExpand userExpand);

	public List<Address> findAllProvinces();

	public List<Role> findAllRoles();

	//
	public boolean resetUserPwd(UserExpand userExpand);

	//
	// public JournalBookExpand findFirstJournalBookByClientId(int userId);
	//
	// public ShoppingMallExpand findAllShppingMallById(Integer mallId);
	//
	public RoleExpand findRoleByUserId(Integer userId);
	//
	// public int getCashFlag(int id);
	//
	// public boolean setCashFlag(UserExpand userExpand);
	//
	// public int getWithDrawRecordCounts(int id);
	//
	// public int getCurrentMonthConsumeRecordCounts(int id);
	//
	// public double findSubmitOrderCheckLimit();
	//
	// public GlobalPrams findGlobalParams();
	//
	// public RechargeAndWithDrawRecordExpand findLastWithDrawRecord(int
	// userId);
	//
	// public RechargeAndWithDrawRecordExpand findFirstWithDrawRecord(int id);

	public Double getTotalConsumeOffline(Integer id) throws Exception;

	public Double getTotalConsumeOnline(Integer id) throws Exception;

	public Double getTotalRecharge(Integer id) throws Exception;

	public Double getTotalRewardJf(Integer id) throws Exception;

	public Double getTotalWithDraws(Integer id) throws Exception;

	public Double getTotalTransferDlb(Integer id) throws Exception;

	public Double getTotalRewardMoney(Integer id) throws Exception;

	public Double getTotalRewardJdb(Integer id) throws Exception;

	public Double getTotalRewardYlj(Integer id) throws Exception;

	public Double getTotalTransferYlj(Integer id) throws Exception;

	public Double getTotalSubmitOrderValue(Integer id) throws Exception;

	public Integer getMoneyAccountRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public Integer getDlbAccountRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public Integer getJfAccountRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public Integer getYljTransferRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public Integer getJournalBookRecordsTotalNumByClientId(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public Integer getSyAccountRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public Integer getOnlineConsumeRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public List<OnlineJournalBookItem> getOnlineConsumeRecord(UserExpandQueryVo userExpandQueryVo) throws Exception;

	/**
	 * 用户收货地址操作
	 */

	public void addNewRecvCommodityAddress(RecvCommodityAddress recvCommodityAddress) throws Exception;

	public void delRecvCommodityAddress(Integer id) throws Exception;

	public void updateRecvCommodityAddress(RecvCommodityAddressQueryVo recvCommodityAddressQueryVo) throws Exception;

	public List<RecvCommodityAddress> getRecvCommodityAddress(RecvCommodityAddressQueryVo recvCommodityAddressQueryVo)
			throws Exception;

	public void setRecvCommodityAddressAsDefault(Integer id, Integer userId) throws Exception;

	public AccountExpand getJdbAccountByUserId(Integer id) throws Exception;

	public void updateJdbBalance(double d, String string, Integer id) throws Exception;
}
