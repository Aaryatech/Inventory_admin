<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

	<body>
	<c:url var="invoiceCalc" value="/invoiceCalc" />
	<c:url var="getBatchList" value="/findBatchList" />
	<c:url var="insertItem" value="/addNewItem" />
		<c:url var="deleteItemDetail" value="/deleteItemDetails" />
			<c:url var="generateBill" value="/saveDirectBill" />
			<c:url var="calculateDiscount" value="/calculateDiscount"/>

	<c:url var="getCustomer" value="/findCustomer" />	
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
						<i class="fa fa-file-o"></i>Invoice
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
								<i class="fa fa-bars"></i>Invoice Detail
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/bills">Invoice List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>




						<div class="box-content" id="div1">
							<form action="${pageContext.request.contextPath}/searchOrders" class="form-horizontal"
								method="post" id="validation-form">
							
                             <div  class="box-content" style="text-align: left;padding-left:100px;  border: 1px solid skyblue; border-radius: 12px;">
                         	<input type="hidden" name="isSameState" id="isSameState"/>
                         		<input type="hidden" name="creditDays" id="creditDays"/>
	                         <div class="form-group">
									<div class="col-md-2">Invoice No:</div>
									<div class="col-md-3">	<input class="form-control" id="invoice_no" size="14"
													type="text" name="invoice_no"
													placeholder="Invoice No." value="${invoiceNo}"required />
									</div>
							      <div class="col-md-3">Invoice Date:</div>
								 
									<div class="col-md-3">
									
								<input class="form-control date-picker" id="invoice_date" size="14"
													type="text" name="invoice_date"
													placeholder="Invoice Date" value="${currentDate}"required />
									</div>
							 
								</div>
								<hr>
							<div class="form-group">
								<div class="col-md-2">Customer:</div>
									<div class="col-md-3">
									<select name="cust_id" id="cust_id" class="form-control" placeholder="Select Customer"  data-rule-required="true"  onchange="return onCustomerChanged(this.value)">
											<option value="0">Select Customer</option>
									 <c:forEach items="${customerList}" var="customerList">
										   <option value="${customerList.custId}"><c:out value="${customerList.custName}"></c:out></option>
										</c:forEach>
												  <option value="-1">Other</option>
								</select>			
											
									</div>
									<div class="col-md-3">GSTIN:</div>
									<div class="col-md-3">
								
										<input type="text" name="gstin_no" id="gstin_no"
											placeholder="GSTIN No" class="form-control"
											data-rule-required="true" />
							</div>
								
							</div>
							  	<hr>
							  <div class="form-group">
									<div class="col-md-2">Customer Type:</div>
									<div class="col-md-3">
								<select name="cust_type" id="cust_type" class="form-control" placeholder="Select Customer Type"  data-rule-required="true"  onchange="return onItemChanged(this.value)">
											<option value="0">Select Type</option>
											<option value="1">Wholesaler</option>
									        <option value="2">Retailer</option>
									
								</select>	
								</div>
									<div class="col-md-3">Mobile:</div>
									<div class="col-md-3">
								
										<input type="text" name="mobile" id="mobile"
											placeholder="Mobile No" class="form-control"
											data-rule-required="true" />
									</div>
								
									</div>
									
									
							</div><br></form>
								
							<div class="col-md-1"><strong>Item:</strong></div>
							<div class="col-md-2">
								<select name="item_id" id="item_id" class="form-control" placeholder="Select Item"  data-rule-required="true"  onchange="return onItemChanged(this.value)">
											<option value="0">Select Item</option>
									 <c:forEach items="${itemList}" var="itemList">
										   <option value="${itemList.itemId}"><c:out value="${itemList.itemName}"></c:out></option>
										</c:forEach>
												
								</select>	
							
							</div>
							<div class="col-md-1"><strong>Batch:</strong></div>
							<div class="col-md-2">
								<select name="batch_no" id="batch_no" class="form-control" placeholder="Select Batch"  data-rule-required="true"  onchange="">
											<option value="0">Select Batch</option>
									<%--  <c:forEach items="${batchList}" var="batchList">
									 <c:choose>
									 <c:when test="${batchList.itemId==orderDetail.itemId}">
										   <option value="${batchList.batchNo}"><c:out value="${batchList.batchNo}"></c:out></option>
									</c:when>	</c:choose>
										</c:forEach> --%>
												
								</select>	
							
							</div>
							<div class="col-sm-1"><strong>Qty:</strong></div>
							<div class="col-md-2">
							<input type="text" name="qty" id="qty"
											placeholder="Qty" class="form-control"
											data-rule-required="true" />
								</div>
								<div class="col-sm-1">	<button class="btn additem_btn" 
										onclick="insertItem()">Add Item</button></div>
									
							<br>
							
						<%-- 	<form action="${pageContext.request.contextPath}/saveBill" class="form-horizontal"
								method="post" id="validation-form"> --%>
					         		<br>
								
									<div class="box"><!-- style="padding-left:7px;padding-right:7px;  border: 1px solid skyblue; border-radius: 12px;" -->
									

									<div class="box-content">

										<div class="clearfix"></div>
						<!-- 	<div id="table-scroll" class="table-scroll">
								 		
								<div id="faux-table" class="faux-table" aria="hidden">
								
									
									</div> -->
									<div class="table-wrap">
									
										<table id="table2" class="table table-bordered table-striped fill-head" style="width: 100%">
											<thead>
												<tr class="bgpink">
													<th width="45" style="width: 18px">Sr.No.</th>
														<th width="150" align="left">Item Name</th>
														<th width="150" align="left">HSN Code</th>
														<th width="150" align="right">Order Qty</th>
														<th width="600" align="left">Batch</th>
														<th width="150" align="left">Bill Qty</th>
														<th width="150" align="left">Rate</th>
														<th width="150" align="left">Base Rate</th>
														<th width="100" align="left">Taxable Amt</th>
														<th width="100" align="left">TAX %</th>
														<th width="100" align="left">CESS %</th>
														<th width="100" align="left">CESS Amt</th>
														<th width="100" align="left">Tax Amt</th>
														<th width="100" align="left">Total</th>
														<th width="100" align="left">Action</th>
														
												</tr>
												</thead> 
                          
											 <tbody>
									
													
												</tbody>
											</table>
										</div>
								<!-- 	</div> -->
								</div>
							
						</div>
						<div class="row">
									<div class="col-md-4">
										<h5 class="col-md-7">
											<b>Total:-</b>
										</h5>
										<h5 class="col-md-5" id="total">00</h5>
										<input type="hidden" class="form-control" id="tot">
									</div>

									<div class="col-md-4">
										<h5 class="col-md-7" style="margin-top: 5px">
											<b>Discount(%):-</b>
										</h5>
										<div class="col-md-5">
											<input type="text" class="form-control" id="discount"
												onkeyup="calculateDiscount()" value="0">
										</div>
									</div>

									<div class="col-md-4">
										<h5 class="col-md-7" style="margin-top: 5px">
											<b>Grand Total:-</b>
										</h5>

										<h5 class="col-md-5" id="grandtotal">00</h5>
										<input type="hidden" class="form-control" id="grandtot">
									</div>

									<div class="clearfix"></div>

									<div class="col-md-4">

										<h5 class="col-md-7">
											<b>Remark:-</b>
										</h5>
										<div class="col-md-5">
											<textarea rows="2" cols="15" id="remark" name="remark" placeholder="Remark...">NA</textarea>

										</div>
									</div>

									<div class="col-md-4">
										<h5 class="col-md-7">
											<b>Paid Amount:-</b>
										</h5>
										<div class="col-md-5">
											<input type="text" class="form-control" id="paidAmount"
												onkeyup="paidAmt()" value="0">
										</div>

									</div>

									<div class="col-md-4">
										<h5 class="col-md-7" style="margin-top: 5px">
											<b>Remaining Amt:-</b>
										</h5>



										<h5 class="col-md-5" id="remAmt">00</h5>
										<input type="hidden" class="form-control" id="remAmount">
									</div>

									<div class="clearfix"></div>


									<hr />
								
								<center>
									<button class="btn additem_btn" id="generateBill"
										onclick="generateBill()">Generate Bill</button>
									<button class="btn additem_btn" id="pdfBtn" 
										onclick="pdfBtn()">Print Bill</button>
								
								</center>
