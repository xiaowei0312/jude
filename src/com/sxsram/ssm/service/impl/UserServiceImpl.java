package com.sxsram.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sxsram.ssm.entity.AccountExpand;
import com.sxsram.ssm.entity.AccountRecordExpand;
import com.sxsram.ssm.entity.Address;
import com.sxsram.ssm.entity.Certification;
import com.sxsram.ssm.entity.CommodityType;
import com.sxsram.ssm.entity.Indent;
import com.sxsram.ssm.entity.JournalBookExpand;
import com.sxsram.ssm.entity.PlatformSyDistributedRecordExpand;
import com.sxsram.ssm.entity.ShoppingMallExpand;
import com.sxsram.ssm.entity.User;
import com.sxsram.ssm.entity.UserAccountExpand;
import com.sxsram.ssm.entity.UserExpand;
import com.sxsram.ssm.entity.UserExpandQueryVo;
import com.sxsram.ssm.entity.UserExtra;
import com.sxsram.ssm.entity.UserExtraQueryVo;
import com.sxsram.ssm.entity.PlatformYljSavedRecordExpand;
import com.sxsram.ssm.entity.RechargeAndWithDrawRecordExpand;
import com.sxsram.ssm.entity.Role;
import com.sxsram.ssm.entity.RoleExpand;
import com.sxsram.ssm.entity.RoleExpandQueryVo;
import com.sxsram.ssm.mapper.UserMapper;
import com.sxsram.ssm.service.UserExtraService;
import com.sxsram.ssm.service.UserService;
import com.sxsram.ssm.util.ConfigUtil;
import com.sxsram.ssm.util.QueryCondition;
import com.sxsram.ssm.util.QueryConditionAbstractItem;
import com.sxsram.ssm.util.QueryConditionItem;
import com.sxsram.ssm.util.QueryConditionOp;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserExtraService userExtraService;

	@Override
	public UserExpand findUserWhenLogin(UserExpand user) throws Exception {
		return userMapper.queryUserWhenLogin(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void updateUserOpenId(UserExpand user) throws Exception {
		userMapper.updateUserOpenId(user);
	}

	@Override
	public void updateCertification(Certification certification) throws Exception {
		userMapper.updateCertification(certification);
	}

	@Override
	public UserExpand getUserAccountInfo(String keyWord) throws Exception {
		return userMapper.queryUserAccountInfo(keyWord);
	}

	@Override
	public boolean usernameExist(String username) throws Exception {
		return userMapper.countUsername(username) != 0 ? true : false;
	}

	@Override
	public boolean openIdExist(String openId) throws Exception {
		return userMapper.countOpenId(openId) != 0 ? true : false;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3, rollbackFor = {
			java.lang.Exception.class })
	@Override
	public boolean registUser(UserExpand userExpand) throws Exception {
		Certification certification = new Certification();
		userMapper.insertNewCertification(certification);
		userExpand.setCertification(certification);
		userMapper.insertNewUser(userExpand);

		UserExtra userExtra = new UserExtra();
		AccountExpand jfAccountExpand = new AccountExpand("plateform-jf-account");
		AccountExpand dlbAccountExpand = new AccountExpand("plateform-dlb-account");
		AccountExpand moneyAccountExpand = new AccountExpand("plateform-money-account");
		AccountExpand yljAccountExpand = new AccountExpand("plateform-ylj-account");
		AccountExpand jdbAccountExpand = new AccountExpand("plateform-jdb-account");

		jfAccountExpand.setUser(userExpand);
		dlbAccountExpand.setUser(userExpand);
		moneyAccountExpand.setUser(userExpand);
		yljAccountExpand.setUser(userExpand);
		jdbAccountExpand.setUser(userExpand);
		userExtra.setUserId(userExpand.getId());
		userExtra.setGiveMoneyFlag(0);

		userMapper.insertNewJfAccount(jfAccountExpand);
		userMapper.insertNewDlbAccount(dlbAccountExpand);
		userMapper.insertNewMoneyAccount(moneyAccountExpand);
		userMapper.insertNewYljAccount(yljAccountExpand);
		userMapper.insertNewJdbAccount(jdbAccountExpand);
		userExtraService.addNewUserExtra(userExtra);

		userExpand.setJfAccount(jfAccountExpand);
		userExpand.setDlbAccount(dlbAccountExpand);
		userExpand.setMoneyAccount(moneyAccountExpand);
		userExpand.setYljAccount(yljAccountExpand);
		userExpand.setJdbAccount(jdbAccountExpand);

		userMapper.updateUserAccountInformation(userExpand);
		return true;
	}

	@Override
	public UserExpand findUserByOpenId(String openId) throws Exception {
		return userMapper.queryUserByOpenId(openId);
	}

	@Override
	public UserExpand findUserById(int id) throws Exception {
		return userMapper.queryUserById(id);
	}

	@Override
	public List<ShoppingMallExpand> findAllShppingMalls() throws Exception {
		return userMapper.queryAllShoppingMalls();
	}

	@Override
	public List<AccountRecordExpand> getMoneyAccountRecord(Integer userId) throws Exception {
		return userMapper.queryAllMoneyRecords(userId);
	}

	@Override
	public List<AccountRecordExpand> getJfAccountRecord(Integer userId) throws Exception {
		return userMapper.queryAllJfRecords(userId);
	}

	@Override
	public List<AccountRecordExpand> getDlbAccountRecord(Integer userId) throws Exception {
		return userMapper.queryAllDlbRecords(userId);
	}

	@Override
	public List<PlatformYljSavedRecordExpand> getYljAccountRecord(Integer userId) throws Exception {
		return userMapper.queryAllYljRecords(userId);
	}

	@Override
	public List<PlatformSyDistributedRecordExpand> getSyAccountRecord(Integer userId) throws Exception {
		return userMapper.queryAllSyRecords(userId);
	}

	@Override
	public List<Address> findEparchies(Integer addrId) throws Exception {
		return userMapper.queryEparchies(addrId);
	}

	@Override
	public List<Address> findCities(Integer addrId) throws Exception {
		return userMapper.queryCities(addrId);
	}

	@Override
	public boolean phoneExist(String phone) throws Exception {
		return userMapper.countPhone(phone) != 0 ? true : false;
	}

	@Override
	public Double getMoneyAccountBalance(int userId) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getMoneyAccountBalance(userId);
	}

	@Override
	public Indent findIndentByIndentNo(String indentNo) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.findIndentByIndentNo(indentNo);
	}

	@Override
	public UserExpand findUserByPhone(String phone) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.queryUserByPhone(phone);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	public void updateUserMoneyBalance(int userId, double money, String desc) throws Exception {

		AccountExpand moneyAccount = userMapper.findUserMoneyAccountById(userId);

		moneyAccount.setAccountBalance(moneyAccount.getAccountBalance() + money);
		userMapper.updateUserMoneyBalance(moneyAccount);

		AccountRecordExpand accountRecordExpand = new AccountRecordExpand();
		accountRecordExpand.setOperateDesc(desc);
		accountRecordExpand.setOperateNum(money);
		accountRecordExpand.setOperation(money > 0 ? 0 : 1);
		accountRecordExpand.setAccount(moneyAccount);
		userMapper.insertNewMoneyChangeRecord(accountRecordExpand);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	private void updateUserMoneyTotalPlatformOutgoings(int userId, double amount) throws Exception {
		AccountExpand moneyAccount = userMapper.findUserMoneyAccountById(userId);
		moneyAccount.setTotalPlatformOutgoings(moneyAccount.getTotalPlatformIncomings() + amount);
		userMapper.updateUserMoneyTotalPlatformOutgoings(moneyAccount);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	public void updateUserJfBalance(int userId, double rewardJf, String desc) throws Exception {
		AccountExpand jfAccount = userMapper.findUserJfAccountById(userId);

		jfAccount.setAccountBalance(jfAccount.getAccountBalance() + rewardJf);
		userMapper.updateUserJfBalance(jfAccount);

		AccountRecordExpand accountRecordExpand = new AccountRecordExpand();
		accountRecordExpand.setOperateDesc(desc);
		accountRecordExpand.setOperateNum(rewardJf);
		accountRecordExpand.setOperation(rewardJf > 0 ? 0 : 1);
		accountRecordExpand.setAccount(jfAccount);
		userMapper.insertNewJfChangeRecord(accountRecordExpand);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void saveNewJournalBookItem(JournalBookExpand journalBookExpand) throws Exception {
		// TODO Auto-generated method stub
		userMapper.insertNewJournalBookItem(journalBookExpand);
		userMapper.updateIndentFlag(journalBookExpand.getIndent().getId());

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
		return userMapper.queryCommodityByParentId(parentId);
	}

	@Override
	public List<JournalBookExpand> findAllJournalBookRecordsByUserId(int id) throws Exception {
		return userMapper.queryAllJournalBookRecordsByUserId(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void userRecharge(RechargeAndWithDrawRecordExpand record) throws Exception {
		userMapper.insertNewRechargeOrWithDrawRecord(record);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void userWithDraw(RechargeAndWithDrawRecordExpand record) throws Exception {
		userMapper.insertNewRechargeOrWithDrawRecord(record);
	}

	@Override
	public RechargeAndWithDrawRecordExpand findRechargeRecordByOrderNo(String orderNo) throws Exception {
		return userMapper.queryRechargeOrWithDarwRecord(orderNo);
	}

	@Override
	public RechargeAndWithDrawRecordExpand findWithDrawRecordByOrderNo(String orderNo) throws Exception {
		return userMapper.queryRechargeOrWithDarwRecord(orderNo);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public void updateRechargeRecordState(RechargeAndWithDrawRecordExpand record) throws Exception {
		userMapper.updateRechageOrWithDarwRecordState(record);
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
		userMapper.updateRechageOrWithDarwRecordState(record);
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
		return userMapper.queryAllWithDrawsRecords(id);
	}

	@Override
	public List<JournalBookExpand> findAllJournalBookRecordsByClientId(int clientId) throws Exception {
		return userMapper.queryAllJournalBookRecordsByClientId(clientId);
	}

	@Override
	public List<UserExpand> findAllUsers() {
		try {
			return userMapper.queryAllUsers();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<UserExpand> findAllProxyUsers() {
		// TODO Auto-generated method stub
		try {
			return userMapper.queryAllProxyUsers();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<UserExpand> findAllManagers() {
		// TODO Auto-generated method stub
		try {
			return userMapper.queryAllManagers();
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
			return userMapper.queryAllSellers();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public boolean addNewUser(UserExpand userExpand) {
		try {
			userMapper.addNewUser(userExpand);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public boolean updateUser(UserExpand userExpand) {
		try {
			userMapper.updateUser(userExpand);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 3)
	@Override
	public boolean deleteUser(UserExpand userExpand) {
		try {
			userMapper.deleteUser(userExpand);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public List<Address> findAllProvinces() {
		try {
			return userMapper.queryAllProvinces();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Role> findAllRoles() {
		// TODO Auto-generated method stub
		try {
			return userMapper.queryAllRoles();
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
			userMapper.updateUser(userExpand);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public UserExpand findUserByKeyWords(String keyWords) {
		try {
			return userMapper.queryUserByKeyWords(keyWords);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserExpand> findAllSellersByProxyId(Integer proxyId) {

		try {
			return userMapper.queryAllSellersByProxyId(proxyId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer getUserListCount(UserExpandQueryVo userExpandQueryVo) throws Exception {
		return userMapper.queryUserListCount(userExpandQueryVo);
	}

	@Override
	public List<UserExpand> getUserList(UserExpandQueryVo userExpandQueryVo) throws Exception {
		List<UserExpand> userExpands = userMapper.queryMultiUserList(userExpandQueryVo);
		for (UserExpand user : userExpands) {
			UserExtra extra = null;
			UserExtraQueryVo userExtraQueryVo = new UserExtraQueryVo();
			List<QueryConditionAbstractItem> items = new ArrayList<QueryConditionAbstractItem>();
			items.add(new QueryConditionItem("e.delFlag", "0", QueryConditionOp.EQ));
			items.add(new QueryConditionItem("e.userId", user.getId() + "", QueryConditionOp.EQ));
			userExtraQueryVo.setQueryCondition(new QueryCondition(items));
			extra = userExtraService.findUserExtra(userExtraQueryVo);
			user.setUserExtra(extra);
		}
		return userExpands;
	}

	@Override
	public Integer getRolesTotalNum(RoleExpandQueryVo roleExpandQueryVo) throws Exception {
		return userMapper.queryRolesTotalNum(roleExpandQueryVo);
	}

	@Override
	public List<RoleExpand> getRoles(RoleExpandQueryVo roleExpandQueryVo) throws Exception {
		return userMapper.queryMultiRoles(roleExpandQueryVo);
	}
}
