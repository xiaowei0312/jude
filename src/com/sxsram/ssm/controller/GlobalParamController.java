package com.sxsram.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sxsram.ssm.entity.GlobalPrams;
import com.sxsram.ssm.service.GlobalParamService;
import com.sxsram.ssm.util.JsonResult;

@Controller()
@RequestMapping(value = "/setting", method = { RequestMethod.GET, RequestMethod.POST })
public class GlobalParamController {
	@Autowired
	private GlobalParamService globalParamService;

	@RequestMapping(value = "/settingCenter", method = { RequestMethod.GET, RequestMethod.POST })
	public String settingCenter(Model model) {
		GlobalPrams globalPrams = null;
		try {
			globalPrams = globalParamService.findCurrentGlobalParams();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("globalParams", globalPrams);
		return "setting/settingCenter";
	}

	@RequestMapping(value = "/updateGlobalParamsAjax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateGlobalParams(GlobalPrams globalParams, Model model) {
		Gson gson = new Gson();
		try {
			globalParamService.updateGlobalParams(globalParams);
			return gson.toJson(new JsonResult("0", "0"));
		} catch (Exception e) {
			e.printStackTrace();
			return gson.toJson(new JsonResult("0", "-1", "更新参数失败"));
		}
	}

	@RequestMapping(value = "/settingRecord", method = { RequestMethod.GET, RequestMethod.POST })
	public String settingRecord(GlobalPrams globalParams, Model model) {
		List<GlobalPrams> globalPramsList = null;
		try {
			globalPramsList = globalParamService.findAllGlobalParamsChangeRecord();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("globalParamsList", globalPramsList);
		return "setting/settingRecord";
	}
}
