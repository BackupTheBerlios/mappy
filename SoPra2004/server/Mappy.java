/*
 * Created on 07.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;

import java.awt.Point;
import java.awt.image.BufferedImage;

import data.*;

public class Mappy {
	public Mappy(){
		
	}
	
	public BufferedImage getMap(Point start, int type){
		BufferedImage img = null;
		DBValues myDBValues = new DBValues();
		try{
			 img = myDBValues.getImage(start, type);
		}
		catch (Exception e){
			System.err.println("koi bildle");
		}
		
		if (img!=null) {
			System.out.println("Bild wird übergeben...");
		}
		return img;
	}
}
