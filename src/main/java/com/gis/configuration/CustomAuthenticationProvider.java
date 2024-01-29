package com.gis.configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.gis.model.User;
import com.gis.service.PostgresService;
import com.gis.util.Encrypt;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	PostgresService postgresService;

	@Autowired
	HttpServletRequest httpServletRequest;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		Authentication userauth = null;
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		
		String encPassword = Encrypt.encrypt(password);
		
		try {
			System.out.println("Login Called......");
				User userData = postgresService.login(username, encPassword , "WEB");
				if(userData != null) {
				String ROLE = "ADMIN";

				grantedAuths.add(new SimpleGrantedAuthority(ROLE));
				userauth = new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
				int userId = userData.getUserId();

				HttpSession session = httpServletRequest.getSession();
				session.setAttribute("userId", userId);
				session.setAttribute("userRole", ROLE);
				session.setAttribute("lastLogin", "Login : " + new Date());
				session.setAttribute("ip", "IP Address : " + getIp());
				
				session.setAttribute("userName", userData.getUserName().toUpperCase());
				session.setAttribute("userId", userData.getUserId());
				session.setAttribute("permission", userData.getPermission());
				session.setAttribute("isadmin", userData.getIsadmin());
				session.setAttribute("token", userData.getToken());
				session.setAttribute("allowedLayerList", postgresService.getAllActiveLayers());
				session.setAttribute("allLayerList", postgresService.getAllLayers());
				session.setAttribute("userList", postgresService.getUserList());
				session.setAttribute("appVersion", userData.getVersion());
			} else {
				throw new BadCredentialsException("Wrong Credentials.");
			}
		}catch(Exception e)
	{
		throw new BadCredentialsException("Unable to connect to DB");
	}

	return userauth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public String getIp() {
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}

}