<%
	String userName = (String)session.getAttribute("userName");
	String isadmin = (String)session.getAttribute("isadmin");
	String pageName = request.getParameter("page");
%>
<!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
      <a href="${pageContext.request.contextPath}/dashboard" class="logo d-flex align-items-center">
        <img src="${pageContext.request.contextPath}/images/logo.png" alt="">
        <span class="d-none d-lg-block">DFMS</span>
      </a>
      <i class="bi bi-list toggle-sidebar-btn"></i>
      <%if(pageName!=null && pageName.equalsIgnoreCase("gis")){ %>
      <div id="tools">
        <a href="#" class="tool-icon" id="pan" title="Move the Map" onclick="panCursor();"><i class="bi bi-hand-index"></i></a>
        <a href="#" class="tool-icon" title="Default Zoom" onclick="defaultZoomLevel()"><i class="bi bi-arrows-move"></i></a>
        <a href="#" class="tool-icon" title="Zoom In" onclick="zoomMap('I')"><i class="bi bi-zoom-in"></i></a>
        <a href="#" class="tool-icon" title="Zoom Out" onclick="zoomMap('O')"><i class="bi bi-zoom-out"></i></a>
        <div class="btn-group" title="Measurement">
		  <button type="button" class="btn btn-default dropdown-toggle tool-icon" data-bs-toggle="dropdown" aria-expanded="false">
		    <i class="bi bi-symmetry-vertical"></i>
		  </button>
		  <ul class="dropdown-menu">
		    <li>
		    	<span class="dropdown-item tools-item">
		   		 	
		   		 	<a href="#" onclick="measurement('X');"><i class="bi bi-circle-fill"></i> Point</a> 
		   		 	<input id="xy_button" type="checkbox" class="hide-item">
				</span>
		    </li>
		    <li><hr class="dropdown-divider"></li>
		    <li>
		    	<span class="dropdown-item">
		   		 	<a href="#" onclick="measurement('L');"><i class="bi bi-border-width"></i> Line</a> 
		   		 	<input id="length_button" type="checkbox" class="hide-item">
				</span>
		    </li>
		    <li><hr class="dropdown-divider"></li>
		    <li>
		    	<span class="dropdown-item">
		   		 	<a href="#"  onclick="measurement('P');"><i class="bi bi-border-width"></i> Polygon</a>
		   		 	<input id="polygon_length_button" type="checkbox" class="hide-item">
				</span>
		    </li>
		  </ul>
		</div>
        <a href="#" class="tool-icon" id="buffer" title="Buffer" onclick="openBuffer()"><i class="bi bi-vinyl"></i></a>
         <div class="btn-group" title="Spatial Analysis">
		  <button type="button" class="btn btn-default dropdown-toggle tool-icon" data-bs-toggle="dropdown" aria-expanded="false">
		    <i class="bi bi-gear"></i>
		  </button>
		  <ul class="dropdown-menu">
		    <li class="dropdown-item ">
				<a href="#"><i class="bi bi-union"></i> Union</a>
		    </li>
		    <li><hr class="dropdown-divider"></li>
		   	<li class="dropdown-item ">
				<a href="#"><i class="bi bi-box-fill"></i> Area</a>
		    </li>
		    <li><hr class="dropdown-divider"></li>
			<li class="dropdown-item ">
				<a href="#"><i class="bi bi-bounding-box"></i> Intersect</a>
		    </li>

		  </ul>
		</div>
        <a href="#" id="spath" class="tool-icon" title="Shortest Path" onclick="shortPath()"><i class="bi bi-signpost-split"></i></a>
        <a href="#" id="filter" class="tool-icon" title="Filter" onclick="openFilter()"><i class="bi bi-funnel"></i></a>
        <a href="#" class="tool-icon" title="Clear Map" onclick="clearMeasurement()"><i class="bi bi-x-circle"></i></a>
        
        
		<select class="form-control form-control-custom input-sm" id="attr_limit" onchange="setAttributeLimit(this.value,'S');" title="Attribute Limit">
			<option value="10" selected="selected">10</option>
			<option value="20">20</option>
			<option value="30">30</option>
			<option value="40">40</option>
			<option value="50">50</option>
			<option value="100">100</option>
		</select>
		<input id="atr_button" type="checkbox" data-toggle="toggle" data-on="Attribute" data-off="Attribute" data-onstyle="success"
			data-offstyle="danger" data-width="60" data-size="mini" data-style="android" title="Attibute Details" checked="checked">
			
        </div> 
        <%} %>
    </div><!-- End Logo -->


    <nav class="header-nav ms-auto">
      <ul class="d-flex align-items-center">

        <li class="nav-item dropdown">

          <a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">
            <i class="bi bi-phone"></i>
            <span class="badge bg-danger badge-number">1</span>
          </a><!-- End Messages Icon -->

          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow messages">
            <li class="dropdown-header">
              You have 1 new messages
              <a href="#"><span class="badge rounded-pill bg-primary p-2 ms-2">View all</span></a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="message-item">
              <a href="#">
                <div>
                  <h4>Mukesh Rout</h4>
                  <p>SOS.</p>
                  <p>4 hrs. ago</p>
                </div>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="dropdown-footer">
              <a href="#">Show all messages</a>
            </li>

          </ul><!-- End Messages Dropdown Items -->

        </li><!-- End Messages Nav -->
        
        <li class="nav-item dropdown">

          <a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">
            <i class="bi bi-bell"></i>
            <span id="notificationNo">
            </span>
          </a><!-- End Notification Icon -->

		  <div id="notInfo">
		  </div>          
        </li><!-- End Notification Nav -->
        
        <li class="nav-item dropdown pe-3">

          <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
			<i class="bi bi-person"></i>
            <span class="d-none d-md-block dropdown-toggle ps-2"><%=userName %></span>
          </a><!-- End Profile Iamge Icon -->

          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
            <li class="dropdown-header">
             <img src="${pageContext.request.contextPath}/images/profile-img.jpg" alt="Profile" class="rounded-circle">
              <h6><%=userName %></h6>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li>
              <a class="dropdown-item d-flex align-items-center" href="${pageContext.request.contextPath}/profile">
                <i class="bi bi-person"></i>
                <span>My Profile</span>
              </a>
            </li>
            <li>
            <hr class="dropdown-divider">
            </li>
            <%if(isadmin.equalsIgnoreCase("Y")){ %>
	            <li class="nav-item">
		        	<a class="dropdown-item d-flex align-items-center" href="${pageContext.request.contextPath}/admin/cpanel">
	                	<i class="bi bi-gear"></i>
	                	<span>Control Panel</span>
	              	</a>
			   	</li>
			   	<li>
	            <hr class="dropdown-divider">
            <%} %>
            </li>
            <li>
              <a class="dropdown-item d-flex align-items-center" href="${pageContext.request.contextPath}/logout">
                <i class="bi bi-box-arrow-right"></i>
                <span>Sign Out</span>
              </a>
            </li>

          </ul><!-- End Profile Dropdown Items -->
        </li><!-- End Profile Nav -->

      </ul>
    </nav><!-- End Icons Navigation -->

  </header>