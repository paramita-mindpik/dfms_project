package com.gis.service;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	static Log logger = LogFactory.getLog(ScheduledTasks.class.getName());

	@Value("${enable.cron}")
	private String cronEnable;
	
	@Autowired
	PostgresService postgresService;

	@Scheduled(cron = "${cron.token.cleaner}")
	public void emailPendancyReport() {
		System.out.println("Schedular execution time " + new Date());
		System.out.println("Cron Job value :" + cronEnable);
		if (cronEnable.equalsIgnoreCase("Y"))
			logger.debug(postgresService.deleteExpiredToken());
	}
}
