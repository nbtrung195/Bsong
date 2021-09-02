package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.contact;
import model.dao.contactDAO;

/**
 * Servlet implementation class PublicContactController
 */
@WebServlet("/contact")
public class PublicContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicContactController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/public/contact.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");
		contact Contact= new contact(0, name, email, website, message);
		contactDAO ContactDAO = new contactDAO();
		if(ContactDAO.getItems(Contact)>0) {
			response.sendRedirect(request.getContextPath()+"/contact?msg=1");
			return;
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/public/contact.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		
		}

}
