<%
	String pageName = request.getParameter("page");
	String currentPage = request.getParameter("currentPage");
	
	String isadmin = (String)session.getAttribute("isadmin");
	
	if(currentPage.equalsIgnoreCase("home")){
%>
	<ul>
	    <li><a href="#" class="gradientmenu active" id="tab_1" title="HOME"><i class="fa fa-home icon-sandybrowen"></i></a></li>
	    <li><a href="#" class="gradientmenu" id="tab_2" title="ATTRIBUTE DETAILS"><i class="fa fa-list icon-sky"></i></a></li>
	    <li><a href="#" class="gradientmenu" id="tab_3" title="TOOLS"><i class="fa fa-wrench icon-yellow"></i></a></li>
	    <%if(isadmin.equalsIgnoreCase("Y")){ %>
	    	<li><a href="#" class="gradientmenu" id="tab_4" title="ADD LAYER"><i class="fa fa-database icon-red"></i></a></li>
	    	<li><a href="#" class="gradientmenu" id="tab_5" title="USERS"><i class="fa fa-users icon-green"></i></a></li>
	    <%} %>
	</ul>
<%}else{ %>
	<ul>
		<li><a href="${pageContext.request.contextPath}/gis" class="gradientmenu" title="HOME"><i class="fa fa-home icon-sandybrowen"></i></a></li>
		<li><a href="${pageContext.request.contextPath}/logout" class="gradientmenu" title="LOGOUT"><i class="fa fa-sign-out icon-red"></i></a></li>
	</ul>
<%}%>