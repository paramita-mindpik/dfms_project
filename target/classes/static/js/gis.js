/****  GIS.JS *****/
/****  Version: v0.0.1***/
/** ** Author : Mukesh Rout ** */

/* VARIABLE DECLARATION */
var contextRoot = location.origin+'/'+location.pathname.split('/')[1];
var userId;
var ip;
var public_ip;
var workspace;
var token;
var axis;
var defaultZoom = 14;

var infoFlag = false;
var attributeFlag = true;
var moveFeatureFlag = false;
var liveLocationFlag = true;
var setSource = false;
var setDestination = false;
var activeList;
var inactiveList;
var totalUsers;
var totalFiles;
var inspectorsLive = '';

var lastUsers = 0 ;
var lastFiles = 0 ;
var mediaObject = '';
var lastId;
var regObject = '';
var logEnable = true;
var cursor = false;
var spath = false;
var filterLayer = false;
var buffer = false;

/* ALL LAYER ARRAY DECLARATION */
var all_layers = [];

var draw;

var colors =  ["#00FFFF","#0000FF","#ADD8E6","#800080","#00FF00","#A52A2A","#800000","#008000","#808000","#34282C","#25383C","#837E7C","#B6B6B4","#D1D0CE","#BCC6CC","#98AFC7","#6D7B8D","#566D7E","#737CA1","#4863A0","#2B547E","#2B3856","#151B54","#000080","#342D7E","#15317E","#0000A0","#2554C7","#1569C7","#6960EC","#736AFF","#357EC7","#368BC1","#488AC7","#3090C7","#659EC7","#87AFC7","#95B9C7","#728FCE","#2B65EC","#306EFF","#157DEC","#1589FF","#6495ED","#6698FF","#38ACEC","#56A5EC","#5CB3FF","#3BB9FF","#79BAEC","#82CAFA","#82CAFF","#A0CFEC","#B7CEEC","#B4CFEC","#C2DFFF","#C6DEFF","#AFDCEC","#ADDFFF","#BDEDFF","#CFECEC","#E0FFFF","#EBF4FA","#F0F8FF","#F0FFFF","#CCFFFF","#93FFE8","#9AFEFF","#7FFFD4","#7DFDFE","#57FEFF","#8EEBEC","#50EBEC","#4EE2EC","#81D8D0","#92C7C7","#77BFC7","#78C7C7","#43C6DB","#7BCCB5","#43BFC7","#3EA99F","#3B9C9C","#348781","#307D7E","#5E7D7E","#4C787E","#008080","#78866B","#848b79","#617C58","#728C00","#667C26","#306754","#347235","#437C17","#387C44","#347C2C","#348017","#4E9258","#6AA121","#4AA02C","#3EA055","#6CBB3C","#4CC417","#52D017","#4CC552","#54C571","#99C68E","#89C35C","#85BB65","#8BB381","#9CB071","#B2C248","#9DC209","#A1C935","#7FE817","#57E964","#5EFB6E","#5FFB17","#8AFB17","#6AFB92","#B5EAAA","#C3FDB8","#CCFB5D","#B1FB17","#BCE954","#EDDA74","#FFE87C","#FFFF00","#FFF380","#FFF8DC","#F3E5AB","#ECE5B6","#FFDB58","#FFD801","#EAC117","#F2BB66","#FBB117","#E9AB17","#E2A76F","#DEB887","#FFCBA4","#C9BE62","#E8A317","#EE9A4D","#C8B560","#D4A017","#C2B280","#C7A317","#C68E17","#B5A642","#ADA96E","#C19A6B","#CD7F32","#C58917","#AF9B60","#AF7817","#B87333","#966F33","#806517","#827839","#827B60","#786D5F","#493D26","#483C32","#6F4E37","#835C3B","#7F5217","#7F462C","#C47451","#C35817","#CC6600","#E56717","#F87431","#E67451","#FF8040","#F88017","#FF7F50","#F9966B","#E77471","#F75D59","#E55451","#E55B3C","#FF0000","#E42217","#DC381F","#C34A2C","#C04000","#C11B17","#9F000F","#954535","#7E3517","#8A4117","#7E3817","#800517","#810541","#7E354D","#7D0552","#7F4E52","#7F5A58","#7F525D","#B38481","#C5908E","#C48189","#C48793","#E8ADAA","#ECC5C0","#EDC9AF","#FDD7E4","#FFDFDD","#FBBBB9","#FAAFBE","#FAAFBA","#F9A7B0","#E7A1B0","#E799A3","#E38AAE","#F778A1","#E56E94","#F660AB","#FC6C85","#F6358A","#F52887","#E45E9D","#E4287C","#F535AA","#FF00FF","#E3319D","#F433FF","#D16587","#C25A7C","#CA226B","#C12267","#C25283","#C12283","#B93B8F","#7E587E","#571B7E","#583759","#4B0082","#461B7E","#4E387E","#614051","#5E5A80","#6A287E","#7D1B7E","#A74AC7","#B048B5","#6C2DC7","#842DCE","#8D38C9","#7A5DC7","#7F38EC","#8E35EF","#893BFF","#8467D7","#A23BEC","#B041FF","#C45AEC","#9172EC","#9E7BFF","#D462FF","#E238EC","#C38EC7","#C8A2C8","#E6A9EC","#E0B0FF","#C6AEC7","#F9B7FF","#D2B9D3","#E9CFEC","#EBDDE2"];

/* MAP ONLOAD CENTER LAT LONG */
var mapCoord = ol.proj.fromLonLat([87.0280262,23.229236]);

