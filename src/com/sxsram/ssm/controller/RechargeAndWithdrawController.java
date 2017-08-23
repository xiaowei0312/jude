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
import com.sxsram.ssm.entity.RechargeAndWithDrawRecordExpand;
import com.sxsram.ssm.entity.RechargeAndWithDrawRecordQueryVo;
import com.sxsram.ssm.service.OrderService;
import com.sxsram.ssm.service.RechargeAndWithdrawService;
import com.sxsram.ssm.util.JsonResult;
import com.sxsram.ssm.util.Pagination;
import com.sxsram.ssm.util.QueryCondition;
import com.sxsram.ssm.util.QueryConditionAbstractItem;
import com.sxsram.ssm.util.QueryConditionItem;
import com.sxsram.ssm.util.QueryConditionOp;
import com.sxsram.ssm.util.QueryConditionOrItems;
import com.sxsram.ssm.util.StringUtil;

@Controller()
@RequestMapping(value = "/rwRecord", method = { RequestMethod.GET, RequestMethod.POST })
public class RechargeAndWithdrawController {

	@Autowired
	private RechargeAndWithdrawService rwService;

	@RequestMapping(value = "/getRwRecordList", method = { RequestMethod.GET, RequestMethod.POST })
	public String getOrderList() {
		return "rwRecord/rwRecordList";
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

	@RequestMapping(value = "/getRwRecordListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getOrderListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize, String searchKey,
			String searchStartDate, String searchEndDate, String typeSelect, String statusSelect,
			Integer addTimeOrderBy) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<RechargeAndWithDrawRecordExpand> rwRecords = null;
		Integer totalNum = 0;

		RechargeAndWithDrawRecordQueryVo rwRecordQueryVo = new RechargeAndWithDrawRecordQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		if (!StringUtils.isEmpty(searchKey)) {
			QueryConditionOrItems queryConditionOrItems = new QueryConditionOrItems();
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("u.phone", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("u.username", searchKey, QueryConditionOp.LIKE));
			whereCondList.add(queryConditionOrItems);
		}

		if (!StringUtil.isEmpty(searchStartDate)) {
			String arr[] = searchStartDate.split("/");
			String startDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("r.operateTime", startDate.toString(), QueryConditionOp.GE));
		}

		if (!StringUtil.isEmpty(searchEndDate)) {
			String arr[] = searchEndDate.split("/");
			String endDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("r.operateTime", endDate.toString(), QueryConditionOp.LE));
		}

		if (!StringUtil.isEmpty(statusSelect) && !statusSelect.equals("-2")) {
			whereCondList.add(new QueryConditionItem("r.operateState", statusSelect, QueryConditionOp.EQ));
		}
		
		if (!StringUtil.isEmpty(typeSelect) && !typeSelect.equals("-2")) {
			whereCondList.add(new QueryConditionItem("r.operateType", typeSelect, QueryConditionOp.EQ));
		}

		try {
			rwRecordQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			totalNum = rwService.getRechargeAndWithdrawRecords(rwRecordQueryVo).size();
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
			return gson.toJson(jsonResult);
		}

		Map<String, String> orderByMap = new HashMap<>();
		if (addTimeOrderBy != null) {
			if (addTimeOrderBy == 0) {
				orderByMap.put("operateTime", "desc");
			} else {
				orderByMap.put("operateTime", "asc");
			}
		}
		try {
			rwRecordQueryVo.setPagination(new Pagination(pageSize, pageNo, 0, orderByMap));
			rwRecords = rwService.getRechargeAndWithdrawRecords(rwRecordQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
			return gson.toJson(jsonResult);
		}

		if (totalNum == null)
			totalNum = 0;
		jsonResult.resultObj = new PageObj(totalNum, rwRecords);
		return gson.toJson(jsonResult);
	}
}
