package com.sxsram.ssm.mapper;

import java.util.List;

import com.sxsram.ssm.entity.AccountExpand;
import com.sxsram.ssm.entity.AccountRecordExpand;
import com.sxsram.ssm.entity.Address;
import com.sxsram.ssm.entity.Certification;
import com.sxsram.ssm.entity.CommodityType;
import com.sxsram.ssm.entity.Indent;
import com.sxsram.ssm.entity.JournalBookExpand;
import com.sxsram.ssm.entity.OnlineJournalBookItem;
import com.sxsram.ssm.entity.PlatformSyDistributedRecord;
import com.sxsram.ssm.entity.PlatformSyDistributedRecordExpand;
import com.sxsram.ssm.entity.ShoppingMallExpand;
import com.sxsram.ssm.entity.UserExpand;
import com.sxsram.ssm.entity.UserExpandQueryVo;
import com.sxsram.ssm.entity.PlatformYljSavedRecordExpand;
import com.sxsram.ssm.entity.RechargeAndWithDrawRecordExpand;
import com.sxsram.ssm.entity.RecvCommodityAddress;
import com.sxsram.ssm.entity.RecvCommodityAddressQueryVo;
import com.sxsram.ssm.entity.Role;
import com.sxsram.ssm.entity.RoleExpand;

public interface MemberMapper {
	public void insertNewJfAccount(AccountExpand accountExpand) throws Exception;

	public void insertNewDlbAccount(AccountExpand accountExpand) throws Exception;

	public void insertNewMoneyAccount(AccountExpand accountExpand) throws Exception;

	public void insertNewYljAccount(AccountExpand accountExpand) throws Exception;

