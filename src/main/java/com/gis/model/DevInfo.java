package com.gis.model;

import java.io.Serializable;

public class DevInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int userid;
	private String imeino;
	private String battery;
	private String screenactive;
	private String applionoff;
	private String mobiledataonoff;
	private String networkname;
	private String divname;
	private String androidversion;
	private String locationdate;
	private double latitude;
	private double longitude;
	private String token;
	private String serverlogtime;
	private int timeinterval;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getImeino() {
		return imeino;
	}
	public void setImeino(String imeino) {
		this.imeino = imeino;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	public String getScreenactive() {
		return screenactive;
	}
	public void setScreenactive(String screenactive) {
		this.screenactive = screenactive;
	}
	public String getApplionoff() {
		return applionoff;
	}
	public void setApplionoff(String applionoff) {
		this.applionoff = applionoff;
	}
	public String getMobiledataonoff() {
		return mobiledataonoff;
	}
	public void setMobiledataonoff(String mobiledataonoff) {
		this.mobiledataonoff = mobiledataonoff;
	}
	public String getNetworkname() {
		return networkname;
	}
	public void setNetworkname(String networkname) {
		this.networkname = networkname;
	}
	public String getDivname() {
		return divname;
	}
	public void setDivname(String divname) {
		this.divname = divname;
	}
	public String getAndroidversion() {
		return androidversion;
	}
	public void setAndroidversion(String androidversion) {
		this.androidversion = androidversion;
	}
	public String getLocationdate() {
		return locationdate;
	}
	public void setLocationdate(String locationdate) {
		this.locationdate = locationdate;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getServerlogtime() {
		return serverlogtime;
	}
	public void setServerlogtime(String serverlogtime) {
		this.serverlogtime = serverlogtime;
	}
	public int getTimeinterval() {
		return timeinterval;
	}
	public void setTimeinterval(int timeinterval) {
		this.timeinterval = timeinterval;
	}
}