package com.sxsram.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sxsram.ssm.entity.OperationExpand;
import com.sxsram.ssm.entity.OperationExpandQueryVo;
import com.sxsram.ssm.entity.PermissionExpand;
import com.sxsram.ssm.entity.PermissionExpandQueryVo;
import com.sxsram.ssm.entity.RoleExpand;
import com.sxsram.ssm.entity.RoleExpandQueryVo;
import com.sxsram.ssm.entity.UserExpand;
import com.sxsram.ssm.entity.UserExpandQueryVo;
import com.sxsram.ssm.service.PermService;
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
@RequestMapping(value = "/perm", method = { RequestMethod.GET, RequestMethod.POST })
public class PermController {
	@Autowired
	private UserService userService;
	@Autowired
	private PermService permService;

	@RequestMapping(value = "/userOverview", method = { RequestMethod.GET, RequestMethod.POST })
	public String userCenter(HttpSession session, Model model, String code, String state) throws Exception {
		return "perm/userOverview";
	}

	@RequestMapping(value = "/roleOverview", method = { RequestMethod.GET, RequestMethod.POST })
	public String roleOverview(HttpSession session, Model model, String code, String state) throws Exception {
		return "perm/roleOverview";
	}

	@RequestMapping(value = "/permOverview", method = { RequestMethod.GET, RequestMethod.POST })
	public String permOverview(HttpSession session, Model model, String code, String state) throws Exception {
		return "perm/permOverview";
	}

