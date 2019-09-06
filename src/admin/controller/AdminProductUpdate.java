package admin.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import admin.model.service.AdminService;
import product.model.vo.Product;

@WebServlet("/admin.productUpdate")
public class AdminProductUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminProductUpdate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminService service = new AdminService();
		int pId = Integer.parseInt(request.getParameter("pId"));
		String pName = request.getParameter("pName");
		int brandNo = Integer.parseInt(request.getParameter("brandNo"));
		int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
		int subCategoryNo = Integer.parseInt(request.getParameter("subCate"));
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		String[] regDateArr = request.getParameter("regDate").split("-");
		Date regDate = null;
			int year = Integer.parseInt(regDateArr[0]);
			int month = Integer.parseInt(regDateArr[1]) - 1;
			int day = Integer.parseInt(regDateArr[2]);
		regDate = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		String useOption = request.getParameter("option");
		
		Product p = new Product(pId, price, stock, brandNo, categoryNo, subCategoryNo, pName, useOption, regDate);

		int result1 = service.updateProduct(p);
		if (result1 > 0) {
			System.out.println("상품등록성공");
		} else {
			System.out.println("상품등록실패");
		}

		String option1 = request.getParameter("option1");
		String option2 = request.getParameter("option2");
		String option3 = request.getParameter("option3");
		String optionResult = "";
		int result2 = 0;
		if (useOption.equals("Y")) {
			if (option1.equals("")) {
				optionResult = "";
			} else if (option2.equals("")) {
				optionResult = option1;
			} else if (option3.equals("")) {
				optionResult = option1 + "/" + option2;
			} else {
				optionResult = option1 + "/" + option2 + "/" + option3;
			}
			result2 = service.updateOption(optionResult, pId);
			if (result2 > 0) {
				System.out.println("옵션 등록 완료");
			} else {
				System.out.println("옵션 등록 실패");
			}
		} else {
			useOption = "N";
			optionResult = "";
			result2 = service.updateOption(optionResult, pId);
		}
		Product product = new AdminService().selectProduct(pId);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(product, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
