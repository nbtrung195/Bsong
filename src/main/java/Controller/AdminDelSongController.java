package Controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import model.bean.song;
import model.dao.songDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminDelSongController
 */
@WebServlet("/admin/song/del")
public class AdminDelSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    songDAO SongDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelSongController() {
        super();
        SongDAO = new songDAO();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/song?err=1");
			return;
		}
		song Song = SongDAO.getItem(id);
		if(Song == null) {
			response.sendRedirect(request.getContextPath()+"/admin/song?err=1");
			return;
		}
		if(SongDAO.delItem(id)>0) {
			final String dirPathName = request.getServletContext().getRealPath("/files");
			String picture = Song.getPicture();
			if(!picture.isEmpty()) {
				String filePathName =dirPathName + File.separator + picture;
				File file = new File(filePathName);
				if(file.exists()) {
					file.delete();
				}
			}
			response.sendRedirect(request.getContextPath()+"/admin/song?msg=3");
			return;
		}
		else {
			response.sendRedirect(request.getContextPath()+"/admin/song?err=2");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
