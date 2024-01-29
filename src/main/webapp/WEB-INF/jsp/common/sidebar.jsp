<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	String userName = (String)session.getAttribute("userName");
	Map<String,String> layerList = (Map)session.getAttribute("allowedLayerList");
	
	String IP = (String) getServletContext().getAttribute("IP");
	String WORKSPACE = (String) getServletContext().getAttribute("WORKSPACE");
	String PUBLIC_IP = (String) getServletContext().getAttribute("PUBLIC_IP");
	
	String pageName = request.getParameter("page");
	String currentPage = request.getParameter("currentPage");
	String style = "style2"; 
%>
<!-- ======= Sidebar ======= -->
<aside id="sidebar" class="sidebar">
<ul class="sidebar-nav" id="sidebar-nav">
<%
	if(pageName.equalsIgnoreCase("gis")){
%>
		<%-- <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/gis" class="gradientmenu">
	    		<img src="${pageContext.request.contextPath}/images/<%=style%>/Encroachment.svg" alt="" height="30" width="30">
	    		<span> GIS</span>
	   		 </a>
	   	</li> --%>
		 <li class="nav-heading">MENU</li>
	   	<li class="nav-item">
	        <a class="nav-link" data-bs-target="#maps" data-bs-toggle="collapse" href="#">
	          <img src="${pageContext.request.contextPath}/images/<%=style%>/map.jpeg" alt="" height="30" width="30">
	          <span>Maps</span><i class="bi bi-chevron-down ms-auto"></i>
	        </a>
	          <ul id="maps" class="nav-content collapse show" data-bs-parent="#sidebar-nav">
		          <li class="paddingli3">
		          	<span class="nav-sub-contents">
		          		<input id="gmap_layer" type="checkbox" data-toggle="toggle" checked="checked" data-on="ON" data-off="OFF" data-onstyle="success" data-offstyle="danger" data-width="20" data-size="mini" data-style="android">
		            	<a href="#"> Google Map </a>
		            </span>
		          </li>
		          <li class="paddingli3">
			          <span class="nav-sub-contents">
			          		<input id="satelite_button" type="checkbox" data-toggle="toggle" data-on="ON" data-off="OFF" data-onstyle="success" data-offstyle="danger" data-size="mini" data-width="20" data-style="android">
			            	<a href="#"> Google Satellite </a>
		              </span>
		          </li>
	        	</ul>
        </li>
        <li class="nav-item">
	        <a class="nav-link collapsed" data-bs-target="#layers" data-bs-toggle="collapse" href="#">
	          <img src="${pageContext.request.contextPath}/images/<%=style%>/layer.jpeg" alt="" height="30" width="30"><span>Layers</span><i class="bi bi-chevron-down ms-auto"></i>
	        </a>
	          <ul id="layers" class="nav-content collapse " data-bs-parent="#sidebar-nav">
	          <% if(layerList!=null && layerList.size()>0){%>
					<%
						for (Map.Entry<String, String> entry : layerList.entrySet()) {
					%>
					<li class="paddingli3">
						<span class="nav-sub-contents">
							<input id="<%=entry.getKey()%>" name="<%=entry.getKey() %>" type="checkbox" data-toggle="toggle" data-on="ON" data-off="OFF" data-onstyle="success" data-offstyle="danger" data-size="mini" data-width="20" data-style="android" onchange="layerSelection(this)">
							<a class="default-red" id="<%=entry.getKey()+"_legend"%>" href="javascript:loadingData('<%=entry.getValue()%>','http://<%=IP%>/geoserver/<%=WORKSPACE%>/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=<%=WORKSPACE%>:<%=entry.getKey()%>&outputFormat=application/json','<%=entry.getKey().toUpperCase()%>','0')">
								<%=entry.getValue()%>
							</a>
						</span>
						
					</li>
					<%}%>
				<%} %>
	        </ul>
      </li>
<%}else if(pageName.equalsIgnoreCase("web")){ %>
      <li class="nav-item">
        <a class="nav-link <%if(!currentPage.equalsIgnoreCase("dashboard")){out.print(" collapsed");} %>" href="${pageContext.request.contextPath}/dashboard">
          <img src="${pageContext.request.contextPath}/images/<%=style%>/Dashboard.svg" alt="" height="30" width="30">
          <span>Dashboard</span>
        </a>
      </li>     
   	 <li class="nav-item">
       	<a class="nav-link <%if(!currentPage.equalsIgnoreCase("gis")){out.print(" collapsed");} %>" href="${pageContext.request.contextPath}/gis" class="gradientmenu" target="_blank">
    		<img src="${pageContext.request.contextPath}/images/<%=style%>/Encroachment.svg" alt="" height="30" width="30">
    		<span> Spatial Map</span>
   		 </a>
   	</li>
   	<li class="nav-item">
        <a class="nav-link <%if(!currentPage.equalsIgnoreCase("sa")){out.print(" collapsed");} %>" data-bs-target="#sa" data-bs-toggle="collapse" href="#">
          <img src="${pageContext.request.contextPath}/images/<%=style%>/Encroachment.svg" alt="" height="30" width="30">
          <span>Spatial Analysis</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
         <ul id="sa" class="nav-content collapse <%if(currentPage.equalsIgnoreCase("sa")){out.print(" show");} %>" data-bs-parent="#sidebar-nav">
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/patrollingroute"> Create Patrolling Route</a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="#"> Forest Damage</a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/gis/risk" target="_blank"> Risk & Loss Mapping</a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="#"> Critical Resources</a>
            </span>
          </li>
       	</ul>
       </li>
       
  <li class="nav-item">
        <a class="nav-link <%if(!currentPage.equalsIgnoreCase("ns")){out.print(" collapsed");} %>" data-bs-target="#ns" data-bs-toggle="collapse" href="#">
          <img src="${pageContext.request.contextPath}/images/<%=style%>/Encroachment.svg" alt="" height="30" width="30">
          <span>Non-Spatial</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
         <ul id="ns" class="nav-content collapse <%if(currentPage.equalsIgnoreCase("ns")){out.print(" show");} %>" data-bs-parent="#sidebar-nav">
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <a  href="${pageContext.request.contextPath}/forestfire">
					<img src="${pageContext.request.contextPath}/images/<%=style%>/Fire.svg" alt="" height="30" width="30">
		    		Forest Fire
		   		 </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <a href="${pageContext.request.contextPath}/patrolling">
		    		<img src="${pageContext.request.contextPath}/images/<%=style%>/Patrolling.svg" alt="" height="30" width="30">
		    		Patrolling
		   		 </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <a href="${pageContext.request.contextPath}/plantation">
	    			<img src="${pageContext.request.contextPath}/images/<%=style%>/Plantation.svg" alt="" height="30" width="30">
	    			Plantation
	   		 	</a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <a href="${pageContext.request.contextPath}/offense">
	    			<img src="${pageContext.request.contextPath}/images/<%=style%>/Incident Reporting.svg" alt="" height="30" width="30">
		    		Offense
		   		 </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	  <a href="${pageContext.request.contextPath}/wildlife">
		    		<img src="${pageContext.request.contextPath}/images/<%=style%>/Wildlife Management.svg" alt="" height="30" width="30">
		    		Wild Life
		   		 </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <a href="${pageContext.request.contextPath}/projectarea">
		    		<img src="${pageContext.request.contextPath}/images/<%=style%>/Working Plan.svg" alt="" height="30" width="30">
		    		Project Area
		   		 </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	  <a href="${pageContext.request.contextPath}/assetmapping">
	    			<img src="${pageContext.request.contextPath}/images/<%=style%>/Assets Mapping.svg" alt="" height="30" width="30">
	    			Asset Mapping
	   		 	</a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <a href="${pageContext.request.contextPath}/forestblock">
		    		<img src="${pageContext.request.contextPath}/images/<%=style%>/Block Survey.svg" alt="" height="30" width="30">
		    		Forest Block
		   		 </a>
            </span>
          </li>
       	</ul>
       </li>
       <li class="nav-item">
        <a class="nav-link <%if(!currentPage.equalsIgnoreCase("pt")){out.print(" collapsed");} %>" data-bs-target="#pt" data-bs-toggle="collapse" href="#">
          <img src="${pageContext.request.contextPath}/images/<%=style%>/Encroachment.svg" alt="" height="30" width="30">
          <span>Permit and Transit</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
         <ul id="pt" class="nav-content collapse <%if(currentPage.equalsIgnoreCase("pt")){out.print(" show");} %>" data-bs-parent="#sidebar-nav">
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="#"> Consignee/Customer Search</a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="#"> Permit</a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="#">Transit Passs </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="#">Online TP Verification </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="#">Check Point Verification </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="#">Track TP</a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="#">Search Transit Pass </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="#">Payment Clearance</a>
            </span>
          </li>
       	</ul>
       </li>
<!-- start Code Paramita -->
	   <li class="nav-item">
        <a class="nav-link <%if(!currentPage.equalsIgnoreCase("pj")){out.print(" collapsed");} %>" data-bs-target="#pj" data-bs-toggle="collapse" href="#">
          <img src="${pageContext.request.contextPath}/images/<%=style%>/Encroachment.svg" alt="" height="30" width="30">
          <span>Plantation Journal</span><i class="bi bi-chevron-down ms-auto"></i>
		  
        </a>
         <ul id="pj" class="nav-content collapse <%if(currentPage.equalsIgnoreCase("pj")){out.print(" show");} %>" data-bs-parent="#sidebar-nav">
          <li class="paddingli">
          	<span class="nav-sub-contents">
				<i class="bi bi-list"></i>
				<a  href="${pageContext.request.contextPath}/pjmastermodule">
					 <!-- <img src="${pageContext.request.contextPath}/images/<%=style%>/Master Module.svg" alt="" height="30" width="30"> -->
		    		Master Module
		   		 </a>
            </span>

          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> 
				 <a href="${pageContext.request.contextPath}/pjlandmodule"> 
					Land Module
				</a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> 
				 <a  href="${pageContext.request.contextPath}/pjplantationmodule">
					Plantation Module 
				</a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> 
				 <a href="${pageContext.request.contextPath}/pjtechnicalstatusofsoil">
					Technical Status of Soil
				 </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> 
				 <a href="${pageContext.request.contextPath}/pjmapuploadmodule">
					Map Upload Module  
                   </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> 
				 <a href="${pageContext.request.contextPath}/pjusermanagement">
					User Management 
                 </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> 
				 <a href="${pageContext.request.contextPath}/pjformauthorisationmodule">
					Form Authorisation module 
                 </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> 
				 <a href="${pageContext.request.contextPath}/pjprintplantationjournalonline">Print plantation journal online & Reports

				 </a>
            </span>
          </li>
       	</ul>
       </li>
<!-- end Code Paramita -->

<!-- start Code Paramita -->
	   <li class="nav-item">
        <a class="nav-link <%if(!currentPage.equalsIgnoreCase("cwu")){out.print(" collapsed");} %>" data-bs-target="#cwu" data-bs-toggle="collapse" href="#">
          <img src="${pageContext.request.contextPath}/images/<%=style%>/Encroachment.svg" alt="" height="30" width="30">
          <span>Creation Work Updation</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
         <ul id="cwu" class="nav-content collapse <%if(currentPage.equalsIgnoreCase("cwu")){out.print(" show");} %>" data-bs-parent="#sidebar-nav">
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> 
				 <a href="${pageContext.request.contextPath}/cwumastermodule"> Master Module

				 </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/cwulandmodule"> Land Module</a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/cwuplantationmodule">Plantation Module </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> 
				 <a href="${pageContext.request.contextPath}/cwutechnicalstatusofsoil">
					Technical Status of Soil
				 </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> 
				 <a href="${pageContext.request.contextPath}/cwumapuploadmodule">
					Map Upload Module  
                   </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> 
				 <a href="${pageContext.request.contextPath}/cwuusermanagement">
					User Management 
                 </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/cwuformauthorisationmodule">
					Form Authorisation module 
                 </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> 
				 <a href="${pageContext.request.contextPath}/cwuprintplantationjournalonline">Print plantation journal online & Reports

				 </a>
            </span>
          </li>
       	</ul>
       </li>
<!-- end Code Paramita -->

<!-- start Code Paramita -->
	   <li class="nav-item">
        <a class="nav-link <%if(!currentPage.equalsIgnoreCase("mu")){out.print(" collapsed");} %>" data-bs-target="#mu" data-bs-toggle="collapse" href="#">
          <img src="${pageContext.request.contextPath}/images/<%=style%>/Encroachment.svg" alt="" height="30" width="30">
          <span>Maintenance Updation</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
         <ul id="mu" class="nav-content collapse <%if(currentPage.equalsIgnoreCase("mu")){out.print(" show");} %>" data-bs-parent="#sidebar-nav">
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/mumastermodule"> Master Module</a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/mulandmodule"> Land Module</a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/muplantationmodule">Plantation Module </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/mutechnicalstatusofsoil">Technical Status of Soil </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/mumapuploadmodule">Map upload module  </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/muusermanagement">User Management </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/muformauthorisationmodule">Form Authorisation module </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/muprintplantationjournalonline">Print plantation journal online & Reports</a>
            </span>
          </li>
       	</ul>
       </li>
<!--end Code Paramita -->

<!-- start Code Paramita -->
	   <li class="nav-item">
        <a class="nav-link <%if(!currentPage.equalsIgnoreCase("iu")){out.print(" collapsed");} %>" data-bs-target="#iu" data-bs-toggle="collapse" href="#">
          <img src="${pageContext.request.contextPath}/images/<%=style%>/Encroachment.svg" alt="" height="30" width="30">
          <span>Inspection Updation</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
         <ul id="iu" class="nav-content collapse <%if(currentPage.equalsIgnoreCase("iu")){out.print(" show");} %>" data-bs-parent="#sidebar-nav">
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/iumastermodule"> Master Module</a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/iulandmodule"> Land Module</a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/iuplantationmodule">Plantation Module </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/iutechnicalstatusofsoil">Technical Status of Soil </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/iumapuploadmodule">Map upload module  </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/iuusermanagement">User Management </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/iuformauthorisationmodule">Form Authorisation module </a>
            </span>
          </li>
          <li class="paddingli">
          	<span class="nav-sub-contents">
            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/iuprintplantationjournalonline">Print plantation journal online & Reports</a>
            </span>
          </li>
       	</ul>
       </li>
	   <!-- end Code Paramita -->

<% } else if(pageName.equalsIgnoreCase("cpanel")){ %>
      <li class="nav-item">
        <a class="nav-link <%if(!currentPage.equalsIgnoreCase("cpanel")){out.print(" collapsed");} %>" href="${pageContext.request.contextPath}/admin/cpanel">
          <i class="bi bi-gear"></i>
          <span>Control Panel</span>
        </a>
      </li>   
      <li class="nav-item">
	        <a class="nav-link <%if(!currentPage.equalsIgnoreCase("userslist")){out.print(" collapsed");} %>" data-bs-target="#users" data-bs-toggle="collapse" href="#">
	          <i class="bi bi-people"></i><span>Users</span><i class="bi bi-chevron-down ms-auto"></i>
	        </a>
          <ul id="users" class="nav-content collapse <%if(currentPage.equalsIgnoreCase("userslist")){out.print(" show");} %>" data-bs-parent="#sidebar-nav">
	          <li class="paddingli">
	          	<span class="nav-sub-contents">
	            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/admin/userslist"> Users List</a>
	            </span>
	          </li>
	          <li class="paddingli">
	          	<span class="nav-sub-contents">
	            	 <i class="bi bi-person"></i> <a href="${pageContext.request.contextPath}/admin/adduser"> Add User </a>
	            </span>
	          </li>
        	</ul>
        </li>  
      <li class="nav-item">
	        <a class="nav-link <%if(!currentPage.equalsIgnoreCase("layerslist")){out.print(" collapsed");} %>" data-bs-target="#layers" data-bs-toggle="collapse" href="#">
	          <i class="bi bi-map"></i><span>Layers</span><i class="bi bi-chevron-down ms-auto"></i>
	        </a>
          <ul id="layers" class="nav-content collapse <%if(currentPage.equalsIgnoreCase("layerslist")){out.print(" show");} %>" data-bs-parent="#sidebar-nav">
	          <li class="paddingli">
	          	<span class="nav-sub-contents">
	            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/admin/layerslist"> Layers List</a>
	            </span>
	          </li>
	          <li class="paddingli">
	          	<span class="nav-sub-contents">
	            	 <i class="bi bi-map"></i> <a href="${pageContext.request.contextPath}/admin/addlayer"> Add Layer </a>
	            </span>
	          </li>
        	</ul>
        </li>
        <li class="nav-item">
	        <a class="nav-link <%if(!currentPage.equalsIgnoreCase("groupslist")){out.print(" collapsed");} %>" data-bs-target="#groups" data-bs-toggle="collapse" href="#">
	          <i class="bi bi-people"></i><span>Groups</span><i class="bi bi-chevron-down ms-auto"></i>
	        </a>
          <ul id="groups" class="nav-content collapse <%if(currentPage.equalsIgnoreCase("groupslist")){out.print(" show");} %>" data-bs-parent="#sidebar-nav">
	          <li class="paddingli">
	          	<span class="nav-sub-contents">
	            	 <i class="bi bi-list"></i> <a href="${pageContext.request.contextPath}/admin/groupslist"> Groups List</a>
	            </span>
	          </li>
	          <li class="paddingli">
	          	<span class="nav-sub-contents">
	            	 <i class="bi bi-people"></i> <a href="${pageContext.request.contextPath}/admin/aadgroup"> Add Group </a>
	            </span>
	          </li>
        	</ul>
        </li>
         <li class="nav-item">
        <a class="nav-link <%if(!currentPage.equalsIgnoreCase("app")){out.print(" collapsed");} %>" href="${pageContext.request.contextPath}/admin/appVersion">
          <i class="bi bi-android"></i>
          <span>App Version</span>
        </a>
      </li>  
<%}%>
</ul>
  </aside><!-- End Sidebar-->