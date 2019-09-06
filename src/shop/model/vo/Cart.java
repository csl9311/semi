package shop.model.vo;

public class Cart {
	private int crId, pId, price, amount;
	private String pName, pOption, userId;
	

	public Cart() {}


	public Cart(int crId, int pId, int price, int amount, String pName, String pOption, String userId) {
		super();
		this.crId = crId;
		this.pId = pId;
		this.price = price;
		this.amount = amount;
		this.pName = pName;
		this.pOption = pOption;
		this.userId = userId;
	}


	public int getCrId() {
		return crId;
	}


	public void setCrId(int crId) {
		this.crId = crId;
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


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
