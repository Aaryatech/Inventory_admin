package com.inventory.project.model;
 
import com.fasterxml.jackson.annotation.JsonFormat;

public class GrnList {
	 
	private int purchaseId;  
	private String invoiceNo; 
	private String invDate;  
	private int suppId;  
	private String suppName; 
	private String batchNo;
	private int itemId;  
	private String itemName; 
	private String hsnCode; 
	private int balance; 
	private float rateWithTax;
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
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
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public float getRateWithTax() {
		return rateWithTax;
	}
	public void setRateWithTax(float rateWithTax) {
		this.rateWithTax = rateWithTax;
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
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	@Override
	public String toString() {
		return "GrnList [purchaseId=" + purchaseId + ", invoiceNo=" + invoiceNo + ", invDate=" + invDate + ", suppId="
				+ suppId + ", suppName=" + suppName + ", batchNo=" + batchNo + ", itemId=" + itemId + ", itemName="
				+ itemName + ", hsnCode=" + hsnCode + ", balance=" + balance + ", rateWithTax=" + rateWithTax + "]";
	}
	
	

}
