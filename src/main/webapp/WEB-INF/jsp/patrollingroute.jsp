<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../jsp/common/jscss.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/css/BsMultiSelect.css" rel="stylesheet">
<body>
	<jsp:include page="../jsp/common/navbar.jsp"></jsp:include>
	<jsp:include page="../jsp/common/sidebar.jsp">
   		<jsp:param value="web" name="page"/>
   		<jsp:param value="sa" name="currentPage"/>
    </jsp:include>

  <main id="main" class="main">
    <div class="pagetitle">
      <h1>New Patrolling Route</h1>
    </div><!-- End Page Title -->

    <section class="section dashboard">
      <div class="row">
      	<div class="col-12">
			<div class="card">
	            <div class="card-body">
	             <form:form cssClass="row" action="#" method="POST" modelAttribute="cddrb" name="angulForm">
	                <div class="col-md-3">
	                  <label for="inputCircle" class="form-label">Circle</label>
	                  <form:errors path="circle_cod" cssClass="error"/>
                      <form:select path="circle_cod" id="circle_cod" cssClass="form-control">
                        	<form:options items="${circleList}"/>
                        </form:select>
	                </div>
	                 <div class="col-md-3">
	                  <label for="inputDivision" class="form-label">Division</label>
	                  <select id="inputDivision" class="form-select">
	                    <option selected>Choose...</option>
	                    <option>BANKURA NORTH</option>
	                  </select>
	                </div>
	                 <div class="col-md-3">
	                  <label for="inputRange" class="form-label">Range</label>
	                  <select id="inputRange" class="form-select">
	                    <option selected>Choose...</option>
	                    <option>BARJORA</option>
	                    <option>BELIATORE</option>
	                  </select>
	                </div>
	                <div class="col-md-3">
	                  <label for="inputBeat" class="form-label">Beat</label>
	                  <select id="inputBeat" class="form-select">
	                    <option selected>Choose...</option>
	                    <option>1</option>
	                    <option>2</option>
	                  </select>
	                </div>
	                <div class="col-md-8">
	                  <label for="inputCompartment" class="form-label">Compartment</label>
	                   <form:select path="compartment" id="compartment" cssClass="form-control" multiple="multiple" onchange="loadPoints(this)">
                        	<form:options items="${compList}"/>
                        </form:select>
	                </div>
	                <div class="col-md-4">
	                		<a href="#" class="btn btn-danger btn-sm margin-top" onclick="$('#compartment').bsMultiSelect('DeselectAll')">Clear</a>
		                  <a href="#" class="btn btn-primary btn-sm margin-top" onclick="mapView()">Map View</a>
	                </div>
	              </form:form>
	            </div>
	          </div>
	      </div>
	   </div>
	   
      <div class="row" id="patdata">
      	<div class="col-8">
              <div class="card card-table overflow-auto">
                <div class="card-body">
                  <table class="table table-borderless">
                    <thead>
                      <tr>
                        <th scope="col">Point Number</th>
                        <th scope="col">Compartment Name </th>
                      </tr>
                    </thead>
                    <tbody id="patPoints">
                    	
                   </tbody>
                  </table>
                </div>
            </div>
         </div>
         <div class="col-4">
         	<div class="row">
         		<div class="col-12">
		             <div class="form-floating mb-3">
		               <select class="form-select" id="floatingSelect" aria-label="Employee">
		                 <option selected>Please select</option>
		                 <option value="1">DFO</option>
		                 <option value="2">ADFO-1</option>
		                 <option value="3">ADFO-2</option>
		                 <option value="4">BEAT-1</option>
		                 <option value="5">BEAT-2</option>
		               </select>
		               <label for="floatingSelect">Users</label>
		             </div>
		         </div>
		         <div class="col-12">
		         	<a href="#" class="btn btn-primary btn-sm margin-top" onclick="">Assign</a>
		         	<a href="#" class="btn btn-success btn-sm margin-top" onclick="">Save</a>
		         </div>
		         
		      </div>
         </div>   
      </div>
    </section>

  </main><!-- End #main -->
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
  <!-- Template Main JS File -->
  <script src="${pageContext.request.contextPath}/js/main.js"></script>
  <script src="${pageContext.request.contextPath}/js/BsMultiSelect.js"></script>

	<script type="text/javascript">
    	$(document).ready(function() {
    		$("#compartment").bsMultiSelect();
    		
    	});
    	function loadPoints(field){
    		$('#patPoints').empty();
    		var table = '';
    		var a = 1;
    		$("#compartment option:selected").each(function () {
    			   var $this = $(this);
    			   if ($this.length) {
    			    table = table + '<tr><td id="'+$this.val()+'">'+a+'</td><td>'+$this.text()+'</td></tr>';
    			    a++;
    			   }
    		});
			   
			$('#patPoints').append(table);
    	}
    	function mapView(){
    		var compartment = $('#compartment').val();
    		var win = window.open('${pageContext.request.contextPath}/route/'+compartment, '_blank');
    		if (win) {
    		    win.focus();
    		} else {
    		    alert('Please allow popups for this website');
    		}
    	}
	</script>
</body>
</html>