package shop.model.vo;

import java.sql.Date;

public class RAttachment {
	
	private int rFId;
	private String originName;
	private String changeName;
	private String filePAth;
	private int rId;
	private Date uploadDate;
	private int pId;
	private String status;
	
	public RAttachment() {}
	
	public RAttachment(int rFId, String originName, String changeName, String filePAth, int rId, Date uploadDate,
			int pId, String status) {
		super();
		this.rFId = rFId;
		this.originName = originName;
		this.changeName = changeName;
		this.filePAth = filePAth;
		this.rId = rId;
		this.uploadDate = uploadDate;
		this.pId = pId;
		this.status = status;
	}

	public RAttachment(int rFId, String originName, String changeName, String filePAth, int rId,
			Date uploadDate) {
		super();
		this.rFId = rFId;
		this.originName = originName;
		this.changeName = changeName;
		this.filePAth = filePAth;
		this.rId = rId;
		this.uploadDate = uploadDate;
	}
	
	

	public RAttachment(String changeName, int rId) {
		super();
		this.changeName = changeName;
		this.rId = rId;
	}
	
	public RAttachment(int rFId, String changeName, int rId) {
		super();
		this.rFId = rFId;
		this.changeName = changeName;
		this.rId = rId;
	}

	public int getrFId() {
		return rFId;
	}

	public void setrFId(int rFId) {
		this.rFId = rFId;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getFilePAth() {
		return filePAth;
	}

	public void setFilePAth(String filePAth) {
		this.filePAth = filePAth;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RAttachment [rFId=" + rFId + ", originName=" + originName + ", changeName=" + changeName + ", filePAth="
				+ filePAth + ", rId=" + rId + ", uploadDate=" + uploadDate + ", pId=" + pId + ", status=" + status
				+ "]";
	}
	
	
	
}
