package community.model.vo;

import java.sql.Date;

public class Board {

	private int bid; // 게시판 고유번호
	private int bType;
	// (게시판타입)일반게시판 / 사진게시판
	/*
	 * private String category; // 게시판 분류
	 */ private String bTitle; // 게시판 제목
	private String bAddress; // 유튜브 주소
	private String bContent; // 게시판 내용
	private String bWriter; // 게시판 작성자
	private int bCount; // 게시판 조회수
	private int bGood; // 추천수
	private Date createDate; // 게시판 작성일
	private Date modifyDate; // 게시판 수정일
	private String status; // 게시판 상태
	private String userWriter;// 로그인한 댓글 작성자

	public Board() {

	}

	public Board(int bid, int bType, String bTitle, String bAddress, String bContent, String bWriter, int bCount,
			int bGood, Date createDate, Date modifyDate, String status) {
		super();
		this.bid = bid;
		this.bType = bType;
		this.bTitle = bTitle;
		this.bAddress = bAddress;
		this.bContent = bContent;
		this.bWriter = bWriter;
		this.bCount = bCount;
		this.bGood = bGood;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}

	public Board(int bid, String bWriter, String userWriter) {
		super();
		this.bid = bid;
		this.bWriter = bWriter;
		this.userWriter = userWriter;
	}

	public Board(int bType, String bWriter, int bGood) {

		this.bType = bType;
		this.bWriter = bWriter;
		this.bGood = bGood;
	}
	
	public Board(int bType,int bid,String bTitle, String bWriter, int bGood) {

		this.bType = bType;
		this.bWriter = bWriter;
		this.bGood = bGood;
		this.bTitle = bTitle;
		this.bid=bid;
	}

	public String getUserWriter() {
		return userWriter;
	}

	public void setUserWriter(String userWriter) {
		this.userWriter = userWriter;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getbType() {
		return bType;
	}

	public void setbType(int bType) {
		this.bType = bType;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbAddress() {
		return bAddress;
	}

	public void setbAddress(String bAddress) {
		this.bAddress = bAddress;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getbWriter() {
		return bWriter;
	}

	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}

	public int getbCount() {
		return bCount;
	}

	public void setbCount(int bCount) {
		this.bCount = bCount;
	}

	public int getbGood() {
		return bGood;
	}

	public void setbGood(int bGood) {
		this.bGood = bGood;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Board [bid=" + bid + ", bType=" + bType + ", bTitle=" + bTitle + ", bAddress=" + bAddress
				+ ", bContent=" + bContent + ", bWriter=" + bWriter + ", bCount=" + bCount + ", bGood=" + bGood
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", status=" + status + "]";
	}

}
