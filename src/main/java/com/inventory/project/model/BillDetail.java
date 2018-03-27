package com.inventory.project.model;


public class BillDetail {

	private int billDetailId; 
	
	private int billNo; 
	
	private int itemId; 
	
	private String itemName; 
	
	private String itemUom;
	
	private String itemHsncd; 
	
	private int billQty; 
	
	private float rate; 
	
	private float baseRate; 
	
	private String batchNo; 
	
	private float value; 
	
	private float taxableAmt; 
	
	private float cgstPer; 
	
	private float cgstRs; 
	
	private float sgstPer; 
	
	private float sgstRs; 
	
	private float igstPer; 
	
	private float igstRs; 
	
	private float cessPer; 
	
	private float cessRs; 
	
	private float taxAmt;
	
	private float discAmt;
	
	private float grandTotal;

	private int uniqueKey;
	
	
	
	public float getDiscAmt() {
		return discAmt;
	}

	public void setDiscAmt(float discAmt) {
		this.discAmt = discAmt;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getItemUom() {
		return itemUom;
	}

	public void setItemUom(String itemUom) {
		this.itemUom = itemUom;
	}

	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public float getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(float baseRate) {
		this.baseRate = baseRate;
	}

	public int getBillDetailId() {
		return billDetailId;
	}

	public void setBillDetailId(int billDetailId) {
		this.billDetailId = billDetailId;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
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

	public String getItemHsncd() {
		return itemHsncd;
	}

	public void setItemHsncd(String itemHsncd) {
		this.itemHsncd = itemHsncd;
	}

	public int getBillQty() {
		return billQty;
	}

	public void setBillQty(int billQty) {
		this.billQty = billQty;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

    
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public float getTaxableAmt() {
		return taxableAmt;
	}

	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	public float getCgstPer() {
		return cgstPer;
	}

	public void setCgstPer(float cgstPer) {
		this.cgstPer = cgstPer;
	}

	public float getCgstRs() {
		return cgstRs;
	}

	public void setCgstRs(float cgstRs) {
		this.cgstRs = cgstRs;
	}

	public float getSgstPer() {
		return sgstPer;
	}

	public void setSgstPer(float sgstPer) {
		this.sgstPer = sgstPer;
	}

	public float getSgstRs() {
		return sgstRs;
	}

	public void setSgstRs(float sgstRs) {
		this.sgstRs = sgstRs;
	}

	public float getIgstPer() {
		return igstPer;
	}

	public void setIgstPer(float igstPer) {
		this.igstPer = igstPer;
	}

	public float getIgstRs() {
		return igstRs;
	}

	public void setIgstRs(float igstRs) {
		this.igstRs = igstRs;
	}

	public float getCessPer() {
		return cessPer;
	}

	public void setCessPer(float cessPer) {
		this.cessPer = cessPer;
	}

	public float getCessRs() {
		return cessRs;
	}

	public void setCessRs(float cessRs) {
		this.cessRs = cessRs;
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

	@Override
	public String toString() {
		return "BillDetail [billDetailId=" + billDetailId + ", billNo=" + billNo + ", itemId=" + itemId + ", itemName="
				+ itemName + ", itemUom=" + itemUom + ", itemHsncd=" + itemHsncd + ", billQty=" + billQty + ", rate="
				+ rate + ", baseRate=" + baseRate + ", batchNo=" + batchNo + ", value=" + value + ", taxableAmt="
				+ taxableAmt + ", cgstPer=" + cgstPer + ", cgstRs=" + cgstRs + ", sgstPer=" + sgstPer + ", sgstRs="
				+ sgstRs + ", igstPer=" + igstPer + ", igstRs=" + igstRs + ", cessPer=" + cessPer + ", cessRs=" + cessRs
				+ ", taxAmt=" + taxAmt + ", discAmt=" + discAmt + ", grandTotal=" + grandTotal + ", uniqueKey="
				+ uniqueKey + "]";
	}
    

}
