package com.inventory.project.model;
 

public class GetCurrentStock {
	 
	private int itemId; 
	private String itemName;   
	private int openingStock; 
	private int totalPurchase; 
	private int totalSale; 
	private int totalGrn; 
	private int closingStock;
	private float lastPurchaseRate; 
	private float lastWholesaleRate; 
	private float lastRetailRate;
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
	public int getOpeningStock() {
		return openingStock;
	}
	public void setOpeningStock(int openingStock) {
		this.openingStock = openingStock;
	}
	public int getTotalPurchase() {
		return totalPurchase;
	}
	public void setTotalPurchase(int totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
	public int getTotalSale() {
		return totalSale;
	}
	public void setTotalSale(int totalSale) {
		this.totalSale = totalSale;
	}
	public int getTotalGrn() {
		return totalGrn;
	}
	public void setTotalGrn(int totalGrn) {
		this.totalGrn = totalGrn;
	}
	
	public int getClosingStock() {
		return closingStock;
	}
	public void setClosingStock(int closingStock) {
		this.closingStock = closingStock;
	}
	public float getLastPurchaseRate() {
		return lastPurchaseRate;
	}
	public void setLastPurchaseRate(float lastPurchaseRate) {
		this.lastPurchaseRate = lastPurchaseRate;
	}
	public float getLastWholesaleRate() {
		return lastWholesaleRate;
	}
	public void setLastWholesaleRate(float lastWholesaleRate) {
		this.lastWholesaleRate = lastWholesaleRate;
	}
	public float getLastRetailRate() {
		return lastRetailRate;
	}
	public void setLastRetailRate(float lastRetailRate) {
		this.lastRetailRate = lastRetailRate;
	}
	@Override
	public String toString() {
		return "GetCurrentStock [itemId=" + itemId + ", itemName=" + itemName + ", openingStock=" + openingStock
				+ ", totalPurchase=" + totalPurchase + ", totalSale=" + totalSale + ", totalGrn=" + totalGrn
				+ ", closingStock=" + closingStock + ", lastPurchaseRate=" + lastPurchaseRate + ", lastWholesaleRate="
				+ lastWholesaleRate + ", lastRetailRate=" + lastRetailRate + "]";
	}
	
	

}
