package shop.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import product.model.vo.Product;
import shop.model.dao.ShopDAO;
import shop.model.vo.Answer;
import shop.model.vo.PAttachment;
import shop.model.vo.Cart;
import shop.model.vo.RAttachment;
import shop.model.vo.Review;

public class ShopService {

	public int getAllListCount() {
		Connection conn = getConnection();
		int result = new ShopDAO().getAllListCount(conn);
		close(conn);
		return result;
	}

	public int getListCount(String cName) {
		Connection conn = getConnection();
		int result = new ShopDAO().getListCount(conn, cName);
		close(conn);
		return result;
	}

	public ArrayList<Product> selectAllList(int currentPage) {
		Connection conn = getConnection();
		ArrayList<Product> list = new ShopDAO().selectAllList(conn, currentPage);
		close(conn);
		return list;
	}

	public ArrayList<Product> selectList(int currentPage, String cName) {
		Connection conn = getConnection();
		ArrayList<Product> list = new ShopDAO().selectList(conn, currentPage, cName);
		close(conn);
		return list;
	}

	public ArrayList<Product> selectSortList(int currentPage, String cName, String sortBy) {
		Connection conn = getConnection();
		ArrayList<Product> list = new ShopDAO().selectSortList(conn, cName, sortBy, currentPage);
		close(conn);
		return list;
	}

	public Product selectProduct(int pId) {
		Connection conn = getConnection();
		Product p = new ShopDAO().selectProduct(conn, pId);
		close(conn);
		return p;
	}

	public String selectOption(int pId) {
		Connection conn = getConnection();
		String option = new ShopDAO().selectOption(conn, pId);
		close(conn);
		return option;
	}

	public ArrayList<Product> selectKeySortList(int currentPage, String cName, String sortBy, String key) {
		Connection conn = getConnection();
		ArrayList<Product> list = new ShopDAO().selectKeySortList(conn, cName, sortBy, currentPage, key);
		close(conn);
		return list;
	}

	public ArrayList<Product> selectkeyList(int currentPage, String cName, String key) {
		Connection conn = getConnection();
		ArrayList<Product> list = new ShopDAO().selectkeyList(conn, cName, currentPage, key);
		close(conn);
		return list;
	}

	public int getKeyListCount(String cName, String key) {
		Connection conn = getConnection();
		int result = new ShopDAO().getKeyListCount(conn, cName, key);
		close(conn);
		return result;
	}

	public ArrayList<Product> selectSortMainList(int currentPage, String sortBy) {
		Connection conn = getConnection();
		ArrayList<Product> list = new ShopDAO().selectSortMainList(conn, currentPage, sortBy);
		close(conn);
		return list;
	}

	public int getAllKeyListCount(String key) {
		Connection conn = getConnection();
		int result = new ShopDAO().getAllKeyListCount(conn, key);
		close(conn);
		return result;
	}

	public ArrayList<Product> selectKeySortMainList(int currentPage, String sortBy, String key) {
		Connection conn = getConnection();
		ArrayList<Product> list = new ShopDAO().selectKeySortMainList(conn, currentPage, sortBy, key);
		close(conn);
		return list;
	}

	public ArrayList<Product> selectAllkeyList(int currentPage, String key) {
		Connection conn = getConnection();
		ArrayList<Product> list = new ShopDAO().selectAllkeyList(conn, currentPage, key);
		close(conn);
		return list;
	}

	public int getKeyNStockListCount(String cName, String key) {
		Connection conn = getConnection();
		int result = new ShopDAO().getKeyNStockListCount(conn, cName, key);
		close(conn);
		return result;
	}

	public int getStockListCount(String cName) {
		Connection conn = getConnection();
		int result = new ShopDAO().getStockListCount(conn, cName);
		close(conn);
		return result;
	}

	public int getAllKeyNStockListCount(String key) {
		Connection conn = getConnection();
		int result = new ShopDAO().getAllKeyNStockListCount(conn, key);
		close(conn);
		return result;
	}

	public int getAllStockListCount() {
		Connection conn = getConnection();
		int result = new ShopDAO().getAllStockListCount(conn);
		close(conn);
		return result;
	}

	public ArrayList<Review> selectReviewList(int pId, int type) {
		System.out.println(1);
		Connection conn = getConnection();
		ArrayList<Review> list = new ShopDAO().selectReviewList(conn, pId, type);
		close(conn);
		return list;
	}

