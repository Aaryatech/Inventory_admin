package com.inventory.project.model;
 
public class GrnReport {
	 
	private int grnDetailId;   
	private int itemId; 
	private String itemName; 
	private String hsnCode;   
	private int qty; 
	private float total;
	public int getGrnDetailId() {
		return grnDetailId;
	}
	public void setGrnDetailId(int grnDetailId) {
		this.grnDetailId = grnDetailId;
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
		return "GrnReport [grnDetailId=" + grnDetailId + ", itemId=" + itemId + ", itemName=" + itemName + ", hsnCode="
				+ hsnCode + ", qty=" + qty + ", total=" + total + "]";
	}
	
	

}
