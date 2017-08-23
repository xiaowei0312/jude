package com.sxsram.ssm.util;

import java.util.ArrayList;
import java.util.List;

public class QueryConditionOrItems implements QueryConditionAbstractItem {
	private List<QueryConditionItem> items = new ArrayList<>();

	public List<QueryConditionItem> getItems() {
		return items;
	}

	public void setItems(List<QueryConditionItem> items) {
		this.items = items;
	}
	
	
}
