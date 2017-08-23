package com.sxsram.ssm.controller;

import java.sql.Date;
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
import com.sxsram.ssm.entity.OnlineJournalBook;
import com.sxsram.ssm.entity.OnlineJournalBookQueryVo;
import com.sxsram.ssm.service.OrderService;
import com.sxsram.ssm.util.JsonResult;
import com.sxsram.ssm.util.Pagination;
import com.sxsram.ssm.util.QueryCondition;
import com.sxsram.ssm.util.QueryConditionAbstractItem;
import com.sxsram.ssm.util.QueryConditionItem;
import com.sxsram.ssm.util.QueryConditionOp;
import com.sxsram.ssm.util.QueryConditionOrItems;
import com.sxsram.ssm.util.StringUtil;

@Controller()
@RequestMapping(value = "/onlineOrder", method = { RequestMethod.GET, RequestMethod.POST })
public class OnlineOrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/getPendingOrderList", method = { RequestMethod.GET, RequestMethod.POST })
	public String getPendingOrderList() {
		return "onlineOrder/orderList_pending";
	}

	@RequestMapping(value = "/getUndoOrderList", method = { RequestMethod.GET, RequestMethod.POST })
	public String getUndoOrderList() {
		return "onlineOrder/orderList_undo";
	}

	@RequestMapping(value = "/getOrderList", method = { RequestMethod.GET, RequestMethod.POST })
	public String getOrderList() {
		return "onlineOrder/orderList";
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

	@RequestMapping(value = "/getPendingOrderListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getPendingOrderListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		// Gson gson = new Gson();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<OnlineJournalBook> onlineJournalBooks = null;
		Integer totalNum = 0;

		OnlineJournalBookQueryVo onlineJournalBookQueryVo = new OnlineJournalBookQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		whereCondList.add(new QueryConditionItem("jour.status", 0 + "", QueryConditionOp.EQ));

		try {
			onlineJournalBookQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			//totalNum = orderService.getOnlineOrdersTotalNum(onlineJournalBookQueryVo);
			totalNum = orderService.getOnlineOrders(onlineJournalBookQueryVo).size();
			onlineJournalBookQueryVo
					.setPagination(new Pagination(pageSize, pageNo, null, "{\"journalTime\":\"desc\"}"));
			onlineJournalBooks = orderService.getOnlineOrders(onlineJournalBookQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (totalNum == null)
			totalNum = 0;
		// model.addAttribute("totalNum", totalNum);
		// model.addAttribute("shoppingMallList", shoppingMallExpands);
		jsonResult.resultObj = new PageObj(totalNum, onlineJournalBooks);
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/getUndoOrderListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getUndoOrderListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<OnlineJournalBook> onlineJournalBooks = null;
		Integer totalNum = 0;

		OnlineJournalBookQueryVo onlineJournalBookQueryVo = new OnlineJournalBookQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		whereCondList.add(new QueryConditionItem("jour.status", -1 + "", QueryConditionOp.EQ));

		try {
			onlineJournalBookQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			//totalNum = orderService.getOnlineOrdersTotalNum(onlineJournalBookQueryVo);
			totalNum = orderService.getOnlineOrders(onlineJournalBookQueryVo).size();
			onlineJournalBookQueryVo
					.setPagination(new Pagination(pageSize, pageNo, null, "{\"journalTime\":\"desc\"}"));
			onlineJournalBooks = orderService.getOnlineOrders(onlineJournalBookQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (totalNum == null)
			totalNum = 0;
		// model.addAttribute("totalNum", totalNum);
		// model.addAttribute("shoppingMallList", shoppingMallExpands);
		jsonResult.resultObj = new PageObj(totalNum, onlineJournalBooks);
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/getOrderListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getOrderListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize, String searchKey,
			String searchStartDate, String searchEndDate, String orderStatusSelect, Integer orderAmountOrderBy,
			Integer orderTimeOrderBy) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<OnlineJournalBook> onlineJournalBooks = null;
		Integer totalNum = 0;

		OnlineJournalBookQueryVo onlineJournalBookQueryVo = new OnlineJournalBookQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		// whereCondList.add(new QueryConditionItem("status",
		// orderStatus.ordinal() + "", QueryConditionOp.EQ));
		if (!StringUtils.isEmpty(searchKey)) {
			QueryConditionOrItems queryConditionOrItems = new QueryConditionOrItems();
			queryConditionOrItems.getItems().add(new QueryConditionItem("orderNo", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems().add(new QueryConditionItem("addr.contacts", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems().add(new QueryConditionItem("addr.phone", searchKey, QueryConditionOp.LIKE));
			whereCondList.add(queryConditionOrItems);
		}

		if (!StringUtil.isEmpty(searchStartDate)) {
			String arr[] = searchStartDate.split("/");
			// Date startDate = new Date(Integer.valueOf(arr[2]),
			// Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
			String startDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("journalTime", startDate.toString(), QueryConditionOp.GE));
		}

		if (!StringUtil.isEmpty(searchEndDate)) {
			String arr[] = searchEndDate.split("/");
			// Date endDate = new Date(Integer.valueOf(arr[2]),
			// Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
			String endDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("journalTime", endDate.toString(), QueryConditionOp.LE));
		}

		if (!StringUtil.isEmpty(orderStatusSelect) && !orderStatusSelect.equals("-2")) {
			whereCondList.add(new QueryConditionItem("jour.status", orderStatusSelect, QueryConditionOp.EQ));
		}

		try {
			onlineJournalBookQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			//totalNum = orderService.getOnlineOrdersTotalNum(onlineJournalBookQueryVo);
			totalNum = orderService.getOnlineOrders(onlineJournalBookQueryVo).size();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> orderByMap = new HashMap<>();
		if (orderAmountOrderBy != null) {
			if (orderAmountOrderBy == 0) {
				orderByMap.put("totalAmount", "desc");
			} else {
				orderByMap.put("totalAmount", "asc");
			}
		}

		if (orderTimeOrderBy != null) {
			if (orderTimeOrderBy == 0) {
				orderByMap.put("journalTime", "desc");
			} else {
				orderByMap.put("journalTime", "asc");
			}
		}
		try {
			onlineJournalBookQueryVo.setPagination(new Pagination(pageSize, pageNo, 0, orderByMap));
			onlineJournalBooks = orderService.getOnlineOrders(onlineJournalBookQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (totalNum == null)
			totalNum = 0;
		jsonResult.resultObj = new PageObj(totalNum, onlineJournalBooks);
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/updateOrderStatusAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateOrderStatus(HttpSession session, Model model, Integer status, Integer orderId) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		OnlineJournalBook onlineJournalBook = new OnlineJournalBook();
		onlineJournalBook.setId(orderId);
		onlineJournalBook.setStatus(status);

		try {
			orderService.updateOrder(onlineJournalBook);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
		}
		return gson.toJson(jsonResult);
	}

}
