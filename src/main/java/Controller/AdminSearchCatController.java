package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.categories;
import model.dao.catDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminSearchCatController
 */
@WebServlet("/admin/cat/search")
public class AdminSearchCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	catDAO CatDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchCatController() {
        super();
        CatDAO = new catDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("Search");
		int NumberOfItems = CatDAO.searchNumberOfItems(name); 
		ArrayList<categories> cat = CatDAO.searchItems(name);
		request.setAttribute("cat", cat);
		request.setAttribute("NumberOfItems", NumberOfItems);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/search.jsp");
		rd.forward(request, response);
	}

}
