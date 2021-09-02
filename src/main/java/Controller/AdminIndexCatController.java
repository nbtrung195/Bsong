package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.categories;
import model.dao.catDAO;
import util.AuthUtil;
import util.DefineUtil;

/**
 * Servlet implementation class AdminIndexCatController
 */
@WebServlet("/AdminIndexCatController")
public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    catDAO CatDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexCatController() {
        super();
        CatDAO = new catDAO();
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
		response.setContentType("text/html;charset=UTF-8\"");
		response.setCharacterEncoding("UTF-8");
		int NumberOfItems = CatDAO.NumberOfItems(); 
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
		ArrayList<categories> cat = CatDAO.getItemsPagination(offset);
		request.setAttribute("NumberOfPages", NumberOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("cat", cat);
		request.setAttribute("offset", offset);
		request.setAttribute("NumberOfItems", NumberOfItems);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/index.jsp");
		rd.forward(request, response);
	}

}
