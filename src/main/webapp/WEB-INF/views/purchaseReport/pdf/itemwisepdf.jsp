<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Purchase Report</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->


 <style type="text/css">
 table {
	border-collapse: collapse;
	font-size: 10;
	width:100%;

} 
p  {
    color: black;
    font-family: arial;
    font-size: 60%;
	margin-top: 0;
	padding: 0;

}
h6  {
    color: black;
    font-family: arial;
    font-size: 80%;
}

th {
	background-color: #EA3291;
	color: white;
	
}
</style>
</head>
<body onload="myFunction()">
<h3 align="center">Sona Electricals</h3>
<p align="center">BEED</p>
<div align="center"> <h5> Purchase Report  &nbsp;&nbsp;&nbsp;&nbsp; From &nbsp; ${fromDate}  &nbsp;To &nbsp; ${toDate}</h5></div>
	<table  align="center" border="1" cellspacing="0" cellpadding="1" 
		id="table_grid" class="table table-bordered">
		<thead>
			<tr class="bgpink">
										<th style="text-align:center;">Sr.No.</th>
										<th style="text-align:center;">Item Name</th>
										<th style="text-align:center;">Qty</th> 
										<th style="text-align:center;">Discount</th>
										<th style="text-align:center;">Freight Amt</th>
										<th style="text-align:center;">Insurance Amt</th> 
										<th style="text-align:center;">Taxable Amt</th>
										<th style="text-align:center;">CGST</th>
										<th style="text-align:center;">SGST</th>
										<th style="text-align:center;">IGST</th> 
										<th style="text-align:center;">Cess</th> 
										<th style="text-align:center;">Other</th>
										<th style="text-align:center;">Total</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="freightAmt" value="${0 }" />
			<c:set var="insuranceAmt" value="${0 }" />
			<c:set var="taxableAmount" value="${0}" />
			<c:set var="igst" value="${0 }" />
			<c:set var="cgst" value="${0 }" />
			<c:set var="sgst" value="${0 }" />
			<c:set var="cess" value="${0 }" />
			<c:set var="discAmt" value="${0 }" /> 
			<c:set var="other" value="${0 }" />  
			<c:set var="total" value="${0 }" />
			
			<c:forEach items="${staticlist}" var="staticlist" varStatus="count">
												<tr>
													<td ><c:out value="${count.index+1}" /></td>
													 
													<td width="100" ><c:out value="${staticlist.itemName}" /></td> 
													<td width="100" style="text-align:right"><c:out value="${staticlist.recQty}" /></td>  
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.discAmt}"/>
													</td>
													 <c:set var="discAmt" value="${discAmt + staticlist.discAmt}"/>
													 
													 <td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.freightAmt}"/>
													 </td><c:set var="freightAmt"  value="${freightAmt+staticlist.freightAmt }"/>
													 
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2"  value = "${staticlist.insuAmt}"/> 
													 </td><c:set var="insuranceAmt"  value="${insuranceAmt+staticlist.insuAmt }"/>
													 
													 <td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.taxableAmt}"/>
													 </td> <c:set var="taxableAmount"  value="${taxAmount+staticlist.taxableAmt }"/>
													 
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.cgst}"/>
													 </td> <c:set var="cgst"  value="${cgst+staticlist.cgst }"/>
													 
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.sgst}"/>
													 </td> <c:set var="sgst"  value="${sgst+staticlist.sgst }"/>
													 
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.igst}"/>
													  </td> <c:set var="igst"  value="${igst + staticlist.igst}"/> 
													  
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.cess}"/>
													 </td><c:set var="cess"  value="${cess+staticlist.cess }"/>
													 
													 <td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.otherExtra}"/>
													 </td><c:set var="other"  value="${other+staticlist.otherExtra }"/>
													 
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.total}"/>
													  </td> 
													 
														<c:set var="total"  value="${total+staticlist.total }"/>
													
												</tr>
												</c:forEach>
				<tr>
				
					<td colspan='3'><b>Total</b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${discAmt}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${freightAmt}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${insuranceAmt}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${taxableAmount}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${cgst}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${sgst}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${igst}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${cess}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${other}" /></b></td> 
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${total}" /></b></td>
					
					<!--  <td><b>Total</b></td> -->
				</tr>
		</tbody>
	</table>
	

	<!-- END Main Content -->

</body>
</html>