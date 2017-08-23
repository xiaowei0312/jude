package com.sxsram.ssm.exception;

public class TestException extends Exception {

	private String message = "Test Exception";

	public TestException() {

	}

	public TestException(String mString) {
		this.message = mString;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
