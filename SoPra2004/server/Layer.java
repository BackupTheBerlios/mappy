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
 * $Id: Layer.java,v 1.6 2004/12/18 19:26:48 fkubis Exp $
 */
public class Layer {
	private int lines = 1;
	private int columns = 1;
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
		this.tiles		= new ArrayList();
	}
	
	public boolean fetchTiles() {
		// solange wir d nicht erfüllen müssen wir weiter fetchen
		// unsere Auflösung beschränken wir jetzt mal auf ganze Kacheln
		System.out.println("Layer " + layerId + " Breite: " + dim.getWidth() );
		System.out.println("Layer " + layerId + " Höhe: " + dim.getHeight() );
		
		int i =0;
		boolean b = false;
		Point actPoint = (Point)startP.clone();
		actPoint = new Point(600, 600);
		do {
			try {
				Tile t = DB.getTile(actPoint, layerId);
				this.tiles.add(t);
				/*
				Object o = new Object();
				this.tiles.add(o);
				*/
				b = true;
			}
			catch (NullPointerException e) {
				System.err.println("Fehler beim erstellen der tiles ArrayList: " + e.getMessage());
			}			
			i++;
		} while(i < 1);
		
		return b;
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

	/**
	 * @return
	 */
	public ArrayList getTiles() {
		return tiles;
	}

}
