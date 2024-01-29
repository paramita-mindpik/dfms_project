package com.gis.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gis.agent.ApiAgent;
import com.gis.model.CDDRB;
import com.gis.model.DevInfo;
import com.gis.model.Document;
import com.gis.model.Layer;
import com.gis.model.ResponseData;
import com.gis.model.User;
import com.gis.model.WayPoints;
import com.gis.util.Encrypt;
import com.gis.util.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class PostgresServiceImpl implements PostgresService {

	static Log logger = LogFactory.getLog(PostgresServiceImpl.class.getName());

	@Autowired
	private JdbcTemplate jdbcTemplatePostgres;

	@Autowired
	ApiAgent agent;
	

	public boolean reloginRequired(HttpServletRequest request) {
		boolean reloginRequired = false;
		HttpSession session = request.getSession(false);
		//Code commented by abc
		try {
			if (session.getAttribute("token") == null) {
				reloginRequired = true;
				request.setAttribute("error", "Session expired. Please login again");
			}
		} catch (Exception e) {
			logger.error("Session Expired :" + e);
			reloginRequired = true;
		}
		return reloginRequired;
	}

	public int executeQuery(String query) throws Exception {
		logger.debug("sql : " + query);
		return jdbcTemplatePostgres.update(query);
	}
	
	public int executeQueryGis(String query) throws Exception {
		logger.debug("sql : " + query);
		return jdbcTemplatePostgres.update(query);
	}

	public int[] executeQueryBatch(String query[]) throws Exception {
		logger.debug("sql : " + query);
		return jdbcTemplatePostgres.batchUpdate(query);
	}

	public String fetch(String query) throws Exception {
		logger.debug("sql : " + query);
		return jdbcTemplatePostgres.queryForObject(query, String.class);
	}
	
	public String fetchGis(String query) throws Exception {
		logger.debug("sql : " + query);
		return jdbcTemplatePostgres.queryForObject(query, String.class);
	}

	public List getList(String query) throws Exception {
		logger.debug("sql : " + query);
		return jdbcTemplatePostgres.queryForList(query);
	}
	
	public List getTableDataGis(String table){
		String sql = "select ST_AsGeoJSON(ST_Transform(geom,4326)) transformed_data ,ST_AsGeoJSON(ST_Transform(geom,3857)) map_data, * from "+table;
		logger.debug("sql : " + sql);
		return jdbcTemplatePostgres.queryForList(sql);
	}
	
	public String select(String query){
		logger.debug("sql : " + query);
		String value = "";
		try{
			value  = jdbcTemplatePostgres.queryForObject(query, String.class);
		}catch(Exception e){}
		return  value;
	}
	
	public List<Map<String, Object>> getListWithParameter(String query, String... arg) throws Exception {
		logger.debug("getLayerPointsWithArg " + query + " total arg " + arg.length);

		Object[] object = new Object[arg.length];
		for (int a = 0; a < object.length; a++) {
			object[a] = arg[a];
			logger.debug(arg[a]);
		}
		logger.debug("query "+query);
		
		return jdbcTemplatePostgres.queryForList(query, object);
	}

	public User login(String username, String password , String platform) {
		logger.debug("username : " + username + " password : " + password);
		String sql = "select * from app_users u left join app_version a on a.vid =u.vid  where (u.user_name = ? or u.mobile=?) and u.password =?";
		User user = new User();
		user.setUserId(0);
		try {
			long mobile = -1;
			Pattern pattern = Pattern.compile("[0-9]*");
			if(pattern.matcher(username).matches())
				mobile = Long.parseLong(username);
			
			logger.debug("mobile " +mobile);
			
			user = (User) jdbcTemplatePostgres.queryForObject(sql, new Object[] { username,mobile, password }, new BeanPropertyRowMapper(User.class));
			int userId = user.getUserId();
			if(platform.equalsIgnoreCase("APP")){
				String isPresent = null;
				try{
					isPresent = fetch("select token from app_token where user_id ="+user.getUserId()+" and platform ="+StringUtils.checkString(platform));	
				}
				catch (Exception e) {}
				
				if(isPresent!=null){
					user = new User();
					user.setActive("I");
					user.setUserId(userId);
				}else{
					user.setToken(getNewToken(user.getUserId(),platform));
				}
			}else{
				user.setToken(getNewToken(user.getUserId(),platform));
			}
			
		} catch (Exception e) {
			logger.error("Exception while login " + e.getMessage());
		}
		return user;
	}

	@Override
	public Map<String, String> getAllActiveLayers() {
		Map<String, String> allLayerList = new LinkedHashMap<String, String>();
		try {
			String sql = "select layer_code,layer_name from app_alllayers where active='Y' ORDER by layer_name";
			List<Map<String, Object>> layers = jdbcTemplatePostgres.queryForList(sql);
			for (Map<String, Object> layersName : layers) {
				allLayerList.put(layersName.get("layer_code").toString(), layersName.get("layer_name").toString());
			}

		} catch (Exception e) {
			logger.error("Exception while getting all active layer " + e.getMessage());
		}
		return allLayerList;
	}
	
	@Override
	public List getAllActiveLayersList() {
		List allLayerList = new LinkedList();
		try {
			String sql = "select layer_code,layer_name from app_alllayers where active='Y' ORDER by layer_name";
			allLayerList = jdbcTemplatePostgres.queryForList(sql);
		} catch (Exception e) {
			logger.error("Exception while getting all layer list" + e.getMessage());
		}
		return allLayerList;
	}
	
	@Override
	public List getAllLayers() {
		List allLayerList = new LinkedList();
		try {
			String sql = "select * from app_alllayers ORDER by layer_name";
			allLayerList = jdbcTemplatePostgres.queryForList(sql);
		} catch (Exception e) {
			logger.error("Exception while getting all layer " + e.getMessage());
		}
		return allLayerList;
	}
	
	@Override
	public Map<String, String> getAllLayersDropdown() {
		Map<String, String> allLayerList = new LinkedHashMap<String, String>();
		try {
			String sql = "select layer_id,layer_name from app_alllayers where active='Y' ORDER by layer_name";
			List<Map<String, Object>> layers = jdbcTemplatePostgres.queryForList(sql);
			for (Map<String, Object> layersName : layers) {
				allLayerList.put(layersName.get("layer_id").toString(), layersName.get("layer_name").toString());
			}

		} catch (Exception e) {
			logger.error("Exception while getting all layer " + e.getMessage());
		}
		return allLayerList;
	}
	
	@Override
	public void addNewUser(User user) {
		try {
			String sql = "INSERT INTO app_users(user_name, password, permission, isadmin,active,mobile,organisation,designation)VALUES (?, ?, ?, ?,'N',?,?,?)";
			jdbcTemplatePostgres.update(sql,
					new Object[] { user.getUserName(), Encrypt.encrypt(user.getPassword()), user.getPermission(), user.getIsadmin(),
					Long.parseLong(user.getMobile()), user.getOrganisation(), user.getDesignation()	
			});

			for (int a = 0; a < user.getLayer().size(); a++) {
				sql = "INSERT INTO user_layers(user_id, layer_id, editable)VALUES ((select max(user_id) from app_users), ?, 'Y')";
				int layerId = Integer.parseInt(user.getLayer().get(a).toString());
				jdbcTemplatePostgres.update(sql, new Object[] { layerId });
			}

		} catch (Exception e) {
			logger.error("Exception while adding user " + e.getMessage());
		}
	}
	
	@Override
	public boolean findUser(String userName , String mobileNumber) {

		String sql = "select * from app_users where (user_name=? or mobile =?) limit 1";
		try {
			long mobile = Long.parseLong(mobileNumber);
			
			jdbcTemplatePostgres.queryForMap(sql, new Object[] { userName ,mobile });
			logger.debug("User name exists");
			return true;
		} catch (Exception e) {
			logger.error("User name doesn't exist");
			return false;
		}
	}
	
	@Override
	public String getNewToken(int userId , String platform) {
		String result = null;
		try {
			UUID uuid = Encrypt.generateUUID();
			String token = String.valueOf(uuid);
			String sql = "INSERT INTO app_token (TOKEN,CREATED,LAST_ACCESS,USER_ID,platform) VALUES('" + token.trim()
					+ "',current_timestamp,current_timestamp," + userId + ","+StringUtils.checkString(platform)+")";
			executeQuery(sql);
			result = token;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return result;
	}

	@Override
	public void updateTokenTime(String token) {
		try {
			String sql = "UPDATE app_token SET LAST_ACCESS=current_timestamp WHERE token = '" + token + "'";
			executeQuery(sql);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Override
	public void insertUpdateUserLog(User user) {
		String query = "SELECT log_id FROM app_userlog WHERE user_name = '" + user.getUserName() + "'";
		try {
			String log_id = null;
			try {
				log_id = fetch(query);
			} catch (EmptyResultDataAccessException er) {
				logger.error("User " +  user.getUserName() + " log not available. Creating log ....");
			}
			query = "INSERT INTO app_userlog (user_id,user_name, last_login,platform) VALUES ("+user.getUserId() +",'" +  user.getUserName() + "', current_timestamp,"+StringUtils.checkString(user.getPlatform())+")";
			if (log_id != null)
				query = "UPDATE app_userlog SET last_login = current_timestamp,platform="+StringUtils.checkString(user.getPlatform())+" WHERE log_id=" + log_id;

			executeQuery(query);
		} catch (Exception e) {
			logger.error("EXCEPTION insertUpdateUserLog()" + e.getMessage());
		}
	}


	@Override
	public boolean validateToken(String token) {
		boolean flag = false;
		try {
			String superAdmin = Encrypt.encrypt(StringUtils.getSeperAdmin());
			if (token != null && !token.equals(superAdmin)) {
				String sql = "SELECT token_id FROM app_token WHERE token = '" + token + "'";
				String output = fetch(sql);
				if (output != null) {
					updateTokenTime(token);
					flag = true;
				}
			} else if (token != null && token.equals(superAdmin)) {
				flag = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return flag;
	}
	
	public boolean InvalidateToken(String token){
		boolean flag = false;
		try {
			if (token != null) {
				String sql = "DELETE FROM app_token WHERE token = " + StringUtils.checkString(token);
				int result = executeQuery(sql);
				logger.debug(result);
				if(result > 0)
					flag = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return flag;
	}
	
	@Override
	public Map<String, String[]> getDatabaseMetaData(String tablename) {
		Map<String, String[]> metaDataList = new LinkedHashMap<String, String[]>();
		try {
			ResultSet rsColumns = null;
			DatabaseMetaData meta = getConnection().getMetaData();
			rsColumns = meta.getColumns(null, null, tablename, null);
			while (rsColumns.next()) {
				String[] data = new String[2];
				data[0] = rsColumns.getString("TYPE_NAME");
				data[1] = StringUtils.i2s(rsColumns.getInt("COLUMN_SIZE"));
				metaDataList.put(rsColumns.getString("COLUMN_NAME"), data);
			}
		} catch (Exception e) {
			logger.error("Exception while getting database meta data for table :" + tablename + " Error :" + e.getMessage());
		}
		return metaDataList;

	}

	@Override
	public Connection getConnection() throws SQLException {
		return jdbcTemplatePostgres.getDataSource().getConnection();
	}
	
	public boolean addDevInfo(DevInfo devInfo){
		boolean result = false;
		try {
			String sql = "INSERT INTO app_locationdetails (locationdate,latitude, longitude, user_id, mobnetwork,battry, timeintervell,created) VALUES (" 
					+ StringUtils.checkString(devInfo.getLocationdate()) + ", "
					+ devInfo.getLatitude() + ", "
					+ devInfo.getLongitude() + ", "
					+ devInfo.getUserid()+ ", "
					+ StringUtils.checkString(devInfo.getNetworkname()) + ", "
					+ StringUtils.checkString(devInfo.getBattery()) + ", "
					+ devInfo.getTimeinterval() + ",CURRENT_TIMESTAMP"
					+ ")";
			System.out.println(sql);
			logger.debug(sql);
			executeQuery(sql);
			result = true;
		} catch (Exception e) {
			logger.error("Error in addDevInfo of PostgresController: ", e);
		}
		return result;
	}
	
	public boolean checkIMEI(DevInfo devInfo){
		boolean result = false;
		try {
			fetch("select imei from app_users where imei = "+ StringUtils.checkString(devInfo.getImeino()));
			result = true;
		} catch (Exception e) {
			logger.error("Error in checkIMEI of PostgresController: ", e);
		}
		return result;
	}
	
	@Override
	public ResponseData livelocation(List<Map<String, Object>> pointData , int id) {
		logger.debug("Getting points for livelocation");
		ResponseData responseData = new ResponseData();	
		Map<String, String> activeUserList = new LinkedHashMap<>();
		Map<String, String> inactiveUserList = new LinkedHashMap<>();
		logger.debug("pointData " + pointData.size());
		try {
			String sql = null;
			if (pointData.size() > 0) {
				/* FEATURE DECLARATION */
				JSONObject features = new JSONObject();
				features.put("type", "FeatureCollection");
				JSONObject crs = new JSONObject();
				JSONObject crsProperties = new JSONObject();
				crs.put("type", "name");
				crsProperties.put("name", "EPSG:3857");

				crs.put("properties", crsProperties);

				JSONArray featuresArray = new JSONArray();
				List<String> meanPointCoordinates = new ArrayList<String>();

				for (int a = 0; a < pointData.size(); a++) {
					Map<String, Object> layerData = pointData.get(a);

					String latLongZoom = "[" + layerData.get("longitude").toString() + "," + layerData.get("latitude").toString() + "]" ;
					String latLong = covert(layerData.get("latitude").toString(), layerData.get("longitude").toString());
					logger.debug("latLong " + latLong);

					String userid = layerData.get("user_id").toString().toUpperCase();
					String time = layerData.get("locationdate").toString();
					String batteryPer =  layerData.get("battry").toString();
					String networkname =  layerData.get("mobnetwork").toString();
					String username =  layerData.get("user_name").toString().toUpperCase();
					
					JSONObject jsonPointFeature = new JSONObject();
					JSONObject jsonPointProperties = new JSONObject();

					jsonPointProperties.put("style", "Live");

					String active = "0";//layerData.get("difference").toString();

					String locTime = time.split(" ")[1];
					logger.debug("active :::::::: "+active);
					String blink = "Blink";
					if (Integer.parseInt(active) > 30) {
						active = "inactive";
						inactiveUserList.put(" <a class='icon-blue' href='#' onclick='locateOnMap("+latLongZoom+")'> "+ username +"</a>", locTime);
						blink = "";
					} else {
						activeUserList.put(" <a class='icon-blue' href='#' onclick='locateOnMap("+latLongZoom+")'> "+ username +"</a>", locTime);
						active = "active";
					}

					StringBuffer sb = new StringBuffer(
							"<table class='table table-bordered' id='propTable'><thead data-toggle='collapse' data-target='.atrbody'><tr class='header'><th colspan='2' align='center' class='"
									+ active.toUpperCase() + "'><span class='fa fa-circle " + blink
									+ "'></span> " + active.toUpperCase()
									+ "</th></tr></thead><tbody id='propTableBody' class='atrbody show'>"
									+ "<tr><td style='width: 30%;'> Date & Time </td><td> " + time +"</td></tr>"
									+ "<tr><td>Name </td><td>" + username + "</td></tr>"
									+ "<tr><td>Battery</td><td> "+batteryPer+"</td></tr>"
									//+ "<tr><td>Android Version</td><td> "+androidver+"</td></tr>"
									//+ "<tr><td>Mobile Data</td><td> "+mobiledataonoff+"</td></tr>"
									+ "<tr><td>Network Name</td><td> "+networkname+"</td></tr>"
									//+ "<tr><td>Screen Active</td><td> "+screenactive+"</td></tr>"
									//+ "<tr><td>Application on/off</td><td> "+applionoff+"</td></tr>"
									//+ "<tr><td>Device Name</td><td> "+divname+"</td></tr>"
									//+ "<tr><td>Organisation</td><td> "+organisation+"</td></tr>"
									//+ "<tr><td>Designation</td><td> "+designation+"</td></tr>"
									+ "<tr style='text-align: center;'><td colspan='2'>");
									
							sb.append(" </td></tr></tbody></table>");

					/* POINT ONHOVER NAME */
					jsonPointProperties.put("name", sb.toString());

					jsonPointProperties.put("username", username);
					jsonPointProperties.put("count", layerData.get("user_id").toString());
					jsonPointProperties.put("status", active);
					jsonPointProperties.put("style", "Live");
					
					JSONObject jsonPointGeometry = new JSONObject();
					jsonPointGeometry.put("type", "Point");
					jsonPointGeometry.put("coordinates", latLong);
					jsonPointFeature.put("type", "Feature");
					jsonPointFeature.put("geometry", jsonPointGeometry);
					jsonPointFeature.put("properties", jsonPointProperties);
					featuresArray.add(jsonPointFeature);

					meanPointCoordinates.add(latLong);
				}
				responseData.setTotal(featuresArray.size());

				features.put("crs", crs);
				features.put("features", featuresArray);
				features.put("activeuser", activeUserList);
				features.put("inactiveuser", inactiveUserList);
				responseData.setFeatures(features);

				JSONArray pointCoordinates = new JSONArray();
				JSONObject pointLatLong = new JSONObject();
				pointLatLong.put("meanpoints", meanPointCoordinates);
				pointCoordinates.add(pointLatLong);
				responseData.setCoordinates(pointCoordinates);
			}
			
			sql = "select * from app_users where mobile <> 0 and active='N'";
			List<Map<String, Object>> regList = new ArrayList<>();
			try{
				regList = getList(sql);
			}catch(Exception e){
				logger.debug("Registered "+e.getMessage());
			}
			
			responseData.setRegUsers(regList);
			responseData.setActive(activeUserList.size());
			responseData.setInactive(inactiveUserList.size());
			responseData.setPermission(true);
			responseData.setError("No Error");

		} catch (Exception e) {
			responseData.setPermission(false);
			responseData.setError(e.getMessage());
			logger.error("Exception : " + e.getMessage());
		}
		return responseData;
	}
	
	@Override
	public boolean addUpdateLayerMapping(Layer layer){
		boolean result = false;
		try {
			String actionType = layer.getActionType();
			
			String sql = null;
			if(actionType.equalsIgnoreCase("ADD")){
				sql = " INSERT INTO app_alllayers (layer_code, layer_name, layer_sequence, active,mobile_view,group_id) VALUES( " 
						+ StringUtils.checkString(layer.getLayerCode()) + ", "
						+ StringUtils.checkString(layer.getLayerName()) + ", "
						+ StringUtils.checkString(layer.getLayerSequence()) + ", "
						+ StringUtils.checkString(layer.getActive()) + ", "
						+ StringUtils.checkString(layer.getMobileView()) + ",1)";
			}else if(actionType.equalsIgnoreCase("UPDATE")){
			sql = "UPDATE app_alllayers SET layer_code="+StringUtils.checkString(layer.getLayerCode())
					+", layer_name="+StringUtils.checkString(layer.getLayerName())
					+", layer_sequence="+StringUtils.checkString(layer.getLayerSequence())
					+", active="+StringUtils.checkString(layer.getActive())
					+", mobile_view="+StringUtils.checkString(layer.getMobileView())
					+" WHERE layer_id= "+layer.getLayerId();	
			}
			logger.debug(sql);
			System.out.println(sql);
			if(sql != null){
				executeQuery(sql);
				result = true;
			}
		} catch (Exception e) {
			logger.error("Error in addDevInfo of PostgresController: ", e);
		}
		return result;
	}
	
	@Override
	public Map<String, String> getAllCircle() {
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		try{
			String sql = "select distinct(circle_cod) as code,circle_nm as value from cir_div_dis_ran_beat order by 1";
			//List<CDDRB> outputData = jdbcTemplatePostgres.query( sql,new BeanPropertyRowMapper(CDDRB.class));
			for (int a=0; a<1;a++) {
				//CDDRB object = outputData.get(a);
				dataMap.put("CENTRAL","CENTRAL");
			}
		} catch (Exception e) {
			logger.error("Exception while getting all layer " + e.getMessage());
		}
		return dataMap;
	}
	
	@Override
	public Map<String, String> getDistrict(String circleCode) {
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		try{
			String sql = "select distinct(district_cod) as code,district_nm as value from cir_div_dis_ran_beat where circle_cod ="+circleCode.toLowerCase()+" order by 1";
			List<CDDRB> outputData = jdbcTemplatePostgres.query( sql,new BeanPropertyRowMapper(CDDRB.class));
			for (int a=0; a<outputData.size();a++) {
				CDDRB object = outputData.get(a);
				dataMap.put(object.getCode(), object.getValue());
			}
		} catch (Exception e) {
			logger.error("Exception while getting all layer " + e.getMessage());
		}
		return dataMap;
	}
	
	@Override
	public Map<String, String> getDivision(String districtCode) {
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		try{
			String sql = "select distinct(division_cod) as code,division_nm as value from cir_div_dis_ran_beat where district_cod ="+districtCode.toLowerCase()+" order by 1";
			List<CDDRB> outputData = jdbcTemplatePostgres.query( sql,new BeanPropertyRowMapper(CDDRB.class));
			for (int a=0; a<outputData.size();a++) {
				CDDRB object = outputData.get(a);
				dataMap.put(object.getCode(), object.getValue());
			}
		} catch (Exception e) {
			logger.error("Exception while getting all layer " + e.getMessage());
		}
		return dataMap;
	}
	
	@Override
	public Map<String, String> getRange(String divisionCode) {
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		try{
			String sql = "select distinct(range_cod) as code,range_nm as value from cir_div_dis_ran_beat where division_cod ="+divisionCode.toLowerCase()+" order by 1";
			List<CDDRB> outputData = jdbcTemplatePostgres.query( sql,new BeanPropertyRowMapper(CDDRB.class));
			for (int a=0; a<outputData.size();a++) {
				CDDRB object = outputData.get(a);
				dataMap.put(object.getCode(), object.getValue());
			}
		} catch (Exception e) {
			logger.error("Exception while getting all layer " + e.getMessage());
		}
		return dataMap;
	}
	
	@Override
	public Map<String, String> getBeat(String rangeCode) {
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		try{
			String sql = "select distinct(beat_cod) as code,beat_nm as value from cir_div_dis_ran_beat where range_cod ="+rangeCode.toLowerCase()+" order by 1";
			List<CDDRB> outputData = jdbcTemplatePostgres.query( sql,new BeanPropertyRowMapper(CDDRB.class));
			for (int a=0; a<outputData.size();a++) {
				CDDRB object = outputData.get(a);
				dataMap.put(object.getCode(), object.getValue());
			}
		} catch (Exception e) {
			logger.error("Exception while getting all layer " + e.getMessage());
		}
		return dataMap;
	}


	@Override
	public boolean uploadAndInsert(Document document) throws Exception{
		boolean result = false;
		String uploadLoaction = agent.getPropertyValue("UPLOAD_LOCATION") + File.separator +"waypoint"+ File.separator ;
		String fileName = StringUtils.getTodayDateTime() + document.getFiles().getOriginalFilename();
		
		File file = new File(uploadLoaction + fileName );
		MultipartFile multipartFile = document.getFiles();
		multipartFile.transferTo(file);

		String sql = " INSERT INTO photo_controlpoint (user_id, photo_time, latitude, longitude, feature_type, mapuser_id, photo_id, photopath, feature_id)"
				+ "VALUES( " 
				+ StringUtils.checkString(document.getUser_id()) + ", "
				+ StringUtils.checkString(document.getPhoto_time()) + ", "
				+ StringUtils.checkString(document.getLatitude()) + ", "
				+ StringUtils.checkString(document.getLongitude()) + ", "
				+ StringUtils.checkString(document.getFeature_type()) + ", "
				+ StringUtils.checkString(document.getMapuser_id()) + ", "
				+ document.getPhoto_id() + ", "
				+ StringUtils.checkString(uploadLoaction) + ", "
				+ StringUtils.checkString(document.getFeature_id()) + ")";
		executeQueryGis(sql);
		result = true;
		return result;
	}
	
	@Override
	public File getFile(String type, String fileName) throws FileNotFoundException {
		String name = agent.getPropertyValue("UPLOAD_LOCATION") + File.separator + type + File.separator + fileName;
		File file = new File(name);
		if (!file.exists()) {
			throw new FileNotFoundException("file with path: " + name + " was not found.");
		}
		return file;
	}
	
	@Override
	public File getFileFromLocation(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		if (!file.exists()) {
			throw new FileNotFoundException("file with path: " + fileName + " was not found.");
		}
		return file;
	}
	
	public List getUserList() throws Exception {
		String sql = "select distinct (user_name),user_id,mobile,active,isadmin from app_users order by user_name";
		logger.debug(sql);
		return jdbcTemplatePostgres.queryForList(sql);
	}
	
	public boolean updateUser(User user) {
		boolean result = false;
		try {
			jdbcTemplatePostgres.update("UPDATE app_users set password=? WHERE USER_ID= ?",new Object[] {Encrypt.encrypt(user.getPassword()), user.getUserId() });
			 result = true;
		} catch (Exception e) {
			logger.error("Exception while updating user " + e.getMessage());
		}
		
		return result;
	}
	
	public List getMettingrList() throws Exception {
		String sql = "select distinct (meeting_nm) meeting_name,gid meeting_id,REPLACE (REPLACE (ST_AsText(ST_Centroid(geom)),'POINT(\','\'),')\','\') coord from meeting_stage order by meeting_nm";
		logger.debug(sql);
		return jdbcTemplatePostgres.queryForList(sql);
	}
	
	@Override
	public boolean validateDuplicateLogin(String userId) {
		boolean flag = false;
		try {
			if (userId != null) {
				String sql = "SELECT token FROM app_token WHERE user_id = " + userId;
				String output = fetch(sql);
				if (output == null) {
					flag = true;
				}
			} 
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return flag;
	}
	
	@Override
	public boolean invalidateDuplicateLogin(int userId, String platform) {
		boolean flag = false;
		try {
			if (userId > 0) {
				String sql = "delete FROM app_token WHERE user_id = " + userId + " and platform ="+StringUtils.checkString(platform);
				int result = executeQuery(sql);
				logger.debug(result);
				if(result > 0)
					flag = true;
			} 
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return flag;
	}
	
	@Override
	public ResponseData getInspectorLocations(List<Map<String, Object>> pointData) {
		logger.debug("Getting points for livelocation");
		ResponseData responseData = new ResponseData();	
		logger.debug("pointData " + pointData.size());
		try {
			if (pointData.size() > 0) {
				/* FEATURE DECLARATION */
				JSONObject features = new JSONObject();
				features.put("type", "FeatureCollection");
				JSONObject crs = new JSONObject();
				JSONObject crsProperties = new JSONObject();
				crs.put("type", "name");
				crsProperties.put("name", "EPSG:3857");

				crs.put("properties", crsProperties);

				JSONArray featuresArray = new JSONArray();
				JSONArray featuresLineArray = new JSONArray();
				
				List<String> meanPointCoordinates = new ArrayList<String>();
				StringBuffer coordinates = new StringBuffer("[");

				for (int a = 0; a < pointData.size(); a++) {
					Map<String, Object> layerData = pointData.get(a);
					logger.debug(layerData);
					
					String username = layerData.get("username").toString().toUpperCase();
					String designation =  layerData.get("designation").toString().toUpperCase();

					String latLong = covert(layerData.get("latitude").toString(), layerData.get("longitude").toString());
					logger.debug("latLong " + latLong);
					
					if (a == 0)
						coordinates.append(latLong);
					else
						coordinates.append("," + latLong);
					
					JSONObject jsonPointFeature = new JSONObject();
					JSONObject jsonPointProperties = new JSONObject();

					jsonPointProperties.put("style", "Inspector");
					jsonPointProperties.put("username", username + " : "+designation);
					
					JSONObject jsonPointGeometry = new JSONObject();
					jsonPointGeometry.put("type", "Point");
					jsonPointGeometry.put("coordinates", latLong);
					
					jsonPointFeature.put("type", "Feature");
					jsonPointFeature.put("geometry", jsonPointGeometry);
					jsonPointFeature.put("properties", jsonPointProperties);
					featuresArray.add(jsonPointFeature);
					
					meanPointCoordinates.add(latLong);
				}
				
				coordinates.append("]");
				
				JSONObject jsonLineProperties = new JSONObject();
				jsonLineProperties.put("style", "MultiLineString");
				
				JSONObject jsonLineGeometry = new JSONObject();
				jsonLineGeometry.put("type", "MultiLineString");
				jsonLineGeometry.put("coordinates", coordinates.toString());
				
				JSONObject jsonLineFeature = new JSONObject();
				jsonLineFeature.put("type", "Feature");
				jsonLineFeature.put("geometry", jsonLineGeometry);
				jsonLineFeature.put("properties", jsonLineProperties);
				
				featuresLineArray.add(jsonLineFeature);
				featuresArray.add(jsonLineFeature);
				
				responseData.setTotal(featuresArray.size());

				features.put("crs", crs);
				features.put("features", featuresArray);
				features.put("featuresLine", featuresLineArray);
				
				responseData.setFeatures(features);
				
				JSONArray pointCoordinates = new JSONArray();
				JSONObject pointLatLong = new JSONObject();
				pointLatLong.put("meanpoints", meanPointCoordinates);
				pointCoordinates.add(pointLatLong);
				responseData.setCoordinates(pointCoordinates);
			}
			responseData.setPermission(true);
			responseData.setError("No Error");

		} catch (Exception e) {
			responseData.setPermission(false);
			responseData.setError(e.getMessage());
			logger.error("Exception : " + e.getMessage());
		}
		return responseData;
	}
	
	@Override
	public JSONObject getTranformData(String layerName) {
		logger.debug("getTranformData for "+layerName);

		/* FEATURE DECLARATION */
		JSONObject features = new JSONObject();
		features.put("type", "FeatureCollection");

		List<String> pointCoordinates = new ArrayList<>();
		
		try {
			List<Map<String, Object>> pointData = getTableDataGis(layerName);
			features.put("totalFeatures", pointData.size());
			
			if (pointData.size() > 0) {
				JSONObject crs = new JSONObject();
				JSONObject crsProperties = new JSONObject();
				crs.put("type", "name");
				crsProperties.put("name", "urn:ogc:def:crs:EPSG::32645");

				crs.put("properties", crsProperties);

				String focus = "";
				JSONArray featuresArray = new JSONArray();
				for (int a = 0; a < pointData.size(); a++) {
					Map<String, Object> layerData = pointData.get(a);
					logger.debug(layerData);

					JSONObject jsonPointFeature = new JSONObject();
					jsonPointFeature.put("type", "Feature");
					jsonPointFeature.put("id", "id."+a);

					jsonPointFeature.put("geometry_app", layerData.get("transformed_data").toString());
					jsonPointFeature.put("geometry", layerData.get("map_data").toString());
					jsonPointFeature.put("geometry_name", "geom");	
					jsonPointFeature.put("count", a);
					jsonPointFeature.put("style", "Point");
					
					pointCoordinates.add(layerData.get("map_data").toString());

					if(a==0)
						focus = layerData.get("transformed_data").toString();
					
					layerData.remove("transformed_data");
					layerData.remove("map_data");
					layerData.remove("points");
					layerData.remove("geom");
					
					jsonPointFeature.put("properties", layerData);
					
					featuresArray.add(jsonPointFeature);
				}
				
				features.put("crs", crs);
				features.put("features", featuresArray);
				features.put("focus", focus);
				features.put("pointCoordinates", pointCoordinates);
				features.put("status", "success");
				features.put("message", "Data fetched");
			}
		} catch (Exception e) {
			logger.error("Exception : " + e.getMessage());
			
			features.put("status", "faliure");
			features.put("message", e.getMessage());
		}
		return features;
	}
	
	@Override
	public boolean forceClose(int userId){
		boolean flag = false;
		try {
			String sql = "insert into app_locationdetails (androidver, battry, divname, imeino, latitude, serverlogtime, longitude, mobiledataonoff, networkname, logtime,userid, applionoff, screenactive)"
						+" select androidver, battry, divname, imeino, latitude, current_timestamp , longitude, mobiledataonoff, networkname, current_timestamp,  userid,'false','false' "
						+" from app_locationdetails di where userid = "+userId+" order by 1 desc limit 1";
			int result = executeQuery(sql);
			logger.debug(result);
			if(result > 0)
				flag = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return flag;
	}
	
	@Override
	public Map<String, Object> getPPName() {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		try{
			String table = agent.getPropertyValue("polling-table");
			String sql = "select "+ agent.getPropertyValue("polling-place") + " from " + table +" order by 2";
			data.put("ppname_data",getList(sql));
		}catch(Exception e){
			logger.error("Exception in getting pool data");
		}
		
		return data;

	}
	
	@Override
	public boolean findUser(String userName) {

		String sql = "select * from app_users where user_name=? limit 1";
		try {
			jdbcTemplatePostgres.queryForMap(sql, new Object[] { userName });
			logger.debug("User name exists");
			return true;
		} catch (Exception e) {
			logger.error("User name doesn't exist");
			return false;
		}
	}
	
	@Override
	public List getUsersLog() throws Exception {
		String sql = "select * from app_userlog order by user_name";
		logger.debug(sql);
		return jdbcTemplatePostgres.queryForList(sql);
	}
	
	@Override
	public List<Map<String, Object>> getFireList(String where) throws Exception{
		String sql = "select ST_AsGeoJSON(ST_Transform(geom,4326)) as lat_long,* from fire";
		if(where.equalsIgnoreCase("TODAY"))
			sql = sql + " where fire_date = '"+StringUtils.getToday()+"' ";
		logger.debug(sql);
		System.out.println(sql);
		return jdbcTemplatePostgres.queryForList(sql);
	}
	
	@Override
	public List<Map<String, Object>> getPatrollingList() throws Exception{
		String sql = "select * from pat_track";
		logger.debug(sql);
		return jdbcTemplatePostgres.queryForList(sql);
	}
	
	@Override
	public List<Map<String, Object>> getFireSMS() {
		String sql = "select * from fire_sms";
		logger.debug(sql);
		return jdbcTemplatePostgres.queryForList(sql);
	}
	
	@Override
	public Map<String, String> getCompList(){
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		try{
			String sql = "select distinct(compart_no) as value,gid as code from compartment order by 1";
			List<CDDRB> outputData = jdbcTemplatePostgres.query( sql,new BeanPropertyRowMapper(CDDRB.class));
			for (int a=0; a<outputData.size();a++) {
				CDDRB object = outputData.get(a);
				dataMap.put(object.getCode(), object.getValue());
			}
		} catch (Exception e) {
			logger.error("Exception while getting all layer " + e.getMessage());
		}
		return dataMap;
	}
	
	@Override
	public Map<String, String> getAllFireSource() {
		Map<String, String> allLayerList = new LinkedHashMap<String, String>();
		try {
			String sql = "select distinct(source) as code ,source as value from fire";
			List<Map<String, Object>> layers = jdbcTemplatePostgres.queryForList(sql);
			for (Map<String, Object> layersName : layers) {
				allLayerList.put(layersName.get("code").toString(), layersName.get("value").toString());
			}

		} catch (Exception e) {
			logger.error("Exception while getting all layer " + e.getMessage());
		}
		return allLayerList;
	}
	
	@Override
	public Map<String, String> getAllPatSource() {
		Map<String, String> allLayerList = new LinkedHashMap<String, String>();
		try {
			String sql = "select distinct(source) as code ,source as value from fire";
			List<Map<String, Object>> layers = jdbcTemplatePostgres.queryForList(sql);
			for (Map<String, Object> layersName : layers) {
				allLayerList.put(layersName.get("code").toString(), layersName.get("value").toString());
			}

		} catch (Exception e) {
			logger.error("Exception while getting all layer " + e.getMessage());
		}
		return allLayerList;
	}
	
	@Override
	public boolean addWayPoint(WayPoints wayPoints){
		boolean result = false;
		try {
			String sql = "INSERT INTO waypoint(circle, district, division, range, beat, mouza, "
					+ " gisid, mapuser_id, compart_no, waypont_id, timestamp, observ, geom) VALUES (" 
					+ StringUtils.checkString(wayPoints.getCircle_nm()) + ", "
					+ StringUtils.checkString(wayPoints.getDistrict_nm()) + ", "
					+ StringUtils.checkString(wayPoints.getDivision_nm()) + ", "
					+ StringUtils.checkString(wayPoints.getRange_nm()) + ", "
					+ StringUtils.checkString(wayPoints.getBeat_nm()) + ", "
					+ StringUtils.checkString(wayPoints.getMouza()) + ", "
					+ wayPoints.getGisid() + ", "
					+ StringUtils.checkString(wayPoints.getMapuser_id()) + ", "
					+ StringUtils.checkString(wayPoints.getCompart_no()) + ", "
					+ wayPoints.getWaypont_id() + ",current_timestamp, "
					+ StringUtils.checkString(wayPoints.getObserv()) + ", st_GeomFromText('POINT("+covertToGeom(wayPoints.getLatitude(),wayPoints.getLongitude()) +")', 32645) )";
			logger.debug(sql);
			System.out.println(sql);
			executeQueryGis(sql);
			
			if(wayPoints.getPhotos() !=null && wayPoints.getPhotos().size()>0){
				for(int a=0;a<wayPoints.getPhotos().size();a++)
					uploadAndInsert(wayPoints.getPhotos().get(a));
				
				result = true;
			}else
				result = true;
			
		} catch (Exception e) {
			logger.error("Error in addDevInfo of PostgresController: ", e);
		}
		return result;
	}
	
	@Override
	public boolean deleteExpiredToken(){
		boolean flag = false;
		try {
			logger.debug("Token Cleaner Called");
			System.out.println("Clean Token..");
			String sql = "DELETE FROM app_token WHERE TOKEN_ID IN (select token_id from app_token where date_part('hour',current_timestamp-last_access::timestamp )*60 >= 60)";
			int result = executeQuery(sql);
			logger.debug(result);
			if(result > 0)
				flag = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return flag;
	}
	
}
