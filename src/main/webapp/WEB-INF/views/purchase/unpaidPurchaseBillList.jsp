<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	 
	<c:url var="getServicingWithDate" value="/getServicingWithDate"></c:url>
	<c:url var="getBomAllListWithDate" value="/getBomAllListWithDate"></c:url> 


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
						<i class="fa fa-file-o"></i> Pending Payment List
					</h1>
					
				</div>
				
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">
		 
				 
					 
					<div class="box" id="todayslist">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i> Pending Payment List
							</h3>
							
							<div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							 
						</div>
					<form onsubmit="return confirm('Do you really want to Approve Payment ?');" id="validation-form" class="form-horizontal"
						action="${pageContext.request.contextPath}/approvedPayment" method="post">
						<div class=" box-content">
					<div class="row">
					
					 
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
									<th align="left"><input type="checkbox"
													onClick="selectAll(this)"
													  /> Select All</th>
										<th>Sr.No.</th>
										<th>Invoice No</th>
										<th>Invoice Date</th> 
										<th>Supplier Name</th> 
										<th>Bill AMT</th>
										<th>Date1</th>
										<th>Date2</th>
										<th>Date3</th>
										<th>Date4</th>
										<th>Action</th>

									</tr>
								</thead>
								<tbody>
								<c:set var = "srNo" value="0"/>
								<!--status 4 -->
									<c:forEach items="${sts4List}" var="sts4List"
													varStatus="count"> 
													
														<c:set var = "color" value="red"/> 
													 
													<tr>
													 <td><input type="checkbox" name="select_to_approve"
																id="select_to_approve" 
																value="${sts4List.purchaseId}" ></td>
														<td style="color: <c:out value = "${color}"/>"><c:out value="${srNo+1}" /></td>
 														<c:set var = "srNo" value="${srNo+1}"/> 
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${sts4List.invoiceNo}" /></td> 
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${sts4List.invDate}" /></td> 
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${sts4List.suppName}" /></td>
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${sts4List.billAmt}" /></td>
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${sts4List.cdDate1}" /></td> 
														 <td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${sts4List.cdDate2}" /></td> 
														 <td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${sts4List.cdDate3}" /></td> 
														 <td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${sts4List.cdDate4}" /></td> 
													 <td><a href="${pageContext.request.contextPath}/purchaseHeaderWithDetail/${sts4List.purchaseId}" class="action_btn" ><abbr title="Details"><i class="fa fa-list"></i></abbr></a></td>
												</tr>
										</c:forEach>
										<!-- status 3 -->
								<c:forEach items="${sts3List}" var="sts3List"
													varStatus="count"> 
													 <c:set var = "color" value="red"/> 
													<tr>
													<td><input type="checkbox" name="select_to_approve"
																id="select_to_approve" 
																value="${sts3List.purchaseId}" ></td>
														<td><c:out value="${srNo+1}" /></td>
 														<c:set var = "srNo" value="${srNo+1}"/> 
														<td align="left"><c:out value="${sts3List.invoiceNo}" /></td> 
														<td align="left"><c:out value="${sts3List.invDate}" /></td> 
														<td align="left"><c:out value="${sts3List.suppName}" /></td>
														<td align="left"><c:out value="${sts3List.billAmt}" /></td>
														<td align="left"><c:out value="${sts3List.cdDate1}" /></td> 
														<td align="left"><c:out value="${sts3List.cdDate2}" /></td> 
														<td align="left"><c:out value="${sts3List.cdDate3}" /></td> 
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${sts3List.cdDate4}" /></td> 
													 <td><a href="${pageContext.request.contextPath}/purchaseHeaderWithDetail/${sts3List.purchaseId}" class="action_btn" ><abbr title="Details"><i class="fa fa-list"></i></abbr></a></td>
												</tr>
										</c:forEach> 
										
											<!-- status 2 -->
									 <c:forEach items="${sts2List}" var="sts2List"
													varStatus="count"> 
													 <c:set var = "color" value="red"/> 
													<tr>
													<td><input type="checkbox" name="select_to_approve"
																id="select_to_approve" 
																value="${sts2List.purchaseId}" ></td>
														<td ><c:out value="${srNo+1}" /></td>
 														<c:set var = "srNo" value="${srNo+1}"/> 
														<td align="left"><c:out value="${sts2List.invoiceNo}" /></td> 
														<td align="left"><c:out value="${sts2List.invDate}" /></td> 
														<td align="left"><c:out value="${sts2List.suppName}" /></td>
														<td align="left"><c:out value="${sts2List.billAmt}" /></td>
														<td align="left"><c:out value="${sts2List.cdDate1}" /></td> 
														<td align="left"><c:out value="${sts2List.cdDate2}" /></td> 
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${sts2List.cdDate3}" /></td> 
														<td align="left"><c:out value="${sts2List.cdDate4}" /></td> 
													 <td><a href="${pageContext.request.contextPath}/purchaseHeaderWithDetail/${sts2List.purchaseId}" class="action_btn" ><abbr title="Details"><i class="fa fa-list"></i></abbr></a></td>
												</tr>
										</c:forEach>
										
											<!-- status 1 -->
									 <c:forEach items="${sts1List}" var="sts1List"
													varStatus="count"> 
													 <c:set var = "color" value="red"/> 
													<tr>
													<td><input type="checkbox" name="select_to_approve"
																id="select_to_approve" 
																value="${sts1List.purchaseId}" ></td>
														<td ><c:out value="${srNo+1}" /></td>
 														<c:set var = "srNo" value="${srNo+1}"/> 
														<td align="left"><c:out value="${sts1List.invoiceNo}" /></td> 
														<td align="left"><c:out value="${sts1List.invDate}" /></td> 
														<td align="left"><c:out value="${sts1List.suppName}" /></td>
														<td align="left"><c:out value="${sts1List.billAmt}" /></td>
														<td align="left"><c:out value="${sts1List.cdDate1}" /></td> 
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${sts1List.cdDate2}" /></td> 
														<td align="left" ><c:out value="${sts1List.cdDate3}" /></td> 
														<td align="left"><c:out value="${sts1List.cdDate4}" /></td> 
													 <td><a href="${pageContext.request.contextPath}/purchaseHeaderWithDetail/${sts1List.purchaseId}" class="action_btn" ><abbr title="Details"><i class="fa fa-list"></i></abbr></a></td>
												</tr>
										</c:forEach>

								</tbody>
							</table>
						</div>
						 
					</div>
					<div class="row">
							<div class="col-md-12" style="text-align: center">
								 <input type="submit" class="btn btn-info" value="Submit" >
							</div>
						</div>

		</div>
		</form>
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
		
		 
</body>
</html>