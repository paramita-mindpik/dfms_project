package com.gis.model;

import java.io.Serializable;

public class Groups implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int groupId;
	private String groupName;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