/* VECTOR LAYER FOR MARKER */
var markerVector;
var dynamicVector;
var dynamicVectorLive; 
var liveTimer;
var reloadtime = 120000;
var markerSourceVector;
var source;
var markerDestinationVector;
var vectorLayerRoute;
var vectorSourceRoute;
var locateOnMap;
var locateOnMapSource;

/*OSRM VARIABLE DECLARATION*/
var points = [],
url_osrm_nearest = '//router.project-osrm.org/nearest/v1/driving/',
url_osrm_route = '//router.project-osrm.org/route/v1/driving/'

/* MEASUREMENT DECLARATION */
var sketch;
var helpTooltipElement;
var helpTooltip;
var measureTooltipElement;
var measureTooltip;

var scaleLineControl = new ol.control.ScaleLine();

/* POPUP ELEMENST */
var container = document.getElementById('popup');
var content = document.getElementById('popup-content');
var closer = document.getElementById('popup-closer');

/* MEAN POSTION VARIABLES */
var features;

/* CSRF TOKEN */
var token = $("meta[name='rdd']").attr("content");

var pointsArray;

/*Animation Variables*/
var f ;
var anim;
var vector;

/* MAP BUTTONS ON/OFF DECLARATION */
$('#gmap_layer').change(function() {
	if($(this).prop('checked')){
		$('#satelite_button').prop('checked', false).change();

		googleLayer.setVisible(true);
		googleSatLayer.setVisible(false);
		$('#gmap_layer_li').addClass('icon-green');
		$('#gmap_layer_li').removeClass('icon-red');
	}else{
		googleLayer.setVisible(false);
		$('#gmap_layer_li').addClass('icon-red');
		$('#gmap_layer_li').removeClass('icon-green');
	}
});

$('#satelite_button').change(function() {
	if($(this).prop('checked')){
		$('#gmap_layer').prop('checked', false).change();

		googleLayer.setVisible(false);
		googleSatLayer.setVisible(true);
		$('#satelite_button_li').addClass('icon-green');
		$('#satelite_button_li').removeClass('icon-red');
	}else{
		googleSatLayer.setVisible(false);
		$('#satelite_button_li').removeClass('icon-green');
		$('#satelite_button_li').addClass('icon-red');
	}
});

/* BUTTONS ON/OFF DECLARATION */
$('#atr_button').change(function() {
	if($(this).prop('checked')){
		attributeFlag = true;
	}else{
		$("#attribute_data").empty();
		attributeFlag = false;
	}
});

/*LINE,POINT POLYGON,XY* */
function measurement(item){
	if(item=='D'){
		infoFlag = false;
		closePopup();
		
		if($('#point_button').prop('checked') == true){
		 	$('#point_button').prop('checked',false);
		 	map.removeInteraction(draw);
		 }
		else if($('#point_button').prop('checked') == false){
			$('#point_button').prop('checked',true);
			
			$('#polygon_length_button').prop('checked', false).change();
			$('#length_button').prop('checked', false).change();
			$('#atr_button').prop('checked', false).change();
			$('#xy_button').prop('checked', false).change();
			map.removeInteraction(draw);
			measurementType('Point');
		}else{
			map.removeInteraction(draw);
		}
	} else if(item=='L'){
		infoFlag = false;
		closePopup();
		
		if($('#length_button').prop('checked') == true){
		 	$('#length_button').prop('checked',false);
		 	map.removeInteraction(draw);
		 }
		else if($('#length_button').prop('checked') == false){
			$('#length_button').prop('checked',true);
			
			$('#polygon_length_button').prop('checked', false).change();
			$('#atr_button').prop('checked', false).change();
			$('#xy_button').prop('checked', false).change();
			map.removeInteraction(draw);
			measurementType('LineString');
		}
	} else if(item=='P'){
		if($('#polygon_length_button').prop('checked') == true){
		 	$('#polygon_length_button').prop('checked',false);
		 	map.removeInteraction(draw);
		 }
		else if($('#polygon_length_button').prop('checked') == false){
			$('#length_button').prop('checked',true);
			
			$('#length_button').prop('checked', false).change();
			$('#atr_button').prop('checked', false).change();
			$('#xy_button').prop('checked', false).change();
			map.removeInteraction(draw);
			measurementType('Polygon');
		}
	}else if(item=='X'){
		if($('#xy_button').prop('checked') == true){
		 	$('#xy_button').prop('checked',false);
		 	
		 	infoFlag = false;
			attributeFlag = false;
			closePopup();
		 }
		else if($('#xy_button').prop('checked') == false){
			$('#xy_button').prop('checked',true);
			
			$('#atr_button').prop('checked', false).change();
			$('#length_button').prop('checked', false).change();
			$('#polygon_length_button').prop('checked', false).change();
			infoFlag = true;
			attributeFlag = false;
		}
	}
}

/* MAP ONLOAD ZOOM VIEW */
var view = new ol.View({
	center : mapCoord,
	zoom : defaultZoom
});

/* MOUSE HOVER LAT LONG POSITION SETTING ON UI */
var mousePositionControl = new ol.control.MousePosition({
	coordinateFormat : ol.coordinate.createStringXY(4),
	projection : 'EPSG:4326',
	className : 'custom-mouse-position',
	target : document.getElementById('mouse-position'),
	undefinedHTML : '&nbsp;'
});

/* POPUP OBJECT DECLARATION */
var popup = new ol.Overlay({
	element : container,
	name : 'popup',
	autoPan : true,
	autoPanAnimation : {
		duration : 250
	}
});

