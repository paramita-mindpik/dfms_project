package com.gis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gis.service.PostgresService;

@Controller
public class AdminGroupController {
	
	@Autowired
	PostgresService postgresService;
	
}


