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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.inventory.project.common.Constants;
import com.inventory.project.model.GetOrder;
import com.inventory.project.model.GetOrderDetail;
import com.inventory.project.model.ItemUom;

@Controller
@Scope("session")
public class OrderController {

	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ModelAndView showAddSupplier(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("order/orderHeader");
		try
		{
			RestTemplate rest = new RestTemplate();
			GetOrder[] orderList = rest.getForObject(Constants.url + "/order/getPendingOrderList",GetOrder[].class);
			ArrayList<GetOrder> orderListRes = new ArrayList<GetOrder>(Arrays.asList(orderList));
			model.addObject("orderList",orderListRes);
		
			
		}
        catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	@RequestMapping(value = "/searchOrders", method = RequestMethod.POST)
	public ModelAndView searchOrders(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("order/orderHeader");
		
		try {
			RestTemplate rest = new RestTemplate();
			String fromDate=request.getParameter("fromDate");
			String toDate=request.getParameter("toDate");
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("fromDate",fromDate);
			map.add("toDate",toDate);
			
			GetOrder[] orderList = rest.postForObject(Constants.url + "/order/getOrderList",map,GetOrder[].class);
			ArrayList<GetOrder> orderListRes = new ArrayList<GetOrder>(Arrays.asList(orderList));
			System.out.println("orderListRes " + orderListRes);
			
			
			model.addObject("orderList",orderListRes);
			model.addObject("fromDate",fromDate);
			model.addObject("toDate",toDate);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}
	@RequestMapping(value = "/orderDetail/{orderId}", method = RequestMethod.GET)
	public ModelAndView showOrderDetail(@PathVariable("orderId")int orderId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("order/orderDetail");
		try
		{
			RestTemplate rest = new RestTemplate();
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("orderId",orderId);
			GetOrderDetail[] orderDetailList = rest.postForObject(Constants.url + "/order/getOrderDetails",map,GetOrderDetail[].class);
			ArrayList<GetOrderDetail> orderDListRes = new ArrayList<GetOrderDetail>(Arrays.asList(orderDetailList));
			model.addObject("orderDetailList",orderDListRes);
			
		}
        catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	

}
