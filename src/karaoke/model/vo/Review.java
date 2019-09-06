package karaoke.model.vo;

import java.sql.Date;

public class Review {
	private String krwriter;
	private int id;
	private int krating;
	private String krcontent;
	private Date createDate;
	private String status;
	
	public Review(String krwriter, int id, int krating, String krcontent, Date createDate, String status) {
		super();
		this.krwriter = krwriter;
		this.id = id;
		this.krating = krating;
		this.krcontent = krcontent;
		this.createDate = createDate;
		this.status = status;
	}

	
	public Review(String krwriter, int id, int krating, String krcontent) {
		super();
		this.krwriter = krwriter;
		this.id = id;
		this.krating = krating;
		this.krcontent = krcontent;
	}


	public String getKrwriter() {
		return krwriter;
	}

	public void setKrwriter(String krwriter) {
		this.krwriter = krwriter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKrating() {
		return krating;
	}

	public void setKrating(int krating) {
		this.krating = krating;
	}

	public String getKrcontent() {
		return krcontent;
	}

	public void setKrcontent(String krcontent) {
		this.krcontent = krcontent;
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