/* MAP DECLARATION */
var map = new ol.Map({
	target : 'map',
	overlays : [ popup],
	loadTilesWhileAnimating : true,
	view: view
});

/*OPEN STREET MAP DECLARATION & ADDING TO MAP LAYER*/ 
var openstreetmap = new ol.layer.Tile({
	preload : 4,
	source : new ol.source.OSM()
});
map.addLayer(openstreetmap);
openstreetmap.set('name', 'osm_layer');
openstreetmap.setVisible(false);

/* GOOGLE MAP DECLARATION */
var googleLayer = new ol.layer.Tile({
    source: new ol.source.OSM({
        url: 'http://mt{0-3}.google.com/vt/lyrs=m&x={x}&y={y}&z={z}'
    })
});
googleLayer.set('name', 'gmap_layer');
googleLayer.setVisible(true);
map.addLayer(googleLayer);

/* GOOGLE SATELLITE MAP DECLARATION */
var googleSatLayer = new ol.layer.Tile({
    source: new ol.source.OSM({
        url: 'http://mt{0-3}.google.com/vt/lyrs=s&x={x}&y={y}&z={z}'
    })
});
googleSatLayer.set('name', 'gmap_sat_layer');
googleSatLayer.setVisible(false);
map.addLayer(googleSatLayer);

/* VECTOR LAYER DECALARATION FOR DRAWING */ 
var vectorSource = new ol.source.Vector({wrapX: false});
var vectorLayer = new ol.layer.Vector({
	source : vectorSource
});
map.addLayer(vectorLayer);

/* LENGTH CALCULATE */
var formatLength = function(line) {
	var length = Math.round(line.getLength() * 100) / 100;
	var output;
	if (length > 100) {
		output = (Math.round(length / 1000 * 100) / 100) + ' ' + 'km';
	} else {
		output = (Math.round(length * 100) / 100) + ' ' + 'm';
	}
	return output;
};

/* AREA CALCULATE */
var formatArea = function(polygon) {
	var area = polygon.getArea();
	var output;
	if (area > 10000) {
		output = (Math.round(area / 1000000 * 100) / 100) + ' '
				+ 'km<sup>2</sup>';
	} else {
		output = (Math.round(area * 100) / 100) + ' ' + 'm<sup>2</sup>';
	}
	return output;
};

/* ON MAP CLICK EVENT */
map.on('click',function(evt) {
	if (infoFlag) {
		var coordinate = evt.coordinate;
		var hdms = ol.proj.transform(coordinate,'EPSG:3857', 'EPSG:4326');
		content.innerHTML = '<p class="center_text" style="margin-top: 10px;"><b>XY-Information:</b><br/><code style="font-size: 14px;">'+ hdms[0].toPrecision(10)+', '+hdms[1].toPrecision(10) + '</code></p>';
		popup.setPosition(coordinate);
		$("#popup-content").removeClass('scrollTable');
		$('#popup-closer').show();
	}
	
	if(buffer){
		var coordinate = evt.coordinate;
		var hdms = ol.proj.transform(coordinate,'EPSG:3857', 'EPSG:4326');
		content.innerHTML = '<input type="text" class="form-control" placeholder="Buffer Area"/> <input type="hidden" value="'+hdms[0].toPrecision(10)+','+hdms[1].toPrecision(10)+'"/><button class="btn btn-success btn-xs">Create Buffer</button>';
		popup.setPosition(coordinate);
		$("#popup-content").removeClass('scrollTable');
		$('#popup-closer').show();
	}

	$("#attribute_data").empty();
	$('#accordionAV').empty();

	if (attributeFlag) {
		var coordinate = evt.coordinate;
		createPopupPanel(evt.coordinate);
	}
	
	if(true){
		if(setSource){
			var coordinate = evt.coordinate;
			var array = ol.proj.transform(coordinate,'EPSG:3857', 'EPSG:4326');
			$.ajax({
				url : 'https://nominatim.openstreetmap.org/reverse?lat='+array[1]+'&lon='+array[0]+'&format=json&json_callback=_l_geocoder_source',
				dataType: 'jsonp',
				type : "GET",
				success : function(returndata) {}		
			});
			$('#sourcelocationll').val(array[0]+","+array[1]);
			setSource = false;
	
			map.removeLayer(vectorLayerRoute);
			
			vectorSourceRoute = new ol.source.Vector(),
			vectorLayerRoute = new ol.layer.Vector({
			  source: vectorSourceRoute
			}),
	
			map.addLayer(vectorLayerRoute);
			
			//CREATE START POINT
			utils.createFeature(array,routestyles.icon_start);
		}
	
	if(setDestination){
		var coordinate = evt.coordinate;
		var array = ol.proj.transform(coordinate,'EPSG:3857', 'EPSG:4326');
		$.ajax({
			url : 'https://nominatim.openstreetmap.org/reverse?lat='+array[1]+'&lon='+array[0]+'&format=json&json_callback=_l_geocoder_destination',
			dataType: 'jsonp',
			type : "GET",
			success : function(returndata) {}		
		});
		
		$('#destinationlocationll').val(array[0]+","+array[1]);
		setDestination = false;
		
		//CREATE START POINT
		utils.createFeature(array,routestyles.icon_end);
	}else{
			var feature = map.forEachFeatureAtPixel(evt.pixel,function(feature) {return feature;});
			if (feature) {
				var coordinates = feature.getGeometry().getCoordinates();
		    	var style = feature.get('style');
		    	var name = feature.get('name');
				content.innerHTML = name;
				if(style!=undefined){
					$("#attribute_data").append(name);
					$("#attribute_div").addClass('scrollTable');
		    	}
			}
		}
	}
});

