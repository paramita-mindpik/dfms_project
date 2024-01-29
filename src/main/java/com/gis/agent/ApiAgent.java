package com.gis.agent;


/**
* Api Agent
* @author mkrout
**/
public interface ApiAgent {
	public String getPropertyValue(String key);

	public String getData(String url);
}
