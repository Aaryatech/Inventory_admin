package com.inventory.project.controller;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4PageMark;

import com.inventory.project.common.Constants;
import com.inventory.project.common.DateConvertor;
import com.inventory.project.model.ExportToExcel;
import com.inventory.project.model.GrnReport;
import com.inventory.project.model.ItemMaster;
import com.inventory.project.model.ItemReplaceReport;
import com.inventory.project.model.ItemWisePurchase;
import com.inventory.project.model.PurchaseReport;
import com.inventory.project.model.SupplierMaster;

@Controller
public class PurchaseBillReportController {
	
	RestTemplate rest = new RestTemplate();
	
	@RequestMapping(value = "/billWise", method = RequestMethod.GET)
	public ModelAndView billWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchaseReport/billWise"); 
		try
		{
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			SupplierMaster[] supplierMaster = rest.postForObject(Constants.url + "getSuppllierList",map,
					SupplierMaster[].class);
			ArrayList<SupplierMaster> supplierList = new ArrayList<SupplierMaster>(Arrays.asList(supplierMaster));
			System.out.println("supplierList " + supplierList);
			model.addObject("supplierList",supplierList);
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}
	
	
	@RequestMapping(value = "/billWisePurchaseReport", method = RequestMethod.GET)
	public @ResponseBody List<PurchaseReport> billWisePurchaseReport(HttpServletRequest request, HttpServletResponse response) {

		List<PurchaseReport> billWisePurchaseReport = new ArrayList<PurchaseReport>();
 
		try {
			String fromDate = request.getParameter("fromDate");
			String todate = request.getParameter("toDate");
			String[] supplier = request.getParameterValues("suppliers[]");
			System.out.println("fromDate" + fromDate);
			System.out.println("todate" + todate);
			System.out.println("supplier" + supplier.toString());

			StringBuilder sb = new StringBuilder();
			String suppliers = new String();

			if (supplier[0].equals("0")) 
			{
				System.out.println("in if all Supplier");
				suppliers="0";
				 
			} 
			else 
			{
				for (int i = 0; i < supplier.length; i++) 
				{
					sb = sb.append(supplier[i] + ","); 
				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", fromDate);
			map.add("toDate", todate);
			map.add("suppId", suppliers);
		 System.out.println(map);
			
			PurchaseReport[] purchaseReport = rest.postForObject(Constants.url + "/purchaseReport/billwisePurchaseReport", map , PurchaseReport[].class); 
			billWisePurchaseReport = new ArrayList<PurchaseReport>(Arrays.asList(purchaseReport));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//export to excel
	 
		
		List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
		
		ExportToExcel expoExcel=new ExportToExcel();
		List<String> rowData=new ArrayList<String>();
		 
		rowData.add("Sr.No.");  
		rowData.add("Invoice No");
		rowData.add("Invoice Date"); 
		rowData.add("Supplier Name"); 
		rowData.add("Discount Amt");
		rowData.add("Freight Amt");
		rowData.add("Insurance Amt"); 
		rowData.add("Taxable Amt"); 
		rowData.add("cgst"); 
		rowData.add("sgst");
		rowData.add("igst");
		rowData.add("cess");
		rowData.add("Other");
		rowData.add("Bill Amt");
	 
			
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for(int i=0;i<billWisePurchaseReport.size();i++)
		{
			  expoExcel=new ExportToExcel();
			 rowData=new ArrayList<String>();
			 
		 
			 
			 rowData.add(""+(i+1));
			rowData.add(""+billWisePurchaseReport.get(i).getInvoiceNo());
			rowData.add(""+billWisePurchaseReport.get(i).getInvDate()); 
			rowData.add(""+billWisePurchaseReport.get(i).getSuppName());
			rowData.add(""+billWisePurchaseReport.get(i).getDiscAmt());
			rowData.add(""+billWisePurchaseReport.get(i).getFreightAmt());
			rowData.add(""+billWisePurchaseReport.get(i).getInsuranceAmt()); 
			rowData.add(""+billWisePurchaseReport.get(i).getTaxableAmt()); 
			rowData.add(""+billWisePurchaseReport.get(i).getCgst());
			rowData.add(""+billWisePurchaseReport.get(i).getSgst());
			rowData.add(""+billWisePurchaseReport.get(i).getIgst()); 
			rowData.add(""+billWisePurchaseReport.get(i).getCess());
			rowData.add(""+billWisePurchaseReport.get(i).getOtherExtra());
			rowData.add(""+billWisePurchaseReport.get(i).getBillAmt()); 
			
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			 
		}
		 
		
		
		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "BillWisePurchase");
		
		return billWisePurchaseReport;

	}
	
	@RequestMapping(value = "pdf/purchaseBillWisePdf/{fromDate}/{toDate}/{supplier}", method = RequestMethod.GET)
	public ModelAndView purchaseBillWisePdf(@PathVariable String fromDate,@PathVariable String toDate,@PathVariable String supplier[],HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchaseReport/pdf/billwisepdf");
		try {
			List<PurchaseReport> purchaseBillWisePdf = new ArrayList<PurchaseReport>();
 
			StringBuilder sb = new StringBuilder();
			String suppliers = new String();
 
			if (supplier[0].equals("0")) 
			{
				System.out.println("in if All"); 
				suppliers="0";

			} 
			else 
			{
				for (int i = 0; i < supplier.length; i++) {
					sb = sb.append(supplier[i] + ",");

			}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			map.add("suppId", suppliers);
		 
			
			PurchaseReport[] purchaseReport = rest.postForObject(Constants.url + "/purchaseReport/billwisePurchaseReport", map , PurchaseReport[].class); 
			purchaseBillWisePdf = new ArrayList<PurchaseReport>(Arrays.asList(purchaseReport));
			
			model.addObject("staticlist", purchaseBillWisePdf);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			System.out.println("billWisePdf" + purchaseBillWisePdf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/supplierWise", method = RequestMethod.GET)
	public ModelAndView supplierWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchaseReport/supplierWise"); 
		try
		{
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			SupplierMaster[] supplierMaster = rest.postForObject(Constants.url + "getSuppllierList",map,
					SupplierMaster[].class);
			ArrayList<SupplierMaster> supplierList = new ArrayList<SupplierMaster>(Arrays.asList(supplierMaster));
			System.out.println("supplierList " + supplierList);
			model.addObject("supplierList",supplierList);
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/supplierWisePurchaseReport", method = RequestMethod.GET)
	public @ResponseBody List<PurchaseReport> supplierWisePurchaseReport(HttpServletRequest request, HttpServletResponse response) {

		List<PurchaseReport> supplierWisePurchaseReport = new ArrayList<PurchaseReport>();
 
		try {
			String fromDate = request.getParameter("fromDate");
			String todate = request.getParameter("toDate");
			String[] supplier = request.getParameterValues("suppliers[]");
			System.out.println("fromDate" + fromDate);
			System.out.println("todate" + todate);
			System.out.println("supplier" + supplier.toString());

			StringBuilder sb = new StringBuilder();
			String suppliers = new String();

			if (supplier[0].equals("0")) 
			{
				System.out.println("in if all Supplier");
				suppliers="0";
				 
			} 
			else 
			{
				for (int i = 0; i < supplier.length; i++) 
				{
					sb = sb.append(supplier[i] + ","); 
				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", fromDate);
			map.add("toDate", todate);
			map.add("suppId", suppliers);
		 System.out.println(map);
			
			PurchaseReport[] purchaseReport = rest.postForObject(Constants.url + "/purchaseReport/supplierwisePurchaseReport", map , PurchaseReport[].class); 
			supplierWisePurchaseReport = new ArrayList<PurchaseReport>(Arrays.asList(purchaseReport));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//export to excel
	 
		
		List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
		
		ExportToExcel expoExcel=new ExportToExcel();
		List<String> rowData=new ArrayList<String>();
		 
		rowData.add("Sr.No.");   
		rowData.add("Supplier Name"); 
		rowData.add("Discount Amt");
		rowData.add("Freight Amt");
		rowData.add("Insurance Amt"); 
		rowData.add("Taxable Amt"); 
		rowData.add("cgst"); 
		rowData.add("sgst");
		rowData.add("igst");
		rowData.add("cess");
		rowData.add("Other");
		rowData.add("Bill Amt");
	 
			
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for(int i=0;i<supplierWisePurchaseReport.size();i++)
		{
			  expoExcel=new ExportToExcel();
			 rowData=new ArrayList<String>();
			 
		 
			 
			 rowData.add(""+(i+1)); 
			rowData.add(""+supplierWisePurchaseReport.get(i).getSuppName());
			rowData.add(""+supplierWisePurchaseReport.get(i).getDiscAmt());
			rowData.add(""+supplierWisePurchaseReport.get(i).getFreightAmt());
			rowData.add(""+supplierWisePurchaseReport.get(i).getInsuranceAmt()); 
			rowData.add(""+supplierWisePurchaseReport.get(i).getTaxableAmt()); 
			rowData.add(""+supplierWisePurchaseReport.get(i).getCgst());
			rowData.add(""+supplierWisePurchaseReport.get(i).getSgst());
			rowData.add(""+supplierWisePurchaseReport.get(i).getIgst()); 
			rowData.add(""+supplierWisePurchaseReport.get(i).getCess());
			rowData.add(""+supplierWisePurchaseReport.get(i).getOtherExtra());
			rowData.add(""+supplierWisePurchaseReport.get(i).getBillAmt()); 
			
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			 
		}
		 
		
		
		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "supplierWisePurchase");
		
		return supplierWisePurchaseReport;

	}
	
	@RequestMapping(value = "pdf/purchaseSupplierWisePdf/{fromDate}/{toDate}/{supplier}", method = RequestMethod.GET)
	public ModelAndView purchaseSupplierWisePdf(@PathVariable String fromDate,@PathVariable String toDate,@PathVariable String supplier[],HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchaseReport/pdf/supplierwisepdf");
		try {
			List<PurchaseReport> purchaseBillWisePdf = new ArrayList<PurchaseReport>();
 
			StringBuilder sb = new StringBuilder();
			String suppliers = new String();
 
			if (supplier[0].equals("0")) 
			{
				System.out.println("in if All"); 
				suppliers="0";

			} 
			else 
			{
				for (int i = 0; i < supplier.length; i++) {
					sb = sb.append(supplier[i] + ",");

			}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			map.add("suppId", suppliers);
		 
			
			PurchaseReport[] purchaseReport = rest.postForObject(Constants.url + "/purchaseReport/supplierwisePurchaseReport", map , PurchaseReport[].class); 
			purchaseBillWisePdf = new ArrayList<PurchaseReport>(Arrays.asList(purchaseReport));
			
			model.addObject("staticlist", purchaseBillWisePdf);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			System.out.println("billWisePdf" + purchaseBillWisePdf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/dateWise", method = RequestMethod.GET)
	public ModelAndView dateWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchaseReport/dateWise"); 
		try
		{
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			SupplierMaster[] supplierMaster = rest.postForObject(Constants.url + "getSuppllierList",map,
					SupplierMaster[].class);
			ArrayList<SupplierMaster> supplierList = new ArrayList<SupplierMaster>(Arrays.asList(supplierMaster));
			System.out.println("supplierList " + supplierList);
			model.addObject("supplierList",supplierList);
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/dateWisePurchaseReport", method = RequestMethod.GET)
	public @ResponseBody List<PurchaseReport> dateWisePurchaseReport(HttpServletRequest request, HttpServletResponse response) {

		List<PurchaseReport> dateWisePurchaseReport = new ArrayList<PurchaseReport>();
 
		try {
			String fromDate = request.getParameter("fromDate");
			String todate = request.getParameter("toDate");
			String[] supplier = request.getParameterValues("suppliers[]");
			System.out.println("fromDate" + fromDate);
			System.out.println("todate" + todate);
			System.out.println("supplier" + supplier.toString());

			StringBuilder sb = new StringBuilder();
			String suppliers = new String();

			if (supplier[0].equals("0")) 
			{
				System.out.println("in if all Supplier");
				suppliers="0";
				 
			} 
			else 
			{
				for (int i = 0; i < supplier.length; i++) 
				{
					sb = sb.append(supplier[i] + ","); 
				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", fromDate);
			map.add("toDate", todate);
			map.add("suppId", suppliers);
		 System.out.println(map);
			
			PurchaseReport[] purchaseReport = rest.postForObject(Constants.url + "/purchaseReport/datewisePurchaseReport", map , PurchaseReport[].class); 
			dateWisePurchaseReport = new ArrayList<PurchaseReport>(Arrays.asList(purchaseReport));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//export to excel
	 
		
		List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
		
		ExportToExcel expoExcel=new ExportToExcel();
		List<String> rowData=new ArrayList<String>();
		 
		rowData.add("Sr.No."); 
		rowData.add("Invoice Date");  
		rowData.add("Discount Amt");
		rowData.add("Freight Amt");
		rowData.add("Insurance Amt"); 
		rowData.add("Taxable Amt"); 
		rowData.add("cgst"); 
		rowData.add("sgst");
		rowData.add("igst");
		rowData.add("cess");
		rowData.add("Other");
		rowData.add("Bill Amt");
	 
			
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for(int i=0;i<dateWisePurchaseReport.size();i++)
		{
			  expoExcel=new ExportToExcel();
			 rowData=new ArrayList<String>();
			 
		 
			 
			 rowData.add(""+(i+1)); 
			rowData.add(""+dateWisePurchaseReport.get(i).getInvDate());  
			rowData.add(""+dateWisePurchaseReport.get(i).getDiscAmt());
			rowData.add(""+dateWisePurchaseReport.get(i).getFreightAmt());
			rowData.add(""+dateWisePurchaseReport.get(i).getInsuranceAmt()); 
			rowData.add(""+dateWisePurchaseReport.get(i).getTaxableAmt()); 
			rowData.add(""+dateWisePurchaseReport.get(i).getCgst());
			rowData.add(""+dateWisePurchaseReport.get(i).getSgst());
			rowData.add(""+dateWisePurchaseReport.get(i).getIgst()); 
			rowData.add(""+dateWisePurchaseReport.get(i).getCess());
			rowData.add(""+dateWisePurchaseReport.get(i).getOtherExtra());
			rowData.add(""+dateWisePurchaseReport.get(i).getBillAmt()); 
			
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			 
		}
		 
		
		
		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "dateWisePurchase");
		
		return dateWisePurchaseReport;

	}
	
	@RequestMapping(value = "pdf/purchaseDateWisePdf/{fromDate}/{toDate}/{supplier}", method = RequestMethod.GET)
	public ModelAndView purchaseDateWisePdf(@PathVariable String fromDate,@PathVariable String toDate,@PathVariable String supplier[],HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchaseReport/pdf/datewisepdf");
		try {
			List<PurchaseReport> purchaseBillWisePdf = new ArrayList<PurchaseReport>();
 
			StringBuilder sb = new StringBuilder();
			String suppliers = new String();
 
			if (supplier[0].equals("0")) 
			{
				System.out.println("in if All"); 
				suppliers="0";

			} 
			else 
			{
				for (int i = 0; i < supplier.length; i++) {
					sb = sb.append(supplier[i] + ",");

			}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			map.add("suppId", suppliers);
		 
			
			PurchaseReport[] purchaseReport = rest.postForObject(Constants.url + "/purchaseReport/datewisePurchaseReport", map , PurchaseReport[].class); 
			purchaseBillWisePdf = new ArrayList<PurchaseReport>(Arrays.asList(purchaseReport));
			
			model.addObject("staticlist", purchaseBillWisePdf);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			System.out.println("billWisePdf" + purchaseBillWisePdf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/monthWise", method = RequestMethod.GET)
	public ModelAndView monthWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchaseReport/monthWise"); 
		try
		{
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			SupplierMaster[] supplierMaster = rest.postForObject(Constants.url + "getSuppllierList",map,
					SupplierMaster[].class);
			ArrayList<SupplierMaster> supplierList = new ArrayList<SupplierMaster>(Arrays.asList(supplierMaster));
			System.out.println("supplierList " + supplierList);
			model.addObject("supplierList",supplierList);
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/monthPurchaseReport", method = RequestMethod.GET)
	public @ResponseBody List<PurchaseReport> monthPurchaseReport(HttpServletRequest request, HttpServletResponse response) {

		List<PurchaseReport> monthPurchaseReport = new ArrayList<PurchaseReport>();
 
		try {
			String fromDate = request.getParameter("fromDate");
			String todate = request.getParameter("toDate");
			String[] supplier = request.getParameterValues("suppliers[]");
			System.out.println("fromDate" + fromDate);
			System.out.println("todate" + todate);
			System.out.println("supplier" + supplier.toString());

			StringBuilder sb = new StringBuilder();
			String suppliers = new String();

			if (supplier[0].equals("0")) 
			{
				System.out.println("in if all Supplier");
				suppliers="0";
				 
			} 
			else 
			{
				for (int i = 0; i < supplier.length; i++) 
				{
					sb = sb.append(supplier[i] + ","); 
				}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", fromDate);
			map.add("toDate", todate);
			map.add("suppId", suppliers);
		 System.out.println(map);
			
			PurchaseReport[] purchaseReport = rest.postForObject(Constants.url + "/purchaseReport/monthwisePurchaseReport", map , PurchaseReport[].class); 
			monthPurchaseReport = new ArrayList<PurchaseReport>(Arrays.asList(purchaseReport));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//export to excel
	 
		
		List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
		
		ExportToExcel expoExcel=new ExportToExcel();
		List<String> rowData=new ArrayList<String>();
		 
		rowData.add("Sr.No."); 
		rowData.add("Month");  
		rowData.add("Discount Amt");
		rowData.add("Freight Amt");
		rowData.add("Insurance Amt"); 
		rowData.add("Taxable Amt"); 
		rowData.add("cgst"); 
		rowData.add("sgst");
		rowData.add("igst");
		rowData.add("cess");
		rowData.add("Other");
		rowData.add("Bill Amt");
	 
			
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for(int i=0;i<monthPurchaseReport.size();i++)
		{
			  expoExcel=new ExportToExcel();
			 rowData=new ArrayList<String>();
			 
		 
			 
			 rowData.add(""+(i+1)); 
			rowData.add(""+monthPurchaseReport.get(i).getInvoiceNo());  
			rowData.add(""+monthPurchaseReport.get(i).getDiscAmt());
			rowData.add(""+monthPurchaseReport.get(i).getFreightAmt());
			rowData.add(""+monthPurchaseReport.get(i).getInsuranceAmt()); 
			rowData.add(""+monthPurchaseReport.get(i).getTaxableAmt()); 
			rowData.add(""+monthPurchaseReport.get(i).getCgst());
			rowData.add(""+monthPurchaseReport.get(i).getSgst());
			rowData.add(""+monthPurchaseReport.get(i).getIgst()); 
			rowData.add(""+monthPurchaseReport.get(i).getCess());
			rowData.add(""+monthPurchaseReport.get(i).getOtherExtra());
			rowData.add(""+monthPurchaseReport.get(i).getBillAmt()); 
			
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			 
		}
		 
		
		
		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "monthWisePurchase");
		
		return monthPurchaseReport;

	}
	
	@RequestMapping(value = "pdf/purchaseMonthWisePdf/{fromDate}/{toDate}/{supplier}", method = RequestMethod.GET)
	public ModelAndView purchaseMonthWisePdf(@PathVariable String fromDate,@PathVariable String toDate,@PathVariable String supplier[],HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchaseReport/pdf/monthwisepdf");
		try {
			List<PurchaseReport> purchaseBillWisePdf = new ArrayList<PurchaseReport>();
 
			StringBuilder sb = new StringBuilder();
			String suppliers = new String();
 
			if (supplier[0].equals("0")) 
			{
				System.out.println("in if All"); 
				suppliers="0";

			} 
			else 
			{
				for (int i = 0; i < supplier.length; i++) {
					sb = sb.append(supplier[i] + ",");

			}

				suppliers = sb.toString();
				suppliers = suppliers.substring(0, suppliers.length() - 1);
				System.out.println("suppliers id list is" + suppliers.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			map.add("suppId", suppliers);
		 
			
			PurchaseReport[] purchaseReport = rest.postForObject(Constants.url + "/purchaseReport/monthwisePurchaseReport", map , PurchaseReport[].class); 
			purchaseBillWisePdf = new ArrayList<PurchaseReport>(Arrays.asList(purchaseReport));
			
			model.addObject("staticlist", purchaseBillWisePdf);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			System.out.println("billWisePdf" + purchaseBillWisePdf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/itemWise", method = RequestMethod.GET)
	public ModelAndView itemWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchaseReport/itemWise"); 
		try
		{
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0); 
			ItemMaster[] itemMaster = rest.postForObject(Constants.url + "getItemList",map, ItemMaster[].class);
			ArrayList<ItemMaster> itemList = new ArrayList<ItemMaster>(Arrays.asList(itemMaster));
			model.addObject("itemList",itemList);
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/itemPurchaseReport", method = RequestMethod.GET)
	public @ResponseBody List<ItemWisePurchase> itemPurchaseReport(HttpServletRequest request, HttpServletResponse response) {

		List<ItemWisePurchase> itemPurchaseReport = new ArrayList<ItemWisePurchase>();
 
		try {
			String fromDate = request.getParameter("fromDate");
			String todate = request.getParameter("toDate");
			String[] item = request.getParameterValues("items[]");
			System.out.println("fromDate" + fromDate);
			System.out.println("todate" + todate);
			System.out.println("item" + item.toString());

			StringBuilder sb = new StringBuilder();
			String items = new String();

			if (item[0].equals("0")) 
			{
				System.out.println("in if all Supplier");
				items="0";
				 
			} 
			else 
			{
				for (int i = 0; i < item.length; i++) 
				{
					sb = sb.append(item[i] + ","); 
				}

				items = sb.toString();
				items = items.substring(0, items.length() - 1);
				System.out.println("items id list is" + items.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", fromDate);
			map.add("toDate", todate);
			map.add("itemId", items);
		 System.out.println(map);
			
		 ItemWisePurchase[] itemWisePurchase = rest.postForObject(Constants.url + "/purchaseReport/itemwisePurchaseReport", map , ItemWisePurchase[].class); 
			itemPurchaseReport = new ArrayList<ItemWisePurchase>(Arrays.asList(itemWisePurchase));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//export to excel
	 
		
		List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
		
		ExportToExcel expoExcel=new ExportToExcel();
		List<String> rowData=new ArrayList<String>();
		 
		rowData.add("Sr.No."); 
		rowData.add("Item Name");  
		rowData.add("Qty");
		rowData.add("Discount Amt");
		rowData.add("Freight Amt");
		rowData.add("Insurance Amt"); 
		rowData.add("Taxable Amt"); 
		rowData.add("cgst"); 
		rowData.add("sgst");
		rowData.add("igst");
		rowData.add("cess");
		rowData.add("Other");
		rowData.add("Bill Amt");
	 
			
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		for(int i=0;i<itemPurchaseReport.size();i++)
		{
			  expoExcel=new ExportToExcel();
			 rowData=new ArrayList<String>();
			 
		 
			 
			 rowData.add(""+(i+1)); 
			rowData.add(""+itemPurchaseReport.get(i).getItemName());  
			rowData.add(""+itemPurchaseReport.get(i).getRecQty());  
			rowData.add(""+itemPurchaseReport.get(i).getDiscAmt());
			rowData.add(""+itemPurchaseReport.get(i).getFreightAmt());
			rowData.add(""+itemPurchaseReport.get(i).getInsuAmt()); 
			rowData.add(""+itemPurchaseReport.get(i).getTaxableAmt()); 
			rowData.add(""+itemPurchaseReport.get(i).getCgst());
			rowData.add(""+itemPurchaseReport.get(i).getSgst());
			rowData.add(""+itemPurchaseReport.get(i).getIgst()); 
			rowData.add(""+itemPurchaseReport.get(i).getCess());
			rowData.add(""+itemPurchaseReport.get(i).getOtherExtra());
			rowData.add(""+itemPurchaseReport.get(i).getTotal()); 
			
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			 
		}
		 
		
		
		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "monthWisePurchase");
		
		return itemPurchaseReport;

	}
	
	@RequestMapping(value = "pdf/purchaseItemWisePdf/{fromDate}/{toDate}/{item}", method = RequestMethod.GET)
	public ModelAndView purchaseItemWisePdf(@PathVariable String fromDate,@PathVariable String toDate,@PathVariable String item[],HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchaseReport/pdf/itemwisepdf");
		try {
			List<ItemWisePurchase> purchaseItemWisePdf = new ArrayList<ItemWisePurchase>();
 
			StringBuilder sb = new StringBuilder();
			String items = new String();
 
			if (item[0].equals("0")) 
			{
				System.out.println("in if All"); 
				items="0";

			} 
			else 
			{
				for (int i = 0; i < item.length; i++) {
					sb = sb.append(item[i] + ",");

			}

				items = sb.toString();
				items = items.substring(0, items.length() - 1);
				System.out.println("suppliers id list is" + items.toString());
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			map.add("itemId", items);
		 
			
			ItemWisePurchase[] itemWisePurchase = rest.postForObject(Constants.url + "/purchaseReport/itemwisePurchaseReport", map , ItemWisePurchase[].class); 
			purchaseItemWisePdf = new ArrayList<ItemWisePurchase>(Arrays.asList(itemWisePurchase));
			
			model.addObject("staticlist", purchaseItemWisePdf);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			System.out.println("purchaseItemWisePdf" + purchaseItemWisePdf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/grnReportItemWise", method = RequestMethod.GET)
	public ModelAndView grnReportItemWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchaseReport/grnReportItemWise"); 
		 
		return model;
	}
	
	@RequestMapping(value = "/grnItemWiseReport", method = RequestMethod.GET)
	public @ResponseBody List<GrnReport> grnItemWiseReport(HttpServletRequest request, HttpServletResponse response) {

		List<GrnReport> grnItemWiseReport = new ArrayList<GrnReport>();
 
		try {
			String fromDate = request.getParameter("fromDate");
			String todate = request.getParameter("toDate");
			int grnType = Integer.parseInt(request.getParameter("grnType"));
			 
			 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(todate));
			map.add("grnType", grnType);
		 System.out.println(map);
			
		 GrnReport[] grnReport = rest.postForObject(Constants.url + "/purchaseReport/grnItemWiseReport", map , GrnReport[].class); 
		 grnItemWiseReport = new ArrayList<GrnReport>(Arrays.asList(grnReport));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 
		return grnItemWiseReport;

	}
	
	@RequestMapping(value = "pdf/grnItemWisePdf/{fromDate}/{toDate}/{grnType}", method = RequestMethod.GET)
	public ModelAndView grnItemWisePdf(@PathVariable String fromDate,@PathVariable String toDate,@PathVariable int grnType,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchaseReport/pdf/grnitemwisepdf");
		try {
			List<GrnReport> grnItemWiseReport = new ArrayList<GrnReport>();
  
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("grnType", grnType);
		 System.out.println(map);
			
		 GrnReport[] grnReport = rest.postForObject(Constants.url + "/purchaseReport/grnItemWiseReport", map , GrnReport[].class); 
		 grnItemWiseReport = new ArrayList<GrnReport>(Arrays.asList(grnReport));
			
			model.addObject("staticlist", grnItemWiseReport);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			model.addObject("grnType", grnType);
			System.out.println("grnItemWiseReport" + grnItemWiseReport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/replaceItemList", method = RequestMethod.GET)
	public ModelAndView replaceItemList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchaseReport/replaceItemList"); 
		List<ItemReplaceReport> grnItemWiseReport = new ArrayList<ItemReplaceReport>();
		try
		{
			ItemReplaceReport[] itemReplaceReport = rest.getForObject(Constants.url + "/purchaseReport/itemReplaceReport", ItemReplaceReport[].class); 
			 grnItemWiseReport = new ArrayList<ItemReplaceReport>(Arrays.asList(itemReplaceReport));
			 model.addObject("grnItemWiseReport",grnItemWiseReport);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return model;
	}
	
	private Dimension format = PD4Constants.A2;
	private boolean landscapeValue = false;
	private int topValue = 8;
	private int leftValue = 0;
	private int rightValue = 0;
	private int bottomValue =8;
	private String unitsValue = "m";
	private String proxyHost = "";
	private int proxyPort = 0;

	private int userSpaceWidth = 750;
	private static int BUFFER_SIZE = 1024;

	@RequestMapping(value = "/purchaseBillReport", method = RequestMethod.GET)
	public void showPDF(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside PDf For Report URL ");
		String url = request.getParameter("url");
		System.out.println("URL " + url);
		
		//File f = new File("/opt/tomcat-latest/webapps/webapi/uploads/Inward.pdf");
		File f = new File("C:/pdf/ordermemo221.pdf");

		try {
			runConverter(Constants.ReportURL + url, f,request,response);
			//runConverter("www.google.com", f,request,response);

		} catch (IOException e) {

			System.out.println("Pdf conversion exception " + e.getMessage());
		}

		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		String filePath = "C:/pdf/ordermemo221.pdf";

		//String filePath = "/opt/tomcat-latest/webapps/webapi/uploads/Inward.pdf";

		// construct the complete absolute path of the file
		String fullPath = appPath + filePath;
		File downloadFile = new File(filePath);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(downloadFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// get MIME type of the file
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				// set to binary type if MIME mapping not found
				mimeType = "application/pdf";
			}
			System.out.println("MIME type: " + mimeType);

			String headerKey = "Content-Disposition";

			// response.addHeader("Content-Disposition", "attachment;filename=report.pdf");
			response.setContentType("application/pdf");

			OutputStream outStream;

			outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;


			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void runConverter(String urlstring, File output, HttpServletRequest request, HttpServletResponse response ) throws IOException {

		if (urlstring.length() > 0) {
			if (!urlstring.startsWith("http://") && !urlstring.startsWith("file:")) {
				urlstring = "http://" + urlstring;
			}
			System.out.println("PDF URL " + urlstring);
			java.io.FileOutputStream fos = new java.io.FileOutputStream(output);

			PD4ML pd4ml = new PD4ML();
		
			try {

				Dimension landscapeA4 = pd4ml.changePageOrientation(PD4Constants.A4);
				pd4ml.setPageSize(landscapeA4);
			
				PD4PageMark footer = new PD4PageMark();  
				
	            footer.setPageNumberTemplate("Page $[page] of $[total]");  
	            footer.setPageNumberAlignment(PD4PageMark.RIGHT_ALIGN);  
	            footer.setFontSize(10);  
	            footer.setAreaHeight(20);     
	            
	            pd4ml.setPageFooter(footer); 
				
			} catch (Exception e) {
				System.out.println("Pdf conversion method excep " + e.getMessage());
			}

			if (unitsValue.equals("mm")) {
				pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));
			} else {
				pd4ml.setPageInsets(new Insets(topValue, leftValue, bottomValue, rightValue));
			}

			pd4ml.setHtmlWidth(userSpaceWidth);

			pd4ml.render(urlstring, fos);
		}
	}
	
	

}
