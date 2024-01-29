/*  CUSTOM.JS  FOR UI 
Version: v0.0.1
Author : Mukesh Rout */

var currentTab = '';
var count = 0;
$(document).ready(function() {
	"use strict";
	
	$('.drag-modal').draggable();
	
	$('#myModal').on('show.bs.modal', function() {
		$(this).find('.modal-body').css({
			'max-height' : '100%'
		});
	});

	$('.drag-modal').on('shown',function(){
	     var offset = 0;
	     $(this).find('.modal-body').attr('style','max-height:'+($(window).height()-offset)+'px !important;');
	});
	
});