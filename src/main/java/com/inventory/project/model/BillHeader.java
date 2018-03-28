package com.inventory.project.model;

import java.util.List;


public class BillHeader{


	private int billNo; 

	private String invoiceNo; 
	
	private String invoiceDate; 
	
	private int custId;
	
	private String custName; 
	
	private String gstin;
	
	private int custType;
	
	private float taxableAmt;
	
	private float cgstRs;
	
	private float sgstRs;
	
	private float igstRs;
	
	private float cessRs;
	
	private float taxAmt;
	
	private float grandTotal;
	
	private float discountPer;

	private float discountAmt;
	
	private float paidAmt;
	
	private float remAmt;
	
	private int billStatus;
	
	private String remark;
	
	private String expiryDate;

	List<BillDetail> billDetailList;
	
	
	public float getDiscountPer() {
		return discountPer;
	}

	public void setDiscountPer(float discountPer) {
		this.discountPer = discountPer;
	}

	public float getCgstRs() {
		return cgstRs;
	}

	public void setCgstRs(float cgstRs) {
		this.cgstRs = cgstRs;
	}

	public float getSgstRs() {
		return sgstRs;
	}

	public void setSgstRs(float sgstRs) {
		this.sgstRs = sgstRs;
	}

	public float getIgstRs() {
		return igstRs;
	}

	public void setIgstRs(float igstRs) {
		this.igstRs = igstRs;
	}

	public float getCessRs() {
		return cessRs;
	}

	public void setCessRs(float cessRs) {
		this.cessRs = cessRs;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public List<BillDetail> getBillDetailList() {
		return billDetailList;
	}

	public void setBillDetailList(List<BillDetail> billDetailList) {
		this.billDetailList = billDetailList;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public int getCustType() {
		return custType;
	}

	public void setCustType(int custType) {
		this.custType = custType;
	}

	public float getTaxableAmt() {
		return taxableAmt;
	}

	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	public float getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(float taxAmt) {
		this.taxAmt = taxAmt;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public float getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(float discountAmt) {
		this.discountAmt = discountAmt;
	}

	public float getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(float paidAmt) {
		this.paidAmt = paidAmt;
	}

	public float getRemAmt() {
		return remAmt;
	}

	public void setRemAmt(float remAmt) {
		this.remAmt = remAmt;
	}

	public int getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(int billStatus) {
		this.billStatus = billStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "BillHeader [billNo=" + billNo + ", invoiceNo=" + invoiceNo + ", invoiceDate=" + invoiceDate
				+ ", custId=" + custId + ", custName=" + custName + ", gstin=" + gstin + ", custType=" + custType
				+ ", taxableAmt=" + taxableAmt + ", cgstRs=" + cgstRs + ", sgstRs=" + sgstRs + ", igstRs=" + igstRs
				+ ", cessRs=" + cessRs + ", taxAmt=" + taxAmt + ", grandTotal=" + grandTotal + ", discountPer="
				+ discountPer + ", discountAmt=" + discountAmt + ", paidAmt=" + paidAmt + ", remAmt=" + remAmt
				+ ", billStatus=" + billStatus + ", remark=" + remark + ", expiryDate=" + expiryDate
				+ ", billDetailList=" + billDetailList + "]";
	}

     
}
