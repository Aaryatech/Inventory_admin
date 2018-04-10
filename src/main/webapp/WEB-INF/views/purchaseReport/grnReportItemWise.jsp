<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 
 	
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 
<body>
 

	<c:url var="grnItemWiseReport" value="/grnItemWiseReport"></c:url> 
	<c:url var="getSupplierListForPurchaseLis" value="/getSupplierListForPurchaseLis"></c:url> 


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
						<i class="fa fa-file-o"></i>GRN Report
					</h1>
					
				</div>
				
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					 
					<div class="box" id="supplierwise" >
					
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>GRN Report
							</h3>
							<div class="box-tool">
								 <a href="${pageContext.request.contextPath}/insertGrn">Insert Grn</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							 
						</div>
						<div class=" box-content">
						<div class="form-group">
									<div class="col-md-2">From Date:</div>
									<div class="col-md-3">
										<input class="form-control date-picker" id="fromDate" size="16"
											 type="text" name="fromDate" required />
									
										</div>
										
										<div class="col-md-2">To Date:</div>
									<div class="col-md-3">
										<input class="form-control date-picker" id="toDate" size="16"
											 type="text" name="toDate" required />
									
										</div>
										
										
										</div><br>
						
								</div>
								
								<div class=" box-content">
								    <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Select GRN Type</label>
									<div class="col-sm-5 col-lg-3 controls">
										<select name="grnType" class="form-control chosen" tabindex="-1" id="grnType" data-rule-required="true">
													<option value="" >Select Type</option>
													 <option value="0" >Regular</option>
													 <option value="1" >Replace</option>
												</select>
									
										</div>
										 
										</div><br>
						
								</div>
								 
								 
								<div class=" box-content">
								<div class="form-group">
								
								<div align="center" class="form-group">
									<div class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										 
										<input type="button" class="btn btn-primary" value="View All" id="searchmixall"
											onclick="searchsupplierwise()">
											  <input type="button" class="btn btn-primary" value="Pdf" onclick="getPdf()" >
									</div><br>
									
									<div align="center" id="loader" style="display: none">

									<span>
										<h4>
											<font color="#343690">Loading</font>
										</h4>
									</span> <span class="l-1"></span> <span class="l-2"></span> <span
										class="l-3"></span> <span class="l-4"></span> <span
										class="l-5"></span> <span class="l-6"></span>
								</div>	
									
									
								</div>
								</div>
								</div>
								
							

						<div class=" box-content">
					<div class="row">
					
					
						<div class="col-md-12 table-responsive">
							<div style="overflow:scroll;height:100%;width:100%;overflow:auto">
									<table width="100%" height="100%" border="0"class="table table-bordered table-striped fill-head "
										style="width: 100%" id="table_grid1">
								<thead>
									<tr>
										<th>Sr.No.</th>  
										<th>Item Name</th> 
										<th>Qty</th> 
										<th>Total</th>  
										
									</tr>
								</thead>
								
								<tbody>
									 
										
									
								</tbody>
							</table>
							<div class="form-group" style="display: none;" id="range">
								 
											 
											 
											<div class="col-sm-3  controls">
											 <input type="button" id="expExcel" class="btn btn-primary" value="EXPORT TO Excel" onclick="exportToExcel();" disabled="disabled">
											</div>
											</div>
							<div id="chart" > <br><br> <br>
							 
      							
        <div align="center" id="chart_div" style="width: 50%" ></div>
       <div  align="center" id="PieChart_div" style="width: 50%"></div>
      
     
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
			<p>2017 © MONGINIS.</p>
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
	 
	</script>
		<script type="text/javascript">
		function searchsupplierwise() {
			 
			var fromDate = $("#fromDate").val();
			var toDate = $("#toDate").val();
		    var grnType = $("#grnType").val();
			 
			var valid=0;
			
			if(fromDate=="")
				{
				alert("Enter Valid From Date");
				valid=1;
				
				}
			else if(toDate=="")
				{
				alert("Enter Valid To Date");
				valid=1;
				}
			else if(grnType=="")
				{
				alert("Select Grn Type");
				valid=1;
				}
			 
		
			if(valid==0)
			{
			 
			$.getJSON('${grnItemWiseReport}',

							{
								 
								fromDate : fromDate,
								toDate : toDate,
								grnType : grnType, 
								ajax : 'true'

							},
							function(data) {

								$('#table_grid1 td').remove();
								$('#loader').hide();

								if (data == "") {
									alert("No records found !!");
									 document.getElementById("expExcel").disabled=true;
								}
								
								 

							  $.each( data, function(key, itemList) {
												
								 
									var suppname;
									var type;
									 
										 
												var tr = $('<tr></tr>');
											  	tr.append($('<td></td>').html(key+1)); 
											   
											  	tr.append($('<td></td>').html(itemList.itemName)); 
											  	tr.append($('<td></td>').html(itemList.qty));
											  	tr.append($('<td></td>').html(itemList.total));  
											  	$('#table_grid1 tbody').append(tr);
												 

											})  
							});
						 
			}
	}
		 
		function getPdf()
		{
		    var fromDate = $("#fromDate").val(); 
		    var toDate = $("#toDate").val();
		    var grnType=$("#grnType").val();
			var valid=0;
			
				if(fromDate=="")
					{
					alert("Enter Valid From Date");
					valid=1;
					
					}
				else if(toDate=="")
					{
					alert("Enter Valid To Date");
					valid=1;
					}
				else if(grnType==null)
				{
				alert("Select Grn Type");
				valid=1;
				}
			
				if(valid==0)
				{
			    	window.open('${pageContext.request.contextPath}/purchaseBillReport?url=pdf/grnItemWisePdf/'+fromDate+'/'+toDate+'/'+grnType+'/');
				}
		    }
	</script>
	 
</body>
</html>