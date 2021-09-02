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
import model.bean.song;
import model.dao.catDAO;
import model.dao.songDAO;
import util.DefineUtil;

/**
 * Servlet implementation class PublicCatController
 */
@WebServlet("/cat")
public class PublicCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    catDAO CatDAO;
    songDAO SongDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicCatController() {
        super();
        CatDAO = new catDAO();
        SongDAO = new songDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			currentPage = 1;
		}
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/404NotFound");
			return;
		}
		categories Categories = CatDAO.getItem(id);
		if (Categories == null) {
			response.sendRedirect(request.getContextPath()+"/404NotFound");
			return;
		}
		int NumberOfItems = SongDAO.NumberOfItems(id);
		int NumberOfPages = (int)Math.ceil((float)NumberOfItems / DefineUtil.NUMBER_PER_PAGE);
		if(currentPage>NumberOfPages || currentPage<1) {
			currentPage = 1;
		}
		int offset = (currentPage - 1)*DefineUtil.NUMBER_PER_PAGE;
		
		ArrayList<song> Songs = SongDAO.getItemsByCategoriesPagination(offset, id);
		request.setAttribute("NumberOfPages", NumberOfPages);
		request.setAttribute("NumberOfItems", NumberOfItems);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("Songs", Songs);
		request.setAttribute("Categories", Categories);
		RequestDispatcher rd = request.getRequestDispatcher("/public/cat.jsp");
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
