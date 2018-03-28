<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<style> table {
    font-family: arial, sans-serif;
     font-size:15px;
    border-collapse: collapse;
    width:100%;
    border-color: black;
    
}td, th {
    border: 1px solid #ddd;
    text-align: left;
    padding: 8px;
}
	 </style>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tableSearch.css">
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
						<i class="fa fa-file-o"></i>Unpaid Sale Bills
					</h1>
						
				</div>
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Sale Bills List
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/orders"></a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
						</div>
	<form onsubmit="return confirm('Do you really want to Approve Payment ?');" id="validation-form" class="form-horizontal"
						action="${pageContext.request.contextPath}/approvedSalePayment" method="post">
						<div class="box-content">
							<div class="clearfix"></div>
							<div class="table-responsive" style="border: 0" >
								<table width="100%" id="table1" >
									<thead>
										<tr  style="background-color: rgba(191, 182, 182, 0.13);color: rgba(128, 126, 128, 0.91);">
										<th align="left">Select</th>
											<th width="80" style="width: 18px">No.</th>
										                <th width="150" align="left">Invoice No.</th>
														<th width="150" align="left">Invoice Date</th>
														<th width="100" align="left">Customer</th>
														<th width="60" align="left">Type</th>
														<th width="100" align="left">Value</th>
														<th width="80" align="left">Tax Amt</th>
														<th width="150" align="left">Grand Total</th>
													    <th width="100" align="left">Discount</th>
													    <th width="100" align="left">Paid Amt</th>
													    <th width="150" align="left">Remaining Amt</th>
										</tr>
									</thead>
									<tbody>
							  <%int c=1; %>
										<c:forEach items="${unPaidBills}" var="bill">
											<tr>
											 <td><input type="checkbox" name="select_to_approve"
																id="select_to_approve" 
																value="${bill.billNo}" ></td>
												<td>	<%=c++%>
											<c:out
														value="${c}" /> 
												</td>
													<td align="left"><c:out	value="${bill.invoiceNo}"></c:out></td>
															<td align="left"><c:out value="${bill.invoiceDate}"></c:out></td>
															<td align="left"><c:out value="${bill.custName}"></c:out></td>
															<td align="left"><c:choose>
															<c:when test="${bill.custType==1}">
															<c:out value="Wholesaler"></c:out>
															</c:when>
															<c:when test="${bill.custType==2}">
															<c:out value="Retailer"></c:out>
															</c:when>
															</c:choose></td>
															<td style="text-align:right;"><c:out
																	value="${bill.taxableAmt}"></c:out></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.taxAmt}"></c:out></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.grandTotal}"></c:out></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.discountAmt}"></c:out></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.paidAmt}"></c:out></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.remAmt}"></c:out></td>											</tr>
										</c:forEach>
                                       <c:forEach items="${unPaidBillsExpGretor}" var="bill">
											<tr  bgcolor="#ff99cc">
											 <td><input type="checkbox" name="select_to_approve"
																id="select_to_approve" 
																value="${bill.billNo}" ></td>
												<td>	<%=c++%>
											<c:out value="${c}" /> 
												</td>
													<td align="left"><c:out	value="${bill.invoiceNo}"></c:out></td>
															<td align="left"><c:out value="${bill.invoiceDate}"></c:out></td>
															<td align="left"><c:out value="${bill.custName}"></c:out></td>
															<td align="left"><c:choose>
															<c:when test="${bill.custType==1}">
															<c:out value="Wholesaler"></c:out>
															</c:when>
															<c:when test="${bill.custType==2}">
															<c:out value="Retailer"></c:out>
															</c:when>
															</c:choose></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.taxableAmt}"></c:out></td>
															<td align="left"style="text-align:right;"><c:out
																	value="${bill.taxAmt}"></c:out></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.grandTotal}"></c:out></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.discountAmt}"></c:out></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.paidAmt}"></c:out></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.remAmt}"></c:out></td>											</tr>
										</c:forEach>
										<c:forEach items="${unPaidBillsBlocked}" var="bill">
										<tr  bgcolor="#f44141" >
										 <td><input type="checkbox" name="select_to_approve"
																id="select_to_approve" 
																value="${bill.billNo}" ></td>
												<td>	<%=c++%>
											<c:out value="${c}" /> 
												</td>
													<td align="left"><c:out	value="${bill.invoiceNo}"></c:out></td>
															<td align="left"><c:out value="${bill.invoiceDate}"></c:out></td>
															<td align="left"><c:out value="${bill.custName}"></c:out></td>
															<td align="left"><c:choose>
															<c:when test="${bill.custType==1}">
															<c:out value="Wholesaler"></c:out>
															</c:when>
															<c:when test="${bill.custType==2}">
															<c:out value="Retailer"></c:out>
															</c:when>
															</c:choose></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.taxableAmt}"></c:out></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.taxAmt}"></c:out></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.grandTotal}"></c:out></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.discountAmt}"></c:out></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.paidAmt}"></c:out></td>
															<td align="left" style="text-align:right;"><c:out
																	value="${bill.remAmt}"></c:out></td>								</tr>
											</c:forEach>
									</tbody>
								</table>
							</div><br><br><div class="row">
							<div class="col-md-12" style="text-align: center">
								 <input type="submit" class="btn btn-info" value="Paid" >
							</div>
						</div>
						</div></form>
					</div>
				</div>
			</div>
			<!-- END Main Content -->
			<footer>
			<p>2017 © SONA ELECTRICALS.</p>
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