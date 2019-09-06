package shop.model.vo;

import java.sql.Date;

public class Purchase {
  private int oNo, pId, totalPrice, totalAmount;
  private String userId, phone, pName, address, history;
  private Date pay_Date;
public Purchase() {
	super();
	// TODO Auto-generated constructor stub
}
public Purchase(int oNo, int pId, int totalPrice, int totalAmount, String userId, String phone, String pName,
		String address, String history, Date pay_Date) {
	super();
	this.oNo = oNo;
	this.pId = pId;
	this.totalPrice = totalPrice;
	this.totalAmount = totalAmount;
	this.userId = userId;
	this.phone = phone;
	this.pName = pName;
	this.address = address;
	this.history = history;
	this.pay_Date = pay_Date;
}
public Purchase(int oNo, int pId, int totalPrice, int totalAmount, String userId, String phone, String pName,
		String address) {
	super();
	this.oNo = oNo;
	this.pId = pId;
	this.totalPrice = totalPrice;
	this.totalAmount = totalAmount;
	this.userId = userId;
	this.phone = phone;
	this.pName = pName;
	this.address = address;
}
public int getoNo() {
	return oNo;
}
public void setoNo(int oNo) {
	this.oNo = oNo;
}
public int getpId() {
	return pId;
}
public void setpId(int pId) {
	this.pId = pId;
}
public int getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(int totalPrice) {
	this.totalPrice = totalPrice;
}
public int getTotalAmount() {
	return totalAmount;
}
public void setTotalAmount(int totalAmount) {
	this.totalAmount = totalAmount;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getpName() {
	return pName;
}
public void setpName(String pName) {
	this.pName = pName;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getHistory() {
	return history;
}
public void setHistory(String history) {
	this.history = history;
}
public Date getPay_Date() {
	return pay_Date;
}
public void setPay_Date(Date pay_Date) {
	this.pay_Date = pay_Date;
}
  
  
  
}
