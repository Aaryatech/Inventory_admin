package com.inventory.project.model;
 
public class StockDetail {
	 
	private int stockDetailId;  
	private int stockId; 
	private int itemId; 
	private String itemName;  
	private int totalPurchase; 
	private int totalSale; 
	private int grn; 
	private int closingStock; 
	private float lastPurchaseRate; 
	private float lastWholesaleRate; 
	private float lastRetailRate;  
	private float total;
	private int openingStock;
	public int getStockDetailId() {
		return stockDetailId;
	}
	public void setStockDetailId(int stockDetailId) {
		this.stockDetailId = stockDetailId;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
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
	public int getGrn() {
		return grn;
	}
	public void setGrn(int grn) {
		this.grn = grn;
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
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
	public int getOpeningStock() {
		return openingStock;
	}
	public void setOpeningStock(int openingStock) {
		this.openingStock = openingStock;
	}
	@Override
	public String toString() {
		return "StockDetail [stockDetailId=" + stockDetailId + ", stockId=" + stockId + ", itemId=" + itemId
				+ ", itemName=" + itemName + ", totalPurchase=" + totalPurchase + ", totalSale=" + totalSale + ", grn="
				+ grn + ", closingStock=" + closingStock + ", lastPurchaseRate=" + lastPurchaseRate
				+ ", lastWholesaleRate=" + lastWholesaleRate + ", lastRetailRate=" + lastRetailRate + ", total=" + total
				+ ", openingStock=" + openingStock + "]";
	}
	
	

}
