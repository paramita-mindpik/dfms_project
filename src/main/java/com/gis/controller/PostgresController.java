package com.gis.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gis.agent.ApiAgent;
import com.gis.model.DevInfo;
import com.gis.model.RequestData;
import com.gis.model.ResponseData;
import com.gis.model.User;
import com.gis.service.PostgresService;
import com.gis.util.Encrypt;
import com.gis.util.StringUtils;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/Postgres")
public class PostgresController {

	static Log logger = LogFactory.getLog(PostgresController.class.getName());

	@Autowired
	PostgresService postgresService;
	
	@Autowired
	ApiAgent agent;

	@RequestMapping(value = "/fetch", method = RequestMethod.POST)
	public @ResponseBody JSONObject fetch(HttpServletRequest request, @RequestBody RequestData requestData) {
		JSONObject jsonObject = new JSONObject();
		String result = "";
		try {
			String query = requestData.getQuery();
			String token = requestData.getToken();
			if (postgresService.validateToken(token)) {
				logger.debug("PostgresController fetch is invoked: " + query);
				result = postgresService.fetchGis("SELECT "+query);
				logger.debug("RESULT : " + result);
			} else {
				result = "-1";
			}
		} catch (Exception e) {
			logger.error("Error in fetch of PostgresController: ", e);
		}
		jsonObject.put("result", result);
		return jsonObject;
	}

