package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.contact;
import util.ConnectDBLibrary;
import util.DefineUtil;

public class contactDAO {
	
	private ConnectDBLibrary connectDBLibrary;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public contactDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public ArrayList<contact> getItems() {
		ArrayList<contact> contacts = new ArrayList<contact>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM contacts";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String website = rs.getString("website");
				String message = rs.getString("message");
				contact Contact = new contact(id,name, email, website, message);
				contacts.add(Contact);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(conn!=null && rs!=null && st!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return contacts;
	}

	public int delItem(int id) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "DELETE FROM contacts WHERE id =? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
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

	public int getItems(contact contact) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "INSERT INTO contacts(name, email, website, message) VALUES(?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getEmail());
			pst.setString(3, contact.getWebsite());
			pst.setString(4, contact.getMessage());
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
		String sql = "SELECT COUNT(*) AS count FROM contacts ORDER BY id DESC";
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

	public ArrayList<contact> getItemsPagination(int offset) {
		ArrayList<contact> contacts = new ArrayList<contact>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM contacts ORDER BY id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2,DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String website = rs.getString("website");
				String message = rs.getString("message");
				contact Contact = new contact(id, name, email, website, message);
				contacts.add(Contact);
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
		return contacts;
	}

	public int searchNumberOfItems(String name) {
		int count = 0;
		String Name = "%"+name+"%";
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT COUNT(*) AS count FROM contacts WHERE name LIKE ?";
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

	public ArrayList<contact> searchItems(String name) {
		ArrayList<contact> contacts = new ArrayList<contact>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String Name = "%"+name+"%";
		String sql = "SELECT * FROM contacts WHERE name LIKE ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, Name);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String namee = rs.getString("name");
				String email = rs.getString("email");
				String website = rs.getString("website");
				String message = rs.getString("message");
				contact Contact = new contact(id, namee, email, website, message);
				contacts.add(Contact);
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
		return contacts;
	}
	
	
}
