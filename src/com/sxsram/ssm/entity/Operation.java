package com.sxsram.ssm.entity;

public class Operation {
	private Integer id;
	private String name;
	private String url;
	private String imgUrl;
	private String style;
	private Integer seqnum;
	private boolean isMenu;
	private Integer parentId;

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Integer getSeqnum() {
		return seqnum;
	}

	public void setSeqnum(Integer seqnum) {
		this.seqnum = seqnum;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getMenu() {
		return isMenu ? 1 : 0;
	}

//	public boolean isMenu() {
//		return isMenu;
//	}

	public void setMenu(int isMenu) {
		this.isMenu = isMenu == 1 ? true : false;
	}

	public void setMenu(boolean isMenu) {
		this.isMenu = isMenu;
	}

	@Override
	public String toString() {
		return "Operation [id=" + id + ", name=" + name + ", url=" + url + ", imgUrl=" + imgUrl + ", style=" + style
				+ ", seqnum=" + seqnum + ", isMenu=" + isMenu + ", parentId=" + parentId + "]";
	}

}
