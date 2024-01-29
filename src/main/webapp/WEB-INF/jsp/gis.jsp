<!--
	File    : GIS.JSP
	Version : 0.0.1
	Author  : Mukesh Rout
	Note 	: DON'T FORMAT THE CODE. 
-->
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="com.gis.model.Layer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.gis.util.StringUtils"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="java.util.Date"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String gisId = (String) request.getParameter("gisId");
String layer = (String) request.getParameter("layer");
String style = "style2";

final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

String IP = (String) getServletContext().getAttribute("IP");
String WORKSPACE = (String) getServletContext().getAttribute("WORKSPACE");
String PUBLIC_IP = (String) getServletContext().getAttribute("PUBLIC_IP");
String PRIVATE_LABEL = (String) getServletContext().getAttribute("PRIVATE_LABEL");

Map<String, String> layerList = (Map) session.getAttribute("allowedLayerList");

String userName = (String) session.getAttribute("userName");
int userId = (Integer) session.getAttribute("userId");
String permission = (String) session.getAttribute("permission");
String admin = (String) session.getAttribute("isadmin");

String contextPath = request.getContextPath();

String appVersion = (String) session.getAttribute("appVersion");

String fromFire = (String) request.getAttribute("fromFire");
if (fromFire == null)
	fromFire = "N";

String route = (String) request.getAttribute("route");
if (route == null)
	route = "N";

String fromRisk = (String) request.getAttribute("fromRisk");
if (fromRisk == null)
	fromRisk = "N";
