/*
 * Created on 07.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;

/**
 * @author ba008959
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.awt.Point;

import javax.swing.*;

import data.*;
public class Mappy {
	public Mappy(){
		
	}
	
	public ImageIcon getMap(){
		DBValues myDBValues = new DBValues();
		Point point = new Point(0, 0);
		int type = 1; 
		ImageIcon img = new ImageIcon ();//myDBValues.getImage(point, type);
		
		return img;
		
	}
}
