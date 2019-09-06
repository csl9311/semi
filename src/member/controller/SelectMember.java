package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Address;
import member.model.vo.Member;

@WebServlet("/selectMember")
public class SelectMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectMember() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = (String)request.getParameter("id");
		Member member = new MemberService().selectMember(id);
		String page = "";
		if (member != null) {
			page ="views/admin/adminMember/adminMemberDetail.jsp";
			// 멤버 객체가 있다면 주소 받으러 다시 접근.
			ArrayList<Address> addressList = new MemberService().getAddress(id);
			// 주소 정보가 없어도 멤버는 request에 담겨야 함.
			request.setAttribute("member", member);
			// 주소정보가 있다면 request에 담아줌
			if(addressList != null) {
				request.setAttribute("addressList", addressList);
			}
		} else {
			page ="views/common/errorPage.jsp";
			request.setAttribute("msg", "회원정보조회에 실패했습니다.");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
