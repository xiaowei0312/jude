package com.sxsram.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sxsram.ssm.controller.MallController.PageObj;
import com.sxsram.ssm.entity.Role;
import com.sxsram.ssm.entity.RoleExpand;
import com.sxsram.ssm.entity.RoleExpandQueryVo;
import com.sxsram.ssm.entity.ShoppingMallType;
import com.sxsram.ssm.entity.ShoppingMallTypeQueryVo;
import com.sxsram.ssm.entity.UserExpand;
import com.sxsram.ssm.entity.UserExpandQueryVo;
import com.sxsram.ssm.entity.UserExtra;
import com.sxsram.ssm.entity.UserExtraQueryVo;
import com.sxsram.ssm.entity.UserExpand;
import com.sxsram.ssm.entity.UserExpandQueryVo;
import com.sxsram.ssm.service.UserExtraService;
import com.sxsram.ssm.service.UserService;
import com.sxsram.ssm.util.ConfigUtil;
import com.sxsram.ssm.util.JsonResult;
import com.sxsram.ssm.util.Pagination;
import com.sxsram.ssm.util.QueryCondition;
import com.sxsram.ssm.util.QueryConditionAbstractItem;
import com.sxsram.ssm.util.QueryConditionItem;
import com.sxsram.ssm.util.QueryConditionOp;
import com.sxsram.ssm.util.QueryConditionOrItems;
import com.sxsram.ssm.util.StringUtil;

@Controller()
@RequestMapping(value = "/user", method = { RequestMethod.GET, RequestMethod.POST })
public class UserController2 {
	@Autowired
	private UserService userService;
	@Resource
	private UserExtraService userExtraService;

	@RequestMapping(value = "/userOverview", method = { RequestMethod.GET, RequestMethod.POST })
	public String userCenter(HttpSession session, Model model, String code, String state) throws Exception {
		return "user2/userOverview";
	}

	// @RequestMapping(value = "/managerManagement", method = {
	// RequestMethod.GET, RequestMethod.POST })
	// public String managerManagement() throws Exception {
	// return "user/managerManagement";
	// }
	//
	// @RequestMapping(value = "/proxyManagement", method = { RequestMethod.GET,
	// RequestMethod.POST })
	// public String proxyManagement(HttpSession session) throws Exception {
	// session.removeAttribute("user");
	// return "user/proxyManagement";
	// }
	//
	// @RequestMapping(value = "/sellerManagement", method = {
	// RequestMethod.GET, RequestMethod.POST })
	// public String sellerManagement(HttpSession session) throws Exception {
	// session.removeAttribute("user");
	// return "user/sellerManagement";
	// }
	//
	// @RequestMapping(value = "/userStatics", method = { RequestMethod.GET,
	// RequestMethod.POST })
	// public String userStatics(HttpSession session) throws Exception {
	// session.removeAttribute("user");
	// return "user/userStatics";
	// }

	class PageObj {
		Integer totalCount;
		Object objList;

		public PageObj() {
		}

		public PageObj(Integer totalCount, Object objList) {
			this.totalCount = totalCount;
			this.objList = objList;
		}
	}

	@RequestMapping(value = "/getUserListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getMallListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize, String searchKey,
			String searchStartDate, String searchEndDate, String roleSelect, Integer timeOrderBy) throws Exception {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<UserExpand> shoppingMallExpands = null;
		Integer totalNum = 0;

		UserExpandQueryVo userExpandQueryVo = new UserExpandQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		if (!StringUtils.isEmpty(searchKey)) {
			QueryConditionOrItems queryConditionOrItems = new QueryConditionOrItems();
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("user.username", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("user.phone", searchKey, QueryConditionOp.LIKE));
			whereCondList.add(queryConditionOrItems);
		}

		if (!StringUtil.isEmpty(searchStartDate)) {
			String arr[] = searchStartDate.split("/");
			String startDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("user.addTime", startDate.toString(), QueryConditionOp.GE));
		}

