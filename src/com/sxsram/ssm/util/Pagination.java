package com.sxsram.ssm.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;

public class Pagination {
	private Integer numPerPage;
	private Integer currentPage;
	private Integer totalPage;
	private Map orderByCols = new HashMap<String, String>();

	private Integer limitStart;
	private Integer limitNum;

	public Integer getLimitStart() {
		if (numPerPage != null && currentPage != null)
			return (currentPage - 1) * numPerPage;
		return null;
	}

	public Integer getLimitNum() {
		return numPerPage;
	}

	public String getOrderByCols() {
		if (orderByCols.size() == 0)
			return null;
		StringBuilder sBuilder = new StringBuilder();

		Set<Entry> entrys = orderByCols.entrySet();
		for (Entry entry : entrys) {
			sBuilder.append(entry.getKey() + " " + entry.getValue());
			sBuilder.append(",");
		}
		return sBuilder.substring(0, sBuilder.length() - 1).toString();
	}

	public Pagination() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pagination(Integer numPerPage, Integer currentPage, Integer totalPage, Map orderByCols) {
		super();
		this.numPerPage = numPerPage;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.orderByCols = orderByCols;
	}

	public Pagination(Integer numPerPage, Integer currentPage, Integer totalPage, String orderByColsJson) {
		super();
		this.numPerPage = numPerPage;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		setOrderByCols(orderByColsJson);
	}

	public void setOrderByCols(Map orderByCols) {
		this.orderByCols = orderByCols;
	}

	public void setOrderByCols(String orderByColsJson) {
		Gson gson = new Gson();
		orderByCols = gson.fromJson(orderByColsJson, Map.class);
	}

	public Integer getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		return "Pagination [numPerPage=" + numPerPage + ", currentPage=" + currentPage + ", totalPage=" + totalPage
				+ ", orderByCols=" + getOrderByCols() + "]";
	}

	public static void main(String[] args) {
		Pagination pagination = new Pagination();
		pagination.currentPage = 0;
		pagination.numPerPage = 5;
		pagination.totalPage = 10;
		// HashMap<String, String> orderByCols = new HashMap<>();
		// orderByCols.put("name", "desc");
		// orderByCols.put("price", "asc");
		// orderByCols.put("num", "desc");
		// pagination.orderByCols = orderByCols;
		// pagination.setOrderByCols("{\"name\":\"desc\",\"price\":\"asc\",\"num\":\"desc\"}");
		pagination.setOrderByCols("{name:desc,price:asc,num:desc}");
		// pagination.setWhereCond("{\"age\":\">,23\",\"name\":\"like,zhangsan\"}");

		// System.out.println(new Pagination(8, 1, null,
		// "{\"commodityPutawayTime\":\"desc\"}").toString());
		System.out.println(pagination);
	}
}
