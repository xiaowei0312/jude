package com.sxsram.ssm.exception;

public class CommodityModelNotFoundException extends Exception {

	private String message;
	private Integer id;

	public CommodityModelNotFoundException() {

	}

	public CommodityModelNotFoundException(Integer id) {
		this.message = "Can't find the specified[" + id + "] CommodityModel";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
