/*
 * Created on 20.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author DrRSatzteil
 *
 *$Id: Reconnector.java,v 1.1 2005/01/20 01:14:56 drrsatzteil Exp $
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Reconnector implements Runnable{
	Connection con;
	private String url;
	private String user;
	private String pass;
	private DBConnector connector;

	Reconnector(String url, String user, String pass, DBConnector connector){
		this.connector = connector;
		this.con = connector.getCon();
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run(){
		while(con == null){
			try{
				con = DriverManager.getConnection(url, user, pass);
				connector.setCon(con);
			}
			catch(SQLException e){
				System.err.println("Fehler beim Verbinden");
			}
			try {
				Thread.sleep(25000);
			} catch (InterruptedException e1){
				e1.printStackTrace();
			}
		}
		System.out.println("Verbindung hergestellt");
	}
}