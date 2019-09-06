package community.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.BoardService;

/**
 * Servlet implementation class ReplyDeleteServlet
 */
@WebServlet("/deletereply.bo")
public class ReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rno = Integer.parseInt(request.getParameter("no"));
		int bid = Integer.parseInt(request.getParameter("bid"));
		System.out.println("댓글삭제 "+rno);
		int result =new BoardService().deleteReply(rno);
		
		String page = null;
		System.out.println("댓삭결과"+result);
		if(result>0) {
			page ="/avidetail.bo?bid="+ bid;
			
		}else {
			page="view/common/errorPage.jsp";
			request.setAttribute("msg","게시글 삭제를 실패하였습니다.");
			
			
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
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
