package com.sxsram.ssm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletInputStream;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WechatUtil {
	// 数组转字符串
	public static String ArrayToString(String[] arr) {
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			bf.append(arr[i]);
		}
		return bf.toString();
	}

	// sha1加密
	public static String SHA1Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public static final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}

	// 微信接口验证
	public static boolean checkSignature(final String TOKEN, String signature, String timestamp, String nonce,
			String echostr) {
		String token = TOKEN;
		String[] tmpArr = { token, timestamp, nonce };
		Arrays.sort(tmpArr);
		String tmpStr = ArrayToString(tmpArr);
		tmpStr = SHA1Encode(tmpStr);
		if (tmpStr.equalsIgnoreCase(signature)) {
			return true;
		} else {
			return false;
		}
	}

	// 从输入流读取post参数
	public static String readPostContent(ServletInputStream in) {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}

	// <xml>
	// <ToUserName><![CDATA[oIDrpjqASyTPnxRmpS9O_ruZGsfk]]></ToUserName>
	// <FromUserName><![CDATA[gh_680bdefc8c5d]]></FromUserName>
	// <CreateTime>1359036631</CreateTime>
	// <MsgType><![CDATA[text]]></MsgType>
	// <Content><![CDATA[【深圳】天气实况 温度：27℃ 湿度：59% 风速：东北风3级
	// 11月03日 周日 27℃~23℃ 小雨 东北风4-5级
	// 11月04日 周一 26℃~21℃ 阵雨 微风
	// 11月05日 周二 27℃~22℃ 阴 微风]]></Content>
	// <FuncFlag>0</FuncFlag>
	// </xml>
	public static String responseText(String fromUserName, String toUserName, String content) {
		String msgType = "text";
		String time = new Date().getTime() + "";
		String textTpl = "<xml>" + "<ToUserName><![CDATA[%1$s]]></ToUserName>"
				+ "<FromUserName><![CDATA[%2$s]]></FromUserName>" + "<CreateTime>%3$s</CreateTime>"
				+ "<MsgType><![CDATA[%4$s]]></MsgType>" + "<Content><![CDATA[%5$s]]></Content>"
				+ "<FuncFlag>0</FuncFlag>" + "</xml>";
		return String.format(textTpl, toUserName, fromUserName, time, msgType, content);
	}

	// <xml>
	// <ToUserName><![CDATA[oIDrpjqASyTPnxRmpS9O_ruZGsfk]]></ToUserName>
	// <FromUserName><![CDATA[gh_680bdefc8c5d]]></FromUserName>
	// <CreateTime>1359011899</CreateTime>
	// <MsgType><![CDATA[news]]></MsgType>
	// <Content><![CDATA[]]></Content>
	// <ArticleCount>1</ArticleCount>
	// <Articles>
	// <item>
	// <Title><![CDATA[[苹果产品信息查询]]></Title>
	// <Description><![CDATA[序列号：USE IMEI NUMBER
	// IMEI号：358031058974471
	// 设备名称：iPhone 5C
	// 设备颜色：
	// 设备容量：
	// 激活状态：已激活
	// 电话支持：未过期[2014-01-13]
	// 硬件保修：未过期[2014-10-14]
	// 生产工厂：中国]]>
	// </Description>
	// <PicUrl><![CDATA[http://www.fangbei.org/weixin/weather/icon/banner.jpg]]></PicUrl>
	// <Url><![CDATA[]]></Url>
	// </item>
	// </Articles>
	// <FuncFlag>0</FuncFlag>
	// </xml>
	public static String responseSingleNews(String fromUserName, String toUserName, String content, WechatNews news) {
		String msgType = "news";
		String time = new Date().getTime() + "";
		String textTpl = "<xml>" + "<ToUserName><![CDATA[%1$s]]></ToUserName>"
				+ "<FromUserName><![CDATA[%2$s]]></FromUserName>" + "<CreateTime>%3$s</CreateTime>"
				+ "<MsgType><![CDATA[%4$s]]></MsgType>" + "<ArticleCount>%6$d</ArticleCount>" + "<Articles><item>"
				+ "<Title><![CDATA[[%7$s]]></Title>" + "<Description><![CDATA[%8$s]]></Description>"
				+ "<PicUrl><![CDATA[%9$s]]></PicUrl>" + "<Url><![CDATA[%10$s]]></Url>" + "</item></Articles>"
				+ "</xml>";
		return String.format(textTpl, toUserName, fromUserName, time, msgType, content, 1, news.getTitle(),
				news.getDesc(), news.getPicUrl(), news.getUrl());
	}

	public static String responseTemplateMsg(String appId, String appSecret, String toUserName, String templateId,
			String url, String content) {
		class WxTemplate {
			public String touser;
			public String template_id;
			public String url;
			public String data;

			public WxTemplate(String touser, String template_id, String url, String data) {
				super();
				this.touser = touser;
				this.template_id = template_id;
				this.url = url;
				this.data = data;
			}

			public WxTemplate() {
				super();
				// TODO Auto-generated constructor stub
			}

		}

		// 获取access_token
		String accessToken = WechatJsapiSignUtil.getAccessToken(appId, appSecret);

		// 发送模板消息
		String templateMsgUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
		WxTemplate wxTemplate = new WxTemplate(toUserName, templateId, url, content);
		Gson gson = new Gson();
		String json = gson.toJson(wxTemplate);
		String response = HttpsUtil.httpsRequest(url, "POST", json);
		return response;
		
		// 发送模板消息
		// https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
		// {
		// "touser":"OPENID",
		// "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
		// "url":"http://weixin.qq.com/download",
		// "data":{
		// "first": {
		// "value":"恭喜你购买成功！",
		// "color":"#173177"
		// },
		// "keynote1":{
		// "value":"巧克力",
		// "color":"#173177"
		// },
		// "keynote2": {
		// "value":"39.8元",
		// "color":"#173177"
		// },
		// "keynote3": {
		// "value":"2014年9月22日",
		// "color":"#173177"
		// },
		// "remark":{
		// "value":"欢迎再次购买！",
		// "color":"#173177"
		// }
		// }
		// }
	}

	/**
	 * 获取网页授权凭证
	 * 
	 * @param appId
	 *            公众账号的唯一标识
	 * @param appSecret
	 *            公众账号的密钥
	 * @param code
	 * @return WechatOauth2Token
	 */
	public static WechatOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
		WechatOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// 获取网页授权凭证
		System.out.println("HttpsRequest Start: " + requestUrl);
		String json = HttpsUtil.httpsRequest(requestUrl, "GET", null);
		System.out.println("HttpsRequest End...");
		Gson gson = new Gson();
		if (null != json) {
			try {
				wat = gson.fromJson(json, WechatOauth2Token.class);
			} catch (Exception e) {
				wat = null;
				JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
				int errorCode = Integer.parseInt(jsonObject.get("errcode").toString());
				String errorMsg = jsonObject.get("errmsg").toString();
				System.err.println("获取网页授权凭证失败 errcode:{" + errorCode + "}," + "errmsg:{" + errorMsg + "}");
			}
		}
		return wat;
	}

	/**
	 * 通过网页授权获取用户信息
	 * 
	 * @param accessToken
	 *            网页授权接口调用凭证
	 * @param openId
	 *            用户标识
	 * @return SNSUserInfo
	 */
	public static WechatOAuth2User getOauth2UserInfo(String accessToken, String openId) {
		WechatOAuth2User snsUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		// 通过网页授权获取用户信息
		String json = HttpsUtil.httpsRequest(requestUrl, "GET", null);
		System.out.println("json: " + json);
		Gson gson = new Gson();
		if (null != json) {
			try {
				snsUserInfo = gson.fromJson(json, WechatOAuth2User.class);
			} catch (Exception e) {
				snsUserInfo = null;
				JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
				int errorCode = Integer.valueOf(jsonObject.get("errcode").toString());
				String errorMsg = jsonObject.get("errmsg").toString();
				System.err.println("获取用户信息失败 errcode:{" + errorCode + "}," + "errmsg:{" + errorMsg + "}");
			}
		}
		return snsUserInfo;
	}

	private final static double EARTH_RADIUS = 6378.137 * 1000;// 地球半径,单位为米

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/// <summary>
	/// 返回两点之间的距离，单位为米
	/// </summary>
	/// <param name="lat1"></param>
	/// <param name="lng1"></param>
	/// <param name="lat2"></param>
	/// <param name="lng2"></param>
	/// <returns></returns>
	public static double getDistance(WechatPosition pos1, WechatPosition pos2) {
		double radLat1 = rad(pos1.getLatitude());
		double radLat2 = rad(pos2.getLatitude());
		double a = radLat1 - radLat2;
		double b = rad(pos1.getLongitude()) - rad(pos2.getLongitude());

		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
}