<div align="center" id="loader" style="display: none">

					<span>
						<h4>
							<font color="#343690">Loading</font>
						</h4>
					</span> <span class="l-1"></span> <span class="l-2"></span> <span
						class="l-3"></span> <span class="l-4"></span> <span class="l-5"></span>
					<span class="l-6"></span>
				</div>
								</div>
							<!-- 	</form> -->
							
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
/* function onBatchChanged(batchNo,key1) {
	
	//alert("key"+key1)
    var $selected = $('#batch'+key1).find(':selected');   
	var batchNo=$('#batch'+key1).val();
	var isSameState=$("#isSameState").val();
	var rowCount=$("#rowCount").val();
	
	$.getJSON('${invoiceCalc}', {
		         batchNo : batchNo,
		         prevBatchNo:$selected.prev().val(),
		         key:key1,
					ajax : 'true' 
	  }, function(data) {
		  
			 var len = data.billDetail.length;
			$('#table2 td').remove();
			  var total=0;
			  var index=0;
			$.each(data.billDetail,function(key, item) {
		      index=key;
			var tr = $('<tr></tr>');
		  	tr.append($('<td></td>').html(key+1));
		  	tr.append($('<td></td>').html(item.itemName));
		  	tr.append($('<td></td>').html(item.itemHsncd));
	     	tr.append($('<td></td>').html(item.billQty));
		 	tr.append($('<td style=" font-weight: bold;"></td>').html(item.batchNo));
		 	tr.append($('<td></td>').html(item.billQty));
		 	tr.append($('<td></td>').html(item.rate));
		 	tr.append($('<td></td>').html(item.baseRate));
		 	tr.append($('<td></td>').html(item.taxableAmt));
		    if(isSameState==1)
			  {
		    	tr.append($('<td></td>').html(item.cgstPer+item.sgstPer));  
			  }
		  else
			  {
			 	tr.append($('<td></td>').html(item.igstPer));  
			  }
		    tr.append($('<td></td>').html(item.cessPer));
			tr.append($('<td></td>').html(item.cessRs));
			tr.append($('<td></td>').html(item.taxAmt));
			tr.append($('<td></td>').html(item.grandTotal));
			
			tr.append($('<td></td>').html("<a href='#' class='action_btn'onclick=deleteItemDetail("+item.batchNo+","+item.itemId+","+item.billQty+","+index+")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
			$('#table2 tbody').append(tr);
		   total=total+item.grandTotal;
		
		 }); 
			
			total=total.toFixed(2);
			document.getElementById('total').innerHTML=""+total;
			document.getElementById('tot').value=total;
			var discount = $("#discount").val();
			var grandAmt = total - (total * (discount / 100));
            document.getElementById("paidAmount").setAttribute('value',grandAmt);
			var paidAmount = $("#paidAmount").val();
			var grandMinusPaidAmt = grandAmt - paidAmount;alert("dd"+grandMinusPaidAmt)
		    document.getElementById('grandtotal').innerHTML=""+grandAmt;
			document.getElementById('grandtot').value=grandAmt;
		    document.getElementById('remAmount').value=""+grandMinusPaidAmt; 
			document.getElementById('remAmt').innerHTML=grandMinusPaidAmt;  
			 if(data.balOver==1)
				{
				  alert("Balance Is Over!!")
				} 
	
 	  if(data.newRow==1)
			  {
 		        $('#tr'+data.uniqueKey).children('td, th').css('background-color','red');
			 } 
 	  else
 		  {
 		 $('#tr'+data.uniqueKey).children('td, th').css('background-color','pink');
 		  } 
	});
	
} */

