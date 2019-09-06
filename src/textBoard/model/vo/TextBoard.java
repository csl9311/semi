package textBoard.model.vo;

import java.sql.Date;

public class TextBoard {
	private int tbid;
	private String tbTitle;
	private String tbContent;
	private String tbWriter;
	private int tbCount;
	private Date createDate;
	private Date ModifyDate;
	private int tbType;
	private String status;
	public TextBoard() {}
	public TextBoard(int tbid, String tbTitle, String tbContent, String tbWriter, int tbCount, Date createDate,
			Date modifyDate, int tbType, String status) {
		this.tbid = tbid;
		this.tbTitle = tbTitle;
		this.tbContent = tbContent;
		this.tbWriter = tbWriter;
		this.tbCount = tbCount;
		this.createDate = createDate;
		this.ModifyDate = modifyDate;
		this.tbType = tbType;
		this.status = status;
	}

	public int getTbid() {
		return tbid;
	}
	public void setTbid(int tbid) {
		this.tbid = tbid;
	}
	public String getTbTitle() {
		return tbTitle;
	}
	public void setTbTitle(String tbTitle) {
		this.tbTitle = tbTitle;
	}
	public String getTbContent() {
		return tbContent;
	}
	public void setTbContent(String tbContent) {
		this.tbContent = tbContent;
	}
	public String getTbWriter() {
		return tbWriter;
	}
	public void setTbWriter(String tbWriter) {
		this.tbWriter = tbWriter;
	}
	public int getTbCount() {
		return tbCount;
	}
	public void setTbCount(int tbCount) {
		this.tbCount = tbCount;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return ModifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		ModifyDate = modifyDate;
	}
	public int getTbType() {
		return tbType;
	}
	public void setTbType(int tbType) {
		this.tbType = tbType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
