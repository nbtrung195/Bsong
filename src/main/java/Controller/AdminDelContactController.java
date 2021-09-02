package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.contactDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminDelContactController
 */
@WebServlet("/AdminDelContactController")
public class AdminDelContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    contactDAO ContactDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelContactController() {
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
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/contact?err=1");
			return;
		}
		if(ContactDAO.delItem(id)>0) {
			response.sendRedirect(request.getContextPath()+"/admin/contact?msg=1");
			return;
		}
		else {
			response.sendRedirect(request.getContextPath()+"/admin/contact?err=2");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
