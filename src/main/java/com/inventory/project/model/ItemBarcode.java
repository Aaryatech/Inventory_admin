package com.inventory.project.model;

public class ItemBarcode {
	
	private int itemId;
	private String itemName;
	private int qty;
    private String batchNo;
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
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	@Override
	public String toString() {
		return "ItemBarcode [itemId=" + itemId + ", itemName=" + itemName + ", qty=" + qty + ", batchNo=" + batchNo
				+ "]";
	}
    
    
    

}