/*POPUP PANEL DECLARATION*/
function createPopupPanel(coordinate){
	var viewResolution = (view.getResolution());
	
	$("#attribute_data").empty();
	
	var url = '';
	var outerpanel = '<div class="panel-group" id="popup-accordion" style="height: 90%;">';
	var layerOpen = false;
	
	all_layers.forEach(function(layer, i, layers) {
		if (layer.getVisible() && layer.get('name') != 'Basemap') {
			layerOpen = true;
			console.log(layer.getSource());
			url = layer.getSource().getFeatureInfoUrl(
				coordinate,
				viewResolution,
				'EPSG:3857',
				{
					'INFO_FORMAT' : 'application/json',
					'FEATURE_COUNT' : '300'
				}
			);
			url = url.replace(public_ip ,ip);
			if (url) {
				outerpanel = outerpanel+ '<div id="panel_div'+ i+ '" class="panel panel-primary panel-collapsed" style="margin: 1px 1px 1px 1px;border: 0px solid transparent;"><div class="panel-heading" style="padding:5px 13px;border-top-left-radius: 0px;border-top-right-radius: 0px;" id="panel-heading'+ i+ '">';
				outerpanel = outerpanel+ '<a href="#" onclick="closePanel('+i+');" class="pull-right"><span class="fa fa-remove icon-red"></span></a>';
				outerpanel = outerpanel+ '<a class="btn-block" style="color: #fff;font-size: 14px;" data-toggle="collapse" data-parent="#popup-accordion"  href="#" onclick="collapse('+ i+ ',\''+ url+ '\',\''+ layer.get('name').toUpperCase()+ '\')">'+ layer.get('name').toUpperCase()+ '<span class="pull-right"><i class="fa fa-chevron-down"></i> &nbsp;&nbsp;&nbsp;</span></a></div>';
				outerpanel = outerpanel+ '<div id="panel'+ i+ '" class="panel-collapse panel-collapsed">';
				outerpanel = outerpanel+ '<div class="data'+ i+ ' collapse" style="background: white;"></div></div></div>';
			}
		}
	});
	
	if(layerOpen){
		$("#attribute_data").append(outerpanel);
		$("#attribute_div").addClass('scrollTable');
	}
}

/*CLOSE PANEL*/
function closePanel(i){
	$('#panel_div'+i).hide();
}

/*POPUP LAYER DETAIL PANEL DECALARATION*/
function collapse(id, url,layername) {
	var panel = "#panel" + id;
	var panelheading = "#panel-heading" + id;
	var data = ".data" + id;
	if (!$(panel).hasClass('panel-collapsed')) {
		$(panel).parents('.panel').find('.data').slideUp();
		$(panel).parents('.panel').find('.data' + id).removeClass('show');
		$(panel).addClass('panel-collapsed');
		$(panelheading).find('i').removeClass('fa-chevron-up').addClass('fa-chevron-down');
	} else {
		$(panel).parents('.panel').find('.data' + id).addClass('show');
		$(panel).parents('.panel').find('.data').slideDown();
		$(panel).removeClass('panel-collapsed');
		$(panelheading).find('i').removeClass('fa-chevron-down').addClass('fa-chevron-up');
		$(data).empty();
		
		$.ajaxSetup({
			async : false
		});

		var canedit = 'N';
		
		var target_url = contextRoot + "/layer";
		
		$.ajax({
			url : target_url,
			type : "POST",
			contentType: "application/json",
	        data: JSON.stringify({"url": url ,"token":token}),
			success : function(returndata) {
				var json = JSON.stringify(eval(returndata));
				var jsonData = jQuery.parseJSON(json).features;
				var innerpanel = '';
				var gid = '';
				for (var a = 0; a < jsonData.length; a++) {
					var id = jsonData[a].id;
					var type = jsonData[a].geometry.type;
					var coordinates = jsonData[a].geometry.coordinates;
					if(a==0)
						gid = id.split(".");
					
					var panleHeading = id;
					innerpanel = innerpanel+ '<div class="panel panel-warning panel-collapsed"><div class="panel-heading" style="padding:5px 13px;" id="panel-heading-inner'+layername+ a+ '"><a class="btn-block" style="color: #c9302c;font-size: 14px;" data-toggle="collapse" href="#" onclick="collapseinner(\''+layername+ a+ '\')">'+ panleHeading+ ' <span class="pull-right"><i class="fa fa-chevron-down"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></div>';
					innerpanel = innerpanel+ '<div id="panel-inner'+layername+ a + '" class="panel-collapse panel-collapsed">';
					innerpanel = innerpanel+ '<div class="data-inner'+layername + a + ' collapse"><table class="table table-bordered" style="font-size: 10px;margin-bottom: 0px;" id="layer_details">';
					var status;
					var ip;
					for ( var i in jsonData[a].properties) {
						var fieldvalue = jsonData[a].properties[i];
						if(fieldvalue instanceof Object) {
							fieldvalue = '';
						}
						innerpanel = innerpanel + '<tr><td><b>' + i+ '</b></td><td> '+fieldvalue + '</td></tr>';
						if (i.toUpperCase() == 'STATUS' && jsonData[a].properties[i].length > 0) {
							status = jsonData[a].properties[i].trim();
						} else if (i.toUpperCase() == 'CAMERA_IP' && jsonData[a].properties[i].length > 0) {
							ip = jsonData[a].properties[i].trim();
						}
					}
					
					var showCamera = '';
					
					var previewImage = '&nbsp;<a class="btn btn-default btn-xs" title="PREVIEW" href="javascript:imagePreview(\''+gid[1]+'\',\''+layername+'\')"><i class="fa fa-image icon-purple"></i> Preview</a>';
					innerpanel = innerpanel+ '<tr><td colspan="2">';
					innerpanel = innerpanel+ previewImage;
					
					if(layername=='DIVISIONAL_CCTV' || layername=='TP_CAMERA'){
						showCamera = '<a href="javascript:showVideo(\''+ip+'\',\''+status+'\')" class="btn btn-info btn-xs"><i class="fa fa-video-camera"></i> View</a> ';	
					}
					
					innerpanel = innerpanel+ showCamera;
					innerpanel = innerpanel+' </td></tr>';

					innerpanel = innerpanel + '</table></div></div></div>'
				}
				$(data).append(innerpanel);
			}
		});

		$.ajaxSetup({
			async : true
		});
	}
}

