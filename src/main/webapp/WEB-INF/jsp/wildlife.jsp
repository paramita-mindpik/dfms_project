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
    <section class="section dashboard">
      <div class="row">
      	<div class="col-12">
			<div class="card">
            <div class="card-body">
              <h5 class="card-title">Wildlife</h5>

              <!-- Bordered Tabs Justified -->
              <ul class="nav nav-tabs nav-tabs-bordered d-flex" id="borderedTabJustified" role="tablist">
                <li class="nav-item flex-fill" role="presentation">
                  <button class="nav-link w-100 active" id="conflict-tab" data-bs-toggle="tab" data-bs-target="#bordered-justified-home" type="button" role="tab" aria-controls="Conflict" aria-selected="true">Conflict</button>
                </li>
                <li class="nav-item flex-fill" role="presentation">
                  <button class="nav-link w-100" id="dep-tab" data-bs-toggle="tab" data-bs-target="#bordered-justified-profile" type="button" role="tab" aria-controls="Depredation" aria-selected="false">Depredation</button>
                </li>
                <li class="nav-item flex-fill" role="presentation">
                  <button class="nav-link w-100" id="sig-tab" data-bs-toggle="tab" data-bs-target="#bordered-justified-contact" type="button" role="tab" aria-controls="Sighting" aria-selected="false">Sighting</button>
                </li>
                <li class="nav-item flex-fill" role="presentation">
                  <button class="nav-link w-100" id="inj-tab" data-bs-toggle="tab" data-bs-target="#bordered-justified-contact" type="button" role="tab" aria-controls="Injury" aria-selected="false">Injury/Death</button>
                </li>
              </ul>
              <div class="tab-content pt-2" id="borderedTabJustifiedContent">
                <div class="tab-pane fade show active" id="bordered-justified-home" role="tabpanel" aria-labelledby="conflict-tab">
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
		                  <label for="inputState" class="form-label">Type</label>
		                  <form:select path="source" id="source" cssClass="form-control">
	                        	<form:options items="${fireSource}"/>
	                        </form:select>
		                </div>
		                <div class="col-md-6">
			                  <a href="#" class="btn btn-primary btn-sm margin-top" onclick="showData('table-data')">Submit</a>
			                  <button type="reset" class="btn btn-secondary btn-sm margin-top">Reset</button>
		                </div>
		              </form:form>
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
			                        <th scope="col">Death</th>
			                        <th scope="col">No. </th>
			                        <th scope="col">Date/Time </th>
			                        <th scope="col">Place </th>
			                        <th scope="col">Injury </th>
			                         <th scope="col">No. </th>
			                        <th scope="col">Date/Time </th>
			                        <th scope="col">Place </th>
			                        <th scope="col">Remark </th>
			                        <th scope="col">Image </th>
			                      </tr>
			                    </thead>
			                    <tbody>
			                    	
			                   </tbody>
			                  </table>
			
			                </div>
			
			              </div>
			            </div>
			      </div>
                </div>
                <div class="tab-pane fade" id="bordered-justified-profile" role="tabpanel" aria-labelledby="dep-tab">
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
			                  <label for="inputState" class="form-label">Animal</label>
			                  <form:select path="source" id="source" cssClass="form-control">
		                        	<form:options items="${fireSource}"/>
		                        </form:select>
			                </div>
			                <div class="col-md-6">
				                  <a href="#" class="btn btn-primary btn-sm margin-top" onclick="showData('table-data-1')">Submit</a>
				                  <button type="reset" class="btn btn-secondary btn-sm margin-top">Reset</button>
			                </div>
			              </form:form>
			            </div>
			          </div>
			          <div class="row" id="table-data-1" style="display: none;">
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
				                        <th scope="col">Animal</th>
				                        <th scope="col">Damages </th>
				                        <th scope="col">Date </th>
				                        <th scope="col">Incident Date </th>
				                        <th scope="col">Name </th>
				                         <th scope="col">Mouja</th>
				                        <th scope="col">JL No. </th>
				                        <th scope="col">Plot No. </th>
				                        <th scope="col">Remark </th>
				                        <th scope="col">Auto Area </th>
				                        <th scope="col">Measure Area </th>
				                        <th scope="col">Khatiyan No</th>
				                        <th scope="col">Description</th>
				                        <th scope="col">Aadhar</th>
				                        <th scope="col">Image</th>
				                      </tr>
				                    </thead>
				                    <tbody>
				                    	
				                   </tbody>
				                  </table>
				
				                </div>
				
				              </div>
				            </div>
				      </div>
                </div>
                <div class="tab-pane fade" id="bordered-justified-contact" role="tabpanel" aria-labelledby="sig-tab">
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
			                  <label for="inputState" class="form-label">Type</label>
			                  <form:select path="source" id="source" cssClass="form-control">
		                        	<form:options items="${fireSource}"/>
		                        </form:select>
			                </div>
			                <div class="col-md-6">
				                  <a href="#" class="btn btn-primary btn-sm margin-top" onclick="showData('table-data-2')">Submit</a>
				                  <button type="reset" class="btn btn-secondary btn-sm margin-top">Reset</button>
			                </div>
			              </form:form>
			            </div>
			          </div>
			          <div class="row" id="table-data-2" style="display: none;">
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
				                        <th scope="col">Animal</th>
				                        <th scope="col">Gender </th>
				                        <th scope="col">Health </th>
				                        <th scope="col">Type </th>
				                        <th scope="col">Nos </th>
				                         <th scope="col">Nature of Sighting </th>
				                        <th scope="col">Place  of Sighting</th>
				                        <th scope="col">Time  of Sighting </th>
				                        <th scope="col">Image </th>
				                      </tr>
				                    </thead>
				                    <tbody>
				                    	
				                   </tbody>
				                  </table>
				
				                </div>
				
				              </div>
				            </div>
				      </div>
                </div>
                <div class="tab-pane fade" id="bordered-justified-contact" role="tabpanel" aria-labelledby="inj-tab">
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
		                  <label for="inputState" class="form-label">Type</label>
		                  <form:select path="source" id="source" cssClass="form-control">
	                        	<form:options items="${fireSource}"/>
	                        </form:select>
		                </div>
		                <div class="col-md-6">
			                  <a href="#" class="btn btn-primary btn-sm margin-top" onclick="showData('table-data-3')">Submit</a>
			                  <button type="reset" class="btn btn-secondary btn-sm margin-top">Reset</button>
		                </div>
		              </form:form>
		            </div>
		          </div>
		          <div class="row" id="table-data-3" style="display: none;">
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
			                        <th scope="col">Animal</th>
			                        <th scope="col">Type </th>
			                        <th scope="col">Date </th>
			                        <th scope="col">Incident Date </th>
			                        <th scope="col">Name </th>
			                         <th scope="col">Mouja</th>
			                        <th scope="col">JL No. </th>
			                        <th scope="col">Plot No. </th>
			                        <th scope="col">Auto Area </th>
			                        <th scope="col">Measure Area</th>
			                        <th scope="col">Khatiyan No</th>
			                        <th scope="col">Description</th>
			                        <th scope="col">Aadhar</th>
			                        <th scope="col">Image</th>
			                      </tr>
			                    </thead>
			                    <tbody>
			                    	
			                   </tbody>
			                  </table>
			
			                </div>
			
			              </div>
			            </div>
			      </div>
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
  <script type="text/javascript">
  	function showData(id){
  		$('#'+id).show();
  		
  	}
  </script>
</body>
</html>