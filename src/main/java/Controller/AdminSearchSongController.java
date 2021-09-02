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
import util.AuthUtil;

/**
 * Servlet implementation class AdminSearchCatController
 */
@WebServlet("/admin/song/search")
public class AdminSearchSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	songDAO SongDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchSongController() {
        super();
        SongDAO = new songDAO();
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
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("Search");
		int NumberOfItems = SongDAO.searchNumberOfItems(name); 
		ArrayList<song> songs = SongDAO.searchItems(name);
		request.setAttribute("songs", songs);
		request.setAttribute("NumberOfItems", NumberOfItems);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/search.jsp");
		rd.forward(request, response);
	}

}
