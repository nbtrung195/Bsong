package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.song;
import model.dao.songDAO;
import util.DefineUtil;

/**
 * Servlet implementation class PublicIndexController
 */
@WebServlet("/index")
public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    songDAO SongDAO;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexController() {
        super();
        SongDAO = new songDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int NumberOfItems = SongDAO.NumberOfItems(); 
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
		ArrayList<song> Song = SongDAO.getItemsPagination(offset);
		request.setAttribute("NumberOfPages", NumberOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("Song", Song);
		request.setAttribute("offset", offset);
		request.setAttribute("NumberOfItems", NumberOfItems);
		RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
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
