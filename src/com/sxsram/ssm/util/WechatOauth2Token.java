package com.sxsram.ssm.util;

import com.google.gson.annotations.SerializedName;

/**
 * 类名: WeixinOauth2Token </br>
 * 描述: 网页授权信息 </br>
 * 开发人员： souvc </br>
 * 创建时间： 2015-11-27 </br>
 * 发布版本：V1.0 </br>
 */
public class WechatOauth2Token {
	// 网页授权接口调用凭证
	@SerializedName("access_token")
	private String accessToken;
	// 凭证有效时长
	@SerializedName("expires_in")
	private int expiresIn;
	// 用于刷新凭证
	@SerializedName("refresh_token")
	private String refreshToken;
	// 用户标识
	@SerializedName("openid")
	private String openId;
	// 用户授权作用域
	@SerializedName("scope")
	private String scope;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "WechatOauth2Token [accessToken=" + accessToken + ", expiresIn=" + expiresIn + ", refreshToken="
				+ refreshToken + ", openId=" + openId + ", scope=" + scope + "]";
	}
}
