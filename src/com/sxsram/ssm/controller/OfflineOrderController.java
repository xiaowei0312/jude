package com.sxsram.ssm.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sxsram.ssm.controller.OnlineOrderController.PageObj;
import com.sxsram.ssm.entity.JournalBookExpand;
import com.sxsram.ssm.entity.JournalBookExpandQueryVo;
import com.sxsram.ssm.entity.OnlineJournalBook;
import com.sxsram.ssm.entity.OnlineJournalBookQueryVo;
import com.sxsram.ssm.entity.UserExpand;
import com.sxsram.ssm.service.SubmitOrderService;
import com.sxsram.ssm.util.JsonResult;
import com.sxsram.ssm.util.Pagination;
import com.sxsram.ssm.util.QueryCondition;
import com.sxsram.ssm.util.QueryConditionAbstractItem;
import com.sxsram.ssm.util.QueryConditionItem;
import com.sxsram.ssm.util.QueryConditionOp;
import com.sxsram.ssm.util.QueryConditionOrItems;
import com.sxsram.ssm.util.StringUtil;

@Controller()
@RequestMapping(value = "/offlineOrder", method = { RequestMethod.GET, RequestMethod.POST })
public class OfflineOrderController {
	@Autowired
	private SubmitOrderService submitOrderService;

	@RequestMapping(value = "/offlineOrderList", method = { RequestMethod.GET, RequestMethod.POST })
	public String submitOrderOverview(Model model, HttpSession session) throws Exception {
		return "offlineOrder/overview";
	}

	@RequestMapping(value = "/offlineOrderVerifyList", method = { RequestMethod.GET, RequestMethod.POST })
	public String offlineOrderVerifyList(Model model, HttpSession session) throws Exception {
		UserExpand sessionUser = (UserExpand) session.getAttribute("user");

		List<JournalBookExpand> journalBookExpands = null;
		if (sessionUser.getRole().getRoleName().equals("管理员")) {
			journalBookExpands = submitOrderService.findAllUnVerifyJournalBooks();
		} else if (sessionUser.getRole().getRoleName().equals("代理商")) {
			journalBookExpands = submitOrderService.findAllUnVerifyJournalBooksByProxyId(sessionUser.getId());
		}
		model.addAttribute("journalBookList", journalBookExpands);
		return "offlineOrder/verify";
	}

