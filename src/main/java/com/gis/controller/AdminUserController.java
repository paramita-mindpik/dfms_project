package com.gis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gis.model.User;
import com.gis.service.PostgresService;

@Controller
public class AdminUserController {
	
	@Autowired
	PostgresService postgresService;
	
	@RequestMapping(value = "/admin/userslist", method = RequestMethod.GET)
	public ModelAndView listusers(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "users/userslist";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/admin/adduser", method = RequestMethod.GET)
	public ModelAndView listusers(HttpSession session, HttpServletRequest request, @ModelAttribute("user") User user) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "users/adduser";
			session.setAttribute("allLayerDropdownList", postgresService.getAllLayersDropdown());
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}	
	
	@RequestMapping(value = "/admin/adduser", method = RequestMethod.POST)
	public ModelAndView listusers(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user, BindingResult result) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "users/adduser";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}	
	
	
	@RequestMapping(value = "/admin/edituser/{userId}", method = RequestMethod.GET)
	public ModelAndView listusers(@PathVariable("userId") String actId,HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "users/edituser";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
}