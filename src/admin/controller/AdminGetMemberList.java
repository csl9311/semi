package admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/admin.allMemberList")
public class AdminGetMemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminGetMemberList() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 전체 멤버 ArrayList 가져오기
		ArrayList<Member> list = new MemberService().selectAll();
		if (list != null) {
			// addressCount의 사이즈를 담을 HashMap 선언
			HashMap<String, Integer> addressCountMap = new HashMap<String, Integer>();
			// memberList의 크기만큼 반복
			for (int i = 0; i < list.size(); i++) {
				String id = list.get(i).getId();
				int addressCount = new MemberService().getAddressCount(id);
				addressCountMap.put(id, addressCount);
			}
			request.setAttribute("list", list);
			request.setAttribute("addressCountMap", addressCountMap);
		} else {
			request.setAttribute("msg", "회원정보조회에 실패했습니다.");
		}
		request.getRequestDispatcher("views/admin/adminMember/adminMemberView.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
