package com.inventory.project.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.inventory.project.common.Constants;
import com.inventory.project.model.BillDetail;
import com.inventory.project.model.BillHeader;
import com.inventory.project.model.ExportToExcel;


@Controller
@Scope("session")
public class ReportController {
	
	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
				
		return cal.getTime();
	}
	@RequestMapping(value = "/saleReportGroupByDate", method = RequestMethod.GET)
	public ModelAndView saleReportGroupByDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/report/saleGroupByDate");
		model.addObject("isexcel",0);
		return model;
	}
	@RequestMapping(value = "/saleUnpaidReport", method = RequestMethod.GET)
	public ModelAndView saleUnpaidReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/report/unpaidSaleBills");
		try {
		RestTemplate rest = new RestTemplate();
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		BillHeader[] billHeaderList = rest.getForObject(Constants.url + "/report/findUnpaidBills",BillHeader[].class);
		ArrayList<BillHeader> billHeaderListRes = new ArrayList<BillHeader>(Arrays.asList(billHeaderList));
		System.out.println("billHeaderListRes " + billHeaderListRes);
		SimpleDateFormat sf=new SimpleDateFormat("dd-MM-yyyy");
		 
		Date currentDate = new Date();
		Date cDate=sf.parse(sf.format(currentDate));
	        
		List<BillHeader>  unPaidBills=new ArrayList<BillHeader>();
		List<BillHeader>  unPaidBillsExpGretor=new ArrayList<BillHeader>();
		List<BillHeader>  unPaidBillsBlocked=new ArrayList<BillHeader>();
		for(int i=0;i<billHeaderListRes.size();i++){
			
			Date invDate=sf.parse(billHeaderListRes.get(i).getInvoiceDate());
			Date expDate=sf.parse(billHeaderListRes.get(i).getExpiryDate());
			Date invDatePlus30=addDays(invDate,30);
			
			if(cDate.compareTo(invDatePlus30)>0)
			{
				unPaidBillsBlocked.add(billHeaderListRes.get(i));
				
			}else if(cDate.compareTo(expDate)>0)
			{
				unPaidBillsExpGretor.add(billHeaderListRes.get(i));
			}
			else {
				
				unPaidBills.add(billHeaderListRes.get(i));
			}
			
			
		}
		model.addObject("unPaidBills",unPaidBills);
		model.addObject("unPaidBillsExpGretor",unPaidBillsExpGretor);
		model.addObject("unPaidBillsBlocked",unPaidBillsBlocked);
        System.err.println(unPaidBills.toString());
        System.err.println(unPaidBillsExpGretor.toString());
        System.err.println(unPaidBillsBlocked.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	@RequestMapping(value = "/saleReportByDate", method = RequestMethod.GET)
	public ModelAndView saleReportByDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/report/saleDateWise");
		model.addObject("isexcel",0);
		return model;
	}
	@RequestMapping(value = "/saleByMonthWise", method = RequestMethod.GET)
	public ModelAndView saleByMonthWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/report/saleByMonthwise");
		model.addObject("isexcel",0);
		return model;
	}
	@RequestMapping(value = "/saleByItemAndHsncodeWise", method = RequestMethod.GET)
	public ModelAndView saleByItemAndHsncodeWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/report/saleItemwise");
		model.addObject("isexcel",0);
		return model;
	}
	@RequestMapping(value = "/searchItemwiseSale", method = RequestMethod.POST)
	public ModelAndView searchItemwiseSale(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/report/saleItemwise");
		
		try {
			RestTemplate rest = new RestTemplate();
			String fromDate=request.getParameter("fromDate");System.err.println(fromDate);
			String toDate=request.getParameter("toDate");System.err.println(toDate);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate",fromDate);
			map.add("toDate",toDate);
			
			BillDetail[] billDetailList = rest.postForObject(Constants.url + "/report/findSaleItemAndHsnCodeWise",map,BillDetail[].class);
			ArrayList<BillDetail> billDetailListRes = new ArrayList<BillDetail>(Arrays.asList(billDetailList));
			System.out.println("billDetailListRes " + billDetailListRes);
			
			
			model.addObject("billDetailList",billDetailListRes);
			model.addObject("fromDate",fromDate);
			model.addObject("toDate",toDate);
			model.addObject("isexcel",1);
			
			//exportToExcel
			List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
				
				ExportToExcel expoExcel=new ExportToExcel();
				List<String> rowData=new ArrayList<String>();
				 
				rowData.add("Sr No");
				rowData.add("Item Name");
				rowData.add("HSN Code");
				rowData.add("Bill Qty");
				rowData.add("Taxable Amt");
				rowData.add("CGST Amt");
				rowData.add("SGST Amt");
				rowData.add("IGST Amt");
				rowData.add("CESS Amt");
				rowData.add("Grand Total");
				
			 
			 
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
			for(int i=0;i<billDetailListRes.size();i++)
				{
					  expoExcel=new ExportToExcel();
					 rowData=new ArrayList<String>();
					 
					rowData.add(""+(i+1));
					rowData.add(billDetailListRes.get(i).getItemName());
					rowData.add(""+billDetailListRes.get(i).getItemHsncd());
			
					rowData.add(""+billDetailListRes.get(i).getBillQty());
					rowData.add(""+billDetailListRes.get(i).getTaxableAmt());
					
					rowData.add(""+billDetailListRes.get(i).getCgstRs());
					rowData.add(""+billDetailListRes.get(i).getSgstRs());
					rowData.add(""+billDetailListRes.get(i).getIgstRs());
					rowData.add(""+billDetailListRes.get(i).getCessRs());

					rowData.add(""+billDetailListRes.get(i).getGrandTotal());
					
					 
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					 
				}
				 
				
				
				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "SaleBillWiseGroupByDate");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}

