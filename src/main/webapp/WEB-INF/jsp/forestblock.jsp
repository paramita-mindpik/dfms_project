<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../jsp/common/jscss.jsp"></jsp:include>
<body>
	<jsp:include page="../jsp/common/navbar.jsp"></jsp:include>
	<jsp:include page="../jsp/common/sidebar.jsp">
   		<jsp:param value="web" name="page"/>
   		<jsp:param value="ns" name="currentPage"/>
    </jsp:include>

  <main id="main" class="main">
    <div class="pagetitle">
      <h1>Forest Block</h1>
    </div><!-- End Page Title -->

    <section class="section dashboard">
      <div class="row">
      	<div class="col-12">
			<div class="card">
	            <div class="card-body">
	             <form:form cssClass="row" action="#" method="POST" modelAttribute="cddrb" name="angulForm">
	                <div class="col-md-2">
	                  <label for="inputCity" class="form-label">From Date</label>
	                  <input type="date" class="form-control">
	                </div>
	                <div class="col-md-2">
	                  <label for="inputCity" class="form-label">To Date</label>
	                  <input type="date" class="form-control">
	                </div>
	                <div class="col-md-2">
	                  <label for="inputState" class="form-label">Circle</label>
	                  <form:errors path="circle_cod" cssClass="error"/>
                      <form:select path="circle_cod" id="circle_cod" cssClass="form-control">
                        	<form:options items="${circleList}"/>
                        </form:select>
	                </div>
	                 <div class="col-md-2">
	                  <label for="inputState" class="form-label">Division</label>
	                  <select id="inputState" class="form-select">
	                    <option selected>Choose...</option>
	                    <option>BANKURA NORTH</option>
	                  </select>
	                </div>
	                 <div class="col-md-2">
	                  <label for="inputState" class="form-label">Range</label>
	                  <select id="inputState" class="form-select">
	                    <option selected>Choose...</option>
	                    <option>BARJORA</option>
	                    <option>BELIATORE</option>
	                  </select>
	                </div>
	                <div class="col-md-2">
	                  <label for="inputState" class="form-label">Beat</label>
	                  <select id="inputState" class="form-select">
	                    <option selected>Choose...</option>
	                    <option>1</option>
	                    <option>2</option>
	                  </select>
	                </div>
	                <div class="col-md-2">
	                  <label for="inputState" class="form-label">Source</label>
	                  <form:select path="source" id="source" cssClass="form-control">
                        	<form:options items="${fireSource}"/>
                        </form:select>
	                </div>
	                <div class="col-md-6">
		                  <a href="#" class="btn btn-primary btn-sm margin-top" onclick="showData()">Submit</a>
		                  <button type="reset" class="btn btn-secondary btn-sm margin-top">Reset</button>
	                </div>
	              </form:form>
	            </div>
	          </div>
	      </div>
	   </div>
      <div class="row" id="table-data" style="display: none;">
      	<div class="col-12">
              <div class="card card-table overflow-auto">
                <div class="card-body">
                  <table class="table table-borderless datatable">
                    <thead>
                      <tr>
                      	<th scope="col"><input type="checkbox"/></th>
                        <th scope="col">Sl #</th>
                        <th scope="col">Circle</th>
                        <th scope="col">Division </th>
                        <th scope="col">Range </th>
                        <th scope="col">Beat </th>
                        <th scope="col">Fire Date </th>
                        <th scope="col">Affected Area </th>
                        <th scope="col">Status </th>
                        <th scope="col">Estimated Loss </th>
                        <th scope="col">Action Taken </th>
                        <th scope="col">Source </th>
                        <th scope="col">Cause </th>
                        <th scope="col">Details </th>
                      </tr>
                    </thead>
                    <tbody>
                    	
                   </tbody>
                  </table>

                </div>

              </div>
            </div>
      
      </div>
    </section>

  </main><!-- End #main -->
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
  <!-- Template Main JS File -->
  <script src="${pageContext.request.contextPath}/js/main.js"></script>
  <script type="text/javascript">
  	function showData(){
  		$('#table-data').show();
  		
  	}
  </script>
</body>
</html>