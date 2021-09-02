package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.categories;
import util.ConnectDBLibrary;
import util.DefineUtil;

public class catDAO {
	
	private ConnectDBLibrary connectDBLibrary;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public catDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}

	public ArrayList<categories> getCategories() {
		ArrayList<categories> list = new ArrayList<categories>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM categories ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				categories cat = new categories(id, name);
				list.add(cat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(conn!=null && st!=null && rs!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		return list;
	}

	public int addCategory(String name) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "INSERT INTO categories(name) VALUES(?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(conn!=null && pst!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		return result;
	}

	public  categories getItem(int id) {
		categories item = null;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM categories WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				item = new categories(id, name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(conn!=null && pst!=null && rs!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		return item;
	}

	public int editItem(categories item) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "UPDATE categories SET name = ? WHERE ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getNameCat());
			pst.setInt(2, item.getIdCat());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(conn!=null && pst!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		return result;
	}

	public int delItem(int id) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "DELETE FROM categories WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(conn!=null && pst!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		return result;
	}

	public int NumberOfItems() {
		int count = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT COUNT(*) AS count FROM categories";
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

	public ArrayList<categories> getItemsPagination(int offset) {
		ArrayList<categories> Items = new ArrayList<>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM categories ORDER BY id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				categories Categories = new categories(id,name);
				Items.add(Categories);
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
		String sql = "SELECT COUNT(*) AS count FROM categories WHERE name LIKE ? ";
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

	public ArrayList<categories> searchItems(String name) {
		ArrayList<categories> Items = new ArrayList<>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String Name = "%"+name+"%";
		String sql = "SELECT * FROM categories WHERE name LIKE ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, Name);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String namee = rs.getString("name");
				categories Categories = new categories(id,namee);
				Items.add(Categories);
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
	
	
}
