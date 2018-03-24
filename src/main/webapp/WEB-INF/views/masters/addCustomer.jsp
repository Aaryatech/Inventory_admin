<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Add Supplier</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<!--base css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/data-tables/bootstrap3/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/compiled/timepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.css" />



<!--page specific css styles-->

<!--flaty css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty-responsive.css">

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/img/favicon.png">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/common.js"></script>
</head>
<body>


	<c:url var="editCustomer" value="/editCustomer"></c:url>
	<c:url var="getMixingAllListWithDate" value="/getMixingAllListWithDate"></c:url>
	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>


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
				 
						<i class="fa fa-file-o"></i>Add Customer
						 
					</h1>
				</div>
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box" id="todayslist">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Add Customer
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/insertCustomer">Add Customer</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						<div class=" box-content">
							<form id="addSupplier" action="${pageContext.request.contextPath}/insertCustomer" method="post">
							
								<div class="box-content">
							
								<div class="col-md-2">Customer Name*</div>
								<div class="col-md-3">
									<input type="text" name="custName" id="custName" class="form-control" placeholder="Enter Customer Name " required/>
									<input type="hidden" name="custId" id="custId"/>
								</div> 
								<div class="col-md-1"></div>
								<div class="col-md-2"> GSTIN*</div>
									<div class="col-md-3">
									<input type="text" name="gstin" id="gstin" class="form-control" placeholder="Enter GSTIN " required/>
									
									</div>
								
				 
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Address*</div>
								<div class="col-md-3">
									<input type="text" name="custAdd" id="custAdd" class="form-control" placeholder="Address" required/>
									</div>
									<div class="col-md-1"></div>
								<div class="col-md-2">Customer Code*</div>
								<div class="col-md-3">
									<input type="text" name="custCode" id="custCode" class="form-control" placeholder="Customer Code" required/>
									</div>
							 
				 
							</div><br>
							
							 
								<div class="box-content">
							
								<div class="col-md-2">Mobile*</div>
								<div class="col-md-3">
									<input type="text" name="mobileNo" id="mobileNo" class="form-control" placeholder="Enter Mobile No " required 
													 pattern="^\d{10}$" required /></div> 
								<div class="col-md-1"></div>
								<div class="col-md-2">Email*</div>
									<div class="col-md-3">
									<input type="email" name="email" id="email" class="form-control"  placeholder="Enter Email " required
													data-rule-email="true" />
									</div>
								
				 
							</div><br>
							 
							<div class="box-content">
							
								<div class="col-md-2">Contact No*</div>
								<div class="col-md-3">
									<input type="text" name="contactNo" id="contactNo" class="form-control" placeholder="Enter Phone "  pattern="^[0-9]{10,12}$" required/>
								</div>
								<div class="col-md-1"></div>
								<div class="col-md-2">Customer Type*</div>
									<div class="col-md-3">
									 <select id="custType" name="custType" id="custType" class="form-control chosen" required>
									  <option value="" Selected> Select Type</option>
									 <option value="1">Wholesale</option>
									 <option value="2">Retailer</option>
									 </select>
									</div>
								
				 
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Contact Person*</div>
								<div class="col-md-3">
									<input type="text" name="conPersn" id="conPersn" class="form-control" placeholder="Enter Contact Person" required/>
								</div>
								<div class="col-md-1"></div>
								<div class="col-md-2">Person Email*</div>
									<div class="col-md-3">
									<input type="email" name="conEmail" id="conEmail" class="form-control"  placeholder="Person Email "  
													data-rule-email="true" required/>
									</div>
								
				 
							</div><br>
							<div class="box-content">
							
								<div class="col-md-2">Customer Pan No*</div>
								<div class="col-md-3">
									<input type="text" name="custPan"  id="custPan" class="form-control"  placeholder="Customer Pan No" required/>
									
								</div>
								<div class="col-md-1"></div>
								<div class="col-md-2">Is Same State*</div>
									<div class="col-md-3">
									 <select id="isSameState" name="isSameState" id="isSameState" class="form-control chosen" required>
									  <option value="" Selected> Select</option>
									 <option value="1">Yes</option>
									 <option value="2">No</option>
									 </select>
									</div>
								 
							</div><br>
							
							 
							<div class="box-content">
							
								<div class="col-md-2">Credit days*</div>
								<div class="col-md-3">
									<input type="text" name="creaditDays" id="creaditDays" class="form-control" placeholder="Credit days" required data-rule-required="true"/>
										
								</div>
								 
							</div><br>
							
							
							 <div class=" box-content">
					<div class="col-md-12" style="text-align: center">
						<input type="submit" class="btn btn-info" value="Submit">
					 <input type="button" class="btn btn-primary" value="Cancel" id="cancel" onclick="cancel1()" disabled>


					</div>
				</div>
							
							
							
							</form>
					 

		</div>
							<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i>Customer List
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
									<table id="table2" class="main-table">
											 
												</table>
									
									</div>
									<div class="table-wrap">
									
										<table id="table1" class="table table-advance">
											<thead>
												<tr class="bgpink">
										<th width="55" style="width: 18px">Sr.No.</th>
														<th width="360" align="center">Customer Name</th>
														<th width="360" align="center">GST No.</th>
														<th width="360" align="center">Customer Code</th>
														<th width="360" align="center">Mobile</th>
														<th width="360" align="center">Email</th>
														<th width="360" align="center">Customer Pan No.</th> 
														<th width="50" align="left">Action</th>
												</tr>
												</thead>
												<tbody>
					  <c:forEach items="${customerList}" var="customerList" varStatus="count">
														<tr>
													
														<td><c:out value="${count.index+1}"/></td>
															<td ><c:out value="${customerList.custName}"></c:out></td>
															<td ><c:out value="${customerList.gstin}"></c:out></td>
															<td ><c:out value="${customerList.custCode}"></c:out></td>
															<td ><c:out value="${customerList.mobile}"></c:out></td>
															<td ><c:out value="${customerList.email}"></c:out></td>
															<td ><c:out value="${customerList.panNo}"></c:out></td>
															<td ><span
														class="glyphicon glyphicon-edit" onclick="edit(${customerList.custId});"></span>&nbsp;
                                                        
                                                        <a href="deleteCustomer/${customerList.custId}"
													    onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a></td>	
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
			<!-- END Main Content -->
			<footer>
			<p>2017 © INVENTORY.</p>
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
		
		
		function edit(custId) {

			         
			 
				//alert("driverId"+driverId);
				$('#loader').show();

				$
						.getJSON(
								'${editCustomer}',

								{
									 
									custId : custId, 
									ajax : 'true'

								},
								function(data) { 
									 
									document.getElementById("custId").value=data.custId;
									document.getElementById("custName").value=data.custName;
									document.getElementById("gstin").value=data.gstin;
									document.getElementById("custAdd").value=data.address;
									document.getElementById("custCode").value=data.custCode;
									document.getElementById("mobileNo").value=data.mobile;
									document.getElementById("email").value=data.email;
									document.getElementById("contactNo").value=data.phone1;
									document.getElementById("conPersn").value=data.conctPrsn;
									document.getElementById("conEmail").value=data.prsnEmail;
									document.getElementById("custPan").value=data.panNo;
									document.getElementById("creaditDays").value=data.creditDays;
									document.getElementById("custType").value=data.custType;
									$('#custType').trigger("chosen:updated");
									document.getElementById("isSameState").value=data.isSameState;
									$('#isSameState').trigger("chosen:updated");
									document.getElementById("cancel").disabled=false;
								});

			 
				 
			
	}
		
		function cancel1() {

	         //alert("cancel");
	         document.getElementById("cancel").disabled=true; 
	         document.getElementById("custId").value="";
				document.getElementById("custName").value="";
				document.getElementById("gstin").value="";
				document.getElementById("custAdd").value="";
				document.getElementById("custCode").value="";
				document.getElementById("mobileNo").value="";
				document.getElementById("email").value="";
				document.getElementById("contactNo").value="";
				document.getElementById("conPersn").value="";
				document.getElementById("conEmail").value="";
				document.getElementById("custPan").value="";
				document.getElementById("creaditDays").value="";
				document.getElementById("custType").value="";
				$('#custType').trigger("chosen:updated");
				document.getElementById("isSameState").value="";
				$('#isSameState').trigger("chosen:updated");
				 
		
	}
		 
		 
	</script>
		 
	
	
</body>
</html>