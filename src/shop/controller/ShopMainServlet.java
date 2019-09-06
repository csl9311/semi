package shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageInfo;
import product.model.vo.Product;
import shop.model.service.ShopService;
import shop.model.vo.PAttachment;

@WebServlet("/shopMain.do")
public class ShopMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShopMainServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println(123);
		ShopService service = new ShopService();
		System.out.println(456);
		

		String key = request.getParameter("key");
		String sortBy = request.getParameter("sort");
		System.out.println("sortBy: " + sortBy);
		System.out.println("key : " + key);

		int listCount = service.getAllListCount();
		if (key != null && !key.equals("null")) {
			System.out.println("들어옴1");
			if (sortBy.equals("stock")) {
				System.out.println("들어옴2");
				listCount = service.getAllKeyNStockListCount(key);
			} else {
				System.out.println("들어옴3");
				listCount = service.getAllKeyListCount(key);
			}
		} else if (sortBy != null && !sortBy.equals("null") && sortBy.equals("stock")) {
			System.out.println("들어옴4");
			listCount = service.getAllStockListCount();
		}

		System.out.println(listCount);
		int currentPage;
		int limit;
		int maxPage;
		int startPage;
		int endPage;

		currentPage = 1;
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		limit = 10;

		maxPage = (int) ((double) listCount / limit + 0.9);
		startPage = (((int) ((double) currentPage / limit + 0.9)) - 1) * limit + 1;
		endPage = startPage + limit - 1;
		if (maxPage < endPage) {
			endPage = maxPage;
		}
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);

		String rank = "sellCount";
		ArrayList<Product> rankList = service.selectSortMainList(1, rank);
		ArrayList<Product> newList = service.selectAllList(1);

		ArrayList<Product> list = null;
		if (sortBy != null && !sortBy.equals("regdate") && !sortBy.equals("null") && key != null
				&& !key.equals("null")) {
			list = service.selectKeySortMainList(currentPage, sortBy, key);
		} else if (sortBy != null && !sortBy.equals("regdate") && !sortBy.equals("null")) {
			list = service.selectSortMainList(currentPage, sortBy);
		} else if (key != null && !key.equals("null")) {
			list = service.selectAllkeyList(currentPage, key);
		} else {
			list = service.selectAllList(currentPage);
		}
		
		ArrayList<PAttachment> pAttList = service.selectAllPAttachmentList();

		String page = null;
		if (list != null) {
			page = "views/shop/shopMainView.jsp";
			request.setAttribute("list", list);
			request.setAttribute("rankList", rankList);
			request.setAttribute("newList", newList);
			request.setAttribute("pAttList", pAttList);
			request.setAttribute("pi", pi);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시판 조회에 실패하였습니다.");
		}
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
