package karaoke.model.vo;

public class Attachment {
	private int fid;
	private String originName;
	private String changeName;
	private String filePath;
	private int fileLevel;
	private int kid;
	
	public Attachment() {}

	public Attachment(int fid, String originName, String changeName, String filePath, int fileLevel, int kid) {
		super();
		this.fid = fid;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.fileLevel = fileLevel;
		this.kid = kid;
	}

	public Attachment(int kid, String changeName) {
		super();
		this.kid = kid;
		this.changeName = changeName;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public int getKid() {
		return kid;
	}

	public void setKid(int kid) {
		this.kid = kid;
	}
	
	
}
