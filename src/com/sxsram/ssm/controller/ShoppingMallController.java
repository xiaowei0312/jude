package com.sxsram.ssm.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;

import com.sxsram.ssm.entity.ShoppingMallExpand;
import com.sxsram.ssm.entity.UserExpand;
import com.sxsram.ssm.service.MallService;
import com.sxsram.ssm.service.UserService;
import com.sxsram.ssm.util.ConfigUtil;
import com.sxsram.ssm.util.JsonResult;

@Controller()
@RequestMapping(value = "/malltest", method = { RequestMethod.GET, RequestMethod.POST })
public class ShoppingMallController {
	@Autowired
	private MallService mallService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/intro", method = { RequestMethod.GET, RequestMethod.POST })
	public String intro() throws Exception {
		return "mall/intro";
	}

	@RequestMapping(value = "/mallStatics", method = { RequestMethod.GET, RequestMethod.POST })
	public String mallStatics() throws Exception {
		return "mall/mallStatics";
	}

	@RequestMapping(value = "/mallOverview", method = { RequestMethod.GET, RequestMethod.POST })
	public String mallOverview(HttpSession session, Model model) throws Exception {
		List<ShoppingMallExpand> shoppingMallExpands = null;
		UserExpand sessionUser = (UserExpand) session.getAttribute("user");
		if (sessionUser.getRole().getRoleName().equals("代理商")) {
			shoppingMallExpands = mallService.findAllMallsByProxyId(sessionUser.getId());
		} else {
			shoppingMallExpands = mallService.findAllMalls();
		}
		if (shoppingMallExpands != null && shoppingMallExpands.size() > 0)
			model.addAttribute("mallList", shoppingMallExpands);
		return "mall/mallOverview";
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

	@RequestMapping(value = "/MallAddAjax", method = { RequestMethod.GET, RequestMethod.POST })
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

	@RequestMapping(value = "/deleteMallAjax", method = { RequestMethod.GET, RequestMethod.POST })
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
