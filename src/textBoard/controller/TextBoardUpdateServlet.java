package textBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import textBoard.model.service.TextBoardService;
import textBoard.model.vo.TextBoard;

/**
 * Servlet implementation class TextBoardUpdateServlet
 */
@WebServlet("/update.tb")
public class TextBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TextBoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("tbTitle");
		String content = request.getParameter("tbContent");
		int tbtype = Integer.parseInt(request.getParameter("tbtype"));
		int tbid = Integer.parseInt(request.getParameter("tbid"));
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		String writer = loginUser.getId();
		
		TextBoard tb = new TextBoard();
		tb.setTbid(tbid);
		tb.setTbTitle(title);
		tb.setTbContent(content);
		tb.setTbWriter(writer);
		tb.setTbType(tbtype);
		System.out.println("dd" + writer);
		int result = new TextBoardService().updateTextBoard(tb);
		
		String page = null;
		if(result > 0) {
			page = "/detail.tb?tbid=" + tbid;
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시글 수정을 실패하였습니다.");
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
