package com.sxsram.ssm.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxsram.ssm.entity.RechargeAndWithDrawRecord;
import com.sxsram.ssm.entity.RechargeAndWithDrawRecordExpand;
import com.sxsram.ssm.entity.User;
import com.sxsram.ssm.entity.UserExpand;
import com.sxsram.ssm.service.UserService;
import com.sxsram.ssm.util.ConfigUtil;
import com.sxsram.ssm.util.HttpsUtil;
import com.sxsram.ssm.util.IpAddressUtil;
import com.sxsram.ssm.util.WechatJsapiSignUtil;
import com.sxsram.ssm.util.WechatOauth2Token;
import com.sxsram.ssm.util.WechatUtil;
import com.sxsram.ssm.util.XMLUtil;

import net.sf.json.JSONObject;

@Controller()
@RequestMapping(value = "/wxpay", method = { RequestMethod.POST })
public class WxPayController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/sellerSubmitOrderPay", method = { RequestMethod.POST })
	@ResponseBody
	public String sellerSubmitOrderPay(HttpServletRequest request, HttpSession session, String code, String state,
			double tradeNum) throws Exception {
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		SortedMap<Object, Object> params = new TreeMap<Object, Object>();
		params.put("error", "");
		// 检查是否为微信用户
		String openId = (String) session.getAttribute("openId");
		if (openId == null) {
			if (code == null) {
				params.put("error", "请使用微信访问本应用.");
			} else {
				WechatOauth2Token wechatOauth2Token = null;
				wechatOauth2Token = WechatUtil.getOauth2AccessToken(ConfigUtil.APPID, ConfigUtil.APPSECRET, code);
				if (wechatOauth2Token == null) {
					params.put("error", "请使用微信访问本应用.");
				} else {
					openId = wechatOauth2Token.getOpenId();
				}
			}
		}
		if (openId == null) {
			String json = JSONObject.fromObject(params).toString();
			return json;
		}
		System.out.println(tradeNum);
		//tradeNum = 0.01;
		String orderNo = WechatJsapiSignUtil.create_nonce_str();
		String orderBody = "微信充值";
		String clientIp = IpAddressUtil.getIpAddr(request);
		UserExpand user = (UserExpand) session.getAttribute("user");

		parameters.put("appid", ConfigUtil.APPID);
		parameters.put("mch_id", ConfigUtil.MCH_ID);
		parameters.put("nonce_str", WechatJsapiSignUtil.create_nonce_str());
		parameters.put("body", orderBody);
		parameters.put("out_trade_no", orderNo);
		parameters.put("total_fee", (int) (tradeNum * 100) + "");
		parameters.put("spbill_create_ip", clientIp);
		parameters.put("notify_url", ConfigUtil.NOTIFY_URL);
		parameters.put("trade_type", "JSAPI");
		parameters.put("openid", openId);
		String sign = WechatJsapiSignUtil.createPaySign(ConfigUtil.PAYKEY, "UTF-8", parameters);
		parameters.put("sign", sign);
		String requestXML = WechatJsapiSignUtil.getRequestXml(parameters);
		System.out.println("---------requestXML: ------" + requestXML);
		String result = HttpsUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
		System.out.println("----result---->" + result);
		Map<String, String> map = XMLUtil.doXMLParse(result);// 解析微信返回的信息，以Map形式存储便于取值

		String prepayId = map.get("prepay_id");
		if (prepayId == null && prepayId.equals("")) {
			params.put("error", "统一订单请求失败");
			String json = JSONObject.fromObject(params).toString();
			return json;
		}

		// 生成订单记录,存入数据库
		RechargeAndWithDrawRecordExpand record = new RechargeAndWithDrawRecordExpand(ConfigUtil.TradeType.RECHARGE,
				orderNo, tradeNum, 0.0, ConfigUtil.TradeState.PAYPENDING, clientIp);
		record.setUser(user);
		userService.userRecharge(record);

		// 返回json给页面
		params.put("appId", ConfigUtil.APPID);
		params.put("timeStamp", Long.toString(new Date().getTime()));
		params.put("nonceStr", WechatJsapiSignUtil.create_nonce_str());
		params.put("package", "prepay_id=" + map.get("prepay_id"));
		params.put("signType", ConfigUtil.SIGN_TYPE);
		String paySign = WechatJsapiSignUtil.createPaySign(ConfigUtil.PAYKEY, "UTF-8", params);
		params.put("packageValue", "prepay_id=" + map.get("prepay_id")); // 这里用packageValue是预防package是关键字在js获取值出错
		params.put("paySign", paySign); // paySign的生成规则和Sign的生成规则一致
		params.put("sendUrl", ConfigUtil.SUCCESS_URL); // 付款成功后跳转的页面
		String userAgent = request.getHeader("user-agent");
		char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger") + 15);
		params.put("agent", new String(new char[] { agent }));// 微信版本号，用于前面提到的判断用户手机微信的版本是否是5.0以上版本。
		String json = JSONObject.fromObject(params).toString();
		return json;
	}

	@RequestMapping(value = "/serverNotify", method = { RequestMethod.POST })
	@ResponseBody
	public String serverNotify(HttpServletRequest request, HttpSession session, String xml) throws Exception {
		// <xml>
		// <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
		// <attach><![CDATA[支付测试]]></attach>
		// <bank_type><![CDATA[CFT]]></bank_type>
		// <fee_type><![CDATA[CNY]]></fee_type>
		// <is_subscribe><![CDATA[Y]]></is_subscribe>
		// <mch_id><![CDATA[10000100]]></mch_id>
		// <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>
		// <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>
		// <out_trade_no><![CDATA[1409811653]]></out_trade_no>
		// <result_code><![CDATA[SUCCESS]]></result_code>
		// <return_code><![CDATA[SUCCESS]]></return_code>
		// <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>
		// <sub_mch_id><![CDATA[10000100]]></sub_mch_id>
		// <time_end><![CDATA[20140903131540]]></time_end>
		// <total_fee>1</total_fee>
		// <trade_type><![CDATA[JSAPI]]></trade_type>
		// <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>
		// </xml>

		String ret = "<xml>" + "<return_code><![CDATA[%1$s]]></return_code>"
				+ "<return_msg><![CDATA[%2$s]]></return_msg>" + "</xml>";

		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		System.out.println("~~~~~~~~~~~~~~~~付款成功~~~~~~~~~");
		outSteam.close();
		inStream.close();
		String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
		Map<Object, Object> map = XMLUtil.doXMLParse(result);
		for (Object keyValue : map.keySet()) {
			System.out.println(keyValue + "=" + map.get(keyValue));
		}

		// 通信失败，提示服务器重新发送
		if (map.get("return_code").toString().equalsIgnoreCase("FAIL")) {// 通信失败
			return String.format(ret, "FAIL", "");
		}

		// 获取订单状态
		RechargeAndWithDrawRecordExpand record = userService
				.findRechargeRecordByOrderNo((String) map.get("out_trade_no"));
		if (record == null) {
			// 报警.
			return String.format(ret, "SUCCESS", "OK"); // 告诉微信服务器，我收到信息了，不要在调用回调action了
		}

		// 查看该订单是否已经处理过
		if (record.getOperateState() != ConfigUtil.TradeState.PAYPENDING.ordinal()) {// 已经处理过了
			return String.format(ret, "SUCCESS", "OK"); // 告诉微信服务器，我收到信息了，不要在调用回调action了
		}

		// 获取交易结果
		if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
			record.setOperateState(ConfigUtil.TradeState.PAYSUCCESS.ordinal());
		} else {
			record.setOperateState(ConfigUtil.TradeState.PAYERROR.ordinal());
		}
		userService.updateRechargeRecordState(record);
		return String.format(ret, "SUCCESS", "OK"); // 告诉微信服务器，我收到信息了，不要在调用回调action了
	}
}
