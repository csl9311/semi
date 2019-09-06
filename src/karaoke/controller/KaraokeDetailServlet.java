package karaoke.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import karaoke.model.service.KaraokeService;
import karaoke.model.vo.Attachment;
import karaoke.model.vo.Karaoke;
import karaoke.model.vo.Review;

/**
 * Servlet implementation class KaraokeDetailServlet
 */
@WebServlet("/detail.ko")
public class KaraokeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public KaraokeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int kid = Integer.parseInt(request.getParameter("kid"));

		KaraokeService service = new KaraokeService();
		Karaoke karaoke = service.selectKaraoke(kid);
		ArrayList<Attachment> fileList = service.selectAttachment(kid);
		ArrayList<Review> list = service.selectReviewList(kid);
		double avg = service.selectRatingAvg(kid);
		double avgr = Math.round(avg*100.0)/100.0;
		System.out.println("노래방 상세 조회 성공 ㅇㅇ");
		if(fileList != null) {
			request.setAttribute("karaoke", karaoke);
			request.setAttribute("fileList", fileList);
			request.setAttribute("list", list);
			request.setAttribute("avg", avgr);
			request.getRequestDispatcher("views/search/searchDetail.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "열람 실패 ㅠㅠ");
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