	public ArrayList<Answer> selectAnswerList(int pId, int type) {
		Connection conn = getConnection();
		ArrayList<Answer> list = new ShopDAO().selectAnswerList(conn, pId, type);
		close(conn);
		return list;
	}


	public int selectWriter(String userId, int rId, String str) {
		Connection conn = getConnection();
		int result = new ShopDAO().selectWriter(conn, userId, rId, str);
		close(conn);
		return result;
	}

	public int updateAnswer(int a_rId, String aContent, Date date) {
		Connection conn = getConnection();
		int result = new ShopDAO().updateAnswer(conn, a_rId, aContent, date);
		close(conn);
		return result;
	}

	public Answer selectAnswer(String userId, int aId) {
		Connection conn = getConnection();
		Answer a = new ShopDAO().selectAnswer(conn, userId, aId);
		close(conn);
		return a;
	}
	public Review selectReview(String userId, int rId) {
		Connection conn = getConnection();
		Review review = new ShopDAO().selectReview(conn, userId, rId);
		close(conn);
		return review;
	}

	/*
	 * public int updateReview(Review r, ArrayList<RAttachment> fileList) {
	 * Connection conn = getConnection(); ShopDAO dao = new ShopDAO();
	 *
	 * int result1 = dao.updateReview(conn, r); int result2 =
	 * dao.updateRAttachment(conn, fileList, r.getrId(), r.getpId());
	 *
	 * if (result1 > 0 && result2 > 0) { commit(conn); } else { rollback(conn); }
	 * close(conn); return result1+result2; }
	 */
	public int updateReview(Review r) {
		Connection conn = getConnection();
		int result = new ShopDAO().updateReview(conn, r);
		close(conn);
		return result;
	}

	public ArrayList<RAttachment> selectRAttachmentList(int pId) {
		Connection conn = getConnection();
		ArrayList<RAttachment> attList = new ShopDAO().selectRAttachmentList(conn, pId);
		close(conn);
		return attList;
	}

	public int deleteRAttachemnt(ArrayList<RAttachment> atList) {
		Connection conn = getConnection();
		int result = new ShopDAO().deleteRAttachemnt(conn, atList);
		close(conn);
		return result;
	}

	public int updateCount(int rId) {
		Connection conn = getConnection();
		int result = new ShopDAO().updateCount(conn, rId);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int selectReviewCount(int rId) {
		Connection conn = getConnection();
		int result = new ShopDAO().selectReviewCount(conn, rId);
		close(conn);
		return result;
	}

	public ArrayList<PAttachment> selectPAttachmentList(int pId) {
		Connection conn = getConnection();
		ArrayList<PAttachment> pAttList = new ShopDAO().selectPAttachmentList(conn, pId);
		close(conn);
		return pAttList;
	}

	public PAttachment selectPAttachment(int pId) {
		Connection conn = getConnection();
		PAttachment thumbP = new ShopDAO().selectPAttachment(conn, pId);
		close(conn);
		return thumbP;
	}

	public ArrayList<PAttachment> selectAllPAttachmentList() {
		Connection conn = getConnection();
		ArrayList<PAttachment> pAttList = new ShopDAO().selectAllPAttachmentList(conn);
		close(conn);
		return pAttList;
	}

	public ArrayList<Cart> selectpay(String userId) {
		Connection conn= getConnection();
		ArrayList<Cart> pay = new ShopDAO().selectpay(conn, userId);

		close(conn);
		return pay;
	}

	public int insertCart(String userId, Product product) {
		Connection conn = getConnection();
		int result= new ShopDAO().insertCart(conn, userId, product);


		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}


		return result;
	}

	public ArrayList<Cart> selectCart(String userId) {
		Connection conn= getConnection();
		ArrayList<Cart> info = new ShopDAO().selectCart(conn, userId);
		close(conn);
		return info;
	}

	public Cart selectPurchase(String userId, int vrr) {
		Connection conn= getConnection();
		ShopDAO dao = new ShopDAO();
		Cart pay = dao.selectPurchase(conn, userId,vrr);

		int result= dao.deleteCart(conn, userId,vrr);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}

		return pay;
	}

		public int deleteCart(int caid,String userId) {
		
		Connection conn = getConnection();
		int result = new ShopDAO().deleteCart(conn, userId, caid);
		System.out.println("dd"+result);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}

}
