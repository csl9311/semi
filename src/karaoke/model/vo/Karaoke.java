package karaoke.model.vo;

public class Karaoke {
	private int kid;
	private String refId;
	private String karaokeName;
	private int oneCoin;
	private int threeCoin;
	private String time;
	private String status;
	private int addressCode;
	private String roadAddress;
	private String addressDetail;
	
	public Karaoke() {}

	public Karaoke(int kid, String refId, String karaokeName, int oneCoin, int threeCoin, String time, String status,
			int addressCode) {
		super();
		this.kid = kid;
		this.refId = refId;
		this.karaokeName = karaokeName;
		this.oneCoin = oneCoin;
		this.threeCoin = threeCoin;
		this.time = time;
		this.status = status;
		this.addressCode = addressCode;
	}



	public Karaoke(int kid, String refId, String karaokeName, int oneCoin, int threeCoin, String time, String status,
			int addressCode, String roadAddress, String addressDetail) {
		super();
		this.kid = kid;
		this.refId = refId;
		this.karaokeName = karaokeName;
		this.oneCoin = oneCoin;
		this.threeCoin = threeCoin;
		this.time = time;
		this.status = status;
		this.addressCode = addressCode;
		this.roadAddress = roadAddress;
		this.addressDetail = addressDetail;
	}



	public String getRoadAddress() {
		return roadAddress;
	}
	
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public int getKid() {
		return kid;
	}

	public void setKid(int kid) {
		this.kid = kid;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getKaraokeName() {
		return karaokeName;
	}

	public void setKaraokeName(String karaokeName) {
		this.karaokeName = karaokeName;
	}

	public int getOneCoin() {
		return oneCoin;
	}

	public void setOneCoin(int oneCoin) {
		this.oneCoin = oneCoin;
	}

	public int getThreeCoin() {
		return threeCoin;
	}

	public void setThreeCoin(int threeCoin) {
		this.threeCoin = threeCoin;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(int addressCode) {
		this.addressCode = addressCode;
	}
	
}
