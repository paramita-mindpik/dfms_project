package com.gis.model;

import java.io.Serializable;

public class Layer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int layerId;
	private String layerCode;
	private String layerName;
	private String layerSequence;
	private String editable;
	private String active;
	private String actionType;
	private String mobileView;
	private String groupName;
	private String groupId;

	public int getLayerId() {
		return layerId;
	}

	public void setLayerId(int layerId) {
		this.layerId = layerId;
	}

	public String getLayerCode() {
		return layerCode;
	}

	public void setLayerCode(String layerCode) {
		this.layerCode = layerCode;
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public String getLayerSequence() {
		return layerSequence;
	}

	public void setLayerSequence(String layerSequence) {
		this.layerSequence = layerSequence;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getMobileView() {
		return mobileView;
	}

	public void setMobileView(String mobileView) {
		this.mobileView = mobileView;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
}
