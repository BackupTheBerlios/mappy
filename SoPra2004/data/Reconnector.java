package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Reconnector Class for rebuilding a connection
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *  
 */
public class Reconnector implements Runnable {
	Connection con;

	private String url;

	private String user;

	private String pass;

	private DBConnector connector;

	/**
	 * Class constructor
	 * 
	 * @param url
	 *            the url of the Database
	 * @param user
	 *            the user of the Database
	 * @param pass
	 *            the password to the defined user of the Database
	 * @param connector
	 *            the DBconnector whcih should be reconnected
	 */
	Reconnector(String url, String user, String pass, DBConnector connector) {
		this.connector = connector;
		this.con = connector.getCon();
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (con == null) {
			try {
				con = DriverManager.getConnection(url, user, pass);
				connector.setCon(con);
			} catch (SQLException e) {
				System.err.println("Fehler beim Verbinden");
			}
			try {
				Thread.sleep(25000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Verbindung hergestellt");
	}
}