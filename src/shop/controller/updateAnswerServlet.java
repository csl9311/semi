package shop.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import member.model.vo.Member;
import shop.model.service.ShopService;
import shop.model.vo.Answer;

@WebServlet("/updateAnswer.do")
public class updateAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public updateAnswerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		String userId = loginUser.getId();
		Answer a = null;
		
		int a_rId = Integer.parseInt(request.getParameter("a_rId"));
		String aContent = request.getParameter("aContent");
		int aId = Integer.parseInt(request.getParameter("aId"));
		Date date = new Date(new GregorianCalendar().getTimeInMillis());
		
		ShopService service = new ShopService();
		
		String str = "Answer";
		
		int result = service.selectWriter(userId, a_rId, str); // 글 작성자와 세션의 유저를 비교하고 
		if(result > 0) {
			result = service.updateAnswer(a_rId, aContent, date); // 글을 수정 후
			if (result > 0) {
				a = service.selectAnswer(userId, aId); // ajax로 뿌릴 데이터를 바로 받아옴
			}
		}
		
		JsonObject jObj = new JsonObject();
		jObj.addProperty("result", result);
		jObj.addProperty("aContent", a.getaContent());
		jObj.addProperty("modify_date", a.getModifyDate().toString());
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		response.setContentType("application/json; charset=utf-8");
		gson.toJson(jObj,response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
