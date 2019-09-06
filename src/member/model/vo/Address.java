package member.model.vo;

public class Address {
	private int address_code;
	private String postNum, roadAddress, jibunAddress, address_detail, id;

	public Address() {
	}
	
	@Override
	public String toString() {
		return "Address [address_code=" + address_code + ", postNum=" + postNum + ", roadAddress=" + roadAddress
				+ ", jibunAddress=" + jibunAddress + ", address_detail=" + address_detail + ", id=" + id + "]";
	}

	// 주소 update
	public Address(int address_code, String postNum, String roadAddress, String jibunAddress, String address_detail) {
		this.address_code = address_code;
		this.postNum = postNum;
		this.roadAddress = roadAddress;
		this.jibunAddress = jibunAddress;
		this.address_detail = address_detail;
	}

	// 주소 insert
	public Address(String postNum, String roadAddress, String jibunAddress, String address_detail, String id) {
		this.postNum = postNum;
		this.roadAddress = roadAddress;
		this.jibunAddress = jibunAddress;
		this.address_detail = address_detail;
		this.id = id;
	}
	
	// 주소 delete
	public Address(int address_code) {
		this.address_code = address_code;
	}

	public int getAddress_code() {
		return address_code;
	}
	public void setAddress_code(int address_code) {
		this.address_code = address_code;
	}
	public String getPostNum() {
		return postNum;
	}
	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}
	public String getRoadAddress() {
		return roadAddress;
	}
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}
	public String getJibunAddress() {
		return jibunAddress;
	}
	public void setJibunAddress(String jibunAddress) {
		this.jibunAddress = jibunAddress;
	}
	public String getAddress_detail() {
		return address_detail;
	}
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
}
