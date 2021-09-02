package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.users;
import model.dao.usersDAO;

/**
 * Servlet implementation class AdminDelUserController
 */
@WebServlet("/admin/user/del")
public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    usersDAO UserDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelUserController() {
        super();
        UserDAO = new usersDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/user?err=1");
			return;
		}
		HttpSession session = request.getSession();
		users Userr = (users)session.getAttribute("userLogin");
		users User = UserDAO.getItem(id);
		if("admin".equals(Userr.getUsername())) {
			if("admin".equals(User.getUsername())) {
				response.sendRedirect(request.getContextPath()+"/admin/user?err=5");
				return;
			}
			else {
				if(UserDAO.delItem(id)>0) {
					response.sendRedirect(request.getContextPath()+"/admin/user?msg=3");
					return;
				}
				else {
					RequestDispatcher rd = request.getRequestDispatcher("/admin/user?err=2");
					rd.forward(request, response);
					return;
				}
			}
		}
		else {
			response.sendRedirect(request.getContextPath()+"/admin/user?err=5");
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
