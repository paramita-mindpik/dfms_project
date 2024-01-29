package com.gis.model;

import java.io.Serializable;
import java.util.List;

public class WayPoints implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int gid;

	private String circle_nm;
	private String circle_cod;
	private String district_nm;
	private String district_cod;
	private String division_nm;
	private String division_cod;
	private String range_nm;
	private String range_cod;
	private String beat_nm;
	private String beat_cod;

	private String latitude;
	private String longitude;

	private String mouza;
	private int gisid;
	private String mapuser_id;
	private String compart_no;
	private float waypont_id;
	private String timestamp;
	private String observ;
	private String geom;
	private String token;
	private int userid;
	
	private List<Document> photos;

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getCircle_nm() {
		return circle_nm;
	}

	public void setCircle_nm(String circle_nm) {
		this.circle_nm = circle_nm;
	}

	public String getCircle_cod() {
		return circle_cod;
	}

	public void setCircle_cod(String circle_cod) {
		this.circle_cod = circle_cod;
	}

	public String getDistrict_nm() {
		return district_nm;
	}

	public void setDistrict_nm(String district_nm) {
		this.district_nm = district_nm;
	}

	public String getDistrict_cod() {
		return district_cod;
	}

	public void setDistrict_cod(String district_cod) {
		this.district_cod = district_cod;
	}

	public String getDivision_nm() {
		return division_nm;
	}

	public void setDivision_nm(String division_nm) {
		this.division_nm = division_nm;
	}

	public String getDivision_cod() {
		return division_cod;
	}

	public void setDivision_cod(String division_cod) {
		this.division_cod = division_cod;
	}

	public String getRange_nm() {
		return range_nm;
	}

	public void setRange_nm(String range_nm) {
		this.range_nm = range_nm;
	}

	public String getRange_cod() {
		return range_cod;
	}

	public void setRange_cod(String range_cod) {
		this.range_cod = range_cod;
	}

	public String getBeat_nm() {
		return beat_nm;
	}

	public void setBeat_nm(String beat_nm) {
		this.beat_nm = beat_nm;
	}

	public String getBeat_cod() {
		return beat_cod;
	}

	public void setBeat_cod(String beat_cod) {
		this.beat_cod = beat_cod;
	}

	public String getMouza() {
		return mouza;
	}

	public void setMouza(String mouza) {
		this.mouza = mouza;
	}

	public int getGisid() {
		return gisid;
	}

	public void setGisid(int gisid) {
		this.gisid = gisid;
	}

	public String getMapuser_id() {
		return mapuser_id;
	}

	public void setMapuser_id(String mapuser_id) {
		this.mapuser_id = mapuser_id;
	}

	public String getCompart_no() {
		return compart_no;
	}

	public void setCompart_no(String compart_no) {
		this.compart_no = compart_no;
	}

	public float getWaypont_id() {
		return waypont_id;
	}

	public void setWaypont_id(float waypont_id) {
		this.waypont_id = waypont_id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getObserv() {
		return observ;
	}

	public void setObserv(String observ) {
		this.observ = observ;
	}

	public String getGeom() {
		return geom;
	}

	public void setGeom(String geom) {
		this.geom = geom;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public List<Document> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Document> photos) {
		this.photos = photos;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}