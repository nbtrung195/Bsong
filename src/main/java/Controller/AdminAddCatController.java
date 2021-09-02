package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.catDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminAddCatController
 */
@WebServlet("/AdminAddCatController")
public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private catDAO CatDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddCatController() {
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
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name") ;
		int result = CatDAO.addCategory(name);
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cat?msg=1");
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp?err=1");
			rd.forward(request, response);
		}
	}

}
