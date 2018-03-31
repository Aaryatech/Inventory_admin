<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	 

<c:url var="getCatListByGroupId" value="/getCatListByGroupId" />
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
						<i class="fa fa-file-o"></i>Item Master
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
								<i class="fa fa-bars"></i>Item
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/getItemList">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


						<div class="box-content">
							<form action="${pageContext.request.contextPath}/insertItem" method="post" class="form-horizontal" id=
									"validation-form"
										 method="post">
							

								<div class="form-group">
								<input type="hidden" name="itemId" id="itemId" value="${itemMaster.itemId}" class="form-control"/>
									<label class="col-sm-3 col-lg-2 control-label">Item Code</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="itemCode" value="${itemMaster.itemCode}" class="form-control"placeholder="Item Code" data-rule-required="true" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Item Name
									</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="itemName" value="${itemMaster.itemName}" class="form-control"placeholder="Item Name"data-rule-required="true" />
									</div>

								</div>
							<input type="hidden" name="uomName" id="uomName" value="${itemMaster.uomName}" class="form-control"/>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Item UOM</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="uomId" id="uomId"  class="form-control" onchange="getUomName();" required>
										<c:forEach items="${itemUomList}" var="itemUomList">
												<c:choose>
													<c:when test="${itemUomList.uomId==itemMaster.uomId}">
													<option value="${itemUomList.uomId}" selected><c:out value="${itemUomList.uomName}"/></option>
													</c:when>
												</c:choose> 
										</c:forEach>
											 <option value="" >Select</option>
											 <c:forEach items="${itemUomList}" var="itemUomList">
												<option value="${itemUomList.uomId}"><c:out value="${itemUomList.uomName}"/></option>
												</c:forEach> 
										</select>
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Item
										Specification </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="rm_specification" class="form-control"placeholder="Specification "data-rule-required="true"/>
									</div>
								</div>
							
								<div class="form-group">
									

									<label class="col-sm-3 col-lg-2 control-label">Item Group</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="groupId" id="groupId" class="form-control" required>
										
										<c:forEach items="${itemGroupList}" var="itemGroupList">
												<c:choose>
													<c:when test="${itemGroupList.groupId==itemMaster.groupId}">
													<option value="${itemGroupList.groupId}" selected><c:out value="${itemGroupList.groupName}"/></option>
													</c:when>
												</c:choose> 
										</c:forEach>
											 <option value="" >Select</option>
												<c:forEach items="${itemGroupList}" var="itemGroupList"
													varStatus="count">
												<option value="${itemGroupList.groupId}"><c:out value="${itemGroupList.groupName}"/></option>
												</c:forEach>
								</select>
									</div>
									<label class="col-sm-3 col-lg-2 control-label">Item Category</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="catId" id="catId" class="form-control" placeholder="Category"data-rule-required="true">
											 <c:forEach items="${itemCategoryList}" var="itemCategoryList">
												<c:choose>
													<c:when test="${itemCategoryList.catId==itemMaster.catId}">
													<option value="${itemCategoryList.catId}" selected><c:out value="${itemCategoryList.catName}"/></option>
													</c:when>
												</c:choose> 
										</c:forEach>
										
										 <c:forEach items="${itemCategoryList}" var="itemCategoryList">
												 
													<option value="${itemCategoryList.catId}" selected><c:out value="${itemCategoryList.catName}"/></option>
													 
										</c:forEach>
										</select>
									</div>
								 
								</div>
							
								<div class="form-group">
									
									 
									<label class="col-sm-3 col-lg-2 control-label">Weight</label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="itemWeight" value="${itemMaster.weight}" class="form-control"placeholder="Weight"data-rule-required="true"data-rule-number="true" />
						</div>
						
						<label class="col-sm-3 col-lg-2 control-label">Item Pack
										Quantity</label>

									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="packQty" value="${itemMaster.packQty}" class="form-control"placeholder="Pack Qty"data-rule-required="true"data-rule-number="true" />
									</div>
								</div>
								
								<div class="form-group">
									 
									<label class="col-sm-3 col-lg-2 control-label">BMS min
										Quantity</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="itemMinQty" value="${itemMaster.bmsMinQty}" id="rm_min_qty" class="form-control"placeholder="Min Qty"data-rule-required="true"data-rule-number="true" />
									</div>
									
									<label class="col-sm-3 col-lg-2 control-label">BMS max
								Quantity </label>

							<div class="col-sm-6 col-lg-4 controls">
								<input type="text" name="itemMaxQty" id="itemMaxQty" value="${itemMaster.bmsMaxQty}" class="form-control"placeholder="Max Qty "data-rule-required="true" data-rule-number="true" />
							</div>
								</div>

						<div class="form-group">

							
						 <label class="col-sm-3 col-lg-2 control-label">BMS ROL Qty</label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="itemRolQty" id="itemRolQty" value="${itemMaster.bmsRolQty}" class="form-control"placeholder="Re Order level Qty"data-rule-required="true"data-rule-number="true" />
						</div>
						
							
						 <label class="col-sm-3 col-lg-2 control-label">CGST%</label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="cgst" id="cgst" value="${itemMaster.cgst}" class="form-control" placeholder="CGST"data-rule-required="true"data-rule-number="true" />
						</div>
					

						
					</div>
					 

					<div class="form-group">
						
						 <label class="col-sm-3 col-lg-2 control-label">SGST%</label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="sgst" id="sgst" value="${itemMaster.sgst}" class="form-control" placeholder="SGST"data-rule-required="true"data-rule-number="true" />
						</div>
						
						 <label class="col-sm-3 col-lg-2 control-label">IGST%</label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="igst" id="igst" value="${itemMaster.igst}" class="form-control" placeholder="IGST"data-rule-required="true"data-rule-number="true" />
						</div>
						 
					</div>
				
					<div class="form-group">
					
					<label class="col-sm-3 col-lg-2 control-label">Store Min
							Qty </label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="storeMinQty" id="storeMinQty" value="${itemMaster.storeMinQty}" class="form-control" placeholder="Store Min Qty"data-rule-required="true"data-rule-number="true" />
						</div>
						
						<label class="col-sm-3 col-lg-2 control-label">Store Max Qty </label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="storeMaxQty"  id="storeMaxQty" value="${itemMaster.storeMaxQty}" class="form-control"placeholder="Store Max Qty"data-rule-required="true" data-rule-number="true" />
						</div>

						
						</div>
						<div class="form-group">
						
						<label class="col-sm-3 col-lg-2 control-label">Store ROL
							Qty </label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="storeRolQty"  id="storeRolQty" value="${itemMaster.storeRolQty}" class="form-control" placeholder="Store ROL Qty"data-rule-required="true" data-rule-number="true" />
						</div>
					<label class="col-sm-3 col-lg-2 control-label">HSN Code</label>

						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="hsnCode" class="form-control" value="${itemMaster.hsnCode}"  placeholder="RM HSNCD" data-rule-required="true"data-rule-number="true"  />
						</div>
						
						
						  
					</div>
					
					<div class="form-group">
					
					<label class="col-sm-3 col-lg-2 control-label">Item
							isCritical </label>
						<div class="col-sm-6 col-lg-4 controls">
						 
						 <c:choose>
						 	<c:when test="${itemMaster.isCritical==2}">
							 	<input type="radio" name="isCritical" value="2" checked="true"> High
								<input type="radio" name="isCritical" value="1" > Normal
	  							<input type="radio" name="isCritical" value="0"> Low 
						 	</c:when>
						 	<c:when test="${itemMaster.isCritical==1}">
							 	<input type="radio" name="isCritical" value="2" > High
								<input type="radio" name="isCritical" value="1" checked="true"> Normal
	  							<input type="radio" name="isCritical" value="0"> Low 
						 	</c:when>
						 	<c:when test="${itemMaster.isCritical==0}">
							 	<input type="radio" name="isCritical" value="2" > High
								<input type="radio" name="isCritical" value="1" > Normal
	  							<input type="radio" name="isCritical" value="0"checked="true"> Low 
						 	</c:when>
						 	<c:otherwise>
						 		<input type="radio" name="isCritical" value="2" > High
								<input type="radio" name="isCritical" value="1" checked="true"> Normal
	  							<input type="radio" name="isCritical" value="0"> Low 
						 	</c:otherwise>
						 </c:choose>
							   
  							
  								</div>
  					<label class="col-sm-3 col-lg-2 control-label">CESS% </label>
						<div class="col-sm-6 col-lg-4 controls">
						<input type="text" name="cess" class="form-control"value="${itemMaster.cess}"  placeholder="CESS" data-rule-required="true"data-rule-number="true"  />
						 
  								</div>

						
  								</div>
									<!-- <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Raw Material Image</label>
									<div class="col-md-3">
										<div class="fileupload fileupload-new"
											data-provides="fileupload">
											 
											<div
												class="fileupload-preview fileupload-exists img-thumbnail"
												style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"><span
													class="fileupload-new">Select image</span> <span
													class="fileupload-exists">Change</span> <input type="file"
													class="file-input" name="image1" id="image1"
													 /></span> <a href="#"
													class="btn btn-default fileupload-exists"
													data-dismiss="fileupload">Remove</a>
											</div>
										</div>

									</div>
								</div> -->
							
					<div class="row">
						<div class="col-md-12" style="text-align: center">
							<input type="submit" class="btn btn-info" value="Submit" onclick="return validate()">


						</div>
					</div>
					</form>
				</div>
			</div>

		</div>
	</div>
	<!-- END Main Content -->
	<footer>
	<p>2018 © SONA ELECTRICALS</p>
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
$(document).ready(function() { 
	$('#groupId').change(
			function() {
				$.getJSON('${getCatListByGroupId}', {
					grpId : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var html = '<option value="" selected >Select Category</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].catId + '">'
								+ data[i].catName + '</option>';
					}
					html += '</option>';
					$('#catId').html(html);
					$('#catId').formcontrol('refresh');

				});
			});
});

 
			function getUomName() {
				 var uomName = $('#uomId option:selected').text();
				 document.getElementById("uomName").value=uomName;
			} 
 
</script>
 

</body>
</html>