	@RequestMapping(value = "/offlineOrderStatics", method = { RequestMethod.GET, RequestMethod.POST })
	public String submitOrderStatics() throws Exception {
		return "offlineOrder/statics";
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

	@RequestMapping(value = "/getOfflineOrderListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String submitOrderListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize,
			String searchKey, String searchStartDate, String searchEndDate, String compareOp, String compareAmount,
			String orderStatusSelect, Integer orderAmountOrderBy, Integer orderTimeOrderBy) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<JournalBookExpand> offlineJournalBooks = null;
		Integer totalNum = 0;

		JournalBookExpandQueryVo offlineJournalBookQueryVo = new JournalBookExpandQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		if (!StringUtils.isEmpty(searchKey)) {
			QueryConditionOrItems queryConditionOrItems = new QueryConditionOrItems();
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("business.phone", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("business.username", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("client.phone", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("client.username", searchKey, QueryConditionOp.LIKE));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("jb.commodityName", searchKey, QueryConditionOp.LIKE));
			whereCondList.add(queryConditionOrItems);
		}

		if (!StringUtil.isEmpty(searchStartDate)) {
			String arr[] = searchStartDate.split("/");
			// Date startDate = new Date(Integer.valueOf(arr[2]),
			// Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
			String startDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("jb.journalTime", startDate.toString(), QueryConditionOp.GE));
		}

		if (!StringUtil.isEmpty(searchEndDate)) {
			String arr[] = searchEndDate.split("/");
			// Date endDate = new Date(Integer.valueOf(arr[2]),
			// Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
			String endDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(new QueryConditionItem("jb.journalTime", endDate.toString(), QueryConditionOp.LE));
		}

		if (!StringUtil.isEmpty(compareAmount)) {
			if (StringUtil.isEmpty(compareOp))
				compareOp = "0";
			QueryConditionOp conditionOp = null;
			switch (Integer.valueOf(compareOp)) {
			case 0:
				conditionOp = QueryConditionOp.GE;
				break;
			case 1:
				conditionOp = QueryConditionOp.LE;
				break;
			case 2:
				conditionOp = QueryConditionOp.EQ;
				break;
			}
			whereCondList.add(new QueryConditionItem("jb.amount", compareAmount, conditionOp));
		}

		if (!StringUtil.isEmpty(orderStatusSelect) && !orderStatusSelect.equals("-2")) {
			whereCondList.add(new QueryConditionItem("jb.flag", orderStatusSelect, QueryConditionOp.EQ));
		}

		UserExpand sessionUser = (UserExpand) session.getAttribute("user");
		if (sessionUser.getRole().getRoleName().equals("代理商")) {
			whereCondList.add(
					new QueryConditionItem("business.proxy_user_id", sessionUser.getId() + "", QueryConditionOp.EQ));
		}

		try {
			offlineJournalBookQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			totalNum = submitOrderService.getOfflineOrdersTotalNum(offlineJournalBookQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> orderByMap = new HashMap<>();
		if (orderAmountOrderBy != null) {
			if (orderAmountOrderBy == 0) {
				orderByMap.put("amount", "desc");
			} else {
				orderByMap.put("amount", "asc");
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
			offlineJournalBookQueryVo.setPagination(new Pagination(pageSize, pageNo, 0, orderByMap));
			offlineJournalBooks = submitOrderService.getOfflineOrders(offlineJournalBookQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.resultCode = "-1";
			jsonResult.resultMsg = e.getMessage();
			return gson.toJson(jsonResult);
		}

		if (totalNum == null)
			totalNum = 0;
		jsonResult.resultObj = new PageObj(totalNum, offlineJournalBooks);
		session.setAttribute("offlineOrderListQueryVo", offlineJournalBookQueryVo);
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/submitOrderExportExcel", method = { RequestMethod.GET, RequestMethod.POST })
	public void submitOrderExportExcel(HttpSession session,HttpServletRequest request, HttpServletResponse response,String searchKey, String searchStartDate, String searchEndDate, String compareOp, String compareAmount,
			String orderStatusSelect, Integer orderAmountOrderBy, Integer orderTimeOrderBy) {

		List<JournalBookExpand> offlineJournalBooks = null;
		JournalBookExpandQueryVo journalBookExpandQueryVo = (JournalBookExpandQueryVo)session.getAttribute("offlineOrderListQueryVo");
		if(journalBookExpandQueryVo == null)
			return;
		journalBookExpandQueryVo.getPagination().setNumPerPage(null);
		try {
			offlineJournalBooks = submitOrderService.getOfflineOrders(journalBookExpandQueryVo);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		//HttpSession session = request.getSession();
		session.setAttribute("state", null);
		// 生成提示信息，
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			// 进行转码，使其支持中文文件名
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			codedFileName = java.net.URLEncoder.encode("报单记录_" + dateFormat.format(new Date()), "UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
			// response.addHeader("Content-Disposition", "attachment;
			// filename=" + codedFileName + ".xls");
			// 产生工作簿对象
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 产生工作表对象
			HSSFSheet sheet = workbook.createSheet();

			// 生成数据
			//UserExpand sessionUser = (UserExpand) session.getAttribute("user");
			//List<JournalBookExpand> journalBookExpands = getJournalBookExpandListByUser(sessionUser);
			for (int i = -1; i < offlineJournalBooks.size(); i++) {
				HSSFRow row = sheet.createRow((int) (i+1));// 创建一行
				HSSFCell cell1 = row.createCell((short) 0);// 创建一列
				HSSFCell cell2 = row.createCell((short) 1);// 创建一列
				HSSFCell cell3 = row.createCell((short) 2);// 创建一列
				HSSFCell cell4 = row.createCell((short) 3);// 创建一列
				HSSFCell cell5 = row.createCell((short) 4);// 创建一列
				HSSFCell cell6 = row.createCell((short) 5);// 创建一列
				HSSFCell cell7 = row.createCell((short) 6);// 创建一列
				HSSFCell cell8 = row.createCell((short) 7);// 创建一列
				HSSFCell cell9 = row.createCell((short) 8);// 创建一列
				cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell8.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell9.setCellType(HSSFCell.CELL_TYPE_STRING);

				if (i == -1) {// 表头
					cell1.setCellValue("商家信息");
					cell2.setCellValue("买家信息");
					cell3.setCellValue("商品名称");
					cell4.setCellValue("金额");
					cell5.setCellValue("优惠率");
					cell6.setCellValue("客户积分");
					cell7.setCellValue("商户积分");
					cell8.setCellValue("报单时间");
					cell9.setCellValue("状态");
				} else {
					JournalBookExpand journalBookExpand = offlineJournalBooks.get(i);
					cell1.setCellValue(journalBookExpand.getBusiness().getUsername());
					cell2.setCellValue(journalBookExpand.getClient().getUsername());
					cell3.setCellValue(journalBookExpand.getCommodityName());
					cell4.setCellValue(journalBookExpand.getAmount());
					cell5.setCellValue(journalBookExpand.getPremiumRates());
					cell6.setCellValue(journalBookExpand.getGiftJf());
					cell7.setCellValue(journalBookExpand.getRewardJf());
					dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					cell8.setCellValue(dateFormat.format(journalBookExpand.getJournalTime()));
					switch (journalBookExpand.getFlag()) {
					case 0:
						cell9.setCellValue("待审核");
						break;
					case 1:
						cell9.setCellValue("已同意");
						break;
					case 2:
						cell9.setCellValue("已奖励");
						break;
					case 3:
						cell9.setCellValue("已拒绝");
						break;
					}
				}
			}
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {
			}
			session.setAttribute("state", "open");
		}
		System.out.println("文件生成...");
	}

	@RequestMapping(value = "/submitOrderVerifySubmitAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String submitOrderVerifySubmit(Model model, JournalBookExpand journalBookExpand) throws Exception {
		Gson gson = new Gson();
		boolean b = submitOrderService.updateJournalBookFlag(journalBookExpand);
		if (b) {
			return gson.toJson(new JsonResult("0", "0", null, null, null));
		}
		return gson.toJson(new JsonResult("0", "-1", "审核失败：unknown reason", null, null));
	}
}
