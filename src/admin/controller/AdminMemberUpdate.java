package admin.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/admin.MemberUpdate")
public class AdminMemberUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminMemberUpdate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String nickName = request.getParameter("nickName");
		String[] phoneArr = request.getParameterValues("phone");
		String phone = "";
		if (phoneArr != null) {
			phone = String.join("-", phoneArr);
		}
		String gender = request.getParameter("gender");
		String[] birthArr = request.getParameter("birth").split("-");
			Date birth = null;
			int year = Integer.parseInt(birthArr[0]);
			int month = Integer.parseInt(birthArr[1])-1;
			int day = Integer.parseInt(birthArr[2]);
			birth = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		
		String email = request.getParameter("email");
		int grade_code = Integer.parseInt(request.getParameter("grade"));
		int news = Integer.parseInt(request.getParameter("news"));
		int sms = Integer.parseInt(request.getParameter("sms"));

		Member member = new Member(id, name, nickName, phone, gender, email, birth, news, sms, grade_code);
		int result = new MemberService().adminUpdateMember(member);

		String msg = "";
		if (result > 0) {
			msg = "수정 되었습니다.";
		} else {
			msg = "정보 수정에 실패했습니다.";
		}

		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/selectMember?id=" + id).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
