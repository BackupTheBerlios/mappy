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
import java.awt.Point;

import javax.swing.*;

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
}
