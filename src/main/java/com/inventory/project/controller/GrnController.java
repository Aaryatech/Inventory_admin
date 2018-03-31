package com.inventory.project.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.inventory.project.common.Constants;
import com.inventory.project.common.DateConvertor;
import com.inventory.project.model.AddPurchaseDetail;
import com.inventory.project.model.GrnGvnDetail;
import com.inventory.project.model.GrnGvnHeader;
import com.inventory.project.model.GrnList;
import com.inventory.project.model.Info;
import com.inventory.project.model.ItemMaster;
import com.inventory.project.model.PurchaseDetail;
import com.inventory.project.model.ReplaceItem;
import com.inventory.project.model.SupplierMaster;

@Controller
@Scope("session")
public class GrnController {
	
	
	RestTemplate rest = new RestTemplate();
	List<GrnList> getGrnList = new ArrayList<GrnList>();
	List<ReplaceItem> replaceItemList = new ArrayList<ReplaceItem>();
	
	@RequestMapping(value = "/insertGrn", method = RequestMethod.GET)
	public ModelAndView insertGrn(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grn/insertGrn");
		 
		
		try {
			 getGrnList = new ArrayList<GrnList>();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			SupplierMaster[] supplierMaster = rest.postForObject(Constants.url + "getSuppllierList",map,
					SupplierMaster[].class);
			ArrayList<SupplierMaster> supplierList = new ArrayList<SupplierMaster>(Arrays.asList(supplierMaster));
			 
			model.addObject("supplierList",supplierList);
			
		}catch(Exception e)
		{
			
		}
		

		return model;
	}
	
