/*
 * Created on 20.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author DrRSatzteil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreateConnection implements Runnable{
	
	private DBConnector connector;
	private Connection con;
	private String url;
	private String user;
	private String pass;
	private PreparedStatement pstmt;
	private DBValues dbv;

	CreateConnection(DBValues dbv){
		this.dbv = dbv;
		this.connector = dbv.getConnector();
		this.con = dbv.getCon();
		this.url = dbv.getUrl();
		this.user = dbv.getUser();
		this.pass = dbv.getPass();
		this.pstmt = dbv.getPstmt();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run(){
		connector = new DBConnector();
		dbv.setConnector(connector);
		con = connector.openDB(url, user, pass);
		dbv.setCon(con);
		if(con != null){
			try {
				pstmt = con.prepareStatement("SELECT * FROM MapDataTransparent" +
				" WHERE (XTo - ?) > 0 AND XFrom < (? + ?) AND (YTo - ?) > 0 AND YFrom < (? + ?)" +
				" AND Type = ?");
				dbv.setPstmt(pstmt);
			}
			catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
}
