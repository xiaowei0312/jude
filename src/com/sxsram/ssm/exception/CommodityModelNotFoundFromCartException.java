package com.sxsram.ssm.exception;

public class CommodityModelNotFoundFromCartException extends Exception {

	private String message;

	public CommodityModelNotFoundFromCartException() {
		this.message = "Can't find the specified CommodityModel from CART";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
