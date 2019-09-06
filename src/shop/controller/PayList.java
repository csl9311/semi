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

import member.model.service.MemberService;
import member.model.vo.Address;
import member.model.vo.Member;
import shop.model.service.ShopService;
import shop.model.vo.Cart;

/**
 * Servlet implementation class PayList
 */
@WebServlet("/paylist.ca")
public class PayList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Member sessionMember = (Member)session.getAttribute("loginUser");
		String userId= sessionMember.getId();
		System.out.println("userid"+ userId);
		
		Address adr= new MemberService().selectAdr(userId);
		ArrayList<Cart> pay = new ShopService().selectpay(userId);
//		System.out.println("adr="+adr.getJibunAddress());
		String page= null;
		
		if(pay!=null) {
			page= "views/MyPage/payment.jsp";
			request.setAttribute("pay", pay);
			request.setAttribute("adr", adr);
		}else {
			page = "views/common/errorPage.jsp";
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
