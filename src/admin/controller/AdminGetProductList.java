package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import product.model.vo.Product;

@WebServlet("/admin.allProductList")
public class AdminGetProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AdminGetProductList() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminService service = new AdminService();
		ArrayList<Product> list = service.getAllProduct();
		
		String page = "";
		if(list != null) {
			request.setAttribute("list", list);
			page = "views/admin/adminProduct/admin_productView.jsp";
		} else {
			request.setAttribute("msg", "상품목록 조회에 실패했습니다.");
			page = "views/common/errorPage.jsp";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
