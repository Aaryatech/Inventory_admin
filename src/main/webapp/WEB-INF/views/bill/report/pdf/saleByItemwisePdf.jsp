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
<title>Sales Report PDF</title>
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
<p align="center">Aurangabad</p>
<div align="center"> <h5> Sales Report-[${reportName}]  &nbsp;&nbsp;&nbsp;&nbsp; From &nbsp; ${fromDate}  &nbsp;To &nbsp; ${toDate}</h5></div>
	<table  align="center" border="1" cellspacing="0" cellpadding="1" 
		id="table_grid" class="table table-bordered">
		<thead>
			<tr class="bgpink">
				<th height="25">Sr.No.</th>
				<th>Item Name</th>
				<th>HSN Code</th>
				<th>Bill Qty</th>
				<th>Taxable Amt</th>
				<th>CGST Amt</th>
			    <th>SGST Amt</th>
				<th>IGST Amt</th>
				<th>CESS Amt</th>
				<th>Grand Total</th>
				
			</tr>
		</thead>
		<tbody>
			<c:set var="taxAmount" value="${0}" />
			<c:set var="total" value="${0 }" />
			<c:set var="igst" value="${0 }" />
			<c:set var="cgst" value="${0 }" />
			<c:set var="sgst" value="${0 }" />
			<c:set var="cess" value="${0 }" />
			<c:set var="billqty" value="${0 }" />
			<c:set var="grandTotal" value="${0 }" />
			
			<c:forEach items="${report}" var="report" varStatus="count">
				<tr>
					<td  width="0"><c:out value="${count.index+1}" /></td>
					<td  width="100"><c:out value="${report.itemName}" /></td>
					<td  width="100" align="right"><c:out value="${report.itemHsncd}" /></td>
					<td  width="100" align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${report.billQty}" /></td>
					<td  width="100" align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${report.taxableAmt}" /></td>
					<td  width="100" align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${report.cgstRs}" /></td>
					<td  width="100" align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${report.sgstRs}" /></td>
					<td  width="100" align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${report.igstRs}" /></td>
					<td  width="100" align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${report.cessRs}" /></td>
				    <c:set var="sgst" value="${sgst + report.sgstRs }" />
				    <c:set var="igst" value="${igst + report.igstRs }" />
					<c:set var="cgst" value="${cgst + report.cgstRs }" />
			        <c:set var="cess" value="${cess + report.cessRs }" />
				    <c:set var="billqty" value="${billqty+report.billQty}" />
					<c:set var="taxAmount" value="${taxAmount + report.taxableAmt}" />
					
					<c:set var="grandTotal" value="${grandTotal+report.grandTotal}" />
					<td width="100" align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${report.grandTotal}" /></td>	
						
				</tr>

			</c:forEach>
				<tr>
					<td colspan='3'><b>Total</b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billqty}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${taxAmount}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${cgst}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${sgst}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${igst}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${cess}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${grandTotal}" /></b></td>
				</tr>
		</tbody>
	</table>
	

	<!-- END Main Content -->

</body>
</html>