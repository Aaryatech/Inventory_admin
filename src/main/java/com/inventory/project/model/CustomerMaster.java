package com.inventory.project.model;
 

public class CustomerMaster {
	 
	private int custId;  
	private String custName; 
	private String address; 
	private String gstin; 
	private String custCode; 
	private String mobile;  
	private String email; 
	private String phone1; 
	private int custType; 
	private String conctPrsn; 
	private String prsnEmail; 
	private String panNo; 
	private int isSameState; 
	private int creditDays; 
	private int delStatus;
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public int getCustType() {
		return custType;
	}
	public void setCustType(int custType) {
		this.custType = custType;
	}
	public String getConctPrsn() {
		return conctPrsn;
	}
	public void setConctPrsn(String conctPrsn) {
		this.conctPrsn = conctPrsn;
	}
	public String getPrsnEmail() {
		return prsnEmail;
	}
	public void setPrsnEmail(String prsnEmail) {
		this.prsnEmail = prsnEmail;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public int getIsSameState() {
		return isSameState;
	}
	public void setIsSameState(int isSameState) {
		this.isSameState = isSameState;
	}
	public int getCreditDays() {
		return creditDays;
	}
	public void setCreditDays(int creditDays) {
		this.creditDays = creditDays;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "CustomerMaster [custId=" + custId + ", custName=" + custName + ", address=" + address + ", gstin="
				+ gstin + ", custCode=" + custCode + ", mobile=" + mobile + ", email=" + email + ", phone1=" + phone1
				+ ", custType=" + custType + ", conctPrsn=" + conctPrsn + ", prsnEmail=" + prsnEmail + ", panNo="
				+ panNo + ", isSameState=" + isSameState + ", creditDays=" + creditDays + ", delStatus=" + delStatus
				+ "]";
	}
	
	

}
