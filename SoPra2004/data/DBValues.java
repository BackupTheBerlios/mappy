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
 * $Id: DBValues.java,v 1.10 2004/12/18 19:26:48 fkubis Exp $
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
	
	public Tile getTile(Point p, int type) {
		Tile t = null;
		Object o = new Object();
		ResultSet r = null;				
		try {
			Statement stmt = this.con.createStatement();
			String sql = "";
			
			sql = "SELECT * " 
				+ "FROM MapData " 
				+ "WHERE " 
				+ "XFrom <= " + (int)p.getX() + " AND " + (int)p.getX() + " < XTo "  
				+ "AND YFrom <= " + (int)p.getY() + " AND " + (int)p.getY() + " < YTo "
				+ " AND Type = " + type;
			
			//sql = "SELECT * FROM MapData WHERE ID = 196 LIMIT 1";
			System.out.println (sql);
						
			try {
				r = stmt.executeQuery(sql);
				r.next();
			}
			catch (SQLException e) {
				System.err.println("SQL Exception :" + e.getMessage());
				System.err.println("trotzdem weiter");
				sql = "SELECT * FROM MapData WHERE ID = 196 LIMIT 1";
				System.err.println ("Standard Query FIX IT!");
				r = stmt.executeQuery(sql);								
				r.next();				
			} 
					
			int id = r.getInt("ID");
			System.out.println("ID des gewählten Datensatzes: " + id);
									
			t = new Tile(id, r.getInt("XFrom"), r.getInt("XTo"), r.getInt("YFrom"), r.getInt("YTo"), r.getBytes("Data"));
			return t;
		}
		catch (SQLException e) {
			System.err.println("SQL Exception :" + e.getMessage());
		}
		catch (NullPointerException e) {
			System.err.println("Such den Fehler ...");
		}
		/*		
		catch (Exception e) {
			System.err.println("Error in getTile. Fehler beim fetchen aus der DB: " + e.getMessage());
			// Wenn wir nix getten können brauchen wir noch standard werte für Tile
			//System.exit(1);	
		}
		*/
		
		System.err.println("Da ging was bei getTile daneben");
		return t;
	} 
	
    //public ImageIcon getImage(Point start, int type) throws NoImageException {
	public BufferedImage getImage(Point start, int type) throws NoImageException {
    	try {
    		Statement stmt = this.con.createStatement();
    		String sql = "";
    		sql = "SELECT * FROM MapData WHERE " +
    			  "XFrom <= " + (int)start.getX() +
				  "AND YFrom = " + (int)start.getY()
				  + " AND Type = " + type;
    		
    		sql = "SELECT * FROM MapData WHERE ID = 196 LIMIT 1";
    		System.out.println (sql);
    		ResultSet res = stmt.executeQuery(sql);
    		res.next();
    		int id = res.getInt("ID");
    		System.err.println("ID des gewählten Datensatzes: " + id);
    		// Data
    		// isNull Check einfügen und entsprechenden return wertqa
    		if (res.getBytes("Data") != null) {
    			//ImageIcon img = new ImageIcon(res.getBytes("Data"));
    			//return img;
    			
    			BufferedImage bI = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);    			    			
    			bI = ImageIO.read( res.getUnicodeStream("Data") );
    			return bI;    			
    		} else {
    			throw new NoImageException("Stream aus der DB ist null");
    		}
    		
    	}
    	catch (Exception e) {
    		System.err.println("Error: " + e.getMessage());
    	} 	
    	
        return null;
    }
    
    /*
    public ArrayList getLocationData() {
    	
    	
    }
    */
}
