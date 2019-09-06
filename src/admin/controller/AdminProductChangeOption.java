package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import admin.model.service.AdminService;
import product.model.vo.Product;

@WebServlet("/product.getSubCategory")
public class AdminProductChangeOption extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminProductChangeOption() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("서블릿 접근");
		int categoryNo = Integer.parseInt(request.getParameter("select"));
		ArrayList<Product> list = new AdminService().getSubCategory(categoryNo);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(list, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
