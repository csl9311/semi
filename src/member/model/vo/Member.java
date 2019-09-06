package member.model.vo;

import java.sql.Date;

public class Member {
	private String id, pw, name, nickName, phone, gender; // 필수정보
	private String email; // 선택정보
	private String grade; // 등급 : admin 회원정보조회에 사용
	private Date birth, regDate, modifyDate;
	private int point, news, sms;
	private int grade_code; // 등급 : admin 회원정보수정에 사용

	@Override
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + ", name=" + name + ", nickName=" + nickName + ", phone=" + phone
				+ ", gender=" + gender + ", email=" + email + ", grade=" + grade + ", birth=" + birth + ", regDate="
				+ regDate + ", modifyDate=" + modifyDate + ", point=" + point + ", news=" + news + ", sms=" + sms
				+ ", grade_code=" + grade_code + "]";
	}

	public Member() {
	}

	// 로그인
	public Member(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	// 세션 등록
	public Member(String id, String grade, int point) {
		this.id = id;
		this.grade = grade;
		this.point = point;
	}

	// 회원가입. 회원정보 수정하고 생성자 형식 같아서 pw 맨 뒤로 뺌.
	public Member(String id, String name, String nickName, String phone, String gender, String email, Date birth,
			int news, int sms, String pw) {
		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.phone = phone;
		this.gender = gender;
		this.email = email;
		this.birth = birth;
		this.news = news;
		this.sms = sms;
		this.pw = pw;
	}

	// 회원정보 수정
	public Member(String id, String name, String nickName, String phone, String gender, String email, Date birth,
			int news, int sms, int grade_code) {
		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.phone = phone;
		this.gender = gender;
		this.email = email;
		this.birth = birth;
		this.news = news;
		this.sms = sms;
		this.grade_code = grade_code;
	}

	// 세션등록, 관리자페이지 상세정보 조회
	public Member(String id, String name, String nickName, String phone, String gender, String email, String grade,
			Date birth, Date regDate, Date modifyDate, int point, int news, int sms) {
		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.phone = phone;
		this.gender = gender;
		this.email = email;
		this.grade = grade;
		this.birth = birth;
		this.regDate = regDate;
		this.modifyDate = modifyDate;
		this.point = point;
		this.news = news;
		this.sms = sms;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
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

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getNews() {
		return news;
	}

	public void setNews(int news) {
		this.news = news;
	}

	public int getSms() {
		return sms;
	}

	public void setSms(int sms) {
		this.sms = sms;
	}

	public int getGrade_code() {
		return grade_code;
	}

	public void setGrade_code(int grade_code) {
		this.grade_code = grade_code;
	}


}
