/*
 * Created on 07.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;
/**
 *
 *$Id: Mappy.java,v 1.16 2005/01/04 21:08:36 drrsatzteil Exp $
 */


import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;

import data.*;

public class Mappy {
	private DBValues DB;
	private MapLabel map;

	public Mappy(){
		DB = new DBValues();
		map = new MapLabel();
	}
	
	public ArrayList getLayers(Dimension d, Point p, int[] layerIds) {
		// ArrayList mit Layern
		// die layer haben Zeilen und Spalten und eine ArrayList mit Tiles
		// die Tiles sind fertig eingefärbt und so ...
		
		ArrayList layers = new ArrayList();
		for (int i = 0; i < layerIds.length; i++){
			Layer layer = new Layer(d, p, layerIds[i]+1, DB);
			layers.add(layer);
		}
		return layers;
	}

	public BufferedImage getMapDemo() {
		System.err.println("getMapDemo");
				
		BufferedImage bI;
				
		Map myMap = new Map();
		bI = myMap.generateMap(new Dimension(), new Point(), (double)100);
		
		System.err.println("Ende getMapDemo");
		return bI;
	}
	
	public void refresh(Point upperLeft, int[] layersToShow){
		map.refresh(this.getLayers(map.getSize(), upperLeft, layersToShow));
	}
	public MapLabel getMapLabel(){
		
		return map;
	}
}
