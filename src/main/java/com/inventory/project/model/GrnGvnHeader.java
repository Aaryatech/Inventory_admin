package com.inventory.project.model;
 
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
  
public class GrnGvnHeader {
	 
	private int grnId; 
	private String date; 
	private int suppId;  
	private String gstnNo; 
	private int delStatus; 
	private List<GrnGvnDetail> grnGvnDetailList;
	public int getGrnId() {
		return grnId;
	}
	public void setGrnId(int grnId) {
		this.grnId = grnId;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getSuppId() {
		return suppId;
	}
	public void setSuppId(int suppId) {
		this.suppId = suppId;
	}
	public String getGstnNo() {
		return gstnNo;
	}
	public void setGstnNo(String gstnNo) {
		this.gstnNo = gstnNo;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public List<GrnGvnDetail> getGrnGvnDetailList() {
		return grnGvnDetailList;
	}
	public void setGrnGvnDetailList(List<GrnGvnDetail> grnGvnDetailList) {
		this.grnGvnDetailList = grnGvnDetailList;
	}
	@Override
	public String toString() {
		return "GrnGvnHeader [grnId=" + grnId + ", date=" + date + ", suppId=" + suppId + ", gstnNo=" + gstnNo
				+ ", delStatus=" + delStatus + ", grnGvnDetailList=" + grnGvnDetailList + "]";
	}
	
	

}
