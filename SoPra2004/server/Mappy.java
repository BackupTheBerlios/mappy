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
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Dimension;

import data.*;

public class Mappy {
	public Mappy(){
		
	}
	
	public Image getMap(){
		Image img=null;
		DBValues myDBValues = new DBValues();
		Point point = new Point(0, 0);
		int type = 1;
		try{
			 img = myDBValues.getImage(point, type);
		}
		catch (Exception e){
			System.err.println("koi bildle");
		}
		
		if (img!=null)System.out.println("Bild wird übergeben...");
		return img;
		
	}
	
	public BufferedImage getMapDemo() {
		System.err.println("getMapDemo");
				
		BufferedImage bI;
				
		Map myMap = new Map();
		bI = myMap.generateMap(new Dimension(), new Point(), (double)100);
		
		System.err.println("Ende getMapDemo");
		return bI;
	}
}
