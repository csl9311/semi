package textBoard.controller;

import java.io.IOException;

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
 * Servlet implementation class textBoardInsertServlet
 */
@WebServlet("/insert.tb")
public class TextBoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TextBoardInsertServlet() {
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
		
		System.out.println("!!"+tbtype);
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		String writer = loginUser.getId();
		
		TextBoard tb = new TextBoard();
		
		tb.setTbTitle(title);
		tb.setTbContent(content);
		tb.setTbWriter(writer);
		tb.setTbType(tbtype);
		
		
		int result = new TextBoardService().insertTextBoard(tb);
		
		if(result > 0) {
			response.sendRedirect("list.tb?currentPage=1");
		} else {
			request.setAttribute("msg", "게시글 작성 실패 ");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
