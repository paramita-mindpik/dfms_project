package com.gis.agent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class ApiAgentImpl implements ApiAgent {
	static Log logger = LogFactory.getLog(ApiAgentImpl.class.getName());

	public String getPropertyValue(String key) {
		String apiURL = "";
		try {
			ResourceBundle properties = ResourceBundle.getBundle("application");
			apiURL = properties.getString(key);

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return apiURL;
	}

	public String getData(String URL) {

		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(URL);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			br.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sb.toString();
	}
}
