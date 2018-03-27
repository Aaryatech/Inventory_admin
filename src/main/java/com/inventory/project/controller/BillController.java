package com.inventory.project.controller;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

import com.inventory.project.common.Constants;
import com.inventory.project.model.BillDetail;
import com.inventory.project.model.BillHeader;
import com.inventory.project.model.BillRecord;
import com.inventory.project.model.CustomerMaster;
import com.inventory.project.model.GetOrder;
import com.inventory.project.model.GetOrderDetail;
import com.inventory.project.model.Info;
import com.inventory.project.model.ItemMaster;
import com.inventory.project.model.PurchaseDetail;
import com.inventory.project.model.TSetting;

@Controller
@Scope("session")
public class BillController {
	private List<BillDetail> insertBillList=new ArrayList<BillDetail>();
	ArrayList<PurchaseDetail> batchList;
	@RequestMapping(value = "/bill", method = RequestMethod.GET)
	public ModelAndView directBill(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/directBill");
		try
		{
            RestTemplate rest = new RestTemplate();
            insertBillList=new ArrayList<BillDetail>();
            
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			
			PurchaseDetail[]  purchaseDetailList=rest.getForObject(Constants.url + "/bill/getItemBatches",PurchaseDetail[].class);
			batchList = new ArrayList<PurchaseDetail>(Arrays.asList(purchaseDetailList));
            System.out.println("purchaseDetailList"+batchList.toString());
            
         	 map = new LinkedMultiValueMap<String, Object>();
		     map.add("flag", 0);
		     ItemMaster[] itemMaster = rest.postForObject(Constants.url + "getItemList",map, ItemMaster[].class);
			ArrayList<ItemMaster> itemList = new ArrayList<ItemMaster>(Arrays.asList(itemMaster));
			model.addObject("itemList", itemList);
			
			 map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			CustomerMaster[] customerMaster = rest.postForObject(Constants.url + "getCustomerList",map,
					CustomerMaster[].class); 
			ArrayList<CustomerMaster> customerList = new ArrayList<CustomerMaster>(Arrays.asList(customerMaster)); 
			
			 map = new LinkedMultiValueMap<String, Object>();
			 map.add("key","invoice_no");
             TSetting tsettingRes=rest.postForObject(Constants.url + "/getUniqueSettingValue",map,TSetting.class); 
             
             int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
             int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
             String financiyalYearFrom="";
             String financiyalYearTo="";
             if(CurrentMonth<4)
             {
                 financiyalYearFrom=""+(CurrentYear-1);
                 financiyalYearTo=""+(CurrentYear);
             }
             else
             {
                 financiyalYearFrom=""+(CurrentYear);
                 financiyalYearTo=""+(CurrentYear+1);
             }
             financiyalYearFrom = financiyalYearFrom.substring(2);
             financiyalYearTo = financiyalYearTo.substring(2);
             
             int maxInvLenth=String.valueOf(tsettingRes.getSettingValue()).length();
             maxInvLenth=5-maxInvLenth;
     		 StringBuilder invoiceNo=new StringBuilder(financiyalYearFrom+""+financiyalYearTo);
     		
     		for(int i=0;i<maxInvLenth;i++)
     		{   String j="0";
     		    invoiceNo.append(j);
     		}
     		invoiceNo.append(String.valueOf(tsettingRes.getSettingValue()));
     		 System.out.println("invoiceNo"+invoiceNo);
     		 model.addObject("settingValue", tsettingRes.getSettingValue());
            model.addObject("invoiceNo", invoiceNo);
            model.addObject("tsettingRes", tsettingRes); 
			model.addObject("customerList",customerList); 		
			model.addObject("currentDate", date);
		}
        catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	@RequestMapping(value = "/calculateDiscount", method = RequestMethod.GET)
	public @ResponseBody List<BillDetail> calculateDiscount(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			System.out.println("hi********************");
		float discountPer=Float.parseFloat(request.getParameter("discount"));
		int isSameState=Integer.parseInt(request.getParameter("isSameState"));
		for(int i=0;i<insertBillList.size();i++)
		{
			float cgstRs=0.0f;
			float sgstRs=0.0f;
			float igstRs=0.0f;
			float cessRs=0.0f;
			float totalTax=0.0f;
			float discountAmt=0.0f;
			float taxableAmt=insertBillList.get(i).getValue();
			System.err.println(taxableAmt);
			discountAmt=(taxableAmt*discountPer)/100;
			taxableAmt=taxableAmt-discountAmt;
			taxableAmt = roundUp(taxableAmt);

			if(isSameState==1)
			{
				cgstRs= (taxableAmt * insertBillList.get(i).getCgstPer()) / 100;
				cgstRs = roundUp(cgstRs);

				sgstRs= (taxableAmt * insertBillList.get(i).getSgstPer()) / 100;
				sgstRs = roundUp(sgstRs);
				
				cessRs= (taxableAmt * insertBillList.get(i).getCessPer()) / 100;
				cessRs = roundUp(cessRs);
				totalTax = sgstRs + cgstRs+cessRs;
			}
			else
			{
				igstRs= (taxableAmt * insertBillList.get(i).getIgstPer()) / 100;
				igstRs = roundUp(igstRs);
				
				totalTax = igstRs+cessRs;
			}
			totalTax = roundUp(totalTax);
			float grandTotal = totalTax + taxableAmt;
			grandTotal = roundUp(grandTotal);
			insertBillList.get(i).setTaxableAmt(taxableAmt);
			insertBillList.get(i).setCgstRs(cgstRs);
			insertBillList.get(i).setSgstRs(sgstRs);
			insertBillList.get(i).setIgstRs(igstRs);
			insertBillList.get(i).setCessRs(cessRs);
			insertBillList.get(i).setTaxAmt(totalTax);
			insertBillList.get(i).setGrandTotal(grandTotal);
			
			
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return insertBillList;
	}
	@RequestMapping(value = "/findBatchList", method = RequestMethod.GET)
	public @ResponseBody List<PurchaseDetail> findItemList(@RequestParam(value = "itemId", required = true) int itemId) {
		List<PurchaseDetail> purchaseDetailList=new ArrayList<PurchaseDetail>();

		for(int j=0;j<batchList.size();j++)
		{
			if(batchList.get(j).getItemId()==itemId)
			{
				purchaseDetailList.add(batchList.get(j));
			}
		}
		System.out.println("purchaseDetailList"+purchaseDetailList.toString());
		return purchaseDetailList;
	}
	
	
	@RequestMapping(value = "/findCustomer", method = RequestMethod.GET)
	public @ResponseBody CustomerMaster findCustomer(HttpServletRequest request, HttpServletResponse response) {
		CustomerMaster customerMaster=new CustomerMaster();
		try {
			int custId = Integer.parseInt(request.getParameter("custId"));
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("custId", custId);
			customerMaster = rest.postForObject(Constants.url + "getCustomerById",map,
					CustomerMaster.class);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
              return customerMaster;
		
	}
	@RequestMapping(value = "/addNewItem", method = RequestMethod.GET)
	public @ResponseBody BillRecord addNewItem(HttpServletRequest request, HttpServletResponse response) {
		
		BillDetail billDetail=new BillDetail();
		BillRecord billRecord=null;
		try {
			int batchKey=0;
		int itemId=Integer.parseInt(request.getParameter("itemId"));
		int isSameState=Integer.parseInt(request.getParameter("isSameState"));
		int custType=Integer.parseInt(request.getParameter("custType"));
		String batchNo=request.getParameter("batchNo");
		int qty=Integer.parseInt(request.getParameter("qty"));
		
		float discount=Float.parseFloat(request.getParameter("discount"));
		
        RestTemplate rest = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("itemId", itemId);
		ItemMaster itemMaster = rest.postForObject(Constants.url + "getItemById",map,
				ItemMaster.class);
		
		PurchaseDetail purchaseDetail = null;
		List<PurchaseDetail> purchaseDetailList=new ArrayList<PurchaseDetail>();
		if(!batchList.isEmpty()) {
			
		for(int i=0;i<batchList.size();i++)
		{
			if(batchList.get(i).getBatchNo().equals(batchNo)&&batchList.get(i).getItemId()==itemId)
			{
				purchaseDetail=batchList.get(i);
				batchKey=i;
			}
		}
		
		}
		float itemRate=0.0f;
		if(custType==1)
		{
			 itemRate=purchaseDetail.getWholesaleRate();
		}
		else if(custType==2)
		{
			itemRate=purchaseDetail.getRetailRate();
		}
		if(purchaseDetail.getBalance()>0)
		{
		if(qty<=purchaseDetail.getBalance())
		{
			billRecord=new BillRecord();
			billRecord.setNewRow(0);
			
			billDetail.setBatchNo(batchNo);
			billDetail.setBillQty(qty);
			billDetail.setBillDetailId(0);
			billDetail.setBillNo(0);
			billDetail.setItemId(itemId);
			billDetail.setItemName(purchaseDetail.getItemName());
			billDetail.setItemHsncd(itemMaster.getHsnCode());
			billDetail.setItemUom(itemMaster.getUomName());
			billDetail.setRate(itemRate);

			float baseRate=0.0f;
			float taxableAmt=0.0f;
			float cgstRs=0.0f;
			float sgstRs=0.0f;
			float igstRs=0.0f;
			float cessRs=0.0f;
			float discountAmt=0.0f;
			float totalTax=0.0f;
			if(isSameState==1)
			{
				System.out.println("HIII");
				baseRate = (itemRate * 100) / (100 + (purchaseDetail.getCgstPer() + purchaseDetail.getSgstPer()+purchaseDetail.getCessPer()));
				System.out.println("HIII"+baseRate);
				baseRate = roundUp(baseRate);
               
				
				taxableAmt=baseRate*qty;
				taxableAmt = roundUp(taxableAmt);
				billDetail.setValue(taxableAmt);
				
				discountAmt=(taxableAmt*discount)/100;
				System.err.println("discountAmt"+discountAmt);
				System.err.println("taxableAmt"+taxableAmt);
				taxableAmt=taxableAmt-discountAmt;
				taxableAmt = roundUp(taxableAmt);
				System.err.println("discountAmt"+discountAmt);
				System.err.println("taxableAmt"+taxableAmt);

				cgstRs= (taxableAmt * purchaseDetail.getCgstPer()) / 100;
				cgstRs = roundUp(cgstRs);

				sgstRs= (taxableAmt * purchaseDetail.getSgstPer()) / 100;
				sgstRs = roundUp(sgstRs);

				cessRs= (taxableAmt * purchaseDetail.getCessPer()) / 100;
				cessRs = roundUp(cessRs);

				totalTax = sgstRs + cgstRs+cessRs;
				totalTax = roundUp(totalTax);
			}
			else if(isSameState==0)
			{
				baseRate = (itemRate * 100) / (100 + (purchaseDetail.getIgstPer()+purchaseDetail.getCessPer()));
				baseRate = roundUp(baseRate);

				taxableAmt=baseRate*qty;
				taxableAmt = roundUp(taxableAmt);
				billDetail.setValue(taxableAmt);
				
				discountAmt=(taxableAmt*discount)/100;
				System.err.println("discountAmt"+discountAmt);
				System.err.println("taxableAmt"+taxableAmt);
				taxableAmt=taxableAmt-discountAmt;
				System.err.println("discountAmt"+discountAmt);
				System.err.println("taxableAmt"+taxableAmt);

				igstRs= (taxableAmt * purchaseDetail.getIgstPer()) / 100;
				igstRs = roundUp(igstRs);

				cessRs= (taxableAmt * purchaseDetail.getCessPer()) / 100;
				cessRs = roundUp(cessRs);

				totalTax = igstRs+cessRs;
				totalTax = roundUp(totalTax);

			}
			billDetail.setBaseRate(baseRate);
			float grandTotal = totalTax + taxableAmt;
			grandTotal = roundUp(grandTotal);
			billDetail.setTaxableAmt(taxableAmt);

			billDetail.setCgstPer(purchaseDetail.getCgstPer());
			
			billDetail.setCgstRs(cgstRs);
			billDetail.setSgstPer(purchaseDetail.getSgstPer());
			billDetail.setSgstRs(sgstRs);
			billDetail.setIgstPer(purchaseDetail.getIgstPer());
			billDetail.setIgstRs(igstRs);
			billDetail.setCessPer(purchaseDetail.getCessPer());
			billDetail.setCessRs(cessRs);
			billDetail.setTaxAmt(totalTax);
			billDetail.setGrandTotal(grandTotal);
			System.out.println("billDetail"+billDetail.toString());
			
			insertBillList.add(billDetail);
			billRecord.setBillDetail(insertBillList);
			billRecord.setPurchaseDetailList(purchaseDetailList);
			System.out.println("billRecord"+billRecord.toString());
			//orderDListRes.get(key).setOrderQty(0);//
			batchList.get(batchKey).setBalance(batchList.get(batchKey).getBalance()-qty);
			batchList.get(batchKey).setSellQty(batchList.get(batchKey).getSellQty()+qty);

		}
		else
		{
			System.err.println("elseeeeeeeee");

			billRecord=new BillRecord();
			billRecord.setNewRow(1);
			
			billDetail.setBatchNo(batchNo);
			billDetail.setBillQty(purchaseDetail.getBalance());
			billDetail.setBillDetailId(0);
			billDetail.setBillNo(0);
			billDetail.setItemId(itemId);
			billDetail.setItemName(purchaseDetail.getItemName());
			billDetail.setItemHsncd(itemMaster.getHsnCode());
			billDetail.setRate(itemRate);
			billDetail.setItemUom(itemMaster.getUomName());
			
			float baseRate=0.0f;
			float taxableAmt=0.0f;
			float cgstRs=0.0f;
			float sgstRs=0.0f;
			float igstRs=0.0f;
			float cessRs=0.0f;
			float totalTax=0.0f;
			float discountAmt=0.0f;
			if(isSameState==1)
			{	System.err.println("elseeeeeeeee3"+itemRate);
				baseRate = (itemRate * 100) / (100 + (purchaseDetail.getCgstPer() + purchaseDetail.getSgstPer()+purchaseDetail.getCessPer()));
				baseRate = roundUp(baseRate);

				taxableAmt=baseRate*purchaseDetail.getBalance();
				taxableAmt = roundUp(taxableAmt);
				
				billDetail.setValue(taxableAmt);
				System.err.println("discountAmt"+discountAmt);
				System.err.println("taxableAmt"+taxableAmt);
				discountAmt=(taxableAmt*discount)/100;
				System.err.println("discountAmt"+discountAmt);
				taxableAmt=taxableAmt-discountAmt;
				taxableAmt = roundUp(taxableAmt);
				System.err.println("taxableAmt"+taxableAmt);

				cgstRs= (taxableAmt * purchaseDetail.getCgstPer()) / 100;
				cgstRs = roundUp(cgstRs);

				sgstRs= (taxableAmt * purchaseDetail.getSgstPer()) / 100;
				sgstRs = roundUp(sgstRs);

				cessRs= (taxableAmt * purchaseDetail.getCessPer()) / 100;
				cessRs = roundUp(cessRs);

				totalTax = sgstRs + cgstRs+cessRs;
				totalTax = roundUp(totalTax);

			}
			else if(isSameState==0)
			{
				baseRate = (itemRate * 100) / (100 + (purchaseDetail.getIgstPer()+purchaseDetail.getCessPer()));
				baseRate = roundUp(baseRate);

				taxableAmt=baseRate*qty;
				taxableAmt = roundUp(taxableAmt);
				billDetail.setValue(taxableAmt);
				
				discountAmt=(taxableAmt*discount)/100;
				System.err.println("discountAmt"+discountAmt);
				System.err.println("taxableAmt"+taxableAmt);
				taxableAmt=taxableAmt-discountAmt;
				taxableAmt = roundUp(taxableAmt);
				System.err.println("discountAmt"+discountAmt);
				System.err.println("taxableAmt"+taxableAmt);

				igstRs= (taxableAmt * purchaseDetail.getIgstPer()) / 100;
				igstRs = roundUp(igstRs);

				cessRs= (taxableAmt * purchaseDetail.getCessPer()) / 100;
				cessRs = roundUp(cessRs);

				totalTax = igstRs+cessRs;
				totalTax = roundUp(totalTax);

			}
			billDetail.setBaseRate(baseRate);

			float grandTotal = totalTax + taxableAmt;
			grandTotal = roundUp(grandTotal);

			billDetail.setTaxableAmt(taxableAmt);
			billDetail.setCgstPer(purchaseDetail.getCgstPer());
			
			billDetail.setCgstRs(cgstRs);
			billDetail.setSgstPer(purchaseDetail.getSgstPer());
			billDetail.setSgstRs(sgstRs);
			billDetail.setIgstPer(purchaseDetail.getIgstPer());
			billDetail.setIgstRs(igstRs);
			billDetail.setCessPer(purchaseDetail.getCessPer());
			billDetail.setCessRs(cessRs);
			billDetail.setTaxAmt(totalTax);
			billDetail.setGrandTotal(grandTotal);
			System.out.println("billDetail"+billDetail.toString());

			
			insertBillList.add(billDetail);
			billRecord.setBillDetail(insertBillList);
			batchList.get(batchKey).setSellQty(batchList.get(batchKey).getSellQty()+batchList.get(batchKey).getBalance());
			batchList.get(batchKey).setBalance(0);
			System.out.println("batchList"+batchList.toString());
		}
		System.out.println("batchList"+batchList.toString());

		}//end of cond gre th 0
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return billRecord;
	}
	public static float roundUp(float d) {
		return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	@RequestMapping(value = "/saveDirectBill", method = RequestMethod.GET)
	public @ResponseBody BillHeader saveDirectBill(HttpServletRequest request, HttpServletResponse response) {
		
		BillHeader   billHeaderRes=null;
		try {
			
			double billGrandTotal;
			double calBillGrandTotal;
			double billTotal;
			String gstNo = "";
			String phoneNo = "";

			String invoiceNo = request.getParameter("invoiceNo");
			System.out.println("invoiceNo:" + invoiceNo);
			
			String invoiceDate = request.getParameter("invoiceDate");
			System.out.println("invoiceDate:" + invoiceDate);
			
			int custId = Integer.parseInt(request.getParameter("custId"));
			System.out.println("Customer id:" + custId);
			
			int custType = Integer.parseInt(request.getParameter("custType"));
			System.out.println("Customer Type:" + custType);
			
			int creditDays = Integer.parseInt(request.getParameter("creditDays"));
			
			String custName = request.getParameter("custName");
			System.out.println("Customer Name:" + custName);
			
			String remark = request.getParameter("remark");
			System.out.println("remark:" + remark);

			gstNo = request.getParameter("gstNo");
			System.out.println("Gst No:" + gstNo);

			phoneNo = request.getParameter("mobile");
			System.out.println("phoneNo:" + phoneNo);

			float discountPer = Float.parseFloat(request.getParameter("discount"));
			System.out.println("discount:" + discountPer);

			//int paymentMode = Integer.parseInt(request.getParameter("paymentMode"));
			//System.out.println("paymentMode:" + paymentMode);

			float paidAmount = Float.parseFloat(request.getParameter("paidAmount"));
			System.out.println("paidAmount:" + paidAmount);

			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = formatter.parse(invoiceDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date); // Now use today date.
			c.add(Calendar.DATE,creditDays); // Adding credit days
			String output = formatter.format(c.getTime());
			System.out.println(output);
			
			BillHeader billHeader=new BillHeader();
			billHeader.setBillNo(0);
			billHeader.setBillStatus(1);//
			billHeader.setCustId(custId);
			billHeader.setCustType(custType);
			billHeader.setCustName(custName);
			billHeader.setGstin(gstNo);
			billHeader.setInvoiceDate(invoiceDate);
			billHeader.setInvoiceNo(invoiceNo);//
		
			billHeader.setBillDetailList(insertBillList);
			float remAmt=0;
			float taxAmt=0;
			float taxableAmt=0;
			float cgstRs=0;
			float sgstRs=0;
			float igstRs=0;
			float cessRs=0;
			float discountAmt=0;
			float grandTotal=0;
			for(int i=0;i<insertBillList.size();i++)
			{
				grandTotal=grandTotal+insertBillList.get(i).getGrandTotal();
				taxAmt=taxAmt+insertBillList.get(i).getTaxAmt();
				cgstRs=cgstRs+insertBillList.get(i).getCgstRs();
				sgstRs=sgstRs+insertBillList.get(i).getSgstRs();
				igstRs=igstRs+insertBillList.get(i).getIgstRs();
				cessRs=cessRs+insertBillList.get(i).getCessRs();
				taxableAmt=taxableAmt+insertBillList.get(i).getTaxableAmt();
				
			}
			
			//discountAmt=grandTotal*discountPer/100;
			//grandTotal=grandTotal-discountAmt;
			
			remAmt=grandTotal-paidAmount;
			billHeader.setPaidAmt(paidAmount);
			billHeader.setRemAmt(remAmt);
			billHeader.setRemark(remark);
			billHeader.setTaxableAmt(taxableAmt);
			billHeader.setTaxAmt(taxAmt);
			billHeader.setIgstRs(igstRs);
			billHeader.setCgstRs(cgstRs);
			billHeader.setCessRs(cessRs);
			billHeader.setSgstRs(sgstRs);
			billHeader.setDiscountAmt(discountAmt);
			billHeader.setExpiryDate(formatter.format(c.getTime()));//
			billHeader.setGrandTotal(grandTotal);
			
            RestTemplate rest = new RestTemplate();

            billHeaderRes = rest.postForObject(Constants.url + "/bill/insertBill", billHeader,BillHeader.class);
            System.out.println("billHeaderResbillHeaderResbillHeaderRes"+billHeaderRes.toString());
            if(billHeaderRes.getBillNo()!=0)
            {
            	insertBillList=new ArrayList<BillDetail>();
            	System.out.println("batchList"+batchList.toString());
            	Info info=rest.postForObject(Constants.url + "/bill/postPurchaseDetails", batchList,Info.class);
            	
            	
            }
            System.out.println("Invoice:"+billHeaderRes.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return billHeaderRes;
		
	}
	@RequestMapping(value = "/bills", method = RequestMethod.GET)
	public ModelAndView showAddSupplier(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/invoiceList");
		try
		{
			RestTemplate rest = new RestTemplate();
			 String pattern = "dd-MM-yyyy";
			String dateInString =new SimpleDateFormat(pattern).format(new Date());
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate",dateInString);
			map.add("toDate",dateInString);
			BillHeader[] billHeaderRes = rest.postForObject(Constants.url + "/bill/findBillsBetweenDate",map,BillHeader[].class);
			ArrayList<BillHeader> billHeaders = new ArrayList<BillHeader>(Arrays.asList(billHeaderRes));
			model.addObject("billHeaderList",billHeaders);
			model.addObject("fromDate",dateInString);
			model.addObject("toDate",dateInString);
		}
        catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	@RequestMapping(value = "/searchBills", method = RequestMethod.POST)
	public ModelAndView searchOrders(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/invoiceList");
		
		try {
			RestTemplate rest = new RestTemplate();
			String fromDate=request.getParameter("fromDate");
			String toDate=request.getParameter("toDate");
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate",fromDate);
			map.add("toDate",toDate);
			
			BillHeader[] billHeaderRes = rest.postForObject(Constants.url + "/bill/findBillsBetweenDate",map,BillHeader[].class);
			ArrayList<BillHeader> billHeaders = new ArrayList<BillHeader>(Arrays.asList(billHeaderRes));
			model.addObject("billHeaderList",billHeaders);
			model.addObject("fromDate",fromDate);
			model.addObject("toDate",toDate);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}
	@RequestMapping(value = "/billDetails/{billNo}", method = RequestMethod.GET)
	public ModelAndView showAddSupplier(@PathVariable("billNo") int billNo,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/invoiceDetails");
		try
		{
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("billNo",billNo);
			BillDetail[] billDetailRes = rest.postForObject(Constants.url + "/bill/findBillDetailByBillNo",map,BillDetail[].class);
			ArrayList<BillDetail> billDetailList = new ArrayList<BillDetail>(Arrays.asList(billDetailRes));
			model.addObject("billDetailList",billDetailList);
		
		}
        catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	@RequestMapping(value = "/showBillPdf/{billNo}", method = RequestMethod.GET)
	public ModelAndView showBillPdf(@PathVariable("billNo")int billNo,HttpServletRequest request, HttpServletResponse response) {
      System.out.println("IN Show bill PDF Method :/showBillPdf");
		ModelAndView model = new ModelAndView("bill/billPdf");
   		
   		try {
   			 
   			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("billNo",billNo);
			BillHeader billHeaderResponse = rest.postForObject(Constants.url + "/bill/findBillByBillNo",map,BillHeader.class);
   			model.addObject("billHeaderRes", billHeaderResponse);
   			System.out.println("billHeaderRes"+billHeaderResponse.toString());
   		}
   		catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	@RequestMapping(value = "pdf/showBillsPdf/{billNos}", method = RequestMethod.GET)
	public ModelAndView showBillsPdf(@PathVariable("billNos")String[] billNos,HttpServletRequest request, HttpServletResponse response) {
      System.out.println("IN Show bill PDF Method :/showBillPdf");
		ModelAndView model = new ModelAndView("bill/allBillPdf");
   		
   		try {
   			RestTemplate rest = new RestTemplate();
			String strBillNos=new String();
			for(int i=0;i<billNos.length;i++)
			{
				strBillNos=strBillNos+","+billNos[i];
			}
			strBillNos=strBillNos.substring(1);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("billNos",strBillNos);
			BillHeader[] billHeaderRes = rest.postForObject(Constants.url + "/bill/findBillsByBillNo",map,BillHeader[].class);
			ArrayList<BillHeader> billHeaders = new ArrayList<BillHeader>(Arrays.asList(billHeaderRes));
   			
   		model.addObject("billHeaderList", billHeaders);
   		}
   		catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	@RequestMapping(value = "/deleteItemDetails", method = RequestMethod.GET)
	public @ResponseBody BillRecord deleteItemDetails(HttpServletRequest request, HttpServletResponse response) {
		BillDetail billDetail=new BillDetail();
		BillRecord billRecord=null;
		try {
			billRecord=new BillRecord();
			String batchNo=request.getParameter("batchNo");
			int itemId=Integer.parseInt(request.getParameter("itemId"));
			int billQty=Integer.parseInt(request.getParameter("billQty"));
			
			int billDetailKey=Integer.parseInt(request.getParameter("key"));
		   
			BillDetail billDetailRes=insertBillList.get(billDetailKey);
		   if(!batchList.isEmpty()) {
				
				for(int i=0;i<batchList.size();i++)
				{
					if(batchList.get(i).getBatchNo().equals(billDetailRes.getBatchNo())&&batchList.get(i).getItemId()==itemId)
					{
						batchList.get(i).setBalance(batchList.get(i).getBalance()+billQty);
						batchList.get(i).setSellQty(batchList.get(i).getSellQty()-billQty);
					System.out.println("hiiiiiii");
					}
				}
				
				}		
			insertBillList.remove(billDetailKey);
			billRecord.setBillDetail(insertBillList);
			System.out.println("Batch List"+batchList.toString());

		}catch (Exception e) {
			e.printStackTrace();
		}
		return billRecord;
	}
	
	private Dimension format = PD4Constants.A4;
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

	@RequestMapping(value = "/pdf", method = RequestMethod.GET)
	public void showPDF(HttpServletRequest request, HttpServletResponse response) {

		String url = request.getParameter("url");
		 System.out.println("URL "+url);
		// http://monginis.ap-south-1.elasticbeanstalk.com
		File f = new File("/home/ats-12/pdf/report.pdf");
           System.out.println("I am here "+f.toString());
		try {
			runConverter(Constants.ReportURL + url, f,request,response);
			System.out.println("Come on lets get ");
		} catch (IOException e) {
			// TODO Auto-generated catch block

			System.out.println("Pdf conversion exception " + e.getMessage());
		}

		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		String filename = "ordermemo221.pdf";
		//String filePath = "/opt/tomcat-latest/webapps/uploads/bill.pdf";
		String filePath = "/home/ats-12/pdf/report.pdf";
		// construct the complete absolute path of the file
		String fullPath = appPath + filePath;
		File downloadFile = new File(filePath);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(downloadFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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

			// get output stream of the response
			OutputStream outStream;

			outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream

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
				pd4ml.setPageSize(landscapeValue ? pd4ml.changePageOrientation(format) : format);
			} catch (Exception e) {
				System.out.println("Pdf conversion ethod excep " + e.getMessage());
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
