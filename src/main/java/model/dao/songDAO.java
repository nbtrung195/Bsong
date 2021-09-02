package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.bean.categories;
import model.bean.song;
import util.ConnectDBLibrary;
import util.DefineUtil;

public class songDAO {
	
	private ConnectDBLibrary connectDBLibrary;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs; 

	public songDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public ArrayList<song> getItems() {
		ArrayList<song> Items = new ArrayList<song>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT s.id AS sid, s.name AS sname, s.preview_text, s.detail_text, s.date_create, s.id_cat, s.picture, s.counter, c.name, youtube"
				      + " FROM songs AS s INNER JOIN categories AS c ON s.id_cat = c.id ORDER BY s.id DESC;";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("sid");
				String name = rs.getString("sname");
				String preview_text = rs.getString("preview_text");
				String detail_text = rs.getString("detail_text");
				Timestamp date_create = rs.getTimestamp("date_create");
				String picture = rs.getString("picture");
				int counter = rs.getInt("counter");
				String youtube = rs.getString("youtube");
				categories Categories = new categories(rs.getInt("id_cat"), rs.getString("c.name"));
				song Song = new song(id,name, preview_text, detail_text, date_create, picture, counter,Categories,youtube);
				Items.add(Song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Items;
	}

	public int addItem(song Item) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "INSERT INTO songs(name, preview_text, detail_text, date_create, picture, counter, id_cat,youtube) VALUES(?,?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, Item.getName());
			pst.setString(2, Item.getPreviewText());
			pst.setString(3, Item.getDetailText());
			pst.setTimestamp(4, Item.getDateCreate());
			pst.setString(5, Item.getPicture());
			pst.setInt(6, Item.getCounter());
			pst.setInt(7, Item.getCategory().getIdCat());
			pst.setString(8, Item.getYoutube());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public song getItem(int id) {
		song Song = null;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT id , name, preview_text, detail_text, date_create, id_cat, picture, counter, youtube FROM songs WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String preview_text = rs.getString("preview_text");
				String detail_text = rs.getString("detail_text");
				Timestamp date_create = rs.getTimestamp("date_create");
				String picture = rs.getString("picture");
				int counter = rs.getInt("counter");
				String youtube = rs.getString("youtube");
				categories Categories = new categories(rs.getInt("id_cat"), null);
				Song = new song(id,name, preview_text, detail_text, date_create, picture, counter,Categories,youtube);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Song;
	}

	public int editItem(song item) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "UPDATE songs SET name=?, preview_text=?, detail_text=?,  picture=?,  id_cat=?, youtube=? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setString(2, item.getPreviewText());
			pst.setString(3, item.getDetailText());
			pst.setString(4, item.getPicture());
			pst.setInt(5, item.getCategory().getIdCat());
			pst.setString(6, item.getYoutube());
			pst.setInt(7, item.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int delItem(int id) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "DELETE FROM songs WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public ArrayList<song> getItems(int number) {
		ArrayList<song> Items = new ArrayList<song>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT s.id AS sid, s.name AS sname, s.preview_text, s.detail_text, s.date_create, s.id_cat, s.picture, s.counter, c.name, s.youtube"
				      + " FROM songs AS s INNER JOIN categories AS c ON s.id_cat = c.id ORDER BY s.id DESC LIMIT ?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, number);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("sid");
				String name = rs.getString("sname");
				String preview_text = rs.getString("preview_text");
				String detail_text = rs.getString("detail_text");
				Timestamp date_create = rs.getTimestamp("date_create");
				String picture = rs.getString("picture");
				int counter = rs.getInt("counter");
				String youtube = rs.getString("youtube");
				categories Categories = new categories(rs.getInt("id_cat"), rs.getString("c.name"));
				song Song = new song(id,name, preview_text, detail_text, date_create, picture, counter,Categories,youtube);
				Items.add(Song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Items;
	}

	public ArrayList<song> getItemsByCategories(int catId) {
		ArrayList<song> Items = new ArrayList<song>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT s.id AS sid, s.name AS sname, s.preview_text, s.detail_text, s.date_create, s.id_cat, s.picture, s.counter, c.name, s.youtube"
				      + " FROM songs AS s INNER JOIN categories AS c ON s.id_cat = c.id WHERE s.id_cat = ? ORDER BY s.id DESC ;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catId);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("sid");
				String name = rs.getString("sname");
				String preview_text = rs.getString("preview_text");
				String detail_text = rs.getString("detail_text");
				Timestamp date_create = rs.getTimestamp("date_create");
				String picture = rs.getString("picture");
				int counter = rs.getInt("counter");
				String youtube = rs.getString("youtube");
				categories Categories = new categories(rs.getInt("id_cat"), rs.getString("c.name"));
				song Song = new song(id,name, preview_text, detail_text, date_create, picture, counter,Categories,youtube);
				Items.add(Song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Items;
	}

	public ArrayList<song> getRelatedItems(song Song, int i) {
		ArrayList<song> Items = new ArrayList<song>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT s.id AS sid, s.name AS sname, s.preview_text, s.detail_text, s.date_create, s.id_cat, s.picture, s.counter, c.name, s.youtube"
				      + " FROM songs AS s INNER JOIN categories AS c ON s.id_cat = c.id WHERE s.id_cat = ? && s.id != ? ORDER BY s.id DESC LIMIT ?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Song.getCategory().getIdCat());
			pst.setInt(2, Song.getId());
			pst.setInt(3, i);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("sid");
				String name = rs.getString("sname");
				String preview_text = rs.getString("preview_text");
				String detail_text = rs.getString("detail_text");
				Timestamp date_create = rs.getTimestamp("date_create");
				String picture = rs.getString("picture");
				int counter = rs.getInt("counter");
				String youtube = rs.getString("youtube");
				categories Categories = new categories(rs.getInt("id_cat"), rs.getString("c.name"));
				song Songs = new song(id,name, preview_text, detail_text, date_create, picture, counter,Categories,youtube);
				Items.add(Songs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Items;
	}

	public void increaseView(int id) {
		// TODO Auto-generated method stub
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "UPDATE songs SET counter = counter + 1 WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int NumberOfItems() {
		int count = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT COUNT(*) AS count FROM songs";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	public ArrayList<song> getItemsPagination(int offset) {
		ArrayList<song> Items = new ArrayList<song>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT s.id AS sid, s.name AS sname, s.preview_text, s.detail_text, s.date_create, s.id_cat, s.picture, s.counter, c.name, s.youtube"
				      + " FROM songs AS s INNER JOIN categories AS c ON s.id_cat = c.id ORDER BY s.id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("sid");
				String name = rs.getString("sname");
				String preview_text = rs.getString("preview_text");
				String detail_text = rs.getString("detail_text");
				Timestamp date_create = rs.getTimestamp("date_create");
				String picture = rs.getString("picture");
				int counter = rs.getInt("counter");
				String youtube = rs.getString("youtube");
				categories Categories = new categories(rs.getInt("id_cat"), rs.getString("c.name"));
				song Song = new song(id,name, preview_text, detail_text, date_create, picture, counter,Categories,youtube);
				Items.add(Song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Items;
	}

	public int NumberOfItems(int id) {
		int count = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT COUNT(*) AS count FROM songs WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	public ArrayList<song> getItemsByCategoriesPagination(int offset, int catId) {
		ArrayList<song> Items = new ArrayList<song>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT s.id AS sid, s.name AS sname, s.preview_text, s.detail_text, s.date_create, s.id_cat, s.picture, s.counter, c.name, s.youtube"
				      + " FROM songs AS s INNER JOIN categories AS c ON s.id_cat = c.id WHERE s.id_cat = ? ORDER BY s.id DESC LIMIT ?, ?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catId);
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("sid");
				String name = rs.getString("sname");
				String preview_text = rs.getString("preview_text");
				String detail_text = rs.getString("detail_text");
				Timestamp date_create = rs.getTimestamp("date_create");
				String picture = rs.getString("picture");
				int counter = rs.getInt("counter");
				String youtube = rs.getString("youtube");
				categories Categories = new categories(rs.getInt("id_cat"), rs.getString("c.name"));
				song Song = new song(id,name, preview_text, detail_text, date_create, picture, counter,Categories,youtube);
				Items.add(Song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Items;
	}

	public int searchNumberOfItems(String name) {
		int count = 0;
		String Name = "%"+name+"%";
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT COUNT(*) AS count FROM songs WHERE name LIKE ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, Name);
			rs = pst.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	public ArrayList<song> searchItems(String name) {
		ArrayList<song> songs = new ArrayList<song>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String Name = "%"+name+"%";
		String sql = "SELECT s.id AS sid, s.name AS sname, s.preview_text, s.detail_text, s.date_create, s.id_cat, s.picture, s.counter, c.name,s.youtube"
			           + " FROM songs AS s INNER JOIN categories AS c ON s.id_cat = c.id WHERE s.name LIKE ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, Name);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("sid");
				String namee = rs.getString("sname");
				String preview_text = rs.getString("preview_text");
				String detail_text = rs.getString("detail_text");
				Timestamp date_create = rs.getTimestamp("date_create");
				String picture = rs.getString("picture");
				int counter = rs.getInt("counter");
				String youtube = rs.getString("youtube");
				categories Categories = new categories(rs.getInt("id_cat"), rs.getString("c.name"));
				song Song = new song(id,namee, preview_text, detail_text, date_create, picture, counter,Categories,youtube);
				songs.add(Song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return songs;
	}

}
