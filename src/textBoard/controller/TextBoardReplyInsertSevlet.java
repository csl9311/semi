package textBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import textBoard.model.service.TextBoardService;
import textBoard.model.vo.TextBoardReply;

/**
 * Servlet implementation class TextBoardReplyInsertSevlet
 */
@WebServlet("/insertrp.tb")
public class TextBoardReplyInsertSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TextBoardReplyInsertSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writer = request.getParameter("rwriter");
		String content = request.getParameter("content");
		int tbid = Integer.parseInt(request.getParameter("tbid"));
		
		TextBoardReply tr = new TextBoardReply();
		tr.setTrwriter(writer);
		tr.setTrcontent(content);
		tr.setTbid(tbid);
		
		ArrayList<TextBoardReply> list = new TextBoardService().insertReply(tr);
		response.setContentType("application/json;");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
