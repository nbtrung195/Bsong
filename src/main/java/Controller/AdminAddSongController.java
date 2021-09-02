package Controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

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
import util.AuthUtil;
import util.FileUtil;

/**
 * Servlet implementation class AdminAddSongController
 */
@WebServlet("/admin/song/add")
@MultipartConfig
public class AdminAddSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private catDAO CatDAO;
    private songDAO SongDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddSongController() {
        super();
        CatDAO = new catDAO();
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
		ArrayList<categories> Categories = CatDAO.getCategories();
		request.setAttribute("Categories", Categories);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/add.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("category"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/song?err=1");
			return;
		}
		String name = request.getParameter("name");
		String youtube = request.getParameter("youtube");
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		Part filePart = request.getPart("picture");
		Timestamp dateCteate = new Timestamp(new Date().getTime());
		final String dirPartName = request.getServletContext().getRealPath("files");
		File dirFile = new File(dirPartName);
	    if(!dirFile.exists()) {
	    	dirFile.mkdir();
	    }
	    String fileName = FileUtil.getName(filePart);
	    String picture = FileUtil.rename(fileName);
	    String filePartName = dirPartName + File.separator + picture;
	    categories Category = new categories(id, null);
	    song Item = new song(0, name, preview, detail, dateCteate, picture, 0, Category,youtube);
	    if(SongDAO.addItem(Item)>0) {
	    	if(!fileName.isEmpty()) {
	    		filePart.write(filePartName);
	    	}
	    	response.sendRedirect(request.getContextPath()+"/admin/song?msg=1");
			return;
	    }
	    else {
	    	ArrayList<categories> Categories = CatDAO.getCategories();
			request.setAttribute("Categories", Categories);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/song/add.jsp?err=1");
			rd.forward(request, response);
	    }
	}

}
