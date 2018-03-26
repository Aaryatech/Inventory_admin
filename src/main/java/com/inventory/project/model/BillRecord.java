package com.inventory.project.model;

import java.util.List;

public class BillRecord {
	
	private List<BillDetail> billDetail;
	
	private int newRow;
	
	private int uniqueKey;
	
	private float total;
	
	private int balOver;
	
	private List<PurchaseDetail> purchaseDetailList;

	

	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public int getBalOver() {
		return balOver;
	}

	public void setBalOver(int balOver) {
		this.balOver = balOver;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getNewRow() {
		return newRow;
	}

	public void setNewRow(int newRow) {
		this.newRow = newRow;
	}
    
	public List<BillDetail> getBillDetail() {
		return billDetail;
	}

	public void setBillDetail(List<BillDetail> billDetail) {
		this.billDetail = billDetail;
	}

	public List<PurchaseDetail> getPurchaseDetailList() {
		return purchaseDetailList;
	}

	public void setPurchaseDetailList(List<PurchaseDetail> purchaseDetailList) {
		this.purchaseDetailList = purchaseDetailList;
	}

	@Override
	public String toString() {
		return "BillRecord [billDetail=" + billDetail + ", newRow=" + newRow + ", uniqueKey=" + uniqueKey + ", total="
				+ total + ", balOver=" + balOver + ", purchaseDetailList=" + purchaseDetailList + "]";
	}
   

}
