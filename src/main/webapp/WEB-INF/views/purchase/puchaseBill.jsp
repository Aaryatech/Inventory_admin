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

							<div class="col-md-2">Select Supplier</div>
							<div class="col-md-3">
							<select name="suppId" id="suppId" class="form-control" onchange="isSameState();" tabindex="-1" required>
							<option value="">Select Supplier</option>
							<c:forEach items="${supplierList}" var="supplierList">
							 <option value="${supplierList.suppId}">${supplierList.suppName}</option>
							</c:forEach>
							
								</select>
							</div>
							
							<div class="col-md-1"></div>
							 <div class="col-md-2" >Vehicle No.</div>
									<div class="col-md-3">
										 <input type="text" name="vehicleNo" id="vehicleNo" class="form-control" required>
									</div>
							
						</div>
						<br>
						
						<div class="box-content">
						<div class="col-md-2" >Invoice no.</div>
									<div class="col-md-3">
										 <input type="text" name="invoiceNo" id="invoiceNo" class="form-control" required>
									</div>
							<div class="col-md-1"></div>
							<div class="col-md-2" >Invoice no. Date</div>
									<div class="col-md-3">
										 <input type="text" name="invoiceDate"   onblur="cdDate()"   id="invoiceDate" class="form-control date-picker" required>
									</div>
									
						</div>
							<br>
							
							<div class="box-content">
							<div class="col-md-2" >Advance</div>
									<div class="col-md-3">
									 
										 <select name="isPaid" id="isPaid" onchange="disabledDate()" class="form-control" tabindex="-1" >
											<option value="1">No</option>
											 <option value="2">Yes</option>
										</select>
									</div>
							 
						</div>
							<br>
							
							<div class="box-content">
						<div class="col-md-2" >Days For Date1</div>
									<div class="col-md-3">
										 <input type="text"  name="days1" onchange="cdDate()" id="days1" class="form-control" required>
									</div>
							<div class="col-md-1"></div>
							<div class="col-md-2" >CD Date1</div>
									<div class="col-md-3">
										 <input type="text" name="cdDate1" id="cdDate1" class="form-control" readonly>
									</div>
									
						</div>
							<br>
							
							<div class="box-content">
						<div class="col-md-2" >Days For Date2</div>
									<div class="col-md-3">
										 <input type="text" name="days2" onchange="cdDate()" id="days2" class="form-control" required>
									</div>
							<div class="col-md-1"></div>
							<div class="col-md-2" >CD Date2</div>
									<div class="col-md-3">
										 <input type="text" name="cdDate2" id="cdDate2" class="form-control " readonly>
									</div>
									
						</div>
							<br>
							
							<div class="box-content">
						<div class="col-md-2" >Days For Date3</div>
									<div class="col-md-3">
										 <input type="text" name="days3" onchange="cdDate()" id="days3" class="form-control" required>
									</div>
							<div class="col-md-1"></div>
							<div class="col-md-2" >CD Date3</div>
									<div class="col-md-3">
										 <input type="text" name="cdDate3" id="cdDate3" class="form-control" readonly>
									</div>
									
						</div>
							<br>
							
							<div class="box-content">
						<div class="col-md-2" >Days For Date4</div>
									<div class="col-md-3">
										 <input type="text" name="days4" onchange="cdDate()" id="days4" class="form-control" required>
									</div>
							<div class="col-md-1"></div>
							<div class="col-md-2" >CD Date4</div>
									<div class="col-md-3">
										 <input type="text" name="cdDate4" id="cdDate4" class="form-control" readonly>
									</div>
									
						</div>
							<br>
							<br>
							
							<hr>
							
							<div class="box-content">
										<div class="col-md-2">Select Item</div>
									<div class="col-md-3">
									<input type="hidden" class="form-control" id="itemName" name="itemName" >
									<input type="hidden" class="form-control" id="index" name="index" >
										<select name="itemId" id="itemId" class="form-control" onchange="getItemName();" tabindex="-1" >
											<option value="">Select Item</option>
											<c:forEach items="${itemList}" var="itemList">
											 	<option value="${itemList.itemId}">${itemList.itemName}</option>
											</c:forEach>
										
										</select>
									</div>
									<div class="col-md-1"></div>
										<div class="col-md-2">Qty </div>
											<div class="col-md-2">
												<input type="text" class="form-control" id="recQty" name="recQty" value="1">
											</div>
									
									 
							
									
						</div><br>
						
						<div class="box-content">
										 
										<div class="col-md-2">Rate </div>
											<div class="col-md-2">
												<input type="text" class="form-control" id="rate" name="rate" value="1">
											</div>
											<div class="col-md-2"></div>
											<div class="col-md-2">Discount% </div>
											<div class="col-md-2">
												<input type="text" class="form-control" id="discPer" name="discPer" value="0">
											</div>
									 
						</div><br>
						
					 <br>
						
						<div class="box-content">
							<div class="col-md-12" style="text-align: center">
								<input type="button" class="btn btn-info" id="addItem" onclick="addItemInList()" value="Add Item">
							</div>
						</div><br><br>
 
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
												<th>Value</th>
												<th >Disc%</th>
												<th>Disc Amt <i class="fa fa-inr" style="font-size:13px"></i></th> 
												<th>Cd Amt <i class="fa fa-inr" style="font-size:13px"></i></th> 
												<th>Insurance Amt <i class="fa fa-inr" style="font-size:13px"></i></th>
												<th>Freight Amt <i class="fa fa-inr" style="font-size:13px"></i></th>  
												<th>Taxable<i class="fa fa-inr" style="font-size:13px"></i></th>
												<th>Tax%</th>  
												<th>Tax AMT<i class="fa fa-inr" style="font-size:13px"></i></th>  
												<th>CESS%</th>
												<th>CESS<i class="fa fa-inr" style="font-size:13px"></i></th>
												<th >Other3(Extra Charges) <i class="fa fa-inr" style="font-size:13px"></i></th> 
												<th>Total<i class="fa fa-inr" style="font-size:13px"></i></th>
												<th>Wholesale Rate<i class="fa fa-inr" style="font-size:13px"></i></th>
												<th>Retail Rate<i class="fa fa-inr" style="font-size:13px"></i></th>
												 
												<th>Action</th>

											</tr>
										</thead>
										<tbody>


											 

										</tbody>
									</table>
								</div>
							</div>

						</div>
						
							<div class="box-content"> 
									<div class="col-md-2">value <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2">
											<input style="text-align:right; width:150px" type="text" name="basicValue" id="basicValue" class="form-control"
										 readonly>
										</div>
									<div class="col-md-2">Freight Amt <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2">
											<input style="text-align:right; width:150px" type="text" onchange="updatePurchaseHeader();" name="freightAmt" id="freightAmt" class="form-control"
										value="0" pattern="[+-]?([0-9]*[.])?[0-9]+" required>
										</div>
									<div class="col-md-2">CGST <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2">
											<input style="text-align:right; width:150px" type="text" name="cgst" id="cgst" class="form-control"
										 readonly>
										</div>
							
							
							</div><br>
							<div class="box-content">
									<div class="col-md-2">Particular Disc Total <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2">
											<input style="text-align:right; width:150px" type="text" name="discAmt2" id="discAmt2" class="form-control"
										 readonly>
										</div>
									<div class="col-md-2">Insu Amt <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2">
											<input style="text-align:right; width:150px" type="text" onchange="updatePurchaseHeader();" name="insuranceAmt" id="insuranceAmt" class="form-control"
										value="0" pattern="[+-]?([0-9]*[.])?[0-9]+" required>
										</div>
									<div class="col-md-2">SGST <i class="fa fa-inr" style="font-size:13px"></i></div>
									<div class="col-md-2">
										<input style="text-align:right; width:150px" type="text" name="sgst" id="sgst" class="form-control"
									 readonly>
									</div>
							
							</div><br>
						
						<div class="box-content"> 
							 
						 	<div class="col-md-2">Per Disc %</div>
							  <div class="col-md-1">
								<input style="text-align:right; width:50px" type="text" onchange="updatePurchaseHeader();" name="discPerOnBill" id="discPerOnBill"
									value="0" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required>
									
									 </div>  
											<div class="col-md-1"> 
									<input style="text-align:right; width:60px" type="text" name="discAmt" id="discAmt"
									 class="form-control"
									readonly>
									 
							</div>
							 <div class="col-md-2">Other3(Extra) <i class="fa fa-inr" style="font-size:13px"></i></div>
									<div class="col-md-2">
										<input style="text-align:right; width:150px" type="text" name="extraCharges" id="extraCharges" onchange="updatePurchaseHeader();" class="form-control"
									value="0" pattern="[+-]?([0-9]*[.])?[0-9]+" required>
									</div>
									
									<div class="col-md-2">IGST <i class="fa fa-inr" style="font-size:13px"></i></div>
									<div class="col-md-2">
										<input style="text-align:right; width:150px" type="text" name="igst" id="igst" class="form-control"
									 readonly>
									</div>
							 </div><br>
						  <div class="box-content">
								 
									<div class="col-md-2"></div>
										<div class="col-md-2">
											
										</div>
										
										<div class="col-md-2"></div>
										<div class="col-md-2">
											
										</div>
									<div class="col-md-2">Cess <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2">
											<input style="text-align:right; width:150px" type="text" name="cess" id="cess" class="form-control"
										readonly>
										</div>
									
							
							</div><br>
							  
							<div class="box-content">
								 	<div class="col-md-2"> </div>
										<div class="col-md-2"> </div>
									<div class="col-md-2"> </div>
										<div class="col-md-2"> </div>
									<div class="col-md-2">Bill Total <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2">
											<input style="text-align:right; width:150px" type="text" name="billAmount" id="billAmount" class="form-control"
										 readonly>
										</div>
									
									
							
							</div><br>
							
							<div class="box-content">
								 
									<div class="col-md-2"> </div>
										<div class="col-md-2"> </div>
									<div class="col-md-2"> </div>
										<div class="col-md-2"> </div>
									
									<div class="col-md-2">Round Off <i class="fa fa-inr" style="font-size:13px"></i></div>
										<div class="col-md-2">
											<input style="text-align:right; width:150px" type="text" name="roundOff" id="roundOff" class="form-control"
										 readonly>
										</div>
									
							
							</div><br>
							  
							 
						<br />
						<br />

						<div class="row">
							<div class="col-md-12" style="text-align: center">
								 <input type="submit" class="btn btn-info" value="Submit" >
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
		var expireDate = $("#expireDate").val();
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
							document.getElementById("expireDate").value="";
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
					document.getElementById("expireDate").value=data.expiryDate;
							 
					
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
	 
	 

	 function disabledDate()
		{
		    
		 var isPaid = $("#isPaid").val();
		 
		 if(isPaid==2)
			 {
			 document.getElementById("days1").disabled=true;
			 document.getElementById("days2").disabled=true;
			 document.getElementById("days3").disabled=true;
			 document.getElementById("days4").disabled=true;
			 }
		 else
			 {
			 document.getElementById("days1").disabled=false;
			 document.getElementById("days2").disabled=false;
			 document.getElementById("days3").disabled=false;
			 document.getElementById("days4").disabled=false;
			 }
		}
	 function cdDate() {
			/* var d = new Date(); */
			 
			if($("#invoiceDate").val()!="")
				{
					var selectedDate = $("#invoiceDate").val().split("-");
					
					var d = new Date(selectedDate[2],selectedDate[1] - 1, selectedDate[0]);
					var days1 = $("#days1").val();
					var days2 = $("#days2").val();
					var days3 = $("#days3").val();
					var days4 = $("#days4").val();
					var todayTimeStamp1 = +d; // Unix timestamp in milliseconds
					d.setDate(d.getDate());
					var todayTimeStamp = +d; // Unix timestamp in milliseconds
					var oneDayTimeStamp = 1000 * 60 * 60 * 24; // Milliseconds in a day
					
					if(days1!="")
					{
						var diff = todayTimeStamp + (oneDayTimeStamp*days1);
						var yesterdayDate = new Date(diff);  
		
						var tommarowString =  yesterdayDate.getDate()+ '-' + (yesterdayDate.getMonth() + 1) + '-' +yesterdayDate.getFullYear();
						document.getElementById("cdDate1").value=tommarowString;
					}
					if(days2!="")
					{
						var diff = todayTimeStamp + (oneDayTimeStamp*days2);
						var yesterdayDate = new Date(diff);  
		
						var tommarowString =  yesterdayDate.getDate()+ '-' + (yesterdayDate.getMonth() + 1) + '-' +yesterdayDate.getFullYear();
						document.getElementById("cdDate2").value=tommarowString;
					}
					if(days3!="")
					{
						var diff = todayTimeStamp + (oneDayTimeStamp*days3);
						var yesterdayDate = new Date(diff);  
		
						var tommarowString =  yesterdayDate.getDate()+ '-' + (yesterdayDate.getMonth() + 1) + '-' +yesterdayDate.getFullYear();
						document.getElementById("cdDate3").value=tommarowString;
					}
					if(days4!="")
					{
						var diff = todayTimeStamp + (oneDayTimeStamp*days4);
						var yesterdayDate = new Date(diff);  
		
						var tommarowString =  yesterdayDate.getDate()+ '-' + (yesterdayDate.getMonth() + 1) + '-' +yesterdayDate.getFullYear();
						document.getElementById("cdDate4").value=tommarowString;
					}
					
				
				}
			 
		}
	</script>
	<!-- onchange="cdDate();" -->

</body>
</html>