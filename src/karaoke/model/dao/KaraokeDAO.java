package karaoke.model.dao;

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

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import karaoke.model.vo.Attachment;
import karaoke.model.vo.Karaoke;
import karaoke.model.vo.Review;
import member.model.vo.Address;

public class KaraokeDAO {
	private Properties prop = new Properties();
	public KaraokeDAO() {
		String fileName
			= KaraokeDAO.class.getResource("/sql/Karaoke/1Karaoke-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertAddress(Connection conn, Address a) {
		PreparedStatement pstmt = null;
		int result = 0;
		System.out.println("주소 넣는데 왜");
		String query = prop.getProperty("insertAddress");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, a.getPostNum());
			pstmt.setString(2, a.getRoadAddress());
			pstmt.setString(3, a.getJibunAddress());
			pstmt.setString(4, a.getAddress_detail());
			pstmt.setString(5, a.getId());
						
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);			
		}
		
		return result;
		
	}
	
	public int selectAddress(Connection conn, Address a) {
		Statement stmt = null;
		int result = 0;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectAddress");
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
	
	public int insertKaraoke(Connection conn, Karaoke k) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertKaraoke");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, k.getRefId());
			pstmt.setString(2, k.getKaraokeName());
			pstmt.setInt(3, k.getOneCoin());
			pstmt.setInt(4, k.getThreeCoin());
			pstmt.setString(5, k.getTime());
			pstmt.setInt(6, k.getAddressCode());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertAttachment(Connection conn, ArrayList<Attachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertAttachment");
		try {
			for(int i=0; i<fileList.size(); i++) {
				Attachment at = fileList.get(i);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileLevel());
				
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return  result;
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

	
	public ArrayList<Karaoke> selectList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Karaoke> list = null;
		int posts = 10;
		
		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;
		
		String query = prop.getProperty("selectKaraokeList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Karaoke>();
			
			while(rset.next()) {
				Karaoke k = new Karaoke(rset.getInt("kid"),
									rset.getString("ref_id"),
									rset.getString("kname"),
									rset.getInt("onecoin"),
									rset.getInt("threecoin"),
									rset.getString("ktime"),
									rset.getString("status"),
									rset.getInt("address_code"),
									rset.getString("roadaddress"),
									rset.getString("address_detail"));
				list.add(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Attachment> selectAlist(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Attachment> list = null;
		int posts = 10;
		
		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;
		
		String query = prop.getProperty("selectAttachmentList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Attachment>();
			
			while(rset.next()) {
				list.add(new Attachment(rset.getInt("kid"),
										rset.getString("change_name")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println(list);
		
		return list;
	}

	public Karaoke selectKaraoke(Connection conn, int kid) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Karaoke k = null;
		
		String query = prop.getProperty("selectKaraoke");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, kid);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				k = new Karaoke(rset.getInt("kid"),
								rset.getString("ref_id"),
								rset.getString("kname"),
								rset.getInt("onecoin"),
								rset.getInt("threecoin"),
								rset.getString("ktime"),
								rset.getString("status"),
								rset.getInt("address_code"),
								rset.getString("roadaddress"),
								rset.getString("address_detail"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return k;
	}

	public ArrayList<Attachment> selectAttachment(Connection conn, int kid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Attachment> list  =  null;
		
		String query = prop.getProperty("selectAttachment");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, kid);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Attachment>();
			
			while(rset.next()) {
				Attachment at = new Attachment();
				at.setFid(rset.getInt("fid"));
				at.setOriginName(rset.getString("origin_name"));
				at.setChangeName(rset.getString("change_name"));
				at.setFilePath(rset.getString("file_path"));
				at.setFileLevel(rset.getInt("file_level"));
				
				list.add(at);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int selectReview(Connection conn, String krwriter) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("selectReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, krwriter);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result=rset.getInt("ref_kid");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
		
	}

	public int insertReview(Connection conn, Review r) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertReview");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, r.getKrwriter());
			pstmt.setInt(2, r.getId());
			pstmt.setInt(3, r.getKrating());
			pstmt.setString(4, r.getKrcontent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
			pstmt.setInt(1, r.getKrating());
			pstmt.setString(2, r.getKrcontent());
			pstmt.setString(3, r.getKrwriter());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Review> selectReviewList(Connection conn, int ref_kid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Review> list = null;
		
		String query = prop.getProperty("selectReviewList");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ref_kid);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Review>();
			
			while(rset.next()) {
				Review rv = new Review(rset.getString("nickname"),
									   rset.getInt("ref_kid"),
									   rset.getInt("krating"),
									   rset.getString("krcontent"),
									   rset.getDate("create_date"),
									   rset.getString("status"));	
				list.add(rv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public double selectRatingAvg(Connection conn, int kid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		double result=0;
		
		String query = prop.getProperty("selectRatingAvg");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, kid);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
				
		return result;
	}

	public ArrayList<Review> selectRList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Review> rl = null;
		
		String query = prop.getProperty("selectRlist");
		
		
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			rl = new ArrayList<Review>();
			while(rset.next()) {
				Review r = new Review(rset.getString("krwriter"),
						   			  rset.getInt("ref_kid"),
						   			  rset.getInt("krating"),
						   			  rset.getString("krcontent"),
						   			  rset.getDate("create_date"),
						   			  rset.getString("status"));
				rl.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return rl;
	}
	
}
