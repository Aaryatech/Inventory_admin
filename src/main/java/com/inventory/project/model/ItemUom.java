package com.inventory.project.model; 


public class ItemUom {
	 
	private int uomId;  
	private String uomName; 
	private int delStatus;
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
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "ItemUom [uomId=" + uomId + ", uomName=" + uomName + ", delStatus=" + delStatus + "]";
	}
	
	
	

}
