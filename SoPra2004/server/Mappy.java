/*
 * Created on 07.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;

/**
 * @author fkubis
 * $Id: Mappy.java,v 1.14 2005/01/04 18:41:17 jesuzz Exp $
 */
import java.awt.Image;
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
	}
	
	public ArrayList getLayers(Dimension d, Point p, boolean[] layerIds) {
		// ArrayList mit Layern
		// die layer haben Zeilen und Spalten und eine ArrayList mit Tiles
		// die Tiles sind fertig eingefärbt und so ...
		
		ArrayList layers = new ArrayList();
		
		for (int i = 0; i < layerIds.length; i++) {
			if(layerIds[i]==true){
			System.out.println (layerIds[i]);
			Layer layer = new Layer(d, p, i+1, DB);
			layers.add(layer);
			}
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
	
	public void refresh(Point upperLeft, boolean[] layersToShow){
		map.refresh(this.getLayers(map.getSize(), upperLeft, layersToShow));
	}
	public MapLabel getMapLabel(ArrayList layerList){
		
		map=new MapLabel(layerList);
		return map;
	}
}