/*POPUP INNER PANEL DECALARATION*/
function collapseinner(id) {
	var panel = "#panel-inner" + id;
	var panelheading = "#panel-heading-inner" + id;
	var data = ".data-inner" + id;
	if (!$(panel).hasClass('panel-collapsed')) {
		$(panel).parents('.panel').find('.data-inner').slideUp();
		$(panel).parents('.panel').find('.data-inner' + id).removeClass('show');
		$(panel).addClass('panel-collapsed');
		$(panelheading).find('i').removeClass('fa-chevron-up').addClass('fa-chevron-down');
		$('#panel-inner'+id).slimScroll({
			height: '0px'
		});
	} else {
		$(panel).parents('.panel').find('.data-inner' + id).addClass('show');
		$(panel).parents('.panel').find('.data-inner').slideDown();
		$(panel).removeClass('panel-collapsed');
		$(panelheading).find('i').removeClass('fa-chevron-down').addClass('fa-chevron-up');
		
		$('#panel-inner'+id).slimScroll({
			height: '250px'
		});
	}
}

var displayFeatureInfo = function(pixel) {
	var feature = map.forEachFeatureAtPixel(pixel, function(feature) {
		return feature;
	});
};

/* CLOSE POPUP WINDOW */
function closePopup() {
	map.removeLayer(markerVector);
	$("#attribute_data").empty();
	$('#accordionAV').empty();
	popup.setPosition(undefined);
	closer.blur();
 	infoFlag = false;
	attributeFlag = false;
	return false;
};

/* MEASUREMENT CODE */
function measurementType(value) {
	if (value !== '') {
		var type = value;//(value == 'area' ? 'Polygon' : 'LineString');
		draw = new ol.interaction.Draw({
			source : vectorSource,
			type : (type),
			style : new ol.style.Style({
				fill : new ol.style.Fill({
					color : 'rgba(255, 255, 255, 0.2)'
				}),
				stroke : new ol.style.Stroke({
					color : 'rgba(0, 0, 0, 0.5)',
					lineDash : [ 10, 10 ],
					width : 2
				}),
				image : new ol.style.Circle({
					radius : 5,
					stroke : new ol.style.Stroke({
						color : 'rgba(0, 0, 0, 0.7)'
					}),
					fill : new ol.style.Fill({
						color : 'rgba(255, 255, 255, 0.2)'
					})
				})
			})
		});
		map.addInteraction(draw);
	
		createMeasureTooltip();
		createHelpTooltip();
	
		var listener;
		draw.on('drawstart', function(evt) {
			sketch = evt.feature;
			var tooltipCoord = evt.coordinate;
	
			listener = sketch.getGeometry().on('change', function(evt) {
				var geom = evt.target;
				var output;
				if (geom instanceof ol.geom.Polygon) {
					output = formatArea(geom);
					tooltipCoord = geom.getInteriorPoint().getCoordinates();
				} else if (geom instanceof ol.geom.LineString) {
					output = formatLength(geom);
					tooltipCoord = geom.getLastCoordinate();
				}
				measureTooltipElement.innerHTML = output;
				measureTooltip.setPosition(tooltipCoord);
			});
		}, this);
	
		draw.on('drawend', function() {
			measureTooltipElement.className = 'tooltip tooltip-static';
			measureTooltip.setOffset([ 0, -7 ]);
			sketch = null;
			measureTooltipElement = null;
			createMeasureTooltip();
			ol.Observable.unByKey(listener);

			map.removeInteraction(draw);
			//$('#point_button').prop('checked', false).change();
			$('#length_button').prop('checked', false).change();
			$('#polygon_length_button').prop('checked', false).change();
		}, this);
	}
}

function createMeasureTooltip() {
	if (measureTooltipElement) {
		measureTooltipElement.parentNode.removeChild(measureTooltipElement);
	}
	measureTooltipElement = document.createElement('div');
	measureTooltipElement.className = 'tooltip tooltip-measure';
	measureTooltip = new ol.Overlay({
		element : measureTooltipElement,
		offset : [ 0, -15 ],
		positioning : 'bottom-center'
	});
	map.addOverlay(measureTooltip);
}

function createHelpTooltip() {
	if (helpTooltipElement) {
		helpTooltipElement.parentNode.removeChild(helpTooltipElement);
	}
	helpTooltipElement = document.createElement('div');
	helpTooltipElement.className = 'tooltip hidden';
	helpTooltip = new ol.Overlay({
		element : helpTooltipElement,
		offset : [ 15, 0 ],
		positioning : 'center-left'
	});
	map.addOverlay(helpTooltip);
}

