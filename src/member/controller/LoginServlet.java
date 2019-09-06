package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet(name="LoginServlet", urlPatterns="/member.login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		System.out.println(userPwd);
		Member member = new Member(userId, userPwd);
		Member login = new MemberService().loginMember(member);
		Member loginUser = null;
		// 각 페이지에서 페이지 정보 받아온 후
		String page ="";
		
		if (login != null) {
			loginUser = new MemberService().selectMember(login.getId());
			session.setAttribute("loginUser", loginUser);
			response.sendRedirect(request.getHeader("referer"));
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "아이디와 비밀번호를 확인해주세요.");
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
