package com.sxsram.ssm.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sxsram.ssm.entity.Address;
import com.sxsram.ssm.service.UserService;
import com.sxsram.ssm.util.JsonResult;
import com.sxsram.ssm.util.SmsUtil;

@Controller()
@RequestMapping(value = "/user", method = { RequestMethod.GET, RequestMethod.POST })
public class UserValidateController {
	private String smsText = "您正在注册成为聚德会员，校验码：SMSCODE，如果以上非您本人操作，请忽略本短信【智网科技】";
	@Autowired
	private UserService userService;

	@RequestMapping({ "imageVerifyCode" })
	public void getImageVerifyCode(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		int width = 63;
		int height = 37;
		Random random = new Random();
		// 设置response头信息
		// 禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 生成缓冲区image类
		BufferedImage image = new BufferedImage(width, height, 1);
		// 产生image类的Graphics用于绘制操作
		Graphics g = image.getGraphics();
		// Graphics类的样式
		g.setColor(this.getRandColor(200, 250));
		g.setFont(new Font("Times New Roman", 0, 28));
		g.fillRect(0, 0, width, height);
		// 绘制干扰线
		for (int i = 0; i < 40; i++) {
			g.setColor(this.getRandColor(130, 200));
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y, x + x1, y + y1);
		}

		// 绘制字符
		String strCode = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			strCode = strCode + rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 28);
		}
		// 将字符保存到session中用于前端的验证
		session.setAttribute("imageVerifyCode", strCode);
		g.dispose();

		ImageIO.write(image, "JPEG", response.getOutputStream());
		response.getOutputStream().flush();
	}

	// 创建颜色
	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	@RequestMapping(value = "/registerValidate", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String registerValidate(HttpSession session, String reqType, String addrId, String username, String phone,
			String verifyCode) throws Exception {
		System.out.println("reqType:"+reqType);
		Gson gson = new Gson();
		String json = "";
		List<Address> addresses = null;
		if (reqType.equals("loadProvince")) {
			addresses = userService.findAllProvinces();
			json = gson.toJson(addresses);
		}else if (reqType.equals("loadEparchy")) {
			addresses = userService.findEparchies(Integer.valueOf(addrId));
			json = gson.toJson(addresses);
		} else if (reqType.equals("loadCity")) {
			addresses = userService.findCities(Integer.valueOf(addrId));
			json = gson.toJson(addresses);
		} else if (reqType.equals("validAcct")) { // 验证账户是否已经存在
			if (userService.usernameExist(username)) {
				json = gson.toJson(new JsonResult("0", "1", "用户名已被注册"));
			} else {
				json = gson.toJson(new JsonResult("0", "0"));
			}
		} else if (reqType.equals("validPhone")) { // 验证手机号是否已经存在
			if (userService.phoneExist(phone)) {
				json = gson.toJson(new JsonResult("0", "1", "手机号已被注册"));
			} else {
				json = gson.toJson(new JsonResult("0", "0"));
			}
		} else if (reqType.equals("validMsg")) { // 发送手机验证码
			// 1.检查验证码是否正确
			String currentVerifyCode = (String) session.getAttribute("imageVerifyCode");
			if (verifyCode == null || currentVerifyCode == null || !verifyCode.equals(currentVerifyCode)) {
				json = gson.toJson(new JsonResult("0", "-1", "验证码错误"));
			} else {
				// 2.检查是否时间不够120s
				Long previousTimeSeconds = (Long) session.getAttribute("smsSendTime");
				long nowTimeSeconds = System.currentTimeMillis() / 1000;
				if (previousTimeSeconds != null && (nowTimeSeconds - previousTimeSeconds) < 120) {
					json = gson.toJson(new JsonResult("0", "-4"));
				} else {
					String smsCode = createRandom(true, 6);
					if (sendSMS(phone, smsText.replaceAll("SMSCODE", smsCode)) != 0) {
						json = gson.toJson(new JsonResult("0", "-2", "短信发送失败"));
					} else {
						session.setAttribute("smsCode", smsCode);
						session.setAttribute("smsSendTime", System.currentTimeMillis() / 1000);
						json = gson.toJson(new JsonResult("0", "0"));
					}
				}
			}
		}
		return json;
	}

	private int sendSMS(String phone, String smsMsg) throws Exception {
		// System.out.println(phone + ":" + smsMsg);
		String account = "xbgtest001";// 账号
		String pswd = "123456";// 密码
		String mobile = phone;// 手机号码，多个号码使用","分割
		String msg = smsMsg;// 短信内容
		String returnString = SmsUtil.SubmitSms(account, pswd, mobile, msg, "1001");
		System.out.println("短发发送状态：" + returnString);

		if (returnString.matches("[0-9]*"))
			return 0;
		return -1;
	}

	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}
}
