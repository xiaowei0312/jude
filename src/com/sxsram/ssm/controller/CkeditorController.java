package com.sxsram.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.jsp.jstl.core.Config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.sxsram.ssm.util.ConfigUtil;
import com.sxsram.ssm.util.JsonResult;

@Controller()
@RequestMapping(value = "/ckeditor", method = { RequestMethod.GET, RequestMethod.POST })
public class CkeditorController {

	private String uploadImg(MultipartFile img) throws IllegalStateException, IOException {
		String orginalFilename = img.getOriginalFilename();
		String imgPath = ConfigUtil.onlineCommodImgs;
		String filename = UUID.randomUUID() + orginalFilename.substring(orginalFilename.lastIndexOf('.'));
		File newFile = new File(imgPath + filename);
		img.transferTo(newFile);
		return filename;
	}

	@RequestMapping(value = "/imgUpload", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String imgUpload(MultipartFile upload, String CKEditorFuncNum) {
		String callback = CKEditorFuncNum;
		String newImg = null;
		MultipartFile img = upload;
		String originalFilename = img.getOriginalFilename();
		if (originalFilename == null || originalFilename.length() == 0) {
			newImg = null;
		} else {
			if (!originalFilename.endsWith(".jpg") && !originalFilename.endsWith(".jpeg")
					&& !originalFilename.endsWith(".png")) {
				return "<script type=\"text/javascript\">" + "window.parent.CKEDITOR.tools.callFunction(" + callback
						+ ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');" + "</script>";
			}
			try {
				newImg = uploadImg(img);
			} catch (Exception e) {
				e.printStackTrace();
				return "<script type=\"text/javascript\">" + "window.parent.CKEDITOR.tools.callFunction(" + callback
						+ ",''," + "'上传文件错误:" + e.getMessage() + "');" + "</script>";
			}
		}

		return "<script type=\"text/javascript\">" + "window.parent.CKEDITOR.tools.callFunction(" + callback + ",'"
				+ ConfigUtil.onlineCommodImgsVFullPath + newImg + "','') " + "</script>";
	}

	@RequestMapping(value = "/imgUploadTest", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String imgUploadTest(MultipartFile upload) {
		Gson gson = new Gson();
		JsonResult jsonResult = new JsonResult("0", "0");

		String newImg = null;
		MultipartFile img = upload;
		String originalFilename = img.getOriginalFilename();
		if (originalFilename == null || originalFilename.length() == 0) {
			newImg = null;
		} else {
			if (!originalFilename.endsWith(".jpg") && !originalFilename.endsWith(".jpeg")
					&& !originalFilename.endsWith(".png")) {
				jsonResult.logicCode = "-1";
				jsonResult.resultMsg = "只能接受.jpg/.jpeg/.png格式的文件.";
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
		jsonResult.resultMsg = newImg;
		return gson.toJson(jsonResult);
	}
}
