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
					<i class="fa fa-file-o"></i>Purchase Bill
				</h1>
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->


		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Purchase Bill
				</h3>
				
				<div class="box-tool">
								<a href="${pageContext.request.contextPath}/purchaseBillList">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

			</div>

			<div class=" box-content">

				<div class="box">
					<form onsubmit="return confirm('Do you really want to submit the form?');" id="validation-form" class="form-horizontal"
						action="${pageContext.request.contextPath}/insertPurchaseBill" method="post">

						 
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
							
							<div class="col-md-1"></div>
							 <div class="col-md-2" >Vehicle No.</div>
									<div class="col-md-3">
										${purchaseHeader.vehicleNo}
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

												

												<th>Sr.No.</th> 
												<th >Batch No.</th>
												<th >Item</th>
												<th>In Qty</th>  
												<th >Basic Rate</th> 
												<th >Disc%</th>
												<th>Disc Amt <i class="fa fa-inr" style="font-size:13px"></i></th>  
												<th>Insurance Amt <i class="fa fa-inr" style="font-size:13px"></i></th>
												<th>Freight Amt <i class="fa fa-inr" style="font-size:13px"></i></th>  
												<th>Taxable<i class="fa fa-inr" style="font-size:13px"></i></th>
												<th>Tax%</th>  
												<th>Tax AMT<i class="fa fa-inr" style="font-size:13px"></i></th>  
												<th>CESS%</th>
												<th>CESS<i class="fa fa-inr" style="font-size:13px"></i></th>
												<th >Extra Charges<i class="fa fa-inr" style="font-size:13px"></i></th> 
												<th>Total<i class="fa fa-inr" style="font-size:13px"></i></th>
												<th>Wholesale Rate<i class="fa fa-inr" style="font-size:13px"></i></th>
												<th>Retail Rate<i class="fa fa-inr" style="font-size:13px"></i></th> 

											</tr>
										</thead>
										<tbody>
										
										<c:forEach items="${purchaseHeader.purchaseDetailList}" var="purchaseDetailList"
													varStatus="count">
													  
													<tr>
												  
														<td ><c:out value="${count.index+1}" /></td> 
														<td ><c:out value="${purchaseDetailList.batchNo}" /></td>
														<td ><c:out value="${purchaseDetailList.itemName}" /></td> 
												 	 	<td align="right" ><c:out value="${purchaseDetailList.recQty}" /> </td>
												 	 	<td align="right" ><c:out value="${purchaseDetailList.rate}" /> </td>
												  		<td align="right"><c:out value="${purchaseDetailList.discPer}" />
												  		<td align="right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${purchaseDetailList.discAmt+purchaseDetailList.discOnBill}"/></td>
												  		 
												  		<td align="right"><c:out value="${purchaseDetailList.insuAmt}" />
												  		<td align="right"><c:out value="${purchaseDetailList.freightAmt}" />
												  		<td align="right"><c:out value="${purchaseDetailList.taxableAmt}" />
												  		<td align="right"><c:out value="${purchaseDetailList.igstPer}" />
												  		<td align="right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${purchaseDetailList.cgstRs+purchaseDetailList.sgstRs+purchaseDetailList.igstRs}"/></td>
												  		 
												  		<td align="right"><c:out value="${purchaseDetailList.cessPer}" />
													  <td align="right"><c:out value="${purchaseDetailList.cessRs}" />
													   <td align="right"><c:out value="${purchaseDetailList.otherExtra}" />
													   <td align="right"><c:out value="${purchaseDetailList.total}" />
													   <td align="right"><c:out value="${purchaseDetailList.wholesaleRate}" />
													   <td align="right"><c:out value="${purchaseDetailList.retailRate}" />
													</tr>
																 
												</c:forEach> 
										</tbody>
									</table>
								</div>
							</div>

						</div>
						
							<div class="box-content" align="right"> 
									<div class="col-md-2">value <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2" >
											${purchaseHeader.basicValue}
										</div>
									<div class="col-md-2">Freight Amt <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2">
											${purchaseHeader.freightAmt}
										</div>
									<div class="col-md-2">CGST <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2">
											${purchaseHeader.cgst}
										</div>
							
							
							</div><br>
							<div class="box-content" align="right">
									<div class="col-md-2">Particular Disc Total <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2">
											${purchaseHeader.discAmt2}
										</div>
									<div class="col-md-2">Insu Amt <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2">
											${purchaseHeader.insuranceAmt}
										</div>
									<div class="col-md-2">SGST <i class="fa fa-inr" style="font-size:13px"></i></div>
									<div class="col-md-2">
										${purchaseHeader.sgst}
									</div>
							
							</div><br>
						
						<div class="box-content" align="right"> 
							 
						 	<div class="col-md-2">Per Disc %</div>
							  <div class="col-md-1">
								${purchaseHeader.discPer}
									
									 </div>  
											<div class="col-md-1"> 
									${purchaseHeader.discAmt}
									 
										</div>
							 <div class="col-md-2">Other3(Extra) <i class="fa fa-inr" style="font-size:13px"></i></div>
									<div class="col-md-2">
										${purchaseHeader.otherExtra}
									</div>
									
									<div class="col-md-2">IGST <i class="fa fa-inr" style="font-size:13px"></i></div>
									<div class="col-md-2">
										${purchaseHeader.igst}
									</div>
							 </div><br>
						  <div class="box-content" align="right">
								 
									<div class="col-md-2"></div>
										<div class="col-md-2">
											
										</div>
										
										<div class="col-md-2"></div>
										<div class="col-md-2">
											
										</div>
									<div class="col-md-2">Cess <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2">
											${purchaseHeader.cess}
										</div>
									
							
							</div><br>
							  
							<div class="box-content" align="right">
								 	<div class="col-md-2"> </div>
										<div class="col-md-2"> </div>
									<div class="col-md-2"> </div>
										<div class="col-md-2"> </div>
									<div class="col-md-2">Bill Total <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2">
											${purchaseHeader.billAmt}
										</div>
									
									
							
							</div><br>
							
							<div class="box-content" align="right">
								 
									<div class="col-md-2"> </div>
										<div class="col-md-2"> </div>
									<div class="col-md-2"> </div>
										<div class="col-md-2"> </div>
									
									<div class="col-md-2">Round Off <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2">
											${purchaseHeader.roundOff}
										</div>
									
							
							</div><br>
							  
							 
						<br />
						<br />

						<!-- <div class="row">
							<div class="col-md-12" style="text-align: center">
								 <input type="submit" class="btn btn-info" value="Submit" >
							</div>
						</div> -->

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


	<script>
	 
	function getItemName()
	{
	
		
		var itemName = $("#itemId option:selected").text();  
		document.getElementById("itemName").value=itemName;
		
	}
	 function isSameState()
		{
		
		 var suppId = $("#suppId").val();
			$
			.getJSON(
					'${checkIsSupplierSameState}',

					{
						 
						suppId : suppId, 
						ajax : 'true',

					},
					function(data) {
						 
						$('#table_grid td').remove();
						 
						var totalValue=0;
						var totalDiscAmt=0;
						var totalDiscOnBill=0; 
						var totalCgst=0;
						var totalSgst=0;
						var totalIgst=0;
						var totalCess=0;
						var totalBill=0;
						$.each(
								data,
								function(key, itemList) {
									
									 
									var tr = $('<tr></tr>');
										tr.append($('<td></td>').html(key+1));
										tr.append($('<td></td>').html(itemList.batchNo));
									  	tr.append($('<td></td>').html(itemList.itemName));
									  	tr.append($('<td></td>').html(itemList.recQty));
									  	tr.append($('<td></td>').html(itemList.rate));  
									  	tr.append($('<td style="text-align:right;"></td>').html(itemList.value)); 
									  	tr.append($('<td style="text-align:right;"></td>').html(itemList.discPer)); 
									  	 tr.append($('<td style="text-align:right;"></td>').html((itemList.discAmt).toFixed(2)));
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.discOnBill).toFixed(2)));
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.insuAmt).toFixed(2)));
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.freightAmt).toFixed(2)));
									  	
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.taxableAmt).toFixed(2))); 
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.igstPer).toFixed(2))); 
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.igstRs+itemList.sgstRs+itemList.cgstRs).toFixed(2)));  
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.cessPer).toFixed(2)));
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.cessRs).toFixed(2))); 
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.otherExtra).toFixed(2)));
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.total).toFixed(2))); 
									  	
									  	tr.append($('<td style="text-align:right;"></td>').html('<input style="text-align:right; width:100px" type="text" onchange="changeRate('+key+')" id="wholesaleRate'+key+'" value="'+(itemList.wholesaleRate).toFixed(2)+'"  class="form-control" '+
											  	'  name="wholesaleRate'+key+'" required><input type="hidden"  id="wholesaleRateOrigingal'+key+'"  value="'+(itemList.wholesaleRate).toFixed(2)+'"  >'));
											  	
										tr.append($('<td style="text-align:right;"></td>').html('<input style="text-align:right; width:100px" type="text" onchange="changeRate('+key+')" id="retailRate'+key+'" value="'+(itemList.retailRate).toFixed(2)+'"  class="form-control" '+
													  	' name="retailRate'+key+'" required><input type="hidden"  id="retailRateOrigingal'+key+'"  value="'+(itemList.retailRate).toFixed(2)+'"  >'));
											  	
									  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" onclick="edit('+key+');"></span> <span class="glyphicon glyphicon-remove" onclick="deleteItem('+key+');""></span>')); 
										$('#table_grid tbody').append(tr);
										
										totalValue=totalValue+itemList.value;
										totalDiscAmt=totalDiscAmt+itemList.discAmt;
										totalDiscOnBill=totalDiscOnBill+itemList.discOnBill;
										totalCgst=totalCgst+itemList.cgstRs;
										totalSgst=totalSgst+itemList.sgstRs;
										totalIgst=totalIgst+itemList.igstRs;
										totalCess=totalCess+itemList.cessRs;
										totalBill=totalBill+itemList.total;

								})
								
						document.getElementById("basicValue").value=(totalValue).toFixed(2);
						document.getElementById("discAmt2").value=(totalDiscAmt).toFixed(2);
						document.getElementById("discAmt").value=(totalDiscOnBill).toFixed(2);
						document.getElementById("cgst").value=(totalCgst).toFixed(2);
						document.getElementById("sgst").value=(totalSgst).toFixed(2);
						document.getElementById("igst").value=(totalIgst).toFixed(2);
						document.getElementById("cess").value=(totalCess).toFixed(2);
						document.getElementById("billAmount").value=(totalBill).toFixed(2);
						           
						document.getElementById("itemId").value="";
						document.getElementById("recQty").value="1";
						document.getElementById("rate").value="1";
						document.getElementById("discPer").value="0";
						document.getElementById("index").value="";
						
					});
			
			
		}
	function addItemInList()
	{
		
		
		var itemId = $("#itemId").val(); 
		var recQty = $("#recQty").val(); 
		var rate = $("#rate").val(); 
		var discPer = $("#discPer").val(); 
		var itemName = $("#itemName").val();
		var discPerOnBill = $("#discPerOnBill").val();
		var extraCharges = $("#extraCharges").val();
		var insuranceAmt = $("#insuranceAmt").val();
		var freightAmt = $("#freightAmt").val();
		var index = $("#index").val();
		var suppId = $("#suppId").val();
		var valid=0;
		if (suppId=="" || isNaN(suppId)) {

			 
			alert("Select Supplier ");
			valid=1;

		}
		if (itemId=="" || isNaN(itemId)) {

			 
			alert("Select Item ");
			valid=1;

		}
		if (recQty=="" || isNaN(recQty) || recQty<1) {

			 
			alert("Enter Recieved Qty");
			valid=1;

		}
		 if (rate=="" || isNaN(rate) || rate<1) {

			 
			alert("Enter Rate ");
			valid=1;

		}
		if (discPer=="" || isNaN(discPer)) {

			 
			alert("Enter Discount %");
			valid=1;

		}
		 
		if (discPerOnBill=="" || isNaN(discPerOnBill)) {

			 
			alert("Enter Disccout On Bill");
			valid=1;

		}
		
		if (extraCharges=="" || isNaN(extraCharges)) {

			 
			alert("Enter Extra Charges ");
			valid=1;

		}
		if (insuranceAmt=="" || isNaN(insuranceAmt)) {

			 
			alert("Insurance Amount");
			valid=1;

		}
		 
		if (freightAmt=="" || isNaN(freightAmt)) {

			 
			alert("Enter Freight Amount ");
			valid=1;

		}
		  
		if(valid==0)
			{
			
				$.getJSON(
						'${addItemInPurchaseBill}',

						{
							index :index,
							itemId : itemId,
							recQty : recQty,
							rate : rate,
							discPer : discPer,
							itemName : itemName, 
							discPerOnBill : discPerOnBill,
							extraCharges : extraCharges,
							insuranceAmt : insuranceAmt,
							freightAmt : freightAmt,
							ajax : 'true',

						},
						function(data) {
							
							 
							$('#table_grid td').remove();
							if (data == "") {
								alert("No records found !!");

							}
							var totalValue=0;
							var totalDiscAmt=0;
							var totalDiscOnBill=0; 
							var totalCgst=0;
							var totalSgst=0;
							var totalIgst=0;
							var totalCess=0;
							var totalBill=0;
							$.each(
									data,
									function(key, itemList) {
										
										 
										var tr = $('<tr></tr>');
											tr.append($('<td></td>').html(key+1));
											tr.append($('<td></td>').html(itemList.batchNo));
										  	tr.append($('<td></td>').html(itemList.itemName));
										  	tr.append($('<td></td>').html(itemList.recQty));
										  	tr.append($('<td></td>').html(itemList.rate));  
										  	tr.append($('<td style="text-align:right;"></td>').html(itemList.value)); 
										  	tr.append($('<td style="text-align:right;"></td>').html(itemList.discPer)); 
										  	 tr.append($('<td style="text-align:right;"></td>').html((itemList.discAmt).toFixed(2)));
										  	tr.append($('<td style="text-align:right;"></td>').html((itemList.discOnBill).toFixed(2)));
										  	tr.append($('<td style="text-align:right;"></td>').html((itemList.insuAmt).toFixed(2)));
										  	tr.append($('<td style="text-align:right;"></td>').html((itemList.freightAmt).toFixed(2)));
										  	
										  	tr.append($('<td style="text-align:right;"></td>').html((itemList.taxableAmt).toFixed(2))); 
										  	tr.append($('<td style="text-align:right;"></td>').html((itemList.igstPer).toFixed(2))); 
										  	tr.append($('<td style="text-align:right;"></td>').html((itemList.igstRs+itemList.sgstRs+itemList.cgstRs).toFixed(2)));  
										  	tr.append($('<td style="text-align:right;"></td>').html((itemList.cessPer).toFixed(2)));
										  	tr.append($('<td style="text-align:right;"></td>').html((itemList.cessRs).toFixed(2))); 
										  	tr.append($('<td style="text-align:right;"></td>').html((itemList.otherExtra).toFixed(2)));
										  	tr.append($('<td style="text-align:right;"></td>').html((itemList.total).toFixed(2))); 
										  	tr.append($('<td style="text-align:right;"></td>').html('<input style="text-align:right; width:100px" type="text" onchange="changeRate('+key+')" id="wholesaleRate'+key+'" value="'+(itemList.wholesaleRate).toFixed(2)+'"  class="form-control" '+
										  	'  name="wholesaleRate'+key+'" required><input type="hidden"  id="wholesaleRateOrigingal'+key+'"  value="'+(itemList.wholesaleRate).toFixed(2)+'"  >'));
										  	
										  	tr.append($('<td style="text-align:right;"></td>').html('<input style="text-align:right; width:100px" type="text" onchange="changeRate('+key+')" id="retailRate'+key+'" value="'+(itemList.retailRate).toFixed(2)+'"  class="form-control" '+
												  	' name="retailRate'+key+'" required><input type="hidden"  id="retailRateOrigingal'+key+'"  value="'+(itemList.retailRate).toFixed(2)+'"  >'));
										  	
										  	 
										  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" onclick="edit('+key+');"></span> <span class="glyphicon glyphicon-remove" onclick="deleteItem('+key+');""></span>')); 
											$('#table_grid tbody').append(tr);
											
											totalValue=totalValue+itemList.value;
											totalDiscAmt=totalDiscAmt+itemList.discAmt;
											totalDiscOnBill=totalDiscOnBill+itemList.discOnBill;
											totalCgst=totalCgst+itemList.cgstRs;
											totalSgst=totalSgst+itemList.sgstRs;
											totalIgst=totalIgst+itemList.igstRs;
											totalCess=totalCess+itemList.cessRs;
											totalBill=totalBill+itemList.total;

									})
									
							document.getElementById("basicValue").value=(totalValue).toFixed(2);
							document.getElementById("discAmt2").value=(totalDiscAmt).toFixed(2);
							document.getElementById("discAmt").value=(totalDiscOnBill).toFixed(2);
							document.getElementById("cgst").value=(totalCgst).toFixed(2);
							document.getElementById("sgst").value=(totalSgst).toFixed(2);
							document.getElementById("igst").value=(totalIgst).toFixed(2);
							document.getElementById("cess").value=(totalCess).toFixed(2);
							document.getElementById("billAmount").value=(totalBill).toFixed(2);
							           
							document.getElementById("itemId").value="";
							document.getElementById("recQty").value="1";
							document.getElementById("rate").value="1";
							document.getElementById("discPer").value="0";
							document.getElementById("index").value="";
							
						});
				
			 
		
		
			}
	}
	
	function deleteItem(key)
	{
	
		var discPerOnBill = $("#discPerOnBill").val();
		var extraCharges = $("#extraCharges").val();
		var insuranceAmt = $("#insuranceAmt").val();
		var freightAmt = $("#freightAmt").val();
		 
		$
		.getJSON(
				'${deleteItemPurchaseList}',

				{
					discPerOnBill : discPerOnBill,
					extraCharges : extraCharges,
					insuranceAmt : insuranceAmt,
					freightAmt : freightAmt,
					index : key, 
					ajax : 'true',

				},
				function(data) {
					 
					$('#table_grid td').remove();
					
					var totalValue=0;
					var totalDiscAmt=0;
					var totalDiscOnBill=0; 
					var totalCgst=0;
					var totalSgst=0;
					var totalIgst=0;
					var totalCess=0;
					var totalBill=0;
					
					$.each(data,
							function(key, itemList) {
								
								 
								var tr = $('<tr></tr>');
									tr.append($('<td></td>').html(key+1));
									tr.append($('<td></td>').html(itemList.batchNo));
								  	tr.append($('<td></td>').html(itemList.itemName));
								  	tr.append($('<td></td>').html(itemList.recQty));
								  	tr.append($('<td></td>').html(itemList.rate));  
								  	tr.append($('<td style="text-align:right;"></td>').html(itemList.value)); 
								  	tr.append($('<td style="text-align:right;"></td>').html(itemList.discPer)); 
								  	 tr.append($('<td style="text-align:right;"></td>').html((itemList.discAmt).toFixed(2)));
								  	tr.append($('<td style="text-align:right;"></td>').html((itemList.discOnBill).toFixed(2)));
								  	tr.append($('<td style="text-align:right;"></td>').html((itemList.insuAmt).toFixed(2)));
								  	tr.append($('<td style="text-align:right;"></td>').html((itemList.freightAmt).toFixed(2)));
								  	
								  	tr.append($('<td style="text-align:right;"></td>').html((itemList.taxableAmt).toFixed(2))); 
								  	tr.append($('<td style="text-align:right;"></td>').html((itemList.igstPer).toFixed(2))); 
								  	tr.append($('<td style="text-align:right;"></td>').html((itemList.igstRs+itemList.sgstRs+itemList.cgstRs).toFixed(2)));  
								  	tr.append($('<td style="text-align:right;"></td>').html((itemList.cessPer).toFixed(2)));
								  	tr.append($('<td style="text-align:right;"></td>').html((itemList.cessRs).toFixed(2))); 
								  	tr.append($('<td style="text-align:right;"></td>').html((itemList.otherExtra).toFixed(2)));
								  	tr.append($('<td style="text-align:right;"></td>').html((itemList.total).toFixed(2))); 
								  	
								  	tr.append($('<td style="text-align:right;"></td>').html('<input style="text-align:right; width:100px" type="text" onchange="changeRate('+key+')" id="wholesaleRate'+key+'" value="'+(itemList.wholesaleRate).toFixed(2)+'"  class="form-control" '+
										  	'  name="wholesaleRate'+key+'" required><input type="hidden"  id="wholesaleRateOrigingal'+key+'"  value="'+(itemList.wholesaleRate).toFixed(2)+'"  >'));
										  	
									tr.append($('<td style="text-align:right;"></td>').html('<input style="text-align:right; width:100px" type="text" onchange="changeRate('+key+')" id="retailRate'+key+'" value="'+(itemList.retailRate).toFixed(2)+'"  class="form-control" '+
												  	' name="retailRate'+key+'" required><input type="hidden"  id="retailRateOrigingal'+key+'"  value="'+(itemList.retailRate).toFixed(2)+'"  >'));
									 
								  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" onclick="edit('+key+');"></span>'
										  	+'<span class="glyphicon glyphicon-remove" onclick="deleteItem('+key+');"></span>')); 
									$('#table_grid tbody').append(tr);
								
									totalValue=totalValue+itemList.value;
									totalDiscAmt=totalDiscAmt+itemList.discAmt;
									totalDiscOnBill=totalDiscOnBill+itemList.discOnBill;
									totalCgst=totalCgst+itemList.cgstRs;
									totalSgst=totalSgst+itemList.sgstRs;
									totalIgst=totalIgst+itemList.igstRs;
									totalCess=totalCess+itemList.cessRs;
									totalBill=totalBill+itemList.total; 

							})
					document.getElementById("basicValue").value=(totalValue).toFixed(2);
					document.getElementById("discAmt2").value=(totalDiscAmt).toFixed(2);
					document.getElementById("discAmt").value=(totalDiscOnBill).toFixed(2);
					document.getElementById("cgst").value=(totalCgst).toFixed(2);
					document.getElementById("sgst").value=(totalSgst).toFixed(2);
					document.getElementById("igst").value=(totalIgst).toFixed(2);
					document.getElementById("cess").value=(totalCess).toFixed(2);
					document.getElementById("billAmount").value=(totalBill).toFixed(2);
							 
					
				});
		
		
	}
	
	 function edit(key)
	{
	
		 document.getElementById("index").value=key;
		$
		.getJSON(
				'${editPurchaseItemList}',

				{
					 
					index : key, 
					ajax : 'true',

				},
				function(data) {
					 
					document.getElementById("itemId").value=data.itemId;
					document.getElementById("recQty").value=data.recQty;
					document.getElementById("rate").value=data.rate;
					document.getElementById("discPer").value=data.discPer;  
							 
					
				});
		
		
	}
	
	 function updatePurchaseHeader()
		{
		
			 var discPerOnBill = $("#discPerOnBill").val();
			var extraCharges = $("#extraCharges").val();
			var insuranceAmt = $("#insuranceAmt").val();
			var freightAmt = $("#freightAmt").val();
			var isValid=true;
			
			if (discPerOnBill=="" || isNaN(discPerOnBill)) {

				isValid = false;
				alert("Please Enter Valid Disc Percentage ");
				document.getElementById("discPerOnBill").value="0";
				discPerOnBill=0;

			}
			
			if (extraCharges=="" || isNaN(extraCharges)) {

				isValid = false;
				alert("Please Enter Valid Extra Amt");
				document.getElementById("extraCharges").value="0";
				extraCharges=0;

			}
			if (insuranceAmt=="" || isNaN(insuranceAmt)) {

				isValid = false;
				alert("Please Enter Valid Insurance Amt");
				document.getElementById("insuranceAmt").value="0";
				insuranceAmt=0;

			}
			if (freightAmt=="" || isNaN(freightAmt)) {

				isValid = false;
				alert("Please Enter Valid Freight Amt");
				document.getElementById("freightAmt").value="0";
				freightAmt=0;

			}
			$
			.getJSON(
					'${updatePurchaseHeader}',

					{
						 
						discPerOnBill : discPerOnBill,
						extraCharges : extraCharges,
						insuranceAmt : insuranceAmt,
						freightAmt : freightAmt,
						ajax : 'true',

					},
					function(data) {
						
						$('#table_grid td').remove();
						
						var totalValue=0;
						var totalDiscAmt=0;
						var totalDiscOnBill=0; 
						var totalCgst=0;
						var totalSgst=0;
						var totalIgst=0;
						var totalCess=0;
						var totalBill=0;
						
						$.each(
								data,
								function(key, itemList) {
									
									 
									var tr = $('<tr></tr>');
										tr.append($('<td></td>').html(key+1));
										tr.append($('<td></td>').html(itemList.batchNo));
									  	tr.append($('<td></td>').html(itemList.itemName));
									  	tr.append($('<td></td>').html(itemList.recQty));
									  	tr.append($('<td></td>').html(itemList.rate));  
									  	tr.append($('<td style="text-align:right;"></td>').html(itemList.value)); 
									  	tr.append($('<td style="text-align:right;"></td>').html(itemList.discPer)); 
									  	 tr.append($('<td style="text-align:right;"></td>').html((itemList.discAmt).toFixed(2)));
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.discOnBill).toFixed(2)));
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.insuAmt).toFixed(2)));
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.freightAmt).toFixed(2)));
									  	
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.taxableAmt).toFixed(2))); 
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.igstPer).toFixed(2))); 
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.igstRs+itemList.sgstRs+itemList.cgstRs).toFixed(2)));   
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.cessPer).toFixed(2)));
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.cessRs).toFixed(2))); 
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.otherExtra).toFixed(2)));
									  	tr.append($('<td style="text-align:right;"></td>').html((itemList.total).toFixed(2))); 
									  	
									  	tr.append($('<td style="text-align:right;"></td>').html('<input style="text-align:right; width:100px" type="text" onchange="changeRate('+key+')" id="wholesaleRate'+key+'" value="'+(itemList.wholesaleRate).toFixed(2)+'"  class="form-control" '+
											  	'  name="wholesaleRate'+key+'" required><input type="hidden"  id="wholesaleRateOrigingal'+key+'"  value="'+(itemList.wholesaleRate).toFixed(2)+'"  >'));
											  	
										tr.append($('<td style="text-align:right;"></td>').html('<input style="text-align:right; width:100px" type="text" onchange="changeRate('+key+')" id="retailRate'+key+'" value="'+(itemList.retailRate).toFixed(2)+'"  class="form-control" '+
													  	' name="retailRate'+key+'" required><input type="hidden"  id="retailRateOrigingal'+key+'"  value="'+(itemList.retailRate).toFixed(2)+'"  >'));
										
									  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" onclick="edit('+key+');"></span>'
											  	+'<span class="glyphicon glyphicon-remove" onclick="deleteItem('+key+');""></span>')); 
										$('#table_grid tbody').append(tr);
									
										totalValue=totalValue+itemList.value;
										totalDiscAmt=totalDiscAmt+itemList.discAmt;
										totalDiscOnBill=totalDiscOnBill+itemList.discOnBill;
										totalCgst=totalCgst+itemList.cgstRs;
										totalSgst=totalSgst+itemList.sgstRs;
										totalIgst=totalIgst+itemList.igstRs;
										totalCess=totalCess+itemList.cessRs;
										totalBill=totalBill+itemList.total;  
								})
								
						document.getElementById("basicValue").value=(totalValue).toFixed(2);
						document.getElementById("discAmt2").value=(totalDiscAmt).toFixed(2);
						document.getElementById("discAmt").value=(totalDiscOnBill).toFixed(2);
						document.getElementById("cgst").value=(totalCgst).toFixed(2);
						document.getElementById("sgst").value=(totalSgst).toFixed(2);
						document.getElementById("igst").value=(totalIgst).toFixed(2);
						document.getElementById("cess").value=(totalCess).toFixed(2);
						document.getElementById("billAmount").value=(totalBill).toFixed(2);
						
					});
			
			
		}
	 
	 function changeRate(key)
		{
		    
			var wholesaleRateOriginal=document.getElementById("wholesaleRateOrigingal"+key+"").value;
		  	var wholesaleRate=document.getElementById("wholesaleRate"+key+"").value;
		   	var retailRate=document.getElementById("retailRate"+key+"").value;
		   	var retailRateOrigingal=document.getElementById("retailRateOrigingal"+key+"").value;
		   	
		   	if(wholesaleRate=="")
		   		{
		   		document.getElementById("wholesaleRate"+key+"").value=wholesaleRateOriginal;
		   		}
			if(retailRate=="")
		   		{
		   		document.getElementById("retailRate"+key+"").value=retailRateOrigingal;
		   		}
			 
		}
	  
	</script>
	

</body>
</html>