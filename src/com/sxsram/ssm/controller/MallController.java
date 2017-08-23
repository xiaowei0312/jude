package com.sxsram.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.sxsram.ssm.entity.ShoppingMallExpand;
import com.sxsram.ssm.entity.ShoppingMallExpandQueryVo;
import com.sxsram.ssm.entity.ShoppingMallType;
import com.sxsram.ssm.entity.ShoppingMallTypeQueryVo;
import com.sxsram.ssm.entity.UserExpand;
import com.sxsram.ssm.service.MallService;
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
@RequestMapping(value = "/mall", method = { RequestMethod.GET, RequestMethod.POST })
public class MallController {
	@Autowired
	private MallService mallService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/typeManagement", method = { RequestMethod.GET, RequestMethod.POST })
	public String typeManagement() {
		return "mall/typeManagement";
	}
	
	@RequestMapping(value = "/mallStatics", method = { RequestMethod.GET, RequestMethod.POST })
	public String mallStatics() throws Exception {
		return "mall/mallStatics";
	}

	@RequestMapping(value = "/mallOverview", method = { RequestMethod.GET, RequestMethod.POST })
	public String mallOverview(HttpSession session, Model model) throws Exception {
		return "mall/mallOverview";
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
	
	/**
	 * MallType Operation
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
	@RequestMapping(value = "/getTypeListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getTypeListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize, String searchKey,
			/* String orderStatusSelect, */ Integer typeSeqOrderBy, Integer orderTimeOrderBy) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<ShoppingMallType> shoppingMallTypes = null;
		Integer totalNum = 0;

