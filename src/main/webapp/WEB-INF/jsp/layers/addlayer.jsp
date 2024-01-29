<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../jsp/common/jscss.jsp"></jsp:include>
<body>
	<jsp:include page="../../jsp/common/navbar.jsp"></jsp:include>
	<jsp:include page="../../jsp/common/sidebar.jsp">
   		<jsp:param value="cpanel" name="page"/>
   		<jsp:param value="layerslist" name="currentPage"/>
    </jsp:include>

  <main id="main" class="main">
    	<div class="pagetitle">
      	<h1><i class="bi bi-person"></i> Add Layer</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/cpanel">Control Panel</a></li>
          <li class="breadcrumb-item active">Add Layers</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

	<section class="section" data-ng-app="angApp">
      <div class="row" data-ng-controller="angController">
        <div class="col-lg-12">
           <div class="card">
            <div class="card-body">
              <form:form action="${pageContext.request.contextPath}/admin/addlayer" method="POST" modelAttribute="layer"> 
                	<div class="row mb-3">
	                  <div class="col-sm-6">
	                      <div class="form-group">
	                          <label class="required">Layer Name</label>
	                          <form:errors path="layerName" cssClass="error"/>
	                          <form:input path="layerName" id="layerName" cssClass="form-control"/>
	                          <form:hidden path="actionType" value="ADD"/>
	                      </div>
	                  </div>
	                  <div class="col-sm-6">
	                      <div class="form-group">
	                          <label class="required">Layer Code</label>
	                          <form:errors path="layerCode" cssClass="error"/>
	                          <form:input path="layerCode" id="layerCode" cssClass="form-control"/>
	                      </div>
	                  </div>
	                  <div class="col-sm-6">
	                      <div class="form-group">
	                          <label class="required">Layer Sequence</label>
	                         <form:errors path="layerSequence" cssClass="error"/>
	                          <form:input path="layerSequence" id="layerCode" cssClass="form-control"/>
	                      </div>
	                  </div>
	                  <div class="col-sm-6">
	                      <div class="form-group">
	                          <label class="required">Active</label>
	                          <form:errors path="active" cssClass="error"/>
	                          <form:select path="active" id="active" cssClass="form-control">
	                          	<form:option value="">Please select</form:option>
	                          	<form:option value="Y">Yes</form:option>
	                          	<form:option value="N">No</form:option>
	                          </form:select>
	                      </div>
	                  </div>
	                  <div class="col-sm-6">
	                      <div class="form-group">
	                          <label class="required">Mobile View</label>
	                          <form:errors path="mobileView" cssClass="error"/>
	                          <form:select path="mobileView" id="mobileView" cssClass="form-control">
	                          	<form:option value="">Please select</form:option>
	                          	<form:option value="Y">Yes</form:option>
	                          	<form:option value="N">No</form:option>
	                          </form:select>
	                      </div>
	                  </div>
				</div>
                <div class="row mb-3">
                  <div class="col-sm-12">
					<a href="${pageContext.request.contextPath}/cpanel" title="BACK"  class="btn btn-danger cancel"><i class="fa fa-remove"></i> Cancel</a>
	                <button id="submit-button" class="btn btn-primary cancel" type="submit"><i class="fa fa-check-square-o"></i> Submit</button>
                  </div>
                </div>
              </form:form>
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
    	var angApp = angular.module('angApp', []);
		angApp.controller('angController', function($scope) {
			$scope.validateForm = function(event) {
				
	   		}
		});
	</script>
</body>
</html>