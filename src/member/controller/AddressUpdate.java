package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Address;

@WebServlet("/address.update")
public class AddressUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddressUpdate() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int address_code = Integer.parseInt(request.getParameter("address_code"));
		String postNum = request.getParameter("postNum");
		String roadAddress = request.getParameter("roadAddress");
		String jibunAddress = request.getParameter("jibunAddress");
		String address_detail = request.getParameter("address_detail");
		
		Address address = new Address(address_code, postNum, roadAddress, jibunAddress, address_detail);
		int result = new MemberService().addressUpdate(address);
		
		String page = request.getParameter("page");
		if(result > 0) {
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "주소 수정에 실패했습니다.");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
