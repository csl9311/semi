package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

@WebServlet("/member.nickCheck")
public class CheckNick extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckNick() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nickName = request.getParameter("nickName");
		int result = new MemberService().checkNick(nickName);
		PrintWriter out = response.getWriter();
		if (result  > 0) {
			out.append("fail");
		} else {
			out.append("success");
		}
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
