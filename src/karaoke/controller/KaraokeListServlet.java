package karaoke.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageInfo;
import karaoke.model.service.KaraokeService;
import karaoke.model.vo.Attachment;
import karaoke.model.vo.Karaoke;
import karaoke.model.vo.Review;


/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/list.ko")
public class KaraokeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KaraokeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8"); 
		 KaraokeService service = new KaraokeService();
			
			int listCount = service.getListCount(); 
			
			
			int currentPage;	
			int limit;			
			int maxPage;		
			int startPage;		
			int endPage;		
			
			currentPage = 1;
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			limit = 10;
			
			maxPage = (int)((double)listCount/limit +0.9); //페이지수 측정
	        startPage = (((int)((double)currentPage/limit+0.9))-1) *limit+1;
	        endPage = startPage + limit -1;
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			System.out.println("23" + pi);
			ArrayList<Karaoke> list = service.selectList(currentPage);
			ArrayList<Attachment> at = service.selectAlist(currentPage);
			ArrayList<Review> review = service.selectRList();
			
			String page = null;
			if(list != null) {
				page = "views/search/searchView.jsp";
				request.setAttribute("list", list);
				request.setAttribute("at", at);
				request.setAttribute("pi", pi);
				request.setAttribute("review", review);
			} else {
				page = "views/common/errorPage.jsp";
				request.setAttribute("msg", "게시판 조회에 실패하였습니다.");
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
