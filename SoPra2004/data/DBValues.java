package data;

import java.awt.Dimension;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.Tile;

/**
 * The Values of our Database
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *  
 */
public class DBValues {
	private DBConnector connector;

	private Connection con;

	private String url = "jdbc:mysql://lnxsvc.cip.uni-bamberg.de:3306/SoPraDB";

	private String user = "projekt";

	private String pass = "StPlan";

	private PreparedStatement pstmt;

	private Thread connect;

	/**
	 * Class constructor
	 */
	public DBValues() {
		connect = new Thread(new CreateConnection(this));
		connect.start();
	}

	/**
	 * 
	 * @param url
	 *            The new URL to look for a DB
	 * @param user
	 *            The required username
	 * @param pass
	 *            The required pass
	 */
	public void setDBValues(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
		if (connect.isAlive()) {
			connect.interrupt();
		}
		connect = new Thread(new CreateConnection(this));
		connect.start();
	}

	/**
	 * Returns the current Tiles
	 * 
	 * @param p
	 *            Point of the Tiles
	 * @param dim
	 *            Dimension of the Tiles
	 * @param type
	 *            int-Value of the defined Layer
	 * @return Arraylist with the current Tiles
	 */
	public ArrayList getTiles(Point p, Dimension dim, int type) {
		ArrayList tiles = null;
		ResultSet r = null;
		if (pstmt != null) {
			try {
				pstmt.setInt(1, p.x);
				pstmt.setInt(2, p.x);
				pstmt.setInt(3, dim.width);
				pstmt.setInt(4, p.y);
				pstmt.setInt(5, p.y);
				pstmt.setInt(6, dim.height);
				pstmt.setInt(7, type);
			} catch (SQLException e) {
				System.err.println("SQL Exception: " + e.getMessage());
				connector.setCon(null);
				pstmt = null;
				return tryToReconnect(p, dim, type);
			}
			try {
				r = pstmt.executeQuery();
				r.last();
				int rowCount = r.getRow();
				tiles = new ArrayList();
				r.beforeFirst();
				for (int i = 0; i < rowCount; i++) {
					r.next();
					tiles.add(new Tile(r.getInt("ID"), r.getInt("XFrom"), r
							.getInt("XTo"), r.getInt("YFrom"), r.getInt("YTo"),
							r.getBytes("Data")));
				}
				pstmt.clearParameters();
			} catch (SQLException e) {
				System.err.println("SQL Exception: " + e.getMessage());
				connector.setCon(null);
				pstmt = null;
				return tryToReconnect(p, dim, type);
			}
		} else {
			return tryToReconnect(p, dim, type);
		}
		return tiles;
	}

	private ArrayList tryToReconnect(Point p, Dimension dim, int type) {
		if (connector != null) {
			con = connector.reconnect();
			if (con != null) {
				try {
					pstmt = con
							.prepareStatement("SELECT * FROM MapDataTransparent"
									+ " WHERE (XTo - ?) > 0 AND XFrom < (? + ?) AND (YTo - ?) > 0 AND YFrom < (? + ?)"
									+ " AND Type = ?");
					return getTiles(p, dim, type);
				} catch (SQLException e) {
				}
			}
		}
		return null;
	}

	/**
	 * Returns the status of the connection
	 * 
	 * @return boolean of the connection-Status (true: Connection is closed)
	 */
	public boolean closeConnection() {
		return connector.closeDB(con);
	}

	/**
	 * Returns the connection
	 * 
	 * @return the current Connection
	 */
	public Connection getCon() {
		return con;
	}

	/**
	 * Sets a Connection
	 * 
	 * @param con
	 *            the defined Connection
	 */
	public void setCon(Connection con) {
		this.con = con;
	}

	/**
	 * Returns the Connector
	 * 
	 * @return the current DBConnector
	 */
	public DBConnector getConnector() {
		return connector;
	}

	/**
	 * Sets a DBConnector
	 * 
	 * @param connector
	 *            the defined DBConnector
	 */
	public void setConnector(DBConnector connector) {
		this.connector = connector;
	}

	/**
	 * Returns a prepared Statement
	 * 
	 * @return the current prepared Statement
	 */
	public PreparedStatement getPstmt() {
		return pstmt;
	}

	/**
	 * Sets a Prepared Statement
	 * 
	 * @param pstmt
	 *            the defined Prepared Statement
	 */
	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}

	/**
	 * Returns the current password of the Database
	 * 
	 * @return current password as String
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * Returns the current URL of the Database
	 * 
	 * @return current URL as String
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Returns the current User of the Database
	 * 
	 * @return current User as String
	 */
	public String getUser() {
		return user;
	}
}