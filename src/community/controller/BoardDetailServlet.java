package community.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.BoardService;
import community.model.vo.Board;
import community.model.vo.Reply;



/**
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/avidetail.bo")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int bid = Integer.parseInt(request.getParameter("bid"));
		
		/******************ajax 추가시 ****************/
		/* ArrayList<Reply> list = new BoardService().selectReplyList(bid); */
		/***********************************************/
		
		
		Board board =new  BoardService().boardDetail(bid);
		new BoardService().updateCount(bid);
		ArrayList<Reply> list = new BoardService().selectReplyList(bid);
		String page = null;
		if(board!=null) {
			
			page = "views/community/aviBoardDetailView.jsp";
			request.setAttribute("board",board);
			/******************ajax 추가시 ****************/
			request.setAttribute("list",list); 
		}else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시판 조회에 실패하였습니다");
		}
		
		RequestDispatcher view =request.getRequestDispatcher(page);
		view.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
