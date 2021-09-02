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
import util.DefineUtil;

/**
 * Servlet implementation class AdminIndexContactController
 */
@WebServlet("/AdminIndexContactController")
public class AdminIndexContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    contactDAO ContactDAO; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexContactController() {
        super();
        ContactDAO = new contactDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		int NumberOfItems = ContactDAO.NumberOfItems(); 
		int NumberOfPages = (int)Math.ceil((float)NumberOfItems / DefineUtil.NUMBER_PER_PAGE);
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		if(currentPage>NumberOfPages || currentPage <1) {
			currentPage = 1;
		}
		int offset = (currentPage - 1)*DefineUtil.NUMBER_PER_PAGE;
		ArrayList<contact> contacts = ContactDAO.getItemsPagination(offset);
		request.setAttribute("NumberOfPages", NumberOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("contacts", contacts);
		request.setAttribute("offset", offset);
		request.setAttribute("NumberOfItems", NumberOfItems);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/contact/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
