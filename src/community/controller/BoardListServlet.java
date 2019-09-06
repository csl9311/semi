package community.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageInfo;
import community.model.service.BoardService;
import community.model.vo.Board;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/list.bo")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String search = request.getParameter("search");
		Board b = new Board();
		b.setbContent(search);
		String page = null;

		System.out.println("검색이요" + search);

		BoardService service = new BoardService();
		int listCount = 0; // 총 개시글 갯수

		if (search == null || search == "") {
			listCount = service.getListCount();
		} else {
			listCount = service.getListCount2(b);
			System.out.println(listCount);
		}
		System.out.println("게시글갯수" + listCount);

		if (listCount == 0 && b.getbContent() != null) {
			request.setAttribute("error", '1');
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "검색 결과가 없습니다.");

		}
		/****************** 페이지처리 ***********************/

		int currentPage; // 현재 페이지
		int limit; // 한 페이지에 표시될 페이징 수
		int maxPage; // 전체 페이지 중에서 가장 마지막 페이지
		int startPage; // 페이징이 된 페이지중 시작 페이지
		int endPage; // 페이징이 된 페이지중 마지막 페이지
		/*
		 * currentPage = listCount / 9;
		 * 
		 * if (listCount % 9 > 0) { currentPage++; }
		 */
		currentPage = 1;

		if (request.getParameter("currentPage") != null) {
			currentPage =

					Integer.parseInt(request.getParameter("currentPage"));
		}

		System.out.println("현재2:" + currentPage);
		limit = 9;

		maxPage = 0;

		maxPage = (int) ((double) listCount / limit + 0.9); // 페이지수 측정
		startPage = (((int) ((double) currentPage / limit + 0.9)) - 1) * limit + 1;
		System.out.println("스페" + startPage);
		System.out.println("막페" + maxPage);
		endPage = startPage + limit - 1;

		if (maxPage < endPage) {
			endPage = maxPage;
		}

		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);

		ArrayList<Board> list = new BoardService().selectList(currentPage, listCount, b);

		if (listCount > 0) {

			ArrayList<Board> reword = new BoardService().selectRewordUser();
			ArrayList<Board> rewordBoard = new BoardService().selectRewordBoard();
			System.out.println("추천" + reword.get(0));
			request.setAttribute("rewordUser", reword);
			request.setAttribute("rewordBoard", rewordBoard);
		}

		if (listCount >= 0) {
			if (list != null) {
				page = "views/community/aviBoardListView.jsp";
				request.setAttribute("search", b);
				request.setAttribute("list", list);
				request.setAttribute("pi", pi);
			} else {
				page = "views/common/errorPage.jsp";
				request.setAttribute("msg", "게시판 조회에 실패하였습니다");
			}

		}
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
