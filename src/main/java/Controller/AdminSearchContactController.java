package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.contact;
import model.dao.contactDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminSearchCatController
 */
@WebServlet("/admin/contact/search")
public class AdminSearchContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	contactDAO ContatctDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchContactController() {
        super();
        ContatctDAO = new contactDAO();
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
		int NumberOfItems = ContatctDAO.searchNumberOfItems(name); 
		ArrayList<contact> contacts = ContatctDAO.searchItems(name);
		request.setAttribute("contacts", contacts);
		request.setAttribute("NumberOfItems", NumberOfItems);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/contact/search.jsp");
		rd.forward(request, response);
	}

}