function onCustomerChanged(custId)
{
	 document.getElementById('gstin_no').value="";
	 document.getElementById('mobile').value="";
 	 document.getElementById('cust_type').value=0;
	if(custId!=-1)
		{
	$.getJSON('${getCustomer}', {
		custId : custId,
			ajax : 'true' 
      }, function(data) {
    	
    	  document.getElementById('creditDays').value=data.creditDays;
    	  document.getElementById('isSameState').value=data.isSameState;
    	  document.getElementById('gstin_no').value=data.gstin;
    	  document.getElementById('mobile').value=data.mobile;
    	  document.getElementById('cust_type').value=data.custType;
      });
	 }
	
	 else
		 {
		 document.getElementById('gstin_no').value=0;
	   	    document.getElementById('mobile').value=0;
	    	  document.getElementById('cust_type').value=0;
	    	  document.getElementById('isSameState').value=1;
	    	  document.getElementById('creditDays').value=0;
		 }
	
	
}
function onItemChanged(itemId)
{
	$.getJSON('${getBatchList}', {
		itemId : itemId,
			ajax : 'true' 
}, function(data) {
	  var html = '<option value="0">Select Batch</option>';
		var len = data.length;
		
		for ( var i = 0; i < len; i++) {
			html += '<option value="' + data[i].batchNo +'">'
					+ data[i].batchNo+ '</option>';
		}
		html += '</option>';
		$('#batch_no').html(html);
		$('#batch_no').form-control('refresh');
	
});

}
 function insertItem()
{
	 var valid=validation();
	 if(valid==true)
		 {
		var discount = $("#discount").val();
		var isSameState=$("#isSameState").val();
		var	custType=$("#cust_type").val();
		var	custName=$("#cust_type").text();
		
	    var itemId = $('#item_id').find(":selected").val();
	    var batchNo = $('#batch_no').find(":selected").val();
	    var qty = $('#qty').val();
	
	$.getJSON('${insertItem}', {
		discount:discount,
		itemId : itemId,
		batchNo:batchNo,
		custType:custType,
		custName:custName,
		isSameState:isSameState,
		qty:qty,
			ajax : 'true' 
      }, function(data) {
		   document.getElementById('item_id').value=0; 
		   document.getElementById('batch_no').value=0; 
		   document.getElementById('qty').value=0; 

    	var len = data.length;
        var total=0;
        var index=0;
		$('#table2 td').remove();
		$.each(data.billDetail,function(key, item) {
	     index=key;
		    var tr = $('<tr></tr>');
	  	    tr.append($('<td></td>').html(key+1));
	  	    tr.append($('<td></td>').html(item.itemName));
	  	    tr.append($('<td></td>').html(item.itemHsncd));
        	tr.append($('<td></td>').html(item.billQty));
	 	    tr.append($('<td style=" font-weight: bold;"></td>').html(item.batchNo));
	 	 	tr.append($('<td></td>').html(item.billQty));
	 	  	tr.append($('<td></td>').html(item.rate));
	 	 	tr.append($('<td></td>').html(item.baseRate));
	 	    tr.append($('<td></td>').html(item.taxableAmt));
	  if(isSameState==1)
		  {
	    	tr.append($('<td></td>').html(item.cgstPer+item.sgstPer));  

		  }
	  else
		  {
		 	tr.append($('<td></td>').html(item.igstPer));  

		  }
	    tr.append($('<td></td>').html(item.cessPer));
		tr.append($('<td></td>').html(item.cessRs));
		tr.append($('<td></td>').html(item.taxAmt));
		tr.append($('<td></td>').html(item.grandTotal));
		tr.append($('<td></td>').html("<a href='#' class='action_btn'onclick=deleteItemDetail("+item.batchNo+","+item.itemId+","+item.billQty+","+index+")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
		$('#table2 tbody').append(tr);
 		total=total+item.grandTotal;

		});
		  total=total.toFixed(2);
		  document.getElementById('total').innerHTML=""+total;
		  document.getElementById('tot').value=total;
		  var discount = $("#discount").val();
		  var grandAmt = total;// - (total * (discount / 100));
		  document.getElementById("paidAmount").setAttribute('value',grandAmt);
		  var paidAmount = $("#paidAmount").val();
		  var grandMinusPaidAmt = grandAmt - paidAmount;
		  document.getElementById('grandtotal').innerHTML=""+grandAmt;
		  document.getElementById('grandtot').value=grandAmt;
		  document.getElementById('remAmount').value=""+grandMinusPaidAmt; 
		  document.getElementById('remAmt').innerHTML=grandMinusPaidAmt;  
     
      });
		 }
} 
 
 
 function calculateDiscount() {

		var discount = $("#discount").val();
		var isSameState=$("#isSameState").val();

	
		$.getJSON('${calculateDiscount}', {
			
			discount:discount,
			isSameState:isSameState,
				ajax : 'true' 
	      }, function(data) {
			  

	    	var len = data.length;
	        var total=0;
	        var index=0;
			$('#table2 td').remove();
			$.each(data,function(key, item) {
		     index=key;
			    var tr = $('<tr></tr>');
		  	    tr.append($('<td></td>').html(key+1));
		  	    tr.append($('<td></td>').html(item.itemName));
		  	    tr.append($('<td></td>').html(item.itemHsncd));
	        	tr.append($('<td></td>').html(item.billQty));
		 	    tr.append($('<td style=" font-weight: bold;"></td>').html(item.batchNo));
		 	 	tr.append($('<td></td>').html(item.billQty));
		 	  	tr.append($('<td></td>').html(item.rate));
		 	 	tr.append($('<td></td>').html(item.baseRate));
		 	    tr.append($('<td></td>').html(item.taxableAmt));
		  if(isSameState==1)
			  {
		    	tr.append($('<td></td>').html(item.cgstPer+item.sgstPer));  

			  }
		  else
			  {
			 	tr.append($('<td></td>').html(item.igstPer));  

			  }
		    tr.append($('<td></td>').html(item.cessPer));
			tr.append($('<td></td>').html(item.cessRs));
			tr.append($('<td></td>').html(item.taxAmt));
			tr.append($('<td></td>').html(item.grandTotal));
			tr.append($('<td></td>').html("<a href='#' class='action_btn'onclick=deleteItemDetail("+item.batchNo+","+item.itemId+","+item.billQty+","+index+")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
			$('#table2 tbody').append(tr);
	 		total=total+item.grandTotal;

			});
			  total=total.toFixed(2);
			  document.getElementById('total').innerHTML=""+total;
			  document.getElementById('tot').value=total;
			  var discount = $("#discount").val();
			 
			  var grandAmt = total;// - (total * (discount / 100));
			
			  document.getElementById("paidAmount").setAttribute('value',grandAmt);
			  var paidAmount = $("#paidAmount").val();
			 
			  var grandMinusPaidAmt = grandAmt - paidAmount;
		
			  document.getElementById('grandtotal').innerHTML=""+grandAmt;
			  document.getElementById('grandtot').value=grandAmt;
			  document.getElementById('remAmount').value=""+grandMinusPaidAmt; 
			  document.getElementById('remAmt').innerHTML=grandMinusPaidAmt;  
	     
	      });
	}
	function paidAmt(token) {
		var paidAmount = $("#paidAmount").val();
		var grandAmount = $("#grandtot").val();
	
		var remainingAmount = (grandAmount - paidAmount);
		remainingAmount = remainingAmount.toFixed(2);
		$('#remAmt').html(remainingAmount);
		document.getElementById("remAmount").setAttribute('value',
				remainingAmount);

	}
</script>
<script type="text/javascript">
function deleteItemDetail(batchNo,itemId,billQty,index){
	var isSameState=$("#isSameState").val();

	//alert("batchNo"+batchNo+"itemId"+itemId+"billQty"+billQty+"index"+index)
	var isDel=confirm('Are you sure want to delete this record');
	if(isDel==true)
	{
	$.getJSON('${deleteItemDetail}', {
		
		batchNo:batchNo,
		itemId:itemId,
		billQty:billQty,
	    key:index,
		ajax : 'true',

	}, function(data) {
		
		var len = data.length;
        var total=0;
       var index=0;
		$('#table2 td').remove();
		$.each(data.billDetail,function(key, item) {
	     index=key;
		 var tr = $('<tr></tr>');
	  	 tr.append($('<td></td>').html(key+1));
	  	 tr.append($('<td></td>').html(item.itemName));
	     tr.append($('<td></td>').html(item.itemHsncd));
  	     tr.append($('<td></td>').html(item.billQty));
	 	 tr.append($('<td style=" font-weight: bold;"></td>').html(item.batchNo));
	 	 tr.append($('<td></td>').html(item.billQty));
	 	 tr.append($('<td></td>').html(item.rate));
	 	 tr.append($('<td></td>').html(item.baseRate));
	 	 tr.append($('<td></td>').html(item.taxableAmt));
	  if(isSameState==1)
		  {
	    	tr.append($('<td></td>').html(item.cgstPer+item.sgstPer));  

		  }
	  else
		  {
		 	tr.append($('<td></td>').html(item.igstPer));  

		  }
	    tr.append($('<td></td>').html(item.cessPer));
		tr.append($('<td></td>').html(item.cessRs));
		tr.append($('<td></td>').html(item.taxAmt));
		tr.append($('<td></td>').html(item.grandTotal));
		tr.append($('<td></td>').html("<a href='#' class='action_btn'onclick=deleteItemDetail("+item.batchNo+","+item.itemId+","+item.billQty+","+index+")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
		$('#table2 tbody').append(tr);
 		total=total+item.grandTotal;
	
		});
		    total=total.toFixed(2);
		    document.getElementById('total').innerHTML=""+total;
		    document.getElementById('tot').value=total;
			var discount = $("#discount").val();
			var grandAmt = total;// - (total * (discount / 100));
		    document.getElementById("paidAmount").setAttribute('value',grandAmt);
			var paidAmount = $("#paidAmount").val();
			var grandMinusPaidAmt = grandAmt - paidAmount;
		    document.getElementById('grandtotal').innerHTML=""+grandAmt;
		    document.getElementById('grandtot').value=grandAmt;
		    document.getElementById('remAmount').value=""+grandMinusPaidAmt; 
		    document.getElementById('remAmt').innerHTML=grandMinusPaidAmt;  
 	  if(data.newRow==1)
			  {
 		        $('#tr'+data.uniqueKey).children('td, th').css('background-color','red');
			  }
 	    else{
 		     $('#tr'+data.uniqueKey).children('td, th').css('background-color','pink');
 		  
 	  }
		
	});
	}
	else
		{
		isDel=false;
		}
}
</script>
<script type="text/javascript">
function generateBill()
{
	
	var invoiceNo = $("#invoice_no").val();
	var creditDays = $("#creditDays").val();
	var invoiceDate = $("#invoice_date").val();
	var custId = $("#cust_id").val();
	var gstin = $("#gstin_no").val();
	var custType = $("#cust_type").val();
	var custName = $("#cust_id option:selected").text();
	var mobile = $("#mobile").val();
	var remark = $("#remark").val();
	var paidAmount = $("#paidAmount").val();
	var discount = $("#discount").val();
	$('#loader').show();

	$
	.getJSON(
			'${generateBill}',
			{
				invoiceNo:invoiceNo,
				invoiceDate:invoiceDate,
				creditDays:creditDays,
				custId:custId,
				gstNo:gstin,
				custType:custType,
				custName:custName,
				mobile:mobile,
				remark:remark,
				discount : discount,
				paidAmount : paidAmount,
				ajax : 'true'

			},
			function(data) {
				$('#loader').hide();

				alert("Bill Saved Successfully.")
		    	     document.getElementById('cust_id').value=0;
				     document.getElementById('gstin_no').value="";
				     document.getElementById('mobile').value="";
				     document.getElementById('cust_type').value=0;
					 $('#table2 td').remove();
					 document.getElementById('total').innerHTML=0;
					 document.getElementById('tot').value=0;
					 document.getElementById("paidAmount").setAttribute('value',0);
					 document.getElementById('grandtotal').innerHTML=0;
					 document.getElementById('grandtot').value=0;
					 document.getElementById('remAmount').value=0; 
					 document.getElementById('remAmt').innerHTML="";  
					 document.getElementById('remark').value="";  
					 document.getElementById('invoice_no').value="";  

			});
	
		 
	
}

</script>
<script type="text/javascript">
function pdfBtn()
{

	var invoiceNo = $("#invoice_no").val();
	var creditDays = $("#creditDays").val();
	var invoiceDate = $("#invoice_date").val();
	var custId = $("#cust_id").val();
	var gstin = $("#gstin_no").val();
	var custType = $("#cust_type").val();
	var custName = $("#cust_id option:selected").text();
	var mobile = $("#mobile").val();
	var remark = $("#remark").val();
	var paidAmount = $("#paidAmount").val();
	var discount = $("#discount").val();
	$('#loader').show();

	$
	.getJSON(
			'${generateBill}',
			{
				invoiceNo:invoiceNo,
				creditDays:creditDays,
				invoiceDate:invoiceDate,
				custId:custId,
				gstNo:gstin,
				custType:custType,
				custName:custName,
				mobile:mobile,
				remark:remark,
				discount : discount,
				paidAmount : paidAmount,
				ajax : 'true'

			},
			function(data) {
				$('#loader').hide();

				alert("Bill Saved Successfully.")

				   window.open('${pageContext.request.contextPath}/pdf?url=/showBillPdf/'+data.billNo);
				
	    	     document.getElementById('cust_id').value=0;
			     document.getElementById('gstin_no').value="";
			     document.getElementById('mobile').value="";
			     document.getElementById('cust_type').value=0;
				 $('#table2 td').remove();
				 document.getElementById('total').innerHTML=0;
				 document.getElementById('tot').value=0;
				 document.getElementById("paidAmount").setAttribute('value',0);
				 document.getElementById('grandtotal').innerHTML=0;
				 document.getElementById('grandtot').value=0;
				 document.getElementById('remAmount').value=0; 
				 document.getElementById('remAmt').innerHTML="";  
				 document.getElementById('remark').value="";  
				 document.getElementById('invoice_no').value=""; 
			});
		 
}
</script>
<script type="text/javascript">
		function validation() {
			var invNo = $("#invoice_no").val();
			var invdate = $("#invoice_date").val();
			var custId = $("#cust_id").val();
			var gstIn = $("#gstin_no").val();
			var custType = $("#cust_type").val();
			var itemId = $("#item_id").val();
			var batchNo = $("#batch_no").val();
			var qty = $("#qty").val();
			var isValid = true;
			if (invNo=="" || invNo == null) {
				isValid = false;
				alert("Please Enter Invoice No.");
			} else
			if (invdate=="" || invdate == null) {
				isValid = false;
				alert("Please Select Invoice Date");
			} else
			if (custId==0 || custId == null) {
				isValid = false;
				alert("Please Select Customer");
			} else if (gstIn== "" || gstIn==null) {
				isValid = false;
				alert("Please Enter Valid GSTIN No");
			} else if (custType ==0 || custType == null) {
				isValid = false;
				alert("Please Select Customer Type");
			}
			 else if (itemId==0 || itemId == null) {
					isValid = false;
					alert("Please Select Item");
				}
			 else if (batchNo==0 || batchNo == null) {
					isValid = false;
					alert("Please Select Batch");
				}
			 else if (qty==0 || qty == "") {
					isValid = false;
					alert("Please Enter Valid Qty");
				}
			return isValid;
		}
</script>
</body>

</html>
