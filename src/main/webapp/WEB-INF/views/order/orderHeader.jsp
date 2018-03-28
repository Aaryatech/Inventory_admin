<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<div class="container" id="main-container">

		<!-- BEGIN Sidebar -->
		<div id="sidebar" class="navbar-collapse collapse">

			<jsp:include page="/WEB-INF/views/include/navigation.jsp"></jsp:include>

			<div id="sidebar-collapse" class="visible-lg">
				<i class="fa fa-angle-double-left"></i>
			</div>
			<!-- END Sidebar Collapse Button -->
		</div>
		<!-- END Sidebar -->

		<!-- BEGIN Content -->
		<div id="main-content">
			<!-- BEGIN Page Title -->
			<div class="page-title">
				<div>
					<h1>
						<i class="fa fa-file-o"></i>Orders
					</h1>

				</div>
			</div>
			<!-- END Page Title -->

			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-bars"></i>Orders
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>




						<div class="box-content">
							<form action="${pageContext.request.contextPath}/searchOrders" class="form-horizontal"
								method="post" id="validation-form">

	                         <div class="form-group">
									<label class="col-sm-2 col-lg-2 control-label">From Date</label>
									<div class="col-sm-4 col-lg-3 controls">
									
								<input class="form-control date-picker" id="fromDate" size="16"
													type="text" name="fromDate"
													required placeholder="From Date" value="${fromDate}"/>
									</div>
							  
									<label class="col-sm-2 col-lg-2 control-label">To Date</label>
									<div class="col-sm-4 col-lg-3 controls">
									
								<input class="form-control date-picker" id="toDate" size="16"
													type="text" name="toDate"
													required placeholder="To Date" value="${toDate}"/>
									</div>
							  </div>
								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-4 col-lg-6 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Search">
     							</div>
								</div>
							</form>
					         		<br>
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i>Orders
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<div class="box-content">

										<div class="clearfix"></div>
							<div id="table-scroll" class="table-scroll">
								 		
								<div id="faux-table" class="faux-table" aria="hidden">
								
									
									</div>
									<div class="table-wrap">
									
										<table id="table1" class="table table-advance">
											<thead>
												<tr class="bgpink">
													<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Order Id</th>
														<th width="100" align="left">Order Date</th>
														<th width="100" align="left">Customer</th>
														<th width="100" align="left">Customer Type</th>
														<th width="100" align="left">Status</th>
														<th width="81" align="left">Action</th>
												</tr>
												</thead> 
                          
												<tbody>
													  <c:forEach items="${orderList}" var="order" varStatus="count">
														<tr>
														
															<td><c:out value="${count.index+1}"/></td>
															<td align="left"><c:out
																	value="${order.orderId}"></c:out></td>
															
															<td align="left"><c:out
																	value="${order.orderDate}"></c:out></td>
															<td align="left"><c:out
																	value="${order.custName}"></c:out></td>
															<td align="left"><c:choose>
															<c:when test="${order.custType==1}">
															<c:out value="Wholesaler"></c:out>
															</c:when>
															<c:when test="${order.custType==2}">
															<c:out value="Retailer"></c:out>
															</c:when>
															</c:choose></td>
												    		<td align="left"><c:choose>
															<c:when test="${order.orderStatus==1}">
															<c:out value="Bill Pending"></c:out>
															</c:when>
															<c:when test="${order.orderStatus==2}">
															<c:out value="Bill Generated"></c:out>
															</c:when>
															</c:choose></td>
														
															<td align="left">
                                                        	<c:choose>
															<c:when test="${order.orderStatus==2}">
															<a href="" onclick="return billAlert()"><abbr title="BILL"></abbr></a>
															</c:when>
															<c:otherwise>
															<a href="${pageContext.request.contextPath}/bill/${order.orderId}"><abbr title="BILL"><input type="button" value="BILL" class="btn btn-default"/></abbr></a>
															
															</c:otherwise>
															</c:choose>
															&nbsp;&nbsp;
                                                       <abbr title="Bill Detail">  <a href="${pageContext.request.contextPath}/orderDetail/${order.orderId}"><span
														class="fa fa-list fa-lg"></span></abbr></a>&nbsp;&nbsp;
                                                         </td>
														</tr>

													</c:forEach> 
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
			<!-- END Main Content -->
			<footer>
			<p>2018 © SONA ELECTRICALS.</p>
			</footer>

			<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
				class="fa fa-chevron-up"></i></a>
		</div>
		<!-- END Content -->
	</div>
	<!-- END Container -->

	<!--basic scripts-->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="${pageContext.request.contextPath}/resources/assets/jquery/jquery-2.0.3.min.js"><\/script>')
	</script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/jquery-cookie/jquery.cookie.js"></script>

	<!--page specific plugin scripts-->
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.resize.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.pie.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.stack.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.crosshair.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.tooltip.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/sparkline/jquery.sparkline.min.js"></script>


	<!--page specific plugin scripts-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>

	<!--flaty scripts-->
	<script src="${pageContext.request.contextPath}/resources/js/flaty.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/flaty-demo-codes.js"></script>
	<!--page specific plugin scripts-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/clockface/js/clockface.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.js"></script>

<script type="text/javascript">
function billAlert()
{
	
alert("Bill Already Completed Of this Order")	
}
</script>
</body>

</html>
