/*
 * Created on 07.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;
/**
 *
 *$Id: Mappy.java,v 1.32 2005/01/21 11:44:19 drrsatzteil Exp $
 */


import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JPanel;
import javax.swing.JProgressBar;



import data.*;

public class Mappy{
	private DBValues DB;
	private MapLabel map;
	private Color[] layerColors;
	private Color[] layerColorsAlpha;
	private ArrayList pins;

	public Mappy(){
		DB = new DBValues();
		map = new MapLabel();
		pins = new ArrayList();
	}
	
	public ArrayList getLayers(Dimension d, Point p, int zoom, int[] layerIds, JProgressBar progress){
		ArrayList layers = new ArrayList();
		for (int i = 0; i < layerIds.length; i++){
			Layer layer = new Layer(d, p, zoom, layerIds[i]+1, DB);
			layer.setColor(layerColors[layerIds[i]], layerColorsAlpha[layerIds[i]]);
			layers.add(layer);
			progress.setValue(i+1);
		}
		return layers;
	}
	

	public void refresh(Point upperLeft, int[] layersToShow, int zoom, JProgressBar progress, Color[] layerColors, Color[] layerColorsAlpha){

		this.layerColors = layerColors;
		this.layerColorsAlpha = layerColorsAlpha;
		map.refresh(this.getLayers(map.getSize(), upperLeft, zoom, layersToShow, progress), pins, upperLeft);
	}
	public JPanel getMapLabel(){
		return map;
	}
	public boolean closeDB(){
		return DB.closeConnection();
	}
	public void setPin(Point p, String name){
		Integer test = pinExists(p);
		if(test == null){
			pins.add(new Pin(p,name));
		}
		map.setPins(pins);
	}
	public Integer pinExists(Point p){
		Integer index = null;
		if(pins != null){
			ListIterator i = pins.listIterator(0);
			while (i.hasNext()){
				Pin temp = (Pin)i.next();
				Point position = temp.getPosition();
				if(p.x >= position.x - 8 && p.x <= position.x + 8){
					if(p.y >= position.y - 8 && p.y <= position.y + 8){
						index = new Integer(i.nextIndex() - 1);
					}
				}
			}
		}
		return index;
	}
	public void removePin(Point p){
		Integer test = pinExists(p);
		if(test != null){
			pins.remove(test.intValue());
		}
		map.setPins(pins);
	}
}
