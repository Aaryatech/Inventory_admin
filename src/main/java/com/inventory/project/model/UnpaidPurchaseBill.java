package com.inventory.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UnpaidPurchaseBill {
	 
	private int purchaseId;  
	private String date;  
	private String invoiceNo; 
	private String invDate; 
	private int suppId;  
	private String suppName; 
	private float billAmt; 
	private String cdDate1; 
	private String cdDate2; 
	private String cdDate3; 
	private String cdDate4; 
	private int extra;
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public float getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(float billAmt) {
		this.billAmt = billAmt;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getCdDate1() {
		return cdDate1;
	}
	public void setCdDate1(String cdDate1) {
		this.cdDate1 = cdDate1;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getCdDate2() {
		return cdDate2;
	}
	public void setCdDate2(String cdDate2) {
		this.cdDate2 = cdDate2;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getCdDate3() {
		return cdDate3;
	}
	public void setCdDate3(String cdDate3) {
		this.cdDate3 = cdDate3;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getCdDate4() {
		return cdDate4;
	}
	public void setCdDate4(String cdDate4) {
		this.cdDate4 = cdDate4;
	}
	public int getExtra() {
		return extra;
	}
	public void setExtra(int extra) {
		this.extra = extra;
	}
	@Override
	public String toString() {
		return "UnpaidPurchaseBill [purchaseId=" + purchaseId + ", date=" + date + ", invoiceNo=" + invoiceNo
				+ ", invDate=" + invDate + ", suppId=" + suppId + ", suppName=" + suppName + ", billAmt=" + billAmt
				+ ", cdDate1=" + cdDate1 + ", cdDate2=" + cdDate2 + ", cdDate3=" + cdDate3 + ", cdDate4=" + cdDate4
				+ ", extra=" + extra + "]";
	}
	
	
	 
	

}
