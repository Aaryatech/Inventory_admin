package com.inventory.project.model;
 

public class ItemMaster {
	 
	private int itemId;  
	private int itemCode; 
	private String itemName; 
	private int uomId;  
	private String uomName; 
	private int groupId; 
	private int catId; 
	private float weight; 
	private int packQty;  
	private int bmsMinQty; 
	private int bmsMaxQty; 
	private int bmsRolQty; 
	private float cgst; 
	private float sgst; 
	private float igst;  
	private float cess; 
	private int storeMinQty; 
	private int storeMaxQty; 
	private int storeRolQty; 
	private String hsnCode;  
	private int isCritical; 
	private String image;  
	private int delStatus;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getItemCode() {
		return itemCode;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getUomId() {
		return uomId;
	}
	public void setUomId(int uomId) {
		this.uomId = uomId;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public int getPackQty() {
		return packQty;
	}
	public void setPackQty(int packQty) {
		this.packQty = packQty;
	}
	public int getBmsMinQty() {
		return bmsMinQty;
	}
	public void setBmsMinQty(int bmsMinQty) {
		this.bmsMinQty = bmsMinQty;
	}
	public int getBmsMaxQty() {
		return bmsMaxQty;
	}
	public void setBmsMaxQty(int bmsMaxQty) {
		this.bmsMaxQty = bmsMaxQty;
	}
	public int getBmsRolQty() {
		return bmsRolQty;
	}
	public void setBmsRolQty(int bmsRolQty) {
		this.bmsRolQty = bmsRolQty;
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
	public int getStoreMinQty() {
		return storeMinQty;
	}
	public void setStoreMinQty(int storeMinQty) {
		this.storeMinQty = storeMinQty;
	}
	public int getStoreMaxQty() {
		return storeMaxQty;
	}
	public void setStoreMaxQty(int storeMaxQty) {
		this.storeMaxQty = storeMaxQty;
	}
	public int getStoreRolQty() {
		return storeRolQty;
	}
	public void setStoreRolQty(int storeRolQty) {
		this.storeRolQty = storeRolQty;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public int getIsCritical() {
		return isCritical;
	}
	public void setIsCritical(int isCritical) {
		this.isCritical = isCritical;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "ItemMaster [itemId=" + itemId + ", itemCode=" + itemCode + ", itemName=" + itemName + ", uomId=" + uomId
				+ ", uomName=" + uomName + ", groupId=" + groupId + ", catId=" + catId + ", weight=" + weight
				+ ", packQty=" + packQty + ", bmsMinQty=" + bmsMinQty + ", bmsMaxQty=" + bmsMaxQty + ", bmsRolQty="
				+ bmsRolQty + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst + ", cess=" + cess
				+ ", storeMinQty=" + storeMinQty + ", storeMaxQty=" + storeMaxQty + ", storeRolQty=" + storeRolQty
				+ ", hsnCode=" + hsnCode + ", isCritical=" + isCritical + ", image=" + image + ", delStatus="
				+ delStatus + "]";
	}
	
	

}
