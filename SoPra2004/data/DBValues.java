/*
 * Created on 30.11.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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
 * @author fkubis
 * $Id: DBValues.java,v 1.25 2005/01/20 01:40:12 drrsatzteil Exp $
 */
public class DBValues{
	private DBConnector connector;
	private Connection	con;
	
	private String url = "jdbc:mysql://lnxsvc.cip.uni-bamberg.de:3306/SoPraDB";
	private String user = "projekt";
	private String pass = "StPlan";
	private PreparedStatement pstmt;
	
	public DBValues(){
		Thread connect = new Thread(new CreateConnection(this));
		connect.start();
	}
	
	public ArrayList getTiles(Point p, Dimension dim, int type){
		ArrayList tiles = null;
		ResultSet r = null;
		if(pstmt != null){
			try{
				pstmt.setInt(1, p.x);
				pstmt.setInt(2, p.x);
				pstmt.setInt(3, dim.width);
				pstmt.setInt(4, p.y);
				pstmt.setInt(5, p.y);
				pstmt.setInt(6, dim.height);
				pstmt.setInt(7, type);
			}
			catch (SQLException e){
				System.err.println("SQL Exception: " + e.getMessage());
				connector.setCon(null);
				pstmt = null;
				return tryToReconnect(p, dim, type);
			}
			try{
				r = pstmt.executeQuery();
				r.last();
				int rowCount = r.getRow();
				tiles = new ArrayList();
				r.beforeFirst();
				for(int i = 0; i < rowCount; i++){
					r.next();
					tiles.add(new Tile(r.getInt("ID"), r.getInt("XFrom"), r.getInt("XTo"), r.getInt("YFrom"), r.getInt("YTo"), r.getBytes("Data")));
				}
				pstmt.clearParameters();
			}
			catch (SQLException e) {
				System.err.println("SQL Exception: " + e.getMessage());
				connector.setCon(null);
				pstmt = null;
				return tryToReconnect(p, dim, type);
			}
		}
		else{
			return tryToReconnect(p, dim, type);
		}
		return tiles;
	}
	/**
	 * 
	 */
	private ArrayList tryToReconnect(Point p, Dimension dim, int type) {
		if(connector != null){
			con = connector.reconnect();
			if(con != null){
				try{
					pstmt = con.prepareStatement("SELECT * FROM MapDataTransparent" +
							" WHERE (XTo - ?) > 0 AND XFrom < (? + ?) AND (YTo - ?) > 0 AND YFrom < (? + ?)" +
							" AND Type = ?");
					return getTiles(p, dim, type);
				}
				catch(SQLException e){
				}
			}
		}
		return null;
	}
	
	public boolean closeConnection(){
		return connector.closeDB(con);
	}

	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	public DBConnector getConnector() {
		return connector;
	}
	public void setConnector(DBConnector connector) {
		this.connector = connector;
	}
	public PreparedStatement getPstmt() {
		return pstmt;
	}
	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}
	public String getPass() {
		return pass;
	}
	public String getUrl() {
		return url;
	}
	public String getUser() {
		return user;
	}
}
