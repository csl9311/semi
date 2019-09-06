package textBoard.model.vo;

import java.sql.Date;

public class TextBoardReply {
	private int trid;
	private String trcontent;
	private int tbid;
	private String trwriter;
	private Date createDate;
	private String status;
	
	public TextBoardReply() {}

	public TextBoardReply(int trid, String trcontent, int tbid, String trwriter, Date createDate, String status) {
		super();
		this.trid = trid;
		this.trcontent = trcontent;
		this.tbid = tbid;
		this.trwriter = trwriter;
		this.createDate = createDate;
		this.status = status;
	}

	public int getTrid() {
		return trid;
	}

	public void setTrid(int trid) {
		this.trid = trid;
	}

	public String getTrcontent() {
		return trcontent;
	}

	public void setTrcontent(String trcontent) {
		this.trcontent = trcontent;
	}

	public int getTbid() {
		return tbid;
	}

	public void setTbid(int tbid) {
		this.tbid = tbid;
	}

	public String getTrwriter() {
		return trwriter;
	}

	public void setTrwriter(String trwriter) {
		this.trwriter = trwriter;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
