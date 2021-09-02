package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.song;
import model.dao.songDAO;

/**
 * Servlet implementation class PublicDetailController
 */
@WebServlet("/detail")
public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    songDAO SongDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicDetailController() {
        super();
        SongDAO = new songDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/404NotFound");
			return;
		}
		HttpSession session = request.getSession();
		String hasVisitted = (String)session.getAttribute("HasSession" + id);

		if(hasVisitted == null) {
			SongDAO.increaseView(id);
			session.setAttribute("HasSession" + id, "yes");
			session.setMaxInactiveInterval(3600);
		}
		song Song = SongDAO.getItem(id);
		if(Song == null) {
			response.sendRedirect(request.getContextPath()+"/404NotFound");
			return;
		}
		ArrayList<song> relatedSongs = SongDAO.getRelatedItems(Song,2);
		request.setAttribute("relatedSongs", relatedSongs);
		request.setAttribute("Song", Song);
		
		RequestDispatcher rd = request.getRequestDispatcher("/public/detail.jsp");
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
