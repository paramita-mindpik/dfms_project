package com.gis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gis.model.CDDRB;
import com.gis.service.PostgresService;

import net.sf.json.JSONObject;

@Controller
public class WebController {
	
	@Autowired
	PostgresService postgresService;
	
	static Log logger = LogFactory.getLog(WebController.class.getName());
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(HttpServletRequest request, HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "dashboard";
			List<Map<String, Object>> fireList = new ArrayList<>();
			try {
				fireList = postgresService.getFireList("TODAY");
			} catch (Exception e) {
				logger.error("Exception" + e.getMessage());
			}
			session.setAttribute("fireList",fireList);
			
			session.setAttribute("circleList",postgresService.getAllCircle());
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
		
	}
	
	@RequestMapping(value = "/assetmapping", method = RequestMethod.GET)
	public ModelAndView assetmapping(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "assetmapping";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/forestblock", method = RequestMethod.GET)
	public ModelAndView forestblock(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "forestblock";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/forestfire", method = RequestMethod.GET)
	public ModelAndView forestfire(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "forestfire";
			List<Map<String, Object>> fireList = new ArrayList<>();
			Map<String, String>  fireSource = new HashMap<>();
			try {
				fireList = postgresService.getFireList("ALL");
				fireSource = postgresService.getAllFireSource();
			} catch (Exception e) {
				logger.error("Exception" + e.getMessage());
			}
			session.setAttribute("fireList",fireList);
			session.setAttribute("fireSource",fireSource);
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/offense", method = RequestMethod.GET)
	public ModelAndView offense(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "offense";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/patrolling", method = RequestMethod.GET)
	public ModelAndView patrolling(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {

			List<Map<String, Object>> patrollingList = new ArrayList<>();
			try {
				patrollingList = postgresService.getPatrollingList();
			} catch (Exception e) {
				logger.error("Exception" + e.getMessage());
			}
			session.setAttribute("patrollingList",patrollingList);
			nextPage = "patrolling";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/plantation", method = RequestMethod.GET)
	public ModelAndView plantation(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "plantation";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	/////////////////// start plantation journal code Paramita///////////////////////////////////////////////////
	
	@RequestMapping(value = "/pjmastermodule", method = RequestMethod.GET)
	public ModelAndView pjmastermodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "pjmastermodule";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/pjlandmodule", method = RequestMethod.GET)
	public ModelAndView pjlandmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "pjlandmodule";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/pjplantationmodule", method = RequestMethod.GET)
	public ModelAndView pjplantationmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "pjplantationmodule";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/pjtechnicalstatusofsoil", method = RequestMethod.GET)
	public ModelAndView pjtechnicalstatusofsoil(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "pjtechnicalstatusofsoil";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/pjmapuploadmodule", method = RequestMethod.GET)
	public ModelAndView pjmapuploadmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "pjmapuploadmodule";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/pjusermanagement", method = RequestMethod.GET)
	public ModelAndView pjusermanagement(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "pjusermanagement";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/pjformauthorisationmodule", method = RequestMethod.GET)
	public ModelAndView pjformauthorisationmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "pjformauthorisationmodule";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/pjprintplantationjournalonline", method = RequestMethod.GET)
	public ModelAndView pjprintplantationjournalonline(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "pjprintplantationjournalonline";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	//////////////////end plantation journal code Paramita///////////////////////////////
	
/////////////////// start Creation Work Updation code Paramita///////////////////////////////////////////////////
	
@RequestMapping(value = "/cwumastermodule", method = RequestMethod.GET)
public ModelAndView cwumastermodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "cwumastermodule";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/cwulandmodule", method = RequestMethod.GET)
public ModelAndView cwulandmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "cwulandmodule";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/cwuplantationmodule", method = RequestMethod.GET)
public ModelAndView cwuplantationmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "cwuplantationmodule";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/cwutechnicalstatusofsoil", method = RequestMethod.GET)
public ModelAndView cwutechnicalstatusofsoil(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "cwutechnicalstatusofsoil";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/cwumapuploadmodule", method = RequestMethod.GET)
public ModelAndView cwumapuploadmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "cwumapuploadmodule";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/cwuusermanagement", method = RequestMethod.GET)
public ModelAndView cwuusermanagement(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "cwuusermanagement";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/cwuformauthorisationmodule", method = RequestMethod.GET)
public ModelAndView cwuformauthorisationmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "cwuformauthorisationmodule";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/cwuprintplantationjournalonline", method = RequestMethod.GET)
public ModelAndView cwuprintplantationjournalonline(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "cwuprintplantationjournalonline";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

//////////////////end Creation Work Updation code Paramita///////////////////////////////

/////////////////// start Maintenance Updation code Paramita///////////////////////////////////////////////////

@RequestMapping(value = "/mumastermodule", method = RequestMethod.GET)
public ModelAndView mumastermodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "mumastermodule";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/mulandmodule", method = RequestMethod.GET)
public ModelAndView mulandmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "mulandmodule";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/muplantationmodule", method = RequestMethod.GET)
public ModelAndView muplantationmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "muplantationmodule";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/mutechnicalstatusofsoil", method = RequestMethod.GET)
public ModelAndView mutechnicalstatusofsoil(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "mutechnicalstatusofsoil";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/mumapuploadmodule", method = RequestMethod.GET)
public ModelAndView mumapuploadmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "mumapuploadmodule";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/muusermanagement", method = RequestMethod.GET)
public ModelAndView muusermanagement(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "muusermanagement";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/muformauthorisationmodule", method = RequestMethod.GET)
public ModelAndView muformauthorisationmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "muformauthorisationmodule";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/muprintplantationjournalonline", method = RequestMethod.GET)
public ModelAndView muprintplantationjournalonline(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "muprintplantationjournalonline";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

//////////////////end Maintenance Updation code Paramita///////////////////////////////

/////////////////// start Inspection Updation code Paramita///////////////////////////////////////////////////

@RequestMapping(value = "/iumastermodule", method = RequestMethod.GET)
public ModelAndView iumastermodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "iumastermodule";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/iulandmodule", method = RequestMethod.GET)
public ModelAndView iulandmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "iulandmodule";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/iuplantationmodule", method = RequestMethod.GET)
public ModelAndView iuplantationmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "iuplantationmodule";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/iutechnicalstatusofsoil", method = RequestMethod.GET)
public ModelAndView iutechnicalstatusofsoil(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "iutechnicalstatusofsoil";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/iumapuploadmodule", method = RequestMethod.GET)
public ModelAndView iumapuploadmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "iumapuploadmodule";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/iuusermanagement", method = RequestMethod.GET)
public ModelAndView iuusermanagement(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "iuusermanagement";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/iuformauthorisationmodule", method = RequestMethod.GET)
public ModelAndView iuformauthorisationmodule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "iuformauthorisationmodule";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

@RequestMapping(value = "/iuprintplantationjournalonline", method = RequestMethod.GET)
public ModelAndView iuprintplantationjournalonline(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
String nextPage = "index";
if (!postgresService.reloginRequired(request)) {
nextPage = "iuprintplantationjournalonline";
}
ModelAndView modelView = new ModelAndView(nextPage);
return modelView;
}

//////////////////end Inspection Updation code Paramita///////////////////////////////
	
	@RequestMapping(value = "/projectarea", method = RequestMethod.GET)
	public ModelAndView projectarea(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "projectarea";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/wildlife", method = RequestMethod.GET)
	public ModelAndView wildlife(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "wildlife";
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	
	@RequestMapping(value = "/patrollingroute", method = RequestMethod.GET)
	public ModelAndView patrollingroute(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("cddrb") CDDRB cddrb,HttpSession session) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			nextPage = "patrollingroute";
			session.setAttribute("compList",postgresService.getCompList());
		}
		ModelAndView modelView = new ModelAndView(nextPage);
		return modelView;
	}
	
	@RequestMapping(value = "/route/{points}", method = RequestMethod.GET)
	public String routePoint(HttpServletRequest request, HttpServletResponse response,@PathVariable("points") List points, final RedirectAttributes redirectAttributes) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			redirectAttributes.addFlashAttribute("points", points);
			nextPage = "redirect:/route";
		}
		return nextPage;
	}
	
	@RequestMapping(value = "/route", method = RequestMethod.GET)
	public String route(HttpServletRequest request, HttpSession session, final RedirectAttributes redirectAttributes, Model model) {
		String nextPage = "index";
		if (!postgresService.reloginRequired(request)) {
			List<String> points = (List) model.asMap().get("points");
			
			String fromTable = " compartment where gid in (" +points.stream().collect(Collectors.joining(","))+")";
			JSONObject responseData = postgresService.getTranformData(fromTable);
			request.setAttribute("route_data", responseData);	
			request.setAttribute("route", "Y");
			nextPage = "gis";
		}
		return nextPage;
	}
}