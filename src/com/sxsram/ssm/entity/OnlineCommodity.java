package com.sxsram.ssm.entity;

import java.util.Date;
import java.util.List;

public class OnlineCommodity {
	private Integer id; /* int(11) NOT NULL AUTO_INCREMENT */
	private String commodityName; /* varchar(100) Not NULL, -- 名称 */
	private String commodityMainPic;
	private String commodityDetailFileName;
	private Integer commoditySequence;
	
	private Integer onlineCommodityFlag;
	private Date onLineCommodityPutawayTime;
	private Date onLineCommoditySoldoutTime;
	
	private OnlineCommodityType commodityType;
	private List<OnlineCommodityModel> commodityModels;
	
	private Integer commodityTypeId;

	public Integer getCommodityTypeId() {
		return commodityTypeId;
	}

	public void setCommodityTypeId(Integer commodityTypeId) {
		this.commodityTypeId = commodityTypeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOnlineCommodityFlag() {
		return onlineCommodityFlag;
	}

	public void setOnlineCommodityFlag(Integer onlineCommodityFlag) {
		this.onlineCommodityFlag = onlineCommodityFlag;
	}

	public Date getOnLineCommodityPutawayTime() {
		return onLineCommodityPutawayTime;
	}

	public void setOnLineCommodityPutawayTime(Date onLineCommodityPutawayTime) {
		this.onLineCommodityPutawayTime = onLineCommodityPutawayTime;
	}

	public Date getOnLineCommoditySoldoutTime() {
		return onLineCommoditySoldoutTime;
	}

	public void setOnLineCommoditySoldoutTime(Date onLineCommoditySoldoutTime) {
		this.onLineCommoditySoldoutTime = onLineCommoditySoldoutTime;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCommodityMainPic() {
		return commodityMainPic;
	}

	public void setCommodityMainPic(String commodityMainPic) {
		this.commodityMainPic = commodityMainPic;
	}

	public String getCommodityDetailFileName() {
		return commodityDetailFileName;
	}

	public void setCommodityDetailFileName(String commodityDetailFileName) {
		this.commodityDetailFileName = commodityDetailFileName;
	}

	public Integer getCommoditySequence() {
		return commoditySequence;
	}

	public void setCommoditySequence(Integer commoditySequence) {
		this.commoditySequence = commoditySequence;
	}

	public OnlineCommodityType getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(OnlineCommodityType commodityType) {
		this.commodityType = commodityType;
	}

	public List<OnlineCommodityModel> getCommodityModels() {
		return commodityModels;
	}

	public void setCommodityModels(List<OnlineCommodityModel> commodityModels) {
		this.commodityModels = commodityModels;
	}

	@Override
	public String toString() {
		return "OnlineCommodity [id=" + id + ", commodityName=" + commodityName + ", commodityMainPic="
				+ commodityMainPic + ", commodityDetailFileName=" + commodityDetailFileName + ", commoditySequence="
				+ commoditySequence + ", commodityType=" + commodityType + ", commodityModels=" + commodityModels + "]";
	}

}
