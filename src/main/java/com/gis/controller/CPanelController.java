package com.gis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gis.service.LayerService;
import com.gis.service.PostgresService;

@Controller
public class CPanelController {
	
	@Autowired
	PostgresService postgresService;
	
	@Autowired
	LayerService layerService;
	
	static Log logger = LogFactory.getLog(CPanelController.class.getName());

	
	@RequestMapping(value = "/admin/cpanel", method = RequestMethod.GET)
	public ModelAndView wildlife(HttpServletRequest request, HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			try {
				request.setAttribute("usersLog",  postgresService.getUsersLog());

				session.setAttribute("allLayerList", postgresService.getAllLayers());
				session.setAttribute("userList", postgresService.getUserList());
				session.setAttribute("allGroupList", layerService.getGroupList());
				
			} catch (Exception e) {
				logger.error("Exception while getting user log " + e.getMessage());
			}
			nextPage = "cpanel";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/admin/groupslist", method = RequestMethod.GET)
	public ModelAndView listgroups(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "groups/groupslist";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/admin/appVersion", method = RequestMethod.GET)
	public ModelAndView app(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "appversion";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/admin/updateAppVersion", method = RequestMethod.POST)
	public ModelAndView appUpdate(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "appversion";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
}