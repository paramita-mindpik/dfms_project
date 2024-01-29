package com.gis;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.gis.agent.ApiAgent;


@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ServletContext servletContext;
	
	@Autowired
	ApiAgent agent;
	
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		logger.info("Loading application data.");
		servletContext.setAttribute("PRIVATE_LABEL",agent.getPropertyValue("PROJECT"));
		servletContext.setAttribute("IP",agent.getPropertyValue("IP"));
		servletContext.setAttribute("WORKSPACE",agent.getPropertyValue("WORKSPACE"));
		servletContext.setAttribute("PUBLIC_IP",agent.getPropertyValue("PUBLIC_IP"));
		return;
		
		
	}

}
