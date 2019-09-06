package shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import shop.model.service.ShopService;

/**
 * Servlet implementation class ReviewCountServlet
 */
@WebServlet("/reviewCount.do")
public class ReviewCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewCountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("서블렛 들어옴");
		
		int rId = Integer.parseInt(request.getParameter("rId"));
		int result = new ShopService().updateCount(rId);
		int count = new ShopService().selectReviewCount(rId);
		
		JsonObject jObj = new JsonObject();
		jObj.addProperty("result", result);
		jObj.addProperty("count", count);
		
		Gson gson = new Gson();
		response.setContentType("application/json; charset=utf-8");
		gson.toJson(jObj,response.getWriter());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
