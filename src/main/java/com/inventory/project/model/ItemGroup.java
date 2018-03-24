package com.inventory.project.model;
 

public class ItemGroup {

	 
	private int groupId;  
	private String groupName; 
	private int delStatus;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "ItemGroup [groupId=" + groupId + ", groupName=" + groupName + ", delStatus=" + delStatus + "]";
	}
	
	
}
