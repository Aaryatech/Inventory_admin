package com.inventory.project.model;
  
import java.util.List;
 
import com.fasterxml.jackson.annotation.JsonFormat;
  
public class PurchaseHeader {
	 
	private int purchaseId;  
	private String purchaseNo; 
	private String date; 
	private String time;  
	private String vehicleNo; 
	private int suppId; 
	private String invoiceNo; 
	private String invDate; 
	private float basicValue;  
	private float discPer; 
	private float discAmt; 
	private float discAmt2;  
	private float freightAmt; 
	private float insuranceAmt; 
	private float cgst;  
	private float sgst; 
	private float igst; 
	private float cess; 
	private float taxableAmt; 
	private float billAmt; 
	private float roundOff;  
	private float otherExtra; 
	private int delStatus;  
	private String cdDate1; 
	private String cdDate2; 
	private String cdDate3; 
	private String cdDate4;
	private int isPaid;
	private List<PurchaseDetail> purchaseDetailList;
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public int getSuppId() {
		return suppId;
	}
	public void setSuppId(int suppId) {
		this.suppId = suppId;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getInvDate() {
		return invDate;
	}
	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}
	public float getBasicValue() {
		return basicValue;
	}
	public void setBasicValue(float basicValue) {
		this.basicValue = basicValue;
	}
	public float getDiscPer() {
		return discPer;
	}
	public void setDiscPer(float discPer) {
		this.discPer = discPer;
	}
	public float getDiscAmt() {
		return discAmt;
	}
	public void setDiscAmt(float discAmt) {
		this.discAmt = discAmt;
	}
	public float getDiscAmt2() {
		return discAmt2;
	}
	public void setDiscAmt2(float discAmt2) {
		this.discAmt2 = discAmt2;
	}
	public float getFreightAmt() {
		return freightAmt;
	}
	public void setFreightAmt(float freightAmt) {
		this.freightAmt = freightAmt;
	}
	public float getInsuranceAmt() {
		return insuranceAmt;
	}
	public void setInsuranceAmt(float insuranceAmt) {
		this.insuranceAmt = insuranceAmt;
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
	public float getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	public float getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(float billAmt) {
		this.billAmt = billAmt;
	}
	public float getRoundOff() {
		return roundOff;
	}
	public void setRoundOff(float roundOff) {
		this.roundOff = roundOff;
	}
	public float getOtherExtra() {
		return otherExtra;
	}
	public void setOtherExtra(float otherExtra) {
		this.otherExtra = otherExtra;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public List<PurchaseDetail> getPurchaseDetailList() {
		return purchaseDetailList;
	}
	public void setPurchaseDetailList(List<PurchaseDetail> purchaseDetailList) {
		this.purchaseDetailList = purchaseDetailList;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getCdDate1() {
		return cdDate1;
	}
	public void setCdDate1(String cdDate1) {
		this.cdDate1 = cdDate1;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getCdDate2() {
		return cdDate2;
	}
	public void setCdDate2(String cdDate2) {
		this.cdDate2 = cdDate2;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getCdDate3() {
		return cdDate3;
	}
	public void setCdDate3(String cdDate3) {
		this.cdDate3 = cdDate3;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getCdDate4() {
		return cdDate4;
	}
	public void setCdDate4(String cdDate4) {
		this.cdDate4 = cdDate4;
	}
	
	public int getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(int isPaid) {
		this.isPaid = isPaid;
	}
	@Override
	public String toString() {
		return "PurchaseHeader [purchaseId=" + purchaseId + ", purchaseNo=" + purchaseNo + ", date=" + date + ", time="
				+ time + ", vehicleNo=" + vehicleNo + ", suppId=" + suppId + ", invoiceNo=" + invoiceNo + ", invDate="
				+ invDate + ", basicValue=" + basicValue + ", discPer=" + discPer + ", discAmt=" + discAmt
				+ ", discAmt2=" + discAmt2 + ", freightAmt=" + freightAmt + ", insuranceAmt=" + insuranceAmt + ", cgst="
				+ cgst + ", sgst=" + sgst + ", igst=" + igst + ", cess=" + cess + ", taxableAmt=" + taxableAmt
				+ ", billAmt=" + billAmt + ", roundOff=" + roundOff + ", otherExtra=" + otherExtra + ", delStatus="
				+ delStatus + ", cdDate1=" + cdDate1 + ", cdDate2=" + cdDate2 + ", cdDate3=" + cdDate3 + ", cdDate4="
				+ cdDate4 + ", isPaid=" + isPaid + ", purchaseDetailList=" + purchaseDetailList + "]";
	}
	
	
	
	

}
