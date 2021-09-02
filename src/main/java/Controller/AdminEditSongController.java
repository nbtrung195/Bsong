package Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.categories;
import model.bean.song;
import model.dao.catDAO;
import model.dao.songDAO;
import util.FileUtil;

/**
 * Servlet implementation class AdminAddSongController
 */
@WebServlet("/admin/song/edit")
@MultipartConfig
public class AdminEditSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private catDAO CatDAO;
    private songDAO SongDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditSongController() {
        super();
        CatDAO = new catDAO();
        SongDAO = new songDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
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
		ArrayList<categories> Categories = CatDAO.getCategories();
		request.setAttribute("Song", Song);
		request.setAttribute("Categories", Categories);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/edit.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = 0;
		int id2 = 0;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
			id2 = Integer.parseInt(request.getParameter("category_new"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/song?err=1");
			return;
		}
		int currentPage = Integer.parseInt(request.getParameter("page")) ;
		String name = request.getParameter("name");
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		String youtube = request.getParameter("youtube");
		Part filePart = request.getPart("picture");
		song Song = SongDAO.getItem(id);
		if(Song == null) {
			response.sendRedirect(request.getContextPath()+"/admin/song?err=1");
			return;
		}
		final String dirPartName = request.getServletContext().getRealPath("files");
		File dirFile = new File(dirPartName);
	    if(!dirFile.exists()) {
	    	dirFile.mkdir();
	    }
	    String fileName = FileUtil.getName(filePart);
	    String picture = "";
	    if(fileName.isEmpty()) {
	    	picture = Song.getPicture();
	    }
	    else {
	    	picture = FileUtil.rename(fileName);
	    }
	    String filePartName = dirPartName + File.separator + picture;
	    categories Category = new categories(id2, null);
	    song Item = new song(id, name, preview, detail, null, picture, 0, Category,youtube);
	    if(SongDAO.editItem(Item)>0) {
	    	if(!fileName.isEmpty()) {
	    		String oldFliePathName = dirPartName + File.separator + Song.getPicture();
	    		File oldFile = new File(oldFliePathName);
	    		if(oldFile.exists()) {
	    			oldFile.delete();
	    		}
	    		filePart.write(filePartName);
	    	}
	    	response.sendRedirect(request.getContextPath()+"/admin/song?msg=2&page="+currentPage);
			return;
	    }
	    else {
	    	ArrayList<categories> Categories = CatDAO.getCategories();
			request.setAttribute("Categories", Categories);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/song/edit.jsp?err=1");
			rd.forward(request, response);
			return;
	    }
	}

}
