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
 * Servlet implementation class AdminEditUserController
 */
@WebServlet("/admin/user/edit")
public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    usersDAO UserDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditUserController() {
        super();
        UserDAO = new usersDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/user/edit.jsp?err=2");
			return;
		}
		HttpSession session = request.getSession();
		users Userr = (users)session.getAttribute("userLogin");
		if("admin".equals(UserDAO.getItem(Userr.getId()).getUsername()) || id == Userr.getId()) {
			users User = UserDAO.getItem(id);
			if(User != null) {
				request.setAttribute("User", User);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp");
				rd.forward(request, response);
			}
			else {
				response.sendRedirect(request.getContextPath()+"/admin/user?err=1");
				return;
			}
		}
		else {
			response.sendRedirect(request.getContextPath()+"/admin/user?err=4");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		int currentPage = Integer.parseInt(request.getParameter("page")) ;
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/user/edit.jsp?err=2");
			return;
		}
		users User = UserDAO.getItem(id);
		String password = request.getParameter("password");
		if("".equals(password)) {
			password = User.getPassword();
		}
		else {
			password = StringUtil.md5(password);
		}
		String fullname = request.getParameter("fullname");
		User.setFullname(fullname);
		User.setPassword(password);
		if(UserDAO.editItem(User)>0) {
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=2&page="+currentPage);
			return;
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
