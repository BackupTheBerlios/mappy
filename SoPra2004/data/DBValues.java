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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.Tile;

/**
 * @author fkubis
 * $Id: DBValues.java,v 1.18 2005/01/13 11:51:42 drrsatzteil Exp $
 */
public class DBValues {
	private DBConnector connector;
	private Connection	con;
	
	private String url = "jdbc:mysql://lnxsvc.cip.uni-bamberg.de:3306/SoPraDB";
	private String user = "projekt";
	private String pass = "StPlan";
	
	public DBValues() {		
		this.connector = new DBConnector();		
		this.con = this.connector.openDB(this.url, this.user, this.pass);
	}
	
	public Tile[] getTiles(Point p, Dimension dim, int type){
		Tile[] tiles = null;
		ResultSet r = null;				
		try{
			Statement stmt = this.con.createStatement();
			String sql;
			sql = "SELECT * FROM MapDataTransparent WHERE (XTo - " + p.x +
			") >= 0 AND XFrom < " + (p.x + dim.width) + " AND (YTo - " + p.y + ") >= 0 AND YFrom < " + (p.y + dim.height)
			+ " AND Type = " + type + " ORDER BY ID";
						
			try{
				r = stmt.executeQuery(sql);
				r.last();
				int rowCount = r.getRow();
				tiles = new Tile[rowCount];
				r.beforeFirst();
				for(int i = 0; i < rowCount; i++){
					r.next();
					tiles[i] = new Tile(r.getInt("ID"), r.getInt("XFrom"), r.getInt("XTo"), r.getInt("YFrom"), r.getInt("YTo"), r.getBytes("Data"));
				}
			}
			catch (SQLException e) {
				System.err.println("SQL Exception: " + e.getMessage());	
			}
		}
		catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		}
		return tiles;
	}
}
