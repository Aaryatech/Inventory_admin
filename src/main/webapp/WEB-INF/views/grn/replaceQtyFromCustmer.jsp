<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body> 
<c:url var="addItemInPurchaseBill" value="/addItemInPurchaseBill"></c:url>
<c:url var="getallExpireItemSupllierWise" value="/getallExpireItemSupllierWise"></c:url>

<c:url var="delteItemFromReplaceList" value="/delteItemFromReplaceList"></c:url> 
<c:url var="addItemInReplaceList" value="/addItemInReplaceList"></c:url> 

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
					<i class="fa fa-file-o"></i>Replace
				</h1>
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->


		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Replace
				</h3>
				<div class="box-tool">
								 <a href="${pageContext.request.contextPath}/purchaseBillList">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

			</div>

			<div class=" box-content">

				<div class="box">
					<form onsubmit="return confirm('Do you really want to submit ?');" id="validation-form" class="form-horizontal"
						action="${pageContext.request.contextPath}/submitReplaceItemList" method="post">

						 
						<div class="box-content">
						<div class="col-md-1" >Batch No</div>
									<div class="col-md-2">
										 <input type="text" name="batchNo" id="batchNo" class="form-control"  >
									</div>
						<div class="col-md-1" >Qty</div>
									<div class="col-md-2">
										 <input type="text" name="qty" id="qty" class="form-control"  >
									</div>
									
							<div class="col-md-1"><input type="button" class="btn btn-info" id=search onclick="searchItem();" value="Add To Replace"></div>
							 
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
												<th>Item Name</th>  
												<th>Batch No</th>  
												<th >Qty</th>  
												<th>Action</th>

											</tr>
										</thead>
										<tbody>
										
										 
										</tbody>
									</table>
								</div>
							</div>

						</div>
						 
						<br />
						<br />

						<div class="row">
							<div class="col-md-12" style="text-align: center">
								 <input type="submit" class="btn btn-info" id="submit" value="Submit" disabled>
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
	 
	 
	  
	function searchItem()
	{
		
		
		var batchNo = $("#batchNo").val(); 
		var qty = $("#qty").val();
		 
		var valid=0;
		if (batchNo=="") {
 
			alert("Enter Batch No ");
			valid=1;

		}
		if (qty=="" || isNaN(qty)) {
			 
			alert("Enter Qty");
			valid=1;

		}
		 
		if(valid==0)
			{
			
				$.getJSON(
						'${addItemInReplaceList}',

						{
							batchNo :batchNo, 
							qty : qty,
							ajax : 'true',

						},
						function(data) {
							
							 
							$('#table_grid td').remove();
							if (data == "") {
								alert("No records found !!");

							}
							else
								{
							 
								document.getElementById("submit").disabled=false;
								 
								}
							
							$.each(
									data,
									function(key, itemList) {
										
										 
										var tr = $('<tr></tr>');
											tr.append($('<td></td>').html(key+1));
											tr.append($('<td></td>').html(itemList.itemName));
										  	tr.append($('<td></td>').html(itemList.batchNo)); 
										  	tr.append($('<td></td>').html(itemList.qty));  
										  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-remove" onclick="deleteItem('+key+');""></span>')); 
											$('#table_grid tbody').append(tr);
											
										 

									})
									
						 
							
						});
				
			 
		
		
			}
	}
	
	function deleteItem(key)
	{
		 
				$.getJSON(
						'${delteItemFromReplaceList}',

						{
							index : key, 
							ajax : 'true',

						},
						function(data) {
							
							 
							$('#table_grid td').remove();
							if (data == "") {
								alert("No records found !!");
								
							 
								document.getElementById("submit").disabled=true;
								  s
							}
							
							 
							$.each(
									data,
									function(key, itemList) {
										
										 
										var tr = $('<tr></tr>');
										tr.append($('<td></td>').html(key+1));
										tr.append($('<td></td>').html(itemList.itemName));
									  	tr.append($('<td></td>').html(itemList.batchNo)); 
									  	tr.append($('<td></td>').html(itemList.qty));  
									  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-remove" onclick="deleteItem('+key+');""></span>')); 
										$('#table_grid tbody').append(tr);
											
										 

									})
									
						 
							
						});
				
	 
	}
	
	 
	  
	</script>
	

</body>
</html>