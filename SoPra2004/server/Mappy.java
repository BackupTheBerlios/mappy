/*
 * Created on 07.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;

/**
 * @author fkubis
 * $Id: Mappy.java,v 1.12 2004/12/21 13:47:22 drrsatzteil Exp $
 */
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;

import data.*;

public class Mappy {
	private DBValues DB;

	public Mappy(){
		DB = new DBValues();
	}
	
	public ArrayList getLayers(Dimension d, Point p, int[] layerIds) {
		// ArrayList mit Layern
		// die layer haben Zeilen und Spalten und eine ArrayList mit Tiles
		// die Tiles sind fertig eingefärbt und so ...
		
		ArrayList layers = new ArrayList();
		
		for (int i = 0; i < layerIds.length; i++) {
			System.out.println (layerIds[i]);
			Layer layer = new Layer(d, p, layerIds[i], DB);
			layers.add(layer);
		}
		return layers;
	}
	
	private Image getTile(){
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
