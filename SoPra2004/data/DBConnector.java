package data;

import java.sql.*;

/**
 * Simple Database Connection Class
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *  
 */
public class DBConnector {
	private Thread reconnect;

	Connection con = null;

	private String url;

	private String user;

	private String pass;

	/**
	 * Class Constructor builds a DBConnector with the defined jdbcDriver
	 * 
	 * @param jdbcDriver
	 *            defined jdbcDriver as String
	 */
	public DBConnector(String jdbcDriver) {
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage() + " not found");
			System.exit(1);
		}
	}

	/**
	 * Class Constructor with predefined jdbcDriver
	 */
	public DBConnector() {
		this("org.gjt.mm.mysql.Driver");
	}

	/**
	 * opens the Connection to the Database
	 * 
	 * @param url
	 *            the url of the Database
	 * @param user
	 *            the user of the Database
	 * @param pass
	 *            the password to the defined user of the Database
	 * @return the Connection
	 */
	public Connection openDB(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
		try {
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Verbindung hergestellt");
		} catch (SQLException e) {
			System.err.println("Fehler beim Verbinden");
			reconnect = new Thread(new Reconnector(url, user, pass, this));
			reconnect.start();
		}
		return con;
	}

	/**
	 * Reconnects to the Database
	 * 
	 * @return the Connection
	 */
	Connection reconnect() {
		if (reconnect != null) {
			if (!reconnect.isAlive()) {
				reconnect.start();
			} else {
				System.err.println("Versuche bereits zu verbinden");
			}
		} else {
			reconnect = new Thread(new Reconnector(url, user, pass, this));
			reconnect.start();
		}
		return con;
	}

	/**
	 * Closes the defined Connection
	 * 
	 * @param con
	 *            the defined Connection
	 * @return the boolean-Value of the Connection (true: connection closed)
	 */
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

	/**
	 * Returns the Connection
	 * 
	 * @return the current Connection
	 */
	public Connection getCon() {
		return con;
	}

	/**
	 * Sets a defined Connection
	 * 
	 * @param con
	 *            sets a defined Connection as the current Connection
	 */
	public void setCon(Connection con) {
		this.con = con;
		reconnect = null;
	}
}