<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body> 
<c:url var="addItemInPurchaseBill" value="/addItemInPurchaseBill"></c:url>
<c:url var="editPurchaseItemList" value="/editPurchaseItemList"></c:url>
<c:url var="updatePurchaseHeader" value="/updatePurchaseHeader"></c:url>
<c:url var="deleteItemPurchaseList" value="/deleteItemPurchaseList"></c:url>
<c:url var="checkIsSupplierSameState" value="/checkIsSupplierSameState"></c:url>

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
					<i class="fa fa-file-o"></i>Generate Barcode
				</h1>
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->


		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Generate Barcode
				</h3>
				
				<div class="box-tool">
								<a href="${pageContext.request.contextPath}/purchaseBillList">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

			</div>

			<div class=" box-content">

				<div class="box">
					<form onsubmit="return confirm('Do you really want to submit the form?');" id="validation-form" class="form-horizontal"
						action="${pageContext.request.contextPath}/generateBarcode" method="post">

						 
						<div class="box-content">

							<div class="col-md-2">Supplier</div>
							<div class="col-md-3">
							 
							<c:forEach items="${supplierList}" var="supplierList">
							<c:choose>
								 <c:when test="${supplierList.suppId==purchaseHeader.suppId}">
								 ${supplierList.suppName}
								 </c:when>
							 </c:choose>
							</c:forEach>
							
								 
							</div>
							
						 
						</div>
						<br>
						
						<div class="box-content">
						<div class="col-md-2" >Invoice no.</div>
									<div class="col-md-3">
										${purchaseHeader.invoiceNo}
									</div>
							<div class="col-md-1"></div>
							<div class="col-md-2" >Invoice no. Date</div>
									<div class="col-md-3">
										${purchaseHeader.invDate}
									</div>
									
						</div>
							<br><br>
						 
 
						<div class=" box-content">
							<div class="row">
								<div style="overflow:scroll;height:100%;width:100%;overflow:auto">
									<table width="100%" border="0"class="table table-bordered table-striped fill-head "
										style="width: 100%" id="table_grid">
										<thead>
											<tr> 
											<th align="left"><input type="checkbox"
													onClick="selectAll(this)"
													  /> Select All</th>
												<th>Sr.No.</th> 
												<th >Batch No.</th>
												<th >Item</th>
												<th>In Qty</th>  
												  
											</tr>
										</thead>
										<tbody>
										
										<c:forEach items="${purchaseHeader.purchaseDetailList}" var="purchaseDetailList"
													varStatus="count">
													  
													<tr>
												   <td><input type="checkbox" name="select_to_approve"
																id="select_to_approve" 
																value="${purchaseDetailList.purDetailId}" ></td>
													 
														<td ><c:out value="${count.index+1}" /></td> 
														<td ><c:out value="${purchaseDetailList.batchNo}" /></td>
														<td ><c:out value="${purchaseDetailList.itemName}" /></td> 
												 	 	<td align="right" ><c:out value="${purchaseDetailList.recQty}" /> </td>
												 	 	 
													</tr>
																 
												</c:forEach> 
										</tbody>
									</table>
								</div>
							</div>

						</div>
						 
							 
						<br />
						<br />

						 <div class="row">
							<div class="col-md-12" style="text-align: center">
								 <input type="submit" class="btn btn-info" value="Generate Barcode" >
							</div>
						</div> 

					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- END Main Content -->

	<footer>
	<p>2017 © Monginis.</p>
	</footer>

	<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
		class="fa fa-chevron-up"></i></a>





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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-tags-input/jquery.tagsinput.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-pwstrength/jquery.pwstrength.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-duallistbox/duallistbox/bootstrap-duallistbox.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/dropzone/downloads/dropzone.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/clockface/js/clockface.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-switch/static/js/bootstrap-switch.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/ckeditor/ckeditor.js"></script>

	<!--flaty scripts-->
	<script src="${pageContext.request.contextPath}/resources/js/flaty.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/flaty-demo-codes.js"></script>


	 
</body>
</html>