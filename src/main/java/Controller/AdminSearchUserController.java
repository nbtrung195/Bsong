package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.users;
import model.dao.usersDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminSearchCatController
 */
@WebServlet("/admin/user/search")
public class AdminSearchUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	usersDAO UserDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchUserController() {
        super();
        UserDAO = new usersDAO();
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
		int NumberOfItems = UserDAO.searchNumberOfItems(name); 
		ArrayList<users> users = UserDAO.searchItems(name);
		request.setAttribute("users", users);
		request.setAttribute("NumberOfItems", NumberOfItems);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/search.jsp");
		rd.forward(request, response);
	}

}
