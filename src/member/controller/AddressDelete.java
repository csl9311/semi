package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Address;

@WebServlet("/address.delete")
public class AddressDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddressDelete() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int address_code = Integer.parseInt(request.getParameter("address_code"));
		
		Address address = new Address(address_code);
		int result = new MemberService().addressDelete(address);
		
		String page = request.getParameter("page");
		if(result > 0) {
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "주소 삭제에 실패했습니다.");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
