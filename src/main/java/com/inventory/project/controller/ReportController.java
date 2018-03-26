package com.inventory.project.controller;


import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.inventory.project.common.Constants;
import com.inventory.project.model.BillDetail;
import com.inventory.project.model.BillHeader;


@Controller
@Scope("session")
public class ReportController {
	
	
	@RequestMapping(value = "/saleReportGroupByDate", method = RequestMethod.GET)
	public ModelAndView saleReportGroupByDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/report/saleGroupByDate");
	
		return model;
	}
	@RequestMapping(value = "/saleReportByDate", method = RequestMethod.GET)
	public ModelAndView saleReportByDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/report/saleDateWise");
	
		return model;
	}
	@RequestMapping(value = "/saleByMonthWise", method = RequestMethod.GET)
	public ModelAndView saleByMonthWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/report/saleByMonthwise");
	
		return model;
	}
	@RequestMapping(value = "/saleByItemAndHsncodeWise", method = RequestMethod.GET)
	public ModelAndView saleByItemAndHsncodeWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/report/saleItemwise");
	
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
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return model;
}
}