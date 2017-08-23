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
import com.sxsram.ssm.entity.UserExtra;
import com.sxsram.ssm.entity.UserExtraQueryVo;
import com.sxsram.ssm.service.UserExtraService;
import com.sxsram.ssm.util.JsonResult;
import com.sxsram.ssm.util.Pagination;
import com.sxsram.ssm.util.QueryCondition;
import com.sxsram.ssm.util.QueryConditionAbstractItem;
import com.sxsram.ssm.util.QueryConditionItem;
import com.sxsram.ssm.util.QueryConditionOp;
import com.sxsram.ssm.util.QueryConditionOrItems;
import com.sxsram.ssm.util.StringUtil;

@Controller()
@RequestMapping(value = "/inviter", method = { RequestMethod.GET, RequestMethod.POST })
public class InviterController {
	@Autowired
	private UserExtraService userExtraService;

	@RequestMapping(value = "/inviterList", method = { RequestMethod.GET, RequestMethod.POST })
	public String inviterList(Model model, HttpSession session) throws Exception {
		return "inviter/inviterList";
	}

	@RequestMapping(value = "/inviterNumList", method = { RequestMethod.GET, RequestMethod.POST })
	public String inviterNumList(Model model, HttpSession session) throws Exception {
		return "inviter/inviterNumList";
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

	@RequestMapping(value = "/getInviterListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getInviterListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize,
			String searchKey, String searchStartDate, String searchEndDate, String sendPushMsgFlag,
			String inviterRewardFlag, String beInviterRewardFlag, Integer addTimeOrderBy) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<UserExtra> userExtras = null;
		Integer totalNum = 0;

		UserExtraQueryVo offlineJournalBookQueryVo = new UserExtraQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		whereCondList.add(new QueryConditionItem("e.delFlag", "0", QueryConditionOp.EQ));
		if (!StringUtils.isEmpty(searchKey)) {
			QueryConditionOrItems queryConditionOrItems = new QueryConditionOrItems();
			queryConditionOrItems.getItems().add(new QueryConditionItem("u.phone", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("u.username", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems().add(new QueryConditionItem("i.phone", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("i.username", searchKey, QueryConditionOp.LIKE));
			whereCondList.add(queryConditionOrItems);
		}

		if (!StringUtil.isEmpty(searchStartDate)) {
			String arr[] = searchStartDate.split("/");
			String startDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("e.addTime", startDate.toString(), QueryConditionOp.GE));
		}

		if (!StringUtil.isEmpty(searchEndDate)) {
			String arr[] = searchEndDate.split("/");
			String endDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("e.addTime", endDate.toString(), QueryConditionOp.LE));
		}

		if (!StringUtil.isEmpty(sendPushMsgFlag) && !sendPushMsgFlag.equals("-2")) {
			whereCondList.add(new QueryConditionItem("e.sendPushMsgFlag", sendPushMsgFlag, QueryConditionOp.EQ));
		}
		if (!StringUtil.isEmpty(inviterRewardFlag) && !inviterRewardFlag.equals("-2")) {
			whereCondList.add(new QueryConditionItem("e.inviterRewardFlag", inviterRewardFlag, QueryConditionOp.EQ));
		}
		if (!StringUtil.isEmpty(beInviterRewardFlag) && !beInviterRewardFlag.equals("-2")) {
			whereCondList
					.add(new QueryConditionItem("e.beInviterRewardFlag", beInviterRewardFlag, QueryConditionOp.EQ));
		}

		try {
			offlineJournalBookQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			totalNum = userExtraService.findUserExtrasHasInviter(offlineJournalBookQueryVo).size();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> orderByMap = new HashMap<>();
		if (addTimeOrderBy != null) {
			if (addTimeOrderBy == 0) {
				orderByMap.put("e.addTime", "desc");
			} else {
				orderByMap.put("e.addTime", "asc");
			}
		}

		try {
			offlineJournalBookQueryVo.setPagination(new Pagination(pageSize, pageNo, 0, orderByMap));
			userExtras = userExtraService.findUserExtrasHasInviter(offlineJournalBookQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.resultCode = "-1";
			jsonResult.resultMsg = e.getMessage();
			return gson.toJson(jsonResult);
		}

		if (totalNum == null)
			totalNum = 0;
		jsonResult.resultObj = new PageObj(totalNum, userExtras);
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/getInviterNumListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getInviterNumListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize,
			String searchKey, String searchStartDate, String searchEndDate, String sendPushMsgFlag,
			String inviterRewardFlag, String beInviterRewardFlag, Integer inviterNumOrderBy) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<UserExtra> userExtras = null;
		Integer totalNum = 0;

		UserExtraQueryVo offlineJournalBookQueryVo = new UserExtraQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		whereCondList.add(new QueryConditionItem("e.delFlag", "0", QueryConditionOp.EQ));
		if (!StringUtils.isEmpty(searchKey)) {
			QueryConditionOrItems queryConditionOrItems = new QueryConditionOrItems();
			// queryConditionOrItems.getItems().add(new
			// QueryConditionItem("u.phone", searchKey, QueryConditionOp.LIKE));
			// queryConditionOrItems.getItems()
			// .add(new QueryConditionItem("u.username", searchKey,
			// QueryConditionOp.LIKE));
			queryConditionOrItems.getItems().add(new QueryConditionItem("i.phone", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("i.username", searchKey, QueryConditionOp.LIKE));
			whereCondList.add(queryConditionOrItems);
		}

		if (!StringUtil.isEmpty(searchStartDate)) {
			String arr[] = searchStartDate.split("/");
			String startDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("e.addTime", startDate.toString(), QueryConditionOp.GE));
		}

		if (!StringUtil.isEmpty(searchEndDate)) {
			String arr[] = searchEndDate.split("/");
			String endDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("e.addTime", endDate.toString(), QueryConditionOp.LE));
		}

		if (!StringUtil.isEmpty(sendPushMsgFlag) && !sendPushMsgFlag.equals("-2")) {
			whereCondList.add(new QueryConditionItem("e.sendPushMsgFlag", sendPushMsgFlag, QueryConditionOp.EQ));
		}
		if (!StringUtil.isEmpty(inviterRewardFlag) && !inviterRewardFlag.equals("-2")) {
			whereCondList.add(new QueryConditionItem("e.inviterRewardFlag", inviterRewardFlag, QueryConditionOp.EQ));
		}
		if (!StringUtil.isEmpty(beInviterRewardFlag) && !beInviterRewardFlag.equals("-2")) {
			whereCondList
					.add(new QueryConditionItem("e.beInviterRewardFlag", beInviterRewardFlag, QueryConditionOp.EQ));
		}

		try {
			offlineJournalBookQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			totalNum = userExtraService.findUserExtrasTotalNumHasInviter(offlineJournalBookQueryVo).size();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> orderByMap = new HashMap<>();
		if (inviterNumOrderBy != null) {
			if (inviterNumOrderBy == 0) {
				orderByMap.put("count(*)", "desc");
			} else {
				orderByMap.put("count(*)", "asc");
			}
		}

		try {
			offlineJournalBookQueryVo.setPagination(new Pagination(pageSize, pageNo, 0, orderByMap));
			userExtras = userExtraService.findUserExtrasTotalNumHasInviter(offlineJournalBookQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.resultCode = "-1";
			jsonResult.resultMsg = e.getMessage();
			return gson.toJson(jsonResult);
		}

		if (totalNum == null)
			totalNum = 0;
		jsonResult.resultObj = new PageObj(totalNum, userExtras);
		return gson.toJson(jsonResult);
	}
}
