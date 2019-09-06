package karaoke.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import karaoke.model.service.KaraokeService;
import karaoke.model.vo.Review;

/**
 * Servlet implementation class KaraokeReplyServlet
 */
@WebServlet("/reply.ko")
public class KaraokeReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KaraokeReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String krwriter = request.getParameter("krwriter");
		int ref_kid = Integer.parseInt(request.getParameter("ref_kid"));
		int krating = Integer.parseInt(request.getParameter("krating"));
		String krcontent = request.getParameter("replytext");
		KaraokeService service = new KaraokeService();
		int result = service.selectReview(krwriter);
		int iresult = 0;
		System.out.println(result);
		Review r = new Review(krwriter, ref_kid, krating, krcontent);
		
		if(result > 0) {
			iresult = service.updateReview(r);
		} else {
			iresult = service.insertReview(r);
		}
		
		String page = null;
		if(iresult > 0) {
			page = "detail.ko?kid=" + ref_kid;
		} else {
			request.setAttribute("msg", "리뷰 입력을 실패했습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
