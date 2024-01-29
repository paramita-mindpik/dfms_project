<%@page import="com.gis.util.StringUtils"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	List<Map<String, Object>> userList = (List)session.getAttribute("userList");
	List<Map<String, Object>> allLayerList = (List)session.getAttribute("allLayerList");
	List<Map<String, Object>> allGroupList = (List)session.getAttribute("allGroupList");
	
	List<Map<String, Object>> usersLog = (List)request.getAttribute("usersLog");
%>
<jsp:include page="../jsp/common/jscss.jsp"></jsp:include>
<body>
	<jsp:include page="../jsp/common/navbar.jsp"></jsp:include>
	<jsp:include page="../jsp/common/sidebar.jsp">
   		<jsp:param value="cpanel" name="page"/>
   		<jsp:param value="cpanel" name="currentPage"/>
    </jsp:include>

  <main id="main" class="main">
    <div class="pagetitle">
      <h1>Control Panel</h1>
    </div><!-- End Page Title -->

    <section class="section dashboard">
      <div class="row">

        <!-- Left side columns -->
        <div class="col-lg-12">
          <div class="row">
            <div class="col-xxl-4 col-md-4">
              <div class="card info-card fire-card">
                <div class="card-body">
                  <a href="${pageContext.request.contextPath}/admin/userslist">
	                  <h5 class="card-title">Total Users<span></span></h5>
	
	                  <div class="d-flex align-items-center">
	                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
	                      <i class="bi bi-people"></i>
	                    </div>
	                    <div class="ps-3">
	                      <h6><%=userList.size() %></h6>
	                    </div>
	                  </div>
                  </a>
                </div>

              </div>
            </div>

            <div class="col-xxl-4 col-md-4">
              <div class="card info-card plant-card">
                <div class="card-body">
	               <a href="${pageContext.request.contextPath}/admin/layerslist">   
	                  <h5 class="card-title">Total Layers <span></span></h5>
	
	                  <div class="d-flex align-items-center">
	                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
	                      <i class="bi bi-map"></i>
	                    </div>
	                    <div class="ps-3">
	                      <h6><%=allLayerList.size() %></h6>
	                    </div>
	                  </div>
	                 </a>
                </div>

              </div>
            </div>
            
            <div class="col-xxl-4 col-md-4">
              <div class="card info-card fire-card">
                <div class="card-body">
	               <a href="${pageContext.request.contextPath}/admin/groupslist">   
	                  <h5 class="card-title">Total Group<span></span></h5>
	
	                  <div class="d-flex align-items-center">
	                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
	                      <i class="bi bi-people"></i>
	                    </div>
	                    <div class="ps-3">
	                      <h6><%=allGroupList.size() %></h6>
	                    </div>
	                  </div>
	                 </a> 
                </div>

              </div>
            </div>
            <div class="col-12">
              <div class="card card-table overflow-auto">
                <div class="card-body">
                  <h5 class="card-title">Users Log <span></span></h5>
                  <table class="table table-borderless datatable">
                    <thead>
                      <tr>
                        <th scope="col">#</th>
                        <th scope="col">User Name</th>
                        <th scope="col">Last Login</th>
                      </tr>
                    </thead>
                    <tbody>
                    	<%
                    		int a=1;
							for (Map<String, Object> log : usersLog) {
						%>
						<tr>
							<th scope="row"><%=a %></th>
							<td>
								<%=log.get("user_name").toString()%>
							</td>
							<td>
							<%=StringUtils.str2Datetime(log.get("last_login").toString())%>
							</td>
						</tr>
						<% a ++;}%>
                    </tbody>
                  </table>

                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

  </main><!-- End #main -->
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
  <!-- Template Main JS File -->
  <script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>