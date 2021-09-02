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
import util.AuthUtil;
import util.StringUtil;

/**
 * Servlet implementation class AdminAddUserController
 */
@WebServlet("/admin/user/add")
public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    usersDAO UserDAO;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddUserController() {
        super();
        UserDAO = new usersDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		users User = (users)session.getAttribute("userLogin");
		if(!"admin".equals(User.getUsername())) {
			response.sendRedirect(request.getContextPath()+"/admin/user?err=3");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		password = StringUtil.md5(password);
		if(UserDAO.hasUser(username)) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add?err=2");
			rd.forward(request, response);
			return;
		}
		users User = new users(0, username, password, fullname);
		if(UserDAO.addItem(User)>0) {
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=1");
			return;
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
