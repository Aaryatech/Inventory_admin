package com.inventory.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GrnGvnDetail {
	 
	private int grnDetailId;  
	private int grnId;  
	private String invoiceNo; 
	private String invoiceDate; 
	private int itemId; 
	private String itemName; 
	private String hsnCode;  
	private String batchNo; 
	private float rate; 
	private int qty; 
	private float total;
	public int getGrnDetailId() {
		return grnDetailId;
	}
	public void setGrnDetailId(int grnDetailId) {
		this.grnDetailId = grnDetailId;
	}
	public int getGrnId() {
		return grnId;
	}
	public void setGrnId(int grnId) {
		this.grnId = grnId;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "GrnGvnDetail [grnDetailId=" + grnDetailId + ", grnId=" + grnId + ", invoiceNo=" + invoiceNo
				+ ", invoiceDate=" + invoiceDate + ", itemId=" + itemId + ", itemName=" + itemName + ", hsnCode="
				+ hsnCode + ", batchNo=" + batchNo + ", rate=" + rate + ", qty=" + qty + ", total=" + total + "]";
	}
	
	

}
