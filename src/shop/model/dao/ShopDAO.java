package shop.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import member.model.dao.MemberDAO;
import product.model.vo.Product;
import shop.model.vo.Answer;
import shop.model.vo.PAttachment;
import shop.model.vo.Cart;
import shop.model.vo.RAttachment;
import shop.model.vo.Review;

public class ShopDAO {
	private Properties prop = new Properties();

	public ShopDAO() {
		String fileName = MemberDAO.class.getResource("/sql/shop/shop-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 메인페이지 전체 카운팅
	public int getAllListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("getAllListCount");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				result = rset.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return result;
	}
	
	// 메인페이지 전부 list로 리턴하는 메소드
	public ArrayList<Product> selectAllList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = null;

		int posts = 8;

		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;

		String query = prop.getProperty("selectAllList");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			list = new ArrayList<Product>();
			while (rset.next()) {
				Product p = new Product(rset.getInt("pId"), 
										rset.getInt("price"), 
										rset.getString("pName"),
										rset.getString("cName"), 
										rset.getString("sub_cname"), 
										rset.getString("bName"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;

	}
	
	// 카테고리 카운팅
	public int getListCount(Connection conn, String cName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("getListCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cName);
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
	
	// 카테고리 리스트
	public ArrayList<Product> selectList(Connection conn, int currentPage, String cName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> pList = null;
		int posts = 8;
		
		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;
		String query = prop.getProperty("selectList");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cName);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			pList = new ArrayList<Product>();
			while (rset.next()) {
				Product p = new Product(rset.getInt("pId"), 
										rset.getInt("price"), 
										rset.getString("pName"),
										rset.getString("cName"), 
										rset.getString("sub_cname"), 
										rset.getString("bName"));
				pList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return pList;

	}
	
	// 카테고리 페이지 검색어 입력시 총 수량 카운팅
	public int getKeyListCount(Connection conn, String cName, String key) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("getKeyListCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cName);
			pstmt.setString(2, "%" + key +"%");
			pstmt.setString(3, "%" + key +"%");
			pstmt.setString(4, "%" + key +"%");
			
			
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
	
	// 카테고리 페이지 정렬기준 선택시 상품 리스트 반환
	public ArrayList<Product> selectSortList(Connection conn, String cName, String sortBy, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();

		int posts = 8;

		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;

		String query = prop.getProperty("selectSortList"+sortBy);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cName);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Product p = new Product(rset.getInt("pId"), 
										rset.getInt("price"), 
										rset.getString("pName"),
										rset.getString("cName"), 
										rset.getString("sub_cname"), 
										rset.getString("bName"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	// 상품 클릭시 해당 상품 반환
	public Product selectProduct(Connection conn, int pId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product p = null;
		
		String query = prop.getProperty("selectProduct");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				p = new Product(rset.getInt("pid"),
								rset.getInt("price"),
								rset.getInt("stock"),
								rset.getInt("sellCount"),
								rset.getString("pname"),
								rset.getString("cname"),
								rset.getString("sub_cname"),
								rset.getString("bname"),
								rset.getString("useoption"),
								rset.getDate("regDate"),
								rset.getDate("modify_date"));
						
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return p;
	}
	
	// 상품에 옵션이 있을 시, 옵션 반환
	public String selectOption(Connection conn, int pId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product option = null;
		
		String query = prop.getProperty("selectOption");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				option = new Product(
							rset.getString("product_op")); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return option.getOption();
	}
	
	// 카테고리 페이지 키워드와 정렬기준이 선택되었을 경우 해당 상품 리스트 반환
	public ArrayList<Product> selectKeySortList(Connection conn, String cName, String sortBy, int currentPage, String key) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();

		int posts = 8;

		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;

		String query = prop.getProperty("selectKeySortList"+sortBy);
		System.out.println(query);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cName);
			pstmt.setString(2, "%" + key +"%");
			pstmt.setString(3, "%" + key +"%");
			pstmt.setString(4, "%" + key +"%");
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, endRow);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Product p = new Product(rset.getInt("pId"), 
										rset.getInt("price"), 
										rset.getString("pName"),
										rset.getString("cName"), 
										rset.getString("sub_cname"), 
										rset.getString("bName"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	// 카테고리내부 키워드 입력시 해당 리스트 반환
	public ArrayList<Product> selectkeyList(Connection conn, String cName, int currentPage, String key) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();

		int posts = 8;

		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;

		String query = prop.getProperty("selectKeyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key +"%");
			pstmt.setString(2, "%" + key +"%");
			pstmt.setString(3, "%" + key +"%");
			pstmt.setString(4, cName);
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, endRow);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Product p = new Product(rset.getInt("pId"), 
										rset.getInt("price"), 
										rset.getString("pName"),
										rset.getString("cName"), 
										rset.getString("sub_cname"), 
										rset.getString("bName"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	// 메인 페이지 정렬기준 선택시 해당 리스트 반환
	public ArrayList<Product> selectSortMainList(Connection conn, int currentPage, String sortBy) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();

		int posts = 8;

		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;

		String query = prop.getProperty("selectSortMainList"+sortBy);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Product p = new Product(rset.getInt("pId"), 
										rset.getInt("price"), 
										rset.getString("pName"),
										rset.getString("cName"), 
										rset.getString("sub_cname"), 
										rset.getString("bName"));
				list.add(p);
			}
			list.size();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	// 메인페이지 키워드 입력시 해당 상품들 카운팅
	public int getAllKeyListCount(Connection conn, String key) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("getAllKeyListCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key +"%");
			pstmt.setString(2, "%" + key +"%");
			pstmt.setString(3, "%" + key +"%");
			
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

	// 메인 페이지 키워드와 정렬기준 선택시 해당 리스트 반환
	public ArrayList<Product> selectKeySortMainList(Connection conn, int currentPage, String sortBy, String key) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();

		int posts = 8;

		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;

		String query = prop.getProperty("selectKeySortMainList"+sortBy);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key +"%");
			pstmt.setString(2, "%" + key +"%");
			pstmt.setString(3, "%" + key +"%");
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Product p = new Product(rset.getInt("pId"), 
										rset.getInt("price"), 
										rset.getString("pName"),
										rset.getString("cName"), 
										rset.getString("sub_cname"), 
										rset.getString("bName"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 메인 페이지 키워드 입력시 해당 리스트 반환
	public ArrayList<Product> selectAllkeyList(Connection conn, int currentPage, String key) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();

		int posts = 8;

		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;

		String query = prop.getProperty("selectAllKeyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key +"%");
			pstmt.setString(2, "%" + key +"%");
			pstmt.setString(3, "%" + key +"%");
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Product p = new Product(rset.getInt("pId"), 
										rset.getInt("price"), 
										rset.getString("pName"),
										rset.getString("cName"), 
										rset.getString("sub_cname"), 
										rset.getString("bName"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	// 카테고리페이지 키워드와 품절 포함 체크시 해당 수량 카운팅
	public int getKeyNStockListCount(Connection conn, String cName, String key) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("getKeyNStockListCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cName);
			pstmt.setString(2, "%" + key +"%");
			pstmt.setString(3, "%" + key +"%");
			pstmt.setString(4, "%" + key +"%");
			
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
	
	// 카테고리 페이지 품절 포함 체크시 해당 수량 카운팅
	public int getStockListCount(Connection conn, String cName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("getStockListCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cName);
			
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
	
	// 메인 페이지 키워드와 품절 포함 체크시 해당 수량 카운팅
	public int getAllKeyNStockListCount(Connection conn, String key) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("getAllKeyNStockListCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key +"%");
			pstmt.setString(2, "%" + key +"%");
			pstmt.setString(3, "%" + key +"%");
			
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
	
	// 메인페이지 품절 포함 체크시 해당 수량 카운팅
	public int getAllStockListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("getAllStockListCount");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				result = rset.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return result;
	}
	
	// 사용자의 상품평과 QnA 리스트 
	public ArrayList<Review> selectReviewList(Connection conn, int pId, int type) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Review> list = null;
		
		String query = prop.getProperty("selectReviewList");
		System.out.println(2);
		
		try {
			pstmt = conn.prepareStatement(query);
			System.out.println(3);
			System.out.println(query);
			pstmt.setInt(1, pId);
			pstmt.setInt(2, type);
			rset = pstmt.executeQuery();
			
			System.out.println("rset : " + rset);
			
			list = new ArrayList<Review>();
			while(rset.next()) {
				Review r = new Review( rset.getInt("rId"),
									  rset.getInt("rType"),
									  rset.getInt("pId"),
									  rset.getString("rWriter"),
									  rset.getString("rTitle"),
									  rset.getString("rContent"),
									  rset.getInt("rCount"),
									  rset.getDate("modify_date"));
				list.add(r);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
			
		}
		
		return list;
	}
	
	// 관리자의 리뷰답변과 QnA 리스트
	public ArrayList<Answer> selectAnswerList(Connection conn, int pId, int type) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Answer> list = null;
		
		String query = prop.getProperty("selectAnswerList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pId);
			pstmt.setInt(2, type);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Answer>();
			while(rset.next()) {
				Answer a = new Answer( rset.getInt("aId"),
						  rset.getInt("aType"),
						  rset.getInt("pId"),
						  rset.getInt("a_rId"),
						  rset.getString("aWriter"),
						  rset.getString("aTitle"),
						  rset.getString("aContent"),
						  rset.getInt("aCount"),
						  rset.getDate("modify_date"));
				list.add(a);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int selectWriter(Connection conn, String userId, int rId, String str) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("select" + str + "Writer");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rId);
			pstmt.setString(2, userId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
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

	public int updateReview(Connection conn, Review r) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, r.getrContent());
			pstmt.setDate(2, r.getModifyDate());
			pstmt.setInt(3, r.getrId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateAnswer(Connection conn, int a_rId, String aContent, Date date) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateAnswer");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, aContent);
			pstmt.setDate(2, date);
			pstmt.setInt(3, a_rId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Answer selectAnswer(Connection conn, String userId, int aId) {
		PreparedStatement pstmt = null;
		ResultSet r = null;
		Answer a = null;
		
		String query = prop.getProperty("selectAnswer");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, aId);
			
			r = pstmt.executeQuery();
			
			if(r.next()) {
				a = new Answer(r.getString("aContent"),
								r.getDate("modify_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(r);
			close(pstmt);
		}
		return a;
	}

	public Review selectReview(Connection conn, String userId, int rId) {
		PreparedStatement pstmt = null;
		ResultSet r = null;
		Review review = null;
		
		String query = prop.getProperty("selectReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, rId);
			
			r = pstmt.executeQuery();
			
			if(r.next()) {
				review = new Review(r.getString("rContent"),
								r.getDate("modify_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(r);
			close(pstmt);
		}
		return review;
	}
	
	public int updateRAttachment(Connection conn, ArrayList<RAttachment> fileList, int rId, int pId) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("updateRAttachment");

		try {
			for (int i = 0; i < fileList.size(); i++) {
				RAttachment at = fileList.get(i);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePAth());
				pstmt.setInt(4, rId);
				pstmt.setInt(5, pId);

				result += pstmt.executeUpdate(); // 계속 더해줄것

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<RAttachment> selectRAttachmentList(Connection conn, int pId) {
		PreparedStatement pstmt = null;
		ResultSet r = null;
		ArrayList<RAttachment> attList = new ArrayList<RAttachment>();
		RAttachment rat = null;
		System.out.println(pId);
		System.out.println(pId+1);
		
		String query = prop.getProperty("selectRAttachmentList");
		
		try {
			pstmt = conn.prepareStatement(query);
			System.out.println(query);
			pstmt.setInt(1, pId);
			
			r = pstmt.executeQuery();
			
			while(r.next()) {
				rat = new RAttachment(r.getInt("rfid"),
										r.getString("change_name"),
										r.getInt("rId"));
				attList.add(rat);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(r);
			close(pstmt);
		}
		
		return attList;
	}

	public int deleteRAttachemnt(Connection conn, ArrayList<RAttachment> atList) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteRAttachemnt");
		int result = 0;
		
		try {
			for (int i = 0; i < atList.size(); i++) {
				RAttachment at = atList.get(i);

				pstmt = conn.prepareStatement(query);
				System.out.println(query);
				pstmt.setString(1, at.getChangeName());
				
				result = pstmt.executeUpdate();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateCount(Connection conn, int rId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rId);
			pstmt.setInt(2, rId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectReviewCount(Connection conn, int rId) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		int result = 0;
		
		String query = prop.getProperty("selectReviewCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("RCOUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<PAttachment> selectPAttachmentList(Connection conn, int pId) {
		PreparedStatement pstmt = null;
		ResultSet r = null;
		ArrayList<PAttachment> pattList = new ArrayList<PAttachment>();
		PAttachment pat = null;
		
		String query = prop.getProperty("selectPAttachmentList");
		
		try {
			pstmt = conn.prepareStatement(query);
			System.out.println(query);
			pstmt.setInt(1, pId);
			
			r = pstmt.executeQuery();
			
			while(r.next()) {
				pat = new PAttachment(r.getInt("pfid"),
										r.getString("file_path"),
										r.getInt("filelevel"));
				pattList.add(pat);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(r);
			close(pstmt);
		}
		
		return pattList;
	}

	public PAttachment selectPAttachment(Connection conn, int pId) {
		PreparedStatement pstmt = null;
		ResultSet r = null;
		PAttachment thumbP = new PAttachment();
		
		String query = prop.getProperty("selectPAttachment");
		
		try {
			pstmt = conn.prepareStatement(query);
			System.out.println(query);
			pstmt.setInt(1, pId);
			
			r = pstmt.executeQuery();
			
			if(r.next()) {
				thumbP = new PAttachment(r.getInt("pfid"),
										r.getString("file_path"),
										r.getInt("filelevel"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(r);
			close(pstmt);
		}
		return thumbP;
	}

	public ArrayList<PAttachment> selectAllPAttachmentList(Connection conn) {
		Statement stmt = null;
		ResultSet r = null;
		ArrayList<PAttachment> pattList = new ArrayList<PAttachment>();
		PAttachment pat = null;
		
		String query = prop.getProperty("selectAllPAttachmentList");
		
		try {
			stmt = conn.createStatement();
			System.out.println(query);
			
			r = stmt.executeQuery(query);
			
			while(r.next()) {
				pat = new PAttachment(r.getInt("pfid"),
										r.getString("file_path"),
										r.getInt("pId"),
										r.getInt("filelevel"));
				pattList.add(pat);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(r);
			close(stmt);
		}
		
		return pattList;
	}

	public Cart selectPurchase(Connection conn, String userId, int arr) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Cart payment=null;
		String query = prop.getProperty("selectpurchase");
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, arr);
			
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
				payment = new Cart(rset.getInt(1),
						  rset.getInt(2),
						  rset.getInt(3),
						  rset.getInt(4),
						  rset.getString(5),
						  rset.getString(6),
						  rset.getString(7));
	
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		return payment;
	}

	public ArrayList<Cart> selectpay(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectpay");
		
		ArrayList<Cart> pay = new ArrayList<Cart>();
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset= pstmt.executeQuery();
			
			while(rset.next()) {
				Cart payment = new Cart(rset.getInt(1),
									  rset.getInt(2),
									  rset.getInt(3),
									  rset.getInt(4),
									  rset.getString(5),
									  rset.getString(6),
									  rset.getString(7));
				
				pay.add(payment);
			
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		return pay;
	}

	public int deleteCart(Connection conn, String userId, int arr) {
		PreparedStatement pstmt =null;
		int result=0;
		
		String query= prop.getProperty("deletecart");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, arr);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public ArrayList<Cart> selectCart(Connection conn, String userId) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectCart");
		
		ArrayList<Cart> info = new ArrayList<Cart>();
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset= pstmt.executeQuery();
			
			while(rset.next()) {
				Cart payment = new Cart(rset.getInt(1),
									  rset.getInt(2),
									  rset.getInt(3),
									  rset.getInt(4),
									  rset.getString(5),
									  rset.getString(6),
									  rset.getString(7));
				
				info.add(payment);
			
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		return info;
	}
	
	public int insertCart(Connection conn, String userId, Product product) {
		PreparedStatement pstmt = null;
		int result=0;
		
		String query =prop.getProperty("insertCart");
		
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, product.getpId());
			pstmt.setInt(2,  product.getPrice());
			pstmt.setInt(3, product.getAmount());
			pstmt.setString(4, product.getpName());
			pstmt.setString(5, product.getOption());
			pstmt.setString(6, userId);
			
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

}