	public List<AccountRecordExpand> queryAllDlbRecords(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public List<AccountRecordExpand> queryAllJfRecords(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public List<PlatformYljSavedRecordExpand> queryAllYljRecords(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public List<AccountRecordExpand> queryAllYljTransferRecords(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public List<PlatformSyDistributedRecordExpand> queryAllSyRecords(UserExpandQueryVo userExpandQueryVo)
			throws Exception;

	public List<AccountRecordExpand> queryAllMoneyRecords(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public void insertNewUser(UserExpand userExpand) throws Exception;

	public void updateUserAccountInformation(UserExpand userExpand) throws Exception;

	public UserExpand queryUserAccountInfo(String keyWord) throws Exception;

	public UserExpand queryUserByOpenId(String openId) throws Exception;

	public UserExpand queryUserById(int id) throws Exception;

	public List<ShoppingMallExpand> queryAllShoppingMalls() throws Exception;

	public UserExpand queryUserWhenLogin(UserExpand user) throws Exception;

	public void updateUserOpenId(UserExpand user) throws Exception;

	public int countUsername(String username) throws Exception;

	public int countOpenId(String openId) throws Exception;

	public List<Address> queryEparchies(Integer addrId) throws Exception;

	public List<Address> queryCities(Integer addrId) throws Exception;

	public int countPhone(String phone) throws Exception;

	public Double getMoneyAccountBalance(int userId) throws Exception;

	public Indent findIndentByIndentNo(String indentNo) throws Exception;

	public UserExpand queryUserByPhone(String phone) throws Exception;

	public void insertNewJournalBookItem(JournalBookExpand journalBookExpand) throws Exception;

	public List<CommodityType> queryCommodityByParentId(int parentId) throws Exception;

	public void updateIndentFlag(int id) throws Exception;

	public void insertNewCertification(Certification certification) throws Exception;

	public List<JournalBookExpand> queryJournalBookRecordsByUserId(int id) throws Exception;

	public AccountExpand findUserMoneyAccountById(int userId) throws Exception;

	public void updateUserMoneyBalance(AccountExpand moneyAccount) throws Exception;

	public void updateUserMoneyTotalPlatformOutgoings(AccountExpand moneyAccount) throws Exception;

	public void insertNewMoneyChangeRecord(AccountRecordExpand accountRecordExpand) throws Exception;

	public AccountExpand findUserJfAccountById(int userId) throws Exception;

	public void updateUserJfBalance(AccountExpand jfAccount) throws Exception;

	public void insertNewJfChangeRecord(AccountRecordExpand accountRecordExpand) throws Exception;

	public void insertNewRechargeOrWithDrawRecord(RechargeAndWithDrawRecordExpand record) throws Exception;

	public RechargeAndWithDrawRecordExpand queryRechargeOrWithDarwRecord(String orderNo) throws Exception;

	public void updateRechageOrWithDarwRecordState(RechargeAndWithDrawRecordExpand record) throws Exception;

	public List<RechargeAndWithDrawRecordExpand> queryAllWithDrawsRecords(int id) throws Exception;

	public List<RechargeAndWithDrawRecordExpand> queryAllRechargeRecords(int id) throws Exception;

	public List<JournalBookExpand> queryAllJournalBookRecordsByClientId(UserExpandQueryVo userExpandQueryVo)
			throws Exception;

	public void updateCertification(Certification certification) throws Exception;

	public List<UserExpand> queryAllUsers() throws Exception;

	public List<UserExpand> queryAllManagers() throws Exception;

	public List<UserExpand> queryAllProxyUsers() throws Exception;

	public List<UserExpand> queryAllSellers() throws Exception;

	public List<UserExpand> queryAllSellersByProxyId() throws Exception;

	public void addNewUser(UserExpand userExpand) throws Exception;

	public void updateUser(UserExpand userExpand) throws Exception;

	public void deleteUser(UserExpand userExpand) throws Exception;

	public List<Address> queryAllProvinces() throws Exception;

	public List<Role> queryAllRoles() throws Exception;

	public boolean resetUserPwd(UserExpand userExpand) throws Exception;

	public UserExpand queryUserByKeyWords(String keyWords) throws Exception;

	public List<UserExpand> queryAllSellersByProxyId(Integer proxyId) throws Exception;

	public RoleExpand queryRoleByUserId(Integer userId) throws Exception;

	public Double queryTotalConsumeOffline(Integer id) throws Exception;

	public Double queryTotalConsumeOnline(Integer id) throws Exception;

	public Double queryTotalRecharge(Integer id) throws Exception;

	public Double queryTotalRewardJf(Integer id) throws Exception;

	public Double queryTotalWithDraws(Integer id) throws Exception;

	public Double queryTotalTransferDlb(Integer id) throws Exception;

	public Double queryTotalRewardMoney(Integer id) throws Exception;

	public Double queryTotalRewardJdb(Integer id) throws Exception;

	public Double queryTotalRewardYlj(Integer id) throws Exception;

	public Double queryTotalTransferYlj(Integer id) throws Exception;

	public Double queryTotalSubmitOrderValue(Integer id) throws Exception;

	public Integer queryMoneyAccountRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public Integer queryDlbAccountRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public Integer queryJfAccountRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public Integer queryYljTransferRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public Integer queryJournalBookRecordsTotalNumByClientId(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public Integer querySyAccountRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public Integer queryOnlineConsumeRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public List<OnlineJournalBookItem> queryOnlineConsumeRecord(UserExpandQueryVo userExpandQueryVo) throws Exception;

	public void addNewRecvCommodityAddress(RecvCommodityAddress address) throws Exception;

	public void delRecvCommodityAddress(Integer id) throws Exception;

	public void updateRecvCommodityAddress(RecvCommodityAddressQueryVo recvCommodityAddressQueryVo) throws Exception;

	public List<RecvCommodityAddress> queryMultiRecvCommodityAddress(
			RecvCommodityAddressQueryVo recvCommodityAddressQueryVo) throws Exception;

	public RecvCommodityAddress querySingleRecvCommodityAddress(RecvCommodityAddressQueryVo recvCommodityAddressQueryVo)
			throws Exception;

	public AccountExpand queryJdbAccountByUserId(Integer id) throws Exception;

	public void updateJdbAccountBalance(AccountExpand jdbAccount) throws Exception;

	public void insertNewJdbChangeRecord(AccountRecordExpand accountRecordExpand) throws Exception;
}