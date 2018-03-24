package com.inventory.project.model;
 

public class ItemStock {
	 
	private int itemId;   
	private String itemName; 
	private float wholesaleRate;  
	private float retailRate; 
	private float rateWithTax;
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
	public float getRateWithTax() {
		return rateWithTax;
	}
	public void setRateWithTax(float rateWithTax) {
		this.rateWithTax = rateWithTax;
	}
	@Override
	public String toString() {
		return "ItemStock [itemId=" + itemId + ", itemName=" + itemName + ", wholesaleRate=" + wholesaleRate
				+ ", retailRate=" + retailRate + ", rateWithTax=" + rateWithTax + "]";
	}
	
	

}
