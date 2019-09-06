package member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import member.model.vo.Address;
import member.model.vo.Member;
import shop.model.vo.RAttachment;
import shop.model.vo.Review;

public class MemberDAO {

	private Properties prop = new Properties();

	public MemberDAO() {
		String fileName = MemberDAO.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

// 로그인정보 받아오기
// 세션에 개인정보를 전부 담아두는게 맞는건지 모르겠다.
// 보안 고려해서 id만 올려두고 각 페이지에서 사용 할 때 마다 selectMember를 실행해줘야 하는건가?
	public Member loginMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member loginUser = null;

		String query = prop.getProperty("loginMember");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			rset = pstmt.executeQuery();
			if (rset.next()) {
				loginUser = new Member(rset.getString("id"), rset.getString("name"));
				loginUser = selectMember(conn, member.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return loginUser;
	}

// 세션에 로그인 정보등록, 관리자페이지 상세정보 조회
	public Member selectMember(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;

		String query = prop.getProperty("selectMember");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				member = new Member(rset.getString("id"), rset.getString("name"), rset.getString("nickname"),
						rset.getString("phone"), rset.getString("gender"), rset.getString("email"),
						rset.getString("grade"), rset.getDate("birth"), rset.getDate("regdate"),
						rset.getDate("modifydate"), rset.getInt("point"), rset.getInt("news"), rset.getInt("sms"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

// 마이페이지에서 개인정보수정 접근 시
	public boolean checkPw(Connection conn, String id, String pw) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		boolean pwCheck = false;
		String query = prop.getProperty("pwCheck");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pwCheck = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return pwCheck;
	}

// 관리자 페이지 멤버 조회
	public ArrayList<Member> selectAll(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = null;

		String query = prop.getProperty("selectAllMember");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<>();

			while (rset.next()) {
				Member member = new Member(rset.getString("id"), rset.getString("name"), rset.getString("nickname"),
						rset.getString("phone"), rset.getString("gender"), rset.getString("email"),
						rset.getString("grade"), rset.getDate("birth"), rset.getDate("regdate"),
						rset.getDate("modifydate"), rset.getInt("point"), rset.getInt("news"), rset.getInt("sms"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

// 회원가입
	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertMember");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getNickName());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getGender());
			pstmt.setString(7, member.getEmail());
			pstmt.setDate(8, member.getBirth());
			pstmt.setInt(9, member.getNews());
			pstmt.setInt(10, member.getSms());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

// 아이디 중복확인
	public int checkId(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("idCheck");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

// 닉네임 중복확인
	public int checkNick(Connection conn, String nickName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("nickCheck");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nickName);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int checkEmail(Connection conn, String email) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("emailCheck");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

// 관리자페이지에서 회원정보 수정
	public int adminUpdateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("adminUpdateMember");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getNickName());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getEmail());
			pstmt.setInt(6, member.getNews());
			pstmt.setInt(7, member.getSms());
			pstmt.setInt(8, member.getGrade_code());
			pstmt.setString(9, member.getId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

// 주소 입력
	public int addressInsert(Connection conn, Address address) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertAddress");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, address.getPostNum());
			pstmt.setString(2, address.getRoadAddress());
			pstmt.setString(3, address.getJibunAddress());
			pstmt.setString(4, address.getAddress_detail());
			pstmt.setString(5, address.getId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

// 주소 수정
	public int addressUpdate(Connection conn, Address add) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("addressUpdate");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, add.getPostNum());
			pstmt.setString(2, add.getRoadAddress());
			pstmt.setString(3, add.getJibunAddress());
			pstmt.setString(4, add.getAddress_detail());
			pstmt.setInt(5, add.getAddress_code());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

// 주소 삭제
	public int addressDelete(Connection conn, Address address) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("addressDelete");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, address.getAddress_code());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

// 주소 가져오기
	public ArrayList<Address> getAddress(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Address> addressList = null;

		String query = prop.getProperty("getAddress");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();

			addressList = new ArrayList<>();
			while (rset.next()) {
				Address address = new Address(rset.getInt("address_code"), rset.getString("postnum"),
						rset.getString("roadaddress"), rset.getString("jibunaddress"),
						rset.getString("address_detail"));
				addressList.add(address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return addressList;
	}

// 해당 아이디의 주소 가져오기
	public int getAddressCount(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("getAddressCount");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return result;
	}

	public Address selectAdr(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Address adr = null;

		String query = prop.getProperty("selectAdr");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if (rset.next()) {

				adr = new Address(rset.getString("postNum"), rset.getString("roadAddress"),
						rset.getString("jibunAddress"), rset.getString("address_detail"), rset.getString("id"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return adr;
	}

	public int insertReview(Connection conn, Review r) {
		PreparedStatement pstmt = null;
		int result = 0;
		System.out.println("리뷰입력시작");
		String query = prop.getProperty("insertReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, r.getrType());
			pstmt.setInt(2, r.getpId());
			pstmt.setString(3, r.getrWriter());
			pstmt.setString(4, r.getrTitle());
			pstmt.setString(5, r.getrContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
			System.out.println("리뷰입력끝");
		return result;
	}

	public int insertRAttachment(Connection conn, ArrayList<RAttachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		System.out.println("사진입력시작");
		String query = prop.getProperty("insertRAttachment");

		try {
			for (int i = 0; i < fileList.size(); i++) {
				RAttachment at = fileList.get(i);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePAth());
				pstmt.setInt(4, at.getpId());
				
				result += pstmt.executeUpdate(); // 계속 더해줄것
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("사진입력끝");
		return result;
	}

}
