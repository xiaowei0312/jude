package com.sxsram.ssm.test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sxsram.ssm.util.WechatOAuth2User;
import com.sxsram.ssm.util.WechatOauth2Token;

public class Test {
	public static void testJson() {
		String json = "{\"access_token\":\"8c95ZA8Jqrsew6iBk0u7OqmjWReKApSKRc_6Z50FD7SYMAHQ19sOJ4of1ZMuM_r5HyFnNMtGP3CnFJg9qzDrwqRC_xIkEPQYb0HafV0SXII\",\"expires_in\":7200,\"refresh_token\":\"fAPnf3DbJnPJazWTO3FCu9NL7yStnT2e6pNXTaQAKn7egrTvvkL6h8JvUSOycFW_7suTIyKaOlRziT63lcxHi55Zb2-UWV2tboPxV9IYKcI\",\"openid\":\"oVX5hwLoTdw1iWTiI5BY0O-NkNmU\",\"scope\":\"snsapi_userinfo\"}";
		// JSONObject jsonObject = JSONObject.fromObject(
		// "{\"access_token\":\"8c95ZA8Jqrsew6iBk0u7OqmjWReKApSKRc_6Z50FD7SYMAHQ19sOJ4of1ZMuM_r5HyFnNMtGP3CnFJg9qzDrwqRC_xIkEPQYb0HafV0SXII\",\"expires_in\":7200,\"refresh_token\":\"fAPnf3DbJnPJazWTO3FCu9NL7yStnT2e6pNXTaQAKn7egrTvvkL6h8JvUSOycFW_7suTIyKaOlRziT63lcxHi55Zb2-UWV2tboPxV9IYKcI\",\"openid\":\"oVX5hwLoTdw1iWTiI5BY0O-NkNmU\",\"scope\":\"snsapi_userinfo\"}");
		
		json = "{\"openid\":\"oVX5hwLoTdw1iWTiI5BY0O-NkNmU\",\"nickname\":\"卫\",\"sex\":0,\"language\":\"zh_CN\",\"city\":\"\",\"province\":\"\",\"country\":\"CN\",\"headimgurl\":\"http://wx.qlogo.cn/mmopen/Q3auHgzwzM5cQOOmksfrPsZ82MbjrbS2NlnX1BSY5VtMspGj3os8Qbc11m5pRCDcNsR5tClZGUoEw0HOhfEicVGUiaY8fDY2rZaIOQwm6vm7k/0\",\"privilege\":[]}";
		Gson gson = new Gson();
		WechatOAuth2User token  = gson.fromJson(json,WechatOAuth2User.class);
		System.out.println(token);
		
		JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
		System.out.println(jsonObject.get("access_token"));
	}
	
	public static void test(){
		String string = "161105160927707321";
		System.out.println(string.matches("[0-9]*"));
	}

	public static void main(String[] args) {
		//testJson();
		test();
	}
}
