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
public class DBConnector{
	private Thread reconnect;
	Connection con = null;
	private String url;
	private String user;
	private String pass;
	public DBConnector(String jdbcDriver) {
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage()+ " not found");
			System.exit(1);
		}
	}
	
	public DBConnector(){
		this("org.gjt.mm.mysql.Driver");
	}
	
	public Connection openDB(String url, String user, String pass){
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
	
	Connection reconnect(){
		if(reconnect != null){
			if(!reconnect.isAlive()){
				reconnect.start();
			}
			else{
				System.err.println("Versuche bereits zu verbinden");
			}
		}
		else{
			reconnect = new Thread(new Reconnector(url, user, pass, this));
			reconnect.start();
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
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
		reconnect = null;
	}
}
