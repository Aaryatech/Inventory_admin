package com.inventory.project.model;
 

public class ItemWisePurchase {
	 
	private int purDetailId;  
	private int itemId; 
	private String itemName; 
	private int recQty;  
	private float discAmt; 
	private float freightAmt; 
	private float insuAmt; 
	private float taxableAmt; 
	private float cgst;  
	private float sgst; 
	private float igst; 
	private float cess; 
	private float total; 
	private float otherExtra;
	public int getPurDetailId() {
		return purDetailId;
	}
	public void setPurDetailId(int purDetailId) {
		this.purDetailId = purDetailId;
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
	public int getRecQty() {
		return recQty;
	}
	public void setRecQty(int recQty) {
		this.recQty = recQty;
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
	public float getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	public float getCgst() {
		return cgst;
	}
	public void setCgst(float cgst) {
		this.cgst = cgst;
	}
	public float getSgst() {
		return sgst;
	}
	public void setSgst(float sgst) {
		this.sgst = sgst;
	}
	public float getIgst() {
		return igst;
	}
	public void setIgst(float igst) {
		this.igst = igst;
	}
	public float getCess() {
		return cess;
	}
	public void setCess(float cess) {
		this.cess = cess;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public float getOtherExtra() {
		return otherExtra;
	}
	public void setOtherExtra(float otherExtra) {
		this.otherExtra = otherExtra;
	}
	@Override
	public String toString() {
		return "ItemWisePurchase [purDetailId=" + purDetailId + ", itemId=" + itemId + ", itemName=" + itemName
				+ ", recQty=" + recQty + ", discAmt=" + discAmt + ", freightAmt=" + freightAmt + ", insuAmt=" + insuAmt
				+ ", taxableAmt=" + taxableAmt + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst + ", cess="
				+ cess + ", total=" + total + ", otherExtra=" + otherExtra + "]";
	}
	
	

}
