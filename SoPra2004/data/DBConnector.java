/*
 * Created on 30.11.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package data;

import java.sql.*;
//import org.gjt.mm.mysql.Driver;
/**
 * @author ba008961
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBConnector {
	public DBConnector(String jdbcDriver) {
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
	
	public DBConnector() {
		this("org.gjt.mm.mysql.Driver");
	}
	
	public Connection openDB(String url, String user, String pass) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			System.err.println("Fehler beim Verbinden");
			return null;
		}
		return con;
	}
	
	public boolean closeDB(Connection con) {
		if (con == null) {
			return false;
		}
			
		try {
			con.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
}
