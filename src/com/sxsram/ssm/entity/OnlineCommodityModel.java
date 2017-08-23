package com.sxsram.ssm.entity;

import java.util.Date;

public class OnlineCommodityModel {
	private Integer id; /* int(11) NOT NULL AUTO_INCREMENT */
	private String commodityModel;
	private Double commodityPrice; /* double(12,2), -- 价格 */
	private String commodityDesc; /* varchar(1024), -- 简介 */
	private String commodityService; /* varchar(128), -- 服务 */
	private Integer commodityRepertory; /* int(11) default 0, -- 库存 */
	private Integer commodityFlag; /*
									 * int(11) default 0, -- 商品状态 0 上架 1 下架 2 删除
									 */
	private Integer commoditySalesVolume; /* int（11） defalut 0 ， -- 销量 */
	private Integer commoditySequence; /* ` int(11) DEFAULT NULL, */
	// private Integer commodityTypeId; /* int（11）, */
	private Date commodityPutawayTime; /* datetime, */
	private Date commoditySoldoutTime; /* datetime, */

	private String commoditySmallPic1;
	private String commoditySmallPic2;
	private String commoditySmallPic3;
	private String commoditySmallPic4;
	private String commoditySmallPic5;
	
	private Integer isDefaultModel;
	private OnlineCommodity onlineCommodity;
	private Integer commodityId;
	
	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Integer getIsDefaultModel() {
		return isDefaultModel;
	}

	public void setIsDefaultModel(Integer isDefaultModel) {
		this.isDefaultModel = isDefaultModel;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommodityModel() {
		return commodityModel;
	}

	public void setCommodityModel(String commodityModel) {
		this.commodityModel = commodityModel;
	}

	public Double getCommodityPrice() {
		return commodityPrice;
	}

	public void setCommodityPrice(Double commodityPrice) {
		this.commodityPrice = commodityPrice;
	}

	public String getCommodityDesc() {
		return commodityDesc;
	}

	public void setCommodityDesc(String commodityDesc) {
		this.commodityDesc = commodityDesc;
	}

	public String getCommodityService() {
		return commodityService;
	}

	public void setCommodityService(String commodityService) {
		this.commodityService = commodityService;
	}

	public Integer getCommodityRepertory() {
		return commodityRepertory;
	}

	public void setCommodityRepertory(Integer commodityRepertory) {
		this.commodityRepertory = commodityRepertory;
	}

	public Integer getCommodityFlag() {
		return commodityFlag;
	}

	public void setCommodityFlag(Integer commodityFlag) {
		this.commodityFlag = commodityFlag;
	}

	public Integer getCommoditySalesVolume() {
		return commoditySalesVolume;
	}

	public void setCommoditySalesVolume(Integer commoditySalesVolume) {
		this.commoditySalesVolume = commoditySalesVolume;
	}

	public Integer getCommoditySequence() {
		return commoditySequence;
	}

	public void setCommoditySequence(Integer commoditySequence) {
		this.commoditySequence = commoditySequence;
	}

	public Date getCommodityPutawayTime() {
		return commodityPutawayTime;
	}

	public void setCommodityPutawayTime(Date commodityPutawayTime) {
		this.commodityPutawayTime = commodityPutawayTime;
	}

	public Date getCommoditySoldoutTime() {
		return commoditySoldoutTime;
	}

	public void setCommoditySoldoutTime(Date commoditySoldoutTime) {
		this.commoditySoldoutTime = commoditySoldoutTime;
	}

	public String getCommoditySmallPic1() {
		return commoditySmallPic1;
	}

	public void setCommoditySmallPic1(String commoditySmallPic1) {
		this.commoditySmallPic1 = commoditySmallPic1;
	}

	public String getCommoditySmallPic2() {
		return commoditySmallPic2;
	}

	public void setCommoditySmallPic2(String commoditySmallPic2) {
		this.commoditySmallPic2 = commoditySmallPic2;
	}

	public String getCommoditySmallPic3() {
		return commoditySmallPic3;
	}

	public void setCommoditySmallPic3(String commoditySmallPic3) {
		this.commoditySmallPic3 = commoditySmallPic3;
	}

	public String getCommoditySmallPic4() {
		return commoditySmallPic4;
	}

	public void setCommoditySmallPic4(String commoditySmallPic4) {
		this.commoditySmallPic4 = commoditySmallPic4;
	}

	public String getCommoditySmallPic5() {
		return commoditySmallPic5;
	}

	public void setCommoditySmallPic5(String commoditySmallPic5) {
		this.commoditySmallPic5 = commoditySmallPic5;
	}

	public OnlineCommodity getOnlineCommodity() {
		return onlineCommodity;
	}

	public void setOnlineCommodity(OnlineCommodity onlineCommodity) {
		this.onlineCommodity = onlineCommodity;
	}

}
