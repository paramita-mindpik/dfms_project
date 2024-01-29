<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	List<Map<String, Object>> allLayerList = (List)session.getAttribute("allLayerList");
%>
<jsp:include page="../../jsp/common/jscss.jsp"></jsp:include>
<body>
	<jsp:include page="../../jsp/common/navbar.jsp"></jsp:include>
	<jsp:include page="../../jsp/common/sidebar.jsp">
   		<jsp:param value="cpanel" name="page"/>
   		<jsp:param value="layerslist" name="currentPage"/>
    </jsp:include>

  <main id="main" class="main">
    	<div class="pagetitle">
      	<h1><i class="bi bi-list-ul"></i> Layers List</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/cpanel">Control Panel</a></li>
          <li class="breadcrumb-item active">Layers List</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

	<section class="section dashboard">
      <div class="row">
        <div class="col-lg-12">
			<div class="card card-table overflow-auto">
                <div class="card-body">
                  <h5 class="card-title">Layers List<span></span></h5>
                  <table class="table table-borderless datatable">
	                <thead>
	                  <tr>
	                    <th scope="col">#</th>
	                    <th scope="col">Layer Name</th>
	                    <th scope="col">Layer Code</th>
	                    <th scope="col">Sequence</th>
	                    <th scope="col">Active</th>
	                    <th scope="col">Group</th>
	                  </tr>
	                </thead>
	                <tbody>
	                	<% if(allLayerList!=null && allLayerList.size()>0){%>
						<%
							for (int a=0; a<allLayerList.size();a ++) {
						%>
						 <tr>
		                    <th scope="row"><%=a+1 %></th>
		                    <td><a href="${pageContext.request.contextPath}/admin/editlayer/<%=allLayerList.get(a).get("layer_id") %>" class="link"><%=allLayerList.get(a).get("layer_name") %></a></td>
		                    <td><%=allLayerList.get(a).get("layer_code") %></td>
		                    <td><%=allLayerList.get(a).get("layer_sequence") %></td>
		                    <td><%=allLayerList.get(a).get("active") %></td>
		                    <td><%=allLayerList.get(a).get("group_id") %></td>
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