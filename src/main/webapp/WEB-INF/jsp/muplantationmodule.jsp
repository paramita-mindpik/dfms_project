<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<Map<String, Object>> fireList = (List)session.getAttribute("fireList");
%>
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
      <h1>Plantation Module</h1>
    </div><!-- End Page Title -->

    <section class="section dashboard">
      <div class="row">
      	<div class="col-12">
			<div class="card">
	            <div class="card-body">
	             <form:form cssClass="row" action="#" method="POST" modelAttribute="cddrb" name="angulForm">
	                <div class="col-md-2">
                        <label for="inputState" class="form-label">Plantation Stock details</label>
                        <select id="inputState" class="form-select">
                          <option selected>Choose...</option>
                          <option>BANKURA NORTH</option>
                        </select>
                      </div>
					  <div class="col-md-2">
                        <label for="inputState" class="form-label">Protection Mechanism</label>
                        <select id="inputState" class="form-select">
                          <option selected>Choose...</option>
                          <option>BANKURA NORTH</option>
                        </select>
                      </div>
					  <div class="col-md-2">
                        <label for="inputState" class="form-label">Fencing</label>
                        <select id="inputState" class="form-select">
                          <option selected>Choose...</option>
                          <option>BANKURA NORTH</option>
                        </select>
                      </div>
	                 <div class="col-md-2">
	                  <label for="inputState" class="form-label">Seedlings</label>
	                  <select id="inputState" class="form-select">
	                    <option selected>Choose...</option>
	                    <option>BANKURA NORTH</option>
	                  </select>
	                </div>
					<div class="col-md-2">
						<label for="inputState" class="form-label">Plantation Model with pattern details</label>
						<select id="inputState" class="form-select">
						  <option selected>Choose...</option>
						  <option>BANKURA NORTH</option>
						</select>
					  </div>
					  <div class="col-md-2">
						<label for="inputState" class="form-label">Soil Conservation</label>
						<select id="inputState" class="form-select">
						  <option selected>Choose...</option>
						  <option>BANKURA NORTH</option>
						</select>
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
                    		<%
                    			for(int a=0;a<fireList.size();a++){
                    				String latLong = fireList.get(a).get("lat_long").toString();
                    				JSONObject jsonObject = JSONObject.fromObject(latLong);
                    				
                    		%>
                    		<tr>
                    			<td><input type="checkbox"/></td>
                    			<td><%=a+1 %> </td>
                    			<td>Central </td>
	                    		<td><%=fireList.get(a).get("division") %> </td>
	                    		<td><%=fireList.get(a).get("range") %> </td>
	                    		<td><%=fireList.get(a).get("beat") %> </td>
	                    		<td><%=fireList.get(a).get("fire_date") %> </td>
	                    		<td><%=fireList.get(a).get("area_affec") %> </td>
	                    		<td><%=fireList.get(a).get("fire_occur") %> </td>
	                    		<td><%=fireList.get(a).get("esti_loss") %> </td>
	                    		<td><%=fireList.get(a).get("act_taken") %> </td>
	                    		<td><%=fireList.get(a).get("source") %> </td>
	                    		<td><%=fireList.get(a).get("cause") %> </td>
	                    		<td>
	                    			<div class="d-flex">
									  <div class="dropdown me-1">
									    <button type="button" class="btn btn-default btn-sm dropdown-toggle" id="dropdownMenuOffset" data-bs-toggle="dropdown" aria-expanded="false" data-bs-offset="10,20">
									      <i class="bi bi-gear"></i> 
									    </button>
									    <ul class="dropdown-menu" aria-labelledby="dropdownMenuOffset">
									      <li><a class="dropdown-item" href="${pageContext.request.contextPath}/gis/fire/<%=fireList.get(a).get("gisid") %>/<%=jsonObject.get("coordinates").toString().replace("[", "").replaceAll("]", "") %>" target="_blank">
									      <i class="bi bi-map"></i> Spactial View</a></li>
									      <li><hr class="dropdown-divider"></li>
									      <li><a class="dropdown-item" href="#"><i class="bi bi-card-text"></i> Action Taken</a></li>
									    </ul>
									  </div>
								</div>
                    		</td>
                    	</tr>
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
  <!-- Template Main JS File -->
  <script src="${pageContext.request.contextPath}/js/main.js"></script>
  <script type="text/javascript">
  	function showData(){
  		$('#table-data').show();
  		
  		$('.dataTable-extraIcon').empty();
  		var data = '<a href="" class="icon-red"><i class="icon bi bi-file-pdf"></i> Report</a>';
  		data = data +'<a href="" class="icon-sky"><i class="icon bi bi-map"></i> Map View</a>';
  		data = data +'<a href="" class="icon-purple"><i class="icon bi bi-person-check"></i> Intimation</a>';
  		data = data +'<a href="" class="icon-darkblue"><i class="icon bi bi-person-plus"></i> Assign</a>';
  		
  		$('.dataTable-extraIcon').append(data);
  	}
  </script>
</body>
</html>