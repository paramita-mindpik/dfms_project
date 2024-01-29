package com.gis.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int userId;

	private String userName;

	private String password;

	private String permission;

	private String isadmin;

	private String active;

	private List layer;

	private Layer[] layers;

	private String oldPassword;

	private String newPassword;

	private String postgresPassword;

	private String oldPostgresPassword;

	private String newPostgresPassword;

	private String token;

	private String message;

	private String workspace;

	private String ip;

	private String publicIp;

	private String axisCam;
	
	private String status;
	
	private String imeino;
	
	private String mobile;
	
	private String designation;
	
	private String organisation;
	
	private String version;
	
	private String role;
	
	
	private String f_name;
	private String m_name;
	private String l_name;
	private String empoly_cod;
	private String empoly_div;
	private String empoly_rang;
	private String empoly_beat;
	private String empoly_cercl;
	private String empoly_desig;
	private String joningdate;
	private String empoly_photo;
	private String empoly_stat;
	private String platform;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}

	public List getLayer() {
		return layer;
	}

	public void setLayer(List layer) {
		this.layer = layer;
	}

	public Layer[] getLayers() {
		return layers;
	}

	public void setLayers(Layer[] layers) {
		this.layers = layers;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getPostgresPassword() {
		return postgresPassword;
	}

	public void setPostgresPassword(String postgresPassword) {
		this.postgresPassword = postgresPassword;
	}

	public String getOldPostgresPassword() {
		return oldPostgresPassword;
	}

	public void setOldPostgresPassword(String oldPostgresPassword) {
		this.oldPostgresPassword = oldPostgresPassword;
	}

	public String getNewPostgresPassword() {
		return newPostgresPassword;
	}

	public void setNewPostgresPassword(String newPostgresPassword) {
		this.newPostgresPassword = newPostgresPassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPublicIp() {
		return publicIp;
	}

	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}

	public String getAxisCam() {
		return axisCam;
	}

	public void setAxisCam(String axisCam) {
		this.axisCam = axisCam;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImeino() {
		return imeino;
	}

	public void setImeino(String imeino) {
		this.imeino = imeino;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public String getEmpoly_cod() {
		return empoly_cod;
	}

	public void setEmpoly_cod(String empoly_cod) {
		this.empoly_cod = empoly_cod;
	}

	public String getEmpoly_div() {
		return empoly_div;
	}

	public void setEmpoly_div(String empoly_div) {
		this.empoly_div = empoly_div;
	}

	public String getEmpoly_rang() {
		return empoly_rang;
	}

	public void setEmpoly_rang(String empoly_rang) {
		this.empoly_rang = empoly_rang;
	}

	public String getEmpoly_beat() {
		return empoly_beat;
	}

	public void setEmpoly_beat(String empoly_beat) {
		this.empoly_beat = empoly_beat;
	}

	public String getEmpoly_cercl() {
		return empoly_cercl;
	}

	public void setEmpoly_cercl(String empoly_cercl) {
		this.empoly_cercl = empoly_cercl;
	}

	public String getEmpoly_desig() {
		return empoly_desig;
	}

	public void setEmpoly_desig(String empoly_desig) {
		this.empoly_desig = empoly_desig;
	}

	public String getJoningdate() {
		return joningdate;
	}

	public void setJoningdate(String joningdate) {
		this.joningdate = joningdate;
	}

	public String getEmpoly_photo() {
		return empoly_photo;
	}

	public void setEmpoly_photo(String empoly_photo) {
		this.empoly_photo = empoly_photo;
	}

	public String getEmpoly_stat() {
		return empoly_stat;
	}

	public void setEmpoly_stat(String empoly_stat) {
		this.empoly_stat = empoly_stat;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
}