@RequestMapping(value = "/searchByDate", method = RequestMethod.POST)
public ModelAndView searchByDate(HttpServletRequest request, HttpServletResponse response) {

	ModelAndView model = new ModelAndView("bill/report/saleDateWise");
	
	try {
		RestTemplate rest = new RestTemplate();
		String fromDate=request.getParameter("fromDate");System.err.println(fromDate);
		String toDate=request.getParameter("toDate");System.err.println(toDate);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("fromDate",fromDate);
		map.add("toDate",toDate);
		
		BillHeader[] billHeaderList = rest.postForObject(Constants.url + "/bill/findBillsBetweenDate",map,BillHeader[].class);
		ArrayList<BillHeader> billHeaderListRes = new ArrayList<BillHeader>(Arrays.asList(billHeaderList));
		System.out.println("billHeaderListRes " + billHeaderListRes);
		
		
		model.addObject("billHeaderList",billHeaderListRes);
		model.addObject("fromDate",fromDate);
		model.addObject("toDate",toDate);
		model.addObject("isexcel",1);
		
		//exportToExcel
		List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
			
			ExportToExcel expoExcel=new ExportToExcel();
			List<String> rowData=new ArrayList<String>();
			 
			rowData.add("Sr No");
			rowData.add("Invoice No");
			rowData.add("Value");
			rowData.add("CGST Amt");
			rowData.add("SGST Amt");
			rowData.add("IGST Amt");
			rowData.add("CESS Amt");
			rowData.add("Grand Total");
			rowData.add("Discount Amt");
			rowData.add("Paid Amt");
			rowData.add("Remaining Amt");
			
		 
		 
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
		for(int i=0;i<billHeaderListRes.size();i++)
			{
				  expoExcel=new ExportToExcel();
				 rowData=new ArrayList<String>();
				 
				rowData.add(""+(i+1));
				rowData.add(billHeaderListRes.get(i).getInvoiceNo());
				rowData.add(""+billHeaderListRes.get(i).getTaxableAmt());
		
				rowData.add(""+billHeaderListRes.get(i).getCgstRs());
				rowData.add(""+billHeaderListRes.get(i).getSgstRs());
				
				rowData.add(""+billHeaderListRes.get(i).getIgstRs());
				rowData.add(""+billHeaderListRes.get(i).getCessRs());
				rowData.add(""+billHeaderListRes.get(i).getGrandTotal());
				rowData.add(""+billHeaderListRes.get(i).getDiscountAmt());

				rowData.add(""+billHeaderListRes.get(i).getPaidAmt());
				rowData.add(""+billHeaderListRes.get(i).getRemAmt());
				
				 
				
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				 
			}
			 
			
			
			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "SaleBillWiseByDate");
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return model;
}