	@RequestMapping(value = "/getGstinNo", method = RequestMethod.GET)
	@ResponseBody
	public SupplierMaster getGstinNo(HttpServletRequest request, HttpServletResponse response) {
		
		 SupplierMaster supplierMaster = new SupplierMaster();
		
		try {
			int suppId = Integer.parseInt(request.getParameter("suppId")); 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 map.add("suppId", suppId);
			  supplierMaster = rest.postForObject(Constants.url + "getSuppllierById",map, SupplierMaster.class);
			   
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return supplierMaster;
	}
	
	@RequestMapping(value = "/getallExpireItemSupllierWise", method = RequestMethod.GET)
	@ResponseBody
	public List<GrnList> getallExpireItemSupllierWise(HttpServletRequest request, HttpServletResponse response) {
		
		 
		 getGrnList = new ArrayList<GrnList>();
		try {
			 Date date = new Date();
			 SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			int suppId = Integer.parseInt(request.getParameter("suppId"));
			int grnType = Integer.parseInt(request.getParameter("grnType"));
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 map.add("suppId", suppId);
			 map.add("grnType", grnType);
			 map.add("currentDate", DateConvertor.convertToYMD(sf.format(date)));
			 GrnList[] allGrnList = rest.postForObject(Constants.url + "getallExpireItemSupllierWise",map, GrnList[].class);
			 getGrnList = new ArrayList<GrnList>(Arrays.asList(allGrnList));
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return getGrnList;
	}
	
	@RequestMapping(value = "/getGrnList", method = RequestMethod.GET)
	@ResponseBody
	public List<GrnList> getGrnList(HttpServletRequest request, HttpServletResponse response) {
		 
		try {
			String batchNo =request.getParameter("batchNo"); 
			int suppId = Integer.parseInt(request.getParameter("suppId"));
			int grnType = Integer.parseInt(request.getParameter("grnType"));
			
			System.out.println("batchNo"+batchNo);
			int flag=0;
			
			 for(int i=0;i<getGrnList.size();i++)
			 {
				 if(getGrnList.get(i).getBatchNo().equals(batchNo))
				 {
					 flag=1;
					 break;
				 }
			 }
			 if(flag!=1)
			 {
				 Date date = new Date();
				 SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
				 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				 map.add("batchNo", batchNo);
				 map.add("suppId", suppId);
				 map.add("grnType", grnType);
				 map.add("currentDate", DateConvertor.convertToYMD(sf.format(date)));
				 GrnList grnList = rest.postForObject(Constants.url + "getItemFromPurchaseBillForGvn",map, GrnList.class);
				 
				 if(grnList.getPurchaseId()!=0)
					 getGrnList.add(grnList); 
			 }
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return getGrnList;
	}
	
	@RequestMapping(value = "/delteItemFromGrnList", method = RequestMethod.GET)
	@ResponseBody
	public List<GrnList> delteItemFromGrnList(HttpServletRequest request, HttpServletResponse response) {
		 
		try {
			int index =Integer.parseInt(request.getParameter("index"));  
			 
			getGrnList.remove(index);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return getGrnList;
	}
	
	@RequestMapping(value = "/submitGrnGvn", method = RequestMethod.POST)
	public String insertPurchaseBill(HttpServletRequest request, HttpServletResponse response) {

	 
		DecimalFormat df = new DecimalFormat("#.00");
		
		try {
				int suppId = Integer.parseInt(request.getParameter("supId"));
				int grnType = Integer.parseInt(request.getParameter("grnType"));
				String gstinNo = request.getParameter("gstinNo");
				String date = request.getParameter("date"); 
				  
				for(int i=0;i<getGrnList.size();i++)
				{
					getGrnList.get(i).setRateWithTax(Float.parseFloat(request.getParameter("rateWithTaxRate"+i)));
					getGrnList.get(i).setBalance(Integer.parseInt(request.getParameter("balanceRate"+i)));  
				}
				 
				GrnGvnHeader insert = new GrnGvnHeader();
				insert.setSuppId(suppId);
				insert.setDate(date);
				insert.setGstnNo(gstinNo);
				insert.setGrnType(grnType);
				
				List<GrnGvnDetail> grnGvnDetailList = new ArrayList<GrnGvnDetail>();
				
				for(int i=0;i<getGrnList.size();i++)
				{
					GrnGvnDetail grnGvnDetail = new GrnGvnDetail();
					grnGvnDetail.setInvoiceNo(getGrnList.get(i).getInvoiceNo());
					grnGvnDetail.setInvoiceDate(getGrnList.get(i).getInvDate());
					grnGvnDetail.setItemId(getGrnList.get(i).getItemId());
					grnGvnDetail.setItemName(getGrnList.get(i).getItemName());
					grnGvnDetail.setHsnCode(getGrnList.get(i).getHsnCode());
					grnGvnDetail.setBatchNo(getGrnList.get(i).getBatchNo());
					grnGvnDetail.setRate(getGrnList.get(i).getRateWithTax());
					grnGvnDetail.setQty(getGrnList.get(i).getBalance());
					grnGvnDetail.setTotal(Float.valueOf(df.format(grnGvnDetail.getQty()*grnGvnDetail.getRate())));
					grnGvnDetail.setExpireDate(getGrnList.get(i).getExpiryDate());
					grnGvnDetailList.add(grnGvnDetail);
				}
				insert.setGrnGvnDetailList(grnGvnDetailList);
				GrnGvnHeader grnGvnHeader = rest.postForObject(Constants.url + "postGrnGvn",insert, GrnGvnHeader.class);
				System.out.println("grnGvnHeader " + grnGvnHeader);
				
				if(grnGvnHeader!=null)
				{
					String batchNo = new String();
					for(int i=0;i<getGrnList.size();i++)
					{
						batchNo=batchNo+","+getGrnList.get(i).getBatchNo();
					}
					 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
					 map.add("batchList", batchNo);
					 map.add("grnType", grnGvnHeader.getGrnType());
					Info info = rest.postForObject(Constants.url + "updateIsGrnInPurchaseDetail",map, Info.class);
					System.out.println("info " + info);
				}
				
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return "redirect:/insertGrn";
	}
	
	
	@RequestMapping(value = "/grnItemHistory", method = RequestMethod.GET)
	public ModelAndView grnItemHistory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grn/grnCompleteList");
		 
	 
		 
		return model;
	}
	
	@RequestMapping(value = "/getHistoryOfGrnGvn", method = RequestMethod.GET)
	@ResponseBody
	public List<GrnGvnHeader> getHistoryOfGrnGvn(HttpServletRequest request, HttpServletResponse response) {
		
		 
		 List<GrnGvnHeader> grnGvnHeaderList = new ArrayList<GrnGvnHeader>();
		try {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			int grnType = Integer.parseInt(request.getParameter("grnType"));
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("grnType", grnType);
			System.out.println(map);
			GrnGvnHeader[] grnGvnHeader = rest.postForObject(Constants.url + "getHistoryOfGrnGvn",map,
					GrnGvnHeader[].class);
			 grnGvnHeaderList = new ArrayList<GrnGvnHeader>(Arrays.asList(grnGvnHeader));
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return grnGvnHeaderList;
	}
	
	@RequestMapping(value = "/grnHeaderWithDetail/{grnId}", method = RequestMethod.GET)
	public ModelAndView grnHeaderWithDetail(@PathVariable int grnId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grn/grnHeaderWithDetail");
		 try {
			 
			 
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("grnId",grnId); 
				GrnGvnHeader grnGvnHeader = rest.postForObject(Constants.url + "getGrnDetailByHeaderId",map, GrnGvnHeader.class);
				 
				map = new LinkedMultiValueMap<String, Object>();
				map.add("flag",1);
				SupplierMaster[] supplierMaster = rest.postForObject(Constants.url + "getSuppllierList",map,
				SupplierMaster[].class);
					ArrayList<SupplierMaster> supplierList = new ArrayList<SupplierMaster>(Arrays.asList(supplierMaster));
				
				 model.addObject("grnGvnHeader",grnGvnHeader);
				 model.addObject("supplierList",supplierList);
				 
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	 
		 
		return model;
	}
	
	@RequestMapping(value = "/replaceQtyFromCustmer", method = RequestMethod.GET)
	public ModelAndView replaceQtyFromCustmer(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("grn/replaceQtyFromCustmer"); 
		replaceItemList = new ArrayList<ReplaceItem>();
		return model;
	}
	
	@RequestMapping(value = "/addItemInReplaceList", method = RequestMethod.GET)
	@ResponseBody
	public List<ReplaceItem> addItemInReplaceList(HttpServletRequest request, HttpServletResponse response) {
		 
		 
		try {
			String batchNo =request.getParameter("batchNo"); 
			int qty = Integer.parseInt(request.getParameter("qty")); 
			int flag=0;
			
			 for(int i=0;i<replaceItemList.size();i++)
			 {
				 if(replaceItemList.get(i).getBatchNo().equals(batchNo))
				 {
					 flag=1;
					 break;
				 }
			 }
			 if(flag!=1)
			 {
				 
				 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				 map.add("batchNo", batchNo);  
				 PurchaseDetail purchaseDetail = rest.postForObject(Constants.url + "getItemByBatchNo",map, PurchaseDetail.class);
				 
				 if(purchaseDetail.getPurDetailId()!=0)
				 {
					 ReplaceItem replaceItem = new ReplaceItem();
					 replaceItem.setItemId(purchaseDetail.getItemId());
					 replaceItem.setItemName(purchaseDetail.getItemName());
					 replaceItem.setBatchNo(purchaseDetail.getBatchNo());
					 replaceItem.setQty(qty);
					 replaceItem.setOldReplaceQty(purchaseDetail.getReplaceQty());
					 replaceItem.setTotalReplaceQty(replaceItem.getQty()+replaceItem.getOldReplaceQty());
					 replaceItemList.add(replaceItem);  
				 }
					
			 } 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return replaceItemList;
	}
	
	@RequestMapping(value = "/delteItemFromReplaceList", method = RequestMethod.GET)
	@ResponseBody
	public List<ReplaceItem> delteItemFromReplaceList(HttpServletRequest request, HttpServletResponse response) {
		 
		try {
			int index =Integer.parseInt(request.getParameter("index"));  
			 
			replaceItemList.remove(index);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return replaceItemList;
	}
	
	@RequestMapping(value = "/submitReplaceItemList", method = RequestMethod.POST)
	public String submitReplaceItemList(HttpServletRequest request, HttpServletResponse response) {
 
		try {
				 
			 
			Info info = rest.postForObject(Constants.url + "updateReplaceQtyInPurchaseBill",replaceItemList, Info.class);
				System.out.println("info " +info);
				
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return "redirect:/replaceQtyFromCustmer";
	}

}