	@RequestMapping(value = "/getImages", method = RequestMethod.POST)
	public List getImages(@RequestBody RequestData requestData) {	
		List photoList = new ArrayList();
		try {
			String query = "SELECT * from photo where gid="+requestData.getLayerId()+" AND layer_name="+StringUtils.checkString(requestData.getLayerName());
			String token = requestData.getToken();
			if (postgresService.validateToken(token)) {
				logger.debug("PostgresController getimages is invoked: " + query);
				photoList = postgresService.getList(query);
				logger.debug("Total Photos : " + photoList.size());
			}

		} catch (Exception e) {
			logger.error("Error in getimages of PostgresController: ", e);
		}
		return photoList;
	}
	@CrossOrigin
	@RequestMapping(value = "/metadata", method = RequestMethod.POST)
	public @ResponseBody Map<String, String[]> getTableMetaData(@RequestBody RequestData requestData) {
		Map<String, String[]> metaData = new HashMap<String, String[]>();
		try {
			String tableName = requestData.getTableName();
			String token = requestData.getToken();
			if (postgresService.validateToken(token)) {
				metaData = postgresService.getDatabaseMetaData(tableName);
			}
		} catch (Exception e) {
			logger.error("Error in getting meta data of PostgresController: ", e);
		}
		return metaData;
	}

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public @ResponseBody User getToken(@RequestBody User tokenUser) {
		User user = new User();
		user.setStatus("false");
		try {
			logger.debug(tokenUser.getUserName());
			logger.debug(tokenUser.getPassword());
			if (tokenUser.getUserName() != null && tokenUser.getPassword() != null) {
				User userData = postgresService.login(tokenUser.getUserName(), Encrypt.encrypt(tokenUser.getPassword()),"APP");
				if (userData.getUserId() != 0 && userData.getActive().equalsIgnoreCase("Y")) {
					user = userData;
					ResourceBundle properties = ResourceBundle.getBundle("application");
					user.setIp(properties.getString("IP"));
					user.setPublicIp(properties.getString("PUBLIC_IP"));
					user.setWorkspace(properties.getString("WORKSPACE"));
					user.setMessage("Login Success.");
					user.setStatus("true");
					
					userData.setPlatform("GIS-APP");
					postgresService.insertUpdateUserLog(userData);
				} else if (userData.getActive() != null && userData.getActive().equalsIgnoreCase("N")) {
					user.setMessage("User not active contact administrator");
					user.setActive("N");
				}else if (userData.getActive() != null && userData.getActive().equalsIgnoreCase("I")) {
					user.setMessage("User already logged in.");
					user.setActive("I");
					user.setUserId(userData.getUserId());
				}else { 
					user.setMessage("Please provide valid credential.");
				}
			} else
				user.setMessage("Please provide User name and password ");

		} catch (Exception e) {
			logger.error("Error in getToken of PostgresController: ", e);
			user.setMessage("Invalid credential ");
		}
		return user;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody User registerUser(@RequestBody User registerUser) {
		User user = new User();
		user.setStatus("false");
		try {
			logger.debug(registerUser.getUserName());
			if (registerUser.getUserName() != null) {
				boolean result = postgresService.findUser(registerUser.getUserName().toLowerCase() , registerUser.getMobile());
				if (result == false) {
					registerUser.setPermission("W");
					registerUser.setIsadmin("N");
					
					postgresService.addNewUser(registerUser);
					user.setMessage("User added successfully.");
					user.setStatus("true");
					
				} else if (result == true) {
					user.setMessage("User already registred with username "+registerUser.getUserName());
				}
			} else
				user.setMessage("Please provide User name ");

		} catch (Exception e) {
			logger.error("Error in Register of PostgresController: ", e);
			user.setMessage("Unable to add new user.");
		}
		return user;
	}
	
	
	@RequestMapping(value = "/divinfo", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> devInfo(@RequestBody DevInfo devInfo) {
		Map<String,String> output = new HashMap<String,String>();
		String key = "output";
		String value = "";
		try {
			String token = devInfo.getToken();
			if (postgresService.validateToken(token)) {
				boolean added = postgresService.addDevInfo(devInfo);
				if(added)
					value = "Device Information Added at "+ new Date();
				else
					value = "Device Information not added";
			} else {
				value = "Invalid Token";
			}
		} catch (Exception e) {
			logger.error("Error in Register of PostgresController: ", e);
			value = "Exception " + e.getMessage();
		}
		output.put(key, value);
		return output;
	}
	
	@RequestMapping(value = "/check/iemi", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> checkIMEI(@RequestBody DevInfo devInfo) {
		Map<String,String> output = new HashMap<String,String>();
		String message = "";
		String status = "false";
		try {
			String token = devInfo.getToken();
			if (postgresService.validateToken(token)) {
				if(postgresService.checkIMEI(devInfo)){
					message = "Valid IMEI Number";
					status = "true";
				}
				else
					message = "Invalid IMEI Number";
			} else {
				message = "Invalid Token";
			}
		} catch (Exception e) {
			logger.error("Error in Register of PostgresController: ", e);
			message = "Exception " + e.getMessage();
		}
		output.put("message", message);
		output.put("status", status);
		return output;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> logout(@RequestBody DevInfo devInfo) {
		Map<String,String> output = new HashMap<String,String>();
		String message = "";
		String status = "false";
		try {
			String token = devInfo.getToken();
			//int userId = devInfo.getUserid();
			//postgresService.forceClose(userId);
			if (postgresService.InvalidateToken(token)) {
				message = "Logout Successfully.";
				status = "true";
			} else {
				message = "Invalid Token";
			}
		} catch (Exception e) {
			logger.error("Error in Register of PostgresController: ", e);
			message = "Exception " + e.getMessage();
		}
		output.put("message", message);
		output.put("status", status);
		return output;
	}
	
	@RequestMapping(value = "/livelocation", method = RequestMethod.POST)
	public ResponseData livelocation(@RequestBody RequestData requestData) {
		logger.debug("livelocation()");
		ResponseData responseData = new ResponseData();
		if (postgresService.validateToken(requestData.getToken())) {
			try{
				String getLiveLocationQuery = "select * from livelocation order by locationdate desc limit 5";

				logger.debug("requestData.getLimit() " +requestData.getLimit());
				logger.debug("requestData.getUserId() "+requestData.getUserId());
				
				/*
				 * if(requestData.getUserId().length() > 0){ getLiveLocationQuery =
				 * getLiveLocationQuery +
				 * " where userid  in (select user_id from userinfo u  where mobile in("
				 * +requestData.getUserId()+"))"; }
				 */
				logger.debug("getLiveLocationQuery "+ getLiveLocationQuery);
				
				List<Map<String, Object>> pointData = postgresService.getList(getLiveLocationQuery);
				responseData = postgresService.livelocation(pointData , requestData.getLimit());
			}catch(Exception e){
				logger.debug("Unable to get live data." +e.getMessage());
			}
		} else {
			responseData.setLogout(true);
		}
		return responseData;
	}
	
	@RequestMapping(value = "/fecthdivinfo", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> fecthDivInfo(@RequestBody DevInfo devInfo) {
		Map<String,Object> output = new HashMap<String,Object>();
		String key = "output";
		String value = "";
		try {
			String token = devInfo.getToken();
			if (postgresService.validateToken(token)) {
				String getLiveLocationQuery = "select  * from app_locationdetails";

				List<Map<String, Object>> pointData = postgresService.getList(getLiveLocationQuery);
				output.put("points", pointData);
				
				value = "Device Information Fetched.";
			} else {
				value = "Invalid Token";
			}
		} catch (Exception e) {
			logger.error("Error in Register of PostgresController: ", e);
			value = "Exception " + e.getMessage();
		}
		output.put(key, value);
		return output;
	}	
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> devInfo(@RequestBody User user) {
		Map<String,String> output = new HashMap<String,String>();
		String key = "output";
		String value = "";
		try {
			String token = user.getToken();
			if (postgresService.validateToken(token)) {
				boolean added = postgresService.updateUser(user);
				if(added)
					value = "Profile updated at "+ new Date();
				else
					value = "Something went wrong!!";
			} else {
				value = "Invalid Token";
			}
		} catch (Exception e) {
			logger.error("Error in Register of PostgresController: ", e);
			value = "Exception " + e.getMessage();
		}
		output.put(key, value);
		return output;
	}
	
	@RequestMapping(value = "/invalidateUser", method = RequestMethod.POST)
	public @ResponseBody User invalidateUser(@RequestBody User invalidUser) {
		User user = new User();
		user.setStatus("false");
		try {
			logger.debug(invalidUser.getUserId());
			if (invalidUser.getUserId() > 0) {
				boolean result = postgresService.invalidateDuplicateLogin(invalidUser.getUserId(), "APP");
				if (result == false) {
					user.setMessage("Something went wrong.");
				} else if (result == true) {
					user.setMessage("User invalidated successfully.");
					user.setStatus("true");
				}
			} else
				user.setMessage("Invalid user id.");

		} catch (Exception e) {
			logger.error("Error in Invalidate of PostgresController: ", e);
			user.setMessage("Unable to invalidate user.");
		}
		return user;
	}
	

	@RequestMapping(value = "/updateAppVersion", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> updateAppVersion(@RequestBody RequestData requestData) {
		Map<String,String> output = new HashMap<String,String>();
		String key = "output";
		String value = "";
		try {
			String token = requestData.getToken();
			if (postgresService.validateToken(token)) {
				
				String sql = "update app_version set a_version ="+StringUtils.checkString(requestData.getAppVersion())+" where id=1";
				postgresService.executeQuery(sql);
				value = "App Version updated to "+ requestData.getAppVersion();
			} else {
				value = "Invalid Token";
			}
		} catch (Exception e) {
			logger.error("Error in Register of PostgresController: ", e);
			value = "Exception " + e.getMessage();
		}
		output.put(key, value);
		return output;
	}
	
	@RequestMapping(value = "/notification", method = RequestMethod.POST)
	public ResponseData notification(@RequestBody RequestData requestData) {
		logger.debug("notification()");
		ResponseData responseData = new ResponseData();
		if (postgresService.validateToken(requestData.getToken())) {
			try{
				List<Map<String, Object>> notificationData = postgresService.getFireSMS();
				responseData.setNotification(notificationData);
				responseData.setPermission(true);
			}catch(Exception e){
				logger.debug("Unable to get notification data." +e.getMessage());
			}
		} else {
			responseData.setLogout(true);
		}
		return responseData;
	}
}