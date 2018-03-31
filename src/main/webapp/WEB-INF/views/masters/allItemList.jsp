<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	 
<c:url var="allRecordwithDate" value="/allRecordwithDate"></c:url> 


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
					<i class="fa fa-file-o"></i>Item List
				</h1>
				
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->

		
		<!-- BEGIN Main Content -->
		<div class="box" id="pending">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Item List
				</h3>
				<div class="box-tool">
				<a href="${pageContext.request.contextPath}/addItem">Add Item</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
				</div>

			</div> 
				<div class=" box-content">
				<form id="validation-form"
				action="allDirectorMaterialReceiptNote"
				method="get">
				<input type="hidden" value="1" name="viewAll">
				
				 
				</form>
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Item Name</th>
										<th>Item Code</th>
										<th>HSN Code</th>
										<th>UOM</th>
										<th>Company</th>
										 <th>Category</th>
										<th>Action</th>


									</tr>
								</thead>
								
								<tbody>

									<c:forEach items="${itemList}" var="itemList"
													varStatus="count">
													  
													 
													<tr>
												  
														<td><c:out value="${count.index+1}" /></td>

														<td><c:out value="${itemList.itemName}" /></td>
																
														<td><c:out value="${itemList.itemCode}" /></td>
																 
												 	 	<td><c:out value="${itemList.hsnCode}" /> </td> 
														<td><c:out value="${itemList.uomName}" /> </td>
														<c:forEach items="${companyList}" var="companyList" >
														 	<c:choose>
														 		<c:when test="${itemList.groupId==companyList.groupId}">
														 		<td><c:out value="${companyList.groupName}" /> </td>
														 		</c:when>
														 	</c:choose>
														</c:forEach>
														<c:forEach items="${itemCategoryList}" var="itemCategoryList" >
														 	<c:choose>
														 		<c:when test="${itemList.catId==itemCategoryList.catId}">
														 		<td><c:out value="${itemCategoryList.catName}" /> </td>
														 		</c:when>
														 	</c:choose>
														</c:forEach>
												  		 
							<td><a href="${pageContext.request.contextPath}/editItem/${itemList.itemId}"><span class="glyphicon glyphicon-edit" onclick="edit(${itemUomList.uomId});"></span></a>
							
							<a href="${pageContext.request.contextPath}/deleteItem/${itemList.itemId}"
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
	<!-- END Main Content -->

	<footer>
	<p>2018 © SONA ELECTRICALS.</p>
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
	 
		
		
		
</body>
</html>