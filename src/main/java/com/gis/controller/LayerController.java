package com.gis.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gis.agent.ApiAgent;
import com.gis.model.RequestData;
import com.gis.model.WayPoints;
import com.gis.service.PostgresService;
import com.gis.util.StringUtils;

import net.sf.json.JSONObject;

@RestController
public class LayerController {
	static Log logger = LogFactory.getLog(LayerController.class.getName());

	@Autowired
	ApiAgent agent;

	@Autowired
	PostgresService postgresService;

	@RequestMapping(value = "/layer", method = RequestMethod.POST)
	public JSONObject layerDetail(HttpServletRequest request,
			@RequestBody RequestData requestData) {
		JSONObject jsonObject = null;
		logger.debug("layerDetail " + requestData);
		try {
			String url = requestData.getUrl();
			String token = requestData.getToken();
			if (postgresService.validateToken(token)) {
				logger.debug("URL " + url);
				String jsonData = agent.getData(url).replaceAll("null", "\" \"");
				jsonObject = JSONObject.fromObject(jsonData.toString()); 
			}

		} catch (Exception e) {
			logger.error("exp :" + e);
		}
		logger.debug("jsonObject " + jsonObject);
		return jsonObject;
	}

	@RequestMapping(value = "/attribute", method = RequestMethod.POST)
	public JSONObject attribute(HttpServletRequest request,
			@RequestBody RequestData requestData) {
		JSONObject jsonObject = null;
		try {
			String layer = requestData.getLayer();
			String token = requestData.getToken();
			if (postgresService.validateToken(token)) {
				String url = "http://"
						+ agent.getPropertyValue("IP")
						+ "/geoserver/"
						+ agent.getPropertyValue("WORKSPACE")
						+ "/ows?service=WFS&version=1.0.0&request=GetFeature&typeName="
						+ agent.getPropertyValue("WORKSPACE") + ":" + layer
						+ "&maxFeatures=" + requestData.getLimit()
						+ "&outputFormat=application/json";
				logger.debug(url);
				String jsonData = agent.getData(url);
				logger.debug(jsonData);
				jsonObject = JSONObject.fromObject(jsonData.toString());
			}

		} catch (Exception e) {
			logger.error("exp :" + e);
		}
		return jsonObject;
	}

	@RequestMapping(value = "/layerData", method = RequestMethod.POST)
	public Map<String, Object> layerData(HttpServletRequest request,
			@RequestBody RequestData requestData) {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		boolean status = false;
		String message = "";
		try {
			String layer = requestData.getLayer();
			String token = requestData.getToken();
			if (postgresService.validateToken(token)) {
				data.put("columns", postgresService.getDatabaseMetaData(layer));
				data.put(
						"rows",
						postgresService
								.getList("SELECT *,case ST_AsText(geom) like 'POINT%' when true then concat(ST_X(geom), concat(',',ST_Y(geom))) else '' end as lat_lon FROM "
										+ layer));
				status = true;
				message = "Data fetched successfully.";
			} else {
				message = "Invalid token";
			}

		} catch (Exception e) {
			logger.error("exp :" + e);
			message = "Exception " + e.getMessage();
		}
		data.put("status", status);
		data.put("message", message);
		return data;
	}
	
	@RequestMapping(value = "/getCircles", method = RequestMethod.POST)
	public Map<String, String> getPollingDetails(HttpServletRequest request,
			@RequestBody RequestData requestData) {
		Map<String, String> data = new LinkedHashMap<>();
		String status = "false";
		String message = "";
		try {
			String token = requestData.getToken();
			if (postgresService.validateToken(token)) {
				data = postgresService.getAllCircle();
				status = "true";
				message = "Data fetched successfully.";
			} else {
				message = "Invalid token";
			}

		} catch (Exception e) {
			logger.error("exp :" + e);
			message = "Exception " + e.getMessage();
		}
		data.put("status", status);
		data.put("message", message);
		return data;
	}
	
	@RequestMapping(value = "/getDistrict", method = RequestMethod.POST)
	public Map<String, String> getDistrict(HttpServletRequest request,
			@RequestBody RequestData requestData) {
		Map<String, String> data = new LinkedHashMap<>();
		String status = "false";
		String message = "";
		try {
			String token = requestData.getToken();
			if (postgresService.validateToken(token)) {
				data = postgresService.getDistrict(requestData.getCode());
				status = "true";
				message = "Data fetched successfully.";
			} else {
				message = "Invalid token";
			}

		} catch (Exception e) {
			logger.error("exp :" + e);
			message = "Exception " + e.getMessage();
		}
		data.put("status", status);
		data.put("message", message);
		return data;
	}
	
