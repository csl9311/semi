package karaoke.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import karaoke.model.dao.KaraokeDAO;
import karaoke.model.vo.Attachment;
import karaoke.model.vo.Karaoke;
import karaoke.model.vo.Review;
import member.model.vo.Address;

public class KaraokeService {
	
	public int insertAddress(Address a) {
		Connection conn = getConnection();
		
		KaraokeDAO dao = new KaraokeDAO();
		
		int result1 = dao.insertAddress(conn, a);
		int result2 = dao.selectAddress(conn, a);
		
		if(result1 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return  result2;

		}

	public int insertKaroke(Karaoke k, ArrayList<Attachment> fileList) {
		Connection conn = getConnection();
		
		KaraokeDAO dao = new KaraokeDAO();
		
		int result1 = dao.insertKaraoke(conn, k);
		int result2 = dao.insertAttachment(conn, fileList);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return  result1;

		}

	public int getListCount() {
		Connection conn = getConnection();
		int result = new KaraokeDAO().getListCount(conn);
		close(conn);
				
		return result;
	}

	public ArrayList<Karaoke> selectList(int currentPage) {
		Connection conn = getConnection();
		ArrayList<Karaoke> list = new KaraokeDAO().selectList(conn, currentPage);
		close(conn);
		return list;
	}

	public ArrayList<Attachment> selectAlist(int currentPage) {
		Connection conn = getConnection();
		ArrayList<Attachment> at = new KaraokeDAO().selectAlist(conn, currentPage);
		close(conn);
		return at;
	}

	public Karaoke selectKaraoke(int kid) {
		Connection conn = getConnection();
		Karaoke karaoke = new KaraokeDAO().selectKaraoke(conn, kid);
		close(conn);
		
		return karaoke;
	}

	public ArrayList<Attachment> selectAttachment(int kid) {
		Connection conn = getConnection();
		ArrayList<Attachment> list = new KaraokeDAO().selectAttachment(conn, kid);
		close(conn);
		return list;
	}

	public int selectReview(String krwriter) {
		Connection conn = getConnection();
		int result = new KaraokeDAO().selectReview(conn, krwriter);
		close(conn);
		
		return result;
	}

	public int insertReview(Review r) {
		Connection conn = getConnection();
		
		int result = new KaraokeDAO().insertReview(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public int updateReview(Review r) {
		Connection conn = getConnection();
		
		int result = new KaraokeDAO().updateReview(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}


	public ArrayList<Review> selectReviewList(int ref_kid) {
		Connection conn = getConnection();
		ArrayList<Review> list = new KaraokeDAO().selectReviewList(conn, ref_kid);
		close(conn);
		
		return list;
	}

	public double selectRatingAvg(int kid) {
		Connection conn = getConnection();
		double result = new KaraokeDAO().selectRatingAvg(conn, kid);
		close(conn);
		return result;
	}

	public ArrayList<Review> selectRList() {
		Connection conn = getConnection();
		ArrayList<Review> list = new KaraokeDAO().selectRList(conn);
		close(conn);
		return list;
	}
}