		if (!StringUtil.isEmpty(searchEndDate)) {
			String arr[] = searchEndDate.split("/");
			String endDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("user.addTime", endDate.toString(), QueryConditionOp.LE));
		}

		if (!StringUtil.isEmpty(roleSelect) && !roleSelect.equals("-2")) {
			if(roleSelect.equals("9")){//停止返现会员
				whereCondList.add(new QueryConditionItem("role.id", 2+"", QueryConditionOp.EQ));
				whereCondList.add(new QueryConditionItem("extra.giveMoneyFlag", 1+"", QueryConditionOp.EQ));
			}else{
				whereCondList.add(new QueryConditionItem("role.id", roleSelect, QueryConditionOp.EQ));
			}
		}

		/*
		 * UserExpand sessionUser = (UserExpand) session.getAttribute("user");
		 * if (sessionUser == null) { jsonResult.resultCode = "-3";
		 * jsonResult.resultMsg = "登录超时，请重新登录"; return gson.toJson(jsonResult);
		 * } if (sessionUser.getRole().getRoleName().equals("代理商")) {
		 * whereCondList .add(new QueryConditionItem("user.proxy_user_id",
		 * sessionUser.getId() + "", QueryConditionOp.EQ)); }
		 */
		whereCondList.add(new QueryConditionItem("user.status", 0 + "", QueryConditionOp.EQ));

		try {
			userExpandQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			totalNum = userService.getUserListCount(userExpandQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> orderByMap = new HashMap<>();
		if (timeOrderBy != null) {
			if (timeOrderBy == 0) {
				orderByMap.put("user.addTime", "desc");
			} else if(timeOrderBy == 1) {
				orderByMap.put("user.addTime", "asc");
			} else if(timeOrderBy == 2){	//提现额度降序
				orderByMap.put("extra.withdrawLimit", "desc");
			} else{	//提现额度升序
				orderByMap.put("extra.withdrawLimit", "asc");
			}
		}
		try {
			userExpandQueryVo.setPagination(new Pagination(pageSize, pageNo, 0, orderByMap));
			shoppingMallExpands = userService.getUserList(userExpandQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.resultCode = "-1";
			jsonResult.resultMsg = e.getMessage();
			return gson.toJson(jsonResult);
		}

		if (totalNum == null)
			totalNum = 0;
		jsonResult.resultObj = new PageObj(totalNum, shoppingMallExpands);
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/setGiveMoneyFlagAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String setGiveMoneyFlagAjax(UserExpand userExpand, Integer giveMoneyFlag) {
		Gson gson = new Gson();
		JsonResult jsonResult = new JsonResult("0", "0");
		if (userExpand == null || userExpand.getId() == null) {
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = "找不要要操作的用户";
			return gson.toJson(jsonResult);
		}

		// 找到对应的UserExtra
		UserExtra userExtra = null;
		UserExtraQueryVo userExtraQueryVo = new UserExtraQueryVo();
		List<QueryConditionAbstractItem> items = new ArrayList<QueryConditionAbstractItem>();
		items.add(new QueryConditionItem("e.delFlag", "0", QueryConditionOp.EQ));
		items.add(new QueryConditionItem("e.userId", userExpand.getId() + "", QueryConditionOp.EQ));
		userExtraQueryVo.setQueryCondition(new QueryCondition(items));
		try {
			userExtra = userExtraService.findUserExtra(userExtraQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
			return gson.toJson(jsonResult);
		}
		if (userExtra == null) {
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = "找不到对应的UserExtra";
			return gson.toJson(jsonResult);
		}

		// 5.更新数据库
		userExtra.setGiveMoneyFlag(giveMoneyFlag);
		try {
			userExtraService.updateUserExtra(userExtra);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
			return gson.toJson(jsonResult);
		}
		return gson.toJson(jsonResult);
	}
	
	@RequestMapping(value = "/setWithdrawLimit", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String setWithdrawLimit(UserExpand userExpand, Integer withdrawLimit) {
		Gson gson = new Gson();
		JsonResult jsonResult = new JsonResult("0", "0");
		if (userExpand == null || userExpand.getId() == null) {
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = "找不要要操作的用户";
			return gson.toJson(jsonResult);
		}

		// 找到对应的UserExtra
		UserExtra userExtra = null;
		UserExtraQueryVo userExtraQueryVo = new UserExtraQueryVo();
		List<QueryConditionAbstractItem> items = new ArrayList<QueryConditionAbstractItem>();
		items.add(new QueryConditionItem("e.delFlag", "0", QueryConditionOp.EQ));
		items.add(new QueryConditionItem("e.userId", userExpand.getId() + "", QueryConditionOp.EQ));
		userExtraQueryVo.setQueryCondition(new QueryCondition(items));
		try {
			userExtra = userExtraService.findUserExtra(userExtraQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
			return gson.toJson(jsonResult);
		}
		if (userExtra == null) {
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = "找不到对应的UserExtra";
			return gson.toJson(jsonResult);
		}

		// 5.更新数据库
		userExtra.setWithdrawLimit(withdrawLimit);
		try {
			userExtraService.updateUserExtra(userExtra);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
			return gson.toJson(jsonResult);
		}
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/addNewUserAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String addNewUser(UserExpand userExpand) throws Exception {
		Gson gson = new Gson();
		if (userExpand == null) {
			return gson.toJson(new JsonResult("0", "-1", "没有找到任何要添加的数据"));
		}
		// 1.检查用户名
		String username = userExpand.getUsername();
		if (username == null || username.equals("") || !username.matches("^[a-zA-z]{1}[A-Za-z0-9_]{5,15}$")) {
			return gson.toJson(new JsonResult("0", "-1", "服务器消息: 用户名必须以字母开头并且长度在6-16位之间"));
		}
		// 判断用户名是否存在
		if (userService.usernameExist(username)) {
			return gson.toJson(new JsonResult("0", "-1", "用户名已经被注册"));
		}

		// 2.检查手机是否存在
		String phone = userExpand.getPhone();
		if (phone == null || phone.equals("") || !phone.matches("^0?(13|15|18|14|17)[0-9]{9}$")) {
			return gson.toJson(new JsonResult("0", "-1", "服务器消息: 请输入正确的手机号码"));
		}
		// 判断电话号码是否存在
		if (userService.phoneExist(phone)) {
			return gson.toJson(new JsonResult("0", "-1", "手机号已被注册"));
		}

		// 3.检查代理用户是否存在
		if (userExpand.getProxyUser() != null) {
			String keywords = userExpand.getProxyUser().getKeywords();
			if (keywords != null && keywords.length() > 0) {
				UserExpand proxyUser = userService.findUserByKeyWords(keywords);
				if (proxyUser == null) {
					return gson.toJson(new JsonResult("0", "-1", "代理商不存在"));
				}
				userExpand.setProxyUser(proxyUser);
			} else {
				userExpand.setProxyUser(null);
			}
		}

		// 4.密码判断
		if (userExpand.getPassword() == null || userExpand.getPassword().length() == 0)
			userExpand.setPassword(ConfigUtil.RESETPWD);

		// 5.插入数据库
		boolean b = userService.registUser(userExpand);
		if (b) {
			return gson.toJson(new JsonResult("0", "0", null, null, null));
		}
		return gson.toJson(new JsonResult("0", "-1", "添加用户失败：Unknown Reason", null, null));
	}

	@RequestMapping(value = "/updateUserAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateUser(UserExpand userExpand, String proxy) throws Exception {
		Gson gson = new Gson();
		if (userExpand == null) {
			return gson.toJson(new JsonResult("0", "-1", "找不到任何更新数据."));
		}

		// 1.检查用户名
		String username = userExpand.getUsername();
		// if (username == null || username.equals("") ||
		// !username.matches("^[a-zA-z]{1}[A-Za-z0-9_]{5,15}$")) {
		// return gson.toJson(new JsonResult("0", "-1",
		// "服务器消息:用户名必须以字母开头并且长度在6-16位之间"));
		// }
		// 判断用户名是否存在
		if (username != null) {
			if (!userService.usernameExist(username)) {
				return gson.toJson(new JsonResult("0", "-1", "用户名不存在，请勿恶意访问."));
			}
		}

		// 2.检查手机是否存在
		String phone = userExpand.getPhone();
		// if (phone == null || phone.equals("") ||
		// !phone.matches("^0?(13|15|18|14|17)[0-9]{9}$")) {
		// return gson.toJson(new JsonResult("0", "-1", "服务器消息: 请输入正确的手机号码"));
		// }
		// 判断电话号码是否存在

		if (phone != null) {
			if (!userService.phoneExist(phone)) {
				return gson.toJson(new JsonResult("0", "-1", "手机号不存在，请勿恶意访问."));
			}
		}

		Integer id = userExpand.getId();
		if (id == null && username == null && phone == null) {
			return gson.toJson(new JsonResult("0", "-1", "至少提供id、usernmae、phone其中一种作为更新依据"));
		}

		if (userExpand.getProxyUser() != null) {
			String keywords = userExpand.getProxyUser().getKeywords();
			if (keywords != null && keywords.length() > 0) {
				UserExpand proxyUser = userService.findUserByKeyWords(keywords);
				if (proxyUser == null) {
					return gson.toJson(new JsonResult("0", "-1", "输入的代理商帐号不存在，请检查输入的用户名或者手机号是否正确."));
				}
				if (!proxyUser.getRole().getRoleName().equals("代理商")) {
					return gson.toJson(new JsonResult("0", "-1", "输入的代理商帐号和角色不匹配，请检查输入的用户名或者手机号是否正确."));
				}
				userExpand.setProxyUser(proxyUser);
			} else {
				userExpand.setProxyUser(null);
			}
		}

		// 3.-1检查
		String province, city, area;
		province = userExpand.getProvince();
		city = userExpand.getCity();
		area = userExpand.getArea();
		if (userExpand.getRole() != null) {
			Integer roleId = userExpand.getRole().getId();
			if (roleId != null && roleId == -1)
				userExpand.getRole().setId(null);
		}
		if (province != null && province.equals("-1"))
			userExpand.setProvince(null);
		if (city != null && city.equals("-1"))
			userExpand.setCity(null);
		if (area != null && area.equals("-1"))
			userExpand.setArea(null);

		// 3.插入数据库
		boolean b = userService.updateUser(userExpand);
		if (b) {
			return gson.toJson(new JsonResult("0", "0", null, null, null));
		}
		return gson.toJson(new JsonResult("0", "-1", "更新用户失败：Unknown Reason", null, null));
	}

	@RequestMapping(value = "/deleteUserAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String deleteUser(UserExpand userExpand) throws Exception {
		Gson gson = new Gson();
		// 3.插入数据库
		boolean b = userService.deleteUser(userExpand);
		if (b) {
			return gson.toJson(new JsonResult("0", "0", null, null, null));
		}
		return gson.toJson(new JsonResult("0", "-1", "删除用户失败：Unknown Reason", null, null));
	}

	@RequestMapping(value = "/resetUserPwdAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String resetUserPwd(UserExpand userExpand) throws Exception {
		Gson gson = new Gson();
		userExpand.setPassword(ConfigUtil.RESETPWD);
		// 3.插入数据库
		boolean b = userService.resetUserPwd(userExpand);
		if (b) {
			return gson.toJson(new JsonResult("0", "0", null, null, null));
		}
		return gson.toJson(new JsonResult("0", "-1", "重置密码失败：Unknown Reason", null, null));
	}

	// Role Operation
	@RequestMapping(value = "/getRoleListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getTypeListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize, String searchKey,
			/* String orderStatusSelect, */ Integer typeSeqOrderBy, Integer orderTimeOrderBy) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<RoleExpand> roleExpands = null;
		Integer totalNum = 0;

		RoleExpandQueryVo roleExpandQueryVo = new RoleExpandQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		if (!StringUtils.isEmpty(searchKey)) {
			whereCondList.add(new QueryConditionItem("t_role.roleName", searchKey, QueryConditionOp.LIKE));
		}
		try {
			roleExpandQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			totalNum = userService.getRolesTotalNum(roleExpandQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> orderByMap = new HashMap<>();
		if (typeSeqOrderBy != null) {
			if (typeSeqOrderBy == 0) {
				orderByMap.put("t_role.seqNum", "desc");
			} else {
				orderByMap.put("t_role.seqNum", "asc");
			}
		}
		try {
			roleExpandQueryVo.setPagination(new Pagination(pageSize, pageNo, 0, orderByMap));
			roleExpands = userService.getRoles(roleExpandQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (totalNum == null)
			totalNum = 0;
		jsonResult.resultObj = new PageObj(totalNum, roleExpands);
		return gson.toJson(jsonResult);
	}

	// ============================== Old Version
	// ===================================
	@RequestMapping(value = "/proxyManagement", method = { RequestMethod.GET, RequestMethod.POST })
	public String proxyManagement(Model model) throws Exception {
		List<UserExpand> userExpands = userService.findAllProxyUsers();
		model.addAttribute("userList", userExpands);
		return "user/proxyManagement";
	}

	@RequestMapping(value = "/sellerManagement", method = { RequestMethod.GET, RequestMethod.POST })
	public String sellerManagement(Model model, HttpSession session) throws Exception {
		UserExpand sessionUser = (UserExpand) session.getAttribute("user");

		List<UserExpand> userExpands = null;
		if (sessionUser.getRole().getRoleName().equals("管理员")) {
			userExpands = userService.findAllSeller();
		} else {
			userExpands = userService.findAllSellersByProxyId(sessionUser.getId());
		}
		model.addAttribute("userList", userExpands);
		return "user/sellerManagement";
	}

	@RequestMapping(value = "/managerManagement", method = { RequestMethod.GET, RequestMethod.POST })
	public String managerManagement(Model model) throws Exception {
		List<UserExpand> userExpands = userService.findAllManagers();
		model.addAttribute("userList", userExpands);
		return "user/managerManagement";
	}

	@RequestMapping(value = "/userStatics", method = { RequestMethod.GET, RequestMethod.POST })
	public String userStatics() throws Exception {
		return "user/userStatics";
	}

}
