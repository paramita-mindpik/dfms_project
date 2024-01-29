<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	List<Map<String, Object>> userList = (List)session.getAttribute("userList");
%>
<jsp:include page="../../jsp/common/jscss.jsp"></jsp:include>
<body>
	<jsp:include page="../../jsp/common/navbar.jsp"></jsp:include>
	<jsp:include page="../../jsp/common/sidebar.jsp">
   		<jsp:param value="cpanel" name="page"/>
   		<jsp:param value="userslist" name="currentPage"/>
    </jsp:include>

  <main id="main" class="main">
    	<div class="pagetitle">
      	<h1><i class="bi bi-list-ul"></i> Users List</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/cpanel">Control Panel</a></li>
          <li class="breadcrumb-item active">Users List</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

	<section class="section dashboard">
      <div class="row">
        <div class="col-lg-12">
			<div class="card card-table overflow-auto">
                <div class="card-body">
                  <h5 class="card-title">Users List<span></span></h5>
                  <table class="table table-borderless datatable">
	                <thead>
	                  <tr>
	                    <th scope="col">#</th>
	                    <th scope="col">User Name</th>
	                    <th scope="col">Mobile</th>
	                    <th scope="col">Active</th>
	                    <th scope="col">Is Admin</th>
	                  </tr>
	                </thead>
	                <tbody>
	                	<% if(userList!=null && userList.size()>0){%>
						<%
							for (int a=0; a<userList.size();a ++) {
						%>
						 <tr>
	                    <th scope="row"><%=a+1 %></th>
	                    <td><a href="${pageContext.request.contextPath}/admin/edituser/<%=userList.get(a).get("user_id") %>" class="link"><%=userList.get(a).get("user_name") %></a></td>
	                    <td><%=userList.get(a).get("mobile") %></td>
	                    <td><%=userList.get(a).get("active") %></td>
	                    <td><%=userList.get(a).get("isadmin") %></td>
	                  </tr>
						<%}%>
					<%} %>
	                </tbody>
	              </table>
	              <!-- End Table with stripped rows -->
	            </div>
          	</div>
        </div>
      </div>
    </section>
  </main><!-- End #main -->
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
  <script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>