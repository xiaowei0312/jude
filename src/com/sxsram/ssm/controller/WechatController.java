package com.sxsram.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxsram.ssm.entity.UserExpand;
import com.sxsram.ssm.service.UserService;
import com.sxsram.ssm.util.ConfigUtil;
import com.sxsram.ssm.util.WechatNews;
import com.sxsram.ssm.util.WechatUtil;

@Controller
@RequestMapping(value = "/linuxcoder", method = { RequestMethod.GET, RequestMethod.POST })
public class WechatController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/validate", method = { RequestMethod.GET, RequestMethod.POST })
	public String validate(String echostr) throws Exception {
		if (null == echostr || echostr.isEmpty()) {
			return "forward:responseMsg.action";
		} else {
			return "forward:responseValidate.action";
		}
	}

	@RequestMapping(value = "/responseValidate", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String responseValidate(HttpServletRequest request, HttpServletResponse response, String signature,
			String timestamp, String nonce, String echostr) throws Exception {
		if (WechatUtil.checkSignature(ConfigUtil.TOKEN, signature, timestamp, nonce, echostr)) {
			return echostr;
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/responseMsg", method = { RequestMethod.POST })
	@ResponseBody
	public String responseMsg(HttpServletRequest request, HttpSession session) throws Exception {
		String postStr = null;
		Document document = null;
		Element root = null;
		postStr = WechatUtil.readPostContent(request.getInputStream());
		System.out.println("Request XML: " + postStr);
		if (null == postStr || postStr.isEmpty()) {
			return "";
		}
		document = DocumentHelper.parseText(postStr);
		if (null == document) {
			return "error";
		}
		root = document.getRootElement();
		String fromUserName = root.elementText("FromUserName");
		String toUserName = root.elementText("ToUserName");
		String msgType = root.elementTextTrim("MsgType");
		String msgId = root.elementTextTrim("MsgId");

		if (msgType.equals("text")) {
			// <xml><ToUserName><![CDATA[gh_680bdefc8c5d]]></ToUserName>
			// <FromUserName><![CDATA[oIDrpjqASyTPnxRmpS9O_ruZGsfk]]></FromUserName>
			// <CreateTime>1359044526</CreateTime>
			// <MsgType><![CDATA[text]]></MsgType>
			// <Content><![CDATA[/::)/::~/::B/::|/:8-)]]></Content>
			// <MsgId>5837051792978241864</MsgId>
			// </xml>
			// 文本消息

			// String content = root.elementTextTrim("Content");
			return WechatUtil.responseText(toUserName, fromUserName, ConfigUtil.USER_INPUT_MSG);

		} else if (msgType.equals("image")) {
			// <xml><ToUserName><![CDATA[gh_680bdefc8c5d]]></ToUserName>
			// <FromUserName><![CDATA[oIDrpjqASyTPnxRmpS9O_ruZGsfk]]></FromUserName>
			// <CreateTime>1359028479</CreateTime>
			// <MsgType><![CDATA[image]]></MsgType>
			// <PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz/L4qjYtOibummHn90t1mnaibYiaR8ljyicF3MW7XX3BLp1qZgUb7CtZ0DxqYFI4uAQH1FWs3hUicpibjF0pOqLEQyDMlg/0]]></PicUrl>
			// <MsgId>5836982871638042400</MsgId>
			// <MediaId><![CDATA[PGKsO3LAgbVTsFYO7FGu51KUYa07D0C_Nozz2fn1z6VYtHOsF59PTFl0vagGxkVH]]></MediaId>
			// </xml>
			String picUrl = root.elementTextTrim("PicUrl");
			String mediaId = root.elementTextTrim("MediaId");
		} else if (msgType.equals("voice")) {
			// <xml>
			// <ToUserName><![CDATA[gh_d035bb259cf5]]></ToUserName>
			// <FromUserName><![CDATA[owEUGj4BW8yeWRvyEERiVGKwAF1Q]]></FromUserName>
			// <CreateTime>1364883809</CreateTime>
			// <MsgType><![CDATA[voice]]></MsgType>
			// <MediaId><![CDATA[JfmCezZ3Cwp0FwUvMADwwhvp-XScuvpictubpw0c6ALyA8tj3HLU4PoXzMpIY72P]]></MediaId>
			// <Format><![CDATA[amr]]></Format>
			// <MsgId>5862131322594912688</MsgId>
			// </xml>
			String format = root.elementTextTrim("Format");
			String mediaId = root.elementTextTrim("MediaId");
		} else if (msgType.equals("video")) {
			// <xml><ToUserName><![CDATA[gh_680bdefc8c5d]]></ToUserName>
			// <FromUserName><![CDATA[oIDrpjqASyTPnxRmpS9O_ruZGsfk]]></FromUserName>
			// <CreateTime>1359028186</CreateTime>
			// <MsgType><![CDATA[video]]></MsgType>
			// <MediaId><![CDATA[DBVFRIj29LB2hxuYpc0R6VLyxwgyCHZPbRj_IIs6YaGhutyXUKtFSDcSCPeoqUYr]]></MediaId>
			// <ThumbMediaId><![CDATA[mxUJ5gcCeesJwx2T9qsk62YzIclCP_HnRdfTQcojlPeT2G9Q3d22UkSLyBFLZ01J]]></ThumbMediaId>
			// <MsgId>5836981613212624665</MsgId>
			// </xml>
			String thumbMediaId = root.elementTextTrim("ThumbMediaId");
			String mediaId = root.elementTextTrim("MediaId");
		} else if (msgType.equals("location")) {
			// <xml>
			// <ToUserName><![CDATA[gh_680bdefc8c5d]]></ToUserName>
			// <FromUserName><![CDATA[oIDrpjqASyTPnxRmpS9O_ruZGsfk]]></FromUserName>
			// <CreateTime>1359036619</CreateTime>
			// <MsgType><![CDATA[location]]></MsgType>
			// <Location_X>22.539968</Location_X>
			// <Location_Y>113.954980</Location_Y>
			// <Scale>16</Scale>
			// <Label><![CDATA[中国广东省深圳市南山区华侨城深南大道9789号 邮政编码: 518057]]></Label>
			// <MsgId>5837017832671832047</MsgId>
			// </xml>
			String location_X = root.elementTextTrim("Location_X");
			String location_Y = root.elementTextTrim("Location_Y");
			String scale = root.elementTextTrim("Scale");
			String label = root.elementTextTrim("Label");
		} else if (msgType.equals("link")) {
			// <xml>
			// <ToUserName><![CDATA[gh_680bdefc8c5d]]></ToUserName>
			// <FromUserName><![CDATA[oIDrpjl2LYdfTAM-oxDgB4XZcnc8]]></FromUserName>
			// <CreateTime>1359709372</CreateTime>
			// <MsgType><![CDATA[link]]></MsgType>
			// <Title><![CDATA[微信公众平台开发者的江湖]]></Title>
			// <Description><![CDATA[陈坤的微信公众号这段时间大火，大家..]]></Description>
			// <Url><![CDATA[http://israel.duapp.com/web/photo.php]]></Url>
			// <MsgId>5839907284805129867</MsgId>
			// </xml>
			String title = root.elementTextTrim("Title");
			String description = root.elementTextTrim("Description");
			String url = root.elementTextTrim("Url");
		} else if (msgType.equals("event")) {
			String event = root.elementTextTrim("Event");
			String eventKey = root.elementTextTrim("EventKey");
			if (event.equals("subscribe")) {
			} else if (event.equals("unsubscribe")) {
			} else if (event.equals("CLICK")) {
				return doClickEvent(fromUserName, toUserName, eventKey, session);
			} else if (event.equals("VIEW")) {
			} else {
			}
		} else {
			// Other
		}
		return "";
	}

	private String doClickEvent(String fromUserName, String toUserName, String eventKey, HttpSession session)
			throws Exception {
		UserExpand userExpand = userService.getUserAccountInfo(fromUserName);
		if (userExpand == null) {
			return WechatUtil.responseText(toUserName, fromUserName, ConfigUtil.MENU_CLICK_NOT_LOGIN_MSG);
		}

		String content = null;
		if (eventKey.equals(ConfigUtil.MENU_KEY_ZHGK)) {
			content = String.format(ConfigUtil.MENU_CLICK_ZZGK_MSG, 
					(int)userExpand.getJfAccount().getAccountBalance(),
					userExpand.getMoneyAccount().getAccountBalance(),
					userExpand.getMoneyAccount().getTotalPlatformOutgoings(),
					userExpand.getMoneyAccount().getTotalPlatformIncomings()
							+ userExpand.getYljAccount().getTotalPlatformIncomings(),
					(int)userExpand.getDlbAccount().getAccountBalance());
			return WechatUtil.responseText(toUserName, fromUserName, content);
		} else if (eventKey.equals(ConfigUtil.MENU_KEY_YLJCX)) {
			content = String.format(ConfigUtil.MENU_CLICK_YLJCX_MSG, userExpand.getYljAccount().getAccountBalance(),
					userExpand.getYljAccount().getAccountBalance(),
					userExpand.getYljAccount().getTotalPlatformIncomings(),
					userExpand.getYljAccount().getTotalPlatformOutgoings());
			return WechatUtil.responseText(toUserName, fromUserName, content);
		} else if (eventKey.equals(ConfigUtil.MENU_KEY_JDLN)) {
			WechatNews news = new WechatNews();
			news.setTitle("聚德消费养老项目介绍");
			news.setDesc("专注为消费者提供线上线下生活消费，积攒养老金服务的O2O电商平台。");
			news.setPicUrl("http://122.114.160.103/wechat/img/logo.png");
			news.setUrl("http://122.114.160.103/wechat/mall/intro.action");
			return WechatUtil.responseSingleNews(toUserName, fromUserName, "", news);
		} else if (eventKey.equals(ConfigUtil.MENU_KEY_GSZZ)) {
			WechatNews news = new WechatNews();
			news.setTitle("聚德购物商城资质");
			news.setDesc("网站备案：晋ICP备1601xxx号");
			news.setPicUrl("http://122.114.160.103/wechat/img/zizhi.png");
			news.setUrl("http://122.114.160.103/wechat/mall/zizhi.action");
			return WechatUtil.responseSingleNews(toUserName, fromUserName, "", news);
		} else {

		}
		return WechatUtil.responseText(toUserName, fromUserName, "");
	}
}
