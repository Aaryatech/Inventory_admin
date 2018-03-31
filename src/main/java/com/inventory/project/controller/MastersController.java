package com.inventory.project.controller;

 
import java.util.ArrayList;
import java.util.Arrays;
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
import com.inventory.project.model.CustomerMaster;
import com.inventory.project.model.Info;
import com.inventory.project.model.ItemCategory;
import com.inventory.project.model.ItemGroup;
import com.inventory.project.model.ItemMaster;
import com.inventory.project.model.ItemUom;
import com.inventory.project.model.SupplierMaster;
import com.inventory.project.model.User;


@Controller
@Scope("session")
public class MastersController {
	
	
	
	@RequestMapping(value = "/addSupplier", method = RequestMethod.GET)
	public ModelAndView showAddSupplier(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addSupplier");
		

		return model;
	}
	
	@RequestMapping(value = "/getSupplierList", method = RequestMethod.GET)
	public ModelAndView getSupplierList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/allSupplierList");
		RestTemplate rest = new RestTemplate();
		try {
			
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			SupplierMaster[] supplierMaster = rest.postForObject(Constants.url + "getSuppllierList",map,
					SupplierMaster[].class);
			ArrayList<SupplierMaster> supplierList = new ArrayList<SupplierMaster>(Arrays.asList(supplierMaster));
			System.out.println("supplierList " + supplierList);
			model.addObject("supplierList",supplierList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return model;
	}
	
	@RequestMapping(value = "/editSupplier/{suppId}", method = RequestMethod.GET)
	public ModelAndView editSupplier(@PathVariable int suppId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addSupplier");
		RestTemplate rest = new RestTemplate();
		try {
			 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("suppId", suppId);
			SupplierMaster supplierMaster = rest.postForObject(Constants.url + "getSuppllierById",map,
					SupplierMaster.class);
		  
			model.addObject("supplierMaster",supplierMaster);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return model;
	}
	
	@RequestMapping(value = "/deleteSupplier/{suppId}", method = RequestMethod.GET)
	public String deleteSupplier(@PathVariable int suppId, HttpServletRequest request, HttpServletResponse response) {
 
		
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("suppId", suppId); 
			RestTemplate rest = new RestTemplate();
			  
			Info info = rest.postForObject(Constants.url + "deleteSuppllier",map,
					Info.class);
			 
			System.out.println("itemUom " + info);
		 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/getSupplierList";
	}
	
	@RequestMapping(value = "/insertSupplier", method = RequestMethod.POST)
	public String insertSupplier(HttpServletRequest request, HttpServletResponse response) {
 
		
		try {
			 
			String suppId = request.getParameter("suppId");
			String supp_name = request.getParameter("supp_name"); 
			String supp_gstin = request.getParameter("supp_gstin");
			String supp_addr = request.getParameter("supp_addr");
			String supp_city = request.getParameter("supp_city");
			String supp_state = request.getParameter("supp_state");
			String supp_country = request.getParameter("supp_country");
			String supp_mob1 = request.getParameter("supp_mob1");
			String supp_email1 = request.getParameter("supp_email1");
			String supp_mob2 = request.getParameter("supp_mob2");
			String supp_email2 = request.getParameter("supp_email2");
			String supp_mob3 = request.getParameter("supp_mob3");
			String supp_email3 = request.getParameter("supp_email3"); 
			String supp_phone1 = request.getParameter("supp_phone1");
			String supp_email4 = request.getParameter("supp_email4");
			String supp_phone2 = request.getParameter("supp_phone2");
			String supp_email5 = request.getParameter("supp_email5");
			String supp_panno = request.getParameter("supp_panno");
			
			String supp_c_person = request.getParameter("supp_c_person");
			String supp_fdalic = request.getParameter("supp_fdalic");
			String supp_pay_id = request.getParameter("supp_pay_id");
			String credit_days = request.getParameter("credit_days"); 
			int isSameState = Integer.parseInt(request.getParameter("isSameState"));
			
			
			RestTemplate rest = new RestTemplate();
			SupplierMaster insert = new SupplierMaster();
			if(suppId==null || suppId=="")
				insert.setSuppId(0);
			else
				insert.setSuppId(Integer.parseInt(suppId));
			insert.setSuppName(supp_name);
			insert.setGstinNo(supp_gstin);
			insert.setAddress(supp_addr);
			insert.setCity(supp_city);
			insert.setState(supp_state);
			insert.setCountry(supp_country);
			insert.setMobile1(supp_mob1);
			insert.setEmail1(supp_email1);
			insert.setMobile2(supp_mob2);
			insert.setEmail2(supp_email2);
			insert.setMobile3(supp_mob3);
			insert.setEmail3(supp_email3);
			insert.setPhone1(supp_phone1);
			insert.setPhone2(supp_phone2);
			insert.setEmail5(supp_email5);
			insert.setEmail4(supp_email4);
			insert.setPanNo(supp_panno);
			insert.setContactPrsn(supp_c_person);
			insert.setFdaLic(supp_fdalic);
			insert.setSupplierPayId(supp_pay_id);
			insert.setCreditDays(Integer.parseInt(credit_days));
			insert.setIsSameState(isSameState);
			SupplierMaster supplierMaster = rest.postForObject(Constants.url + "postSuppllier",insert,
					SupplierMaster.class);
			 
			System.out.println("supplierMaster " + supplierMaster);
		 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/getSupplierList";
	}
	
	@RequestMapping(value = "/addUom", method = RequestMethod.GET)
	public ModelAndView addUom(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addUom");
		
		try {
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			ItemUom[] itemUom = rest.postForObject(Constants.url + "getAllUomList",map,
					ItemUom[].class);
			ArrayList<ItemUom> itemUomList = new ArrayList<ItemUom>(Arrays.asList(itemUom));
			System.out.println("itemUomList " + itemUomList);
			model.addObject("itemUomList",itemUomList);
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/insertItemUom", method = RequestMethod.POST)
	public String insertItemUom(HttpServletRequest request, HttpServletResponse response) {
 
		
		try {
			 
			String uomId = request.getParameter("uomId");
			String uomName = request.getParameter("uomName");
			System.out.println("uomName" +uomName);
			RestTemplate rest = new RestTemplate();
			ItemUom insert = new ItemUom();
			if(uomId==null || uomId=="")
				insert.setUomId(0);
			else
				insert.setUomId(Integer.parseInt(uomId));
			insert.setUomName(uomName);
			
			ItemUom itemUom = rest.postForObject(Constants.url + "postUom",insert,
					ItemUom.class);
			 
			System.out.println("itemUom " + itemUom);
		 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addUom";
	}
	
	@RequestMapping(value = "/deleteItemUom/{uomId}", method = RequestMethod.GET)
	public String deleteItemUom(@PathVariable int uomId, HttpServletRequest request, HttpServletResponse response) {
 
		
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("uomId", uomId); 
			RestTemplate rest = new RestTemplate();
			  
			Info info = rest.postForObject(Constants.url + "deleteUom",map,
					Info.class);
			 
			System.out.println("itemUom " + info);
		 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addUom";
	}
	
	@RequestMapping(value = "/editUom", method = RequestMethod.GET)
	@ResponseBody
	public ItemUom editUom(HttpServletRequest request, HttpServletResponse response) {

		ItemUom itemUom = new ItemUom();
		try {
			int uomId = Integer.parseInt(request.getParameter("uomId"));
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("uomId", uomId);
			  itemUom = rest.postForObject(Constants.url + "getUomById",map,
					ItemUom.class);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return itemUom;
	}
	
	@RequestMapping(value = "/addGroup", method = RequestMethod.GET)
	public ModelAndView addGroup(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addGroup");
		try {
		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("flag", 0);
		ItemGroup[] itemGroup = rest.postForObject(Constants.url + "getAllGroupList",map,
				ItemGroup[].class);
		ArrayList<ItemGroup> itemGroupList = new ArrayList<ItemGroup>(Arrays.asList(itemGroup));
		System.out.println("itemGroupList " + itemGroupList);
		model.addObject("itemGroupList",itemGroupList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/insertItemGroup", method = RequestMethod.POST)
	public String insertItemGroup(HttpServletRequest request, HttpServletResponse response) {
 
		
		try {
			 
			String groupId = request.getParameter("groupId");
			String groupName = request.getParameter("groupName"); 
			RestTemplate rest = new RestTemplate();
			ItemGroup insert = new ItemGroup();
			if(groupId==null || groupId=="")
				insert.setGroupId(0);
			else
				insert.setGroupId(Integer.parseInt(groupId));
			insert.setGroupName(groupName);
			
			ItemGroup itemGroup = rest.postForObject(Constants.url + "postGroup",insert,
					ItemGroup.class);
			 
			System.out.println("itemGroup " + itemGroup);
		 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addGroup";
	}
	
	@RequestMapping(value = "/deleteItemGroup/{groupId}", method = RequestMethod.GET)
	public String deleteItemGroup(@PathVariable int groupId, HttpServletRequest request, HttpServletResponse response) {
 
		
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("groupId", groupId); 
			RestTemplate rest = new RestTemplate();
			  
			Info info = rest.postForObject(Constants.url + "deleteGroup",map,
					Info.class);
			 
			System.out.println("info " + info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addGroup";
	}
	
	@RequestMapping(value = "/editItemGroup", method = RequestMethod.GET)
	@ResponseBody
	public ItemGroup editItemGroup(HttpServletRequest request, HttpServletResponse response) {

		ItemGroup itemGroup = new ItemGroup();
		try {
			int groupId = Integer.parseInt(request.getParameter("groupId"));
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("groupId", groupId);
			itemGroup = rest.postForObject(Constants.url + "getGroupById",map,
					ItemGroup.class);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return itemGroup;
	}
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView addCategory(HttpServletRequest request, HttpServletResponse response) {

		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		ModelAndView model = new ModelAndView("masters/addCategory");
		
		try {
			
					
				map.add("flag", 1);
				ItemGroup[] allGroup = rest.postForObject(Constants.url + "getAllGroupList",map, ItemGroup[].class);
				ArrayList<ItemGroup> allGroupList = new ArrayList<ItemGroup>(Arrays.asList(allGroup));
				
				map = new LinkedMultiValueMap<String, Object>();
				map.add("flag", 0);
				ItemGroup[] activeGroup = rest.postForObject(Constants.url + "getAllGroupList",map, ItemGroup[].class);
				ArrayList<ItemGroup> activeGroupList = new ArrayList<ItemGroup>(Arrays.asList(activeGroup));
			  
				map = new LinkedMultiValueMap<String, Object>();
				map.add("flag", 0);
				ItemCategory[] itemCategory = rest.postForObject(Constants.url + "getCategoryList",map,
						ItemCategory[].class); 
				ArrayList<ItemCategory> itemCategoryList = new ArrayList<ItemCategory>(Arrays.asList(itemCategory)); 
				
				model.addObject("itemCategoryList",itemCategoryList);
				model.addObject("activeGroupList",activeGroupList);
				model.addObject("itemGroupList",allGroupList);
			}catch(Exception e)
			{
				e.printStackTrace();
			}

		return model;
	}
	
	@RequestMapping(value = "/insertCategory", method = RequestMethod.POST)
	public String insertCategory(HttpServletRequest request, HttpServletResponse response) {
 
		RestTemplate rest = new RestTemplate();
		try {
			
			String catId = request.getParameter("catId");
			int groupId = Integer.parseInt(request.getParameter("groupId"));
			String catName = request.getParameter("catName");
			String desc = request.getParameter("catDesc");
			
			ItemCategory insert = new ItemCategory();
			if(catId==null || catId=="")
				insert.setCatId(0);
			else
				insert.setCatId(Integer.parseInt(catId));
			insert.setGroupId(groupId);
			insert.setCatName(catName);
			insert.setCatDesc(desc);
			
			ItemCategory itemCategory = rest.postForObject(Constants.url + "postCategory",insert,
					ItemCategory.class);
			 
			System.out.println("itemCategory " + itemCategory);
		 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addCategory";
	}
	
	@RequestMapping(value = "/editCategory", method = RequestMethod.GET)
	@ResponseBody
	public ItemCategory editCategory(HttpServletRequest request, HttpServletResponse response) {

		ItemCategory itemCategory = new ItemCategory();
		try {
			int catId = Integer.parseInt(request.getParameter("catId"));
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("catId", catId);
			itemCategory = rest.postForObject(Constants.url + "getCategoryById",map,
					ItemCategory.class);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return itemCategory;
	}
	
	@RequestMapping(value = "/deleteItemCategory/{catId}", method = RequestMethod.GET)
	public String deleteItemCategory(@PathVariable int catId, HttpServletRequest request, HttpServletResponse response) {
 
		
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("catId", catId); 
			RestTemplate rest = new RestTemplate();
			  
			Info info = rest.postForObject(Constants.url + "deleteCategory",map,
					Info.class);
			 
			System.out.println("info " + info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addCategory";
	}
	
	@RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
	public ModelAndView addCustomer(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addCustomer");
		
		try {
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			CustomerMaster[] customerMaster = rest.postForObject(Constants.url + "getCustomerList",map,
					CustomerMaster[].class); 
			ArrayList<CustomerMaster> customerList = new ArrayList<CustomerMaster>(Arrays.asList(customerMaster)); 
			
			model.addObject("customerList",customerList); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/insertCustomer", method = RequestMethod.POST)
	public String insertCustomer(HttpServletRequest request, HttpServletResponse response) {
 
		RestTemplate rest = new RestTemplate();
		try {
			 
			String custId = request.getParameter("custId");
			String custName = request.getParameter("custName"); 
			String gstin = request.getParameter("gstin");
			String custAdd = request.getParameter("custAdd");
			String custCode = request.getParameter("custCode");
			String mobileNo = request.getParameter("mobileNo");
			String email = request.getParameter("email");
			String contactNo = request.getParameter("contactNo"); 
			String conPersn = request.getParameter("conPersn");
			String conEmail = request.getParameter("conEmail");
			String custPan = request.getParameter("custPan");
			int custType = Integer.parseInt(request.getParameter("custType"));
			int isSameState = Integer.parseInt(request.getParameter("isSameState"));
			int creaditDays = Integer.parseInt(request.getParameter("creaditDays")); 
			 
			CustomerMaster insert = new CustomerMaster();
			if(custId==null || custId=="")
				insert.setCustId(0);
			else
				insert.setCustId(Integer.parseInt(custId));  
			insert.setCustName(custName);
			insert.setGstin(gstin);
			insert.setAddress(custAdd);
			insert.setCustCode(custCode);
			insert.setMobile(mobileNo);
			insert.setEmail(email);
			insert.setPhone1(contactNo);
			insert.setConctPrsn(conPersn);
			insert.setPrsnEmail(conEmail);
			insert.setPanNo(custPan);
			insert.setCustType(custType);
			insert.setIsSameState(isSameState);
			insert.setCreditDays(creaditDays);
			
			CustomerMaster customerMaster = rest.postForObject(Constants.url + "postCustomer",insert,
					CustomerMaster.class);
			 
			System.out.println("customerMaster " + customerMaster);
		 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addCustomer";
	}
	
	@RequestMapping(value = "/editCustomer", method = RequestMethod.GET)
	@ResponseBody
	public CustomerMaster editCustomer(HttpServletRequest request, HttpServletResponse response) {

		CustomerMaster customerMaster = new CustomerMaster();
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
	
	@RequestMapping(value = "/deleteCustomer/{custId}", method = RequestMethod.GET)
	public String deleteCustomer(@PathVariable int custId, HttpServletRequest request, HttpServletResponse response) {
 
		
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("custId", custId); 
			RestTemplate rest = new RestTemplate();
			  
			Info info = rest.postForObject(Constants.url + "deleteCustomer",map,
					Info.class);
			 
			System.out.println("info " + info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addCustomer";
	}
	
	@RequestMapping(value = "/addItem", method = RequestMethod.GET)
	public ModelAndView addItem(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addItem");
		
		try {
			
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			ItemGroup[] itemGroup = rest.postForObject(Constants.url + "getAllGroupList",map,
					ItemGroup[].class);
			ArrayList<ItemGroup> itemGroupList = new ArrayList<ItemGroup>(Arrays.asList(itemGroup));
			
			map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			ItemUom[] itemUom = rest.postForObject(Constants.url + "getAllUomList",map,
					ItemUom[].class);
			ArrayList<ItemUom> itemUomList = new ArrayList<ItemUom>(Arrays.asList(itemUom));
			
			model.addObject("itemGroupList",itemGroupList);
			model.addObject("itemUomList",itemUomList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/getItemList", method = RequestMethod.GET)
	public ModelAndView getItemList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/allItemList");
		RestTemplate rest = new RestTemplate();
		try {
			 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 1);
			ItemGroup[] itemGroup = rest.postForObject(Constants.url + "getAllGroupList",map,
					ItemGroup[].class);
			ArrayList<ItemGroup> itemGroupList = new ArrayList<ItemGroup>(Arrays.asList(itemGroup));
			
			ItemCategory[] itemCategory = rest.postForObject(Constants.url + "getCategoryList",map,
					ItemCategory[].class); 
			ArrayList<ItemCategory> itemCategoryList = new ArrayList<ItemCategory>(Arrays.asList(itemCategory));
			
			 map = new LinkedMultiValueMap<String, Object>();
			 map.add("flag", 0);
			 ItemMaster[] itemMaster = rest.postForObject(Constants.url + "getItemList",map, ItemMaster[].class);
				ArrayList<ItemMaster> itemList = new ArrayList<ItemMaster>(Arrays.asList(itemMaster));
			
			model.addObject("companyList",itemGroupList);
			model.addObject("itemCategoryList",itemCategoryList);
			model.addObject("itemList",itemList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return model;
	}
	
	@RequestMapping(value = "/getCatListByGroupId", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemCategory> getCatListByGroupId(HttpServletRequest request, HttpServletResponse response) {

		List<ItemCategory> itemCategoryList = new ArrayList<ItemCategory>();
		try {
			 int groupId = Integer.parseInt(request.getParameter("grpId"));
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map = new LinkedMultiValueMap<String, Object>();
			map.add("groupId", groupId);
			ItemCategory[] itemCategory = rest.postForObject(Constants.url + "getCatListByGroupId",map,
					ItemCategory[].class); 
			itemCategoryList = new ArrayList<ItemCategory>(Arrays.asList(itemCategory));
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return itemCategoryList;
	}
	
	@RequestMapping(value = "/editItem/{itemId}", method = RequestMethod.GET)
	public ModelAndView editItem(@PathVariable int itemId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addItem");
		
		try {
			
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			ItemGroup[] itemGroup = rest.postForObject(Constants.url + "getAllGroupList",map,
					ItemGroup[].class);
			ArrayList<ItemGroup> itemGroupList = new ArrayList<ItemGroup>(Arrays.asList(itemGroup));
			
			map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			ItemUom[] itemUom = rest.postForObject(Constants.url + "getAllUomList",map,
					ItemUom[].class);
			ArrayList<ItemUom> itemUomList = new ArrayList<ItemUom>(Arrays.asList(itemUom));
			
			map = new LinkedMultiValueMap<String, Object>();
			map.add("itemId", itemId);
			ItemMaster itemMaster = rest.postForObject(Constants.url + "getItemById",map,
					ItemMaster.class);
			
			map = new LinkedMultiValueMap<String, Object>();
			map = new LinkedMultiValueMap<String, Object>();
			map.add("groupId", itemMaster.getGroupId());
			ItemCategory[] itemCategory = rest.postForObject(Constants.url + "getCatListByGroupId",map,
					ItemCategory[].class); 
			ArrayList<ItemCategory> itemCategoryList = new ArrayList<ItemCategory>(Arrays.asList(itemCategory));
			
			model.addObject("itemCategoryList",itemCategoryList);
			model.addObject("itemMaster",itemMaster);
			model.addObject("itemGroupList",itemGroupList);
			model.addObject("itemUomList",itemUomList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/deleteItem/{itemId}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable int itemId, HttpServletRequest request, HttpServletResponse response) {
 
		
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("itemId", itemId); 
			RestTemplate rest = new RestTemplate();
			  
			Info info = rest.postForObject(Constants.url + "deleteItem",map,
					Info.class);
			 
			System.out.println("info " + info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/getItemList";
	}
	
	@RequestMapping(value = "/insertItem", method = RequestMethod.POST)
	public String insertItem(HttpServletRequest request, HttpServletResponse response) {
 
		RestTemplate rest = new RestTemplate();
		try {
			 
			String itemId = request.getParameter("itemId");
			String itemName = request.getParameter("itemName");
			int itemCode = Integer.parseInt(request.getParameter("itemCode"));
			int uomId = Integer.parseInt(request.getParameter("uomId"));
			//String rm_specification = request.getParameter("rm_specification");
			String uomName = request.getParameter("uomName");
			int groupId = Integer.parseInt(request.getParameter("groupId")); 
			int catId = Integer.parseInt(request.getParameter("catId"));
			float itemWeight = Float.parseFloat(request.getParameter("itemWeight")); 
			int packQty = Integer.parseInt(request.getParameter("packQty"));
			int itemMinQty = Integer.parseInt(request.getParameter("itemMinQty"));
			int itemMaxQty = Integer.parseInt(request.getParameter("itemMaxQty"));
			int itemRolQty = Integer.parseInt(request.getParameter("itemRolQty"));
			float cgst = Float.parseFloat(request.getParameter("cgst")); 
			float sgst = Float.parseFloat(request.getParameter("sgst")); 
			float igst = Float.parseFloat(request.getParameter("igst")); 
			float cess = Float.parseFloat(request.getParameter("cess"));
			int storeMinQty = Integer.parseInt(request.getParameter("storeMinQty"));
			int storeMaxQty = Integer.parseInt(request.getParameter("storeMaxQty"));
			int storeRolQty = Integer.parseInt(request.getParameter("storeRolQty"));
			int isCritical = Integer.parseInt(request.getParameter("isCritical")); 
			String hsnCode = request.getParameter("hsnCode");  
			 
			ItemMaster insert = new ItemMaster();
			if(itemId==null || itemId=="")
				insert.setItemId(0);
			else
				insert.setItemId(Integer.parseInt(itemId));
			insert.setItemCode(itemCode);
			insert.setItemName(itemName);
			insert.setUomId(uomId);
			insert.setUomName(uomName);
			insert.setGroupId(groupId);
			insert.setCatId(catId);
			insert.setWeight(itemWeight);
			insert.setPackQty(packQty);
			insert.setBmsMinQty(itemMinQty);
			insert.setBmsMaxQty(itemMaxQty);
			insert.setBmsRolQty(itemRolQty);
			insert.setCgst(cgst);
			insert.setSgst(sgst);
			insert.setIgst(igst);
			insert.setCess(cess);
			insert.setStoreMinQty(storeMinQty);
			insert.setStoreMaxQty(storeMaxQty);
			insert.setStoreRolQty(storeRolQty);
			insert.setIsCritical(isCritical);
			insert.setHsnCode(hsnCode);
			
			ItemMaster itemMaster = rest.postForObject(Constants.url + "postItem",insert,
					ItemMaster.class);
			 
			System.out.println("itemMaster " + itemMaster);
		 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/getItemList";
	}
	 
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) {

		  
		ModelAndView model = new ModelAndView("masters/addUser");
		try {
			
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			User[] user = rest.postForObject(Constants.url + "getUserList",map,
					User[].class);
			ArrayList<User> userList = new ArrayList<User>(Arrays.asList(user));
			
			model.addObject("userList",userList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/insertNewUser", method = RequestMethod.POST)
	public String insertNewUser(HttpServletRequest request, HttpServletResponse response) {
 
		RestTemplate rest = new RestTemplate();
		try {
			 
			String userId = request.getParameter("userId");
			String uname = request.getParameter("uname");
			String upass = request.getParameter("upass");
			int userType = Integer.parseInt(request.getParameter("userType"));  
			 
			User insert = new User();
			if(userId==null || userId=="")
				insert.setUserId(0);
			else
				insert.setUserId(Integer.parseInt(userId));
			insert.setUserName(uname);
			insert.setPassword(upass);
			insert.setUserType(userType);
			
			User user = rest.postForObject(Constants.url + "postUser",insert,
					User.class);
			 
			System.out.println("user " + user);
		 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addUser";
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	@ResponseBody
	public User editUser(HttpServletRequest request, HttpServletResponse response) {

		User user = new User();
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("userId", userId);
			user = rest.postForObject(Constants.url + "getUserById",map,
					User.class);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return user;
	}
	
	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable int userId, HttpServletRequest request, HttpServletResponse response) {
 
		
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("userId", userId); 
			RestTemplate rest = new RestTemplate();
			  
			Info info = rest.postForObject(Constants.url + "deleteUser",map,
					Info.class);
			 
			System.out.println("info " + info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addUser";
	}
	
	
	@RequestMapping(value = "/getSupplierListForPurchaseLis", method = RequestMethod.GET)
	@ResponseBody
	public List<SupplierMaster> getSupplierListForPurchaseLis(HttpServletRequest request, HttpServletResponse response) {
		
		RestTemplate rest = new RestTemplate();
		List<SupplierMaster> supplierList = new ArrayList<SupplierMaster>();
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("flag", 0);
			SupplierMaster[] supplierMaster = rest.postForObject(Constants.url + "getSuppllierList",map,
					SupplierMaster[].class);
			supplierList = new ArrayList<SupplierMaster>(Arrays.asList(supplierMaster));
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return supplierList;
	}
	

}
