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
 * Servlet implementation class AuthLoginController
 */
@WebServlet("/login")
public class AuthLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private usersDAO UsersDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthLoginController() {
        super();
        UsersDAO = new usersDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/admin");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = StringUtil.md5(password);
		users User = UsersDAO.existUser(username, password);
		if(User != null) {
			session.setAttribute("userLogin", User);
			response.sendRedirect(request.getContextPath()+"/admin");
			return;
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