		ShoppingMallTypeQueryVo shoppingMallTypeQueryVo = new ShoppingMallTypeQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		if (!StringUtils.isEmpty(searchKey)) {
			whereCondList
					.add(new QueryConditionItem("t_shopping_mall_type.typeName", searchKey, QueryConditionOp.LIKE));
		}
		try {
			shoppingMallTypeQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			totalNum = mallService.getMallTypesTotalNum(shoppingMallTypeQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> orderByMap = new HashMap<>();
		if (typeSeqOrderBy != null) {
			if (typeSeqOrderBy == 0) {
				orderByMap.put("t_shopping_mall_type.sequence", "desc");
			} else {
				orderByMap.put("t_shopping_mall_type.sequence", "asc");
			}
		}
		try {
			shoppingMallTypeQueryVo.setPagination(new Pagination(pageSize, pageNo, 0, orderByMap));
			shoppingMallTypes = mallService.getMallTypes(shoppingMallTypeQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (totalNum == null)
			totalNum = 0;
		jsonResult.resultObj = new PageObj(totalNum, shoppingMallTypes);
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/updateTypeAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateTypeAjax(HttpSession session, Model model, Integer id, Integer typeSeq, String typeName) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		ShoppingMallType type = new ShoppingMallType();
		type.setId(id);
		if (!StringUtils.isEmpty(typeSeq))
			type.setSequence(typeSeq);
		if (!StringUtil.isEmpty(typeName))
			type.setTypeName(typeName);
		type.setParentId(null);

		try {
			mallService.updateType(type);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
		}
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/deleteTypeAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String deleteTypeAjax(HttpSession session, Model model, Integer id, Integer reqType) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		if (id == null || reqType == null || reqType < 0 || reqType > 2) {
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = "找不到reqtype，非法操作!";
			return gson.toJson(jsonResult);
		}

		ShoppingMallType type = new ShoppingMallType();
		type.setId(id);

		Integer totalNum = 0;
		switch (reqType) {
		case 0: // 获取类别下的商品数量
			ShoppingMallExpandQueryVo shoppingMallExpandQueryVo = new ShoppingMallExpandQueryVo();
			List<QueryConditionAbstractItem> items = new ArrayList<QueryConditionAbstractItem>();
			items.add(new QueryConditionItem("t_online_commodity.commodityTypeId", id + "", QueryConditionOp.EQ));
			try {
				shoppingMallExpandQueryVo.setQueryCondition(new QueryCondition(items));
				totalNum = mallService.getMallListCount(shoppingMallExpandQueryVo);
				jsonResult.resultObj = totalNum;
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult.logicCode = "-1";
				jsonResult.resultMsg = e.getMessage();
			}
			break;
		case 1: // 级联删除
			try {
				mallService.deleteTypeCascadeCommodity(type);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult.logicCode = "-1";
				jsonResult.resultMsg = e.getMessage();
			}
			break;
		case 2: // 级联删除
			try {
				mallService.deleteType(type);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult.logicCode = "-1";
				jsonResult.resultMsg = e.getMessage();
			}
			break;
		}
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/addTypeAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String addTypeAjax(HttpSession session, Model model, Integer typeSeq, String typeName) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		ShoppingMallType type = new ShoppingMallType();
		if (!StringUtils.isEmpty(typeSeq))
			type.setSequence(typeSeq);
		if (!StringUtil.isEmpty(typeName))
			type.setTypeName(typeName);
		type.setParentId(null);

		try {
			mallService.addType(type);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
		}
		return gson.toJson(jsonResult);
	}
	
	
	/**
	 * MallList
	 * @param session
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param searchKey
	 * @param searchStartDate
	 * @param searchEndDate
	 * @param mallTypeSelect
	 * @param orderTimeOrderBy
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/getMallListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getMallListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize, String searchKey,
			String searchStartDate, String searchEndDate, String typeSelect, Integer timeOrderBy)
			throws Exception {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<ShoppingMallExpand> shoppingMallExpands = null;
		Integer totalNum = 0;

		ShoppingMallExpandQueryVo shoppingMallExpandQueryVo = new ShoppingMallExpandQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		if (!StringUtils.isEmpty(searchKey)) {
			QueryConditionOrItems queryConditionOrItems = new QueryConditionOrItems();
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("mall.mallName", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("mall.mallPhone", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("mall.mallLinkMan", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("mall.mallAddress", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("user.username", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("user.phone", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("proxy.username", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("proxy.phone", searchKey, QueryConditionOp.LIKE));

			whereCondList.add(queryConditionOrItems);
		}

		if (!StringUtil.isEmpty(searchStartDate)) {
			String arr[] = searchStartDate.split("/");
			// Date startDate = new Date(Integer.valueOf(arr[2]),
			// Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
			String startDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("mall.createTime", startDate.toString(), QueryConditionOp.GE));
		}

		if (!StringUtil.isEmpty(searchEndDate)) {
			String arr[] = searchEndDate.split("/");
			// Date endDate = new Date(Integer.valueOf(arr[2]),
			// Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
			String endDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("mall.createTime", endDate.toString(), QueryConditionOp.LE));
		}

		if (!StringUtil.isEmpty(typeSelect) && !typeSelect.equals("-2")) {
			whereCondList.add(new QueryConditionItem("type.id", typeSelect, QueryConditionOp.EQ));
		}

		UserExpand sessionUser = (UserExpand) session.getAttribute("user");
		if (sessionUser == null) {
			jsonResult.resultCode = "-3";
			jsonResult.resultMsg = "登录超时，请重新登录";
			return gson.toJson(jsonResult);
		}
		if (sessionUser.getRole().getRoleName().equals("代理商")) {
			whereCondList
					.add(new QueryConditionItem("mall.proxy_user_id", sessionUser.getId() + "", QueryConditionOp.EQ));
		}

		whereCondList.add(new QueryConditionItem("mall.locked", 0 + "", QueryConditionOp.EQ));

		try {
			shoppingMallExpandQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			totalNum = mallService.getMallListCount(shoppingMallExpandQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> orderByMap = new HashMap<>();
		if (timeOrderBy != null) {
			if (timeOrderBy == 0) {
				orderByMap.put("mall.createTime", "desc");
			} else {
				orderByMap.put("mall.createTime", "asc");
			}
		}
		try {
			shoppingMallExpandQueryVo.setPagination(new Pagination(pageSize, pageNo, 0, orderByMap));
			shoppingMallExpands = mallService.getMallList(shoppingMallExpandQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.resultCode = "-1"; 
			jsonResult.resultMsg = e.getMessage();
			return gson.toJson(jsonResult);
		}

		if (totalNum == null)
			totalNum = 0;
		jsonResult.resultObj = new PageObj(totalNum, shoppingMallExpands);
		// session.setAttribute("offlineOrderListQueryVo",
		// offlineJournalBookQueryVo);
		return gson.toJson(jsonResult);
	}

	private String uploadImg(MultipartFile img) throws IllegalStateException, IOException {
		String orginalFilename = img.getOriginalFilename();
		String imgPath = ConfigUtil.mallImgPath;
		String filename = UUID.randomUUID() + orginalFilename.substring(orginalFilename.lastIndexOf('.'));
		File newFile = new File(imgPath + filename);
		img.transferTo(newFile);
		return filename;
	}

	@RequestMapping(value = "/mallImgUploadAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String mallImgUpload(HttpSession session, Model model, Integer mallId, MultipartFile img0,
			MultipartFile img1, MultipartFile img2, MultipartFile img3, MultipartFile img4, MultipartFile img5,
			String mallDesc) throws Exception {
		Gson gson = new Gson();

		if (mallId == null) {
			return gson.toJson(new JsonResult("0", "-1", "MallId is null"));
		}

		String newImg = null, newImg1 = null, newImg2 = null, newImg3 = null, newImg4 = null, newImg5 = null;

		if (img0 != null) {
			String originalFilename = img0.getOriginalFilename();
			if (originalFilename == null || originalFilename.length() == 0) {
				newImg = null;
			} else {
				if (!originalFilename.endsWith(".jpg") && !originalFilename.endsWith(".jpeg")
						&& !originalFilename.endsWith(".png")) {
					return gson.toJson(new JsonResult("0", "-1", "不支持的文件类型，仅支持.jpg(.jpeg)和.png"));
				}
				newImg = uploadImg(img0);
			}
		}
		if (img1 != null) {
			String originalFilename = img1.getOriginalFilename();
			if (originalFilename == null || originalFilename.length() == 0) {
				newImg1 = null;
			} else {
				if (!originalFilename.endsWith(".jpg") && !originalFilename.endsWith(".jpeg")
						&& !originalFilename.endsWith(".png")) {
					return gson.toJson(new JsonResult("0", "-1", "不支持的文件类型，仅支持.jpg(.jpeg)和.png"));
				}
				newImg1 = uploadImg(img1);
			}
		}

		if (img2 != null) {
			String originalFilename = img2.getOriginalFilename();
			if (originalFilename == null || originalFilename.length() == 0) {
				newImg2 = null;
			} else {
				if (!originalFilename.endsWith(".jpg") && !originalFilename.endsWith(".jpeg")
						&& !originalFilename.endsWith(".png")) {
					return gson.toJson(new JsonResult("0", "-1", "不支持的文件类型，仅支持.jpg(.jpeg)和.png"));
				}
				newImg2 = uploadImg(img2);
			}
		}

		if (img3 != null) {
			String originalFilename = img3.getOriginalFilename();
			if (originalFilename == null || originalFilename.length() == 0) {
				newImg3 = null;
			} else {
				if (!originalFilename.endsWith(".jpg") && !originalFilename.endsWith(".jpeg")
						&& !originalFilename.endsWith(".png")) {
					return gson.toJson(new JsonResult("0", "-1", "不支持的文件类型，仅支持.jpg(.jpeg)和.png"));
				}
				newImg3 = uploadImg(img3);
			}
		}

		if (img4 != null) {
			String originalFilename = img4.getOriginalFilename();
			if (originalFilename == null || originalFilename.length() == 0) {
				newImg4 = null;
			} else {
				if (!originalFilename.endsWith(".jpg") && !originalFilename.endsWith(".jpeg")
						&& !originalFilename.endsWith(".png")) {
					return gson.toJson(new JsonResult("0", "-1", "不支持的文件类型，仅支持.jpg(.jpeg)和.png"));
				}
				newImg4 = uploadImg(img4);
			}
		}

		if (img5 != null) {
			String originalFilename = img5.getOriginalFilename();
			if (originalFilename == null || originalFilename.length() == 0) {
				newImg5 = null;
			} else {
				if (!originalFilename.endsWith(".jpg") && !originalFilename.endsWith(".jpeg")
						&& !originalFilename.endsWith(".png")) {
					return gson.toJson(new JsonResult("0", "-1", "不支持的文件类型，仅支持.jpg(.jpeg)和.png"));
				}
				newImg5 = uploadImg(img5);
			}
		}
		ShoppingMallExpand mallExpand = new ShoppingMallExpand();
		mallExpand.setId(mallId);
		mallExpand.setMallMainPicUrl(newImg);
		mallExpand.setMallPicUrl1(newImg1);
		mallExpand.setMallPicUrl2(newImg2);
		mallExpand.setMallPicUrl3(newImg3);
		mallExpand.setMallPicUrl4(newImg4);
		mallExpand.setMallPicUrl5(newImg5);
		mallExpand.setMallDesc(mallDesc);

		if (mallService.updateMall(mallExpand) == false) {
			return gson.toJson(new JsonResult("0", "-1", "更新图片失败"));
		}

		return gson.toJson(new JsonResult("0", "0"));
	}

	@RequestMapping(value = "/mallUpdateAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String mallUpdate(ShoppingMallExpand mallExpand) throws Exception {
		Gson gson = new Gson();

		// 检查是否有id字段
		if (mallExpand == null || mallExpand.getId() == null) {
			return gson.toJson(new JsonResult("0", "-1", "没有有效ID字段，请勿恶意访问."));
		}

		// 检查是否有该会员 可与 商铺进行管理
		if (mallExpand.getUser() != null) {
			String seller = mallExpand.getUser().getKeywords();
			// 判断关联商家是否存在
			UserExpand userExpand = userService.findUserByKeyWords(seller);
			if (userExpand == null) {
				return gson.toJson(new JsonResult("0", "-1", "输入的关联商家帐号不存在，请检查输入的用户名或者手机号是否正确."));
			}
			if (!userExpand.getRole().getRoleName().equals("商家")) {
				return gson.toJson(new JsonResult("0", "-1", "输入的商家帐号目前并不具备商家身份，请先修改该会员角色为商家."));
			}
			mallExpand.setUser(userExpand);
		}

		// 检查是否有该代理商
		if (mallExpand.getProxyUser() != null) {
			String proxy = mallExpand.getProxyUser().getKeywords();
			// 判断代理商是否存在
			UserExpand userExpand = userService.findUserByKeyWords(proxy);
			if (userExpand == null) {
				return gson.toJson(new JsonResult("0", "-1", "输入的代理商帐号不存在，请检查输入的用户名或者手机号是否正确."));
			}
			if (!userExpand.getRole().getRoleName().equals("代理商")) {
				return gson.toJson(new JsonResult("0", "-1", "输入的代理商帐号和角色不匹配，请检查输入的用户名或者手机号是否正确."));
			}
			mallExpand.setProxyUser(userExpand);
		}

		// 3.插入数据库
		boolean b = mallService.updateMall(mallExpand);
		if (b) {
			return gson.toJson(new JsonResult("0", "0", null, null, null));
		}
		return gson.toJson(new JsonResult("0", "-1", "更新用户失败：Unknown Reason", null, null));
	}

	@RequestMapping(value = "/mallAddAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String addNewMall(HttpSession session, ShoppingMallExpand mallExpand, Model model) throws Exception {
		Gson gson = new Gson();
		// 检查必须字段
		if (mallExpand == null) {
			return gson.toJson(new JsonResult("0", "-1", "找不到需要的任何字段，请勿恶意访问."));
		}

		if (mallExpand.getMallName() == null || mallExpand.getMallName().length() == 0) {
			return gson.toJson(new JsonResult("0", "-1", "商铺名称不能为空."));
		}

		if (mallExpand.getMallLinkMan() == null || mallExpand.getMallLinkMan().length() == 0) {
			return gson.toJson(new JsonResult("0", "-1", "商铺联系人不能为空."));
		}

		if (mallExpand.getMallPhone() == null || mallExpand.getMallPhone().length() == 0) {
			return gson.toJson(new JsonResult("0", "-1", "商铺联系电话不能为空."));
		}
		if (mallExpand.getMallAddress() == null || mallExpand.getMallAddress().length() == 0) {
			return gson.toJson(new JsonResult("0", "-1", "商铺地址不能为空."));
		}
		// if (mallExpand.getMallDesc() == null ||
		// mallExpand.getMallDesc().length() == 0) {
		// return gson.toJson(new JsonResult("0", "-1", "商铺描述信息不能为空."));
		// }

		// 检查是否有该会员 可与 商铺进行管理
		if (mallExpand.getUser() != null) {
			String seller = mallExpand.getUser().getKeywords();
			// 判断关联商家是否存在
			UserExpand userExpand = userService.findUserByKeyWords(seller);
			if (userExpand == null) {
				return gson.toJson(new JsonResult("0", "-1", "输入的关联商家帐号不存在，请检查输入的用户名或者手机号是否正确."));
			}
			if (!userExpand.getRole().getRoleName().equals("商家")) {
				return gson.toJson(new JsonResult("0", "-1", "输入的商家帐号目前并不具备商家身份，请先修改该会员角色为商家."));
			}
			mallExpand.setUser(userExpand);
		}

		// 检查是否有该代理商
		if (mallExpand.getProxyUser() != null) {
			String proxy = mallExpand.getProxyUser().getKeywords();
			// 判断代理商是否存在
			UserExpand userExpand = userService.findUserByKeyWords(proxy);
			if (userExpand == null) {
				return gson.toJson(new JsonResult("0", "-1", "输入的代理商帐号不存在，请检查输入的用户名或者手机号是否正确."));
			}
			if (!userExpand.getRole().getRoleName().equals("代理商")) {
				return gson.toJson(new JsonResult("0", "-1", "输入的代理商帐号和角色不匹配，请检查输入的用户名或者手机号是否正确."));
			}
			mallExpand.setProxyUser(userExpand);
		}

		boolean b = mallService.addNewMall(mallExpand);
		if (b)
			return gson.toJson(new JsonResult("0", "0", null, mallExpand, null));
		return gson.toJson(new JsonResult("0", "-1", "更新用户失败：Unknown Reason", null, null));
	}

	@RequestMapping(value = "/mallDeleteAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String deleteMall(HttpSession session, ShoppingMallExpand mallExpand, Model model) throws Exception {
		Gson gson = new Gson();
		// 检查是
		if (mallExpand == null || mallExpand.getId() == null) {
			return gson.toJson(new JsonResult("0", "-1", "找不到需要的任何字段，请勿恶意访问."));
		}
		mallExpand.setLocked(1);
		mallService.updateMall(mallExpand);
		return gson.toJson(new JsonResult("0", "0", null, null, null));
	}
}
