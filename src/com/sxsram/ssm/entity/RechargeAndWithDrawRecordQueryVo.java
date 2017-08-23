package com.sxsram.ssm.entity;

import com.sxsram.ssm.util.Pagination;
import com.sxsram.ssm.util.QueryCondition;

public class RechargeAndWithDrawRecordQueryVo extends RechargeAndWithDrawRecordExpand {
	private Pagination pagination;
	private QueryCondition queryCondition;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public QueryCondition getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(QueryCondition queryCondition) {
		this.queryCondition = queryCondition;
	}

}
