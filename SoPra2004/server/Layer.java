/*
 * Created on 18.12.2004
 *
 */
package server;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import data.DBValues;

/**
 * @author fkubis
 * $Id: Layer.java,v 1.4 2004/12/18 17:23:44 fkubis Exp $
 */
public class Layer {
	private int lines;
	private int columns;
	private int layerId;		
	private ArrayList tiles;
	
	private Dimension dim;
	private Point startP;
	private DBValues DB;
		
	Layer(Dimension d, Point p, int layerId, DBValues DB) {
		dim 			= d;
		startP 			= p;
		this.layerId 	= layerId;
		this.DB 		= DB;
	}
	
	public boolean fetchTiles() {
		// solange wir d nicht erfüllen müssen wir weiter fetchen
		// unsere Auflösung beschränken wir jetzt mal auf ganze Kacheln
		System.out.println("Layer " + layerId + " Breite: " + dim.getWidth() );
		System.out.println("Layer " + layerId + " Höhe: " + dim.getHeight() );
		
		int i =0;
		Point actPoint = (Point)startP.clone();		
		do {
			tiles.add(DB.getTile(actPoint));
			i++;
		} while(i < 10);
		
		return false;
	}
	
	/**
	 * @return
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @return
	 */
	public int getLines() {
		return lines;
	}

	/**
	 * @param i
	 */
	public void setColumns(int i) {
		columns = i;
	}

	/**
	 * @param i
	 */
	public void setLines(int i) {
		lines = i;
	}

	/**
	 * @return
	 */
	public int getLayerId() {
		return layerId;
	}

	/**
	 * @param i
	 */
	public void setLayerId(int i) {
		layerId = i;
	}

}
