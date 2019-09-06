package product.model.vo;

import java.sql.Date;

public class Product {
	private int pId, price, stock, sellCount, amount;
	private int brandNo, categoryNo, subCategoryNo;
	private String pName, category, subCategory, brand, useOption, option;
	private Date regDate, modifyDate;

	@Override
	public String toString() {
		return "Product [pId=" + pId + ", price=" + price + ", stock=" + stock + ", sellCount=" + sellCount
				+ ", amount=" + amount + ", brandNo=" + brandNo + ", categoryNo=" + categoryNo + ", subCategoryNo="
				+ subCategoryNo + ", pName=" + pName + ", category=" + category + ", subCategory=" + subCategory
				+ ", brand=" + brand + ", useOption=" + useOption + ", option=" + option + ", regDate=" + regDate
				+ ", modifyDate=" + modifyDate + "]";
	}

	// 관리자 상품 등록
	public Product(int pId, int price, int stock, int brandNo, int categoryNo, int subCategoryNo, String pName,
			String useOption) {
		super();
		this.pId = pId;
		this.price = price;
		this.stock = stock;
		this.brandNo = brandNo;
		this.categoryNo = categoryNo;
		this.subCategoryNo = subCategoryNo;
		this.pName = pName;
		this.useOption = useOption;
	}

	// 관리자 상품 등록 select>option 변경
	public Product(int subCategoryNo, String subCategory) {
		super();
		this.subCategoryNo = subCategoryNo;
		this.subCategory = subCategory;
	}

	// 관리자 상품 정보 수정
	public Product(int pId, int price, int stock, int brandNo, int categoryNo, int subCategoryNo, String pName,
			String useOption, Date regDate) {
		super();
		this.pId = pId;
		this.price = price;
		this.stock = stock;
		this.brandNo = brandNo;
		this.categoryNo = categoryNo;
		this.subCategoryNo = subCategoryNo;
		this.pName = pName;
		this.useOption = useOption;
		this.regDate = regDate;
	}

	// 관리자 상품목록조회
	public Product(int pId, int price, int stock, int sellCount, String pName, String category, String subCategory,
			String brand, String useOption, String option, Date regDate, Date modifyDate) {
		super();
		this.pId = pId;
		this.price = price;
		this.stock = stock;
		this.sellCount = sellCount;
		this.pName = pName;
		this.category = category;
		this.subCategory = subCategory;
		this.brand = brand;
		this.useOption = useOption;
		this.option = option;
		this.regDate = regDate;
		this.modifyDate = modifyDate;
	}

	public Product(int pId, int price, int stock, int sellCount, String pName, String category, String subCategory,
			String brand, String useOption, Date regDate, Date modifyDate) {
		super();
		this.pId = pId;
		this.price = price;
		this.stock = stock;
		this.sellCount = sellCount;
		this.pName = pName;
		this.category = category;
		this.subCategory = subCategory;
		this.brand = brand;
		this.useOption = useOption;
		this.regDate = regDate;
		this.modifyDate = modifyDate;
	}

	public Product(int pId, int price, String pName, String category, String subCategory, String brand) {
		super();
		this.pId = pId;
		this.price = price;
		this.pName = pName;
		this.category = category;
		this.subCategory = subCategory;
		this.brand = brand;
	}

	public Product(int pId, int price, int amount, String pName, String option) {
		super();
		this.pId = pId;
		this.price = price;
		this.amount = amount;
		this.pName = pName;
		this.option = option;
	}

	public Product(String option) {
		super();
		this.option = option;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getSellCount() {
		return sellCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getUseOption() {
		return useOption;
	}

	public void setUseOption(String useOption) {
		this.useOption = useOption;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public int getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(int brandNo) {
		this.brandNo = brandNo;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public int getSubCategoryNo() {
		return subCategoryNo;
	}

	public void setSubCategoryNo(int subCategoryNo) {
		this.subCategoryNo = subCategoryNo;
	}

}