/* CLEAR MEASURMENT */
function clearMeasurement(){
	vectorSource.clear();
	console.log(map.getOverlays());
	for(var a=0;a<map.getOverlays().length;a++){
		map.removeOverlay(map.getOverlays().q[a]);
	}
	
	resetFilter();
}

/* CLOSE MODAL WINDOW EDIT/UPLOAD/VIDEO */
function closeModal(option){
	if(option=='legend'){
		$('#legendDetailModal').modal('toggle');
	}else if(option=='edit'){
		$('#editFeatureModal').modal('toggle');
	}else if(option=='add'){
		$('#addLayerModal').modal('toggle');
	}else if(option=='av'){
		$('#avModal').modal('toggle');
	}
}

var styleFunction = function(feature) {
	var iconStyle;
	var iconType = feature.get('style');
	if(iconType =='Mean'){
		iconStyle = new ol.style.Style({
			image: new ol.style.Circle({
				 radius: 9,
		         fill: new ol.style.Fill({
		        	 color: 'rgba(0, 0, 255, 0.5)',
		         })
		    }),
		    text: new ol.style.Text({
	    	  textAlign: "center",
	          textBaseline: "middle",
	          font: 'Normal 10px Arial',
	          text: 'M',
	          fill: new ol.style.Fill({
	              color: 'white'
	          }),  
		    })
		});
	} else if(iconType =='Point'){
		var count = feature.get('count')+"";
		iconStyle = new ol.style.Style({
			image: new ol.style.Circle({
				 radius: 8,
		         stroke: new ol.style.Stroke({
		             color: '#000000'
		         }),
		         fill: new ol.style.Fill({
		             color: 'blue'
		         })
		    }),
		    text: new ol.style.Text({
	    	  textAlign: "center",
	          textBaseline: "middle",
	          font: 'Normal 12px Arial',
	          fill: new ol.style.Fill({
	              color: '#ffa400'
	          }),
	          stroke: new ol.style.Stroke({
	              color: '#000000',
	              width: 3
	          }),
	          offsetX: 0,
	          offsetY: 15,
	          rotation: 0,
	          text: count,
		    })
		});
	} else if(iconType =='StartPoint'){
		iconStyle = new ol.style.Style({
				image: new ol.style.Icon(({
				opacity: 1,
				anchor: [0.4, 0.4],
				size: [170, 175],
			    scale: 0.65,
		    	src: contextRoot+'/resources/images/start.png'
		    }))
		});
	} else if(iconType =='EndPoint'){
		iconStyle = new ol.style.Style({
				image: new ol.style.Icon(({
				opacity: 1,
				anchor: [0.4, 0.4],
				size: [160, 160],
			    scale: 0.65,
		        src: contextRoot+'/resources/images/end.png'
			}))
		}); 
	} else if(iconType =='MultiLineString'){
		iconStyle =  new ol.style.Style({
			stroke: new ol.style.Stroke({
			    color: 'black',
			    width: 2,
			    lineDash: [.1, 4]
			})	
		});
	} else if(iconType =='Animate'){
		iconStyle = new ol.style.Style({
			image: new ol.style.Circle({
				 radius: 10,
		         stroke: new ol.style.Stroke({
		             color: '#000000'
		         }),
		         fill: new ol.style.Fill({
		             color: 'red'
		         })
		    })
		})
	}else if(iconType =='MeanBuffer') {
		iconStyle = new ol.style.Style({
	    	stroke: new ol.style.Stroke({
	    		color: 'rgba(0, 0, 255, 0.2)',
	            width: 1
	        }),
	        fill: new ol.style.Fill({
	            color: 'rgba(0, 0, 255, 0.1)'
	        })
	    })
	}else if(iconType =='LAYER'){
		var color = feature.get('color')+"";
		iconStyle = new ol.style.Style({
			image: new ol.style.Circle({
				 radius:8,
		         stroke: new ol.style.Stroke({
		             color: '#000000'
		         }),
		         fill: new ol.style.Fill({
		             color: color
		         })
		    }),
		    text: new ol.style.Text({
	    	  textAlign: "center",
	          textBaseline: "middle",
	          font: 'Normal 12px Arial',
	          fill: new ol.style.Fill({
	              color: '#ffa400'
	          }),
	          stroke: new ol.style.Stroke({
	              color: '#000000',
	              width: 3
	          }),
	          offsetX: 0,
	          offsetY: 15,
	          rotation: 0
		    })
		});
		
	} else if(iconType =='Location'){
		var count = feature.get('count')+"";
		var number = feature.get('number')+"";
		iconStyle = new ol.style.Style({
			image: new ol.style.Circle({
				 radius:8,
		         fill: new ol.style.Fill({
		             color: colors[count]
		         })
		    }),
		    text: new ol.style.Text({
	    	  textAlign: "center",
	          textBaseline: "middle",
	          font: 'Bold 10px Arial',
	          text: number,
	          offsetX: 0,
	          offsetY: 0,
	          rotation: 0,
	          fill: new ol.style.Fill({
	              color: 'blue'
	          }),
	          stroke: new ol.style.Stroke({
	              color: '#fff',
	              width: 2
	          }),
		    })
		});
	} else if(iconType =='Live'){
		var username = feature.get('username')+"";
		var count = feature.get('count')+"";
		var status = feature.get('status')+"";
		var bgColor =  'green';
		if(status=='inactive')
			bgColor = 'red';
		
		
		var iconStyle = new ol.style.Style({
		    image: new ol.style.Icon(({
		    	anchor: [0.5, 0.96],
		   	  	src: contextRoot+'/images/'+status+'.png',
		    })),
		    text: new ol.style.Text({
	    	  textAlign: "center",
	          textBaseline: "middle",
	          font: 'Bold 10px Arial',
	          text: username,
	          offsetX: 0,
	          offsetY: 15,
	          rotation: 0,
	          fill: new ol.style.Fill({
	              color: bgColor
	          }),
	          stroke: new ol.style.Stroke({
	              color: '#fff',
	              width: 2
	          }),
		    })
		}); 
	}else if(iconType =='Inspector'){
		var username = feature.get('username')+"";
		iconStyle = new ol.style.Style({
			image: new ol.style.Circle({
				 radius: 8,
		         stroke: new ol.style.Stroke({
		             color: '#000000'
		         }),
		         fill: new ol.style.Fill({
		             color: 'red'
		         })
		    }),
		    text: new ol.style.Text({
		    	textAlign: "center",
		          textBaseline: "middle",
		          font: 'Bold 10px Arial',
		          text: username,
		          offsetX: 0,
		          offsetY: 15,
		          rotation: 0,
		          fill: new ol.style.Fill({
		              color: 'red'
		          })
		    })
		});
	} 
	
	return iconStyle;
};

