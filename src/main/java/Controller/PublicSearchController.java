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

/**
 * Servlet implementation class PublicIndexController
 */
@WebServlet("/search")
public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    songDAO SongDAO;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicSearchController() {
        super();
        SongDAO = new songDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("Search_song");
		int NumberOfItems = SongDAO.searchNumberOfItems(name); 
		ArrayList<song> Song = SongDAO.searchItems(name);
		request.setAttribute("Song", Song);
		request.setAttribute("NumberOfItems", NumberOfItems);
		RequestDispatcher rd = request.getRequestDispatcher("/public/search.jsp");
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
