package shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import shop.model.service.ShopService;
import shop.model.vo.Cart;

/**
 * Servlet implementation class SelectCartServlet
 */
@WebServlet("/selectcart.ca")
public class SelectCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		HttpSession session = request.getSession(); //세션호출
		Member sessionMember = (Member)session.getAttribute("loginUser");
		String userId= sessionMember.getId();
		ArrayList<Cart> info = new ShopService().selectCart(userId);
		
		String page= null;
		
		if(info!=null) {
			page= "views/MyPage/cart.jsp";
			request.setAttribute("info",info);					
		}else {
			page = "views/MyPage/emptycart.jsp";
			request.setAttribute("msg", "장바구니 조회에 실패했습니다.");
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
