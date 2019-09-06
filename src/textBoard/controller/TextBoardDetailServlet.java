package textBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import textBoard.model.service.TextBoardService;
import textBoard.model.vo.TextBoard;
import textBoard.model.vo.TextBoardReply;

/**
 * Servlet implementation class TextBoardDetailServlet
 */
@WebServlet("/detail.tb")
public class TextBoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TextBoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tbid = Integer.parseInt(request.getParameter("tbid"));
		TextBoardService service = new TextBoardService();
		
		TextBoard tb = service.selectTextBoard(tbid);
		service.updateCount(tbid);
		
		ArrayList<TextBoardReply> list = service.selectReplyList(tbid);
		String page=null;
		
		if(tb != null) {
			page = "views/textBoard/textBoardDetailView.jsp";
			request.setAttribute("tb", tb);
			
			request.setAttribute("list", list);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시글이.. 없어요...");
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
