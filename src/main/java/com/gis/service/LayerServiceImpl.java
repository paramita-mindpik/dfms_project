package com.gis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gis.model.Groups;

@Service
public class LayerServiceImpl implements LayerService {

	static Log logger = LogFactory.getLog(LayerServiceImpl.class.getName());

	@Autowired
	private JdbcTemplate jdbcTemplatePostgres;
	 
	@Override
	public Map<String, String> getTableMetaData(String table) throws Exception {
		Map<String, String> metaData = new HashMap<String, String>();
		List<Map<String, Object>> tempData = null;
		String sql = "select column_name, data_type from information_schema.columns where table_name=?";
		try {
			tempData = jdbcTemplatePostgres.queryForList(sql, new Object[]{ table });
			
			if(tempData != null && tempData.size()>0){
				for(Map<String, Object> data : tempData){
					metaData.put((String)data.get("column_name"), (String)data.get("data_type"));
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return metaData;
	}

	@Override
	public List<Groups> getLayerGroups() throws Exception {
		String sql = "select * from layer_group order by group_name";
		List<Groups> layerGroups = new ArrayList<Groups>();
		List<Map<String, Object>> tempList;
		Groups group = null;
		try {
			tempList = jdbcTemplatePostgres.queryForList(sql);
			
			for(Map<String, Object> data : tempList){
				group = new Groups();
				group.setGroupId((Integer) data.get("group_id"));
				group.setGroupName((String)data.get("group_name"));
				layerGroups.add(group);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return layerGroups;
	}

	@Override
	public void addGroupName(String groupName) throws Exception {
		String sql = "insert into layer_group (group_name) values (?)"; 
		try {
			jdbcTemplatePostgres.update(sql, new Object[]{groupName});
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void deleteGroup(String groupId) throws Exception {
		try {
			jdbcTemplatePostgres.update("delete from layer_group where group_id=?", new Object[]{Integer.parseInt(groupId)});
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}
	
	@Override
	public List getGroupList() throws Exception {
		String sql = "select * from app_layergroup order by group_name";
		logger.debug(sql);
		return jdbcTemplatePostgres.queryForList(sql);
	}
}