/*
 * Created on 18.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


/**
 * @author DrRSatzteil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IOHandler {
	static Point getSavedStart(){
		File path = new File ("save" + File.separatorChar + "start.mpy");
		if(path.exists()){
			try{
				FileInputStream file = new FileInputStream (path);
				ObjectInputStream upperLeft = new ObjectInputStream (file);
				Point start = (Point) upperLeft.readObject();
				upperLeft.close();
				return start;
			}
			catch (IOException e) {
				System.err.println ("Failed to load Startpoint");
				return new Point(0,0);
			}
			catch (ClassNotFoundException e) {
				System.err.println ("Failed to load Startpoint");
				return new Point(0,0);
			}
		}
		else{
			System.err.println("Failed to load StartPoint");
			return new Point(0,0);
		}
	}
}
