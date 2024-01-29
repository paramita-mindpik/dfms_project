package com.gis.model;

import java.io.Serializable;

/**
 * @author User
 *
 */
/**
 * @author User
 *
 */
public class RequestData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String url;

	private String userId;

	private String token;

	private String query;

	private String layer;

	private String tableName;

	private String text;
	
	private int limit;
	
	private String layerName;
	
	private String layerCode;
	
	private String layerSequence;
	
	private String layerActive;
	
	private String actionType;
	
	private String layerId;
	
	private String ac;
	
	private String division;
	
	private String ps;
	
	private String fromDate;

	private String toDate;
	
	private String boothNo;
	
	private String appVersion;
	
	private String mobileView;
	
	private String code;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public String getLayerCode() {
		return layerCode;
	}

	public void setLayerCode(String layerCode) {
		this.layerCode = layerCode;
	}

	public String getLayerSequence() {
		return layerSequence;
	}

	public void setLayerSequence(String layerSequence) {
		this.layerSequence = layerSequence;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getLayerActive() {
		return layerActive;
	}

	public void setLayerActive(String layerActive) {
		this.layerActive = layerActive;
	}

	public String getLayerId() {
		return layerId;
	}

	public void setLayerId(String layerId) {
		this.layerId = layerId;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getBoothNo() {
		return boothNo;
	}

	public void setBoothNo(String boothNo) {
		this.boothNo = boothNo;
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getMobileView() {
		return mobileView;
	}

	public void setMobileView(String mobileView) {
		this.mobileView = mobileView;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}