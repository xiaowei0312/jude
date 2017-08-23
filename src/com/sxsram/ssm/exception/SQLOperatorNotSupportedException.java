package com.sxsram.ssm.exception;

public class SQLOperatorNotSupportedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String operator;
	private String message = "";

	public SQLOperatorNotSupportedException() {

	}

	public SQLOperatorNotSupportedException(String operator) {
		this.message = "SQL Operator[" + operator + "] Not Supported";
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