@RequestMapping(value = "/searchGroupByDateSale", method = RequestMethod.POST)
public ModelAndView searchGroupByDateSale(HttpServletRequest request, HttpServletResponse response) {

	ModelAndView model = new ModelAndView("bill/report/saleGroupByDate");
	
	try {
		RestTemplate rest = new RestTemplate();
		String fromDate=request.getParameter("fromDate");System.err.println(fromDate);
		String toDate=request.getParameter("toDate");System.err.println(toDate);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("fromDate",fromDate);
		map.add("toDate",toDate);
		
		BillHeader[] billHeaderList = rest.postForObject(Constants.url + "/report/findSaleGroupByDate",map,BillHeader[].class);
		ArrayList<BillHeader> billHeaderListRes = new ArrayList<BillHeader>(Arrays.asList(billHeaderList));
		System.out.println("billHeaderListRes " + billHeaderListRes);
		
		
		model.addObject("billHeaderList",billHeaderListRes);
		model.addObject("fromDate",fromDate);
		model.addObject("toDate",toDate);
		model.addObject("isexcel",1);
		//exportToExcel
		List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
			
			ExportToExcel expoExcel=new ExportToExcel();
			List<String> rowData=new ArrayList<String>();
			 
			rowData.add("Sr No");
			rowData.add("Invoice Date");
			rowData.add("Value");
			rowData.add("CGST Amt");
			rowData.add("SGST Amt");
			rowData.add("IGST Amt");
			rowData.add("CESS Amt");
			rowData.add("Grand Total");
			rowData.add("Discount Amt");
			rowData.add("Paid Amt");
			rowData.add("Remaining Amt");
			
		 
		 
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
		for(int i=0;i<billHeaderListRes.size();i++)
			{
				  expoExcel=new ExportToExcel();
				 rowData=new ArrayList<String>();
				 
				rowData.add(""+(i+1));
				rowData.add(billHeaderListRes.get(i).getInvoiceDate());
				rowData.add(""+billHeaderListRes.get(i).getTaxableAmt());
		
				rowData.add(""+billHeaderListRes.get(i).getCgstRs());
				rowData.add(""+billHeaderListRes.get(i).getSgstRs());
				
				rowData.add(""+billHeaderListRes.get(i).getIgstRs());
				rowData.add(""+billHeaderListRes.get(i).getCessRs());
				rowData.add(""+billHeaderListRes.get(i).getGrandTotal());
				rowData.add(""+billHeaderListRes.get(i).getDiscountAmt());

				rowData.add(""+billHeaderListRes.get(i).getPaidAmt());
				rowData.add(""+billHeaderListRes.get(i).getRemAmt());
				
				 
				
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				 
			}
			 
			
			
			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "SaleBillWiseGroupByDate");
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return model;
}
@RequestMapping(value = "/searchMonthWiseSale", method = RequestMethod.POST)
public ModelAndView searchMonthWiseSale(HttpServletRequest request, HttpServletResponse response) {

	ModelAndView model = new ModelAndView("bill/report/saleByMonthwise");
	
	try {
		RestTemplate rest = new RestTemplate();
		String fromDate=request.getParameter("fromDate");System.err.println(fromDate);
		String toDate=request.getParameter("toDate");System.err.println(toDate);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("fromDate",fromDate);
		map.add("toDate",toDate);
		
		BillHeader[] billHeaderList = rest.postForObject(Constants.url + "/report/findSaleGroupByMonth",map,BillHeader[].class);
		ArrayList<BillHeader> billHeaderListRes = new ArrayList<BillHeader>(Arrays.asList(billHeaderList));
		System.out.println("billHeaderListRes " + billHeaderListRes);
		
		
		model.addObject("billHeaderList",billHeaderListRes);
		model.addObject("fromDate",fromDate);
		model.addObject("toDate",toDate);
		model.addObject("isexcel",1);
		//exportToExcel
				List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
					
					ExportToExcel expoExcel=new ExportToExcel();
					List<String> rowData=new ArrayList<String>();
					 
					rowData.add("Sr No");
					rowData.add("Month");
					rowData.add("Value");
					rowData.add("CGST Amt");
					rowData.add("SGST Amt");
					rowData.add("IGST Amt");
					rowData.add("CESS Amt");
					rowData.add("Grand Total");
					rowData.add("Discount Amt");
					rowData.add("Paid Amt");
					rowData.add("Remaining Amt");
					
				 
				 
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
				for(int i=0;i<billHeaderListRes.size();i++)
					{
						  expoExcel=new ExportToExcel();
						 rowData=new ArrayList<String>();
						 
						rowData.add(""+(i+1));
						rowData.add(billHeaderListRes.get(i).getInvoiceNo());
						rowData.add(""+billHeaderListRes.get(i).getTaxableAmt());
				
						rowData.add(""+billHeaderListRes.get(i).getCgstRs());
						rowData.add(""+billHeaderListRes.get(i).getSgstRs());
						
						rowData.add(""+billHeaderListRes.get(i).getIgstRs());
						rowData.add(""+billHeaderListRes.get(i).getCessRs());
						rowData.add(""+billHeaderListRes.get(i).getGrandTotal());
						rowData.add(""+billHeaderListRes.get(i).getDiscountAmt());

						rowData.add(""+billHeaderListRes.get(i).getPaidAmt());
						rowData.add(""+billHeaderListRes.get(i).getRemAmt());
						
						 
						
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);
						 
					}
					 
					
					
					HttpSession session = request.getSession();
					session.setAttribute("exportExcelList", exportToExcelList);
					session.setAttribute("excelName", "SaleMonthwise");
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return model;
}
   @RequestMapping(value = "/showSaleByDatePdf/{fromDate}/{toDate}", method = RequestMethod.GET)
    public ModelAndView showSaleByDatePdf(@PathVariable String fromDate, @PathVariable String toDate,
		HttpServletRequest request, HttpServletResponse response) {

	    ModelAndView model = new ModelAndView("bill/report/pdf/saleByDatePdf");
	    try {
			RestTemplate rest = new RestTemplate();

	    	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate",fromDate);
			map.add("toDate",toDate);
			
			BillHeader[] billHeaderList = rest.postForObject(Constants.url + "/bill/findBillsBetweenDate",map,BillHeader[].class);
			ArrayList<BillHeader> billHeaderListRes = new ArrayList<BillHeader>(Arrays.asList(billHeaderList));
			System.out.println("billHeaderListRes " + billHeaderListRes);
			
			model.addObject("reportName","By Date");
			model.addObject("report",billHeaderListRes);
			model.addObject("fromDate",fromDate);
			model.addObject("toDate",toDate);
	    	
	    }catch (Exception e) {
			e.printStackTrace();
		}
	   return model;
     }
