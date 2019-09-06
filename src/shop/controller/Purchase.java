package shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import payment.model.service.PaymentService;
import payment.model.vo.Payment;
import shop.model.service.ShopService;
import shop.model.vo.Cart;

/**
 * Servlet implementation class Purchase
 */
@WebServlet("/purchase.ca")
public class Purchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Purchase() {
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
		String[] ship = request.getParameter("ship").split(",");
		String[] arr= request.getParameter("list").split(",");
		String recipient = ship[0];
		String recipienPhone = ship[1];
		String address = ship[2];
		String req = ship[3];
		
		Payment shipinfo = new Payment();
		shipinfo.setRecipient(recipient);
		shipinfo.setPhone(recipienPhone);
		shipinfo.setAddress(address);
		shipinfo.setReq(req);
		int result =0;
		
		ArrayList<Cart> pay = new ArrayList<Cart>();
		for(int i=0; i<arr.length;i++) {
		System.out.println("list="+arr[i]);
		int vrr = Integer.parseInt(arr[i]);
		Cart p = new ShopService().selectPurchase(userId,vrr);
		
		pay.add(p);
		}
		for(int i=0; i<pay.size(); i++) {
			Cart ca= pay.get(i);
			result = new PaymentService().insertPurchase(userId, ca, shipinfo);
		}
		
		
		if(result>0) {
			System.out.println("dddd"+result);			
			
		}
		
			
//		for(int i=0; i<pay.size();i++) {
//			Payment p= pay.get(i);
//			System.out.println(p.getpId());
//			
//		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
