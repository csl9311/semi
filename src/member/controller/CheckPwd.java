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

@WebServlet(name="CheckPwd", urlPatterns="/checkpwd.me")
public class CheckPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckPwd() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Member loginUser = (Member) session.getAttribute("loginUser");

		String id = loginUser.getId();
		String pw = (String) request.getParameter("pwd");
		
		boolean pwCheck = new MemberService().checkPw(id, pw);
		
		String page = "";
		if(pwCheck) {
			response.sendRedirect("views/MyPage/upDate.jsp"); // 어디로 갈거야?
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "비밀번호가 같지 않습니다.");
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
