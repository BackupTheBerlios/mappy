/*
 * Created on 07.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;
/**
 *
 *$Id: Mappy.java,v 1.30 2005/01/20 01:40:12 drrsatzteil Exp $
 */


import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JProgressBar;



import data.*;

public class Mappy{
	private DBValues DB;
	private MapLabel map;
	private Color[] layerColors;
	private Color[] layerColorsAlpha;

	public Mappy(){
		DB = new DBValues();
		map = new MapLabel();
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
		this.layerColorsAlpha=layerColorsAlpha;
		map.refresh(this.getLayers(map.getSize(), upperLeft, zoom, layersToShow, progress));

	}
	public JPanel getMapLabel(){
		return map;
	}
	public boolean closeDB(){
		return DB.closeConnection();
	}
}