function messagePopup(message){
	$('#message-appender').empty();
	$('#message-appender').append(message);
	$('#messageModal').modal('show');
	window.setTimeout(function () {
        $("#messageModal").modal("hide");
        $('#message-appender').empty();
    }, 3000);
}

/* LOAD LAYER ON/OFF BUTTONS */
function layerSelection(option) {
	var isChecked = $('#' + option.id).prop('checked');
	loadMap(option.id, isChecked);
}

/*LOAD LEGEND DATA ON CLICK FROM LEGEND PANEL*/
function loadingData(layer,url,layername,startIndex){
	var isChecked = $('#' + layername.toLowerCase()).prop('checked');
	loadLegendDetailData(layer,url,layername,startIndex);
}

function loadLegendDetailData(layer,url,layername,startIndex) {
	
	var attributeLimit = parseInt($('#attr_limit').val());
	
	$.ajaxSetup({
		async : false
	});
	
	var target_url = contextRoot + "/Postgres/metadata";
	 //GET TABLE METADATA
	 $.ajax({
		  url : target_url,
		  type : "POST",
		  contentType: "application/json",
		  data: JSON.stringify({"tableName": layername.toLowerCase() ,"token":token}),
		  success : function(returndata) {
			debugToConsole('metadata ', returndata);
			 var metadata = returndata;
			 target_url = contextRoot + "/layer";
			 $.ajax({
				url : target_url,
				type : "POST",
				contentType: "application/json",
				data: JSON.stringify({"url": url + '&startindex='+startIndex+'&maxFeatures='+attributeLimit ,"token":token}),
				success : function(returndata) {
					debugToConsole('layer ', returndata);
					try{
						var jsonData = returndata.features;
						var totalCount = returndata.totalFeatures;
						var content = '<div class="card-body"><table class="table table-bordered" width="100%">';

						$('#legend_heading').empty();
						$('#legend_heading').append(layer + ' [ '+totalCount+' ] <a style="color: red;margin-right: 11px;float: right;" onclick="closeLegend()"> <i class="fa fa-close fa-2x"></i></a>');
						
						var headerArray = new Array();
						for (var a = 0; a < jsonData.length; a++) {
							if(a==0){
								content = content +'<thead><tr><th>&nbsp;</th>';
								for ( var i in jsonData[a].properties) {
									headerArray.push(i);
									 content = content+'<th> '+i.toUpperCase()+'</th>';
								}
								content = content +'</tr></thead><tbody>';
			
							}
							content = content+'<tr  style="white-space: nowrap;background: #fff;">';
							for(var b = 0; b < headerArray.length; b++){
								var tdValue = jsonData[a].properties[headerArray[b]];
								if(b==0){
									var type = jsonData[a].geometry.type;
									var coordinates = jsonData[a].geometry.coordinates;
									var id = jsonData[a].id;
									var gid =id.split("."); 
									content = content+'<td>';
									content = content+ '&nbsp;&nbsp;<a title="ZOOM" href="javascript:mapMarker(\''+a+'\',\''+id+'\',\''+type+'\',\''+coordinates+'\')"><i id="zoom_'+a+'" class="fa fa-map-marker icon-green fa-2x"></i></a>';
									content = content+ '&nbsp;&nbsp;<a title="PREVIEW" href="javascript:imagePreview(\''+gid[1]+'\',\''+layername+'\')"><i class="fa fa-image fa-2x icon-purple"></i></a>';
									
									var columntype = metadata[headerArray[b]];
									if(columntype=='date' && tdValue!=''){
										if(tdValue instanceof Object) {
											tdValue = '';
										}else {
											tdValue =  tdValue.replace("Z","");
										}
									}
									if(tdValue instanceof Object) {
										tdValue = '';
									}
									
									content = content+'</td><td>'+tdValue+'</td>';
									
								}
								else{
									var columntype = metadata[headerArray[b]];
									if(columntype=='date' && tdValue!=''){
										if(tdValue instanceof Object) {
											tdValue = '';
										}else {
											tdValue =  tdValue.replace("Z","");
										}
									}
									
									if(tdValue instanceof Object) {
										tdValue = '';
									}
									
									content = content+'<td>'+tdValue+'</td>';
								}
							}
							content = content+'</tr>';
						}
						content = content+ '</tbody></table></div>'; 
						
						var footerContent='<div class="card-footer"><div class="col-lg-12 form-group" style="text-align: center;"><nav aria-label="..."><ul class="pager">';
						
						if(totalCount>=attributeLimit){
							var loopCount = Math.ceil( totalCount/attributeLimit);
							var initialCount = attributeLimit;
							var next = initialCount;
							var previous = startIndex;
							if(startIndex!=0){
								previous = parseInt(previous) - parseInt(initialCount);
								next = parseInt(next)+parseInt(startIndex);
							}
							footerContent = footerContent+'<li class="previous';
							if(startIndex==0)
								footerContent = footerContent+ ' disabled "><a href="#"><span class="fa fa-arrow-circle-o-left fa-2x"></span></a></li>';
							else
								footerContent = footerContent+ '"><a href="javascript:pagination(\''+layer+'\',\''+url+'\',\''+layername+'\','+previous+')"><span class="fa fa-arrow-circle-o-left fa-2x"></span> </a></li>';
							
							footerContent = footerContent+'<li><select style="position: absolute;" id="pageDropdown" onchange="pagination(\''+layer+'\',\''+url+'\',\''+layername+'\',this.value)">';
							initialCount = 0;
							for(var a=1;a<=loopCount;a++){
									footerContent = footerContent+'<option value="'+initialCount+'">'+a+'</option>';
								initialCount = initialCount +attributeLimit;
							}
							
							footerContent = footerContent+'</select></li>';
							footerContent = footerContent+'<li class="next ' ;
							if(next>totalCount)
								footerContent = footerContent+'disabled "><a href="#"><span class="fa fa-arrow-circle-o-right fa-2x"></span></a></li>';
							else
								footerContent = footerContent+'"><a href="javascript:pagination(\''+layer+'\',\''+url+'\',\''+layername+'\','+next+')"><span class="fa fa-arrow-circle-o-right fa-2x"></span></a></li>';
						}else{
							footerContent = footerContent;
						}
						footerContent= footerContent + '</ul></nav></div></div>';
						var panel = content +footerContent;
						
						$('#legend_body').empty();
						
						$('#legend_body').append(panel);

	        			$('#legend_div').show();
				  			
						if(startIndex!=0){
							document.getElementById('pageDropdown').value=startIndex;
						}
						
						$('#loadingModal').modal('hide');
					}
					catch(err) {
						console.log(err);
						$('#loadingModal').modal('hide');
					}
				},
				  error : function(xhr, ajaxOptions, thrownError) {
					  console.log('Error' +xhr);
					  $('#loadingModal').modal('hide');
				  }
			});
		  },
		  error : function(xhr, ajaxOptions, thrownError) {
			  console.log(xhr);
			  $('#loadingModal').modal('hide');
		  }
	});
	$.ajaxSetup({
		async : true
	});
}


