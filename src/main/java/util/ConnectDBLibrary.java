package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectDBLibrary {
	private Connection conn;
	private String db;
	private String url;
	private String user;
	private String pass;
	
	public ConnectDBLibrary() {
		Properties properties = PropertiesUtil.readProperties();
		this.db = properties.getProperty("db");
		this.url = properties.getProperty("url") + db;
		this.user = properties.getProperty("user");
		this.pass = properties.getProperty("pass");
	}
	
	public Connection getConnectDBLibrary() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
		ConnectDBLibrary a = new ConnectDBLibrary();
	    if (a.getConnectDBLibrary() !=null){
			System.out.println("success");
		}
	    else {
	    	System.out.println("fail");
	    }
	}
}
