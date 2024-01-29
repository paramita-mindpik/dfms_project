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
   		<jsp:param value="userslist" name="currentPage"/>
    </jsp:include>

  <main id="main" class="main">
    	<div class="pagetitle">
      	<h1><i class="bi bi-pencil"></i> Edit User</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/cpanel">Control Panel</a></li>
          <li class="breadcrumb-item active">Edit User</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

	<section class="section" data-ng-app="angApp">
      <div class="row" data-ng-controller="angController">
        <div class="col-lg-12">
           <div class="card">
            <div class="card-body">
              
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