@RequestMapping(value = "/showSaleGroupByDatePdf/{fromDate}/{toDate}", method = RequestMethod.GET)
public ModelAndView showSaleGroupByDatePdf(@PathVariable String fromDate, @PathVariable String toDate,
	HttpServletRequest request, HttpServletResponse response) {

    ModelAndView model = new ModelAndView("bill/report/pdf/saleByDatePdf");
    try {
		RestTemplate rest = new RestTemplate();

    	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("fromDate",fromDate);
		map.add("toDate",toDate);
		
		BillHeader[] billHeaderList = rest.postForObject(Constants.url + "/report/findSaleGroupByDate",map,BillHeader[].class);
		ArrayList<BillHeader> billHeaderListRes = new ArrayList<BillHeader>(Arrays.asList(billHeaderList));
		
		model.addObject("reportName","Group By Date");
		model.addObject("report",billHeaderListRes);
		model.addObject("fromDate",fromDate);
		model.addObject("toDate",toDate);
    	
    }catch (Exception e) {
		e.printStackTrace();
	}
   return model;
 }
@RequestMapping(value = "/showSaleByMonthPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
public ModelAndView showSaleByMonthPdf(@PathVariable String fromDate, @PathVariable String toDate,
	HttpServletRequest request, HttpServletResponse response) {

    ModelAndView model = new ModelAndView("bill/report/pdf/saleByMonthwisePdf");
    try {
		RestTemplate rest = new RestTemplate();

    	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("fromDate",fromDate);
		map.add("toDate",toDate);
		
		BillHeader[] billHeaderList = rest.postForObject(Constants.url + "/report/findSaleGroupByMonth",map,BillHeader[].class);
		ArrayList<BillHeader> billHeaderListRes = new ArrayList<BillHeader>(Arrays.asList(billHeaderList));
		System.out.println("billHeaderListRes " + billHeaderListRes);
		
		model.addObject("reportName","Monthwise");
		model.addObject("report",billHeaderListRes);
		model.addObject("fromDate",fromDate);
		model.addObject("toDate",toDate);
    	
    }catch (Exception e) {
		e.printStackTrace();
	}
   return model;
 }
@RequestMapping(value = "/showSaleByItemPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
public ModelAndView showSaleByItemPdf(@PathVariable String fromDate, @PathVariable String toDate,
	HttpServletRequest request, HttpServletResponse response) {

    ModelAndView model = new ModelAndView("bill/report/pdf/saleByItemwisePdf");
    try {
		RestTemplate rest = new RestTemplate();

    	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("fromDate",fromDate);
		map.add("toDate",toDate);
		
		BillDetail[] billDetailList = rest.postForObject(Constants.url + "/report/findSaleItemAndHsnCodeWise",map,BillDetail[].class);
		ArrayList<BillDetail> billDetailListRes = new ArrayList<BillDetail>(Arrays.asList(billDetailList));
		System.out.println("billDetailListRes " + billDetailListRes);
		
		model.addObject("reportName","Item & HSN Code wise");
		model.addObject("report",billDetailListRes);
		model.addObject("fromDate",fromDate);
		model.addObject("toDate",toDate);
    	
    }catch (Exception e) {
		e.printStackTrace();
	}
   return model;
 }
}