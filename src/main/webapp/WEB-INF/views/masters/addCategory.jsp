<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	 <c:url var="editCategory" value="/editCategory" /> 
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
						<i class="fa fa-file-o"></i>Item
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
								<i class="fa fa-bars"></i>Add Item Category 
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showItemCatList">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						<div class="box-content">
							<form action="${pageContext.request.contextPath}/insertCategory" class="form-horizontal"
								method="post" id="validation-form">



                         <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Company</label>
									<div class="col-sm-9 col-lg-10 controls">
										 <select id="groupId" name="groupId" class="form-control" required>
									  <option value="">Select Company</option>
										  <c:forEach items="${activeGroupList}" var="activeGroupList" varStatus="count"> 
										 	 <option value="${activeGroupList.groupId}">${activeGroupList.groupName}</option> 
										  </c:forEach>
									 
									 </select>
									</div>
								</div>
						
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label" for="item_name">Category
										Name</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="catName" id="catName"
											placeholder="Category Name" class="form-control"
											data-rule-required="true" />
											<input type="hidden" name="catId" id="catId"  />
									</div>
								</div>
                               

                               <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label" for="item_name">Category
										Description</label>
									<div class="col-sm-9 col-lg-10 controls">
										<textarea name="catDesc" id="catDesc"
											placeholder="Category Description" class="form-control"
											data-rule-required="true"></textarea>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
										<input type="submit" class="btn btn-primary" value="Submit" onclick="return validate()">
										<input type="button" class="btn btn-primary" value="Cancel" id="cancel" onclick="cancel1()" disabled>
 
 									</div>
								</div>
							</form>
						</div>
						
						<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i>Category List
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
														<th width="360" align="center">Category Name</th>
														<th width="360" align="center">Company Name</th>
														<th width="50" align="left">Action</th>
												</tr>
												</thead>
												<tbody>
					 			 <c:forEach items="${itemCategoryList}" var="itemCategoryList" varStatus="count">
					 			 
														<tr>
														
															<td><c:out value="${count.index+1}"/></td>
															<td align="left"><c:out
																	value="${itemCategoryList.catName}"></c:out></td>
															
															<c:forEach items="${itemGroupList}" var="itemGroupList">
																<c:choose>
																	<c:when test="${itemGroupList.groupId==itemCategoryList.groupId}">
																	<td align="left"><c:out
																	value="${itemGroupList.groupName}"></c:out></td>
																	</c:when>
																</c:choose>
															</c:forEach>
															
															<td align="left"><span
														class="glyphicon glyphicon-edit" onclick="edit(${itemCategoryList.catId});"></span>&nbsp;
                                                        
                                                        <a href="deleteItemCategory/${itemCategoryList.catId}"
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

</body>
<script type="text/javascript">
		
		
		function edit(catId) {

			         
			 
				//alert("driverId"+driverId);
				$('#loader').show();

				$
						.getJSON(
								'${editCategory}',

								{
									 
									catId : catId, 
									ajax : 'true'

								},
								function(data) { 
									 
									document.getElementById("catId").value=data.catId;
									document.getElementById("catName").value=data.catName; 
									document.getElementById("groupId").value=data.groupId;
									document.getElementById("catDesc").value=data.catDesc;
									document.getElementById("cancel").disabled=false;
								});

			 
				 
			
	}
		
		function cancel1() {

	         //alert("cancel");
	         document.getElementById("cancel").disabled=true; 
	         document.getElementById("catId").value="";
				document.getElementById("catName").value=""; 
				document.getElementById("groupId").value="";
				document.getElementById("catDesc").value="";
				 
		
	}
		 
		 
	</script>
</html>