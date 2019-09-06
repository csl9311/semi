package shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.vo.Product;
import shop.model.service.ShopService;
import shop.model.vo.Answer;
import shop.model.vo.PAttachment;
import shop.model.vo.RAttachment;
import shop.model.vo.Review;

@WebServlet("/shopDetail.do")
public class ShopDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShopDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		int pId = Integer.parseInt(request.getParameter("pId"));
		
		ShopService service = new ShopService();
		Product p = service.selectProduct(pId);
		PAttachment thumbP = service.selectPAttachment(pId);
		
		ArrayList<Review> rList = service.selectReviewList(pId, 1);
		ArrayList<Answer> aList = service.selectAnswerList(pId, 1);
		ArrayList<RAttachment> attList = service.selectRAttachmentList(pId);
		ArrayList<PAttachment> pAttList = service.selectPAttachmentList(pId);
		
		System.out.println("rList: " + rList);
		System.out.println("aList: " + aList);
		System.out.println("attList: " + attList);
		System.out.println("pAttList: " + pAttList);
		
		String option = "";
		
		String page = null;
		if(p != null) {
			page = "views/shop/shopDetailView.jsp";
			request.setAttribute("p", p);
			if(p.getUseOption() != null &&  p.getUseOption().equals("Y")) {
				option = service.selectOption(pId);
				System.out.println(option);
			}
			request.setAttribute("option", option);
			request.setAttribute("rList", rList);
			request.setAttribute("aList", aList);
			request.setAttribute("thumbP", thumbP);
			request.setAttribute("attList", attList);
			request.setAttribute("pAttList", pAttList);
			
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "상품 상세페이지 조회에 실패했습니다.");
		}
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
