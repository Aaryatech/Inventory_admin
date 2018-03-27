package com.inventory.project.controller;

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

import com.inventory.project.common.Constants;
import com.inventory.project.model.BillDetail;
import com.inventory.project.model.BillHeader;
import com.inventory.project.model.BillRecord;
import com.inventory.project.model.GetOrder;
import com.inventory.project.model.GetOrderDetail;
import com.inventory.project.model.Info;
import com.inventory.project.model.ItemMaster;
import com.inventory.project.model.PurchaseDetail;
import com.inventory.project.model.TSetting;

@Controller
@Scope("session")
public class OrderBillController {

	ArrayList<GetOrderDetail> orderDListRes;
	ArrayList<GetOrderDetail> orderDOrignalListRes;
	ArrayList<PurchaseDetail> batchList;
	private List<BillDetail> insertBillList=new ArrayList<BillDetail>();
	GetOrder getOrderRes=new GetOrder();
	//-------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/bill/{orderId}", method = RequestMethod.GET)
	public ModelAndView bill(@PathVariable("orderId") int orderId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/bill");
		try
		{
            RestTemplate rest = new RestTemplate();
            insertBillList=new ArrayList<BillDetail>();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("orderId",orderId);
			getOrderRes=rest.postForObject(Constants.url + "/order/getOrder",map,GetOrder.class);
			
			GetOrderDetail[] orderDetailList = rest.postForObject(Constants.url + "/order/getOrderDetails",map,GetOrderDetail[].class);
			orderDListRes = new ArrayList<GetOrderDetail>(Arrays.asList(orderDetailList));
			orderDOrignalListRes=orderDListRes;
			model.addObject("orderDetailList",orderDListRes);
			model.addObject("orderHeader", getOrderRes);
			String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			
			PurchaseDetail[]  purchaseDetailList=rest.getForObject(Constants.url + "/bill/getItemBatches",PurchaseDetail[].class);
			batchList = new ArrayList<PurchaseDetail>(Arrays.asList(purchaseDetailList));
            System.out.println("purchaseDetailList"+batchList.toString());
            
       	 map = new LinkedMultiValueMap<String, Object>();
		 map.add("flag", 0);
		 ItemMaster[] itemMaster = rest.postForObject(Constants.url + "getItemList",map, ItemMaster[].class);
			ArrayList<ItemMaster> itemList = new ArrayList<ItemMaster>(Arrays.asList(itemMaster));
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
            model.addObject("invoiceNo", invoiceNo);
			model.addObject("itemList", itemList);
			model.addObject("batchList", batchList);
			model.addObject("currentDate", date);
			model.addObject("orderId", orderId);
		}
        catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/calculateOrderDiscount", method = RequestMethod.GET)
	public @ResponseBody List<BillDetail> calculateOrderDiscount(HttpServletRequest request, HttpServletResponse response) {
		
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
			discountAmt=(taxableAmt*discountPer)/100;	System.err.println(discountAmt);
			taxableAmt=taxableAmt-discountAmt;System.err.println(taxableAmt);
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
	//-------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/invoiceCalc", method = RequestMethod.GET)
	public @ResponseBody BillRecord invoiceCalc(@RequestParam(value = "batchNo", required = true) String batchNo,@RequestParam(value = "prevBatchNo", required = true) String prevBatchNo,
			@RequestParam(value = "key", required = true) int key,@RequestParam(value = "discount", required = true) float discount) {
		BillDetail billDetail=new BillDetail();
		BillRecord billRecord=null;
		System.err.println(prevBatchNo);

		try {
		int batchKey=0;
		GetOrderDetail getOrderDetail=orderDListRes.get(key);
	   System.out.println("getOrderDetailgetOrderDetail"+getOrderDetail.toString());
		

		PurchaseDetail purchaseDetail = new PurchaseDetail();
		List<PurchaseDetail> purchaseDetailList=new ArrayList<PurchaseDetail>();
		System.err.println(getOrderRes.getIsSameState());
		int isSameState=getOrderRes.getIsSameState();
		if(!batchList.isEmpty()) {
			
		for(int i=0;i<batchList.size();i++)
		{
			if(batchList.get(i).getBatchNo().equals(batchNo)&&batchList.get(i).getItemId()==getOrderDetail.getItemId())
			{
				purchaseDetail=batchList.get(i);
				batchKey=i;
			}
		}
		
		}
		float itemRate=0.0f;
		if(getOrderRes.getCustType()==1)
		{
			 itemRate=purchaseDetail.getWholesaleRate();
		}
		else if(getOrderRes.getCustType()==2)
		{
			itemRate=purchaseDetail.getRetailRate();
		}
		if(getOrderDetail.getOrderQty()>0&&purchaseDetail.getBalance()>0)
		{
		if(getOrderDetail.getOrderQty()<=purchaseDetail.getBalance())
		{
			billRecord=new BillRecord();
			billRecord.setNewRow(0);
			
			billDetail.setBatchNo(batchNo);
			billDetail.setBillQty(getOrderDetail.getOrderQty());
			billDetail.setBillDetailId(0);
			billDetail.setBillNo(0);
			billDetail.setItemId(getOrderDetail.getItemId());
			billDetail.setItemName(getOrderDetail.getItemName());
			billDetail.setItemUom(getOrderDetail.getUomName());
			billDetail.setItemHsncd(getOrderDetail.getHsnCode());
			billDetail.setRate(itemRate);

			float baseRate=0.0f;
			float taxableAmt=0.0f;
			float cgstRs=0.0f;
			float sgstRs=0.0f;
			float igstRs=0.0f;
			float discountAmt=0.0f;
			float cessRs=0.0f;
			float totalTax=0.0f;
			if(isSameState==1)
			{
				System.out.println("HIII");
				baseRate = (itemRate * 100) / (100 + (purchaseDetail.getCgstPer() + purchaseDetail.getSgstPer()+purchaseDetail.getCessPer()));
				System.out.println("HIII"+baseRate);
				baseRate = roundUp(baseRate);

				taxableAmt=baseRate*getOrderDetail.getOrderQty();
				taxableAmt = roundUp(taxableAmt);
				//
                billDetail.setValue(taxableAmt);
				
				discountAmt=(taxableAmt*discount)/100;
				System.err.println("discountAmt"+discountAmt);
				System.err.println("taxableAmt"+taxableAmt);
				taxableAmt=taxableAmt-discountAmt;
				taxableAmt = roundUp(taxableAmt);
				//
				cgstRs= (taxableAmt * purchaseDetail.getCgstPer()) / 100;
				cgstRs = roundUp(cgstRs);

				sgstRs= (taxableAmt * purchaseDetail.getSgstPer()) / 100;
				sgstRs = roundUp(sgstRs);

				cessRs= (taxableAmt * purchaseDetail.getCessPer()) / 100;
				cessRs = roundUp(cessRs);

				totalTax = sgstRs + cgstRs+cessRs;
				totalTax = roundUp(totalTax);
				System.out.println("batchList.get(batchKey).getBalance()-getOrderDetail.getOrderQty()"+(batchList.get(batchKey).getBalance()+"-getOrderDetail.getOrderQty()"+getOrderDetail.getOrderQty()));

			}
			else if(isSameState==0)
			{
				baseRate = (itemRate * 100) / (100 + (purchaseDetail.getIgstPer()+purchaseDetail.getCessPer()));
				baseRate = roundUp(baseRate);

				taxableAmt=baseRate*getOrderDetail.getOrderQty();
				taxableAmt = roundUp(taxableAmt);
				//
                billDetail.setValue(taxableAmt);
				
				discountAmt=(taxableAmt*discount)/100;
				System.err.println("discountAmt"+discountAmt);
				System.err.println("taxableAmt"+taxableAmt);
				taxableAmt=taxableAmt-discountAmt;
				taxableAmt = roundUp(taxableAmt);
				//
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
			billDetail.setUniqueKey(key);
			System.out.println("billDetail"+billDetail.toString());
			
			insertBillList.add(billDetail);
			billRecord.setBillDetail(insertBillList);
			billRecord.setPurchaseDetailList(purchaseDetailList);
			System.out.println("ifbillRecord"+billRecord.toString());
		
			batchList.get(batchKey).setSellQty(batchList.get(batchKey).getSellQty()+getOrderDetail.getOrderQty());

			batchList.get(batchKey).setBalance(batchList.get(batchKey).getBalance()-getOrderDetail.getOrderQty());
			System.out.println("Batch List"+batchList.toString());
System.out.println("batchList.get(batchKey).getBalance()-getOrderDetail.getOrderQty()"+(batchList.get(batchKey).getBalance()-getOrderDetail.getOrderQty()));
orderDListRes.get(key).setOrderQty(0);//
		
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
			billDetail.setItemId(getOrderDetail.getItemId());
			billDetail.setItemName(getOrderDetail.getItemName());
			billDetail.setItemHsncd(getOrderDetail.getHsnCode());
			billDetail.setRate(itemRate);
			billDetail.setItemUom(getOrderDetail.getUomName());

			float baseRate=0.0f;
			float taxableAmt=0.0f;
			float cgstRs=0.0f;
			float sgstRs=0.0f;
			float discountAmt=0.0f;
			float igstRs=0.0f;
			float cessRs=0.0f;
			float totalTax=0.0f;
			if(getOrderRes.getIsSameState()==1)
			{	System.err.println("elseeeeeeeee3"+itemRate);
				baseRate = (itemRate * 100) / (100 + (purchaseDetail.getCgstPer() + purchaseDetail.getSgstPer()+purchaseDetail.getCessPer()));
				baseRate = roundUp(baseRate);

				taxableAmt=baseRate*purchaseDetail.getBalance();
				taxableAmt = roundUp(taxableAmt);
				//
                billDetail.setValue(taxableAmt);
				
				discountAmt=(taxableAmt*discount)/100;
				System.err.println("discountAmt"+discountAmt);
				System.err.println("taxableAmt"+taxableAmt);
				taxableAmt=taxableAmt-discountAmt;
				taxableAmt = roundUp(taxableAmt);
				//
				cgstRs= (taxableAmt * purchaseDetail.getCgstPer()) / 100;
				cgstRs = roundUp(cgstRs);

				sgstRs= (taxableAmt * purchaseDetail.getSgstPer()) / 100;
				sgstRs = roundUp(sgstRs);

				cessRs= (taxableAmt * purchaseDetail.getCessPer()) / 100;
				cessRs = roundUp(cessRs);

				totalTax = sgstRs + cgstRs+cessRs;
				totalTax = roundUp(totalTax);

			}
			else if(getOrderRes.getIsSameState()==0)
			{
				baseRate = (itemRate * 100) / (100 + (purchaseDetail.getIgstPer()+purchaseDetail.getCessPer()));
				baseRate = roundUp(baseRate);

				taxableAmt=baseRate*getOrderDetail.getOrderQty();
				taxableAmt = roundUp(taxableAmt);
				//
                billDetail.setValue(taxableAmt);
				
				discountAmt=(taxableAmt*discount)/100;
				System.err.println("discountAmt"+discountAmt);
				System.err.println("taxableAmt"+taxableAmt);
				taxableAmt=taxableAmt-discountAmt;
				taxableAmt = roundUp(taxableAmt);
				//
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
			billDetail.setUniqueKey(key);//
			System.out.println("billDetail"+billDetail.toString());

			
			insertBillList.add(billDetail);
			billRecord.setBillDetail(insertBillList);
			for(int j=0;j<batchList.size();j++)
			{
				if(batchList.get(j).getItemId()==getOrderDetail.getItemId())
				{
					purchaseDetailList.add(batchList.get(j));
				}
			}
			billRecord.setPurchaseDetailList(purchaseDetailList);
			System.out.println("elsebillRecord1"+billRecord.toString());
		   
			batchList.get(batchKey).setSellQty(batchList.get(batchKey).getSellQty()+batchList.get(batchKey).getBalance());

		    orderDListRes.get(key).setOrderQty(orderDListRes.get(key).getOrderQty()-purchaseDetail.getBalance());
		    batchList.get(batchKey).setBalance(0);
			

			System.out.println("Batch List"+batchList.toString()+"hh"+(batchList.get(batchKey).getSellQty()+""+batchList.get(batchKey).getBalance()));
			System.out.println("orderDListRes.get(key).getOrderQty()-purchaseDetail.getBalance()"+(orderDListRes.get(key).getOrderQty()-purchaseDetail.getBalance()));

		}
		
	}
		else //order qty is 0
		{
			billRecord=new BillRecord();
			if(getOrderDetail.getOrderQty()>0)
			{
				billRecord.setNewRow(1);
			}
			else
			{
				billRecord.setNewRow(0);
			}
			billRecord.setBalOver(1);
			billRecord.setBillDetail(insertBillList);
			
		}
		//insertBillList.add(billDetail);//added to insert
		//insertBillList.add(key, billDetail);
		float total=0.0f;
		System.out.println("insertBillList************"+insertBillList.toString());
		System.out.println("Bill************"+billRecord.toString());
		billRecord.setUniqueKey(key);
		/*for(int j=0;j<insertBillList.size();j++)
		{
			 total=total+insertBillList.get(j).getGrandTotal();
		}
		billRecord.setTotal(total);*/
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return billRecord;
	}
	/*@RequestMapping(value = "/findBatchList", method = RequestMethod.GET)
	public @ResponseBody List<PurchaseDetail> findItemList(@RequestParam(value = "itemId", required = true) int itemId) {
		List<PurchaseDetail> purchaseDetailList=new ArrayList<PurchaseDetail>();

		for(int j=0;j<batchList.size();j++)
		{
			if(batchList.get(j).getItemId()==itemId)
			{
				purchaseDetailList.add(batchList.get(j));
			}
		}
		return purchaseDetailList;
	}*/
	@RequestMapping(value = "/addNewOrderItem", method = RequestMethod.GET)
	public @ResponseBody BillRecord addNewOrderItem(HttpServletRequest request, HttpServletResponse response) {
		
		BillDetail billDetail=new BillDetail();
		BillRecord billRecord=null;
		try {
			int batchKey=0;
		int itemId=Integer.parseInt(request.getParameter("itemId"));
		String batchNo=request.getParameter("batchNo");
		int qty=Integer.parseInt(request.getParameter("qty"));


        RestTemplate rest = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("itemId", itemId);
		ItemMaster itemMaster = rest.postForObject(Constants.url + "getItemById",map,
				ItemMaster.class);
		
		PurchaseDetail purchaseDetail = null;
		List<PurchaseDetail> purchaseDetailList=new ArrayList<PurchaseDetail>();
		System.err.println(getOrderRes.getIsSameState());
		int isSameState=getOrderRes.getIsSameState();
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
		if(getOrderRes.getCustType()==1)
		{
			 itemRate=purchaseDetail.getWholesaleRate();
		}
		else if(getOrderRes.getCustType()==2)
		{
			itemRate=purchaseDetail.getRetailRate();
		}
		
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
			billDetail.setRate(itemRate);
			billDetail.setItemUom(itemMaster.getUomName());

			float baseRate=0.0f;
			float taxableAmt=0.0f;
			float cgstRs=0.0f;
			float sgstRs=0.0f;
			float igstRs=0.0f;
			float cessRs=0.0f;
			float totalTax=0.0f;
			if(isSameState==1)
			{
				System.out.println("HIII");
				baseRate = (itemRate * 100) / (100 + (purchaseDetail.getCgstPer() + purchaseDetail.getSgstPer()+purchaseDetail.getCessPer()));
				System.out.println("HIII"+baseRate);
				baseRate = roundUp(baseRate);

				taxableAmt=baseRate*qty;
				taxableAmt = roundUp(taxableAmt);

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

				igstRs= (taxableAmt * purchaseDetail.getIgstPer()) / 100;
				igstRs = roundUp(igstRs);

				cessRs= (taxableAmt * purchaseDetail.getCessPer()) / 100;
				cessRs = roundUp(cessRs);

				totalTax = igstRs+cessRs;
				totalTax = roundUp(totalTax);

			}
			billDetail.setBaseRate(baseRate);
			float grandTotal = totalTax + taxableAmt;
			
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
			billDetail.setItemUom(itemMaster.getUomName());
			billDetail.setItemName(purchaseDetail.getItemName());
			billDetail.setItemHsncd(itemMaster.getHsnCode());
			billDetail.setRate(itemRate);

			float baseRate=0.0f;
			float taxableAmt=0.0f;
			float cgstRs=0.0f;
			float sgstRs=0.0f;
			float igstRs=0.0f;
			float cessRs=0.0f;
			float totalTax=0.0f;
			if(getOrderRes.getIsSameState()==1)
			{	System.err.println("elseeeeeeeee3"+itemRate);
				baseRate = (itemRate * 100) / (100 + (purchaseDetail.getCgstPer() + purchaseDetail.getSgstPer()+purchaseDetail.getCessPer()));
				baseRate = roundUp(baseRate);

				taxableAmt=baseRate*purchaseDetail.getBalance();
				taxableAmt = roundUp(taxableAmt);

				cgstRs= (taxableAmt * purchaseDetail.getCgstPer()) / 100;
				cgstRs = roundUp(cgstRs);

				sgstRs= (taxableAmt * purchaseDetail.getSgstPer()) / 100;
				sgstRs = roundUp(sgstRs);

				cessRs= (taxableAmt * purchaseDetail.getCessPer()) / 100;
				cessRs = roundUp(cessRs);

				totalTax = sgstRs + cgstRs+cessRs;
				totalTax = roundUp(totalTax);

			}
			else if(getOrderRes.getIsSameState()==0)
			{
				baseRate = (itemRate * 100) / (100 + (purchaseDetail.getIgstPer()+purchaseDetail.getCessPer()));
				baseRate = roundUp(baseRate);

				taxableAmt=baseRate*qty;
				taxableAmt = roundUp(taxableAmt);

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
			System.out.println("billRecord1"+billRecord.toString());
		}
		float total=0.0f;
		for(int j=0;j<insertBillList.size();j++)
		{
			 total=total+insertBillList.get(j).getGrandTotal();
		}
		billRecord.setTotal(total);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return billRecord;
	}
	
	@RequestMapping(value = "/deleteItemDetail", method = RequestMethod.GET)
	public @ResponseBody BillRecord deleteItemDetail(HttpServletRequest request, HttpServletResponse response) {
		BillDetail billDetail=new BillDetail();
		BillRecord billRecord=null;
		try {
			billRecord=new BillRecord();
			String batchNo=request.getParameter("batchNo");
			int itemId=Integer.parseInt(request.getParameter("itemId"));
			int billQty=Integer.parseInt(request.getParameter("billQty"));
			
			int billDetailKey=Integer.parseInt(request.getParameter("key"));
		   
			BillDetail billDetailRes=insertBillList.get(billDetailKey);
		  //  System.out.println("@@@@@@billDetailRes"+billDetailRes.toString());
		   orderDListRes.get(billDetailRes.getUniqueKey()).setOrderQty(orderDListRes.get(billDetailRes.getUniqueKey()).getOrderQty()+billQty);
		System.out.println("GGGGGGGGGGGGGGGGGGGGGGG"+   orderDListRes.get(billDetailRes.getUniqueKey()).getOrderQty());
		   if(!batchList.isEmpty()) {
				
				for(int i=0;i<batchList.size();i++)
				{System.out.println(billDetailRes.getBatchNo()+"hhh"+itemId+"ss"+billQty);
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
			billRecord.setNewRow(1);
			billRecord.setUniqueKey(billDetailRes.getUniqueKey());
			System.out.println("Batch List"+batchList.toString());
            System.out.println(orderDListRes.toString());
            System.out.println("@@@@@@@"+insertBillList.toString()+"unique"+billDetailRes.getUniqueKey());

		}catch (Exception e) {
			e.printStackTrace();
		}
		return billRecord;
	}
	public static float roundUp(float d) {
		return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}
    
	@RequestMapping(value = "/saveBill", method = RequestMethod.GET)
	public @ResponseBody BillHeader saveBill(HttpServletRequest request, HttpServletResponse response) {
		BillHeader billHeaderRes=null;
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
			
			String remark = request.getParameter("remark");
			System.out.println("remark:" + remark);

			float discountPer = Float.parseFloat(request.getParameter("discount"));
			System.out.println("discount:" + discountPer);

			float paidAmount = Float.parseFloat(request.getParameter("paidAmount"));
			System.out.println("paidAmount:" + paidAmount);

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.now();
			System.out.println(dtf.format(localDate)); // 2016/11/16
			
			
			BillHeader billHeader=new BillHeader();
			billHeader.setBillNo(0);
			billHeader.setBillStatus(1);//
			billHeader.setCustId(getOrderRes.getCustId());
			billHeader.setCustType(getOrderRes.getCustType());
			billHeader.setCustName(getOrderRes.getCustName());

			billHeader.setGstin(getOrderRes.getGstin());
			billHeader.setInvoiceDate(invoiceDate);
			billHeader.setInvoiceNo(invoiceNo);//
		
			billHeader.setBillDetailList(insertBillList);
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = formatter.parse(invoiceDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date); // Now use today date.
			c.add(Calendar.DATE,getOrderRes.getCreditDays()); // Adding credit days
			String output = formatter.format(c.getTime());
			System.out.println(output);
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
            
            if(billHeaderRes.getBillNo()!=0)
            {
            	insertBillList=new ArrayList<BillDetail>();
            	Info info=rest.postForObject(Constants.url + "/bill/postPurchaseDetails", batchList,Info.class);
            	
    			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
    			map.add("orderId",getOrderRes.getOrderId());
                Info infoRes=rest.postForObject(Constants.url + "/order/updateOrderStatus", map, Info.class);
            }
            System.out.println("Invoicea:"+billHeaderRes.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return billHeaderRes;
		
	}
	
}
