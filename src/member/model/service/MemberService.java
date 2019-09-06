package member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import member.model.dao.MemberDAO;
import member.model.vo.Address;
import member.model.vo.Member;
import shop.model.vo.Review;

public class MemberService {
	public ArrayList<Member> selectAll() {
		Connection conn = getConnection();
		ArrayList<Member> list = new MemberDAO().selectAll(conn);
		close(conn);
		return list;
	}

	public Member selectMember(String id) {
		Connection conn = getConnection();
		Member member = new MemberDAO().selectMember(conn, id);
		close(conn);
		return member;
	}
// 관리자 회원정보 수정
	public int adminUpdateMember(Member member) {
		Connection conn = getConnection();
		int result = new MemberDAO().adminUpdateMember(conn, member);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	// 아이디 중복체크
	public int checkId(String id) {
		Connection conn = getConnection();
		int result = new MemberDAO().checkId(conn, id);
		close(conn);
		return result;
	}
	// 닉네임 중복체크
	public int checkNick(String nickName) {
		Connection conn = getConnection();
		int result = new MemberDAO().checkNick(conn, nickName);
		close(conn);
		return result;
	}
	// 이메일 중복체크
	public int checkEmail(String email) {
		Connection conn = getConnection();
		int result = new MemberDAO().checkEmail(conn, email);
		close(conn);
		return result;
	}
	// 회원가입
	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = new MemberDAO().insertMember(conn, member);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	

	public Member loginMember(Member member) {
		Connection conn = getConnection();
		Member loginUser = new MemberDAO().loginMember(conn, member);
		close(conn);
		return loginUser;
	}

	public boolean checkPw(String id, String pw) {
		Connection conn = getConnection();
		boolean pwCheck = new MemberDAO().checkPw(conn, id, pw);
		close(conn);
		return pwCheck;
	}

	public ArrayList<Address> getAddress(String id) {
		Connection conn = getConnection();
		ArrayList<Address> list = new MemberDAO().getAddress(conn, id);
		close(conn);
		return list;
	}
	
	public int addressInsert(Address address) {
		Connection conn = getConnection();
		int result = new MemberDAO().addressInsert(conn, address);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int getAddressCount(String id) {
		Connection conn = getConnection();
		int result = new MemberDAO().getAddressCount(conn, id);
		close(conn);
		return result;
	}

	public int addressUpdate(Address address) {
		Connection conn = getConnection();
		int result = new MemberDAO().addressUpdate(conn, address);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int addressDelete(Address address) {
		Connection conn = getConnection();
		int result = new MemberDAO().addressDelete(conn, address);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	

	public Address selectAdr(String userId) {
		Connection conn= getConnection();
		Address adr= new MemberDAO().selectAdr(conn,userId);
		close(conn);
		return adr;
	}

	public int insertReview(Review r) {
		Connection conn = getConnection();
		MemberDAO dao = new MemberDAO();
		int result = dao.insertReview(conn, r);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
