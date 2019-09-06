package textBoard.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import textBoard.model.dao.TextBoardDAO;
import textBoard.model.vo.TextBoard;
import textBoard.model.vo.TextBoardReply;

public class TextBoardService {

	public int getListCount() {
		Connection conn = getConnection();
		int result = new TextBoardDAO().getListCount(conn);
		close(conn);
		
		return result;
	}

	public ArrayList<TextBoard> selectList(int currentPage) {
		Connection conn = getConnection();
		ArrayList<TextBoard> list = new TextBoardDAO().selectList(conn, currentPage);
		close(conn);
		return list;
	}
	
	public ArrayList<TextBoard> selectNlist(int ntype) {
		Connection conn = getConnection();
		ArrayList<TextBoard> list = new TextBoardDAO().selectNlist(conn, ntype);
		close(conn);
		return list;
	}

	public int insertTextBoard(TextBoard tb) {
		Connection conn = getConnection();
		int result = new TextBoardDAO().insertTextBoard(conn, tb);
		
		if(result > 0) {
			commit(conn);
		} else {
			close(conn);
		}
		
		return result;
	}

	public TextBoard selectTextBoard(int tbid) {
		Connection conn = getConnection();
		TextBoard tb = new TextBoardDAO().selectTextBoard(conn, tbid);
		
		close(conn);
		
		return tb;
	}
	
	public ArrayList<TextBoardReply> selectReplyList(int tbid) {
		Connection conn = getConnection();
		ArrayList<TextBoardReply> list = new TextBoardDAO().selectReplyList(conn, tbid);
		close(conn);
		return list;
	}

	public ArrayList<TextBoardReply> insertReply(TextBoardReply tr) {
		Connection conn = getConnection();
		TextBoardDAO dao = new TextBoardDAO();
		int result = dao.insertTextBoardReply(conn, tr);
		ArrayList<TextBoardReply> rlist = null;
		
		if(result > 0) {
			commit(conn);
			rlist = dao.selectReplyList(conn, tr.getTbid());
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return rlist;
	}

	public int deleteTextBoardReply(int trid) {
		Connection conn = getConnection();
		
		int result = new TextBoardDAO().deleteReply(conn,trid);
		if(result > 0) {
			commit(conn);
			     
		} else {
			rollback(conn);
		}
			close(conn);
		return result;
	}

	public int updateCount(int tbid) {
		Connection conn = getConnection();
		int result = new TextBoardDAO().updateCount(conn, tbid);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}

	public int deleteTextBoard(int tbid) {
		Connection conn = getConnection();
		int result = new TextBoardDAO().deleteBoard(conn, tbid);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int updateTextBoard(TextBoard tb) {
		Connection conn = getConnection();
		int result = new TextBoardDAO().updateTextBoard(conn, tb);
		
		System.out.println(tb.getTbid());
		if(result >0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

}
