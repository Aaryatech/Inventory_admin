package com.inventory.project.controller;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URLConnection;
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
import org.springframework.util.FileCopyUtils;
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
import com.inventory.project.model.ItemBarcode;
import com.inventory.project.model.ItemMaster; 
import com.inventory.project.model.PurchaseDetail;
import com.inventory.project.model.PurchaseHeader;
import com.inventory.project.model.SupplierMaster;
import com.inventory.project.model.TSetting;

@Controller
@Scope("session")
public class PurchaseController {
	
	RestTemplate rest = new RestTemplate();
	List<AddPurchaseDetail> purchaseDetailtemList = new ArrayList<AddPurchaseDetail>();
	DecimalFormat df = new DecimalFormat("#.00");
	int isSameState;
	List<PurchaseDetail> purchaseDetailListForBarcode;
	
	@RequestMapping(value = "/purchaseBill", method = RequestMethod.GET)
	public ModelAndView purchaseBill(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchase/puchaseBill");
		
		try {
			purchaseDetailtemList = new ArrayList<AddPurchaseDetail>();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			SupplierMaster[] supplierMaster = rest.postForObject(Constants.url + "getSuppllierList",map,
					SupplierMaster[].class);
			ArrayList<SupplierMaster> supplierList = new ArrayList<SupplierMaster>(Arrays.asList(supplierMaster));
			
			 map = new LinkedMultiValueMap<String, Object>();
			 map.add("flag", 0);
			 ItemMaster[] itemMaster = rest.postForObject(Constants.url + "getItemList",map, ItemMaster[].class);
				ArrayList<ItemMaster> itemList = new ArrayList<ItemMaster>(Arrays.asList(itemMaster));
			 
			model.addObject("itemList",itemList);
			model.addObject("supplierList",supplierList);
			
			
			
			
		}catch(Exception e)
		{
			
		}
		

		return model;
	}
	
	
	
	
	@RequestMapping(value = "/checkIsSupplierSameState", method = RequestMethod.GET)
	@ResponseBody
	public List<AddPurchaseDetail> checkIsSupplierSameState(HttpServletRequest request, HttpServletResponse response) {
		
		 SupplierMaster supplierMaster = new SupplierMaster();
		
		try {
			int suppId = Integer.parseInt(request.getParameter("suppId")); 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 map.add("suppId", suppId);
			  supplierMaster = rest.postForObject(Constants.url + "getSuppllierById",map, SupplierMaster.class);
			  if(supplierMaster.getIsSameState()==1)
				  isSameState=1;
			  else
				  isSameState=0;
			  
			  for(int i=0;i<purchaseDetailtemList.size();i++)
				{
					 
					 
						if(isSameState==1)
						{
							purchaseDetailtemList.get(i).setCgstRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
									purchaseDetailtemList.get(i).getCgstPer()/100)));
							purchaseDetailtemList.get(i).setSgstRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
									purchaseDetailtemList.get(i).getSgstPer()/100)));
							purchaseDetailtemList.get(i).setIgstRs(0);
						}
						else
						{
							purchaseDetailtemList.get(i).setCgstRs(0);
							purchaseDetailtemList.get(i).setSgstRs(0);
							purchaseDetailtemList.get(i).setIgstRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
									purchaseDetailtemList.get(i).getIgstPer()/100)));
						}
						purchaseDetailtemList.get(i).setTotal(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()
								+purchaseDetailtemList.get(i).getIgstRs()+purchaseDetailtemList.get(i).getCgstRs()+purchaseDetailtemList.get(i).getSgstRs()
								+purchaseDetailtemList.get(i).getCessRs()+purchaseDetailtemList.get(i).getOtherExtra())));
						purchaseDetailtemList.get(i).setRateWithTax(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTotal()/purchaseDetailtemList.get(i).getRecQty())));
						purchaseDetailtemList.get(i).setRateWithoutTax(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()/purchaseDetailtemList.get(i).getRecQty())));
						float wholesaleAmt = purchaseDetailtemList.get(i).getRateWithTax()*20/100;
						float retailerAmt = purchaseDetailtemList.get(i).getRateWithTax()*30/100;
						purchaseDetailtemList.get(i).setWholesaleRate(Float.valueOf(df.format(purchaseDetailtemList.get(i).getRateWithTax()+wholesaleAmt)));
						purchaseDetailtemList.get(i).setRetailRate(Float.valueOf(df.format(purchaseDetailtemList.get(i).getRateWithTax()+retailerAmt)));
						 
				}
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return purchaseDetailtemList;
	}
	
	@RequestMapping(value = "/addItemInPurchaseBill", method = RequestMethod.GET)
	@ResponseBody
	public List<AddPurchaseDetail> editUom(HttpServletRequest request, HttpServletResponse response) {

	 
		try {
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			int recQty = Integer.parseInt(request.getParameter("recQty"));
			float rate = Float.parseFloat(request.getParameter("rate"));
			float discPer = Float.parseFloat(request.getParameter("discPer"));
			float discPerOnBill = Float.parseFloat(request.getParameter("discPerOnBill")); 
			float freightAmt = Float.parseFloat(request.getParameter("freightAmt"));
			float insuAmt = Float.parseFloat(request.getParameter("insuranceAmt"));
			float extraCharges = Float.parseFloat(request.getParameter("extraCharges"));
			String itemName = request.getParameter("itemName");
			String index = request.getParameter("index");
			String expireDate = request.getParameter("expireDate");
			
			 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 map.add("itemId", itemId);
			 ItemMaster itemMaster = rest.postForObject(Constants.url + "getItemById",map, ItemMaster.class);
			if(index==null || index=="")   //Add new Item
			{
				map = new LinkedMultiValueMap<String, Object>();
				map.add("key", "Batch No");
				TSetting tSetting = rest.postForObject(Constants.url + "getSettingValueByKey",map, TSetting.class);
				 
				 
				System.out.println("add new Item");
				AddPurchaseDetail addItem = new AddPurchaseDetail();
				addItem.setItemName(itemName);
				addItem.setItemId(itemId);
				addItem.setRate(rate);
				addItem.setRecQty(recQty);
				addItem.setValue(Float.valueOf(df.format(recQty*rate))); 
				addItem.setDiscPer(discPer);
				addItem.setDiscAmt(Float.valueOf(df.format((discPer/100)*addItem.getValue()))); 
				addItem.setItemUom(itemMaster.getUomName());
				addItem.setCgstPer(itemMaster.getCgst()); 
				addItem.setSgstPer(itemMaster.getSgst());
				addItem.setIgstPer(itemMaster.getIgst());
				addItem.setCessPer(itemMaster.getCess());
				String batchNo = itemId+"-"+tSetting.getSettingValue();
				addItem.setBatchNo(batchNo);
				addItem.setExpiryDate(expireDate);
				purchaseDetailtemList.add(addItem);
				 
				float totalValue = 0; 
				
				for(int i=0;i<purchaseDetailtemList.size();i++)
				{
					totalValue=totalValue+purchaseDetailtemList.get(i).getValue();
				}
				 
				for(int i=0;i<purchaseDetailtemList.size();i++)
				{
					float divFactor=purchaseDetailtemList.get(i).getValue()/totalValue*100;
					System.out.println(purchaseDetailtemList.get(i).getValue()+"/"+totalValue+"*100="+divFactor);
					
					if(Float.isNaN(divFactor))
					{
						System.out.println("in if division factor");
						purchaseDetailtemList.get(i).setDiscOnBill( Float.valueOf(df.format((purchaseDetailtemList.get(i).getValue() - purchaseDetailtemList.get(i).getDiscAmt())
								* discPerOnBill / 100)));
						purchaseDetailtemList.get(i).setDivisionFactor(0);
						purchaseDetailtemList.get(i).setDiscOnBill(0); 
						purchaseDetailtemList.get(i).setFreightAmt(0);
						purchaseDetailtemList.get(i).setInsuAmt(0); 
						 
					}
					else
					{
						System.out.println("in else division factor");
						purchaseDetailtemList.get(i).setDiscOnBill( Float.valueOf(df.format((purchaseDetailtemList.get(i).getValue() - purchaseDetailtemList.get(i).getDiscAmt())
								* discPerOnBill / 100)));
						purchaseDetailtemList.get(i).setDivisionFactor(Float.valueOf(df.format(divFactor))); 
						purchaseDetailtemList.get(i).setFreightAmt(Float.valueOf(df.format(divFactor*freightAmt/100)));
						purchaseDetailtemList.get(i).setInsuAmt(Float.valueOf(df.format(divFactor*insuAmt/100))); 
						purchaseDetailtemList.get(i).setTaxableAmt(Float.valueOf(df.format( purchaseDetailtemList.get(i).getValue()-
								purchaseDetailtemList.get(i).getDiscAmt()-purchaseDetailtemList.get(i).getDiscOnBill()+
								purchaseDetailtemList.get(i).getFreightAmt()+purchaseDetailtemList.get(i).getInsuAmt())));
						if(isSameState==1)
						{
							purchaseDetailtemList.get(i).setCgstRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
									purchaseDetailtemList.get(i).getCgstPer()/100)));
							purchaseDetailtemList.get(i).setSgstRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
									purchaseDetailtemList.get(i).getSgstPer()/100)));
							purchaseDetailtemList.get(i).setIgstRs(0);
						}
						else
						{
							purchaseDetailtemList.get(i).setCgstRs(0);
							purchaseDetailtemList.get(i).setSgstRs(0);
							purchaseDetailtemList.get(i).setIgstRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
									purchaseDetailtemList.get(i).getIgstPer()/100)));
						}
						
						purchaseDetailtemList.get(i).setCessRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
								purchaseDetailtemList.get(i).getCessPer()/100)));
						purchaseDetailtemList.get(i).setOtherExtra(Float.valueOf(df.format(divFactor*extraCharges/100)));
						purchaseDetailtemList.get(i).setTotal(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()
								+purchaseDetailtemList.get(i).getIgstRs()+purchaseDetailtemList.get(i).getCgstRs()+purchaseDetailtemList.get(i).getSgstRs()
								+purchaseDetailtemList.get(i).getCessRs()+purchaseDetailtemList.get(i).getOtherExtra())));
						purchaseDetailtemList.get(i).setRateWithTax(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTotal()/purchaseDetailtemList.get(i).getRecQty())));
						purchaseDetailtemList.get(i).setRateWithoutTax(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()/purchaseDetailtemList.get(i).getRecQty())));
						float wholesaleAmt = purchaseDetailtemList.get(i).getRateWithTax()*20/100;
						float retailerAmt = purchaseDetailtemList.get(i).getRateWithTax()*30/100;
						purchaseDetailtemList.get(i).setWholesaleRate(Float.valueOf(df.format(purchaseDetailtemList.get(i).getRateWithTax()+wholesaleAmt)));
						purchaseDetailtemList.get(i).setRetailRate(Float.valueOf(df.format(purchaseDetailtemList.get(i).getRateWithTax()+retailerAmt)));
						 
					}
				}
			}
			else  //Edit Item
			{
				System.out.println("edit Item");
				 int key=Integer.parseInt(index);
				 if(purchaseDetailtemList.get(key).getItemId()!=itemId)
				 {
					map = new LinkedMultiValueMap<String, Object>();
					map.add("key", "Batch No");
					TSetting tSetting = rest.postForObject(Constants.url + "getSettingValueByKey",map, TSetting.class);
					String batchNo = itemId+"-"+tSetting.getSettingValue();
					purchaseDetailtemList.get(key).setBatchNo(batchNo);
				 }
				purchaseDetailtemList.get(key).setItemName(itemName);
				purchaseDetailtemList.get(key).setItemId(itemId);
				purchaseDetailtemList.get(key).setRate(rate);
				purchaseDetailtemList.get(key).setRecQty(recQty);
				purchaseDetailtemList.get(key).setExpiryDate(expireDate);
				purchaseDetailtemList.get(key).setValue(Float.valueOf(df.format(recQty*rate))); 
				purchaseDetailtemList.get(key).setDiscPer(discPer);
				purchaseDetailtemList.get(key).setDiscAmt(Float.valueOf(df.format((discPer/100)*purchaseDetailtemList.get(key).getValue()))); 
				purchaseDetailtemList.get(key).setItemUom(itemMaster.getUomName());
				purchaseDetailtemList.get(key).setCgstPer(itemMaster.getCgst()); 
				purchaseDetailtemList.get(key).setSgstPer(itemMaster.getSgst());
				purchaseDetailtemList.get(key).setIgstPer(itemMaster.getIgst());
				purchaseDetailtemList.get(key).setCessPer(itemMaster.getCess()); 
				
				 
				float totalValue = 0; 
				
				for(int i=0;i<purchaseDetailtemList.size();i++)
				{
					totalValue=totalValue+purchaseDetailtemList.get(i).getValue();
				}
				 
				for(int i=0;i<purchaseDetailtemList.size();i++)
				{
					float divFactor=purchaseDetailtemList.get(i).getValue()/totalValue*100;
					System.out.println(purchaseDetailtemList.get(i).getValue()+"/"+totalValue+"*100="+divFactor);
					
					if(Float.isNaN(divFactor))
					{
						System.out.println("in if division factor");
						purchaseDetailtemList.get(i).setDiscOnBill( Float.valueOf(df.format((purchaseDetailtemList.get(i).getValue() - purchaseDetailtemList.get(i).getDiscAmt())
								* discPerOnBill / 100)));
						purchaseDetailtemList.get(i).setDivisionFactor(0);
						purchaseDetailtemList.get(i).setDiscOnBill(0); 
						purchaseDetailtemList.get(i).setFreightAmt(0);
						purchaseDetailtemList.get(i).setInsuAmt(0); 
						 
					}
					else
					{
						System.out.println("in else division factor");
						purchaseDetailtemList.get(i).setDiscOnBill( Float.valueOf(df.format((purchaseDetailtemList.get(i).getValue() - purchaseDetailtemList.get(i).getDiscAmt())
								* discPerOnBill / 100)));
						purchaseDetailtemList.get(i).setDivisionFactor(Float.valueOf(df.format(divFactor))); 
						purchaseDetailtemList.get(i).setFreightAmt(Float.valueOf(df.format(divFactor*freightAmt/100)));
						purchaseDetailtemList.get(i).setInsuAmt(Float.valueOf(df.format(divFactor*insuAmt/100))); 
						purchaseDetailtemList.get(i).setTaxableAmt(Float.valueOf(df.format( purchaseDetailtemList.get(i).getValue()-
								purchaseDetailtemList.get(i).getDiscAmt()-purchaseDetailtemList.get(i).getDiscOnBill()+
								purchaseDetailtemList.get(i).getFreightAmt()+purchaseDetailtemList.get(i).getInsuAmt())));
						if(isSameState==1)
						{
							purchaseDetailtemList.get(i).setCgstRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
									purchaseDetailtemList.get(i).getCgstPer()/100)));
							purchaseDetailtemList.get(i).setSgstRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
									purchaseDetailtemList.get(i).getSgstPer()/100)));
							purchaseDetailtemList.get(i).setIgstRs(0);
						}
						else
						{
							purchaseDetailtemList.get(i).setCgstRs(0);
							purchaseDetailtemList.get(i).setSgstRs(0);
							purchaseDetailtemList.get(i).setIgstRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
									purchaseDetailtemList.get(i).getIgstPer()/100)));
						}
						purchaseDetailtemList.get(i).setCessRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
								purchaseDetailtemList.get(i).getCessPer()/100)));
						purchaseDetailtemList.get(i).setOtherExtra(Float.valueOf(df.format(divFactor*extraCharges/100)));
						purchaseDetailtemList.get(i).setTotal(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()
								+purchaseDetailtemList.get(i).getIgstRs()+purchaseDetailtemList.get(i).getCgstRs()+purchaseDetailtemList.get(i).getSgstRs()
								+purchaseDetailtemList.get(i).getCessRs()+purchaseDetailtemList.get(i).getOtherExtra())));
						purchaseDetailtemList.get(i).setRateWithTax(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTotal()/purchaseDetailtemList.get(i).getRecQty())));
						purchaseDetailtemList.get(i).setRateWithoutTax(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()/purchaseDetailtemList.get(i).getRecQty())));
						float wholesaleAmt = purchaseDetailtemList.get(i).getRateWithTax()*20/100;
						float retailerAmt = purchaseDetailtemList.get(i).getRateWithTax()*30/100;
						purchaseDetailtemList.get(i).setWholesaleRate(Float.valueOf(df.format(purchaseDetailtemList.get(i).getRateWithTax()+wholesaleAmt)));
						purchaseDetailtemList.get(i).setRetailRate(Float.valueOf(df.format(purchaseDetailtemList.get(i).getRateWithTax()+retailerAmt)));
						 
					}
				}
			}
			
			 
			System.out.println(purchaseDetailtemList);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return purchaseDetailtemList;
	}
	
	@RequestMapping(value = "/editPurchaseItemList", method = RequestMethod.GET)
	@ResponseBody
	public AddPurchaseDetail editPurchaseItemList(HttpServletRequest request, HttpServletResponse response) {
		
		AddPurchaseDetail edit = new AddPurchaseDetail(); 
		
		try {
			int index = Integer.parseInt(request.getParameter("index")); 
			edit=purchaseDetailtemList.get(index);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return edit;
	}
	
	@RequestMapping(value = "/updatePurchaseHeader", method = RequestMethod.GET)
	@ResponseBody
	public List<AddPurchaseDetail> updatePurchaseHeader(HttpServletRequest request, HttpServletResponse response) {

	 
		try {
			 
			float discPerOnBill = Float.parseFloat(request.getParameter("discPerOnBill")); 
			float freightAmt = Float.parseFloat(request.getParameter("freightAmt"));
			float insuAmt = Float.parseFloat(request.getParameter("insuranceAmt"));
			float extraCharges = Float.parseFloat(request.getParameter("extraCharges"));
			 
		 
				 
				float totalValue = 0; 
				
				for(int i=0;i<purchaseDetailtemList.size();i++)
				{
					totalValue=totalValue+purchaseDetailtemList.get(i).getValue();
				}
				 
				for(int i=0;i<purchaseDetailtemList.size();i++)
				{
					float divFactor=purchaseDetailtemList.get(i).getValue()/totalValue*100;
					System.out.println(purchaseDetailtemList.get(i).getValue()+"/"+totalValue+"*100="+divFactor);
					
					if(Float.isNaN(divFactor))
					{
						System.out.println("in if division factor");
						purchaseDetailtemList.get(i).setDiscOnBill( Float.valueOf(df.format((purchaseDetailtemList.get(i).getValue() - purchaseDetailtemList.get(i).getDiscAmt())
								* discPerOnBill / 100)));
						purchaseDetailtemList.get(i).setDivisionFactor(0);
						purchaseDetailtemList.get(i).setDiscOnBill(0); 
						purchaseDetailtemList.get(i).setFreightAmt(0);
						purchaseDetailtemList.get(i).setInsuAmt(0); 
						 
					}
					else
					{
						System.out.println("in else division factor");
						purchaseDetailtemList.get(i).setDiscOnBill( Float.valueOf(df.format((purchaseDetailtemList.get(i).getValue() - purchaseDetailtemList.get(i).getDiscAmt())
								* discPerOnBill / 100)));
						purchaseDetailtemList.get(i).setDivisionFactor(Float.valueOf(df.format(divFactor))); 
						purchaseDetailtemList.get(i).setFreightAmt(Float.valueOf(df.format(divFactor*freightAmt/100)));
						purchaseDetailtemList.get(i).setInsuAmt(Float.valueOf(df.format(divFactor*insuAmt/100))); 
						purchaseDetailtemList.get(i).setTaxableAmt(Float.valueOf(df.format( purchaseDetailtemList.get(i).getValue()-
								purchaseDetailtemList.get(i).getDiscAmt()-purchaseDetailtemList.get(i).getDiscOnBill()+
								purchaseDetailtemList.get(i).getFreightAmt()+purchaseDetailtemList.get(i).getInsuAmt())));
						if(isSameState==1)
						{
							purchaseDetailtemList.get(i).setCgstRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
									purchaseDetailtemList.get(i).getCgstPer()/100)));
							purchaseDetailtemList.get(i).setSgstRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
									purchaseDetailtemList.get(i).getSgstPer()/100)));
							purchaseDetailtemList.get(i).setIgstRs(0);
						}
						else
						{
							purchaseDetailtemList.get(i).setCgstRs(0);
							purchaseDetailtemList.get(i).setSgstRs(0);
							purchaseDetailtemList.get(i).setIgstRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
									purchaseDetailtemList.get(i).getIgstPer()/100)));
						}
						purchaseDetailtemList.get(i).setCessRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
								purchaseDetailtemList.get(i).getCessPer()/100)));
						purchaseDetailtemList.get(i).setOtherExtra(Float.valueOf(df.format(divFactor*extraCharges/100)));
						purchaseDetailtemList.get(i).setTotal(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()
								+purchaseDetailtemList.get(i).getIgstRs()+purchaseDetailtemList.get(i).getCgstRs()+purchaseDetailtemList.get(i).getSgstRs()
								+purchaseDetailtemList.get(i).getCessRs()+purchaseDetailtemList.get(i).getOtherExtra())));
						purchaseDetailtemList.get(i).setRateWithTax(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTotal()/purchaseDetailtemList.get(i).getRecQty())));
						purchaseDetailtemList.get(i).setRateWithoutTax(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()/purchaseDetailtemList.get(i).getRecQty())));
						float wholesaleAmt = purchaseDetailtemList.get(i).getRateWithTax()*20/100;
						float retailerAmt = purchaseDetailtemList.get(i).getRateWithTax()*30/100;
						purchaseDetailtemList.get(i).setWholesaleRate(Float.valueOf(df.format(purchaseDetailtemList.get(i).getRateWithTax()+wholesaleAmt)));
						purchaseDetailtemList.get(i).setRetailRate(Float.valueOf(df.format(purchaseDetailtemList.get(i).getRateWithTax()+retailerAmt)));
						 
					}
				}
		 
			 
			 
			System.out.println(purchaseDetailtemList);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return purchaseDetailtemList;
	}
	
	@RequestMapping(value = "/deleteItemPurchaseList", method = RequestMethod.GET)
	@ResponseBody
	public List<AddPurchaseDetail> deleteItemPurchaseList(HttpServletRequest request, HttpServletResponse response) {

	 
		try {
			 
			float discPerOnBill = Float.parseFloat(request.getParameter("discPerOnBill")); 
			float freightAmt = Float.parseFloat(request.getParameter("freightAmt"));
			float insuAmt = Float.parseFloat(request.getParameter("insuranceAmt"));
			float extraCharges = Float.parseFloat(request.getParameter("extraCharges"));
			int index = Integer.parseInt(request.getParameter("index"));
		 
			purchaseDetailtemList.remove(index);
				float totalValue = 0; 
				
				for(int i=0;i<purchaseDetailtemList.size();i++)
				{
					totalValue=totalValue+purchaseDetailtemList.get(i).getValue();
				}
				 
				for(int i=0;i<purchaseDetailtemList.size();i++)
				{
					float divFactor=purchaseDetailtemList.get(i).getValue()/totalValue*100;
					System.out.println(purchaseDetailtemList.get(i).getValue()+"/"+totalValue+"*100="+divFactor);
					
					if(Float.isNaN(divFactor))
					{
						System.out.println("in if division factor");
						purchaseDetailtemList.get(i).setDiscOnBill( Float.valueOf(df.format((purchaseDetailtemList.get(i).getValue() - purchaseDetailtemList.get(i).getDiscAmt())
								* discPerOnBill / 100)));
						purchaseDetailtemList.get(i).setDivisionFactor(0);
						purchaseDetailtemList.get(i).setDiscOnBill(0); 
						purchaseDetailtemList.get(i).setFreightAmt(0);
						purchaseDetailtemList.get(i).setInsuAmt(0); 
						 
					}
					else
					{
						System.out.println("in else division factor");
						purchaseDetailtemList.get(i).setDiscOnBill( Float.valueOf(df.format((purchaseDetailtemList.get(i).getValue() - purchaseDetailtemList.get(i).getDiscAmt())
								* discPerOnBill / 100)));
						purchaseDetailtemList.get(i).setDivisionFactor(Float.valueOf(df.format(divFactor))); 
						purchaseDetailtemList.get(i).setFreightAmt(Float.valueOf(df.format(divFactor*freightAmt/100)));
						purchaseDetailtemList.get(i).setInsuAmt(Float.valueOf(df.format(divFactor*insuAmt/100))); 
						purchaseDetailtemList.get(i).setTaxableAmt(Float.valueOf(df.format( purchaseDetailtemList.get(i).getValue()-
								purchaseDetailtemList.get(i).getDiscAmt()-purchaseDetailtemList.get(i).getDiscOnBill()+
								purchaseDetailtemList.get(i).getFreightAmt()+purchaseDetailtemList.get(i).getInsuAmt())));
						if(isSameState==1)
						{
							purchaseDetailtemList.get(i).setCgstRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
									purchaseDetailtemList.get(i).getCgstPer()/100)));
							purchaseDetailtemList.get(i).setSgstRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
									purchaseDetailtemList.get(i).getSgstPer()/100)));
							purchaseDetailtemList.get(i).setIgstRs(0);
						}
						else
						{
							purchaseDetailtemList.get(i).setCgstRs(0);
							purchaseDetailtemList.get(i).setSgstRs(0);
							purchaseDetailtemList.get(i).setIgstRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
									purchaseDetailtemList.get(i).getIgstPer()/100)));
						}
						purchaseDetailtemList.get(i).setCessRs(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()*
								purchaseDetailtemList.get(i).getCessPer()/100)));
						purchaseDetailtemList.get(i).setOtherExtra(Float.valueOf(df.format(divFactor*extraCharges/100)));
						purchaseDetailtemList.get(i).setTotal(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()
								+purchaseDetailtemList.get(i).getIgstRs()+purchaseDetailtemList.get(i).getCgstRs()+purchaseDetailtemList.get(i).getSgstRs()
								+purchaseDetailtemList.get(i).getCessRs()+purchaseDetailtemList.get(i).getOtherExtra())));
						purchaseDetailtemList.get(i).setRateWithTax(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTotal()/purchaseDetailtemList.get(i).getRecQty())));
						purchaseDetailtemList.get(i).setRateWithoutTax(Float.valueOf(df.format(purchaseDetailtemList.get(i).getTaxableAmt()/purchaseDetailtemList.get(i).getRecQty())));
						float wholesaleAmt = purchaseDetailtemList.get(i).getRateWithTax()*20/100;
						float retailerAmt = purchaseDetailtemList.get(i).getRateWithTax()*30/100;
						purchaseDetailtemList.get(i).setWholesaleRate(Float.valueOf(df.format(purchaseDetailtemList.get(i).getRateWithTax()+wholesaleAmt)));
						purchaseDetailtemList.get(i).setRetailRate(Float.valueOf(df.format(purchaseDetailtemList.get(i).getRateWithTax()+retailerAmt)));
						 
					}
				}
		 
			 
			 
			System.out.println(purchaseDetailtemList);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return purchaseDetailtemList;
	}
	
	
	@RequestMapping(value = "/insertPurchaseBill", method = RequestMethod.POST)
	public String insertPurchaseBill(HttpServletRequest request, HttpServletResponse response) {

	 
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
		Date date = new Date();
		
		try {
				int suppId = Integer.parseInt(request.getParameter("suppId"));
				String vehicleNo = request.getParameter("vehicleNo");
				String invoiceNo = request.getParameter("invoiceNo");
				String invoiceDate = request.getParameter("invoiceDate");
				float basicValue = Float.parseFloat(request.getParameter("basicValue"));
				float freightAmt = Float.parseFloat(request.getParameter("freightAmt"));
				float cgst = Float.parseFloat(request.getParameter("cgst"));
				float discAmt2 = Float.parseFloat(request.getParameter("discAmt2"));
				float insuranceAmt = Float.parseFloat(request.getParameter("insuranceAmt"));
				float sgst = Float.parseFloat(request.getParameter("sgst"));
				float discPerOnBill = Float.parseFloat(request.getParameter("discPerOnBill"));
				float discAmt = Float.parseFloat(request.getParameter("discAmt"));
				float extraCharges = Float.parseFloat(request.getParameter("extraCharges"));
				float igst = Float.parseFloat(request.getParameter("igst"));
				float cess = Float.parseFloat(request.getParameter("cess"));
				float billAmount = Float.parseFloat(request.getParameter("billAmount"));
				//float roundOff = Float.parseFloat(request.getParameter("roundOff"));
				float totalTaxableAmt=0;
				for(int i=0;i<purchaseDetailtemList.size();i++)
				{
					purchaseDetailtemList.get(i).setWholesaleRate(Float.parseFloat(request.getParameter("wholesaleRate"+i)));
					purchaseDetailtemList.get(i).setRetailRate(Float.parseFloat(request.getParameter("retailRate"+i)));
					totalTaxableAmt=totalTaxableAmt+purchaseDetailtemList.get(i).getTaxableAmt();
				}
				 
				PurchaseHeader insert = new PurchaseHeader();
				insert.setSuppId(suppId);
				insert.setInvoiceNo(invoiceNo);
				insert.setInvDate(invoiceDate);
				insert.setBasicValue(basicValue);
				insert.setDate(sf.format(date));
				insert.setTime(time.format(date));
				insert.setDiscPer(discPerOnBill);
				insert.setDiscAmt(discAmt);
				insert.setDiscAmt2(discAmt2);
				insert.setFreightAmt(freightAmt);
				insert.setInsuranceAmt(insuranceAmt);
				insert.setTaxableAmt(Float.valueOf(df.format(totalTaxableAmt)));
				insert.setCgst(cgst);
				insert.setSgst(sgst);
				insert.setIgst(igst);
				insert.setCess(cess);
				insert.setBillAmt(billAmount); 
				insert.setOtherExtra(extraCharges);
				insert.setVehicleNo(vehicleNo);
				insert.setPurchaseNo("");
				
				List<PurchaseDetail> purchaseDetailList = new ArrayList<PurchaseDetail>();
				
				for(int i=0;i<purchaseDetailtemList.size();i++)
				{
					PurchaseDetail purchaseDetail = new PurchaseDetail();
					purchaseDetail.setItemName(purchaseDetailtemList.get(i).getItemName());
					purchaseDetail.setItemId(purchaseDetailtemList.get(i).getItemId());
					purchaseDetail.setItemUom(purchaseDetailtemList.get(i).getItemUom());
					purchaseDetail.setRecQty(purchaseDetailtemList.get(i).getRecQty());
					purchaseDetail.setBalance(purchaseDetailtemList.get(i).getRecQty());
					purchaseDetail.setRate(purchaseDetailtemList.get(i).getRate());
					purchaseDetail.setValue(purchaseDetailtemList.get(i).getValue());
					purchaseDetail.setDiscPer(purchaseDetailtemList.get(i).getDiscPer());
					purchaseDetail.setDiscAmt(purchaseDetailtemList.get(i).getDiscAmt());
					purchaseDetail.setFreightAmt(purchaseDetailtemList.get(i).getFreightAmt());
					purchaseDetail.setInsuAmt(purchaseDetailtemList.get(i).getInsuAmt());
					purchaseDetail.setCgstPer(purchaseDetailtemList.get(i).getCgstPer());
					purchaseDetail.setCgstRs(purchaseDetailtemList.get(i).getCgstRs());
					purchaseDetail.setSgstPer(purchaseDetailtemList.get(i).getSgstPer());
					purchaseDetail.setSgstRs(purchaseDetailtemList.get(i).getSgstRs());
					purchaseDetail.setIgstPer(purchaseDetailtemList.get(i).getIgstPer());
					purchaseDetail.setIgstRs(purchaseDetailtemList.get(i).getIgstRs()); 
					purchaseDetail.setCessPer(purchaseDetailtemList.get(i).getCessPer());
					purchaseDetail.setCessRs(purchaseDetailtemList.get(i).getCessRs());
					purchaseDetail.setTaxableAmt(purchaseDetailtemList.get(i).getTaxableAmt());
					purchaseDetail.setTotal(purchaseDetailtemList.get(i).getTotal());
					purchaseDetail.setRoundOff(purchaseDetailtemList.get(i).getRoundOff());
					purchaseDetail.setDiscOnBill(purchaseDetailtemList.get(i).getDiscOnBill());
					purchaseDetail.setOtherExtra(purchaseDetailtemList.get(i).getOtherExtra());
					purchaseDetail.setBatchNo(purchaseDetailtemList.get(i).getBatchNo());
					purchaseDetail.setRateWithTax(purchaseDetailtemList.get(i).getRateWithTax());
					purchaseDetail.setRateWithoutTax(purchaseDetailtemList.get(i).getRateWithoutTax());
					purchaseDetail.setWholesaleRate(purchaseDetailtemList.get(i).getWholesaleRate());
					purchaseDetail.setRetailRate(purchaseDetailtemList.get(i).getRetailRate());
					purchaseDetail.setExpiryDate(purchaseDetailtemList.get(i).getExpiryDate());
					purchaseDetailList.add(purchaseDetail);
				}
				insert.setPurchaseDetailList(purchaseDetailList);
				PurchaseHeader purchaseHeader = rest.postForObject(Constants.url + "postPurchaseHeader",insert, PurchaseHeader.class);
				System.out.println("purchaseHeader " + purchaseHeader);
				
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return "redirect:/purchaseBillList";
	}
	
	@RequestMapping(value = "/purchaseBillList", method = RequestMethod.GET)
	public ModelAndView purchaseBillList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchase/purchaseBillList");
		
	
		return model;
	}
	
	@RequestMapping(value = "/purchaseBillBetweenDate", method = RequestMethod.GET)
	@ResponseBody
	public List<PurchaseHeader> purchaseBillBetweenDate(HttpServletRequest request, HttpServletResponse response) {
		
		List<PurchaseHeader> purchaseHeaderList = new ArrayList<PurchaseHeader>();
		
		try {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			 map.add("toDate", DateConvertor.convertToYMD(toDate));
			 PurchaseHeader[] purchaseHeader = rest.postForObject(Constants.url + "getPurchaseHeader",map, PurchaseHeader[].class);
			 purchaseHeaderList = new ArrayList<PurchaseHeader>(Arrays.asList(purchaseHeader));
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return purchaseHeaderList;
	}
	
	@RequestMapping(value = "/purchaseHeaderWithDetail/{purchaseId}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView purchaseHeaderWithDetail(@PathVariable int purchaseId, HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("purchase/purchaseHeaderWithDetail");
	try {
		
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("purchaseId",purchaseId); 
			PurchaseHeader purchaseHeader = rest.postForObject(Constants.url + "getPurchaseHeaderAndDetailWithId",map, PurchaseHeader.class);
			 
			map = new LinkedMultiValueMap<String, Object>();
			map.add("flag",1);
			SupplierMaster[] supplierMaster = rest.postForObject(Constants.url + "getSuppllierList",map,
			SupplierMaster[].class);
				ArrayList<SupplierMaster> supplierList = new ArrayList<SupplierMaster>(Arrays.asList(supplierMaster));
			
			 model.addObject("purchaseHeader",purchaseHeader);
			 model.addObject("supplierList",supplierList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/itemListForBarcode/{purchaseId}", method = RequestMethod.GET)
	public ModelAndView generateBarcode(@PathVariable int purchaseId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchase/generateBarcode");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("purchaseId",purchaseId); 
		PurchaseHeader purchaseHeader = rest.postForObject(Constants.url + "getPurchaseHeaderAndDetailWithId",map, PurchaseHeader.class);
		 
		map = new LinkedMultiValueMap<String, Object>();
		map.add("flag",1);
		SupplierMaster[] supplierMaster = rest.postForObject(Constants.url + "getSuppllierList",map,
		SupplierMaster[].class);
			ArrayList<SupplierMaster> supplierList = new ArrayList<SupplierMaster>(Arrays.asList(supplierMaster));
			purchaseDetailListForBarcode = purchaseHeader.getPurchaseDetailList();
		 model.addObject("purchaseHeader",purchaseHeader);
		 model.addObject("supplierList",supplierList);
	
		return model;
	}
	
	@RequestMapping(value = "/generateBarcode", method = RequestMethod.POST)
	public void  generateBarcode(HttpServletRequest request, HttpServletResponse response) {

	 
		try
		{
			 List<ItemBarcode> itemBarcodelist = new ArrayList<ItemBarcode>();
			
			String []checkbox=request.getParameterValues("select_to_approve");
			for (int i = 0; i < purchaseDetailListForBarcode.size(); i++) 
			{
				 
				for(int j=0;j<checkbox.length;j++) 
				{
					 
					if(Integer.parseInt(checkbox[j])==purchaseDetailListForBarcode.get(i).getPurDetailId())  
					{
						
						 ItemBarcode itemBarcode = new ItemBarcode();
						 itemBarcode.setItemId(purchaseDetailListForBarcode.get(i).getItemId());
						 itemBarcode.setItemName(purchaseDetailListForBarcode.get(i).getItemName());
						 itemBarcode.setBatchNo(purchaseDetailListForBarcode.get(i).getBatchNo());
						 itemBarcode.setQty(purchaseDetailListForBarcode.get(i).getRecQty());
						 itemBarcode.setExpireDate(purchaseDetailListForBarcode.get(i).getExpiryDate());
						 itemBarcodelist.add(itemBarcode);
					}
						 
				}
			}
			System.out.println("itemBarcodelist " + itemBarcodelist.size() +itemBarcodelist);
			 
			File file = new File("prod.txt");

			try (Writer writer = new BufferedWriter(new FileWriter(file))) {

				for (ItemBarcode prod : itemBarcodelist) {

					int q = prod.getQty();

					for (int i = 1; i <= q; i++) {

					 

						String generalSetting = "SIZE 47.5 mm, 24.5 mm" + System.getProperty("line.separator") + "SPEED 3"
								+ System.getProperty("line.separator") + "DENSITY 17" + System.getProperty("line.separator")
								+ "SET RIBBON ON" + System.getProperty("line.separator") + "DIRECTION 0,0"
								+ System.getProperty("line.separator") + "REFERENCE 0,0"
								+ System.getProperty("line.separator") + "OFFSET 0 mm"
								+ System.getProperty("line.separator") + "SET PEEL OFF"
								+ System.getProperty("line.separator") + "SET TEAR ON"
								+ System.getProperty("line.separator") + "CLS" + System.getProperty("line.separator")
								+ "CODEPAGE 850" + System.getProperty("line.separator")
								+ "TEXT 347,135,\"ROMAN.TTF\",180,1,10,\"Item Name\"";
						
						String itemName = generalSetting + System.getProperty("line.separator") + 
								"TEXT 235,145,\"ROMAN.TTF\",180,1,16,\""
								+ prod.getItemName() + "\"";

						String barcode = itemName + System.getProperty("line.separator") + 
								"BARCODE 347,100,\"128M\",54,0,180,2,4,\""
								+ prod.getBatchNo() + "\"";

						String text = barcode + System.getProperty("line.separator")
								+ "TEXT 347,40,\"ROMAN.TTF\",180,1,10,\"" + prod.getBatchNo() + "\"";
						
						String expireDate = text + System.getProperty("line.separator")
						+ "TEXT 347,40,\"ROMAN.TTF\",180,1,10,\"" + prod.getExpireDate() + "\"";
 

						String contents = expireDate + System.getProperty("line.separator") + "PRINT 1,1"
								+ System.getProperty("line.separator");

						writer.write(contents);

					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("file " + file.getAbsolutePath());

			String mimeType = URLConnection.guessContentTypeFromName(file.getName());

			if (mimeType == null) {

				System.out.println("mimetype is not detectable");
				mimeType = "application/octet-stream";

			}

			System.out.println("mimetype : " + mimeType);

			response.setContentType(mimeType);

			response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

			// response.setHeader("Content-Disposition", String.format("attachment;
			// filename=\"%s\"", file.getName()));

			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			try {
				FileCopyUtils.copy(inputStream, response.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	  
	}

}
