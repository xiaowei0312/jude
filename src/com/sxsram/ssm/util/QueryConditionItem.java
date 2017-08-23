package com.sxsram.ssm.util;

public class QueryConditionItem implements QueryConditionAbstractItem {
	private String key;
	private String value;
	private QueryConditionOp operator;

	public QueryConditionItem(String key, String value, QueryConditionOp operator) {
		super();
		this.key = key;
		this.value = value;
		this.operator = operator;
	}

	public QueryConditionItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public QueryConditionOp getOperator() {
		return operator;
	}

	public void setOperator(QueryConditionOp operator) {
		this.operator = operator;
	}
}