%>
<!DOCTYPE html>
<html>
<jsp:include page="../jsp/common/jscss.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap-toggle.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/gis.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ol.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap-toggle.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/lightgallery.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/jquery/css/jquery-ui.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-select.min.css" />
<body onload="loadNotifications('first');">

	<jsp:include page="../jsp/common/navbar.jsp">
		<jsp:param value="gis" name="page" />
	</jsp:include>
	<jsp:include page="../jsp/common/sidebar.jsp">
		<jsp:param value="gis" name="page" />
		<jsp:param value="gis" name="currentPage" />
	</jsp:include>
	<main id="main-gis" class="main-gis">
		<section class="section dashboard">
			<div id="map" class="map"></div>
		</section>
	</main>
	<!-- End #main -->
	<div id="live_location_div">
		<div class="badge rounded-pill bg-light">
			<a href="#" id="live_location" onclick="isLive()"> <i
				id="loc_icon" class="fa fa-circle icon-red"></i> Live
			</a> <span id="live_total"></span> <input type="hidden" id="isLive"
				value="N">
		</div>
		<%-- <a href="#" id="cur_loc" onclick="backToCenter(14);"> <img
				src="${pageContext.request.contextPath}/images/logo.png" alt="LOGO"
				height="80px;" style="background: #f4f4f4; border-radius: 100%;" />
			</a>  <br> 
			<br> <a href="#" id="show-new-users"
				title="New Registered Users"> <span
				class="badge rounded-pill bg-primary"
				style="float: right; margin-right: 25px;"> <span
					id="new-users">0</span> <i class="fa fa-users"></i>
			</span>
			</a> &nbsp; <a href="#" id="show-notification" title="Media Notification">
				<span class="badge rounded-pill bg-danger"
				style="float: right; margin-right: 25px;"> <span
					id="new-notification">0</span> <i class="fa fa-bell"></i>
			</span>
			</a> &nbsp; <a href="#" id="show-live-user" title="New Live User"> <span
				class="badge rounded-pill bg-success"
				style="float: right; margin-right: 25px;"> <span
					id="new-live-user">0</span> &nbsp;<i class="fa fa-circle"></i>
			</span>
			</a> --%>
	</div>
	<div id="filter_div" style="display: none;">
		<div class="card card-custom">
			<div class="card-body" id="filterBody">
				<div class="row">
					<div class="col-lg-12 form-group">
						<label style="margin-bottom: 0px;">Layer</label> <input
							type="hidden" id="totalcondition" value="0" /> <select
							class="form-control form-control-custom"
							onchange="loadAttibutes(this.value)" id="match">
							<option value="">Please select</option>
							<%
							for (Map.Entry<String, String> entry : layerList.entrySet()) {
							%>
							<option value="<%=entry.getKey().toUpperCase()%>"><%=entry.getValue().toUpperCase()%></option>
							<%
							}
							%>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6 form-group">
						<label style="margin-bottom: 0px;"> Attribute <span
							id="attribute_type_-1"></span>
						</label> <select class="form-control form-control-custom"
							id="attributeFields"></select>
					</div>
					<div class="col-lg-6 form-group">
						<label style="margin-bottom: 0px;">Condition</label> <select
							class="form-control form-control-custom" id="condition"
							onchange="changeField(this.value,'')">
							<option value=""></option>
							<option value="=">=</option>
							<option value="&lt;&gt;">&lt;&gt;</option>
							<option value="&lt;">&lt;</option>
							<option value="&gt;">&gt;</option>
							<option value="&lt;=">&lt;=</option>
							<option value="&gt;=">&gt;=</option>
							<option value="like">like</option>
							<option value="between">between</option>
						</select>
					</div>
				</div>
				<div class="row">
					<label style="margin-bottom: 0px;" class="col-lg-12">Value</label>
					<div id="notbetween">
						<div class="col-lg-12 form-group">
							<input type="text" class="form-control form-control-custom"
								id="searchfield">
						</div>
					</div>
					<div id="between" style="display: none;">
						<div class="col-lg-12 form-group">
							<input type="text" class="form-control form-control-custom"
								id="searchfieldbetween1">
						</div>
						<div class="col-lg-12 form-group">
							<input type="text" class="form-control form-control-custom"
								id="searchfieldbetween2">
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer">
				<div class="row">
					<div class="col-lg-12 form-group" style="text-align: center;">
						<button type="submit" class="btn btn-xs btn-success"
							onclick="searchFilter();">
							<i class="fa fa-search"></i> Search
						</button>
						<button type="submit" class="btn btn-xs btn-danger"
							onclick="resetFilter();">
							<i class="fa fa-retweet"></i> Clear
						</button>
						<a href="javascript:addNewCondition()"
							class="btn btn-primary btn-xs"><i class="fa fa-plus"></i> Add
							Condition</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="legend_div" class="card card-custom" style="display: none;">
		<h5 class="card-title" id="legend_heading"></h5>
		<div id="legend_body"></div>
	</div>
	<div id="spath_div" style="display: none;">
		<div class="card card-custom">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-12 form-group">
						<label>Source <a href="javascript:setSourcePin()"
							class="fa fa-map-marker icon-green" style="font-size: 20px;"></a></label>
						<div class="leaflet-routing-geocoder">
							<input type="text" class="form-control form-control-custom"
								id="sourcelocation"> <input type="hidden"
								id="sourcelocationll"> <input type="hidden"
								id="route_id">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 form-group">
						<label>Destination <a
							href="javascript:setDestinationPin()"
							class="fa fa-map-marker icon-red" style="font-size: 20px;"></a>
						</label> <input type="text" class="form-control form-control-custom"
							id="destinationlocation"> <input type="hidden"
							id="destinationlocationll">
					</div>
				</div>
			</div>
			<div class="card-footer">
				<div class="row">
					<div class="col-lg-12 form-group" style="text-align: center;">
						<button type="submit" class="btn btn-xs btn-danger"
							onclick="resetShortestPath();">
							<i class="fa fa-retweet"></i> Clear
						</button>
						<button type="submit" class="btn btn-xs btn-success"
							onclick="findShortestPath()">
							<i class="fa fa-search"></i> Find
						</button>
					</div>
				</div>
				<div class="row">
					<b style="display: none;" class="distance_time pull-right"></b>
				</div>
			</div>
		</div>
	</div>
	<div id="attribute_div">
		<div id="attribute_data" style="height: 90%"></div>
	</div>
	<div id="popup" class="ol-popup"
		style="height: auto; background-color: #eee;">
		<div id="popup-content" class=""></div>
		<a id="popup-closer" href="#" onclick="closePopup()"
			class="btn btn-danger btn-xs">Close</a>
	</div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- MESSAGE MODAL START-->
	<div id="messageModal" class="modal fade" role="dialog"
		style="z-index: 2000;">
		<div class="modal-dialog modal-dialog-custom">
			<div class="modal-content">
				<div class="modal-body">
					<div class="message-div">
						<div id="message-appender"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- MESSAGE MODAL END-->
	<!-- LOADING MODAL START-->
	<div id="loadingModal" class="modal fade" role="dialog"
		style="top: 35%; z-index: 2000;" data-backdrop="static"
		data-keyboard="false">
		<div class="modal-dialog" style="width: 10%">
			<div class="modal-content"
				style="border: 0px; background-color: transparent;">
				<div class="modal-body">
					<img alt=""
						src="${pageContext.request.contextPath}/images/loading3.gif"
						height="70px" width="70px" style="margin-left: 10px;">
				</div>
			</div>
		</div>
	</div>

	<!-- MODAL IMAGE PREVIEW START-->
	<div id="previewImageModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="image-gallery">
						<div id="image_appender"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- MODAL IMAGE PREVIEW END-->

	<!-- MODAL ATTRIBUTE START-->
	<div id="attributeDetailsModal" class="modal fade" role="dialog"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">
						<i class="fa fa-map-o icon-green"></i> <span id="attrHeading"></span>
					</h4>
					<button type="button" class="close" data-dismiss="modal"
						style="color: red;" onclick="closeModal('attr')">
						<i class="fa fa-close fa-2x"></i>
					</button>
				</div>

				<div class="modal-body" style="padding: 0px;">
					<div id="attributeDetails"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- MODAL ATTRIBUTE END-->

	<script src="${pageContext.request.contextPath}/lib/jquery/js/jQueryv2.0.3.js"></script>
	<script src="${pageContext.request.contextPath}/js/custom.js"></script>
	<script src="${pageContext.request.contextPath}/js/ol.js"></script>
	<script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap-toggle.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places&key=AIzaSyDb3lOuIEGiDMPtLxmsmNXVCKbodr9chqg"></script>
	<script src="${pageContext.request.contextPath}/js/turf.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/gis.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.datetimepicker.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-add-clear.min.js"></script>
	<script src="${pageContext.request.contextPath}/lib/jquery/js/jquery-ui.js"></script>
	<script src="${pageContext.request.contextPath}/js/angular.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.slimscroll.min.js" type="text/javascript"></script>

	<!-- Template Main JS File -->
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>