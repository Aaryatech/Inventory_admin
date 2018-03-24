package com.inventory.project.controller;
 
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.inventory.project.common.Constants;
import com.inventory.project.common.DateConvertor;
import com.inventory.project.model.GetCurrentStock;
import com.inventory.project.model.ItemCategory;
import com.inventory.project.model.ItemStock; 
import com.inventory.project.model.StockDetail;
import com.inventory.project.model.StockHeader; 

@Controller
@Scope("session")
public class StockController {
	
	DecimalFormat df = new DecimalFormat("#.00");
	List<StockDetail> stockDetailList = new ArrayList<StockDetail>();
	StockHeader stockHeader = new StockHeader();
	ArrayList<GetCurrentStock> currentStockList = new ArrayList<GetCurrentStock>();
	
	
	@RequestMapping(value = "/itemStock", method = RequestMethod.GET)
	public ModelAndView getSupplierList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("stock/itemStock");
		RestTemplate rest = new RestTemplate();
		try {
			 String date;
			stockHeader = rest.getForObject(Constants.url + "getStock",
					StockHeader.class); 
			System.out.println("stockHeader " + stockHeader); 
			 stockDetailList = new ArrayList<StockDetail>();
			
			if(stockHeader.getStockId()!=0)
			{
				System.out.println("data available");
				 
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("stockId", stockHeader.getStockId());
				StockDetail[] itemStockDetail = rest.postForObject(Constants.url + "getStockDetail",map,
						StockDetail[].class);
				stockDetailList = new ArrayList<StockDetail>(Arrays.asList(itemStockDetail));
				
				ItemStock[] itemStock = rest.getForObject(Constants.url + "getFirstTimeItemStock",
						ItemStock[].class); 
				ArrayList<ItemStock> itemStockList = new ArrayList<ItemStock>(Arrays.asList(itemStock));
				for(int i=0;i<stockDetailList.size();i++)
				{
					for(int j=0;j<itemStockList.size();j++)
					{
						if(stockDetailList.get(i).getStockDetailId()==0 && stockDetailList.get(i).getItemId()==itemStockList.get(j).getItemId())
						{
							stockDetailList.get(i).setLastPurchaseRate(Float.valueOf(df.format(itemStockList.get(j).getRateWithTax())));
							stockDetailList.get(i).setLastWholesaleRate(Float.valueOf(df.format(itemStockList.get(j).getWholesaleRate())));
							stockDetailList.get(i).setLastRetailRate(Float.valueOf(df.format(itemStockList.get(j).getRetailRate())));
							break;
						}
					}
				}
				date = stockHeader.getDate();
			}
			else
			{
				System.out.println("data not available");
				ItemStock[] itemStock = rest.getForObject(Constants.url + "getFirstTimeItemStock",
						ItemStock[].class); 
				ArrayList<ItemStock> itemStockList = new ArrayList<ItemStock>(Arrays.asList(itemStock));
				
				 
					for(int j=0;j<itemStockList.size();j++)
					{
						StockDetail stockDetail = new StockDetail();
						stockDetail.setItemId(itemStockList.get(j).getItemId());
						stockDetail.setItemName(itemStockList.get(j).getItemName());
						stockDetail.setLastPurchaseRate(itemStockList.get(j).getRateWithTax());
						stockDetail.setLastWholesaleRate(itemStockList.get(j).getWholesaleRate());
						stockDetail.setLastRetailRate(itemStockList.get(j).getRetailRate());
						stockDetailList.add(stockDetail);
					}
					 Date todaydate = new Date();
					 SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
					 date=sf.format(todaydate);
			}
			
			model.addObject("stockDetailList",stockDetailList);
			model.addObject("date",date);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return model;
	}
	
	
	@RequestMapping(value = "/submitStock", method = RequestMethod.POST)
	public String submitStock(HttpServletRequest request, HttpServletResponse response) {

		 
		RestTemplate rest = new RestTemplate();
		try {
			 
			 for(int i=0;i<stockDetailList.size();i++)
			 {
				 stockDetailList.get(i).setOpeningStock(Integer.parseInt(request.getParameter("openQty"+stockDetailList.get(i).getItemId())));
				 stockDetailList.get(i).setLastPurchaseRate(Float.parseFloat(request.getParameter("purchaseRate"+stockDetailList.get(i).getItemId())));
				 stockDetailList.get(i).setLastWholesaleRate(Float.parseFloat(request.getParameter("wholesaleRate"+stockDetailList.get(i).getItemId())));
				 stockDetailList.get(i).setLastRetailRate(Float.parseFloat(request.getParameter("retailRate"+stockDetailList.get(i).getItemId())));
				 stockDetailList.get(i).setTotal(Float.parseFloat(request.getParameter("total"+stockDetailList.get(i).getItemId())));
			 }
			 
			 if(stockHeader.getStockId()!=0)
			 {
				 stockHeader.setStockDetailList(stockDetailList);
				 
				 StockHeader edit = rest.postForObject(Constants.url + "insertStock",stockHeader,
						 StockHeader.class); 
				 System.out.println("Edit "+edit);
			 }
			 else
			 {
				 
				 stockHeader = new StockHeader();
				 stockHeader.setDate(request.getParameter("date"));
				 stockHeader.setStockDetailList(stockDetailList); 
				 StockHeader insert = rest.postForObject(Constants.url + "insertStock",stockHeader,
						 StockHeader.class); 
				 System.out.println("insert "+insert);
			 }
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return "redirect:/itemStock";
	}
	
	StockHeader updateStatus = new StockHeader();
	private List<StockDetail> updateStockDetailList = new ArrayList<StockDetail>();
	
	@RequestMapping(value = "/getCurrentStock", method = RequestMethod.GET)
	public ModelAndView getCurrentStock(HttpServletRequest request, HttpServletResponse response) {
		
		
		ModelAndView model = new ModelAndView("stock/getCurrentStock");
		RestTemplate rest = new RestTemplate();
		try {
			
			List<ItemStock> itemStockList = new ArrayList<ItemStock>();
			updateStatus = rest.getForObject(Constants.url + "getStock",
					StockHeader.class); 
			 
			 
			 if(updateStatus.getStockId()!=0) 
			 { 
				 //for get cuurent rate
					
					
				 MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				 
				 map.add("stockId",updateStatus.getStockId());
				 StockDetail[] StockDetailList = rest.postForObject(Constants.url + "getStockDetailForUpdate",map,
						 StockDetail[].class);
				 updateStockDetailList = new ArrayList<StockDetail>(Arrays.asList(StockDetailList));  
				 
				 
				 map = new LinkedMultiValueMap<>();	
				map.add("date", DateConvertor.convertToYMD(updateStatus.getDate()));
				GetCurrentStock[] getCurrentStock = rest.postForObject(Constants.url + "getCurrentStock",map,
							GetCurrentStock[].class);
				 currentStockList = new ArrayList<GetCurrentStock>(Arrays.asList(getCurrentStock)); 
				 
				 ItemStock[] itemStock = rest.getForObject(Constants.url + "getFirstTimeItemStock",
							ItemStock[].class); 
					itemStockList = new ArrayList<ItemStock>(Arrays.asList(itemStock));
					
				 for(int i=0;i<currentStockList.size();i++)
				 { 
					 for(int j=0;j<itemStockList.size();j++)
					 {
						 if(currentStockList.get(i).getItemId()==itemStockList.get(j).getItemId())
						 {
							 currentStockList.get(i).setLastPurchaseRate(itemStockList.get(j).getRateWithTax());
							 currentStockList.get(i).setLastWholesaleRate(itemStockList.get(j).getWholesaleRate());
							 currentStockList.get(i).setLastRetailRate(itemStockList.get(j).getRetailRate());
							 
						 }
					 }
				 }
				 
				 map = new LinkedMultiValueMap<String, Object>();
					map.add("flag", 0);
					ItemCategory[] itemCategory = rest.postForObject(Constants.url + "getCategoryList",map,
							ItemCategory[].class); 
					ArrayList<ItemCategory> itemCategoryList = new ArrayList<ItemCategory>(Arrays.asList(itemCategory));
					
				 SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
					Date date = new Date();
					Calendar c = Calendar.getInstance();
					c.setTime(date);
					c.add(Calendar.DATE, 1);
					date=c.getTime();
					c.add(Calendar.DATE, -1);
					Date yesterDay=c.getTime();
				model.addObject("currentStockList",currentStockList);
				model.addObject("itemCategoryList",itemCategoryList);
				model.addObject("stockDate",updateStatus.getDate());
				model.addObject("tommorowDate",sf.format(date));
				model.addObject("yesterDay",sf.format(yesterDay));
			 }
				
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return model;
	}
	
	@RequestMapping(value = "/getBetweenDateStock", method = RequestMethod.GET)
	@ResponseBody
	public List<GetCurrentStock> getBetweenDateStock(HttpServletRequest request, HttpServletResponse response) {

		List<GetCurrentStock> getBetweenDateStock = new ArrayList<GetCurrentStock>();
		try {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			int catId = Integer.parseInt(request.getParameter("catId"));
			
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("catId", catId);
			GetCurrentStock[] betweenDate  = rest.postForObject(Constants.url + "getStockBetweenDate",map,
					GetCurrentStock[].class);
			getBetweenDateStock = new ArrayList<GetCurrentStock>(Arrays.asList(betweenDate));
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return getBetweenDateStock;
	}
	
	@RequestMapping(value = "/StockDayEnd", method = RequestMethod.POST)
	public String StockDayEnd(HttpServletRequest request, HttpServletResponse response) {

		 
		RestTemplate rest = new RestTemplate();
		try {
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			Date date = sf.parse(updateStatus.getDate());
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 1);
			date=c.getTime();
			System.out.println(date);
			
			
			
			for(int i=0;i<updateStockDetailList.size();i++)
			 {
				updateStockDetailList.get(i).setOpeningStock(Integer.parseInt(request.getParameter("openQty"+currentStockList.get(i).getItemId())));
				updateStockDetailList.get(i).setTotalPurchase(Integer.parseInt(request.getParameter("totalPurchase"+currentStockList.get(i).getItemId())));
				updateStockDetailList.get(i).setTotalSale(Integer.parseInt(request.getParameter("totalSale"+currentStockList.get(i).getItemId())));
				updateStockDetailList.get(i).setGrn(Integer.parseInt(request.getParameter("totalGrn"+currentStockList.get(i).getItemId())));
				updateStockDetailList.get(i).setClosingStock(Integer.parseInt(request.getParameter("closingQty"+currentStockList.get(i).getItemId())));
				updateStockDetailList.get(i).setLastPurchaseRate(Float.parseFloat(request.getParameter("lastPurchaseRate"+currentStockList.get(i).getItemId()))); 
				updateStockDetailList.get(i).setTotal(updateStockDetailList.get(i).getClosingStock()*updateStockDetailList.get(i).getLastPurchaseRate());
				updateStockDetailList.get(i).setLastWholesaleRate(Float.parseFloat(request.getParameter("lastWholesaleRate"+currentStockList.get(i).getItemId())));
				updateStockDetailList.get(i).setLastRetailRate(Float.parseFloat(request.getParameter("lastRetailRate"+currentStockList.get(i).getItemId())));
			 }
			
			updateStatus.setStatus(1);
			updateStatus.setStockDetailList(updateStockDetailList);
			
			StockHeader udateStockStatus = rest.postForObject(Constants.url + "insertStock",updateStatus,
					StockHeader.class); 
			
			 if(udateStockStatus!=null) 
			 {
				 StockHeader stockHeader = new StockHeader();
				 stockHeader.setDate(sf.format(date));
				 List<StockDetail> stockDetailList = new ArrayList<StockDetail>();
				 
				 
					
				 
				 for(int i=0;i<currentStockList.size();i++)
				 {
					 StockDetail stockDetail = new StockDetail(); 
					 stockDetail.setItemId(currentStockList.get(i).getItemId());
					 stockDetail.setItemName(currentStockList.get(i).getItemName());
					 stockDetail.setLastPurchaseRate(currentStockList.get(i).getLastPurchaseRate());  
					 stockDetail.setLastWholesaleRate(currentStockList.get(i).getLastWholesaleRate());
					 stockDetail.setLastRetailRate(currentStockList.get(i).getLastRetailRate());
					 stockDetail.setOpeningStock(Integer.parseInt(request.getParameter("closingQty"+currentStockList.get(i).getItemId())));
					 stockDetail.setTotal(stockDetail.getOpeningStock()*stockDetail.getLastPurchaseRate());
					 stockDetailList.add(stockDetail); 
				 }
				 
				
				 stockHeader.setStockDetailList(stockDetailList);
				 StockHeader insert = rest.postForObject(Constants.url + "insertStock",stockHeader,
						 StockHeader.class); 
				 System.out.println("insert "+insert);
				 
			 }
				
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return "redirect:/getCurrentStock";
	}

}
