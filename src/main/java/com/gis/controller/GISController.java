package com.gis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gis.model.User;
import com.gis.service.LayerService;
import com.gis.service.PostgresService;
import com.gis.util.Encrypt;
import com.gis.util.StringUtils;

@Controller
public class GISController {
	static Log logger = LogFactory.getLog(GISController.class.getName());

	@Autowired
	PostgresService postgresService;

	@Autowired
	LayerService layerService;
	
	private final String sql = "select max(id) from audio_video";

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}

	@RequestMapping(value = "/gis", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "index";
		String username = request.getParameter("username");
		//String username = "superadmin";//added by abc
		logger.debug(username);
		String password = request.getParameter("password");
		//String password = "ABC";//added by abc
		String encPassword = Encrypt.encrypt(password);
		logger.debug(encPassword);
		String landingPage = request.getParameter("landingPage");
		try{
			if (username.equals("superadmin") && password.equals(StringUtils.getSeperAdmin())) {
			//if (username.equals("superadmin") && password.equals("ABC")) {//added by abc
				HttpSession session = request.getSession();
				session.setAttribute("userName", "SUPER ADMIN");
				session.setAttribute("userId", 0);
				session.setAttribute("permission", "W");
				session.setAttribute("isadmin", "Y");
				session.setAttribute("token", postgresService.getNewToken(0,"SA"));
				session.setAttribute("allowedLayerList", postgresService.getAllActiveLayers());
				session.setAttribute("appVersion", postgresService.select("select version from app_version"));
				session.setAttribute("circleList",postgresService.getAllCircle());
				nextPage = "redirect:/"+landingPage.toLowerCase();
			} else {
				User userData = postgresService.login(username, encPassword , "WEB");
				if (userData.getUserId() != 0 && userData.getActive().equalsIgnoreCase("Y")) {
					HttpSession session = request.getSession();
					session.setAttribute("userName", userData.getUserName().toUpperCase());
					session.setAttribute("userId", userData.getUserId());
					session.setAttribute("permission", userData.getPermission());
					session.setAttribute("isadmin", userData.getIsadmin());
					session.setAttribute("token", userData.getToken());
					session.setAttribute("allowedLayerList", postgresService.getAllActiveLayers());
					session.setAttribute("appVersion", userData.getVersion());
					session.setAttribute("circleList",postgresService.getAllCircle());
					nextPage = "redirect:/"+landingPage.toLowerCase();
					
					userData.setPlatform("WEB");
					postgresService.insertUpdateUserLog(userData);
				} else {
					if (userData.getActive() != null && userData.getActive().equalsIgnoreCase("N"))
						request.setAttribute("error", "User not active contact administrator");
					else
						request.setAttribute("error", "Invalid user name or password");
				}
			}
		}catch(Exception e){
			nextPage = "index";
			request.setAttribute("error", e.getMessage());
		}
		return nextPage;
	}

	@RequestMapping(value = "/gis", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			HttpSession session = request.getSession(false);
			session.setAttribute("allowedLayerList", postgresService.getAllActiveLayers());
			session.setAttribute("allLayerList", postgresService.getAllLayers());
			session.setAttribute("maxId", postgresService.select(sql));
			nextPage = "gis";
		}
		return nextPage;
	}
	
	@RequestMapping(value = "/gis/risk", method = RequestMethod.GET)
	public String loadGis(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			HttpSession session = request.getSession(false);
			session.setAttribute("allowedLayerList", postgresService.getAllActiveLayers());
			session.setAttribute("allLayerList", postgresService.getAllLayers());
			session.setAttribute("maxId", postgresService.select(sql));
			session.setAttribute("layer", "fire,forest_mzbnd,compartment");
			request.setAttribute("fromRisk", "Y");
			nextPage = "gis";
		}
		return nextPage;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, final RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		try{
			postgresService.InvalidateToken((String)session.getAttribute("token"));
		}catch(Exception e){}
		
		session.invalidate();
		String message = (String) model.asMap().get("message");
		request.setAttribute("error", message);
		
		return "index";
	}
}
