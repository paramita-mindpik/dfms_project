<%@page import="com.gis.util.StringUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../jsp/common/jscss.jsp"></jsp:include>
<%
	List<Map<String, Object>> fireList = (List)session.getAttribute("fireList");
%>
<body>
	<jsp:include page="../jsp/common/navbar.jsp">
		<jsp:param value="web" name="page"/>
	</jsp:include>
	<jsp:include page="../jsp/common/sidebar.jsp">
   		<jsp:param value="web" name="page"/>
   		<jsp:param value="dashboard" name="currentPage"/>
    </jsp:include>

  <main id="main" class="main">
    <div class="pagetitle">
      <h1>Dashboard</h1>
    </div><!-- End Page Title -->

    <section class="section dashboard">
      <div class="row">
        <div class="col-lg-8">
          <div class="row">
            <!-- Reports -->
            <div class="col-12">
              <div class="card">
            <div class="card-body">
              <h5 class="card-title">District Wise Fire Report<span>| Today</span></h5>

              <!-- Column Chart -->
              <div id="columnChart"></div>

              <script>
                document.addEventListener("DOMContentLoaded", () => {
                  new ApexCharts(document.querySelector("#columnChart"), {
                    series: [{
                      name: 'FSI',
                      data: [44, 55, 57, 56, 61, 58, 63, 60, 66]
                    }, {
                      name: 'Field',
                      data: [76, 85, 101, 98, 87, 105, 91, 114, 94]
                    }],
                    chart: {
                      type: 'bar',
                      height: 350
                    },
                    plotOptions: {
                      bar: {
                        horizontal: false,
                        columnWidth: '75%',
                        endingShape: 'rounded'
                      },
                    },
                    dataLabels: {
                      enabled: true
                    },
                    stroke: {
                      show: true,
                      width: 2,
                      colors: ['transparent']
                    },
                    xaxis: {
                      categories: ['Alipurduar', 'Bankura', 'Birbhum', 'Darjeeling', 'Howrah', 'North 24 Parganas', 'Hooghly', 'Purba Burdwan', 'Kolkata'],
                    },
                    fill: {
                      opacity: 1
                    },
                    tooltip: {
                      y: {
                        formatter: function(val) {
                          return  val 
                        }
                      }
                    }
                  }).render();
                });
              </script>

            </div>
          </div>
            </div>

            <div class="col-12">
              <div class="card recent-sales overflow-auto">
                <div class="card-body">
                  <h5 class="card-title">Fire Detail <span>| Today</span></h5>

                  <table class="table table-borderless datatable">
                    <thead>
                      <tr>
                        <th scope="col">Fire location</th>
                        <th scope="col">Range Name</th>
                        <th scope="col">Temperature</th>
                        <th scope="col">Affected Area</th>
                        <th scope="col">Status</th>
                      </tr>
                    </thead>
                    <tbody>
                    	<%
                    			for(int a=0;a<fireList.size();a++){
                    		%>
                    		<tr>
	                    		<td><%=StringUtils.nullReplaceWithEmpty(fireList.get(a).get("division").toString()) %> </td>
	                    		<td><%=StringUtils.nullReplaceWithEmpty(fireList.get(a).get("range").toString()) %> </td>
	                    		<td>&nbsp;</td>
	                    		<td><%=StringUtils.nullReplaceWithEmpty(fireList.get(a).get("area_affec").toString()) %> </td>
	                    		<td><span class="badge bg-warning">Pending</span></td>
                    	</tr>
                    <%} %>
                    </tbody>
                  </table>

                </div>

              </div>
            </div>
            <div class="col-12">
              <div class="card recent-sales overflow-auto">
	            <div class="card-body">
	              <h5 class="card-title">Forest Revenue Graph</h5>
	
	              <!-- Bar Chart -->
	              <canvas id="barChart" style="max-height: 400px;"></canvas>
	              <script>
	                document.addEventListener("DOMContentLoaded", () => {
	                  new Chart(document.querySelector('#barChart'), {
	                    type: 'bar',
	                    data: {
	                      labels: ['Royality', 'Action', 'Comp', 'Penality', 'Tickets', 'Reservation'],
	                      datasets: [{
	                        label: 'Forest Revenue',
	                        data: [65, 59, 80, 81, 56, 55],
	                        backgroundColor: [
	                          'rgba(255, 99, 111, 0.2)',
	                          'rgba(255, 159, 64, 0.2)',
	                          'rgba(255, 205, 86, 0.2)',
	                          'rgba(75, 192, 192, 0.2)',
	                          'rgba(54, 162, 235, 0.2)',
	                          'rgba(153, 102, 255, 0.2)'
	                        ],
	                        borderColor: [
	                          'rgb(255, 99, 132)',
	                          'rgb(255, 159, 64)',
	                          'rgb(255, 205, 86)',
	                          'rgb(75, 192, 192)',
	                          'rgb(54, 162, 235)',
	                          'rgb(153, 102, 255)'
	                        ],
	                        borderWidth: 1
	                      }]
	                    },
	                    options: {
	                      scales: {
	                        y: {
	                          beginAtZero: true
	                        }
	                      }
	                    }
	                  });
	                });
	              </script>
	            </div>
	          </div>
            </div>
        	<div class="col-12">
              <div class="card recent-sales overflow-auto">
                <div class="card-body">
                  <h5 class="card-title">Forest Revenue <span>&nbsp;</span></h5>

                  <table class="table table-borderless">
                    <thead>
                      <tr>
                        <th scope="col">Royality</th>
                        <th scope="col">Auction/Sale</th>
                        <th scope="col">Compounding</th>
                        <th scope="col">Penalty</th>
                        <th scope="col">Tickets</th>
                        <th scope="col">Reservation</th>
                      </tr>
                    </thead>
                    <tbody>
                    	<tr>
	                    	<td>2127109</td>
	                    	<td>0</td>
	                    	<td>113000</td>
	                    	<td>0</td>
	                    	<td>0</td>
	                    	<td>0</td>
	                    </tr>	
                    </tbody>
                  </table>

                </div>

              </div>
            </div>
          <div class="row">
            <div class="col-4">
            	<div class="alert alert-success fade show" role="alert">
	                <h4 class="alert-heading">Total Crime Alert</h4>
	                <p>0</p>
              	</div>
          	</div>
          	<div class="col-4">
            	<div class="alert alert-warning fade show" role="alert">
	                <h4 class="alert-heading">Total Forest Produce Movement</h4>
	                <p>892</p>
              	</div>
          	</div>
          	<div class="col-4">
            	<div class="alert alert-primary fade show" role="alert">
	                <h4 class="alert-heading">Total Crime Cases</h4>
	                <p>0</p>
              	</div>
          	</div>
          	<div class="col-4">
            	<div class="alert alert-warning fade show" role="alert">
	                <h4 class="alert-heading">Permit Request</h4>
	                <p>6</p>
              	</div>
          	</div>
          	<div class="col-4">
            	<div class="alert alert-success fade show" role="alert">
	                <h4 class="alert-heading">Total Permit Issued</h4>
	                <p>2</p>
              	</div>
          	</div>
          	<div class="col-4">
            	<div class="alert alert-warning fade show" role="alert">
	                <h4 class="alert-heading">Total TP Issued</h4>
	                <p>172</p>
              	</div>
          	</div>
          	<div class="col-4">
            	<div class="alert alert-danger fade show" role="alert">
	                <h4 class="alert-heading">Crime Alert</h4>
	                <p>0</p>
              	</div>
          	</div>
          	<div class="col-4">
            	<div class="alert alert-info fade show" role="alert">
	                <h4 class="alert-heading">New TP Request</h4>
	                <p>16</p>
              	</div>
          	</div>
          	<div class="col-4">
            	<div class="alert alert-success fade show" role="alert">
	                <h4 class="alert-heading">Consignee Registration</h4>
	                <p>1000</p>
              	</div>
          	</div>
          </div>
          </div>
        </div>

        <!-- Right side columns -->
        <div class="col-lg-4">
	              <div class="card info-card fire-card">
	                <div class="card-body">
	                  <h5 class="card-title">Fire <span>| Today</span></h5>
	
	                  <div class="d-flex align-items-center">
	                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
	                      <i class="bi bi-fire"></i>
	                    </div>
	                    <div class="ps-3">
	                      <h6>145</h6>
	                      <span class="text-success small pt-1 fw-bold">FSI - 120</span> <span class="text-muted small pt-2 ps-1"> | Field - 25</span>
	
	                    </div>
	                  </div>
	                </div>
	               </div>
	              <div class="card info-card plant-card">
	                <div class="card-body">
	                  <h5 class="card-title">Plantation <span>| Month</span></h5>
	
	                  <div class="d-flex align-items-center">
	                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
	                      <i class="bi bi-tree-fill"></i>
	                    </div>
	                    <div class="ps-3">
	                      <h6>1209</h6>
	                      <span class="text-success small pt-1 fw-bold"></span> <span class="text-muted small pt-2 ps-1"></span>
	
	                    </div>
	                  </div>
	                </div>
	
	              </div>
	              <div class="card info-card fire-card">
	                <div class="card-body">
	                  <h5 class="card-title">Patrolling <span>| Today</span></h5>
	
	                  <div class="d-flex align-items-center">
	                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
	                      <i class="bi bi-geo"></i>
	                    </div>
	                    <div class="ps-3">
	                      <h6>1244</h6>
	                      <span class="text-success small pt-1 fw-bold">Active - 12</span> <span class="text-muted small pt-2 ps-1"> | In-Active - 10</span>
	
	                    </div>
	                  </div>
	                </div>
	
	              </div>
	              <div class="card info-card fire-card">
	                <div class="card-body">
	                  <h5 class="card-title">Offense <span>| Month</span></h5>
	
	                  <div class="d-flex align-items-center">
	                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
	                      <i class="bi bi-fingerprint"></i>
	                    </div>
	                    <div class="ps-3">
	                      <h6>1254</h6>
	                      <span class="text-success small pt-1 fw-bold">&nbsp;</span> <span class="text-muted small pt-2 ps-1">&nbsp;</span>
	
	                    </div>
	                  </div>
	                </div>
	
	              </div>
	              <div class="card info-card plant-card">
	                <div class="card-body">
	                  <h5 class="card-title">Wildlife <span>| Month</span></h5>
	
	                  <div class="d-flex align-items-center">
	                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
	                      <i class="bi bi-image-alt"></i>
	                    </div>
	                    <div class="ps-3">
	                      <h6>145</h6>
	                      <span class="text-success small pt-1 fw-bold">Death - 12</span> <span class="text-muted small pt-2 ps-1">Injured - 12</span>
	
	                    </div>
	                  </div>
	                </div>
	
	              </div>

          <!-- Recent Activity -->
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Recent Activity <span>| Today</span></h5>

              <div class="activity">
              
              	<div class="activity-item d-flex">
                  <div class="activite-label">29 min</div>
                  <i class='bi bi-circle-fill activity-badge text-success align-self-start'></i>
                  <div class="activity-content">
                    Fire Department notified <a href="#" class="fw-bold text-dark"></a>
                  </div>
                </div>

				<div class="activity-item d-flex">
                  <div class="activite-label">30 min</div>
                  <i class='bi bi-circle-fill activity-badge text-success align-self-start'></i>
                  <div class="activity-content">
                    Alert send to range officer of <a href="#" class="fw-bold text-dark">, Range Beliatore</a>
                  </div>
                </div>
                
                <div class="activity-item d-flex">
                  <div class="activite-label">32 min</div>
                  <i class='bi bi-circle-fill activity-badge text-danger align-self-start'></i>
                  <div class="activity-content">
                    Fire occured in Bankura (N) <a href="#" class="fw-bold text-dark">, Range Beliatore</a> Affected area approx. 0.48
                  </div>
                </div>
              </div>

            </div>
          </div><!-- End Recent Activity -->


          <div class="card">

            <div class="card-body pb-0">
              <h5 class="card-title">News &amp; Updates <span>| Today</span></h5>

              <div class="news">
                <div class="post-item clearfix">
                  <h4><a href="#">Nihil blanditiis at in nihil autem</a></h4>
                  <p>Sit recusandae non aspernatur laboriosam. Quia enim eligendi sed ut harum...</p>
                </div>

                <div class="post-item clearfix">
                  <h4><a href="#">Quidem autem et impedit</a></h4>
                  <p>Illo nemo neque maiores vitae officiis cum eum turos elan dries werona nande...</p>
                </div>

              </div><!-- End sidebar recent posts-->

            </div>
          </div><!-- End News & Updates -->
          
          <div class="card">
		            <div class="card-body pb-0">
		              <h5 class="card-title">Website Traffic <span>| Today</span></h5>
		
		              <div id="trafficChart" style="min-height: 400px;" class="echart"></div>
		
		              <script>
		                document.addEventListener("DOMContentLoaded", () => {
		                  echarts.init(document.querySelector("#trafficChart")).setOption({
		                    tooltip: {
		                      trigger: 'item'
		                    },
		                    legend: {
		                      top: '5%',
		                      left: 'center'
		                    },
		                    series: [{
		                      name: 'Access From',
		                      type: 'pie',
		                      radius: ['40%', '70%'],
		                      avoidLabelOverlap: false,
		                      label: {
		                        show: false,
		                        position: 'center'
		                      },
		                      emphasis: {
		                        label: {
		                          show: true,
		                          fontSize: '18',
		                          fontWeight: 'bold'
		                        }
		                      },
		                      labelLine: {
		                        show: false
		                      },
		                      data: [{
		                          value: 12,
		                          name: 'Web User'
		                        },
		                        {
		                          value: 4,
		                          name: 'App user'
		                        }
		                      ]
		                    }]
		                  });
		                });
		              </script>
		
		            </div>
		          </div><!-- End Website Traffic -->
          

        </div><!-- End Right side columns -->

      </div>
    </section>

  </main><!-- End #main -->
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
  <!-- Template Main JS File -->
  <script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>