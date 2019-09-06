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
import shop.model.vo.Review;

@WebServlet("/updateReview.do")
public class UpdateReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateReviewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		String userId = loginUser.getId();
		Review r = null;
		
		int rId = Integer.parseInt(request.getParameter("rId"));
		String rContent = request.getParameter("rContent");
		Date date = new Date(new GregorianCalendar().getTimeInMillis());
		r = new Review(rId, rContent, date);
		
		ShopService service = new ShopService();
		
		String str = "Review";
		
		int result = service.selectWriter(userId, rId, str); // 글 작성자와 세션의 유저를 비교하고 
		if(result > 0) {
			System.out.println("글수정?");
			result = service.updateReview(r); // 글을 수정 후
			if (result > 0) {
				r = service.selectReview(userId, rId); // ajax로 뿌릴 데이터를 바로 받아옴
			}
		}
		
		JsonObject jObj = new JsonObject();
		jObj.addProperty("result", result);
		jObj.addProperty("rContent", r.getrContent());
		jObj.addProperty("modify_date", r.getModifyDate().toString());
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		response.setContentType("application/json; charset=utf-8");
		gson.toJson(jObj,response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
