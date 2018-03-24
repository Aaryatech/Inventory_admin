package com.inventory.project.model;
 

public class ItemCategory {
	 
	private int catId;  
	private String catName; 
	private int groupId; 
	private String catDesc; 
	private int delStatus;
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getCatDesc() {
		return catDesc;
	}
	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "ItemCategory [catId=" + catId + ", catName=" + catName + ", groupId=" + groupId + ", catDesc=" + catDesc
				+ ", delStatus=" + delStatus + "]";
	}
	
	

}
