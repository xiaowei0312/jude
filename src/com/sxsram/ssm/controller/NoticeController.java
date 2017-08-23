package com.sxsram.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import com.sxsram.ssm.entity.Notice;
import com.sxsram.ssm.entity.NoticeQueryVo;
import com.sxsram.ssm.entity.NoticeType;
import com.sxsram.ssm.entity.NoticeTypeQueryVo;
import com.sxsram.ssm.entity.OnlineCommodity;
import com.sxsram.ssm.entity.ShoppingMallExpand;
import com.sxsram.ssm.entity.ShoppingMallExpandQueryVo;
import com.sxsram.ssm.entity.ShoppingMallType;
import com.sxsram.ssm.entity.ShoppingMallTypeQueryVo;
import com.sxsram.ssm.entity.UserExpand;
import com.sxsram.ssm.service.MallService;
import com.sxsram.ssm.service.NoticeService;
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
@RequestMapping(value = "/notice", method = { RequestMethod.GET, RequestMethod.POST })
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/typeManagement", method = { RequestMethod.GET, RequestMethod.POST })
	public String typeManagement() {
		return "notice/typeManagement";
	}

	@RequestMapping(value = "/statics", method = { RequestMethod.GET, RequestMethod.POST })
	public String mallStatics() throws Exception {
		return "notice/statics";
	}

	@RequestMapping(value = "/overview", method = { RequestMethod.GET, RequestMethod.POST })
	public String mallOverview(HttpSession session, Model model) throws Exception {
		return "notice/overview";
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

		List<NoticeType> noticeTypes = null;
		Integer totalNum = 0;

		NoticeTypeQueryVo noticeTypeQueryVo = new NoticeTypeQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		if (!StringUtils.isEmpty(searchKey)) {
			whereCondList.add(new QueryConditionItem("typeName", searchKey, QueryConditionOp.LIKE));
		}
		try {
			noticeTypeQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			totalNum = noticeService.getNoticeTypeListCount(noticeTypeQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> orderByMap = new HashMap<>();
		if (typeSeqOrderBy != null) {
			if (typeSeqOrderBy == 0) {
				orderByMap.put("sequence", "desc");
			} else {
				orderByMap.put("sequence", "asc");
			}
		}
		try {
			noticeTypeQueryVo.setPagination(new Pagination(pageSize, pageNo, 0, orderByMap));
			noticeTypes = noticeService.getMultiNoticeTypes(noticeTypeQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (totalNum == null)
			totalNum = 0;
		jsonResult.resultObj = new PageObj(totalNum, noticeTypes);
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/updateTypeAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateTypeAjax(HttpSession session, Model model, Integer id, Integer typeSeq, String typeName) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		NoticeType type = new NoticeType();
		type.setId(id);
		if (!StringUtils.isEmpty(typeSeq))
			type.setSequence(typeSeq);
		if (!StringUtil.isEmpty(typeName))
			type.setTypeName(typeName);
		type.setParentId(null);

		try {
			noticeService.updateNoticeType(type);
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

		NoticeType type = new NoticeType();
		type.setId(id);

		Integer totalNum = 0;
		switch (reqType) {
		case 0: // 获取类别下的商品数量
			NoticeQueryVo noticeQueryVo = new NoticeQueryVo();
			List<QueryConditionAbstractItem> items = new ArrayList<QueryConditionAbstractItem>();
			items.add(new QueryConditionItem("n.noticeTypeId", id + "", QueryConditionOp.EQ));
			try {
				noticeQueryVo.setQueryCondition(new QueryCondition(items));
				totalNum = noticeService.getNoticeListCount(noticeQueryVo);
				jsonResult.resultObj = totalNum;
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult.logicCode = "-1";
				jsonResult.resultMsg = e.getMessage();
			}
			break;
		case 1: // 级联删除
			try {
				noticeService.deleteTypeCascadeNotice(type.getId());
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult.logicCode = "-1";
				jsonResult.resultMsg = e.getMessage();
			}
			break;
		case 2: // 级联删除
			try {
				noticeService.deleteNoticeType(type.getId());
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

		NoticeType type = new NoticeType();
		if (!StringUtils.isEmpty(typeSeq))
			type.setSequence(typeSeq);
		if (!StringUtil.isEmpty(typeName))
			type.setTypeName(typeName);
		type.setParentId(null);

		try {
			noticeService.addNewNoticeType(type);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
		}
		return gson.toJson(jsonResult);
	}

	/**
	 * NoticeList
	 * 
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

	@RequestMapping(value = "/getNoticeListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getMallListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize, String searchKey,
			String searchStartDate, String searchEndDate, String typeSelect, Integer timeOrderBy) throws Exception {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<Notice> notices = null;
		Integer totalNum = 0;

		NoticeQueryVo noticeQueryVo = new NoticeQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		if (!StringUtils.isEmpty(searchKey)) {
			QueryConditionOrItems queryConditionOrItems = new QueryConditionOrItems();
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("n.noticeTitle", searchKey, QueryConditionOp.LIKE));
			whereCondList.add(queryConditionOrItems);
		}

		if (!StringUtil.isEmpty(searchStartDate)) {
			String arr[] = searchStartDate.split("/");
			// Date startDate = new Date(Integer.valueOf(arr[2]),
			// Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
			String startDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("n.noticeAddTime", startDate.toString(), QueryConditionOp.GE));
		}

		if (!StringUtil.isEmpty(searchEndDate)) {
			String arr[] = searchEndDate.split("/");
			// Date endDate = new Date(Integer.valueOf(arr[2]),
			// Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
			String endDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("n.noticeAddTime", endDate.toString(), QueryConditionOp.LE));
		}

		if (!StringUtil.isEmpty(typeSelect) && !typeSelect.equals("-2")) {
			whereCondList.add(new QueryConditionItem("n.noticeTypeId", typeSelect, QueryConditionOp.EQ));
		}

		// UserExpand sessionUser = (UserExpand) session.getAttribute("user");
		// if (sessionUser == null) {
		// jsonResult.resultCode = "-3";
		// jsonResult.resultMsg = "登录超时，请重新登录";
		// return gson.toJson(jsonResult);
		// }
		// if (sessionUser.getRole().getRoleName().equals("代理商")) {
		// whereCondList
		// .add(new QueryConditionItem("mall.proxy_user_id", sessionUser.getId()
		// + "", QueryConditionOp.EQ));
		// }

		whereCondList.add(new QueryConditionItem("n.noticeFlag", 0 + "", QueryConditionOp.EQ));

		try {
			noticeQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			totalNum = noticeService.getNoticeListCount(noticeQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> orderByMap = new HashMap<>();
		if (timeOrderBy != null) {
			if (timeOrderBy == 0) {
				orderByMap.put("n.noticeAddTime", "desc");
			} else {
				orderByMap.put("n.noticeAddTime", "asc");
			}
		}
		try {
			noticeQueryVo.setPagination(new Pagination(pageSize, pageNo, 0, orderByMap));
			notices = noticeService.getMultiNotices(noticeQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.resultCode = "-1";
			jsonResult.resultMsg = e.getMessage();
			return gson.toJson(jsonResult);
		}

		if (totalNum == null)
			totalNum = 0;
		jsonResult.resultObj = new PageObj(totalNum, notices);
		// session.setAttribute("offlineOrderListQueryVo",
		// offlineJournalBookQueryVo);
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/noticeUpdateAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String mallUpdate(Notice notice) throws Exception {
		Gson gson = new Gson();

		// 检查是否有id字段
		if (notice == null || notice.getId() == null) {
			return gson.toJson(new JsonResult("0", "-1", "没有有效ID字段，请勿恶意访问."));
		}

		// 3.插入数据库
		try {
			noticeService.updateNotice(notice);
		} catch (Exception e) {
			return gson.toJson(new JsonResult("0", "-1", e.getMessage(), null, null));
		}
		return gson.toJson(new JsonResult("0", "0", null, null, null));
	}

	@RequestMapping(value = "/noticeAddAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String addNewMall(HttpSession session, Notice notice, Model model) throws Exception {
		Gson gson = new Gson();
		// 检查必须字段
		if (notice == null) {
			return gson.toJson(new JsonResult("0", "-1", "找不到需要的任何字段，请勿恶意访问."));
		}

		if (notice.getNoticeTitle() == null || notice.getNoticeTitle().length() == 0) {
			return gson.toJson(new JsonResult("0", "-1", "商铺名称不能为空."));
		}

		try {
			notice.setNoticeAddTime(new Date());
			notice.setNoticeFlag(0);
			noticeService.addNewNotice(notice);
		} catch (Exception e) {
			return gson.toJson(new JsonResult("0", "-1", e.getMessage(), null, null));
		}
		return gson.toJson(new JsonResult("0", "0", null, null, null));
	}

	@RequestMapping(value = "/noticeDeleteAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String deleteMall(HttpSession session, Notice notice, Model model) throws Exception {
		Gson gson = new Gson();
		// 检查是
		if (notice == null || notice.getId() == null) {
			return gson.toJson(new JsonResult("0", "-1", "找不到需要的任何字段，请勿恶意访问."));
		}
		notice.setNoticeFlag(1);
		noticeService.updateNotice(notice);
		return gson.toJson(new JsonResult("0", "0", null, null, null));
	}
	
	@RequestMapping(value = "/noticeDetailUpdateAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateCommodityDetailAjax(String id,String editor1) {
		Gson gson = new Gson();
		JsonResult jsonResult = new JsonResult("0","0");
		try {
			Notice onlineCommodity = new Notice();
			if(StringUtil.isEmpty(id)){
				jsonResult.logicCode = "-1";
				jsonResult.resultMsg = "Can't find id";
				return gson.toJson(jsonResult);
			}
			onlineCommodity.setId(Integer.valueOf(id));
			onlineCommodity.setNoticeFileName(editor1.trim());
			noticeService.updateNotice(onlineCommodity);
		} catch (Exception e) {
			//e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
		}
		return gson.toJson(jsonResult);
	}
}
