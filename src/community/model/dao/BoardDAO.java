package community.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import community.model.vo.Board;
import community.model.vo.Reply;

public class BoardDAO {

	private Properties prop = new Properties();

	public BoardDAO() {
		String fileName = BoardDAO.class.getResource("/sql/board/board-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // 쿼리문을 DAO에 받아오는곳

	public int insertBoard(Connection conn, Board b) {

		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertAVIBoard");
		System.out.println(b.getbWriter());
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getbTitle());
			pstmt.setString(2, b.getbContent());
			pstmt.setString(3, b.getbWriter());
			pstmt.setString(4, b.getbAddress());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}

	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;

		int result = 0;

		String query = prop.getProperty("getListAVICount");
		System.out.println("dddd"+query);
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				result = rset.getInt(1);	
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		System.out.println("결과값:"+result);
		return result;
	}

	public int getListCount2(Connection conn, Board b) {
		PreparedStatement stmt = null;
		ResultSet rset = null;

		int result = 0;

		String query = prop.getProperty("getListAVICountSearch");

		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, '%' + b.getbContent() + '%');
			stmt.setString(2, '%' + b.getbContent() + '%');
			rset = stmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}
			System.out.println("검색카운트" + result);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return result;
	}

	public ArrayList<Board> selectList(Connection conn, int currentPage, int listCount, Board bb) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		int posts = 9; // 한 페이지에서 보여질 게시글 갯수

		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;
		System.out.println("스타트" + startRow);
		System.out.println("끝" + endRow);

		System.out.println("마지막글" + endRow);
		System.out.println("총갯수" + listCount);
		String query = "";
		if (bb.getbContent() == null || bb.getbContent() == "") {
			query = prop.getProperty("selectAVIList");
		} else {
			query = prop.getProperty("selectAVISerachList");
			System.out.println("listsearch"+query);
		}

		try {
			pstmt = conn.prepareStatement(query);

			if (bb.getbContent() == null || bb.getbContent() == "") {
				pstmt.setInt(2, ((listCount + 1) - startRow));
				pstmt.setInt(1, ((listCount + 1) - endRow));

			} else {
				pstmt.setString(1, '%' + bb.getbContent() + '%');
				pstmt.setString(2, '%' + bb.getbContent() + '%');
				pstmt.setInt(4, ((listCount + 1) - startRow));
				pstmt.setInt(3, ((listCount + 1) - endRow));
				  
				  System.out.println("처음검색:"+startRow);
				  System.out.println("마지막:"+endRow);
				System.out.println("검색결과" + bb.getbContent());
							}
			rset = pstmt.executeQuery();

			list = new ArrayList<Board>();

			while (rset.next()) {
				Board b = new Board(rset.getInt("bid"), rset.getInt("btype"), rset.getString("btitle"),
						rset.getString("byoutubeaddress"), rset.getString("bcontent"), rset.getString("bwriter"),
						rset.getInt("bcount"), rset.getInt("bgood"), rset.getDate("create_date"),
						rset.getDate("modify_date"), rset.getString("status"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;

	}

	public Board boardDetail(Connection conn, int bid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		Board board = null;

		String query = prop.getProperty("boardAVIDetail");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bid);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				board = new Board(rset.getInt("bid"), rset.getInt("btype"), rset.getString("btitle"),
						rset.getString("byoutubeaddress"), rset.getString("bcontent"), rset.getString("bwriter"),
						rset.getInt("bcount"), rset.getInt("bgood"), rset.getDate("create_date"),
						rset.getDate("modify_date"), rset.getString("status"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return board;
	}

	public int updateCount(Connection conn, int bid) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("updateCount");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bid);
			pstmt.setInt(2, bid);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public ArrayList<Reply> selectReplyList(Connection conn, int bid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Reply> rlist = null;

		String query = prop.getProperty("selectReplyList");
		// DB켜서 view 생성 해야함!

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bid);
			rs = pstmt.executeQuery();

			rlist = new ArrayList<Reply>();

			while (rs.next()) {
				rlist.add(new Reply(rs.getInt("rid"), rs.getString("rcontent"), rs.getInt("ref_abid"),
						rs.getString("rwriter"), rs.getDate("create_date"), rs.getDate("modify_date"),
						rs.getString("status")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return rlist;
	}

	public int insertReply(Connection conn, Reply r) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertReply");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, r.getrContent());
			pstmt.setString(2, r.getrWriter());
			pstmt.setInt(3, r.getRefBid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertGood(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertGood");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, b.getBid());
			pstmt.setString(2, b.getbWriter());
			pstmt.setString(3, b.getUserWriter());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}

	public void insertGoodDB(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertGoodDB");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, b.getBid());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

	}

	public int updateBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;

		int result = 0;

		String query = prop.getProperty("updateAviBoard");

		try {
			pstmt = conn.prepareStatement(query);

			System.out.println(board.getbTitle());
			pstmt.setString(1, board.getbTitle());
			pstmt.setString(2, board.getbContent());
			pstmt.setString(3, board.getbAddress());
			System.out.println("ewqewqwe" + board.getbAddress());
			System.out.println("111" + board.getBid());
			pstmt.setInt(4, board.getBid());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteBoard(Connection conn, int bno) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("deleteBoard");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bno);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteReply(Connection conn, int rno) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("deleteReply");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rno);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public void insertGoodMember(Connection conn,Board b) {
		PreparedStatement pstmt = null;
	
		
		String query = prop.getProperty("insertgoodtomember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getUserWriter());
			pstmt.setString(2, b.getUserWriter());
			
			 pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
	}

	public void getReword(Connection conn) {
		
		
	}

	public ArrayList<Board> selectRewordUser(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		String query = prop.getProperty("rewordUser");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while (rset.next()) {
				Board b = new Board(rset.getInt("rownum"), 
					rset.getString("bwriter"),rset.getInt("good"));
					
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	public ArrayList<Board> selectRewordBoard(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		String query = prop.getProperty("rewordBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			System.out.println(query);
			while (rset.next()) {
				Board b = new Board(rset.getInt("rownum"),rset.getInt("bid"),rset.getString("btitle"),
					rset.getString("bwriter"),rset.getInt("bgood"));
					
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

}
