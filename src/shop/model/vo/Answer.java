package shop.model.vo;

import java.sql.Date;

public class Answer {
	
	private int aId;
	private int aType;
	private int pId;
	private int aRId;
	private String aWriter;
	private String aTitle;
	private String aContent;
	private int aCount;
	private Date createDate;
	private Date modifyDate;
	private String status;
	
	public Answer() {}
	
	public Answer(int aId, int aType, int pId, int aRId, String aWriter, String aTitle, String aContent, int aCount,
			Date createDate, Date modifyDate, String status) {
		super();
		this.aId = aId;
		this.aType = aType;
		this.pId = pId;
		this.aRId = aRId;
		this.aWriter = aWriter;
		this.aTitle = aTitle;
		this.aContent = aContent;
		this.aCount = aCount;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}
	
	public Answer(int aId, int aType, int pId, int aRId, String aWriter, String aTitle, String aContent, int aCount,
			Date modifyDate) {
		super();
		this.aId = aId;
		this.aType = aType;
		this.pId = pId;
		this.aRId = aRId;
		this.aWriter = aWriter;
		this.aTitle = aTitle;
		this.aContent = aContent;
		this.aCount = aCount;
		this.modifyDate = modifyDate;
	}
	
	

	public Answer(String aContent, Date modifyDate) {
		super();
		this.aContent = aContent;
		this.modifyDate = modifyDate;
	}
	
	

	public Answer(int aId, String aWriter, Date modifyDate) {
		super();
		this.aId = aId;
		this.aWriter = aWriter;
		this.modifyDate = modifyDate;
	}

	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
	}
	public int getaType() {
		return aType;
	}
	public void setaType(int aType) {
		this.aType = aType;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public int getaRId() {
		return aRId;
	}
	public void setaRId(int aRId) {
		this.aRId = aRId;
	}
	public String getaWriter() {
		return aWriter;
	}
	public void setaWriter(String aWriter) {
		this.aWriter = aWriter;
	}
	public String getaTitle() {
		return aTitle;
	}
	public void setaTitle(String aTitle) {
		this.aTitle = aTitle;
	}
	public String getaContent() {
		return aContent;
	}
	public void setaContent(String aContent) {
		this.aContent = aContent;
	}
	public int getaCount() {
		return aCount;
	}
	public void setaCount(int aCount) {
		this.aCount = aCount;
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
		return "Answer [aId=" + aId + ", aType=" + aType + ", pId=" + pId + ", aRId=" + aRId + ", aWriter=" + aWriter
				+ ", aTitle=" + aTitle + ", aContent=" + aContent + ", aCount=" + aCount + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", status=" + status + "]";
	}
	
	
}
