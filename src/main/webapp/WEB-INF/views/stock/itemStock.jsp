<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	 
	
<c:url var="getSfndRawItem" value="/getSfndRawItem"></c:url>

 

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
						<i class="fa fa-file-o"></i>Item Stock
					</h1>
				</div>
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Item Stock
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						
						<div class="box-content">

							<form id="completproduction" action="${pageContext.request.contextPath}/submitStock" method="post">
							<div class="box-content">
							 <div class="col-md-2">Date</div>
							 <div class="col-md-2">
							 <input type="text" name='date' class='form-control'  id='date'   value="${date}" readonly>
							</div>
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
							 
							
							<div class=" box-content">
								<div class="row">
								<div class="col-md-12 table-responsive">
									<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Item Name</th>
										<th> Qty</th>
										<th> Purchase Rate</th>
										<th> Wholesale Rate</th>
										<th> Retail Rate</th>
										<th> Total</th>
									</tr>
								</thead>
									<tbody>
											<c:forEach items="${stockDetailList}" var="stockDetailList"
													varStatus="count">
													  
													<tr>
												  
														<td ><c:out value="${count.index+1}" /></td>  
														<td ><c:out value="${stockDetailList.itemName}" /></td> 
												 	 	<td align="right" ><input type="text" name='openQty${stockDetailList.itemId }' class='form-control' onchange="changeQty(${stockDetailList.itemId });" id='openQty${stockDetailList.itemId}'   value="${stockDetailList.openingStock}"> 
												 	 	<input type="hidden" name='originalOpenQty${stockDetailList.itemId }' id='originalOpenQty${stockDetailList.itemId}'   value="${stockDetailList.openingStock}"></td>
												 
												 	 	<td align="right" ><input type="text" name='purchaseRate${stockDetailList.itemId }' class='form-control' onchange="changeQty(${stockDetailList.itemId });" id='purchaseRate${stockDetailList.itemId}'   value="${stockDetailList.lastPurchaseRate}"> 
												 	 	<input type="hidden" name='originalPurchaseRate${stockDetailList.itemId }' id='originalPurchaseRate${stockDetailList.itemId}'   value="${stockDetailList.lastPurchaseRate}"></td>
												 	 	
												 	 	<td align="right" ><input type="text" name='wholesaleRate${stockDetailList.itemId }' class='form-control' onchange="changeQty(${stockDetailList.itemId });" id='wholesaleRate${stockDetailList.itemId}'   value="${stockDetailList.lastWholesaleRate}"> 
												 	 	<input type="hidden" name='originalWholesaleRate${stockDetailList.itemId }' id='originalWholesaleRate${stockDetailList.itemId}'   value="${stockDetailList.lastWholesaleRate}"></td>
												 	 	
												 	 	<td align="right" ><input type="text" name='retailRate${stockDetailList.itemId }' class='form-control' onchange="changeQty(${stockDetailList.itemId });" id='retailRate${stockDetailList.itemId}'   value="${stockDetailList.lastRetailRate}"> 
												 	 	<input type="hidden" name='originalRetailRate${stockDetailList.itemId }' id='originalRetailRate${stockDetailList.itemId}'   value="${stockDetailList.lastRetailRate}"></td>
												 	 	
												 	 	<td align="right" ><input type="text" name='total${stockDetailList.itemId }' class='form-control' onchange="changeQty(${stockDetailList.itemId });" id='total${stockDetailList.itemId}'   value="${stockDetailList.total}" readonly> 
												 	 	<input type="hidden" name='originalTotal${stockDetailList.itemId }' id='originalTotal${stockDetailList.itemId}'   value="${stockDetailList.total}"></td>
												 	 	
												 	 	 
												  		<%-- <td align="right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${purchaseDetailList.cgstRs+purchaseDetailList.sgstRs+purchaseDetailList.igstRs}"/></td>
												  		  --%> 
													</tr>
																 
												</c:forEach> 
									</tbody>
									</table>
								</div>
								</div>
							</div>
							
							
							
												<div align="center" class="form-group">
												<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										
													<input type="submit" class="btn btn-primary" value="Request">
												</div>
											</div>
													
							
									

							
							<div class="box-content">
							
							
							</div><br><br><br>
							
							

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
	function changeQty(itemId)
	{
		 
		var openQty=document.getElementById("openQty"+itemId+"").value;
	  	var originalOpenQty=parseFloat(document.getElementById("originalOpenQty"+itemId+"").value);
	   	var purchaseRate=parseFloat(document.getElementById("purchaseRate"+itemId+"").value);
	   	var originalPurchaseRate=parseFloat(document.getElementById("originalPurchaseRate"+itemId+"").value); 
	   	var wholesaleRate=parseFloat(document.getElementById("wholesaleRate"+itemId+"").value);
	   	var originalWholesaleRate=parseFloat(document.getElementById("originalWholesaleRate"+itemId+"").value);
		var retailRate=parseFloat(document.getElementById("retailRate"+itemId+"").value);
	   	var originalRetailRate=parseFloat(document.getElementById("originalRetailRate"+itemId+"").value);
	   	var total=parseFloat(document.getElementById("total"+itemId+"").value);
	   	var originalTotal=parseFloat(document.getElementById("originalTotal"+itemId+"").value);
	   	
	   	if(openQty=="" || isNaN(openQty))
	   		{
	   		 
	   		document.getElementById("total"+itemId+"").value=(originalOpenQty*purchaseRate).toFixed(2);
	   		document.getElementById("openQty"+itemId+"").value=originalOpenQty;
	   		
	   		}
	   	else{
	   		document.getElementById("total"+itemId+"").value=(openQty*purchaseRate).toFixed(2);
	   	    }
	   	
		if(purchaseRate=="" || isNaN(purchaseRate))
	   		{
			document.getElementById("total"+itemId+"").value=(openQty*originalPurchaseRate).toFixed(2);
	   		document.getElementById("purchaseRate"+itemId+"").value=originalPurchaseRate; 
	   		}
		else{
				if(openQty=="")
					{
					document.getElementById("total"+itemId+"").value=(originalOpenQty*purchaseRate).toFixed(2);
					}
				else
					{
					document.getElementById("total"+itemId+"").value=(openQty*purchaseRate).toFixed(2);
					}
	   			
	   	    }
		
		if(wholesaleRate=="" || isNaN(wholesaleRate))
	   		{
	   		document.getElementById("wholesaleRate"+itemId+"").value=originalWholesaleRate; 
	   		}
		if(retailRate=="" || isNaN(retailRate))
	   		{
	   		document.getElementById("retailRate"+itemId+"").value=originalRetailRate; 
	   		} 
		 
	}
	</script> 				
</body>
</html>