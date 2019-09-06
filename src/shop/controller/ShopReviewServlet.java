package shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import shop.model.service.ShopService;
import shop.model.vo.Answer;
import shop.model.vo.RAttachment;
import shop.model.vo.Review;

@WebServlet("/shopReview.do")
public class ShopReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShopReviewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("활성화는 되는거니..");
		
		ShopService service = new ShopService();
		
		int pId = Integer.parseInt(request.getParameter("pId"));
		int type = Integer.parseInt(request.getParameter("type"));
		ArrayList<Review> rList = service.selectReviewList(pId, type);
		ArrayList<Answer> aList = service.selectAnswerList(pId, type);
		ArrayList<RAttachment> attList = service.selectRAttachmentList(pId);
		
		JsonArray reviewArr = new JsonArray();
		JsonObject reviewObj = null;
		
		for(Review review : rList) {
			reviewObj = new JsonObject();
			reviewObj.addProperty("rId", review.getrId());
			reviewObj.addProperty("rTitle", review.getrTitle());
			reviewObj.addProperty("rWriter", review.getrWriter());
			reviewObj.addProperty("rModifyDate", review.getModifyDate().toString());
			reviewObj.addProperty("rCount", review.getrCount());
			reviewObj.addProperty("rContent", review.getrContent());
			
			reviewArr.add(reviewObj);
		}
		
		JsonArray answerArr = new JsonArray();
		JsonObject answerObj = null;
		
		for(Answer answer : aList) {
			answerObj = new JsonObject();
			answerObj.addProperty("aId", answer.getaId());
			answerObj.addProperty("aRId", answer.getaRId());
			answerObj.addProperty("aTitle", answer.getaTitle());
			answerObj.addProperty("aWriter", answer.getaWriter());
			answerObj.addProperty("aModifyDate", answer.getModifyDate().toString());
			answerObj.addProperty("aCount", answer.getaCount());
			answerObj.addProperty("aContent", answer.getaContent());
			
			answerArr.add(answerObj);
		}
		
		JsonArray attArr = new JsonArray();
		JsonObject attObj = null;
		
		for(RAttachment att : attList) {
			attObj = new JsonObject();
			attObj.addProperty("rFId", att.getrFId());
			attObj.addProperty("changeName", att.getChangeName());
			attObj.addProperty("rId", att.getrId());
			
			attArr.add(attObj); 
		}
		
		JsonObject jsonObject = new JsonObject();
		
		jsonObject.add("rList", reviewArr);
		jsonObject.add("aList", answerArr);
		jsonObject.add("attList", attArr);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		response.setContentType("application/json; charset=utf-8");
		gson.toJson(jsonObject,response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
