package community.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.BoardService;
import community.model.vo.Board;

/**
 * Servlet implementation class BoardGoodServlet
 */
@WebServlet("/avigood.bo")
public class BoardGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardGoodServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int bid = Integer.parseInt(request.getParameter("bid"));
		String writer = request.getParameter("writer"); 
		String bwriter = request.getParameter("bwriter"); //게글
		System.out.println(bid);
		System.out.println(writer);
		
		Board b = new Board();
		
		b.setBid(bid);
		b.setbWriter(writer);
		b.setUserWriter(bwriter);
		
		int result = new BoardService().insertGood(b);
		
		

	
		if(result>0) {
			System.out.println("삽입성공");
			
			
		} else {
			System.out.println("오긴오는데..");
			response.setContentType("application/json,charset = utf-8");
		
		}
		
		
		

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
