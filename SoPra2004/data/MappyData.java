/*
 * Created on 30.11.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package data;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * @author ba008961
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MappyData {

	public static void main(String[] args) {
		DBValues myDBValues = new DBValues();
		Point point = new Point(0, 0);
		int type = 1; 
		BufferedImage img = myDBValues.getImage(point, type);
	}
}
