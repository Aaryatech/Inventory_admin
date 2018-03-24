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


	<c:url var="getMixingListWithDate" value="/getMixingListWithDate"></c:url>
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
				 
						<i class="fa fa-file-o"></i>Add Supplier
						 
					</h1>
				</div>
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box" id="todayslist">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Add Supplier
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/getSupplierList">Supplier List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						<div class=" box-content">
							<form id="addSupplier" action="${pageContext.request.contextPath}/insertSupplier" method="post">
							
								<div class="box-content">
							
								<div class="col-md-2">Supplier Name*</div>
								<div class="col-md-3">
									<input type="text" name="supp_name" class="form-control" value="${supplierMaster.suppName}" placeholder="Enter Supplier Name " required/>
									<input type="hidden" name="suppId" value="${supplierMaster.suppId}" />
								</div> 
								<div class="col-md-1"></div>
								<div class="col-md-2">Supplier GSTIN*</div>
									<div class="col-md-3">
									<input type="text" name="supp_gstin" class="form-control" value="${supplierMaster.gstinNo}" placeholder="Enter GSTIN " required/>
									
									</div>
								
				 
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Address*</div>
								<div class="col-md-3">
									<input type="text" name="supp_addr" value="${supplierMaster.address}" class="form-control" placeholder="Address" required/>
									</div>
									<div class="col-md-1"></div>
								<div class="col-md-2">City*</div>
									<div class="col-md-3">
									<input type="text" name="supp_city" value="${supplierMaster.city}" class="form-control"  placeholder="Enter City " required/>
									
									</div>
								
				 
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">State*</div>
								<div class="col-md-3">
									<input type="text" name="supp_state" value="${supplierMaster.state}" class="form-control"  placeholder="Enter State" required />
								</div>
								<div class="col-md-1"></div>
								<div class="col-md-2">Country*</div>
									<div class="col-md-3">
									<input type="text" name="supp_country" value="${supplierMaster.country}" class="form-control"  placeholder="Enter County " required >
								
									</div>
								
				 
							</div><br>
								<div class="box-content">
							
								<div class="col-md-2">Mobile 1*</div>
								<div class="col-md-3">
									<input type="text" name="supp_mob1" value="${supplierMaster.mobile1}" class="form-control" placeholder="Enter Mobile 1 " required 
													 pattern="^\d{10}$" required /></div> 
								<div class="col-md-1"></div>
								<div class="col-md-2">Email 1*</div>
									<div class="col-md-3">
									<input type="email" name="supp_email1" value="${supplierMaster.email1}" class="form-control"  placeholder="Enter Email 1 " required
													data-rule-email="true" />
									</div>
								
				 
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Mobile 2*</div>
								<div class="col-md-3">
									<input type="text" name="supp_mob2" value="${supplierMaster.mobile2}" class="form-control" placeholder="Enter Mobile 2 " required 
													 pattern="^\d{10}$" required /></div> 
								 <div class="col-md-1"></div>
								<div class="col-md-2">Email 2</div>
									<div class="col-md-3">
									<input type="email" name="supp_email2" value="${supplierMaster.email2}" class="form-control"  placeholder="Enter Email 2 "  
													data-rule-email="true" />
									</div>
								
				 
							</div><br>
							<div class="box-content">
							
								<div class="col-md-2">Mobile 3</div>
								<div class="col-md-3">
									<input type="text" name="supp_mob3" value="${supplierMaster.mobile3}" class="form-control" placeholder="Enter Mobile3 "   
													 pattern="^\d{10}$"   /></div> 
								<div class="col-md-1"></div>
								<div class="col-md-2">Email 3</div>
									<div class="col-md-3">
									<input type="email" name="supp_email3" value="${supplierMaster.email3}" class="form-control"  placeholder="Enter Email3 "  
													data-rule-email="true" />
									</div>
								
				 
							</div><br>
							<div class="box-content">
							
								<div class="col-md-2">Phone 1</div>
								<div class="col-md-3">
									<input type="text" name="supp_phone1" value="${supplierMaster.phone1}" class="form-control" placeholder="Enter Phone 1 "  pattern="^[0-9]{10,12}$" />
								</div>
								<div class="col-md-1"></div>
								<div class="col-md-2">Email 4</div>
									<div class="col-md-3">
									<input type="email" name="supp_email4" value="${supplierMaster.email4}" class="form-control"  placeholder="Enter Email4 "  
													data-rule-email="true" />
									</div>
								
				 
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Phone 2</div>
								<div class="col-md-3">
									<input type="text" name="supp_phone2" value="${supplierMaster.phone2}" class="form-control" placeholder="Enter Phone 5 "  pattern="^[0-9]{10,12}$" />
								</div>
								<div class="col-md-1"></div>
								<div class="col-md-2">Email 5</div>
									<div class="col-md-3">
									<input type="email" name="supp_email5" value="${supplierMaster.email5}" class="form-control"  placeholder="Enter Email5 "  
													data-rule-email="true" />
									</div>
								
				 
							</div><br>
							<div class="box-content">
							
								<div class="col-md-2">Supplier Pan No*</div>
								<div class="col-md-3">
									<input type="text" name="supp_panno" value="${supplierMaster.panNo}" class="form-control"  placeholder="Supplier Pan No" required/>
									
								</div>
								<div class="col-md-1"></div>
								<div class="col-md-2"> Contact Person*</div>
									<div class="col-md-3">
									<input type="text" name="supp_c_person" value="${supplierMaster.contactPrsn}" class="form-control" placeholder="Supplier contact person" required/>
									
									</div>
								
				 
							</div><br>
								<div class="box-content">
							
								<div class="col-md-2">Supplier FDA Lic*</div>
								<div class="col-md-3">
									<input type="text" name="supp_fdalic" value="${supplierMaster.fdaLic}" class="form-control" placeholder="Supplier FDA Lic" required />
										
								</div>
								<div class="col-md-1"></div>
								<div class="col-md-2">Supplier Pay Id*</div>
									<div class="col-md-3">
									<input type="text" name="supp_pay_id" value="${supplierMaster.supplierPayId}" class="form-control" placeholder=" Supplier Pay Id" required="true"  data-rule-required="true"/>
									
									</div>
								
				 
							</div><br>
							<div class="box-content">
							
								<div class="col-md-2">Supplier Credit days*</div>
								<div class="col-md-3">
									<input type="text" name="credit_days" value="${supplierMaster.creditDays}" class="form-control" placeholder="Supplier Credit days" required data-rule-required="true"/>
										
								</div>
								<c:choose>
									<c:when test="${supplierMaster.isSameState==1}">
										<c:set var="choose" value="Yes"></c:set> 
									</c:when>
									<c:otherwise>
										<c:set var="choose" value="No"></c:set> 
									</c:otherwise>
								</c:choose>
								<div class="col-md-1"></div>
								<div class="col-md-2">Is Same State*</div>
									<div class="col-md-3">
									 <select id="isSameState" name="isSameState" id="isSameState" class="form-control chosen" required>
									   <option value="${supplierMaster.isSameState}" >${choose}</option>
									  <option value=""  > Select</option>
									 <option value="1">Yes</option>
									 <option value="2">No</option>
									 </select>
									</div>
								 
							</div><br>
							
							
							 <div class=" box-content">
					<div class="col-md-12" style="text-align: center">
						<input type="submit" class="btn btn-info" value="Submit">
					 


					</div>
				</div>
							
							
							
							</form>
					 

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
		
		 
	
	
</body>
</html>