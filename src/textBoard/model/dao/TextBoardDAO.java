package textBoard.model.dao;

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

import textBoard.model.vo.TextBoard;
import textBoard.model.vo.TextBoardReply;

public class TextBoardDAO {
	private Properties prop = new Properties();
	public TextBoardDAO() {
		String fileName = TextBoardDAO.class.getResource("/sql/TextBoard/Textboard-query1.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("getListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
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

	public ArrayList<TextBoard> selectList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<TextBoard> list = null;
		int posts = 20;
		
		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;
		
		String query = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
		
			rset = pstmt.executeQuery();
			
			list = new ArrayList<TextBoard>();
			
			
			while(rset.next()) {
				TextBoard tb = new TextBoard(rset.getInt("tbid"),
									rset.getString("tbtitle"),
									rset.getString("tbcontent"),
									rset.getString("nickname"),
									rset.getInt("tbcount"),
									rset.getDate("create_date"),
									rset.getDate("modify_date"),
									rset.getInt("tbtype"),
									rset.getString("status"));	
				list.add(tb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<TextBoard> selectNlist(Connection conn, int ntype) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<TextBoard> list = null;
		
		String query = prop.getProperty("selectNlist");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ntype);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<TextBoard>();
			
			while(rset.next()) {
				TextBoard tb = new TextBoard(rset.getInt("tbid"),
									rset.getString("tbtitle"),
									rset.getString("tbcontent"),
									rset.getString("nickname"),
									rset.getInt("tbcount"),
									rset.getDate("create_date"),
									rset.getDate("modify_date"),
									rset.getInt("tbtype"),
									rset.getString("status"));	
				list.add(tb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int insertTextBoard(Connection conn, TextBoard tb) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertTextBoard");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tb.getTbTitle());
			pstmt.setString(2, tb.getTbContent());
			pstmt.setString(3, tb.getTbWriter());
			pstmt.setInt(4, tb.getTbType());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	/*this.tbid = tbid;
		this.tbTitle = tbTitle;
		this.tbContent = tbContent;
		this.tbWriter = tbWriter;
		this.tbCount = tbCount;
		this.createDate = createDate;
		ModifyDate = modifyDate;
		this.tbType = tbType;
		this.status = status;*/
	public TextBoard selectTextBoard(Connection conn, int tbid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TextBoard tb = null;
		
		String query = prop.getProperty("selectText");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tbid);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				tb = new TextBoard(rset.getInt("tbid"),
											 rset.getString("tbtitle"),
											 rset.getString("tbcontent"),
											 rset.getString("nickname"),
											 rset.getInt("tbcount"),
											 rset.getDate("create_date"),
											 rset.getDate("modify_date"),
											 rset.getInt("tbtype"),
											 rset.getString("status"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return tb;
	}
	
	public ArrayList<TextBoardReply> selectReplyList(Connection conn, int tbid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<TextBoardReply> list = null;
		
		
		
		String query = prop.getProperty("selectReplyList");

		
		try {
			System.out.println(tbid);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tbid);
			rset = pstmt.executeQuery();
								
			list = new ArrayList<TextBoardReply>();
			
			while(rset.next()) {
				list.add(new TextBoardReply(rset.getInt("trid"),
											 rset.getString("trcontent"),
											 rset.getInt("ref_tbid"),
											 rset.getString("nickname"),
											 rset.getDate("create_date"),
											 rset.getString("status")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}


	public int insertTextBoardReply(Connection conn, TextBoardReply tr) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tr.getTrcontent());
			pstmt.setInt(2, tr.getTbid());
			pstmt.setString(3, tr.getTrwriter());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteReply(Connection conn, int trid) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("deleteTextBoardReply");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, trid);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateCount(Connection conn, int tbid) {
		PreparedStatement pstmt= null;
		int result=0;
		
		String query = prop.getProperty("tbUpdateCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tbid);
			pstmt.setInt(2, tbid);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteBoard(Connection conn, int tbid) {
		PreparedStatement pstmt= null;
		int result=0;
		
		String query = prop.getProperty("deleteTextBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tbid);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateTextBoard(Connection conn, TextBoard tb) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateTextBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tb.getTbTitle());
			pstmt.setString(2, tb.getTbContent());
			pstmt.setInt(3, tb.getTbid());
			
			
			result = pstmt.executeUpdate();
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		System.out.println("asdsa" + result);
		return result;
		
	}
}
