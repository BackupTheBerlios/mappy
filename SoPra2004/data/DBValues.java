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
 * $Id: DBValues.java,v 1.23 2005/01/17 18:10:34 drrsatzteil Exp $
 */
public class DBValues{
	private DBConnector connector;
	private Connection	con;
	
	private String url = "jdbc:mysql://lnxsvc.cip.uni-bamberg.de:3306/SoPraDB";
	private String user = "projekt";
	private String pass = "StPlan";
	private PreparedStatement pstmt;
	
	public DBValues() {		
		connector = new DBConnector();		
		con = connector.openDB(url, user, pass);
		try {
			pstmt = con.prepareStatement("SELECT * FROM MapDataTransparent" +
			" WHERE (XTo - ?) > 0 AND XFrom < (? + ?) AND (YTo - ?) > 0 AND YFrom < (? + ?)" +
			" AND Type = ?");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public ArrayList getTiles(Point p, Dimension dim, int type){
		ArrayList tiles = null;
		ResultSet r = null;
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
		}
		return tiles;
	}
}
