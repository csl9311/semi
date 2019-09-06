package shop.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;
import member.model.service.MemberService;
import member.model.vo.Member;
import shop.model.vo.RAttachment;
import shop.model.vo.Review;

@WebServlet("/insertReview.do")

public class InsertReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertReview() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		String userId = loginUser.getId();
		
		MemberService service = new MemberService();
		
		int result = 0;
		System.out.println("서블렛 들어옴");
		
		String apId = request.getParameter("pId");
		String atype = request.getParameter("type");
		
		int pId = Integer.parseInt(apId);
		int type = Integer.parseInt(atype);
		
		System.out.println("type : " + type);
		String rContent = request.getParameter("rContent");
		String rTitle = request.getParameter("rTitle");
		
		Review r = new Review();
		r.setrType(type);
		r.setrTitle(rTitle);
		r.setrContent(rContent);
		r.setpId(pId);
		r.setrWriter(userId);
		System.out.println(userId);
				
		result = service.insertReview(r);
		System.out.println("result : " + result);
		
		JsonObject jObj = new JsonObject();
		jObj.addProperty("result", result);
		
		Gson gson = new Gson();
		response.setContentType("application/json; charset=utf-8");
		gson.toJson(jObj,response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
