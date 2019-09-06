package community.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import community.model.dao.BoardDAO;
import community.model.vo.Board;
import community.model.vo.Reply;

public class BoardService {

	
	
	public int insertBoard(Board b) {
		Connection conn =  getConnection();
		int result = new BoardDAO().insertBoard(conn,b);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;

}

	public int getListCount() {
		Connection conn = getConnection();
		int result =0;
		
		result= new BoardDAO().getListCount(conn);
		
		System.out.println("총카운트"+result);

		close(conn);
		return result;
	}
	
	public int getListCount2(Board b) {
		Connection conn = getConnection();
		int result =0;
		
		result= new BoardDAO().getListCount2(conn,b);
		System.out.println("카운트 서치"+b.getbContent());

		close(conn);
		return result;
	}

	public ArrayList<Board> selectList(int currentPage,int listCount,Board b) {
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDAO().selectList(conn,currentPage,listCount,b);
		close(conn);
		
		return list;
		
	}

	public Board boardDetail(int bid) {
		Connection conn = getConnection();
		Board board = new BoardDAO().boardDetail(conn,bid);
		close(conn);
		
		return board;
	}

	public void updateCount(int bid) {
		Connection conn = getConnection();
		
		int result = new BoardDAO().updateCount(conn,bid);
		//커밋을 해줘야하기때문에 정수형 변수가 필요하다
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
	}

	public ArrayList<Reply> selectReplyList(int bid) {
		Connection conn = getConnection();
		ArrayList<Reply> list = new BoardDAO().selectReplyList(conn,bid);
		close(conn);
		
		return list;
	}

	public ArrayList<Reply> insertReply(Reply r) {
		Connection conn = getConnection();
		BoardDAO dao = new BoardDAO();
		
		int result = dao.insertReply(conn,r);
		
		ArrayList<Reply> rlist = null;
		
		if(result > 0) {
			commit(conn);
			rlist = dao.selectReplyList(conn, r.getRefBid());
		}else {
			rollback(conn);
		}
		close(conn);
		return rlist;
	}

	public int insertGood(Board b) {
		
		Connection conn  = getConnection();
		BoardDAO dao =  new BoardDAO();
		
		int result = dao.insertGood(conn,b);
		
		if(result>0) {
			dao.insertGoodDB(conn,b);
			dao.insertGoodMember(conn,b);
			commit(conn);
			
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
			
		
	}
	
	public int updateBoard(Board board) {
		Connection conn = getConnection();
		
		int result = new BoardDAO().updateBoard(conn,board);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int deleteBoard(int bno) {
		Connection conn = getConnection();
		BoardDAO dao = new BoardDAO();
		
		int result = dao.deleteBoard(conn,bno);
		
			     if(result > 0) {
			         commit(conn);
			         
			      } else {
			         rollback(conn);
			      }
			      close(conn);
			      
			      return result;
	}

	public int deleteReply(int rno) {
		Connection conn = getConnection();
		BoardDAO dao = new BoardDAO();
		
		int result = dao.deleteReply(conn,rno);
		
			     if(result > 0) {
			         commit(conn);
			         
			      } else {
			         rollback(conn);
			      }
			      close(conn);
			      
			      return result;
	}

	public ArrayList<Board> selectRewordUser() {
		Connection conn = getConnection();
		ArrayList<Board> Board  = new BoardDAO().selectRewordUser(conn);
		close(conn);
		
		
		return Board;
	}

	public ArrayList<Board> selectRewordBoard() {
		Connection conn = getConnection();
		ArrayList<Board> Board  = new BoardDAO().selectRewordBoard(conn);
		close(conn);
		
		
		return Board;
	}


	
}
