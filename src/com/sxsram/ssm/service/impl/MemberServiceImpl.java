package com.sxsram.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.DoubleLiteral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sxsram.ssm.aop.SystemServiceLog;
import com.sxsram.ssm.entity.AccountExpand;
import com.sxsram.ssm.entity.AccountRecordExpand;
import com.sxsram.ssm.entity.Address;
import com.sxsram.ssm.entity.Certification;
import com.sxsram.ssm.entity.CommodityType;
import com.sxsram.ssm.entity.Indent;
import com.sxsram.ssm.entity.JournalBookExpand;
import com.sxsram.ssm.entity.OnlineJournalBookItem;
import com.sxsram.ssm.entity.PlatformSyDistributedRecordExpand;
import com.sxsram.ssm.entity.PlatformYljSavedRecordExpand;
import com.sxsram.ssm.entity.RechargeAndWithDrawRecordExpand;
import com.sxsram.ssm.entity.RecvCommodityAddress;
import com.sxsram.ssm.entity.RecvCommodityAddressQueryVo;
import com.sxsram.ssm.entity.Role;
import com.sxsram.ssm.entity.RoleExpand;
import com.sxsram.ssm.entity.ShoppingMallExpand;
import com.sxsram.ssm.entity.UserExpand;
import com.sxsram.ssm.entity.UserExpandQueryVo;
import com.sxsram.ssm.exception.TestException;
import com.sxsram.ssm.mapper.MemberMapper;
import com.sxsram.ssm.mapper.UserMapper;
import com.sxsram.ssm.service.MemberService;
import com.sxsram.ssm.util.ConfigUtil;
import com.sxsram.ssm.util.Pagination;
import com.sxsram.ssm.util.QueryCondition;
import com.sxsram.ssm.util.QueryConditionAbstractItem;
import com.sxsram.ssm.util.QueryConditionItem;
import com.sxsram.ssm.util.QueryConditionOp;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public UserExpand findUserWhenLogin(UserExpand user) throws Exception {
		return memberMapper.queryUserWhenLogin(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void updateUserOpenId(UserExpand user) throws Exception {
		memberMapper.updateUserOpenId(user);
	}

	@Override
	public void updateCertification(Certification certification) throws Exception {
		memberMapper.updateCertification(certification);
	}

	@Override
	public UserExpand getUserAccountInfo(String keyWord) throws Exception {
		return memberMapper.queryUserAccountInfo(keyWord);
	}

	@Override
	public boolean usernameExist(String username) throws Exception {
		return memberMapper.countUsername(username) != 0 ? true : false;
	}

	@Override
	public boolean openIdExist(String openId) throws Exception {
		return memberMapper.countOpenId(openId) != 0 ? true : false;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public boolean registUser(UserExpand userExpand) throws Exception {
		try {
			Certification certification = new Certification();
			memberMapper.insertNewCertification(certification);
			userExpand.setCertification(certification);
			memberMapper.insertNewUser(userExpand);

			AccountExpand jfAccountExpand = new AccountExpand("plateform-jf-account");
			AccountExpand dlbAccountExpand = new AccountExpand("plateform-dlb-account");
			AccountExpand moneyAccountExpand = new AccountExpand("plateform-money-account");
			AccountExpand yljAccountExpand = new AccountExpand("plateform-ylj-account");

			jfAccountExpand.setUser(userExpand);
			dlbAccountExpand.setUser(userExpand);
			moneyAccountExpand.setUser(userExpand);
			yljAccountExpand.setUser(userExpand);

			memberMapper.insertNewJfAccount(jfAccountExpand);
			memberMapper.insertNewDlbAccount(dlbAccountExpand);
			memberMapper.insertNewMoneyAccount(moneyAccountExpand);
			memberMapper.insertNewYljAccount(yljAccountExpand);

			userExpand.setJfAccount(jfAccountExpand);
			userExpand.setDlbAccount(dlbAccountExpand);
			userExpand.setMoneyAccount(moneyAccountExpand);
			userExpand.setYljAccount(yljAccountExpand);

			memberMapper.updateUserAccountInformation(userExpand);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public UserExpand findUserByOpenId(String openId) throws Exception {
		return memberMapper.queryUserByOpenId(openId);
	}

	@Override
	public UserExpand findUserById(int id) throws Exception {
		return memberMapper.queryUserById(id);
	}

	@Override
	public List<ShoppingMallExpand> findAllShppingMalls() throws Exception {
		return memberMapper.queryAllShoppingMalls();
	}

	@Override
	public List<AccountRecordExpand> getJfAccountRecord(UserExpandQueryVo userExpandQueryVo) throws Exception {
		return memberMapper.queryAllJfRecords(userExpandQueryVo);
	}

	@Override
	public List<AccountRecordExpand> getMoneyAccountRecord(UserExpandQueryVo userExpandQueryVo) throws Exception {
		return memberMapper.queryAllMoneyRecords(userExpandQueryVo);
	}

	@Override
	public List<AccountRecordExpand> getDlbAccountRecord(UserExpandQueryVo userExpandQueryVo) throws Exception {
		return memberMapper.queryAllDlbRecords(userExpandQueryVo);
	}

	@Override
	public List<PlatformYljSavedRecordExpand> getYljAccountRecord(UserExpandQueryVo userExpandQueryVo)
			throws Exception {
		return memberMapper.queryAllYljRecords(userExpandQueryVo);
	}

	@Override
	public List<PlatformSyDistributedRecordExpand> getSyAccountRecord(UserExpandQueryVo userExpandQueryVo)
			throws Exception {
		return memberMapper.queryAllSyRecords(userExpandQueryVo);
	}

	@Override
	public List<Address> findEparchies(Integer addrId) throws Exception {
		return memberMapper.queryEparchies(addrId);
	}

	@Override
	public List<Address> findCities(Integer addrId) throws Exception {
		return memberMapper.queryCities(addrId);
	}

	@Override
	public boolean phoneExist(String phone) throws Exception {
		return memberMapper.countPhone(phone) != 0 ? true : false;
	}

	@Override
	public Double getMoneyAccountBalance(int userId) throws Exception {
		// TODO Auto-generated method stub
		return memberMapper.getMoneyAccountBalance(userId);
	}

	@Override
	public Indent findIndentByIndentNo(String indentNo) throws Exception {
		// TODO Auto-generated method stub
		return memberMapper.findIndentByIndentNo(indentNo);
	}

	@Override
	public UserExpand findUserByPhone(String phone) throws Exception {
		// TODO Auto-generated method stub
		return memberMapper.queryUserByPhone(phone);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	public void updateUserMoneyBalance(int userId, double money, String desc) throws Exception {

		AccountExpand moneyAccount = memberMapper.findUserMoneyAccountById(userId);

		moneyAccount.setAccountBalance(moneyAccount.getAccountBalance() + money);
		memberMapper.updateUserMoneyBalance(moneyAccount);

		AccountRecordExpand accountRecordExpand = new AccountRecordExpand();
		accountRecordExpand.setOperateDesc(desc);
		accountRecordExpand.setOperateNum(money);
		accountRecordExpand.setOperation(money > 0 ? 0 : 1);
		accountRecordExpand.setAccount(moneyAccount);
		memberMapper.insertNewMoneyChangeRecord(accountRecordExpand);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	private void updateUserMoneyTotalPlatformOutgoings(int userId, double amount) throws Exception {
		AccountExpand moneyAccount = memberMapper.findUserMoneyAccountById(userId);
		moneyAccount.setTotalPlatformOutgoings(moneyAccount.getTotalPlatformIncomings() + amount);
		memberMapper.updateUserMoneyTotalPlatformOutgoings(moneyAccount);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	public void updateUserJfBalance(int userId, double rewardJf, String desc) throws Exception {
		AccountExpand jfAccount = memberMapper.findUserJfAccountById(userId);

		jfAccount.setAccountBalance(jfAccount.getAccountBalance() + rewardJf);
		memberMapper.updateUserJfBalance(jfAccount);

		AccountRecordExpand accountRecordExpand = new AccountRecordExpand();
		accountRecordExpand.setOperateDesc(desc);
		accountRecordExpand.setOperateNum(rewardJf);
		accountRecordExpand.setOperation(rewardJf > 0 ? 0 : 1);
		accountRecordExpand.setAccount(jfAccount);
		memberMapper.insertNewJfChangeRecord(accountRecordExpand);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void saveNewJournalBookItem(JournalBookExpand journalBookExpand) throws Exception {
		// TODO Auto-generated method stub
		memberMapper.insertNewJournalBookItem(journalBookExpand);
		memberMapper.updateIndentFlag(journalBookExpand.getIndent().getId());

		double money = journalBookExpand.getAmount() * journalBookExpand.getPremiumRates() / 100;
		// 更新商家现金账户
		updateUserMoneyBalance(journalBookExpand.getBusiness().getId(), -1 * money, "商家报单");
		// 更新平台现金账户
		updateUserMoneyBalance(1, money, "商家报单");// 1代表平台账户
		// 更新买家累计消费
		updateUserMoneyTotalPlatformOutgoings(journalBookExpand.getClient().getId(), journalBookExpand.getAmount());
		// if (journalBookExpand.getReward()!=null &&
		// journalBookExpand.getRewardJf() > 0) {
		// // 更新奖励用户积分账户
		// updateUserJfBalance(journalBookExpand.getReward().getId(),
		// journalBookExpand.getRewardJf(), "商家报单");
		// // 更新平台积分账户
		// updateUserJfBalance(1, -1 * journalBookExpand.getRewardJf(), "商家报单");
		// }
		// if (journalBookExpand.getClient()!=null &&
		// journalBookExpand.getGiftJf() > 0) {
		// // 更新商家积分账户
		// updateUserJfBalance(journalBookExpand.getClient().getId(),
		// journalBookExpand.getGiftJf(), "商家报单");
		// // 更新平台积分账户
		// updateUserJfBalance(1, -1 * journalBookExpand.getGiftJf(), "商家报单");
		// }
	}

	@Override
	public List<CommodityType> findCommodityByParentId(int parentId) throws Exception {
		return memberMapper.queryCommodityByParentId(parentId);
	}

	@Override
	public List<JournalBookExpand> getJournalBookRecordsByUserId(int id) throws Exception {
		return memberMapper.queryJournalBookRecordsByUserId(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void userRecharge(RechargeAndWithDrawRecordExpand record) throws Exception {
		memberMapper.insertNewRechargeOrWithDrawRecord(record);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void userWithDraw(RechargeAndWithDrawRecordExpand record) throws Exception {
		memberMapper.insertNewRechargeOrWithDrawRecord(record);
	}

	@Override
	public RechargeAndWithDrawRecordExpand findRechargeRecordByOrderNo(String orderNo) throws Exception {
		return memberMapper.queryRechargeOrWithDarwRecord(orderNo);
	}

	@Override
	public RechargeAndWithDrawRecordExpand findWithDrawRecordByOrderNo(String orderNo) throws Exception {
		return memberMapper.queryRechargeOrWithDarwRecord(orderNo);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void updateRechargeRecordState(RechargeAndWithDrawRecordExpand record) throws Exception {
		memberMapper.updateRechageOrWithDarwRecordState(record);
		if (record.getOperateState() == ConfigUtil.TradeState.PAYSUCCESS.ordinal()) {
			if (record.getOperateType() == ConfigUtil.TradeType.RECHARGE.ordinal()) {
				updateUserMoneyBalance(record.getUser().getId(), record.getOperateNum(), "账户充值");
			} else {
				updateUserMoneyBalance(record.getUser().getId(), -1 * record.getOperateNum(), "账户提现");
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void updateWithDrawRecordState(RechargeAndWithDrawRecordExpand record) throws Exception {
		memberMapper.updateRechageOrWithDarwRecordState(record);
		if (record.getOperateState() == ConfigUtil.TradeState.PAYSUCCESS.ordinal()) {
			if (record.getOperateType() == ConfigUtil.TradeType.RECHARGE.ordinal()) {
				updateUserMoneyBalance(record.getUser().getId(), record.getOperateNum(), "账户充值");
			} else {
				updateUserMoneyBalance(record.getUser().getId(), -1 * record.getOperateNum(), "账户提现");
			}
		}
	}

	@Override
	public List<RechargeAndWithDrawRecordExpand> findAllWithDrawsRecords(int id) throws Exception {
		// TODO Auto-generated method stub
		return memberMapper.queryAllWithDrawsRecords(id);
	}

	@Override
	public List<JournalBookExpand> getJournalBookRecordsByClientId(UserExpandQueryVo userExpandQueryVo)
			throws Exception {
		return memberMapper.queryAllJournalBookRecordsByClientId(userExpandQueryVo);
	}

	@SystemServiceLog(description = "Service: Query all users")
	@Override
	public List<UserExpand> findAllUsers() throws Exception {
		if (1 == 0) {
			throw new TestException("Test Exception message");
		}
		return memberMapper.queryAllUsers();
	}

	@Override
	public List<UserExpand> findAllProxyUsers() {
		// TODO Auto-generated method stub
		try {
			return memberMapper.queryAllProxyUsers();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<UserExpand> findAllManagers() {
		// TODO Auto-generated method stub
		try {
			return memberMapper.queryAllManagers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UserExpand> findAllSeller() {
		// TODO Auto-generated method stub
		try {
			return memberMapper.queryAllSellers();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public boolean addNewUser(UserExpand userExpand) {
		try {
			memberMapper.addNewUser(userExpand);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@SystemServiceLog(description = "UpdateUserService")
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public boolean updateUser(UserExpand userExpand) throws Exception {
		memberMapper.updateUser(userExpand);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public boolean deleteUser(UserExpand userExpand) {
		try {
			memberMapper.deleteUser(userExpand);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public List<Address> findAllProvinces() {
		try {
			return memberMapper.queryAllProvinces();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Role> findAllRoles() {
		// TODO Auto-generated method stub
		try {
			return memberMapper.queryAllRoles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public boolean resetUserPwd(UserExpand userExpand) {
		// TODO Auto-generated method stub
		try {
			memberMapper.updateUser(userExpand);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public UserExpand findUserByKeyWords(String keyWords) {
		try {
			return memberMapper.queryUserByKeyWords(keyWords);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserExpand> findAllSellersByProxyId(Integer proxyId) {

		try {
			return memberMapper.queryAllSellersByProxyId(proxyId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RoleExpand findRoleByUserId(Integer userId) {
		try {
			return memberMapper.queryRoleByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Double getTotalConsumeOffline(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Double res = memberMapper.queryTotalConsumeOffline(id);
		return res == null ? 0 : res;
	}

	@Override
	public Double getTotalConsumeOnline(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Double res = memberMapper.queryTotalConsumeOnline(id);
		return res == null ? 0 : res;
	}

	@Override
	public Double getTotalRecharge(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Double res = memberMapper.queryTotalRecharge(id);
		return res == null ? 0 : res;
	}

	@Override
	public Double getTotalRewardJf(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Double res = memberMapper.queryTotalRewardJf(id);
		return res == null ? 0 : res;
	}

	@Override
	public Double getTotalWithDraws(Integer id) throws Exception {
		Double res = memberMapper.queryTotalWithDraws(id);
		return res == null ? 0 : res;
	}

	@Override
	public Double getTotalTransferDlb(Integer id) throws Exception {
		Double res = memberMapper.queryTotalTransferDlb(id);
		return res == null ? 0 : res;
	}

	@Override
	public Double getTotalRewardMoney(Integer id) throws Exception {
		Double res = memberMapper.queryTotalRewardMoney(id);
		return res == null ? 0 : res;
	}

	@Override
	public Double getTotalRewardJdb(Integer id) throws Exception {
		Double res = memberMapper.queryTotalRewardJdb(id);
		return res == null ? 0 : res;
	}

	@Override
	public Double getTotalRewardYlj(Integer id) throws Exception {
		Double res = memberMapper.queryTotalRewardYlj(id);
		return res == null ? 0 : res;
	}

	@Override
	public Double getTotalTransferYlj(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Double res = memberMapper.queryTotalTransferYlj(id);
		return res == null ? 0 : res;
	}

	@Override
	public Double getTotalSubmitOrderValue(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Double res = memberMapper.queryTotalSubmitOrderValue(id);
		return res == null ? 0 : res;
	}

	@Override
	public Integer getMoneyAccountRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception {
		return memberMapper.queryMoneyAccountRecordsTotalNum(userExpandQueryVo);
	}

	@Override
	public Integer getDlbAccountRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception {
		return memberMapper.queryDlbAccountRecordsTotalNum(userExpandQueryVo);
	}

	@Override
	public Integer getJfAccountRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception {
		return memberMapper.queryJfAccountRecordsTotalNum(userExpandQueryVo);
	}

	@Override
	public Integer getYljTransferRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception {
		return memberMapper.queryYljTransferRecordsTotalNum(userExpandQueryVo);
	}

	@Override
	public Integer getJournalBookRecordsTotalNumByClientId(UserExpandQueryVo userExpandQueryVo) throws Exception {
		return memberMapper.queryJournalBookRecordsTotalNumByClientId(userExpandQueryVo);
	}

	@Override
	public Integer getSyAccountRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception {
		return memberMapper.querySyAccountRecordsTotalNum(userExpandQueryVo);
	}

	@Override
	public Integer getOnlineConsumeRecordsTotalNum(UserExpandQueryVo userExpandQueryVo) throws Exception {
		return memberMapper.queryOnlineConsumeRecordsTotalNum(userExpandQueryVo);
	}

	@Override
	public List<AccountRecordExpand> getYljTransferRecord(UserExpandQueryVo userExpandQueryVo) throws Exception {
		return memberMapper.queryAllYljTransferRecords(userExpandQueryVo);
	}

	@Override
	public List<OnlineJournalBookItem> getOnlineConsumeRecord(UserExpandQueryVo userExpandQueryVo) throws Exception {
		return memberMapper.queryOnlineConsumeRecord(userExpandQueryVo);
	}

	/**
	 * 用户收货地址操作
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void addNewRecvCommodityAddress(RecvCommodityAddress recvCommodityAddress) throws Exception {
		memberMapper.addNewRecvCommodityAddress(recvCommodityAddress);
		this.setRecvCommodityAddressAsDefault(recvCommodityAddress.getId(), recvCommodityAddress.getUser().getId());
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void delRecvCommodityAddress(Integer id) throws Exception {
		RecvCommodityAddress recvCommodityAddress = null, lastModifiedAddress = null;
		RecvCommodityAddressQueryVo recvCommodityAddressQueryVo = new RecvCommodityAddressQueryVo();

		// 1.查寻指定id的地址
		List<QueryConditionAbstractItem> items = new ArrayList<QueryConditionAbstractItem>();
		items.add(new QueryConditionItem("id", id + "", QueryConditionOp.EQ));
		recvCommodityAddressQueryVo.setQueryCondition(new QueryCondition(items));
		recvCommodityAddress = memberMapper.querySingleRecvCommodityAddress(recvCommodityAddressQueryVo);

		// 2.删除
		memberMapper.delRecvCommodityAddress(id);
		if (recvCommodityAddress == null)
			return;
		if (recvCommodityAddress.getIsDefault() == 1) {
			// 2.查询修改时间最晚的地址
			recvCommodityAddressQueryVo.setQueryCondition(null);
			recvCommodityAddressQueryVo.setPagination(new Pagination(1, 1, 0, "{\"lastModifyTime\":\"desc\"}"));
			lastModifiedAddress = memberMapper.querySingleRecvCommodityAddress(recvCommodityAddressQueryVo);
			this.setRecvCommodityAddressAsDefault(lastModifiedAddress.getId(), lastModifiedAddress.getUserId());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void updateRecvCommodityAddress(RecvCommodityAddressQueryVo recvCommodityAddressQueryVo) throws Exception {
		memberMapper.updateRecvCommodityAddress(recvCommodityAddressQueryVo);
	}

	@Override
	public List<RecvCommodityAddress> getRecvCommodityAddress(RecvCommodityAddressQueryVo recvCommodityAddressQueryVo)
			throws Exception {
		return memberMapper.queryMultiRecvCommodityAddress(recvCommodityAddressQueryVo);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void setRecvCommodityAddressAsDefault(Integer id, Integer userId) throws Exception {
		RecvCommodityAddressQueryVo addressQueryVo = new RecvCommodityAddressQueryVo();
		// 1.先把所有的全部设置为 non-default
		List<QueryConditionAbstractItem> items = new ArrayList<QueryConditionAbstractItem>();
		items.add(new QueryConditionItem("userId", userId + "", QueryConditionOp.EQ));
		addressQueryVo.setIsDefault(0);// 更新字段
		addressQueryVo.setQueryCondition(new QueryCondition(items));// where条件
		memberMapper.updateRecvCommodityAddress(addressQueryVo);

		// 2.再把指定id设置为default
		items.clear();
		items.add(new QueryConditionItem("id", id + "", QueryConditionOp.EQ));
		addressQueryVo.setIsDefault(1);// 更新字段
		addressQueryVo.setQueryCondition(new QueryCondition(items));// where条件
		memberMapper.updateRecvCommodityAddress(addressQueryVo);
	}

	@Override
	public AccountExpand getJdbAccountByUserId(Integer id) throws Exception {
		return memberMapper.queryJdbAccountByUserId(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void updateJdbBalance(double num, String desc, Integer userId) throws Exception {
		// 1.增加一条记录 JdbAccountRecord
		// 2.更新余额
		AccountExpand jdbAccount = memberMapper.queryJdbAccountByUserId(userId);

		jdbAccount.setAccountBalance(jdbAccount.getAccountBalance() + num);
		memberMapper.updateJdbAccountBalance(jdbAccount);

		AccountRecordExpand accountRecordExpand = new AccountRecordExpand();
		accountRecordExpand.setOperateDesc(desc);
		accountRecordExpand.setOperateNum(num);
		accountRecordExpand.setOperation(num > 0 ? 0 : 1);
		accountRecordExpand.setAccount(jdbAccount);
		accountRecordExpand.setBalance(jdbAccount.getAccountBalance());
		memberMapper.insertNewJdbChangeRecord(accountRecordExpand);
	}
}