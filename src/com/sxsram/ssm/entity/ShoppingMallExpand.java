package com.sxsram.ssm.entity;

public class ShoppingMallExpand extends ShoppingMall implements Comparable {
	private UserExpand user;
	private UserExpand proxyUser;
	private double distance;
	private ShoppingMallType mallType;
	
	public ShoppingMallType getMallType() {
		return mallType;
	}

	public void setMallType(ShoppingMallType mallType) {
		this.mallType = mallType;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public UserExpand getUser() {
		return user;
	}

	public void setUser(UserExpand user) {
		this.user = user;
	}

	public UserExpand getProxyUser() {
		return proxyUser;
	}

	public void setProxyUser(UserExpand proxyUser) {
		this.proxyUser = proxyUser;
	}

	@Override
	public String toString() {
		return "ShoppingMallExpand [user=" + user + ", distance=" + distance + ", getId()=" + getId()
				+ ", getMallPos_lat()=" + getMallPos_lat() + ", getMallPos_lnt()=" + getMallPos_lnt()
				+ ", getMallName()=" + getMallName() + ", getMallDesc()=" + getMallDesc() + ", getLocked()="
				+ getLocked() + ", getMallMainPicUrl()=" + getMallMainPicUrl() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof ShoppingMallExpand){
			ShoppingMallExpand mallExpand = (ShoppingMallExpand) o;
			if(this.distance >mallExpand.distance){
				return 1;
			}else{
				return 0;
			}
		}
		return 0;
	}
}
