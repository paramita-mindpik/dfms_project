package com.gis.service;

/**
 * Postgres Service
 * @author mkrout
 **/
import java.util.List;
import java.util.Map;

import com.gis.model.Groups;

public interface LayerService {

	public Map<String, String> getTableMetaData(String table) throws Exception;

	public List<Groups> getLayerGroups() throws Exception;

	public void addGroupName(String groupName) throws Exception;

	public void deleteGroup(String groupId) throws Exception;
	
	public List getGroupList() throws Exception;
}
