<%
	String PRIVATE_LABEL = (String) getServletContext().getAttribute("PRIVATE_LABEL");
	String token = (String)session.getAttribute("token");
%>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="rdd" content="<%=token%>">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title><%=PRIVATE_LABEL%></title>


  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/lib/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/lib/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/lib/quill/quill.snow.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/lib/quill/quill.bubble.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/lib/remixicon/remixicon.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/lib/simple-datatables/style.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
  
  <!-- Vendor JS Files -->
  <script src="${pageContext.request.contextPath}/lib/apexcharts/apexcharts.min.js"></script>
  <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/lib/chart.js/chart.min.js"></script>
  <script src="${pageContext.request.contextPath}/lib/echarts/echarts.min.js"></script>
  <script src="${pageContext.request.contextPath}/lib/quill/quill.min.js"></script>
  <script src="${pageContext.request.contextPath}/lib/simple-datatables/simple-datatables.js"></script>
  <script src="${pageContext.request.contextPath}/lib/tinymce/tinymce.min.js"></script>
  <script src="${pageContext.request.contextPath}/lib/php-email-form/validate.js"></script>
  <script src="${pageContext.request.contextPath}/lib/jquery/js/jQueryv2.0.3.js"></script>
  <script src="${pageContext.request.contextPath}/js/angular.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	
</head>