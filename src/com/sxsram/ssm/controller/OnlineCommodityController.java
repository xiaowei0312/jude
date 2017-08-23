package com.sxsram.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
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
import com.sxsram.ssm.entity.OnLineCommodityQueryVo;
import com.sxsram.ssm.entity.OnLineCommodityTypeQueryVo;
import com.sxsram.ssm.entity.OnlineCommodity;
import com.sxsram.ssm.entity.OnlineCommodityModel;
import com.sxsram.ssm.entity.OnlineCommodityType;
import com.sxsram.ssm.service.CommodityService;
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
@RequestMapping(value = "/onlineCommodity", method = { RequestMethod.GET, RequestMethod.POST })
public class OnlineCommodityController {
	@Autowired
	private CommodityService commodityService;

	@RequestMapping(value = "/typeManagement", method = { RequestMethod.GET, RequestMethod.POST })
	public String typeManagement() {
		return "onlineCommodity/typeManagement";
	}
	
	@RequestMapping(value = "/commodityManagement", method = { RequestMethod.GET, RequestMethod.POST })
	public String commodityManagement() {
		return "onlineCommodity/commodityManagement";
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

	@RequestMapping(value = "/getTypeListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getTypeListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize, String searchKey,
			/* String orderStatusSelect, */ Integer typeSeqOrderBy, Integer orderTimeOrderBy) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<OnlineCommodityType> onlineCommodityTypes = null;
		Integer totalNum = 0;

		OnLineCommodityTypeQueryVo onLineCommodityTypeQueryVo = new OnLineCommodityTypeQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		if (!StringUtils.isEmpty(searchKey)) {
			whereCondList
					.add(new QueryConditionItem("t_online_commodity_type.typeName", searchKey, QueryConditionOp.LIKE));
		}
		try {
			onLineCommodityTypeQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			totalNum = commodityService.getOnlineCommodityTypesTotalNum(onLineCommodityTypeQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> orderByMap = new HashMap<>();
		if (typeSeqOrderBy != null) {
			if (typeSeqOrderBy == 0) {
				orderByMap.put("t_online_commodity_type.sequence", "desc");
			} else {
				orderByMap.put("t_online_commodity_type.sequence", "asc");
			}
		}
		try {
			onLineCommodityTypeQueryVo.setPagination(new Pagination(pageSize, pageNo, 0, orderByMap));
			onlineCommodityTypes = commodityService.getOnlineTypes(onLineCommodityTypeQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (totalNum == null)
			totalNum = 0;
		jsonResult.resultObj = new PageObj(totalNum, onlineCommodityTypes);
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/updateTypeAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateTypeAjax(HttpSession session, Model model, Integer id, Integer typeSeq, String typeName) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		OnlineCommodityType type = new OnlineCommodityType();
		type.setId(id);
		if (!StringUtils.isEmpty(typeSeq))
			type.setSequence(typeSeq);
		if (!StringUtil.isEmpty(typeName))
			type.setTypeName(typeName);
		type.setParentId(null);

		try {
			commodityService.updateType(type);
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

		OnlineCommodityType type = new OnlineCommodityType();
		type.setId(id);

		Integer totalNum = 0;
		switch (reqType) {
		case 0: // 获取类别下的商品数量
			OnLineCommodityQueryVo onLineCommodityQueryVo = new OnLineCommodityQueryVo();
			List<QueryConditionAbstractItem> items = new ArrayList<QueryConditionAbstractItem>();
			items.add(new QueryConditionItem("t_online_commodity.commodityTypeId", id + "", QueryConditionOp.EQ));
			try {
				onLineCommodityQueryVo.setQueryCondition(new QueryCondition(items));
				totalNum = commodityService.getOnlineCommoditiesTotalNum(onLineCommodityQueryVo);
				jsonResult.resultObj = totalNum;
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult.logicCode = "-1";
				jsonResult.resultMsg = e.getMessage();
			}
			break;
		case 1: // 级联删除
			try {
				commodityService.deleteTypeCascadeCommodity(type);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult.logicCode = "-1";
				jsonResult.resultMsg = e.getMessage();
			}
			break;
		case 2: // 级联删除
			try {
				commodityService.deleteType(type);
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

		OnlineCommodityType type = new OnlineCommodityType();
		if (!StringUtils.isEmpty(typeSeq))
			type.setSequence(typeSeq);
		if (!StringUtil.isEmpty(typeName))
			type.setTypeName(typeName);
		type.setParentId(null);

		try {
			commodityService.addType(type);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
		}
		return gson.toJson(jsonResult);
	}

	// ============================================ 商品
	// =======================================================

	@RequestMapping(value = "/getCommodityListAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getCommodityListAjax(HttpSession session, Model model, Integer pageNo, Integer pageSize,
			String searchKey, String searchStartDate, String searchEndDate, String statusSelect, String typeSelect,
			Integer timeOrderBy) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<OnlineCommodity> onlineCommodities = null;
		Integer totalNum = 0;

		OnLineCommodityQueryVo onLineCommodityQueryVo = new OnLineCommodityQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		if (!StringUtils.isEmpty(searchKey)) {
			QueryConditionOrItems queryConditionOrItems = new QueryConditionOrItems();
			queryConditionOrItems.getItems().add(new QueryConditionItem("id", searchKey, QueryConditionOp.EQ));
			queryConditionOrItems.getItems()
					.add(new QueryConditionItem("commodityName", searchKey, QueryConditionOp.LIKE));
			whereCondList.add(queryConditionOrItems);
		}

		if (!StringUtil.isEmpty(searchStartDate)) {
			String arr[] = searchStartDate.split("/");
			// Date startDate = new Date(Integer.valueOf(arr[2]),
			// Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
			String startDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList.add(
					new QueryConditionItem("onLineCommodityPutawayTime", startDate.toString(), QueryConditionOp.GE));
		}

		if (!StringUtil.isEmpty(searchEndDate)) {
			String arr[] = searchEndDate.split("/");
			// Date endDate = new Date(Integer.valueOf(arr[2]),
			// Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
			String endDate = arr[2] + "-" + arr[0] + "-" + arr[1];
			whereCondList
					.add(new QueryConditionItem("onLineCommoditySoldoutTime", endDate.toString(), QueryConditionOp.LE));
		}

		if (!StringUtil.isEmpty(statusSelect) && !statusSelect.equals("-2")) {
			whereCondList.add(new QueryConditionItem("onlineCommodityFlag", statusSelect, QueryConditionOp.EQ));
		}

		if (!StringUtil.isEmpty(typeSelect) && !typeSelect.equals("-2")) {
			whereCondList.add(new QueryConditionItem("commodityTypeId", typeSelect, QueryConditionOp.EQ));
		}

		try {
			onLineCommodityQueryVo.setQueryCondition(new QueryCondition(whereCondList));
			totalNum = commodityService.getOnlineCommoditiesTotalNum(onLineCommodityQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
			return gson.toJson(jsonResult);
		}

		Map<String, String> orderByMap = new HashMap<>();
		// if (orderAmountOrderBy != null) {
		// if (orderAmountOrderBy == 0) {
		// orderByMap.put("totalAmount", "desc");
		// } else {
		// orderByMap.put("totalAmount", "asc");
		// }
		// }
		//
		if (timeOrderBy != null) {
			if (timeOrderBy == 0) {
				orderByMap.put("onLineCommodityPutawayTime", "desc");
			} else {
				orderByMap.put("onLineCommodityPutawayTime", "asc");
			}
		}
		try {
			onLineCommodityQueryVo.setPagination(new Pagination(pageSize, pageNo, 0, orderByMap));
			onlineCommodities = commodityService.getOnlineCommodities(onLineCommodityQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
			return gson.toJson(jsonResult);
		}

		if (totalNum == null)
			totalNum = 0;
		jsonResult.resultObj = new PageObj(totalNum, onlineCommodities);
		return gson.toJson(jsonResult);
	}

	private String uploadImg(MultipartFile img) throws IllegalStateException, IOException {
		String orginalFilename = img.getOriginalFilename();
		String imgPath = ConfigUtil.onlineCommodImgs;
		String filename = UUID.randomUUID() + orginalFilename.substring(orginalFilename.lastIndexOf('.'));
		File newFile = new File(imgPath + filename);
		img.transferTo(newFile);
		return filename;
	}

	@RequestMapping(value = "/updateCommodityAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateCommodityAjax(HttpSession session, Model model, Integer id, String commodityName,
			String commoditySequence, String typeId, String onLineCommodityFlag, MultipartFile img) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		if (id == null) {
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = "找不到id，非法操作!";
			return gson.toJson(jsonResult);
		}

		OnlineCommodity onlineCommodity = new OnlineCommodity();
		onlineCommodity.setId(id);
		if (!StringUtils.isEmpty(commodityName))
			onlineCommodity.setCommodityName(commodityName);
		if (!StringUtil.isEmpty(commoditySequence))
			onlineCommodity.setCommoditySequence(Integer.valueOf(commoditySequence));
		if (!StringUtil.isEmpty(typeId))
			onlineCommodity.setCommodityTypeId(Integer.valueOf(typeId));
		if (!StringUtil.isEmpty(onLineCommodityFlag)) {
			onlineCommodity.setOnlineCommodityFlag(Integer.valueOf(onLineCommodityFlag));
			if (onLineCommodityFlag.equals("0") || onLineCommodityFlag.equals("2")) // 上架
				onlineCommodity.setOnLineCommodityPutawayTime(new java.util.Date());
			if (onlineCommodity.equals("1")) // 下架
				onlineCommodity.setOnLineCommoditySoldoutTime(new java.util.Date());
		}
		String newImg = "";
		if (img != null) {
			String originalFilename = img.getOriginalFilename();
			if (originalFilename == null || originalFilename.length() == 0) {
				newImg = null;
			} else {
				if (!originalFilename.endsWith(".jpg") && !originalFilename.endsWith(".jpeg")
						&& !originalFilename.endsWith(".png")) {
					jsonResult.logicCode = "-1";
					jsonResult.resultMsg = "不支持的文件类型，仅支持.jpg(.jpeg)和.png";
					return gson.toJson(jsonResult);
				}
				try {
					newImg = uploadImg(img);
				} catch (Exception e) {
					e.printStackTrace();
					jsonResult.logicCode = "-1";
					jsonResult.resultMsg = e.getMessage();
					return gson.toJson(jsonResult);
				}
			}
		}
		if (newImg != null) {
			onlineCommodity.setCommodityMainPic(newImg);
		}

		try {
			commodityService.updateCommodity(onlineCommodity);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
		}
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/deleteCommodityAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String deleteCommodityAjax(HttpSession session, Model model, Integer id) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		if (id == null) {
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = "找不到id，非法操作!";
			return gson.toJson(jsonResult);
		}

		OnlineCommodity commodity = new OnlineCommodity();
		commodity.setId(id);
		commodity.setOnlineCommodityFlag(2);
		try {
			commodityService.updateCommodity(commodity);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
		}
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/addCommodityAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String addCommodityAjax(HttpSession session, Model model, String commodityName, String commoditySequence,
			String typeId, MultipartFile img) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		OnlineCommodity onlineCommodity = new OnlineCommodity();
		if (!StringUtils.isEmpty(commodityName))
			onlineCommodity.setCommodityName(commodityName);
		if (!StringUtil.isEmpty(commoditySequence))
			onlineCommodity.setCommoditySequence(Integer.valueOf(commoditySequence));
		if (!StringUtil.isEmpty(typeId))
			onlineCommodity.setCommodityTypeId(Integer.valueOf(typeId));
		onlineCommodity.setOnlineCommodityFlag(0);
		onlineCommodity.setOnLineCommodityPutawayTime(new Date());

		String newImg = "";
		if (img != null) {
			String originalFilename = img.getOriginalFilename();
			if (originalFilename == null || originalFilename.length() == 0) {
				newImg = null;
			} else {
				if (!originalFilename.endsWith(".jpg") && !originalFilename.endsWith(".jpeg")
						&& !originalFilename.endsWith(".png")) {
					jsonResult.logicCode = "-1";
					jsonResult.resultMsg = "不支持的文件类型，仅支持.jpg(.jpeg)和.png";
					return gson.toJson(jsonResult);
				}
				try {
					newImg = uploadImg(img);
				} catch (Exception e) {
					e.printStackTrace();
					jsonResult.logicCode = "-1";
					jsonResult.resultMsg = e.getMessage();
					return gson.toJson(jsonResult);
				}
			}
		}
		if (newImg != null) {
			onlineCommodity.setCommodityMainPic(newImg);
		}
		try {
			commodityService.addNewCommodity(onlineCommodity);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
		}
		return gson.toJson(jsonResult);
	}

	// ============================== CommodityModel Operation
	// ======================

	@RequestMapping(value = "/updateModelAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateModelAjax(HttpSession session, Integer id, String commodityModel, String price,
			String commodityRepertory, String commodityFlag, String isDefaultModel, MultipartFile img0,
			MultipartFile img1, MultipartFile img2, MultipartFile img3, MultipartFile img4) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<MultipartFile> imgs = new ArrayList<>();
		if (img0 != null)
			imgs.add(img0);
		if (img1 != null)
			imgs.add(img1);
		if (img2 != null)
			imgs.add(img2);
		if (img3 != null)
			imgs.add(img3);
		if (img4 != null)
			imgs.add(img4);

		if (id == null) {
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = "Can't find modelId,Invalid operation.";
			return gson.toJson(jsonResult);
		}

		OnlineCommodityModel onlineCommodityModel = new OnlineCommodityModel();
		onlineCommodityModel.setId(id);

		if (!StringUtils.isEmpty(commodityModel))
			onlineCommodityModel.setCommodityModel(commodityModel);
		if (!StringUtil.isEmpty(price))
			onlineCommodityModel.setCommodityPrice(Double.valueOf(price));
		if (!StringUtil.isEmpty(commodityRepertory))
			onlineCommodityModel.setCommodityRepertory(Integer.valueOf(commodityRepertory));
		if (!StringUtil.isEmpty(isDefaultModel))
			onlineCommodityModel.setIsDefaultModel(Integer.valueOf(isDefaultModel));
		if (!StringUtil.isEmpty(commodityFlag)) {
			if (commodityFlag.equals("0")) {
				onlineCommodityModel.setCommodityPutawayTime(new Date());
			}
			if (commodityFlag.equals("1")) {
				onlineCommodityModel.setCommoditySoldoutTime(new Date());
			}
			onlineCommodityModel.setCommodityFlag(Integer.valueOf(commodityFlag));
		}
		List<String> newImgs = new ArrayList<String>();
		if (imgs != null) {
			for (MultipartFile img : imgs) {
				String newImg = null;
				if (img != null) {
					String originalFilename = img.getOriginalFilename();
					if (originalFilename == null || originalFilename.length() == 0) {
						newImg = null;
					} else {
						if (!originalFilename.endsWith(".jpg") && !originalFilename.endsWith(".jpeg")
								&& !originalFilename.endsWith(".png")) {
							jsonResult.logicCode = "-1";
							jsonResult.resultMsg = "不支持的文件类型，仅支持.jpg(.jpeg)和.png";
							return gson.toJson(jsonResult);
						}
						try {
							newImg = uploadImg(img);
						} catch (Exception e) {
							e.printStackTrace();
							jsonResult.logicCode = "-1";
							jsonResult.resultMsg = e.getMessage();
							return gson.toJson(jsonResult);
						}
					}
				}
				newImgs.add(newImg);
			}
		}
		if (newImgs.size() > 0) {
			onlineCommodityModel.setCommoditySmallPic1(newImgs.get(0));
			onlineCommodityModel.setCommoditySmallPic2(newImgs.get(1));
			onlineCommodityModel.setCommoditySmallPic3(newImgs.get(2));
			onlineCommodityModel.setCommoditySmallPic4(newImgs.get(3));
			onlineCommodityModel.setCommoditySmallPic5(newImgs.get(4));
		}
		try {
			commodityService.updateCommodityModel(onlineCommodityModel);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
		}
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/deleteModelAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String deleteModelAjax(HttpSession session, Integer id) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		if (id == null) {
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = "找不到id，非法操作!";
			return gson.toJson(jsonResult);
		}

		try {
			commodityService.deleteCommodityModel(id);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
		}
		return gson.toJson(jsonResult);
	}

	@RequestMapping(value = "/addModelAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String addModelAjax(HttpSession session, Integer commodityId, String commodityModel, String price,
			String commodityRepertory, String isDefaultModel, MultipartFile img0, MultipartFile img1,
			MultipartFile img2, MultipartFile img3, MultipartFile img4) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonResult jsonResult = new JsonResult("0", "0");

		List<MultipartFile> imgs = new ArrayList<>();
		if (img0 != null)
			imgs.add(img0);
		if (img1 != null)
			imgs.add(img1);
		if (img2 != null)
			imgs.add(img2);
		if (img3 != null)
			imgs.add(img3);
		if (img4 != null)
			imgs.add(img4);

		if (commodityId == null) {
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = "Can't find commodityId,Invalid operation.";
			return gson.toJson(jsonResult);
		}

		OnlineCommodityModel onlineCommodityModel = new OnlineCommodityModel();
		onlineCommodityModel.setCommodityId(commodityId);

		if (!StringUtils.isEmpty(commodityModel))
			onlineCommodityModel.setCommodityModel(commodityModel);
		if (!StringUtil.isEmpty(price))
			onlineCommodityModel.setCommodityPrice(Double.valueOf(price));
		if (!StringUtil.isEmpty(commodityRepertory))
			onlineCommodityModel.setCommodityRepertory(Integer.valueOf(commodityRepertory));
		if (!StringUtil.isEmpty(isDefaultModel))
			onlineCommodityModel.setIsDefaultModel(Integer.valueOf(isDefaultModel));
		onlineCommodityModel.setCommodityFlag(0);
		onlineCommodityModel.setCommodityPutawayTime(new Date());

		List<String> newImgs = new ArrayList<String>();
		if (imgs != null) {
			for (MultipartFile img : imgs) {
				String newImg = null;
				if (img != null) {
					String originalFilename = img.getOriginalFilename();
					if (originalFilename == null || originalFilename.length() == 0) {
						newImg = null;
					} else {
						if (!originalFilename.endsWith(".jpg") && !originalFilename.endsWith(".jpeg")
								&& !originalFilename.endsWith(".png")) {
							jsonResult.logicCode = "-1";
							jsonResult.resultMsg = "不支持的文件类型，仅支持.jpg(.jpeg)和.png";
							return gson.toJson(jsonResult);
						}
						try {
							newImg = uploadImg(img);
						} catch (Exception e) {
							e.printStackTrace();
							jsonResult.logicCode = "-1";
							jsonResult.resultMsg = e.getMessage();
							return gson.toJson(jsonResult);
						}
					}
				}
				newImgs.add(newImg);
			}
		}
		if (newImgs.size() > 0) {
			onlineCommodityModel.setCommoditySmallPic1(newImgs.get(0));
			onlineCommodityModel.setCommoditySmallPic2(newImgs.get(1));
			onlineCommodityModel.setCommoditySmallPic3(newImgs.get(2));
			onlineCommodityModel.setCommoditySmallPic4(newImgs.get(3));
			onlineCommodityModel.setCommoditySmallPic5(newImgs.get(4));
		}
		try {
			commodityService.addNewCommodityModel(onlineCommodityModel);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
		}
		return gson.toJson(jsonResult);
	}
	

	@RequestMapping(value = "/updateCommodityDetailAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateCommodityDetailAjax(String id,String editor1) {
		Gson gson = new Gson();
		JsonResult jsonResult = new JsonResult("0","0");
		try {
			OnlineCommodity onlineCommodity = new OnlineCommodity();
			if(StringUtil.isEmpty(id)){
				jsonResult.logicCode = "-1";
				jsonResult.resultMsg = "Can't find id";
				return gson.toJson(jsonResult);
			}
			onlineCommodity.setId(Integer.valueOf(id));
			onlineCommodity.setCommodityDetailFileName(editor1.trim());
			commodityService.updateCommodity(onlineCommodity);
		} catch (Exception e) {
			//e.printStackTrace();
			jsonResult.logicCode = "-1";
			jsonResult.resultMsg = e.getMessage();
		}
		return gson.toJson(jsonResult);
	}
}
