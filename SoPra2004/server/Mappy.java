/*
 * Created on 07.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;
/**
 *
 *$Id: Mappy.java,v 1.19 2005/01/12 16:32:57 jesuzz Exp $
 */


import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JProgressBar;

import data.*;

public class Mappy {
	private DBValues DB;
	private MapLabel map;

	public Mappy(){
		DB = new DBValues();
		map = new MapLabel();
	}
	
	public ArrayList getLayers(Dimension d, Point p, int[] layerIds, JProgressBar progress){
		// ArrayList mit Layern
		// die layer haben Zeilen und Spalten und eine ArrayList mit Tiles
		// die Tiles sind fertig eingefärbt und so ...
		
		ArrayList layers = new ArrayList();
		for (int i = 0; i < layerIds.length; i++){
			Layer layer = new Layer(d, p, layerIds[i]+1, DB);
			layers.add(layer);
			progress.setValue(i+1);
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
	
	public void move(String direction){
		System.out.println(direction);
	}
	public void refresh(Point upperLeft, int[] layersToShow, JProgressBar progress){
		map.refresh(this.getLayers(map.getSize(), upperLeft, layersToShow, progress));

	}
	public MapLabel getMapLabel(){
		return map;
	}
}
