package com.inventory.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ItemReplaceReport {
	 
	private int purDetailId; 
	private int suppId;  
	private String suppName;  
	private String invoiceNo; 
	private String invDate; 
	private String batchNo;  
	private int itemId; 
	private String itemName; 
	private int replaceQty;
	public int getPurDetailId() {
		return purDetailId;
	}
	public void setPurDetailId(int purDetailId) {
		this.purDetailId = purDetailId;
	}
	public int getSuppId() {
		return suppId;
	}
	public void setSuppId(int suppId) {
		this.suppId = suppId;
	}
	public String getSuppName() {
		return suppName;
	}
	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	} 
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getInvDate() {
		return invDate;
	}
	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
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
	public int getReplaceQty() {
		return replaceQty;
	}
	public void setReplaceQty(int replaceQty) {
		this.replaceQty = replaceQty;
	}
	@Override
	public String toString() {
		return "ItemReplaceReport [purDetailId=" + purDetailId + ", suppId=" + suppId + ", suppName=" + suppName
				+ ", invoiceNo=" + invoiceNo + ", invDate=" + invDate + ", batchNo=" + batchNo + ", itemId=" + itemId
				+ ", itemName=" + itemName + ", replaceQty=" + replaceQty + "]";
	}
	
	

}
