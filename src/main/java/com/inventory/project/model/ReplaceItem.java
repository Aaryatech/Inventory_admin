package com.inventory.project.model;

public class ReplaceItem {
	
	private int itemId;
	private String itemName;
	private int qty;
	private int oldReplaceQty;
	private int totalReplaceQty;
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
	
	public int getOldReplaceQty() {
		return oldReplaceQty;
	}
	public void setOldReplaceQty(int oldReplaceQty) {
		this.oldReplaceQty = oldReplaceQty;
	}
	
	
	public int getTotalReplaceQty() {
		return totalReplaceQty;
	}
	public void setTotalReplaceQty(int totalReplaceQty) {
		this.totalReplaceQty = totalReplaceQty;
	}
	@Override
	public String toString() {
		return "ReplaceItem [itemId=" + itemId + ", itemName=" + itemName + ", qty=" + qty + ", oldReplaceQty="
				+ oldReplaceQty + ", totalReplaceQty=" + totalReplaceQty + ", batchNo=" + batchNo + "]";
	}
	
	

}
