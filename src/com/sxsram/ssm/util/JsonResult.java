package com.sxsram.ssm.util;

public class JsonResult {
	public String resultCode;
	public String logicCode;
	public String resultMsg;
	public Object resultObj;
	public String url;
	
	public JsonResult(String resultCode, String logicCode, String resultMsg, Object resultObj, String url) {
		super();
		this.resultCode = resultCode;
		this.logicCode = logicCode;
		this.resultMsg = resultMsg;
		this.resultObj = resultObj;
		this.url = url;
	}

	public JsonResult(String resultCode, String logicCode, String resultMsg, Object resultObj) {
		super();
		this.resultCode = resultCode;
		this.logicCode = logicCode;
		this.resultMsg = resultMsg;
		this.resultObj = resultObj;
	}

	public JsonResult(String resultCode, String logicCode, String resultMsg) {
		super();
		this.resultCode = resultCode;
		this.logicCode = logicCode;
		this.resultMsg = resultMsg;
	}

	public JsonResult(String resultCode, String logicCode) {
		super();
		this.resultCode = resultCode;
		this.logicCode = logicCode;
	}

	public JsonResult() {
		super();
		// TODO Auto-generated constructor stub
	}
}
