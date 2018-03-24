<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	 
	
<c:url var="getBetweenDateStock" value="/getBetweenDateStock"></c:url>

 

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
						<i class="fa fa-file-o"></i>Current Stock
					</h1>
				</div>
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Current Stock
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						
						<div class="box-content">

							<form id="completproduction" action="${pageContext.request.contextPath}/StockDayEnd" method="post">
							 
							<div class="box-content">
								 <div class="col-md-2">Current Stock Date</div>
								 	<div class="col-md-3">
									 <input type="text" name='date' class='form-control'  id='date'   value="${stockDate}" readonly>
									</div>
									<div class="col-md-1"></div>
									 <div class="col-md-1">Select Type </div>
								 	<div class="col-md-3">
									<select name="uomId" id="stockType"  class="form-control" onchange="unlockDiv();"  >
										 <option value="1">Get Current Stock</option>
										 <option value="2">Between Date</option>
										</select>
									</div>
							</div><br><br>
							
							<div class="box-content" id="selectFromDate" style="display: none">
								 <div class="col-md-2">From Date</div>
								 	<div class="col-md-3">
									 <input class="form-control date-picker" id="fromDate" size="16"
											 type="text" name="fromDate"   />
									</div>
									<div class="col-md-1"></div>
									 <div class="col-md-1">To Date</div>
								 	<div class="col-md-3">
									 <input class="form-control date-picker" id="toDate" size="16"
											 type="text" name="toDate"   />
									</div>
							</div><br>
							
							<div class="box-content" id="selectItemInfo" style="display: none">
								 <div class="col-md-2">Select Category</div>
								 	<div class="col-md-3">
									<select name="catId" id="catId"  class="form-control"   >
										 <option value="">Select Category</option> 
											 <c:forEach items="${itemCategoryList}" var="itemCategoryList">
												<option value="${itemCategoryList.catId}"><c:out value="${itemCategoryList.catName}"/></option>
												</c:forEach> 
										</select>
									</div>
									<div class="col-md-1"></div>
									 <div class="col-md-1"><input type="button" class="btn btn-primary" onclick="getBetweenDateStock();" value="Search"></div>
								  
							</div><br><br>
							
							<div align="center" id="loader" style="display: none">

							<span>
								<h4>
									<font color="#343690">Loading</font>
								</h4>
							</span> <span class="l-1"></span> <span class="l-2"></span> <span
						class="l-3"></span> <span class="l-4"></span> <span class="l-5"></span>
					<span class="l-6"></span>
				</div>
							 
							
							<div class=" box-content" id="currentStockTable">
								<div class="row">
								<div class="col-md-12 table-responsive">
									<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Item Name</th>
										<th>Opening Stock</th>
										<th>Purchase Qty</th>
										<th>Sell Qty</th>
										<th>GRN Qty</th>
										<th>Closing Qty</th>
										<th>Purchase Rate</th>
										<th>Total</th>
										<th>Wholesale Rate</th>
										<th>Retail Rate</th>
									</tr>
								</thead>
									<tbody>
											<c:forEach items="${currentStockList}" var="currentStockList"
													varStatus="count">
													  
													<tr>
												  
														<td ><c:out value="${count.index+1}" /></td>  
														<td ><c:out value="${currentStockList.itemName}" /></td> 
												 	 	<td align="right" ><input type="text" name='openQty${currentStockList.itemId }' class='form-control' onchange="changeQty(${currentStockList.itemId });" id='openQty${currentStockList.itemId}'   value="${currentStockList.openingStock}" readonly> 
												 	 	<input type="hidden" name='originalOpenQty${currentStockList.itemId }' id='originalOpenQty${currentStockList.itemId}'   value="${currentStockList.openingStock}"></td>
												 
												 	 	<td align="right" ><input type="text" name='totalPurchase${currentStockList.itemId }' class='form-control' onchange="changeQty(${currentStockList.itemId });" id='totalPurchase${currentStockList.itemId}'   value="${currentStockList.totalPurchase}" readonly> 
												 	 	<input type="hidden" name='originalTotalPurchase${currentStockList.itemId }' id='originalTotalPurchase${currentStockList.itemId}'   value="${currentStockList.totalPurchase}"></td>
												 	 	
												 	 	<td align="right" ><input type="text" name='totalSale${currentStockList.itemId }' class='form-control' onchange="changeQty(${currentStockList.itemId });" id='totalSale${currentStockList.itemId}'   value="${currentStockList.totalSale}" readonly> 
												 	 	<input type="hidden" name='originalTotalSale${currentStockList.itemId }' id='originalTotalSale${currentStockList.itemId}'   value="${currentStockList.totalSale}"></td>
												 	 	
												 	 	<td align="right" ><input type="text" name='totalGrn${currentStockList.itemId }' class='form-control' onchange="changeQty(${currentStockList.itemId });" id='totalGrn${currentStockList.itemId}'   value="${currentStockList.totalGrn}" readonly> 
												 	 	<input type="hidden" name='originalRetailRate${currentStockList.itemId }' id='originalRetailRate${currentStockList.itemId}'   value="${currentStockList.totalGrn}"></td>
												 	 	
												 	 	<c:set var="closingQty" value="${currentStockList.openingStock+currentStockList.totalPurchase-currentStockList.totalSale-currentStockList.totalGrn}"></c:set>
												 	 	
												 	 	<td align="right" ><input type="text" name='closingQty${currentStockList.itemId }' class='form-control' onchange="changeQty(${currentStockList.itemId });" id='closingQty${currentStockList.itemId}'   value="${closingQty}" > 
												 	 	<input type="hidden" name='originalClosingQty${currentStockList.itemId }' id='originalClosingQty${currentStockList.itemId}'   value="${closingQty}"></td>
												 	 	
												 	 	 <td align="right" ><input type="text" name='lastPurchaseRate${currentStockList.itemId }' class='form-control' onchange="changeQty(${currentStockList.itemId });" id='lastPurchaseRate${currentStockList.itemId}'   value="${currentStockList.lastPurchaseRate}" readonly> 
												 	 	<input type="hidden" name='originalPurchaseRate${currentStockList.itemId }' id='originalPurchaseRate${currentStockList.itemId}'   value="${currentStockList.lastPurchaseRate}"></td>
												 	 	
												 	 	 <td align="right" ><input type="text" name='total${currentStockList.itemId }' class='form-control' onchange="changeQty(${currentStockList.itemId });" id='total${currentStockList.itemId}'   value="${currentStockList.lastPurchaseRate*closingQty}" readonly> 
												 	 	<input type="hidden" name='originalPurchaseRate${currentStockList.itemId }' id='originalPurchaseRate${currentStockList.itemId}'   value="${currentStockList.lastPurchaseRate}"></td>
												 	 	
												 	 	 <td align="right" ><input type="text" name='lastWholesaleRate${currentStockList.itemId}' class='form-control' onchange="changeQty(${currentStockList.itemId });" id='lastWholesaleRate${currentStockList.itemId}'   value="${currentStockList.lastWholesaleRate}" readonly> 
												 	 	<input type="hidden" name='originalPurchaseRate${currentStockList.itemId }' id='originalPurchaseRate${currentStockList.itemId}'   value="${currentStockList.lastWholesaleRate}"></td>
												 	 	
												 	 	 <td align="right" ><input type="text" name='lastRetailRate${currentStockList.itemId}' class='form-control' onchange="changeQty(${currentStockList.itemId });" id='lastRetailRate${currentStockList.itemId}'   value="${currentStockList.lastRetailRate}" readonly> 
												 	 	<input type="hidden" name='originalPurchaseRate${currentStockList.itemId }' id='originalPurchaseRate${currentStockList.itemId}'   value="${currentStockList.lastRetailRate}"></td>
												  		<%-- <td align="right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${purchaseDetailList.cgstRs+purchaseDetailList.sgstRs+purchaseDetailList.igstRs}"/></td>
												  		  --%> 
													</tr>
																 
												</c:forEach> 
									</tbody>
									</table>
								</div>
								</div>
							</div>
							
							 <div class=" box-content" id="dayEndButton">
							<c:choose>
								<c:when test="${stockDate!=tommorowDate}">
								<div align="center" class="form-group">
												<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										
													<input type="submit" class="btn btn-primary" value="Day End ">
												</div>
											</div>
								</c:when>
							</c:choose>
							</div>
										 <br><br>
										 
							<div class=" box-content" id="betweenDateTable" style="display: none">
								<div class="row">
								<div class="col-md-12 table-responsive">
									<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid1">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Item Name</th>
										<th>Opening Stock</th>
										<th>Purchase Qty</th>
										<th>Sell Qty</th>
										<th>GRN Qty</th>
										<th>Closing Qty</th>
										<th>Purchase Rate</th>
										<th>Total</th>
										<th>Wholesale Rate</th>
										<th>Retail Rate</th>
									</tr>
								</thead>
									<tbody>
											 
									</tbody>
									</table>
								</div>
								</div>
							</div>
							
							

						</form>
						</div>	
						</div>
					 
						
						 
					</div>
				</div>
			</div>
			<!-- END Main Content -->
			<footer>
			<p>2017 © MONGINIS.</p>
			</footer>

			<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
				class="fa fa-chevron-up"></i></a>
		
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
	<script>
	
	function getBetweenDateStock()
	{
	
		var fromDate = $("#fromDate").val();
		var toDate = $("#toDate").val();
		var catId = $("#catId").val(); 
		var date = $("#date").val(); 
		var valid = 0;
		
		if(fromDate=="")
			{
				alert("Enter From Date");
				valid=1;
			}
		else if(toDate=="" || toDate>=date)
		{
			if(toDate=="")
				alert("Enter To Date");
			else
				alert("Enter Date Befor Stock Date");
			valid=1;
		}
		else if(catId=="")
		{
			alert("Select Category");
			valid=1;
		}
		
		if(valid==0)
		{
			
		$
		.getJSON(
				'${getBetweenDateStock}',

				{
					fromDate : fromDate,
					toDate : toDate,
					catId : catId, 
					ajax : 'true',

				},
				function(data) {
					 if(data=="")
						 {
						 alert("No Record Found");
						 }
					 else
						 {
							 $("#currentStockTable").hide();
							 $("#dayEndButton").hide();
							 $("#betweenDateTable").show();
							 
							$('#table_grid1 td').remove();
							
							$.each(data,
									function(key, itemList) {
										
										 
										var tr = $('<tr></tr>');
											tr.append($('<td></td>').html(key+1));
											tr.append($('<td></td>').html(itemList.itemName));
											tr.append($('<td></td>').html(itemList.openingStock));
										  	tr.append($('<td></td>').html(itemList.totalPurchase));
										  	tr.append($('<td></td>').html(itemList.totalSale));
										  	tr.append($('<td></td>').html(itemList.totalGrn));
										  	tr.append($('<td></td>').html(itemList.closingStock));
										  	tr.append($('<td></td>').html(itemList.lastPurchaseRate));
										  	tr.append($('<td></td>').html((itemList.lastPurchaseRate*itemList.closingStock).toFixed(2)));
										  	tr.append($('<td></td>').html(itemList.lastWholesaleRate));
										  	tr.append($('<td></td>').html(itemList.lastRetailRate)); 
										  	$('#table_grid1 tbody').append(tr);
									})
						 }
					
					 
					
				});
		}
		
	}
	 
function unlockDiv() {
	 
		var flag = $('#stockType').val();
		if(flag==2)
		{
			$('#selectFromDate').show();
			$("#selectItemInfo").show(); 
		}
		else
			{
			$('#selectFromDate').hide();
			$("#selectItemInfo").hide(); 
			$("#currentStockTable").show();
			$("#betweenDateTable").hide();
			$("#dayEndButton").show();
			}
		 
	 
}
	function changeQty(itemId)
	{
		 
		var closingQty=document.getElementById("closingQty"+itemId+"").value;
	  	var originalClosingQty=parseFloat(document.getElementById("originalClosingQty"+itemId+"").value);
	   	var lastPurchaseRate=parseFloat(document.getElementById("lastPurchaseRate"+itemId+"").value);  
	   	  
		if(closingQty=="" || isNaN(closingQty))
	   		{
	   		document.getElementById("closingQty"+itemId+"").value=originalClosingQty; 
	   		document.getElementById("total"+itemId+"").value=(originalClosingQty*lastPurchaseRate).toFixed(2); 
	   		}
		else
			{
			document.getElementById("total"+itemId+"").value=(closingQty*lastPurchaseRate).toFixed(2); 
			}
		 
		 
	}
	</script> 				
</body>
</html>