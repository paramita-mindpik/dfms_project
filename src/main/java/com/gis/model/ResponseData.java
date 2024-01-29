package com.gis.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author MUKESH ROUT
 *
 */
public class ResponseData implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean permission;

	private String error;

	private JSONArray data;

	private JSONObject features;

	private String focus;

	private JSONArray coordinates;

	private int total;

	private int active;

	private int inactive;

	private boolean logout;

	private String layer;
	
	private List<Map<String, Object>> regUsers;

	private List<Map<String, Object>> notification;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isPermission() {
		return permission;
	}

	public String getError() {
		return error;
	}

	public JSONArray getData() {
		return data;
	}

	public JSONObject getFeatures() {
		return features;
	}

	public String getFocus() {
		return focus;
	}

	public JSONArray getCoordinates() {
		return coordinates;
	}

	public int getTotal() {
		return total;
	}

	public boolean isLogout() {
		return logout;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setData(JSONArray data) {
		this.data = data;
	}

	public void setFeatures(JSONObject features) {
		this.features = features;
	}

	public void setFocus(String focus) {
		this.focus = focus;
	}

	public void setCoordinates(JSONArray coordinates) {
		this.coordinates = coordinates;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setLogout(boolean logout) {
		this.logout = logout;
	}

	public int getActive() {
		return active;
	}

	public int getInactive() {
		return inactive;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public void setInactive(int inactive) {
		this.inactive = inactive;
	}

	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public List<Map<String, Object>> getRegUsers() {
		return regUsers;
	}

	public void setRegUsers(List<Map<String, Object>> regUsers) {
		this.regUsers = regUsers;
	}

	public List<Map<String, Object>> getNotification() {
		return notification;
	}

	public void setNotification(List<Map<String, Object>> notification) {
		this.notification = notification;
	}
}