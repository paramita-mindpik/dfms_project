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

import com.gis.model.Layer;
import com.gis.service.PostgresService;
import com.gis.util.StringUtils;

@Controller
public class AdminLayerController {
	
	@Autowired
	PostgresService postgresService;
	
	@RequestMapping(value = "/admin/layerslist", method = RequestMethod.GET)
	public ModelAndView listlayers(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "layers/layerslist";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}

	@RequestMapping(value = "/admin/addlayer", method = RequestMethod.GET)
	public ModelAndView listusers(HttpSession session, HttpServletRequest request, @ModelAttribute("layer") Layer layer) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "layers/addlayer";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}	
	
	@RequestMapping(value = "/admin/addlayer", method = RequestMethod.POST)
	public ModelAndView listusers(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("layer") Layer layer, BindingResult result) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "layers/addlayer";
			if (!result.hasErrors()) {
				postgresService.addUpdateLayerMapping(layer);
				nextPage = "redirect:/admin/layerslist";
			}
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}	
	
	
	@RequestMapping(value = "/admin/editlayer/{layerId}", method = RequestMethod.GET)
	public ModelAndView listusers(@PathVariable("layerId") String actId,HttpServletRequest request, HttpServletResponse response,@ModelAttribute("layer") Layer layer) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "layers/editlayer";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
}