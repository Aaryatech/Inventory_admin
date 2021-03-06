<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	  
<c:url var="editItemGroup" value="/editItemGroup" />
<c:url var="getRmCategory" value="/getRmCategory" />



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
						<i class="fa fa-file-o"></i>Item Company
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
								<i class="fa fa-bars"></i>Item Company
							</h3>
							<div class="box-tool">
								<a href=""></a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
						
						</div>

						<div class="box-content">
							<form action="${pageContext.request.contextPath}/insertItemGroup" method="post" class="form-horizontal" id=
									"validation-form"
										 method="post">
							

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Company Name </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="groupName" id="groupName" class="form-control"placeholder="Group Name" required/>
									</div>
									<input type="hidden" name="groupId" id="groupId" />
									
						</div>

					<div class="row">
						<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
							<input type="submit" class="btn btn-info" value="Submit">
<input type="button" class="btn btn-primary" value="Cancel" id="cancel" onclick="cancel1()" disabled>

						</div>
					</div>
					</form>
							<br>
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i>Company List
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
									 
									
									</div>
									<div class="table-wrap">
									
										<table id="table1" class="table table-advance">
											<thead>
												<tr class="bgpink">
										<th width="55" style="width: 18px">Sr.No.</th>
														<th width="360" align="center">Company Name</th>
														<th width="50" align="left">Action</th>
												</tr>
												</thead>
												<tbody>
					  <c:forEach items="${itemGroupList}" var="itemGroupList" varStatus="count">
														<tr>
														
															<td><c:out value="${count.index+1}"/></td>
															<td align="left"><c:out
																	value="${itemGroupList.groupName}"></c:out></td>
															
															<td align="left"><span
														class="glyphicon glyphicon-edit" onclick="edit(${itemGroupList.groupId});"></span>&nbsp;
                                                        
                                                        <a href="deleteItemGroup/${itemGroupList.groupId}"
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
	</div>
	<!-- END Main Content -->
	<footer>
	<p>2018 © SONA ELECTRICALS.</p>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>

			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>
				<script type="text/javascript">
		
		
		function edit(groupId) {

			         
			 
				//alert("driverId"+driverId);
				$('#loader').show();

				$
						.getJSON(
								'${editItemGroup}',

								{
									 
									groupId : groupId, 
									ajax : 'true'

								},
								function(data) { 
									 
									document.getElementById("groupId").value=data.groupId;
									document.getElementById("groupName").value=data.groupName; 
									document.getElementById("cancel").disabled=false;
								});

			 
				 
			
	}
		
		function cancel1() {

	         //alert("cancel");
	         document.getElementById("cancel").disabled=true; 
	         document.getElementById("groupId").value="";
				document.getElementById("groupName").value="";
				 
		
	}
		 
		 
	</script>
	
</body>
</html>