	@RequestMapping(value = "/getDivision", method = RequestMethod.POST)
	public Map<String, String> getDivision(HttpServletRequest request,
			@RequestBody RequestData requestData) {
		Map<String, String> data = new LinkedHashMap<>();
		String status = "false";
		String message = "";
		try {
			String token = requestData.getToken();
			if (postgresService.validateToken(token)) {
				data = postgresService.getDivision(requestData.getCode());
				status = "true";
				message = "Data fetched successfully.";
			} else {
				message = "Invalid token";
			}

		} catch (Exception e) {
			logger.error("exp :" + e);
			message = "Exception " + e.getMessage();
		}
		data.put("status", status);
		data.put("message", message);
		return data;
	}
	
	@RequestMapping(value = "/getRange", method = RequestMethod.POST)
	public Map<String, String> getRange(HttpServletRequest request,
			@RequestBody RequestData requestData) {
		Map<String, String> data = new LinkedHashMap<>();
		String status = "false";
		String message = "";
		try {
			String token = requestData.getToken();
			if (postgresService.validateToken(token)) {
				data = postgresService.getRange(requestData.getCode());
				status = "true";
				message = "Data fetched successfully.";
			} else {
				message = "Invalid token";
			}

		} catch (Exception e) {
			logger.error("exp :" + e);
			message = "Exception " + e.getMessage();
		}
		data.put("status", status);
		data.put("message", message);
		return data;
	}
	
	@RequestMapping(value = "/getBeat", method = RequestMethod.POST)
	public Map<String, String> getBeat(HttpServletRequest request,
			@RequestBody RequestData requestData) {
		Map<String, String> data = new LinkedHashMap<>();
		String status = "false";
		String message = "";
		try {
			String token = requestData.getToken();
			if (postgresService.validateToken(token)) {
				data = postgresService.getBeat(requestData.getCode());
				status = "true";
				message = "Data fetched successfully.";
			} else {
				message = "Invalid token";
			}

		} catch (Exception e) {
			logger.error("exp :" + e);
			message = "Exception " + e.getMessage();
		}
		data.put("status", status);
		data.put("message", message);
		return data;
	}

	
	@RequestMapping(value = "/addwaypoints", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public @ResponseBody Map<String,String> addwaypoints(@ModelAttribute WayPoints wayPoints) {
		Map<String,String> output = new HashMap<String,String>();
		String key = "output";
		String value = "";
		try {
			String token = wayPoints.getToken();
			if (postgresService.validateToken(token)) {
				boolean added = postgresService.addWayPoint(wayPoints);
				if(added)
					value = "Way Point Added at "+ new Date();
				else
					value = "Unable to add Way Point";
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

	@RequestMapping(value = "/getActiveLayersList", method = RequestMethod.POST)
	public List getLayersList(HttpServletRequest request,
			@RequestBody RequestData requestData) {
		List data = new ArrayList<>();
		try {
			String token = requestData.getToken();
			if (postgresService.validateToken(token)) {
				data = postgresService.getAllActiveLayersList();
			}
		} catch (Exception e) {
			logger.error("exp :" + e);
		}
		return data;
	}
	
	@RequestMapping(value = "/getAllLayers", method = RequestMethod.POST)
	public List getAllLayers(HttpServletRequest request,@RequestBody RequestData requestData) {
		List data = new ArrayList();
		try {
			String token = requestData.getToken();
			if (postgresService.validateToken(token)) {
				data = postgresService.getAllLayers();
			}
		} catch (Exception e) {
			logger.error("exp :" + e);
		}
		return data;
	}
	
	@RequestMapping(value = "/getTranformedLayer", method = RequestMethod.POST)
	public JSONObject livelocation(@RequestBody RequestData requestData) {
		logger.debug("livelocation()");
		JSONObject responseData = new JSONObject();
		responseData.put("status", "failure");
		responseData.put("message", "Something went wrong");
		
		if (postgresService.validateToken(requestData.getToken())) {
			responseData = postgresService.getTranformData(requestData.getLayerName());
		} else {
			responseData.put("message", "Invalid Token");
		}
		return responseData;
	}
}