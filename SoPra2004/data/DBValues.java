/*
 * Created on 30.11.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package data;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

import server.NoImageException;
import server.Tile;

/**
 * @author fkubis
 * $Id: DBValues.java,v 1.15 2005/01/12 20:12:23 drrsatzteil Exp $
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
	
	public Tile getTile(Point p, int type){
		Tile t = null;
		ResultSet r = null;				
		try{
			Statement stmt = this.con.createStatement();
			String sql;
			sql = "SELECT * " 
				+ "FROM MapDataTransparent " 
				+ "WHERE " 
				+ "XFrom <= " + (int)p.x + " AND " + (int)p.x + " <= XTo "  
				+ "AND YFrom <= " + (int)p.y + " AND " + (int)p.y + " <= YTo "
				+ "AND Type = " + type;
						
			try {
				r = stmt.executeQuery(sql);
				r.next();
				System.out.println("ID des gewählten Datensatzes: " + r.getInt("ID"));
				t = new Tile(r.getInt("ID"), r.getInt("XFrom"), r.getInt("XTo"), r.getInt("YFrom"), r.getInt("YTo"), r.getBytes("Data"));
				return t;
			}
			catch (SQLException e) {
				System.err.println("SQL Exception: " + e.getMessage());	
			}
		}
		catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage() + "Verdammt");
		}
		return new Tile();
	}
}
