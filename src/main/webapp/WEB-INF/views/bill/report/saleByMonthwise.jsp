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
						<i class="fa fa-file-o"></i>Sale Report(Monthwise)
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
								<i class="fa fa-bars"></i>Sale Report
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>




						<div class="box-content">
							<form action="${pageContext.request.contextPath}/searchMonthWiseSale" class="form-horizontal"
								method="post" id="validation-form">

	                         <div class="form-group">
									<label class="col-sm-2 col-lg-2 control-label">From Date</label>
									<div class="col-sm-4 col-lg-3 controls">
									
								<input class="form-control date-picker" id="fromDate" size="16"
													type="text" name="fromDate"
													 placeholder="From Date" value="${fromDate}" required/>
									</div>
							  
									<label class="col-sm-2 col-lg-2 control-label">To Date</label>
									<div class="col-sm-4 col-lg-3 controls">
									
								<input class="form-control date-picker" id="toDate" size="16"
													type="text" name="toDate"
													 placeholder="To Date" value="${toDate}" required/>
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
											<i class="fa fa-table"></i>Sale Report List
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
									<div class="table-wrap" >
									
										<table id="table1" class="table table-bordered table-striped fill-head">
											<thead>
												<tr class="bgpink">
													<th width="5" style="width: 18px">No.</th>
														<th width="150" align="left">Month</th>
														<th width="100" align="left">Value</th>
														<th width="80" align="left">CGST Amt</th>
														<th width="80" align="left">SGST Amt</th>
														<th width="80" align="left">IGST Amt</th>
														<th width="80" align="left">CESS Amt</th>
														<th width="100" align="left">Grand Total</th>
													    <th width="100" align="left">Discount Amt</th>
													    <th width="100" align="left">Paid Amt</th>
													    <th width="100" align="left">Remaining Amt</th>
												</tr>
												</thead> 
                          
												<tbody>
													   <c:forEach items="${billHeaderList}" var="bill" varStatus="count">
														<tr>
															
															<td><c:out value="${count.index+1}"/></td>
														
															<td align="left"><c:out
																	value="${bill.invoiceNo}"></c:out></td>
														
															<td align="left"><c:out
																	value="${bill.taxableAmt}"></c:out></td>
																	<td align="left"><c:out
																	value="${bill.cgstRs}"></c:out></td>
																	<td align="left"><c:out
																	value="${bill.sgstRs}"></c:out></td>
																	<td align="left"><c:out
																	value="${bill.igstRs}"></c:out></td>
																	<td align="left"><c:out
																	value="${bill.cessRs}"></c:out></td>
															
															<td align="left"><c:out
																	value="${bill.grandTotal}"></c:out></td>
															<td align="left"><c:out
																	value="${bill.discountAmt}"></c:out></td>
															<td align="left"><c:out
																	value="${bill.paidAmt}"></c:out></td>
															<td align="left"><c:out
																	value="${bill.remAmt}"></c:out></td>
												    	
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
			<p>2018 © MONGINIS.</p>
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
function billPdf()
{

var checkedVals = $('.chk:checkbox:checked').map(function() {
    return this.value;
}).get();
checkedVals=checkedVals.join(",");

if(checkedVals=="")
	{
	alert("Please Select Bill No.")
	}
else
	{
	   window.open('${pageContext.request.contextPath}/pdf?url=pdf/showBillsPdf/'+checkedVals);

	}

}
function singleBill(billNo)
{
	
	 window.open('${pageContext.request.contextPath}/pdf?url=pdf/showBillsPdf/'+billNo);

}
</script>

</body>

</html>
