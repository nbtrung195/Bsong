package Controller;

import java.io.IOException;

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
 * Servlet implementation class AdminAddCatController
 */
@WebServlet("/AdminEditCatController")
public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private catDAO CatDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditCatController() {
        super();
        // TODO Auto-generated constructor stub
        CatDAO = new catDAO();
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
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/cat?err=1");
			return;
		}
		categories category = CatDAO.getItem(id);
		if(category!=null) {
			request.setAttribute("category", category);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/edit.jsp");
			rd.forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath()+"/admin/cat?err=1");
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
			response.sendRedirect(request.getContentLength()+"/admin/cat?err=1");
			return;
		}
		String name = request.getParameter("name") ;
		categories item = new categories(id,name);
		
		int result = CatDAO.editItem(item);
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cat?msg=2&page="+currentPage);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/edit.jsp?err=1");
			rd.forward(request, response);
		}
	}

}
