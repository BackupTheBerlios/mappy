/*
 * Created on 14.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.Dimension;


import data.DBValues;

/**
 * @author ba008961
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Map {
	DBValues myDBValues;
	Point point = new Point(0, 0);
	int type = 1;
	
	Map() {
		this.myDBValues = new DBValues();
	}
	
	public BufferedImage generateMap(Dimension dim, Point point, double zoom) {
		
		try {
			BufferedImage bI = myDBValues.getImage(point, this.type);
		}
		catch ( NoImageException e ) {
			System.err.println("An dieser Stelle existiert keine Kachel");
		}
		
		BufferedImage bufImg = new BufferedImage((int)dim.getWidth(), (int)dim.getHeight(), BufferedImage.TYPE_INT_RGB);
		return bufImg;
	}
}
