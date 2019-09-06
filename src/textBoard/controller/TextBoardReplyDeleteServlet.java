package textBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import textBoard.model.service.TextBoardService;

/**
 * Servlet implementation class TextBoardReplyDeleteServlet
 */
@WebServlet("/deletetr.tb")
public class TextBoardReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TextBoardReplyDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int trid = Integer.parseInt(request.getParameter("trid"));
		int tbid = Integer.parseInt(request.getParameter("tbid"));
		int result =new TextBoardService().deleteTextBoardReply(trid);
		
		String page = null;
		if(result>0) {
			page ="/detail.tb?tbid="+ tbid;
		}else {
			page="view/common/errorPage.jsp";
			request.setAttribute("msg","댓글 삭제를 실패하였습니다.");
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
