<!--
	File    : GIS.JSP
	Version : 0.0.1
	Author  : Mukesh Rout
	Note 	: DON'T FORMAT THE CODE. 
-->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String PRIVATE_LABEL = (String) getServletContext().getAttribute("PRIVATE_LABEL");

	String userName = (String)session.getAttribute("userName");
	int userId = (Integer)session.getAttribute("userId");
	String permission = (String)session.getAttribute("permission");
	String admin = (String)session.getAttribute("isadmin");
	String token = (String)session.getAttribute("token");
	
	String contextPath = request.getContextPath();
	
	String x = (String)request.getAttribute("xcord");
	
	String y = (String)request.getAttribute("ycord");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="kolkatapolice" content="<%=token%>">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title>DIRECTION</title>
	<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600&amp;subset=latin-ext" rel="stylesheet">
	<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/jquery/css/jquery-ui.css"/>
	
	<script src="${pageContext.request.contextPath}/lib/jquery/js/jQueryv2.0.3.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places&key=AIzaSyDb3lOuIEGiDMPtLxmsmNXVCKbodr9chqg"></script>
    <style type="text/css">
   		html, body {
          height: 100%;
          margin: 0;
          padding: 0;
      }
      
      .map-container {
		    position: relative;
		}
		
		img {
		   position: absolute;
			top: 0;
			right: 0;
			margin-left: -50px;
			height: 89px;
		}
		.map {
    height: 650px;
    background-color: #ccc;
}
    </style>
</head>
<body> 
	<div class="map-container">
	    <div id="google-map" class="map"></div>
	    <img src="<%=contextPath%>/images/logo.png">
	</div>
	
	<script type="text/javascript">
		/*GOOGLE DIRECTION*/
		function gDirection() {
			 var pointA = new google.maps.LatLng(<%=x%>),
		   		pointB = new google.maps.LatLng(<%=y%>),
			  	myOptions = {
			       zoom: 7,
			       center: pointA,
			       fullscreenControl: false
			   },
		   	gmap = new google.maps.Map(document.getElementById('google-map'), myOptions),
	
		   directionsService = new google.maps.DirectionsService,
		   directionsDisplay = new google.maps.DirectionsRenderer({
		       map: gmap
		   }),
		   markerA = new google.maps.Marker({
		       position: pointA,
		       map: gmap
		   }),
		   markerB = new google.maps.Marker({
		       position: pointB,
		       map: gmap
		   });
			
			calculateAndDisplayRoute(directionsService, directionsDisplay, pointA, pointB);	 
		}
	
		function calculateAndDisplayRoute(directionsService, directionsDisplay, pointA, pointB) {
		   directionsService.route({
		       origin: pointA,
		       destination: pointB,
		       avoidTolls: true,
		       avoidHighways: false,
		       travelMode: google.maps.TravelMode.DRIVING
		   }, function (response, status) {
		       if (status == google.maps.DirectionsStatus.OK) {
		           directionsDisplay.setDirections(response);
		       } else {
		       		console.log('Directions request failed due to ' + status);
		       }
		   });
		}
		gDirection();
	</script>
</body>
</html>