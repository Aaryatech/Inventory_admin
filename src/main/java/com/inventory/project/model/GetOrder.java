package com.inventory.project.model;


public class GetOrder{

	private int orderId; 
	
	private String orderDate; 
	
	private int custId; 
	
	private int custType; 
	
	private int isSameState; 
	
	private String custName; 
	
	private String gstin; 
	
	private String address; 
	
	private String mobile; 
	
	private int creditDays; 
	
	private int orderSubType; 
	
	private int orderStatus; 
	
	private String orderDatetime; 
	
	private String deliveryDate;
	
	private int userId; 
	
	private String userName; 
	
	private int isBillGenerated;

	
	public int getIsSameState() {
		return isSameState;
	}

	public void setIsSameState(int isSameState) {
		this.isSameState = isSameState;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getCreditDays() {
		return creditDays;
	}

	public void setCreditDays(int creditDays) {
		this.creditDays = creditDays;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getCustType() {
		return custType;
	}

	public void setCustType(int custType) {
		this.custType = custType;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getOrderSubType() {
		return orderSubType;
	}

	public void setOrderSubType(int orderSubType) {
		this.orderSubType = orderSubType;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getIsBillGenerated() {
		return isBillGenerated;
	}

	public void setIsBillGenerated(int isBillGenerated) {
		this.isBillGenerated = isBillGenerated;
	}

	@Override
	public String toString() {
		return "GetOrder [orderId=" + orderId + ", orderDate=" + orderDate + ", custId=" + custId + ", custType="
				+ custType + ", isSameState=" + isSameState + ", custName=" + custName + ", gstin=" + gstin
				+ ", address=" + address + ", mobile=" + mobile + ", creditDays=" + creditDays + ", orderSubType="
				+ orderSubType + ", orderStatus=" + orderStatus + ", orderDatetime=" + orderDatetime + ", deliveryDate="
				+ deliveryDate + ", userId=" + userId + ", userName=" + userName + ", isBillGenerated="
				+ isBillGenerated + "]";
	}

    
}
