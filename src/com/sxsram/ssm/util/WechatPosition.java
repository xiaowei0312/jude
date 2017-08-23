package com.sxsram.ssm.util;

public class WechatPosition {
	private double latitude;
	private double longitude;
	private double speed;
	private double accuracy;
	
	public WechatPosition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WechatPosition(double mallPos_lat, double mallPos_lnt) {
		this.latitude = mallPos_lat;
		this.longitude = mallPos_lnt;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	@Override
	public String toString() {
		return "WechatPosition [latitude=" + latitude + ", longitude=" + longitude + ", speed=" + speed + ", accuracy="
				+ accuracy + "]";
	}

}