	@RequestMapping(value = "/opOverview", method = { RequestMethod.GET, RequestMethod.POST })
	public String opOverview(HttpSession session, Model model, String code, String state) throws Exception {
		return "perm/opOverview";
	}

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
			whereCondList.add(new QueryConditionItem("role.id", roleSelect, QueryConditionOp.EQ));
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
			} else {
				orderByMap.put("user.addTime", "asc");
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

	/**
	 * Role Opeartion
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param searchKey
	 * @param typeSeqOrderBy
	 * @param orderTimeOrderBy
	 * @return
	 */
	@RequestMapping(value = "/getRoleListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getRoleListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize, String searchKey,
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
			whereCondList.add(new QueryConditionItem("t_role.roleName", "平台", QueryConditionOp.NE));
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

	@RequestMapping(value = "/addNewRoleAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String addNewRole(RoleExpand roleExpand) throws Exception {
		Gson gson = new Gson();
		if (roleExpand == null) {
			return gson.toJson(new JsonResult("0", "-1", "没有找到任何要添加的数据"));
		}

		if (StringUtil.isEmpty(roleExpand.getRoleGrade()))
			roleExpand.setRoleGrade("1");
		try {
			permService.addNewRole(roleExpand);
		} catch (Exception e) {
			e.printStackTrace();
			return gson.toJson(new JsonResult("0", "-1", e.getMessage(), null, null));
		}
		return gson.toJson(new JsonResult("0", "0", null, null, null));
	}

	@RequestMapping(value = "/updateRoleAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateRole(RoleExpand roleExpand, String proxy) throws Exception {
		Gson gson = new Gson();
		if (roleExpand == null) {
			return gson.toJson(new JsonResult("0", "-1", "找不到任何更新数据."));
		}

		Integer id = roleExpand.getId();
		if (id == null) {
			return gson.toJson(new JsonResult("0", "-1", "无法找到id字段,请勿恶意访问."));
		}

		try {
			permService.updateRole(roleExpand);
		} catch (Exception e) {
			return gson.toJson(new JsonResult("0", "-1", e.getMessage(), null, null));
		}
		return gson.toJson(new JsonResult("0", "0", null, null, null));
	}

	@RequestMapping(value = "/deleteRoleAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String deleteRole(RoleExpand roleExpand) throws Exception {
		Gson gson = new Gson();
		try {
			// 3.插入数据库
			permService.deleteRole(roleExpand);
		} catch (Exception e) {
			return gson.toJson(new JsonResult("0", "-1", e.getMessage(), null, null));
		}
		return gson.toJson(new JsonResult("0", "0", null, null, null));
	}

	/**
	 * Perm Opeartion
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param searchKey
	 * @param typeSeqOrderBy
	 * @param orderTimeOrderBy
	 * @return
	 */
	@RequestMapping(value = "/getPermListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getTypeListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize, String searchKey,
			/* String orderStatusSelect, */ Integer typeSeqOrderBy, Integer orderTimeOrderBy) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<PermissionExpand> permExpands = null;
		Integer totalNum = 0;

		PermissionExpandQueryVo permExpandQueryVo = new PermissionExpandQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		if (!StringUtils.isEmpty(searchKey)) {
			whereCondList.add(new QueryConditionItem("t_permission.name", searchKey, QueryConditionOp.LIKE));
		}
		try {
			permExpandQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			totalNum = permService.getPermsTotalNum(permExpandQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> orderByMap = new HashMap<>();
		if (typeSeqOrderBy != null) {
			if (typeSeqOrderBy == 0) {
				orderByMap.put("t_perm.seqNum", "desc");
			} else {
				orderByMap.put("t_perm.seqNum", "asc");
			}
		}
		try {
			permExpandQueryVo.setPagination(new Pagination(pageSize, pageNo, 0, orderByMap));
			permExpands = permService.getPerms(permExpandQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (totalNum == null)
			totalNum = 0;
		jsonResult.resultObj = new PageObj(totalNum, permExpands);
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/addNewPermAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String addNewPerm(PermissionExpand permExpand) throws Exception {
		Gson gson = new Gson();
		if (permExpand == null) {
			return gson.toJson(new JsonResult("0", "-1", "没有找到任何要添加的数据"));
		}

		try {
			permService.addNewPerm(permExpand);
		} catch (Exception e) {
			e.printStackTrace();
			return gson.toJson(new JsonResult("0", "-1", e.getMessage(), null, null));
		}
		return gson.toJson(new JsonResult("0", "0", null, null, null));
	}

	@RequestMapping(value = "/updatePermAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updatePerm(PermissionExpand permExpand, String proxy) throws Exception {
		Gson gson = new Gson();
		if (permExpand == null) {
			return gson.toJson(new JsonResult("0", "-1", "找不到任何更新数据."));
		}

		Integer id = permExpand.getId();
		if (id == null) {
			return gson.toJson(new JsonResult("0", "-1", "无法找到id字段,请勿恶意访问."));
		}

		try {
			permService.updatePerm(permExpand);
		} catch (Exception e) {
			return gson.toJson(new JsonResult("0", "-1", e.getMessage(), null, null));
		}
		return gson.toJson(new JsonResult("0", "0", null, null, null));
	}

	@RequestMapping(value = "/deletePermAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String deletePerm(PermissionExpand permExpand) throws Exception {
		Gson gson = new Gson();
		try {
			// 3.插入数据库
			permService.deletePerm(permExpand);
		} catch (Exception e) {
			return gson.toJson(new JsonResult("0", "-1", e.getMessage(), null, null));
		}
		return gson.toJson(new JsonResult("0", "0", null, null, null));
	}

	/**
	 * Operation Opeartion
	 * 
	 * @param session
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param searchKey
	 * @param typeSeqOrderBy
	 * @param orderTimeOrderBy
	 * @return
	 */
	@RequestMapping(value = "/getOperListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getOperListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize, String searchKey,
			/* String orderStatusSelect, */ Integer typeSeqOrderBy, Integer orderTimeOrderBy) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<OperationExpand> operExpands = null;
		Integer totalNum = 0;

		OperationExpandQueryVo operExpandQueryVo = new OperationExpandQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		if (!StringUtils.isEmpty(searchKey)) {
			whereCondList.add(new QueryConditionItem("t_oper.name", searchKey, QueryConditionOp.LIKE));
		}
		try {
			operExpandQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			totalNum = permService.getOpersTotalNum(operExpandQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> orderByMap = new HashMap<>();
		if (typeSeqOrderBy != null) {
			if (typeSeqOrderBy == 0) {
				orderByMap.put("t_oper.seqNum", "desc");
			} else {
				orderByMap.put("t_oper.seqNum", "asc");
			}
		}
		try {
			operExpandQueryVo.setPagination(new Pagination(pageSize, pageNo, 0, orderByMap));
			operExpands = permService.getOpers(operExpandQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (totalNum == null)
			totalNum = 0;
		jsonResult.resultObj = new PageObj(totalNum, operExpands);
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/addNewOperAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String addNewOper(OperationExpand operExpand) throws Exception {
		Gson gson = new Gson();
		if (operExpand == null) {
			return gson.toJson(new JsonResult("0", "-1", "没有找到任何要添加的数据"));
		}

		try {
			permService.addNewOper(operExpand);
		} catch (Exception e) {
			e.printStackTrace();
			return gson.toJson(new JsonResult("0", "-1", e.getMessage(), null, null));
		}
		return gson.toJson(new JsonResult("0", "0", null, null, null));
	}

	@RequestMapping(value = "/updateOperAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateOper(OperationExpand operExpand) throws Exception {
		Gson gson = new Gson();
		if (operExpand == null) {
			return gson.toJson(new JsonResult("0", "-1", "找不到任何更新数据."));
		}

		Integer id = operExpand.getId();
		if (id == null) {
			return gson.toJson(new JsonResult("0", "-1", "无法找到id字段,请勿恶意访问."));
		}

		try {
			permService.updateOper(operExpand);
		} catch (Exception e) {
			return gson.toJson(new JsonResult("0", "-1", e.getMessage(), null, null));
		}
		return gson.toJson(new JsonResult("0", "0", null, null, null));
	}

	@RequestMapping(value = "/deleteOperAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String deleteOper(OperationExpand operExpand) throws Exception {
		Gson gson = new Gson();
		try {
			// 3.插入数据库
			permService.deleteOper(operExpand);
		} catch (Exception e) {
			return gson.toJson(new JsonResult("0", "-1", e.getMessage(), null, null));
		}
		return gson.toJson(new JsonResult("0", "0", null, null, null));
	}
}
