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
import java.sql.Statement;

import javax.imageio.ImageIO;
import server.NoImageException;
import server.Tile;

/**
 * @author fkubis
 * $Id: DBValues.java,v 1.7 2004/12/18 17:23:44 fkubis Exp $
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
	
	public Tile getTile(Point p) {
		Tile t = null;
				
		try {
			Statement stmt = this.con.createStatement();
			String sql = "";
			/*
			sql = "SELECT * FROM MapData WHERE " +
				  "XFrom <= " + (int)start.getX() +
				  "AND YFrom = " + (int)start.getY()
				  + " AND Type = " + type;
			*/
			sql = "SELECT * FROM MapData WHERE ID = 196 LIMIT 1";
			System.out.println (sql);
			ResultSet res = stmt.executeQuery(sql);
			res.next();
			int id = res.getInt("ID");
			System.err.println("ID des gewählten Datensatzes: " + id);
			
			t = new Tile(id, res.getInt("XFrom"), res.getInt("XTo"), 
								res.getInt("YFrom"), res.getInt("YTo"), res.getBytes("Data"));
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			// Wenn wir nix getten können brauchen wir noch standard werte für Tile
			System.exit(1);			
		}			
				
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
