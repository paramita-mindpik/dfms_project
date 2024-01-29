<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	List<Map<String, Object>> allGroupList = (List)session.getAttribute("allGroupList");
%>
<jsp:include page="../../jsp/common/jscss.jsp"></jsp:include>
<body>
	<jsp:include page="../../jsp/common/navbar.jsp"></jsp:include>
	<jsp:include page="../../jsp/common/sidebar.jsp">
   		<jsp:param value="cpanel" name="page"/>
   		<jsp:param value="groupslist" name="currentPage"/>
    </jsp:include>

  <main id="main" class="main">
    	<div class="pagetitle">
      	<h1><i class="bi bi-list-ul"></i> Groups List</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/cpanel">Control Panel</a></li>
          <li class="breadcrumb-item active">Groups List</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

	<section class="section dashboard">
      <div class="row">
        <div class="col-lg-12">
			<div class="card card-table overflow-auto">
                <div class="card-body">
                  <h5 class="card-title">Groups List<span></span></h5>
                  <table class="table table-borderless datatable">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Group Name</th>
                  </tr>
                </thead>
                <tbody>
                	<% if(allGroupList!=null && allGroupList.size()>0){%>
					<%
						for (int a=0; a<allGroupList.size();a ++) {
					%>
					 <tr>
	                    <th scope="row"><%=a+1 %></th>
	                    <td><a href="${pageContext.request.contextPath}/admin/editgroup/<%=allGroupList.get(a).get("group_id") %>" class="link"><%=allGroupList.get(a).get("group_name") %></a></td>
	                  </tr>
					<%}%>
				<%} %>
                </tbody>
              </table>
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