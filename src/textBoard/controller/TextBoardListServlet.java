package textBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageInfo;
import textBoard.model.service.TextBoardService;
import textBoard.model.vo.TextBoard;

/**
 * Servlet implementation class TextBoardListServlet
 */
@WebServlet("/list.tb")
public class TextBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TextBoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TextBoardService service = new TextBoardService();
		
		int listCount = service.getListCount();
		System.out.println(listCount);
		int currentPage;
		int limit;
		int maxPage;
		int startPage;	
		int endPage;		
		
		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		limit = 20;
		
		maxPage = (int)((double)listCount/limit +0.96);
        startPage = (((int)((double)currentPage/limit+0.95))-1) *limit+1;
        endPage = startPage + limit -1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		System.out.println("ㅇㅇ" + maxPage + startPage + endPage + currentPage);
		
		ArrayList<TextBoard> list = service.selectList(currentPage);
		
		
		int ntype=2;
		ArrayList<TextBoard> nlist = service.selectNlist(ntype);
		
		
		String page = null;
		if(list != null) {
			page = "views/textBoard/textBoardListView.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			request.setAttribute("nlist", nlist);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "여긴..어디...?");
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
