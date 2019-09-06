package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Address;
import member.model.vo.Member;

@WebServlet(name="SignUpServlet", urlPatterns="/member.signUp")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String nickName = request.getParameter("nickName");
		String name = request.getParameter("name");
		// 핸드폰 번호
		String[] phoneArr = request.getParameterValues("phone");
		String phone = String.join("-", phoneArr);
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		// 생년월일
		String[] birthArr = request.getParameterValues("birth");
		Date birth = null;
		int year = Integer.parseInt(birthArr[0]);
		int month = Integer.parseInt(birthArr[1]) - 1;
		int day = Integer.parseInt(birthArr[2]);
		birth = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());

		int news = Integer.parseInt(request.getParameter("news"));
		int sms = Integer.parseInt(request.getParameter("sms"));

		String postNum = request.getParameter("postNum");
		String roadAddress = request.getParameter("roadAddress");
		String jibunAddress = request.getParameter("jibunAddress");
		String address_detail = request.getParameter("address_detail");

		MemberService ms = new MemberService();

		Member member = new Member(id, name, nickName, phone, gender, email, birth, news, sms, pw);
		int mResult = ms.insertMember(member);

		if (!postNum.equals("")) {
			Address address = new Address(postNum, roadAddress, jibunAddress, address_detail, id);
			int aResult = ms.addressInsert(address);
			if (aResult > 0) {
				System.out.println("주소 등록 성공");
			} else {
				System.out.println("주소 등록 실패");
			}
		}
		String page = "";
		if (mResult > 0) {
			request.setAttribute("msg", "회원가입에 성공했습니다.");
			page = "views/member/signUp.jsp";
		} else {
			request.setAttribute("msg", "회원가입에 실패했습니다.");
			page = "views/common/errorPage.jsp";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