/*LEGEND DETAIL PAGINATION*/
function pagination(layer,url,layername,startIndex) {
}

/*DIRECTION*/
var style = [
	new ol.style.Style({
		stroke: new ol.style.Stroke({
			color: [0,0,0,0.3],
			width: 2
		}),
		fill: new ol.style.Fill({
			color: [0,0,0,0.3]
		}),
		zIndex: -1
	}),
	new ol.style.Style({
		image: new ol.style.RegularShape({
			radius: 10,
			radius2: 5,
			points: 5,
			fill: new ol.style.Fill({ color: 'blue' })
       }),
       stroke: new ol.style.Stroke({
    	   color: [0,0,255],
    	   width: 2
       }),
       fill: new ol.style.Fill({
    	   color: [0,0,255,0.3]
       })
    })
];

style[1].getImage().getAnchor()[1] += 10

var moveStyle = new ol.style.Style({
	image: new ol.style.Circle({
		 radius: 8,
         stroke: new ol.style.Stroke({
             color: '#000000'
         }),
         fill: new ol.style.Fill({
             color: 'blue'
         })
    }),
    text: new ol.style.Text({
	  textAlign: "center",
      textBaseline: "middle",
      font: 'Normal 12px Arial',
      fill: new ol.style.Fill({
          color: '#ffa400'
      }),
      stroke: new ol.style.Stroke({
          color: '#000000',
          width: 3
      }),
      offsetX: 0,
      offsetY: 15,
      rotation: 0
    })
});

function addFireLogo(coord)
{
	
	var iconStyle = new ol.style.Style({
    image: new ol.style.Icon(({
    	anchor: [0.5, 0.96],
   	  	src: contextRoot+'/images/fire.png',
    })),
    text: new ol.style.Text({
        font: 'bold 15px Calibri,sans-serif',
        fill: new ol.style.Fill({ color: 'blue' }),
        stroke: new ol.style.Stroke({
            color: '#fff', width: 4
        }),
        text: 'SOUTH-EAST JAULASAL BEAT',
        offsetY: 10,
        textAlign: 'center',
        
    })
}); 

var latLong = ol.proj.fromLonLat(coord);
var feature = new ol.Feature({
    geometry: new ol.geom.Point(latLong),
    name: searchBox,
    editable : true
});

feature.setStyle(iconStyle);
map.removeLayer(markerVector);

markerVector = new ol.layer.Vector({
	source : new ol.source.Vector({
		features : [ feature ]
	})
})
markerVector.setVisible(true);
map.addLayer(markerVector);
	
}