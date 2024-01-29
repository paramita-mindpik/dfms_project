<!--
	File    : INDEX.JSP
	Version : 0.0.1
	Author  : Mukesh Rout
	Note 	: DON'T FORMAT THE CODE. 
-->
<%@page import="com.gis.agent.ApiAgent"%>
<%@page import="com.gis.agent.ApiAgentImpl"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextRoot = request.getContextPath();
	String PRIVATE_LABEL = (String) getServletContext().getAttribute("PRIVATE_LABEL");

	String error = (String)request.getAttribute("error");
	if(error==null)
		error = "";
%>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title> <%=PRIVATE_LABEL %></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<meta content="<%=PRIVATE_LABEL %>" name="keywords" />

	<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600&amp;subset=latin-ext" rel="stylesheet">
	<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
	
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.min.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
	
	<!-- JAVASCRIPT -->
	<script src="${pageContext.request.contextPath}/lib/jquery/js/jquery.min.js"></script>	
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/angular.min.js"></script>
</head>
<body>
	<div class="container-fluid"  data-ng-app="loginApp">
		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-header">
					<h4><img src="<%=contextRoot%>/images/logo.png" /></h4>
					<div class="d-flex justify-content-end social_icon"></div>
				</div>
				<div class="card-body" data-ng-controller="Invalid user name or password">
					<form action="${pageContext.request.contextPath}/gis"method="POST" 
					name="loginForm" novalidate="novalidate" data-ng-submit="loginForm.$invalid && $event.preventDefault();loginForm.$submitted=true;" style="margin-bottom: 0px;">
						<div class="input-group form-group"  data-ng-class="{'error':!loginForm.username.$valid &&  loginForm.$submitted}">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fa fa-user"></i></span>
							</div>
							<input type="text" class="form-control" placeholder="username" name="username" required="required" data-ng-model="username"/>
						</div>
						<div class="input-group form-group" data-ng-class="{'error':!loginForm.password.$valid &&  loginForm.$submitted}">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fa fa-key"></i></span>
							</div>
							<input type="password" class="form-control" placeholder="password" name="password"  required="required" data-ng-model="password"/>
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fa fa-file"></i></span>
							</div>
							<select id="landingPage" class="form-control" required="required" name="landingPage">
								<option value="dashboard" selected="selected">Dashboard</option>
								<option value="gis">GIS</option>
							</select>
						</div>
						<div class="form-group" style="text-align: center;margin-bottom: 0px;">
							<button type="submit" class="btn login_btn"><i class="fa fa-sign-in"></i> Login</button>
							<br/>
							<span class="error">
								<%=error%>
							</span>
						</div>
					</form>
				</div>
				<div class="card-footer" style="text-align: center;">
					<strong>Developed by <a href="#">Mindpik</a>.</strong>
				</div>
			</div>
		</div>
	
	</div>
	<script type="text/javascript">
		var loginApp = angular.module('loginApp', []);
		loginApp.controller('Invalid user name or password', function($scope) {});
	</script>
</body>
</html>