package payment.model.vo;

import java.sql.Date;

public class Payment {
	private int oNo;
	private String userId;
	private String phone;
	private int pId;
	private int price;
	private int amount;
	private String pName;
	private String pOption;
	private Date payDate;
	private String address;
	private String history;
	private String recipient;
	private String req;
	
	public Payment () {} 
	
	public Payment(int oNo, String userId, String phone, int pId, int price, int amount, String pName, String pOption,
			Date payDate, String address, String history) {
		super();
		this.oNo = oNo;
		this.userId = userId;
		this.phone = phone;
		this.pId = pId;
		this.price = price;
		this.amount = amount;
		this.pName = pName;
		this.pOption = pOption;
		this.payDate = payDate;
		this.address = address;
		this.history = history;
	}

	public Payment(int oNo, String userId, String phone, int pId, int price, int amount, String pName, String pOption,
			Date payDate, String address, String history, String recipient, String req) {
		super();
		this.oNo = oNo;
		this.userId = userId;
		this.phone = phone;
		this.pId = pId;
		this.price = price;
		this.amount = amount;
		this.pName = pName;
		this.pOption = pOption;
		this.payDate = payDate;
		this.address = address;
		this.history = history;
		this.recipient = recipient;
		this.req = req;
	}
	
	
	

	public Payment(String phone, String address, String recipient, String req) {
		super();
		this.phone = phone;
		this.address = address;
		this.recipient = recipient;
		this.req = req;
	}

	public int getoNo() {
		return oNo;
	}
	public void setoNo(int oNo) {
		this.oNo = oNo;
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
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpOption() {
		return pOption;
	}
	public void setpOption(String pOption) {
		this.pOption = pOption;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
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
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

	@Override
	public String toString() {
		return "Payment [oNo=" + oNo + ", userId=" + userId + ", phone=" + phone + ", pId=" + pId + ", price=" + price
				+ ", amount=" + amount + ", pName=" + pName + ", pOption=" + pOption + ", payDate=" + payDate
				+ ", address=" + address + ", history=" + history + "]";
	}
	
	
	
}
