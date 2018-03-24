package com.inventory.project.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
 

public class StockHeader {
	
	 
	private int stockId;  
	private String date; 
	private int status;
	private List<StockDetail> stockDetailList;
	
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public List<StockDetail> getStockDetailList() {
		return stockDetailList;
	}
	public void setStockDetailList(List<StockDetail> stockDetailList) {
		this.stockDetailList = stockDetailList;
	}
	@Override
	public String toString() {
		return "StockHeader [stockId=" + stockId + ", date=" + date + ", status=" + status + ", stockDetailList="
				+ stockDetailList + "]";
	}
	
	
	

}
