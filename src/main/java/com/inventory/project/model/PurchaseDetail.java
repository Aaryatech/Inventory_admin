package com.inventory.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PurchaseDetail {
	 
	private int purDetailId; 
	private int purchaseId;  
	private String itemName; 
	private int itemId; 
	private String itemUom;  
	private int recQty; 
	private float rate; 
	private float baseRate; 
	private float value; 
	private float discPer;  
	private float discAmt; 
	private float freightAmt; 
	private float insuAmt;  
	private float cgstPer; 
	private float cgstRs; 
	private float sgstPer; 
	private float sgstRs; 
	private float igstPer; 
	private float igstRs; 
	private float cessPer; 
	private float cessRs; 
	private float taxableAmt; 
	private float total;  
	private float roundOff; 
	private float discOnBill; 
	private float otherExtra; 
	private String batchNo; 
	private int sellQty; 
	private int balance; 
	private float rateWithoutTax;  
	private float rateWithTax; 
	private float wholesaleRate; 
	private float retailRate; 
	private int delStatus;
	private String expiryDate;
	private int replaceQty;
	public int getPurDetailId() {
		return purDetailId;
	}
	public void setPurDetailId(int purDetailId) {
		this.purDetailId = purDetailId;
	}
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	 
	
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemUom() {
		return itemUom;
	}
	public void setItemUom(String itemUom) {
		this.itemUom = itemUom;
	}
	public int getRecQty() {
		return recQty;
	}
	public void setRecQty(int recQty) {
		this.recQty = recQty;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public float getBaseRate() {
		return baseRate;
	}
	public void setBaseRate(float baseRate) {
		this.baseRate = baseRate;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public float getDiscPer() {
		return discPer;
	}
	public void setDiscPer(float discPer) {
		this.discPer = discPer;
	}
	public float getDiscAmt() {
		return discAmt;
	}
	public void setDiscAmt(float discAmt) {
		this.discAmt = discAmt;
	}
	public float getFreightAmt() {
		return freightAmt;
	}
	public void setFreightAmt(float freightAmt) {
		this.freightAmt = freightAmt;
	}
	public float getInsuAmt() {
		return insuAmt;
	}
	public void setInsuAmt(float insuAmt) {
		this.insuAmt = insuAmt;
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
	public float getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public float getRoundOff() {
		return roundOff;
	}
	public void setRoundOff(float roundOff) {
		this.roundOff = roundOff;
	}
	public float getDiscOnBill() {
		return discOnBill;
	}
	public void setDiscOnBill(float discOnBill) {
		this.discOnBill = discOnBill;
	}
	public float getOtherExtra() {
		return otherExtra;
	}
	public void setOtherExtra(float otherExtra) {
		this.otherExtra = otherExtra;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	 
	public int getSellQty() {
		return sellQty;
	}
	public void setSellQty(int sellQty) {
		this.sellQty = sellQty;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public float getRateWithoutTax() {
		return rateWithoutTax;
	}
	public void setRateWithoutTax(float rateWithoutTax) {
		this.rateWithoutTax = rateWithoutTax;
	}
	public float getRateWithTax() {
		return rateWithTax;
	}
	public void setRateWithTax(float rateWithTax) {
		this.rateWithTax = rateWithTax;
	}
	public float getWholesaleRate() {
		return wholesaleRate;
	}
	public void setWholesaleRate(float wholesaleRate) {
		this.wholesaleRate = wholesaleRate;
	}
	public float getRetailRate() {
		return retailRate;
	}
	public void setRetailRate(float retailRate) {
		this.retailRate = retailRate;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public int getReplaceQty() {
		return replaceQty;
	}
	public void setReplaceQty(int replaceQty) {
		this.replaceQty = replaceQty;
	}
	
	@Override
	public String toString() {
		return "PurchaseDetail [purDetailId=" + purDetailId + ", purchaseId=" + purchaseId + ", itemName=" + itemName
				+ ", itemId=" + itemId + ", itemUom=" + itemUom + ", recQty=" + recQty + ", rate=" + rate
				+ ", baseRate=" + baseRate + ", value=" + value + ", discPer=" + discPer + ", discAmt=" + discAmt
				+ ", freightAmt=" + freightAmt + ", insuAmt=" + insuAmt + ", cgstPer=" + cgstPer + ", cgstRs=" + cgstRs
				+ ", sgstPer=" + sgstPer + ", sgstRs=" + sgstRs + ", igstPer=" + igstPer + ", igstRs=" + igstRs
				+ ", cessPer=" + cessPer + ", cessRs=" + cessRs + ", taxableAmt=" + taxableAmt + ", total=" + total
				+ ", roundOff=" + roundOff + ", discOnBill=" + discOnBill + ", otherExtra=" + otherExtra + ", batchNo="
				+ batchNo + ", sellQty=" + sellQty + ", balance=" + balance + ", rateWithoutTax=" + rateWithoutTax
				+ ", rateWithTax=" + rateWithTax + ", wholesaleRate=" + wholesaleRate + ", retailRate=" + retailRate
				+ ", delStatus=" + delStatus + ", expiryDate=" + expiryDate + ", replaceQty=" + replaceQty + "]";
	}
	 
	

}
